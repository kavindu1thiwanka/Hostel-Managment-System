package controller;

import bo.BOFactory;
import bo.impl.StudentBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.StudentDTO;
import entity.Room;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;

import java.io.IOException;
import java.net.URL;
import java.util.List;

public class StudentFormController{
    public JFXTextField txtStudentId;
    public JFXTextField txtStudentName;
    public JFXTextField txtStudentAddress;
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
    public JFXComboBox cmbRoomNum;

//    StudentBOImpl studentBOImpl = BOFactory.getInstance().getBO(BOType.STUDENT);
private final StudentBOImpl studentBOImpl = (StudentBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.STUDENT);

    public void initialize(){
        ObservableList keyMoney = FXCollections.observableArrayList("Payed","Later");
        cmbKeyMoney.getItems().addAll(keyMoney);

        ObservableList roomsNo = FXCollections.observableArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Room";
        Query query = session.createQuery(hql);
        List<Room> roomList = query.list();

        for (Room rooms : roomList) {
            roomsNo.add(rooms.getRoom_id());
        }
        transaction.commit();
        session.close();
        cmbRoomNum.getItems().addAll(roomsNo);
    }

    public void clearFields() {
        txtStudentId.setText(null);
        txtStudentName.setText(null);
        txtStudentAddress.setText(null);
        cmbRoomNum.setValue(null);
        cmbKeyMoney.setValue(null);
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String roomNum = cmbRoomNum.getValue().toString();
        String keyMoney = cmbKeyMoney.getValue().toString();
        try {
            if(studentBOImpl.update(new StudentDTO(id, name, address,roomNum,keyMoney))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String id = txtStudentId.getText();
        String name = txtStudentName.getText();
        String address = txtStudentAddress.getText();
        String roomNum = cmbRoomNum.getValue().toString();
        String keyMoney = cmbKeyMoney.getValue().toString();

        try {
            if (studentBOImpl.add(new StudentDTO(id, name, address,roomNum,keyMoney))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }

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
