/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Etudiant;
import classes.Profil;
import java.math.BigInteger;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import services.EmployeService;
import services.EtudiantService;
import services.ProfilService;

/**
 *
 * @author Sinponzakra
 */
public class ChartController implements Initializable {

    EmployeService es = new EmployeService();
    ProfilService ps = new ProfilService();
    EtudiantService ets = new EtudiantService();
    @FXML
    private PieChart pieChart;
    @FXML
    private PieChart pieChart2;

//       ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
//
//        for (Profil p : ps.findAll()) {
//            pieChartData.add(new PieChart.Data(p.getLibelle(), es.CountEmployesByProfilName(p.getLibelle())));
//
//        }
//        pieChart.setData(pieChartData);
    @FXML
    private CategoryAxis xAxes = new CategoryAxis();

    @FXML
    private NumberAxis yAxes = new NumberAxis();

    @FXML
    private BarChart<String, Number> mChart = new BarChart<String, Number>(xAxes, yAxes);

    private void setChart() {

        mChart.getData().clear();
        XYChart.Series chartSeries = new XYChart.Series();

        for (Profil p : ps.findAll()) {
            // pList.add(p.getLibelle());
            chartSeries.getData().add(new XYChart.Data(p.getLibelle(), es.CountEmployesByProfilName(p.getLibelle())));

        }

        mChart.getData().addAll(chartSeries);
        mChart.setTitle("عدد الموظفين");
    }

    public void setChart2() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (String r : ets.DistinctDecision()) {
            pieChartData.add(new PieChart.Data(r, ets.countDecision(r)));

        }
        pieChart.setData(pieChartData);
    }
       public void setChart3() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        for (String r : ets.DistinctDernierNiveau()) {
            pieChartData.add(new PieChart.Data(r, ets.countDernierNiveau(r)));

        }
        pieChart2.setData(pieChartData);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //         final Label caption = new Label("");
//        caption.setTextFill(Color.DARKORANGE);
//        caption.setStyle("-fx-font: 24 arial;");
//        for (final PieChart.Data data : pieChart.getData()) {
//            data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
//                    new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent e) {
//                    caption.setTranslateX(e.getSceneX());
//                    caption.setTranslateY(e.getSceneY());
//                    caption.setText(String.valueOf(data.getPieValue()) + "%");
//                }
//            });
//        }

        setChart();
        setChart2();
        setChart3();

    }
}
