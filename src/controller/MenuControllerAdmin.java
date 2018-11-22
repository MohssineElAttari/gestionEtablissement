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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class MenuControllerAdmin implements Initializable {

    @FXML
    private AnchorPane mainpane;
    @FXML
    private AnchorPane pane;

    @FXML
    private ImageView logo1;

    @FXML
    private void actionProfil(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/ProfilVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void actionEtablissement(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/EtablissementVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void actionEmploye(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/EmployeVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void actionEtudiant(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/EtudiantVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void actionChart(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/ChartVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void actionAcceuil(ActionEvent event) throws IOException {
        // AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/ChartVue.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void actionChartPie(ActionEvent event) throws IOException {
         AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/PieChartVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //logo1.setImage(new Image("img/ministere.jpg", 200, 88, false, true));
    }

}
