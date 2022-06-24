package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class StudentFormController{
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
    public JFXTextField txtRoomNum;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXComboBox cmbKeyMoney;
    public AnchorPane root;
    public TableColumn colStuId;
    public TableColumn colStuName;
    public TableColumn colStuAddress;
    public TableColumn colRoomNum;
    public TableColumn colKeyMoneyStat;
    public TableView tblStudent;

    public void initialize(){

    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
    }

    public void textFields_Key_Released(KeyEvent keyEvent) {

    }

    public void navigateToHome(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/DashboardForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }

    public void closeWindowOnAction(ActionEvent actionEvent) {
        javafx.application.Platform.exit();
    }

    public void navigateToLogin(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoggingForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}
