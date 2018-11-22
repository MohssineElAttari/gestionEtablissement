package controller;

import classes.Employe;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
//import javafx.scene.control.Alert;
//import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import services.EmployeService;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class LoginController implements Initializable {

    Stage dialogStage = new Stage();
    Scene scene;

    @FXML
    private Button loginE;
    @FXML
    private Button CloseE;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    
    Stage stage;
    Parent root;
    EmployeService es = new EmployeService();
    

    @FXML
    private void handleButtonAction(ActionEvent event) {
        try {
            Employe e = es.findByEmail(username.getText());
            if (e == null) {
//                infoBox("Enter Correct Email and Password", "Failed", null);
            } else {
//                infoBox("Login Successfull", "Success", null);
                Node source = (Node) event.getSource();
                dialogStage = (Stage) source.getScene().getWindow();
                dialogStage.close();
                scene = new Scene(FXMLLoader.load(getClass().getResource(e.getProfil().getId() == 1 ? "/vue/MenuAdminVue.fxml" : "/vue/MenuSpectateurVue.fxml")));
                dialogStage.setScene(scene);
                dialogStage.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //logo.setImage(new Image("img/boss.png", 130, 78, false, true));
    }

//    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
//        Alert alert = new Alert(AlertType.INFORMATION);
//        alert.setTitle(titleBar);
//        alert.setHeaderText(headerMessage);
//        alert.setContentText(infoMessage);
//        alert.showAndWait();
//    }
}
