/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Employe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.EmployeService;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class LoginLayoutController implements Initializable {

    @FXML
    private Button loginE;
    @FXML
    private Button CloseE;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private ImageView logo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Stage stage;
        Parent root;

        EmployeService es = new EmployeService();
        Employe e = es.findByEmail(username.getText());
//        if (e != null) {
//            if (e.getReference().equals(password.getText())) {
//                if (event.getSource() == btn1) {
//                    stage = (Stage) btn1.getScene().getWindow();
//                    root = FXMLLoader.load(getClass().getResource("../vue/MachineVue.fxml"));
//
//                    Scene scene = new Scene(root);
//                    stage.hide();
//                    stage.setScene(scene);
//                    stage.show();
//                }
//            } else {
//                stage = (Stage) btn2.getScene().getWindow();
//                root = FXMLLoader.load(getClass().getResource("../vue/LoginVue.fxml"));
//            }
//    }

}
}
