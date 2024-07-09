package lk.ijse.tailorshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.tailorshop.bo.BOFactory;
import lk.ijse.tailorshop.bo.custom.AddGarmentBO;
import lk.ijse.tailorshop.bo.custom.MaterialBO;
import lk.ijse.tailorshop.db.DbConnection;
import lk.ijse.tailorshop.dto.AddGarmentDTO;
import lk.ijse.tailorshop.dto.MaterialDTO;
import lk.ijse.tailorshop.entity.AddGarment;
import lk.ijse.tailorshop.entity.Garment;
import lk.ijse.tailorshop.entity.Material;
import lk.ijse.tailorshop.entity.MaterialDetail;
import lk.ijse.tailorshop.util.Regex;
import lk.ijse.tailorshop.view.tdm.MaterialCartTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

public class GarmentFormController {
    @FXML
    private Label lblCustomerId;

    @FXML
    private Label lblGarmentId;

    @FXML
    private Label lblMaterialDescription;

    @FXML
    private Label lblNetTotal;

    @FXML
    private Label lblQtyOnHand;

    @FXML
    private Label lblUnitPrice;

    @FXML
    private JFXButton btnAddInfo;

    @FXML
    private ComboBox<String> cmbMaterialId;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colCustomerId;

    @FXML
    private TableColumn<?, ?> colMaterialDescription;

    @FXML
    private TableColumn<?, ?> colMaterialId;

    @FXML
    private TableColumn<?, ?> colQtyOnHand;

    @FXML
    private TableColumn<?, ?> colTotal;

    @FXML
    private TableColumn<?, ?> colUnitPrice;

    @FXML
    private AnchorPane pane;

    @FXML
    private TableView<MaterialCartTm> tblMaterialCart;

    @FXML
    private TextField txtCategory;

    @FXML
    private TextField txtGarmentDescription;

    @FXML
    private TextField txtMaterialCost;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtQty;

    @FXML
    private TextField txtSize;

    @FXML
    private TextField txtTotalPrice;

    @FXML
    private TextField txtTowage;

    @FXML
    private TextField txtQtyOnHand;

    AddGarmentBO addGarmentBO= (AddGarmentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ADDGARMENT);

    private ObservableList<MaterialCartTm> mcList = FXCollections.observableArrayList();
    private double netTotal = 0;




    public void initialize() throws ClassNotFoundException {
        //
        setCellValueFactory();
        loadNextGarmentId();
        getMaterialId();

    }

