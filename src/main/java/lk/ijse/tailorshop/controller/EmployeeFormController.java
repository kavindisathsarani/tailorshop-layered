package lk.ijse.tailorshop.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class EmployeeFormController {
    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnEmployee;

    @FXML
    private JFXButton btnHome;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private TableColumn<?, ?> colPosition;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<?> tblEmployee;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPosition;

    @FXML
    void btnBackOnAction(ActionEvent event) {

    }

    @FXML
    void btnClearOnAction(ActionEvent event) {

    }


    @FXML
    void btnDeleteOnAction(ActionEvent event) {

    }




    @FXML
    void btnSaveOnAction(ActionEvent event) {

    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {

    }

    @FXML
    void txtAddressOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtEmployeeIdOnKeyReleased(KeyEvent event) {

    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {

    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Employee Form");
        stage.centerOnScreen();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    public void btnMeasurementOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/measurement_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Measurement Form");
        stage.centerOnScreen();
    }

    public void btnMaterialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Material Form");
        stage.centerOnScreen();
    }

    public void btnGarmentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/garment_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Garment Form");
        stage.centerOnScreen();
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Order Form");
        stage.centerOnScreen();
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.root.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login Form");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }
}
