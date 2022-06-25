package controller;

import bo.BOFactory;
import bo.custom.ReserveBO;
import bo.custom.RoomBO;
import bo.custom.StudentBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import dto.ReserveDTO;
import dto.StudentDTO;
import entity.Reserve;
import entity.Room;
import entity.Student;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import util.FactoryConfiguration;
import view.tm.ReserveTM;
import view.tm.StudentTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.time.LocalDate.now;

public class ReserveFormController {

    public AnchorPane root;
    public JFXTextField txtReserveId;
    public JFXTextField txtKeyMoney;
    public TableView<Reserve> tblReserve;
    public TableColumn colResId;
    public TableColumn colStuId;
    public TableColumn colRoomNo;
    public TableColumn colKeyMoney;
    public JFXButton btnSave;
    public JFXButton btnUpdate;
    public JFXButton btnDelete;
    public JFXComboBox cmbRoomNum;
    public JFXComboBox cmbStudentId;
    public JFXButton btnAddNewStudent;
    public TableColumn colDate;

    private final ReserveBO reserveBO = (ReserveBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.RESERVE);
    private final RoomBO roomBO = (RoomBO) BOFactory.getBOFactory().getBO(BOFactory.BOTypes.ROOM);

    public void initialize(){

        txtReserveId.setText(generateNewId());
        txtReserveId.setDisable(true);

        ObservableList roomsNo = FXCollections.observableArrayList();
        ObservableList students = FXCollections.observableArrayList();
        Session session = FactoryConfiguration.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        String hql = "FROM Room";
        String hql2 = "FROM student";
        Query query = session.createQuery(hql);
        Query query2 = session.createQuery(hql2);
        List<Room> roomList = query.list();
        List<Student> studentList = query2.list();

        for (Room rooms : roomList) {
            roomsNo.add(rooms.getRoom_id());
        }
        for (Student student : studentList) {
            students.add(student.getFullName());
        }
        transaction.commit();
        session.close();
        cmbRoomNum.getItems().addAll(roomsNo);
        cmbStudentId.getItems().addAll(students);

        colResId.setCellValueFactory(new PropertyValueFactory<>("res_id"));
        colStuId.setCellValueFactory(new PropertyValueFactory<>("student_id"));
        colRoomNo.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colKeyMoney.setCellValueFactory(new PropertyValueFactory<>("key_money"));

        loadAllReserve();
//        storeValidations();

        tblReserve.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                txtReserveId.setText(newValue.getRes_id());
                txtKeyMoney.setText(String.valueOf(newValue.getKey_money()));
                cmbStudentId.setValue(newValue.getStudent_id());
                cmbRoomNum.setValue(newValue.getRoom_id());
                txtReserveId.setDisable(true);
                btnSave.setDisable(true);
            }
        });

    }

    private void loadAllReserve() {
        tblReserve.getItems().clear();
        try {
            ArrayList<ReserveDTO> allReserve = reserveBO.getAllReserve();
            for (ReserveDTO reserve : allReserve) {
                tblReserve.getItems().add(new ReserveTM(reserve.getRes_id(),reserve.getDate(),reserve.getKey_money(),reserve.getStudent_id(),reserve.getRoom_id()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnSave_OnAction(ActionEvent actionEvent) {
        String rId = txtReserveId.getText();
        double kM = Double.parseDouble(txtKeyMoney.getText());
        String sName = cmbStudentId.getValue().toString();
        String rmId = cmbRoomNum.getValue().toString();
        String date= now().toString();


        try {
            if (reserveBO.add(new ReserveDTO(rId, date, kM,sName,rmId))) {
                new Alert(Alert.AlertType.CONFIRMATION, "Saved.!").show();
                Session session = FactoryConfiguration.getInstance().getSession();
                Transaction transaction = session.beginTransaction();
                try {
                    System.out.println("999999999999");
                    Query query1 = session.createQuery("SELECT room_qty FROM Room WHERE room_id=:rmId");
                    System.out.println("dadadwdawdadadadadad");
                    //////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////////
                    //////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////
                    ////////////////////////////////////////////////////////
                    ///////////////////////////////////////////////////////////
                    /////////////////////////////////////////////////////////////
                    Query query = session.createQuery("UPDATE Room SET room_qty=:sn WHERE room_id =:rmId");
                    System.out.println("11111111111111111111111000000000000");
                }catch (Exception e){
                    System.out.println(e);
                }

                transaction.commit();
                session.close();

                clearFields();
            }
        } catch (Exception e) {
            System.out.println(e);
            new Alert(Alert.AlertType.ERROR, "Something Happened. try again carefully!").showAndWait();
        }
        loadAllReserve();
        txtReserveId.setText(generateNewId());
    }

    private String generateNewId() {
        try {
            return reserveBO.generateNewID();
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to generate a new id " + e.getMessage()).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        if (tblReserve.getItems().isEmpty()) {
            return "RS001";
        } else {
            String id = getLastReserveId();
            int newStudentId = Integer.parseInt(id.replace("RS", "")) + 1;
            return String.format("RS%03d", newStudentId);
        }
    }

    private String getLastReserveId() {
        List<ReserveTM> tempReserveList = new ArrayList(tblReserve.getItems());
        Collections.sort(tempReserveList);
        return tempReserveList.get(tempReserveList.size() - 1).getRes_id();
    }

    public void clearFields() {
        txtReserveId.setText(null);
        txtKeyMoney.setText(null);
        cmbRoomNum.setValue(null);
        cmbStudentId.setValue(null);
    }

    public void btnUpdate_OnAction(ActionEvent actionEvent) {
        String rId = txtReserveId.getText();
        double kM = Double.parseDouble(txtKeyMoney.getText());
        String sName = cmbStudentId.getValue().toString();
        String rmId = cmbRoomNum.getValue().toString();
        String date = now().toString();

        try {
            if(reserveBO.update(new ReserveDTO(rId, date ,kM,sName,rmId)))  {
                new Alert(Alert.AlertType.CONFIRMATION, "Updated.!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Something Happened").show();
            }
        } catch (Exception e) {
            new Alert(Alert.AlertType.ERROR, "Something Happened").show();
        }
        loadAllReserve();
        txtReserveId.setText(generateNewId());
    }

    public void btnDelete_OnAction(ActionEvent actionEvent) {
        String id = tblReserve.getSelectionModel().getSelectedItem().getRes_id();
        try {
            if (!existReserve(id)) {
                new Alert(Alert.AlertType.ERROR, "There is no such course associated with the id " + id).show();
            }else{
                new Alert(Alert.AlertType.CONFIRMATION, "Deleted...!").show();
                reserveBO.delete(id);
                tblReserve.getItems().remove(tblReserve.getSelectionModel().getSelectedItem());
                tblReserve.getSelectionModel().clearSelection();
                clearFields();
                txtReserveId.setText(generateNewId());
                btnSave.setDisable(false);
                loadAllReserve();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, "Failed to delete the course " + id).show();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    boolean existReserve(String id) throws SQLException, ClassNotFoundException {
        return reserveBO.ifReserveExist(id);
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

    public void btnAddNewStudent_OnAction(ActionEvent actionEvent) throws IOException {
        URL resource = this.getClass().getResource("/view/StudentForm.fxml");
        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        Stage primaryStage = (Stage) (this.root.getScene().getWindow());
        primaryStage.setScene(scene);
        primaryStage.centerOnScreen();
        Platform.runLater(() -> primaryStage.sizeToScene());
    }
}