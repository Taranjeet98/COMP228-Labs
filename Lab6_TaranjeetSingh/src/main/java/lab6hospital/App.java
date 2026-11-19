package lab6hospital;

import java.sql.SQLException;
import java.time.LocalDate;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    PatientDAO dao = new PatientDAO();
    ObservableList<Patient> data = FXCollections.observableArrayList();
    TextField txtId = new TextField();
    TextField txtName = new TextField();
    TextField txtAge = new TextField();
    ComboBox<String> cmbGender = new ComboBox<>();
    TextField txtPhone = new TextField();
    TextField txtDiagnosis = new TextField();
    DatePicker dpDate = new DatePicker();
    TextField txtSearch = new TextField();
    TableView<Patient> table = new TableView<>();
    Label lblStatus = new Label("Ready");

    @Override
    public void start(Stage stage) {
        Label lblTitle = new Label("Hospital Management System");
        lblTitle.setStyle("-fx-font-size: 22px; -fx-font-weight: bold;");
        Label lblSubtitle = new Label("Patient Registration Form");
        VBox top = new VBox(5, lblTitle, lblSubtitle);
        top.setPadding(new Insets(15));

        txtId.setEditable(false);
        cmbGender.getItems().addAll("Female", "Male", "Non-binary", "Not to say");

        GridPane form = new GridPane();
        form.setHgap(10);
        form.setVgap(10);
        form.add(new Label("Patient ID"), 0, 0);
        form.add(txtId, 1, 0);
        form.add(new Label("Full name"), 0, 1);
        form.add(txtName, 1, 1);
        form.add(new Label("Age"), 0, 2);
        form.add(txtAge, 1, 2);
        form.add(new Label("Gender"), 0, 3);
        form.add(cmbGender, 1, 3);
        form.add(new Label("Phone"), 0, 4);
        form.add(txtPhone, 1, 4);
        form.add(new Label("Diagnosis"), 0, 5);
        form.add(txtDiagnosis, 1, 5);
        form.add(new Label("Admission date"), 0, 6);
        form.add(dpDate, 1, 6);

        Button btnAdd = new Button("Add Patient");
        Button btnUpdate = new Button("Update");
        Button btnDelete = new Button("Delete");
        Button btnClear = new Button("Clear");
        HBox buttons = new HBox(10, btnAdd, btnUpdate, btnDelete, btnClear);

        VBox left = new VBox(15, new Label("Patient Details"), form, buttons);
        txtSearch.setPromptText("Search by name or phone");
        Button btnSearch = new Button("Search");
        Button btnShowAll = new Button("Show All");
        HBox searchRow = new HBox(10, txtSearch, btnSearch, btnShowAll);

        TableColumn<Patient, Integer> colId = new TableColumn<>("ID");
        colId.setCellValueFactory(new PropertyValueFactory<>("patientId"));

        TableColumn<Patient, String> colName = new TableColumn<>("Name");
        colName.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Patient, Integer> colAge = new TableColumn<>("Age");
        colAge.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Patient, String> colGender = new TableColumn<>("Gender");
        colGender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Patient, String> colPhone = new TableColumn<>("Phone");
        colPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Patient, String> colDiagnosis = new TableColumn<>("Diagnosis");
        colDiagnosis.setCellValueFactory(new PropertyValueFactory<>("diagnosis"));

        TableColumn<Patient, LocalDate> colDate = new TableColumn<>("Admission Date");
        colDate.setCellValueFactory(new PropertyValueFactory<>("admissionDate"));

        table.getColumns().addAll(colId, colName, colAge, colGender, colPhone, colDiagnosis, colDate);
        table.setItems(data);
        table.setPrefWidth(650);

        VBox right = new VBox(10, new Label("Patient Records"), searchRow, table);
        HBox center = new HBox(20, left, right);
        center.setPadding(new Insets(15));

        lblStatus.setPadding(new Insets(10, 15, 10, 15));
        BorderPane root = new BorderPane();
        root.setTop(top);
        root.setCenter(center);
        root.setBottom(lblStatus);
        btnAdd.setOnAction(e -> addPatient());
        btnUpdate.setOnAction(e -> updatePatient());
        btnDelete.setOnAction(e -> deletePatient());
        btnClear.setOnAction(e -> clearForm());
        btnSearch.setOnAction(e -> searchPatient());
        btnShowAll.setOnAction(e -> showAllPatients());

        table.setOnMouseClicked(e -> fillFormFromTable());

        stage.setScene(new Scene(root, 1150, 600));
        stage.setTitle("Hospital Management System");
        stage.show();

        showAllPatients();
    }

    void addPatient() {
        if (!validateForm()) {
            return;
        }
        try {
            Patient p = new Patient(0, txtName.getText().trim(),
                    Integer.parseInt(txtAge.getText().trim()),
                    cmbGender.getValue(), txtPhone.getText().trim(),
                    txtDiagnosis.getText().trim(), dpDate.getValue());
            dao.insert(p);
            showAllPatients();
            clearForm();
            showMessage("Patient added successfully.", false);
        } catch (SQLException e) {
            showMessage("Database error: " + e.getMessage(), true);
        }
    }

    void showAllPatients() {
        try {
            data.setAll(dao.findAll());
            showMessage("Loaded " + data.size() + " record(s).", false);
        } catch (SQLException e) {
            showMessage("Database error: " + e.getMessage(), true);
        }
    }

    void updatePatient() {
        if (txtId.getText().isEmpty()) {
            showMessage("Select a patient from the table first.", true);
            return;
        }
        if (!validateForm()) {
            return;
        }
        try {
            Patient p = new Patient(Integer.parseInt(txtId.getText()), txtName.getText().trim(),
                    Integer.parseInt(txtAge.getText().trim()),
                    cmbGender.getValue(), txtPhone.getText().trim(),
                    txtDiagnosis.getText().trim(), dpDate.getValue());
            dao.update(p);
            showAllPatients();
            clearForm();
            showMessage("Patient updated successfully.", false);
        } catch (SQLException e) {
            showMessage("Database error: " + e.getMessage(), true);
        }
    }

    void deletePatient() {
        if (txtId.getText().isEmpty()) {
            showMessage("Select a patient from the table first.", true);
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION,
                "Delete this patient record?", ButtonType.YES, ButtonType.NO);
        confirm.showAndWait();

        if (confirm.getResult() == ButtonType.YES) {
            try {
                dao.delete(Integer.parseInt(txtId.getText()));
                showAllPatients();
                clearForm();
                showMessage("Patient deleted.", false);
            } catch (SQLException e) {
                showMessage("Database error: " + e.getMessage(), true);
            }
        }
    }

    void searchPatient() {
        String keyword = txtSearch.getText().trim();
        if (keyword.isEmpty()) {
            showMessage("Enter a name or phone to search.", true);
            return;
        }
        try {
            data.setAll(dao.search(keyword));
            showMessage(data.size() + " record(s) found.", false);
        } catch (SQLException e) {
            showMessage("Database error: " + e.getMessage(), true);
        }
    }

    boolean validateForm() {
        if (txtName.getText().trim().isEmpty() || txtAge.getText().trim().isEmpty()
                || cmbGender.getValue() == null || txtPhone.getText().trim().isEmpty()
                || txtDiagnosis.getText().trim().isEmpty() || dpDate.getValue() == null) {
            showMessage("All fields are required.", true);
            return false;
        }

        int age;
        try {
            age = Integer.parseInt(txtAge.getText().trim());
        } catch (NumberFormatException e) {
            showMessage("Age must be a whole number.", true);
            return false;
        }
        if (age < 0 || age > 125) {
            showMessage("Age must be between 0 and 125.", true);
            return false;
        }

        if (!txtPhone.getText().trim().matches("[0-9+()\\- ]{7,20}")) {
            showMessage("Enter a valid phone number.", true);
            return false;
        }

        if (dpDate.getValue().isAfter(LocalDate.now())) {
            showMessage("Admission date cannot be in the future.", true);
            return false;
        }
        return true;
    }

    void fillFormFromTable() {
        Patient p = table.getSelectionModel().getSelectedItem();
        if (p != null) {
            txtId.setText(String.valueOf(p.getPatientId()));
            txtName.setText(p.getName());
            txtAge.setText(String.valueOf(p.getAge()));
            cmbGender.setValue(p.getGender());
            txtPhone.setText(p.getPhone());
            txtDiagnosis.setText(p.getDiagnosis());
            dpDate.setValue(p.getAdmissionDate());
        }
    }

    void clearForm() {
        txtId.clear();
        txtName.clear();
        txtAge.clear();
        cmbGender.setValue(null);
        txtPhone.clear();
        txtDiagnosis.clear();
        dpDate.setValue(null);
        table.getSelectionModel().clearSelection();
    }

    void showMessage(String message, boolean isError) {
        lblStatus.setText(message);
        if (isError) {
            lblStatus.setStyle("-fx-text-fill: red;");
        } else {
            lblStatus.setStyle("-fx-text-fill: green;");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}