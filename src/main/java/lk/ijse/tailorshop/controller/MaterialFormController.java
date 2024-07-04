package lk.ijse.tailorshop.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorshop.bo.BOFactory;
import lk.ijse.tailorshop.bo.custom.EmployeeBO;
import lk.ijse.tailorshop.bo.custom.MaterialBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.EmployeeDTO;
import lk.ijse.tailorshop.dto.MaterialDTO;
import lk.ijse.tailorshop.util.Regex;
import lk.ijse.tailorshop.view.tdm.MaterialTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaterialFormController {
    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colDescription;

    @FXML
    private TableColumn<?, ?> colMaterialId;

    @FXML
    private TableColumn<?, ?> colQty;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<MaterialTm> tblMaterial;

    @FXML
    private TextField txtCustomerId;

    @FXML
    private TextField txtDescription;

    @FXML
    private TextField txtMaterialId;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtUnitPrice;

    MaterialBO materialBO= (MaterialBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.MATERIAL);


    private List<MaterialDTO> materialList = new ArrayList<>();

    public void initialize() throws ClassNotFoundException {
        this.materialList = getAllMaterials();
        setCellValueFactory();
        loadMaterialTable();
    }

    private void loadMaterialTable() {
        ObservableList<MaterialTm> tmList = FXCollections.observableArrayList();

        for (MaterialDTO material : materialList) {
            MaterialTm materialTm = new MaterialTm(
                    material.getMaterialId(),
                    material.getDescription(),
                    material.getQty(),
                    material.getUnitPrice(),
                    material.getCustomerId()
            );

            tmList.add(materialTm);
        }
        tblMaterial.setItems(tmList);
        MaterialTm selectedItem = tblMaterial.getSelectionModel().getSelectedItem();
    }

    private void setCellValueFactory() {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQty.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));

    }

    private ArrayList<MaterialDTO> getAllMaterials() throws ClassNotFoundException {
        ArrayList<MaterialDTO> materialDTOS = null;
        try {
            materialDTOS = materialBO.getAllMaterial();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return materialDTOS;


    }


    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtMaterialId.setText("");
        txtDescription.setText("");
        txtQty.setText("");
        txtUnitPrice.setText("");
        txtCustomerId.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws ClassNotFoundException {
        String materialId = txtMaterialId.getText();

        try {
            boolean isDeleted = materialBO.deleteMaterial(materialId);
            if (isDeleted) {
                new Alert(Alert.AlertType.CONFIRMATION, "material deleted!").show();
                initialize();

            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnNewCustomerOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws ClassNotFoundException {

        if(txtMaterialId.getText().isEmpty() || txtQty.getText().isEmpty()|| txtUnitPrice.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in empty fields before adding a new material!").show();

        }else {

            String materialId = txtMaterialId.getText();
            String description = txtDescription.getText();
            double qty = Double.parseDouble(txtQty.getText());
            double unitPrice = Double.parseDouble(txtUnitPrice.getText());
            String customerId = txtCustomerId.getText();


            if(isValid()) {
                try {
                    boolean isSaved = materialBO.saveMaterial(new MaterialDTO(materialId,description,qty,unitPrice,customerId));
                    if (isSaved) {
                        new Alert(Alert.AlertType.CONFIRMATION, "material saved!").show();
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
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.MATERIALID,txtMaterialId)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.QTY,txtQty)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.UNITPRICE,txtUnitPrice)) return false;
        return true;
    }

    @FXML
    void btnUpdateOnAction(ActionEvent event) throws ClassNotFoundException {
        String materialId=txtMaterialId.getText();
        String description=txtDescription.getText();
        double qty= Double.parseDouble(txtQty.getText());
        double unitPrice= Double.parseDouble(txtUnitPrice.getText());
        String customerId=txtCustomerId.getText();


        if(isValid()) {
            try {
                boolean isUpdated = materialBO.updateMaterial(new MaterialDTO(materialId,description,qty,unitPrice,customerId));
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "materail updated!").show();
                    initialize();

                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }else {
            new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();
        }
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) throws ClassNotFoundException {
        String materialId = txtMaterialId.getText();

        try {
            MaterialDTO material = materialBO.searchByIdMaterial(materialId);

            if (material != null) {
                txtMaterialId.setText(material.getMaterialId());
                txtDescription.setText(material.getDescription());
                txtQty.setText(String.valueOf(material.getQty()));
                txtUnitPrice.setText(String.valueOf(material.getUnitPrice()));
                txtCustomerId.setText(material.getCustomerId());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtMaterialIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.MATERIALID,txtMaterialId);

    }


    public void txtCustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.CUSTOMERID,txtCustomerId);

    }

    public void txtQtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.QTY,txtQty);

    }


    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/Report/MaterialReport.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();
        data.put("material Id",txtMaterialId.getText());


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


    public void txtUnitPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.UNITPRICE,txtUnitPrice);

    }

}
