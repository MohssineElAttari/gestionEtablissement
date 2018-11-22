/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class MenuInspecteurController implements Initializable {

    @FXML
    private AnchorPane mainpane;

    @FXML
    private void actionRecherche(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/RechercheVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
