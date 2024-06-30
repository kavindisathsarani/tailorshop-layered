package lk.ijse.tailorshop.controller;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class LoginFormController {
    @FXML
    private JFXButton btnLogin;

    @FXML
    private PasswordField pw;

    @FXML
    private AnchorPane rootNode;

    @FXML
    private TextField txtusername;

    private String username="kavi";
    private String password="1234";

    @FXML
    void btnLoginOnAction(ActionEvent event) throws IOException {
        if (username.equals(txtusername.getText()) && password.equals(pw.getText())){
            navigateToTheDashboard();

        } else {
            new Alert(Alert.AlertType.ERROR,"Incorrect password or Username").show();
        }
    }

    private void navigateToTheDashboard() throws IOException {
        AnchorPane rootNode = FXMLLoader.load(this.getClass().getResource("/view/dashboard_form.fxml"));

        Scene scene = new Scene(rootNode);

        Stage stage = (Stage) this.rootNode.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.centerOnScreen();

        stage.setTitle("Dashboard Form");
    }

}
