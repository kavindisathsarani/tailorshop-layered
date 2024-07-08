package lk.ijse.tailorshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorshop.bo.BOFactory;
import lk.ijse.tailorshop.bo.custom.CustomerBO;
import lk.ijse.tailorshop.bo.custom.PaymentBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.CustomerDTO;
import lk.ijse.tailorshop.dto.PaymentDTO;
import lk.ijse.tailorshop.util.Regex;
import lk.ijse.tailorshop.view.tdm.PaymentTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentFormController {
    @FXML
    private JFXButton btnCustomer;

    @FXML
    private JFXButton btnHome;

    @FXML
    private TableColumn<?, ?> colBalance;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colNetTotal;

    @FXML
    private TableColumn<?, ?> colOrderId;

    @FXML
    private TableColumn<?, ?> colPaidAmount;

    @FXML
    private TableColumn<?, ?> colPaymentId;

    @FXML
    private TableColumn<?, ?> colStatus;

    @FXML
    private Label lblDate;

    @FXML
    private TextField txtPaymentId;


    @FXML
    private TableView<PaymentTm> tblPayment;

    @FXML
    private TextField txtBalance;

    @FXML
    private TextField txtNetTotal;

    @FXML
    private TextField txtOrderId;

    @FXML
    private TextField txtPaidAmount;

    @FXML
    private TextField txtStatus;

    @FXML
    private AnchorPane pane;
    PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);

    private List<PaymentDTO> paymentList = new ArrayList<>();

    public void initialize() throws ClassNotFoundException {
        this.paymentList = getAllPayments();
        setDate();
        setCellValueFactory();
        loadPaymentTable();
    }

    private void setCellValueFactory() {
        colPaymentId.setCellValueFactory(new PropertyValueFactory<>("paymentId"));
        colNetTotal.setCellValueFactory(new PropertyValueFactory<>("TotalCost"));
        colPaidAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colBalance.setCellValueFactory(new PropertyValueFactory<>("balance"));
        colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colOrderId.setCellValueFactory(new PropertyValueFactory<>("orderId"));
    }

    private void loadPaymentTable() {
        ObservableList<PaymentTm> tmList = FXCollections.observableArrayList();

        for (PaymentDTO payment : paymentList) {
            PaymentTm paymentTm = new PaymentTm(
                    payment.getPaymentId(),
                    payment.getTotalCost(),
                    payment.getAmount(),
                    payment.getBalance(),
                    payment.getStatus(),
                    payment.getDate(),
                    payment.getOrderId()
            );

            tmList.add(paymentTm);
        }
        tblPayment.setItems(tmList);
        PaymentTm selectedItem = tblPayment.getSelectionModel().getSelectedItem();
    }

    private ArrayList<PaymentDTO> getAllPayments() throws ClassNotFoundException {
        ArrayList<PaymentDTO> paymentDTOS = null;
        try {
            paymentDTOS = paymentBO.getAllPayments();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return paymentDTOS;
    }

    private void setDate() {
        LocalDate now = LocalDate.now();
        lblDate.setText(String.valueOf(now));
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtPaymentId.setText("");
        txtNetTotal.setText("");
        txtPaidAmount.setText("");
        txtBalance.setText("");
        txtStatus.setText("");
        lblDate.setText("");
        txtOrderId.setText("");
    }

    @FXML
    void btnCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String orderId = txtOrderId.getText();

        try {
            boolean isDeleted = paymentBO.deletePayment(orderId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "payment deleted!").show();
                initialize();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnEmployeeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Employee Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnExitOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login Form");
    }

    @FXML
    void btnGarmentOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/garment_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Garment Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnHomeOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnMaterialOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Material Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnMeasurementOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/measurement_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Measurement Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnOrderOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Order Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {
        if(txtOrderId.getText().isEmpty()|| txtNetTotal.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in empty fields before adding a new payment!").show();

        }else {

            String paymentId = txtPaymentId.getText();
            double TotalCost = Double.parseDouble(txtNetTotal.getText());
            double amount = Double.parseDouble(txtPaidAmount.getText());
            double balance = Double.parseDouble(txtBalance.getText());
            String status = txtStatus.getText();
            Date date = Date.valueOf(LocalDate.now());
            String orderId = txtOrderId.getText();


            if(isValid()) {
                try {
                    boolean isSaved = paymentBO.savePayments(new PaymentDTO(paymentId, TotalCost, amount, balance,status, date,orderId));
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "payment saved!").show();
                        initialize();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else {
                new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();
            }
        }
    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.PAYMENTID,txtPaymentId)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOTALCOST,txtNetTotal)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.AMOUNT,txtPaidAmount)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.BALANCE,txtBalance)) return false;
        return true;
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String paymentId=txtPaymentId.getText();
        double TotalCost= Double.parseDouble(txtNetTotal.getText());
        double amount= Double.parseDouble(txtPaidAmount.getText());
        double balance= Double.parseDouble(txtBalance.getText());
        String status = txtStatus.getText();
        Date date = Date.valueOf(LocalDate.now());
        String orderId=txtOrderId.getText();


        if(isValid()) {
            try {
                boolean isUpdated = paymentBO.updatePayment(new PaymentDTO(paymentId, TotalCost, amount, balance,status, date,orderId));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "payment updated!").show();
                    initialize();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();

        }

    }


    public void txtOrderSearchOnAction(ActionEvent actionEvent) throws ClassNotFoundException {
        String orderId = txtOrderId.getText();

        try {
            PaymentDTO payment = paymentBO.searchByIdPayment(orderId);

            if (payment != null) {
                txtPaymentId.setText(payment.getPaymentId());
                txtNetTotal.setText(String.valueOf(payment.getTotalCost()));
                txtPaidAmount.setText(String.valueOf(payment.getAmount()));
                txtBalance.setText(String.valueOf(payment.getBalance()));
                txtStatus.setText(payment.getStatus());
                lblDate.setText(String.valueOf(payment.getDate()));
                txtOrderId.setText(payment.getOrderId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnGenerateBillOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/Report/finalBill.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("paymentID",txtPaymentId.getText());


        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DbConnection.getDbConnection().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }



    public void txtTotalCostOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOTALCOST,txtNetTotal);

    }

    public void txtAmountOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.AMOUNT,txtPaidAmount);

    }

    public void txtBalanceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.BALANCE,txtBalance);

    }


    public void txtPaymentIdOnAction(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.PAYMENTID,txtPaymentId);
    }

}
