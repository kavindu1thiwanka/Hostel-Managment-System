package controller;

import bo.BOFactory;
import bo.impl.RoomBOImpl;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.RoomDTO;
import dto.StudentDTO;
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

import java.io.IOException;
import java.net.URL;

public class RoomFormController {
    public AnchorPane root;
    public JFXTextField txtRoomId;
    public TableView tblStudent;
    public TableColumn colRoomId;
    public TableColumn colRoomType;
    public TableColumn colMonthlyRent;
    public TableColumn colQty;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXComboBox cmbRoomType;
    public JFXTextField txtQty;
    public JFXTextField txtMonthlyRent;

//    RoomBOImpl roomBOImpl = BOFactory.getInstance().getBO(BOType.ROOM);
private final RoomBOImpl roomBOImpl = (RoomBOImpl) BOFactory.getBOFactory().getBO(BOFactory.BoTypes.ROOM);

    public void initialize(){
        ObservableList roomType = FXCollections.observableArrayList("Non-AC","Non-AC / Food","AC","AC / Food");
        cmbRoomType.getItems().addAll(roomType);
    }

    public void clearFields() {
        txtRoomId.setText(null);
        txtMonthlyRent.setText(null);
        txtQty.setText(null);
        cmbRoomType.setValue(null);
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

    public void navigateToLogin(MouseEvent mouseEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/LoggingForm.fxml");
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

    public void textFields_Key_Released(KeyEvent keyEvent) {
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String id = txtRoomId.getText();
        String type = cmbRoomType.getValue().toString();
        double rent = Double.parseDouble(txtMonthlyRent.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            if(roomBOImpl.update(new RoomDTO(id, type, rent,qty))) {
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
        String id = txtRoomId.getText();
        String type = cmbRoomType.getValue().toString();
        double rent = Double.parseDouble(txtMonthlyRent.getText());
        int qty = Integer.parseInt(txtQty.getText());

        try {
            if (roomBOImpl.add(new RoomDTO(id, type, rent,qty))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
    }
}
