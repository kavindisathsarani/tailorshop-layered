package lk.ijse.tailorshop.controller;
import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorshop.bo.BOFactory;
import lk.ijse.tailorshop.bo.custom.CustomerBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.entity.Customer;
import lk.ijse.tailorshop.util.Regex;
import lk.ijse.tailorshop.view.tdm.CustomerTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


public class CustomerFormController {
    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnHome;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colContactNumber;

    @FXML
    private TableColumn<?, ?> colEmail;

    @FXML
    private TableColumn<?, ?> colGender;

    @FXML
    private TableColumn<?, ?> colId;

    @FXML
    private TableColumn<?, ?> colName;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<CustomerTm> tblCustomer;

    @FXML
    private TextField txtAddress;

    @FXML
    private TextField txtContactNumber;

    @FXML
    private TextField txtEmail;

    @FXML
    private TextField txtGender;

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    CustomerBO customerBO= (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

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
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        if(txtId.getText().isEmpty() || txtAddress.getText().isEmpty()|| txtEmail.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in empty fields before adding a new customer!").show();

        }else {
            //save customer
            String customerId = txtId.getText();
            String name = txtName.getText();
            String gender = txtGender.getText();
            String address = txtAddress.getText();
            int contactNumber = Integer.parseInt(txtContactNumber.getText());
            String email = txtEmail.getText();

           // Customer customer = new Customer(customerId, name, gender, address, contactNumber, email);

            if (isValid()) {
                try {
                    boolean isSaved = customerBO.saveCustomers(new CustomerDTO(customerId, name, gender, address,contactNumber, email));
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "customer saved!").show();
                       // initialize();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();
            }
        }
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        //update customer info

        String customerId =txtId.getText();
        String name=txtName.getText();
        String gender=txtGender.getText();
        String address=txtAddress.getText();
        int contactNumber=Integer.parseInt(txtContactNumber.getText());
        String email=txtEmail.getText();

       // Customer customer = new Customer(customerId, name, gender, address,contactNumber,email);

        if(isValid()) {
            try {
                boolean isUpdated = customerBO.updateCustomers(new CustomerDTO(customerId, name, gender, address,contactNumber, email));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "customer updated!").show();
                    //initialize();

                }
            } catch (SQLException | ClassNotFoundException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else{
            new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();
        }
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
    }

    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.CUSTOMERID,txtId);
    }


    public void txtAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.ADDRESS,txtAddress);
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.CUSTOMERID,txtId)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.ADDRESS,txtAddress)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.EMAIL,txtEmail)) return false;
        return true;
    }


    public void txtEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.EMAIL,txtEmail);

    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException, ClassNotFoundException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/Report/CustomerDetailReport.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("CustomerID",txtId.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DbConnection.getDbConnection().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
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
