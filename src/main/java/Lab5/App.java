package Lab5;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) {
        BorderPane bPane = new BorderPane();

        Label mainTitle = new Label("Student Registration System");
        mainTitle.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        HBox topHBox = new HBox(mainTitle);
        topHBox.setPadding(new Insets(15));
        topHBox.setStyle("-fx-background-color: #598baf; -fx-alignment: center;");
        bPane.setTop(topHBox);

        Label menuTitle = new Label("MENU");
        menuTitle.setStyle("-fx-font-weight: bold;");
        Button dBtn = new Button("Dashboard");
        Button rBtn = new Button("Registration");
        Button cBtn = new Button("Courses");
        Button hBtn = new Button("Help");

        VBox nav = new VBox(12, menuTitle, dBtn, rBtn, cBtn, hBtn);
        nav.setPadding(new Insets(15));
        nav.setPrefWidth(160);
        nav.setStyle("-fx-background-color: #f1f3f5;");
        bPane.setLeft(nav);

        Label formTitle = new Label("Registration Form");
        formTitle.setStyle("-fx-font-weight: bold;");

        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 0, 10, 0));

        Label lblId = new Label("Student ID:");
        TextField txtId = new TextField();
        
        Label lblName = new Label("Full Name:");
        TextField txtName = new TextField();
        
        Label lblEmail = new Label("Email:");
        TextField txtEmail = new TextField();
        
        Label lblProgram = new Label("Program:");
        TextField txtProgram = new TextField();

        gridPane.add(lblId, 0, 0);
        gridPane.add(txtId, 1, 0);
        gridPane.add(lblName, 0, 1);
        gridPane.add(txtName, 1, 1);
        gridPane.add(lblEmail, 0, 2);
        gridPane.add(txtEmail, 1, 2);
        gridPane.add(lblProgram, 0, 3);
        gridPane.add(txtProgram, 1, 3);
        Button regBtn = new Button("Register");
        Button clrBtn = new Button("Clear");
        
        HBox actionHBox = new HBox(10, regBtn, clrBtn);
        Label lblStatus = new Label();
        regBtn.setOnAction(e -> {
            if (txtId.getText().isEmpty() || txtName.getText().isEmpty() || 
            		txtEmail.getText().isEmpty() || 
            		txtProgram.getText().isEmpty()) {
                lblStatus.setText("Error: Please fill in all fields.");
                lblStatus.setStyle("-fx-text-fill: red;");
            } 
            else {
                lblStatus.setText("Success: Student registered successfully!");
                lblStatus.setStyle("-fx-text-fill: green;");
            }
        });

        clrBtn.setOnAction(e -> {
            txtId.clear();
            txtName.clear();
            txtEmail.clear();
            txtProgram.clear();
            lblStatus.setText("");
        });

        VBox centerVBox = new VBox(12, formTitle, gridPane, actionHBox, lblStatus);
        centerVBox.setPadding(new Insets(15));
        bPane.setCenter(centerVBox);
        
        Scene scene = new Scene(bPane, 650, 400);
        primaryStage.setTitle("Student Registration");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}