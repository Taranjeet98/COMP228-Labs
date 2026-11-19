package wk10;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    String url = "jdbc:mysql://localhost:3306/employment_db";
    String username = "root";
    String password = "Matharu@1998";

    TextField txtFullName = new TextField();
    TextField txtAddress = new TextField();
    TextField txtContact = new TextField();
    TextField txtEmail = new TextField();
    TextField txtEducation = new TextField();
    RadioButton rbMale = new RadioButton("Male");
    RadioButton rbFemale = new RadioButton("Female");
    RadioButton rbOther = new RadioButton("Other");
    ToggleGroup genderGroup = new ToggleGroup();
    DatePicker dpDate = new DatePicker();
    TextField txtPosition = new TextField();
    TextField txtSalary = new TextField();
    RadioButton rbAuthYes = new RadioButton("Yes");
    RadioButton rbAuthNo = new RadioButton("No");
    ToggleGroup authGroup = new ToggleGroup();
    RadioButton rbRelYes = new RadioButton("Yes");
    RadioButton rbRelNo = new RadioButton("No");
    ToggleGroup relGroup = new ToggleGroup();
    TextField txtExplain = new TextField();
    TextField txtSignature = new TextField();

    @Override
    public void start(Stage stage) {

        rbMale.setToggleGroup(genderGroup);
        rbFemale.setToggleGroup(genderGroup);
        rbOther.setToggleGroup(genderGroup);
        rbAuthYes.setToggleGroup(authGroup);
        rbAuthNo.setToggleGroup(authGroup);
        rbRelYes.setToggleGroup(relGroup);
        rbRelNo.setToggleGroup(relGroup);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        grid.add(new Label("Full Name"), 0, 0);
        grid.add(txtFullName, 1, 0);
        grid.add(new Label("Current Address"), 0, 1);
        grid.add(txtAddress, 1, 1);
        grid.add(new Label("Contact Number"), 0, 2);
        grid.add(txtContact, 1, 2);
        grid.add(new Label("Email Address"), 0, 3);
        grid.add(txtEmail, 1, 3);
        grid.add(new Label("Highest Educational Attainment"), 0, 4);
        grid.add(txtEducation, 1, 4);
        grid.add(new Label("Gender"), 0, 5);
        grid.add(new HBox(10, rbMale, rbFemale, rbOther), 1, 5);
        grid.add(new Label("Date Available"), 0, 6);
        grid.add(dpDate, 1, 6);
        grid.add(new Label("Desired Position"), 0, 7);
        grid.add(txtPosition, 1, 7);
        grid.add(new Label("Desired Salary"), 0, 8);
        grid.add(txtSalary, 1, 8);
        grid.add(new Label("Legally authorized to work?"), 0, 9);
        grid.add(new HBox(10, rbAuthYes, rbAuthNo), 1, 9);
        grid.add(new Label("Relatives working for our company?"), 0, 10);
        grid.add(new HBox(10, rbRelYes, rbRelNo), 1, 10);
        grid.add(new Label("If yes, please explain further"), 0, 11);
        grid.add(txtExplain, 1, 11);
        grid.add(new Label("Applicant's Signature"), 0, 12);
        grid.add(txtSignature, 1, 12);

        Button btnSubmit = new Button("Submit");
        btnSubmit.setOnAction(e -> submit());

        Label lblTitle = new Label("Employment Application");
        lblTitle.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        VBox root = new VBox(15, lblTitle, grid, btnSubmit);
        root.setPadding(new Insets(20));

        stage.setScene(new Scene(root, 600, 650));
        stage.setTitle("Employment Application");
        stage.show();
    }
    
    void submit() {
        String qry = "insert into applications (full_name, current_address, contact_number, email, education, gender, date_available, desired_position, desired_salary, authorized_to_work, has_relatives, relatives_details, signature) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            Connection conn = DriverManager.getConnection(url, username, password);
            PreparedStatement ps = conn.prepareStatement(qry);

            ps.setString(1, txtFullName.getText().trim());
            ps.setString(2, txtAddress.getText().trim());
            ps.setString(3, txtContact.getText().trim());
            ps.setString(4, txtEmail.getText().trim());
            ps.setString(5, txtEducation.getText().trim());
            ps.setString(6, ((RadioButton) genderGroup.getSelectedToggle()).getText());
            ps.setDate(7, dpDate.getValue() != null ? Date.valueOf(dpDate.getValue()) : null);
            ps.setString(8, txtPosition.getText().trim());
            ps.setString(9, txtSalary.getText().trim());
            ps.setString(10, ((RadioButton) authGroup.getSelectedToggle()).getText());
            ps.setString(11, ((RadioButton) relGroup.getSelectedToggle()).getText());
            ps.setString(12, txtExplain.getText().trim());
            ps.setString(13, txtSignature.getText().trim());

            ps.executeUpdate();

            ps.close();
            conn.close();

            new Alert(Alert.AlertType.INFORMATION, "Successful!").showAndWait();

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).showAndWait();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}