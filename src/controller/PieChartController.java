/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Profil;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import services.EmployeService;
import services.ProfilService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class PieChartController implements Initializable {

    EmployeService es = new EmployeService();
    ProfilService ps = new ProfilService();
    @FXML
    private PieChart pieChart;

    public void setChart2() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (Profil p : ps.findAll()) {
            pieChartData.add(new PieChart.Data(p.getLibelle(), es.CountEmployesByProfilName(p.getLibelle())));

        }
        pieChart.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setChart2();
    }

}