    private void setCellValueFactory() {
        colMaterialId.setCellValueFactory(new PropertyValueFactory<>("materialId"));
        colMaterialDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colQtyOnHand.setCellValueFactory(new PropertyValueFactory<>("qty"));
        colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCustomerId.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        colTotal.setCellValueFactory(new PropertyValueFactory<>("total"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnRemove"));
    }

    private void getMaterialId() throws ClassNotFoundException {
        ObservableList<String> obList = FXCollections.observableArrayList();
        try {
            ArrayList<String> codeList = addGarmentBO.getMaterialIds();
            obList.addAll(codeList);
            cmbMaterialId.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadNextGarmentId() throws ClassNotFoundException {
      /*  try {
            ArrayList<String> currentIds = addGarmentBO.currentGarmentId();
            if (!currentIds.isEmpty()) {
                String currentId = currentIds.get(0); // Assuming you want the first ID in the list
                String nextId = nextId(currentId);
                lblGarmentId.setText(nextId);
            } else {
                // Handle case where no current ID is available
                lblGarmentId.setText("G1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }*/

        try {
            ArrayList<String> currentIds = addGarmentBO.currentGarmentId();
            if (!currentIds.isEmpty()) {
                String currentId = currentIds.get(currentIds.size() - 1); // Get the last ID in the list
                String nextId = nextId(currentId);
                lblGarmentId.setText(nextId);
            } else {
                lblGarmentId.setText("G1");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private String nextId(String currentId) {
        /*if (currentId != null) {
            String[] split = currentId.split("G");
            // System.out.println("Arrays.toString(split) = " + Arrays.toString(split));
            int id = Integer.parseInt(split[1]);    //2
            return "G" + ++id;

        }
        return "G1";*/

        if (currentId != null && currentId.startsWith("G")) {
            int id = Integer.parseInt(currentId.substring(1)); // Extract numeric part
            return "G" + (id + 1); // Increment and format back to 'Gx' format
        }
        return "G1"; // Default if currentId is null or doesn't match expected format


    }

    @FXML
    void btnAddGarmentOnAction(ActionEvent event) {
        if(txtTowage.getText().isEmpty() || txtTotalPrice.getText().isEmpty()|| txtMaterialCost.getText().isEmpty()
                || txtQtyOnHand.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in empty fields!").show();

        }else {

            String garmentId = lblGarmentId.getText();
            String name = txtName.getText();
            String description = txtGarmentDescription.getText();
            String category = txtCategory.getText();
            String size = txtSize.getText();
            double qtyOnHand = Double.parseDouble(txtQtyOnHand.getText());
            double materialCost = Double.parseDouble(txtMaterialCost.getText());
            double towage = Double.parseDouble(txtTowage.getText());
            double totalPrice = Double.parseDouble(txtTotalPrice.getText());


            var garment = new Garment(garmentId, name, description, category, size, qtyOnHand, materialCost, towage, totalPrice);

            ArrayList<MaterialDetail> mdList = new ArrayList<>();
            for (int i = 0; i < tblMaterialCart.getItems().size(); i++) {
                MaterialCartTm tm = mcList.get(i);

                MaterialDetail md = new MaterialDetail(
                        garmentId,
                        tm.getMaterialId(),
                        tm.getQty()
                );
                mdList.add(md);
            }

            AddGarmentDTO ad = new AddGarmentDTO(garment, mdList);

            if(isValid()) {
                try {
                    boolean isPlaced = addGarmentBO.addGarment(ad);
                    if (isPlaced) {
                        new Alert(Alert.AlertType.CONFIRMATION, "garment placed!").show();
                    } else {
                        new Alert(Alert.AlertType.WARNING, "garment not placed!").show();
                    }
                } catch (SQLException e) {
                    new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                }
            }else{
                new Alert(Alert.AlertType.ERROR, "Oops! It seems there are errors in the fields you filled. Please review and correct the information accordingly!").show();

            }
        }

    }

    public boolean isValid(){
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.MATERIALCOST,txtMaterialCost)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOWAGE,txtTowage)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOTALPRICE,txtTotalPrice)) return false;
        if (!Regex.setTextColor(lk.ijse.tailorshop.util.TextField.QTYONHAND,txtQtyOnHand)) return false;
        return true;
    }

    @FXML
    void btnAddInfoOnAction(ActionEvent event) {

        if(txtTowage.getText().isEmpty() || txtTotalPrice.getText().isEmpty()|| txtMaterialCost.getText().isEmpty()
                || txtQtyOnHand.getText().isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please fill in empty fields!").show();

        }else {

            String materialId = cmbMaterialId.getValue();
            String description = lblMaterialDescription.getText();
            double qty = Double.parseDouble(txtQty.getText());
            double unitPrice = Double.parseDouble(lblUnitPrice.getText());
            String customerId = lblCustomerId.getText();
            double total = qty * unitPrice;
            JFXButton btnRemove = new JFXButton("remove");
            btnRemove.setCursor(Cursor.HAND);

            btnRemove.setOnAction((e) -> {
                ButtonType yes = new ButtonType("yes", ButtonBar.ButtonData.OK_DONE);
                ButtonType no = new ButtonType("no", ButtonBar.ButtonData.CANCEL_CLOSE);

                Optional<ButtonType> type = new Alert(Alert.AlertType.INFORMATION, "Are you sure to remove?", yes, no).showAndWait();

                if (type.orElse(no) == yes) {
                    int selectedIndex = tblMaterialCart.getSelectionModel().getSelectedIndex();
                    mcList.remove(selectedIndex);

                    tblMaterialCart.refresh();
                    calculateNetTotal();
                }
            });

            for (int i = 0; i < tblMaterialCart.getItems().size(); i++) {
                if (materialId.equals(colMaterialId.getCellData(i))) {
                    qty += mcList.get(i).getQty();
                    total = unitPrice * qty;

                    mcList.get(i).setQty(qty);
                    mcList.get(i).setTotal(total);

                    tblMaterialCart.refresh();
                    calculateNetTotal();
                    txtQty.setText("");
                    return;
                }
            }

            MaterialCartTm materialCartTm = new MaterialCartTm(materialId, description, qty, unitPrice, customerId, total, btnRemove);

            mcList.add(materialCartTm);

            tblMaterialCart.setItems(mcList);
            txtQty.setText("");
            calculateNetTotal();
        }
    }

    private void calculateNetTotal() {
        netTotal = 0;
        for (int i = 0; i < tblMaterialCart.getItems().size(); i++) {
            netTotal += (double) colTotal.getCellData(i);
        }
        lblNetTotal.setText(String.valueOf(netTotal));
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    @FXML
    void cmbMaterialOnAction(ActionEvent event) throws ClassNotFoundException {
        String materialId = cmbMaterialId.getValue();
        try {
            Material material = addGarmentBO.searchByIdMaterials(materialId);
            if (material != null) {
                lblMaterialDescription.setText(material.getDescription());
                lblQtyOnHand.setText(String.valueOf(material.getQty()));
                lblUnitPrice.setText(String.valueOf(material.getUnitPrice()));
                lblCustomerId.setText(material.getCustomerId());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        txtQty.requestFocus();
    }

    @FXML
    void txtQtyOnAction(ActionEvent event) {
        btnAddInfoOnAction(event);
    }

    public void btnGenerateReportOnAction(ActionEvent actionEvent) throws JRException, SQLException {
        JasperDesign jasperDesign =
                JRXmlLoader.load("src/main/resources/Report/GarmentsDetailReport.jrxml");
        JasperReport jasperReport =
                JasperCompileManager.compileReport(jasperDesign);

        Map<String, Object> data = new HashMap<>();



        JasperPrint jasperPrint =
                JasperFillManager.fillReport(
                        jasperReport,
                        data,
                        DbConnection.getDbConnection().getConnection());

        JasperViewer.viewReport(jasperPrint,false);
    }

    public void btnEmployeeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/employee_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Employee Form");
        stage.centerOnScreen();
    }

    public void btnCustomerOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/customer_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Customer Form");
        stage.centerOnScreen();
    }

    public void btnMeasurementOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/measurement_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Measurement Form");
        stage.centerOnScreen();
    }

    public void btnMaterialOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/material_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Material Form");
        stage.centerOnScreen();
    }

    public void btnGarmentOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/garment_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Garment Form");
        stage.centerOnScreen();
    }

    public void btnOrderOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/order_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Order Form");
        stage.centerOnScreen();
    }

    public void btnExitOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/login_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.pane.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Login Form");
    }

    public void btnHomeOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard_form.fxml"));
        Stage stage = (Stage) pane.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setResizable(false);
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }

    public void txtTowageOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOWAGE,txtTowage);

    }

    public void txtTotalPriceOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.TOTALPRICE,txtTotalPrice);

    }

    public void txtMaterialCostOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.MATERIALCOST,txtMaterialCost);
    }

    public void txtOrderQtyKeyOnReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.ORDERQTY,txtQty);
    }

    public void txtQtyOnHandKeyOnReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.ijse.tailorshop.util.TextField.QTYONHAND,txtQtyOnHand);

    }

}
