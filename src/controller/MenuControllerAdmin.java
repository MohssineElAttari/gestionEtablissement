package controller;

import classes.Employe;
import classes.Profil;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import services.EmployeService;
import services.EtudiantService;
import services.ProfilService;

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
    private ImageView logo;
    @FXML
    private Button idProfil;
    @FXML
    private Button idEtudiant;
    @FXML
    private Button idEtablissement;
    @FXML
    private Button idEmploye;
    @FXML
    private Button idRecherche; 
    @FXML
    private Label nomEtablissement;
    @FXML
    private Label nomDericteur;
    Stage dialogStage = new Stage();
    Scene scene;
//    Preferences userPreferences = Preferences.userRoot();
//    String idProfile = userPreferences.get("idProfile", "-1");
    EtudiantService ets= new EtudiantService();
    ProfilService ps = new ProfilService();

    Preferences preference = Preferences.userRoot();
    int idEmp = Integer.parseInt(preference.get("idEmploye", "-1"));
    EmployeService es = new EmployeService();

    Employe e = es.findById(idEmp);

    public void visibelButton() {
        if (e.getProfil().getId() == 1) {
            idRecherche.setVisible(false);
        } else if (e.getProfil().getId() != 1) {
            idProfil.setVisible(false);
            idEtablissement.setVisible(false);
            idEmploye.setVisible(false);

        }
    }

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
//        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/ChartVue.fxml"));
        mainpane.getChildren().setAll(pane);
    }

    @FXML
    private void actionChartPie(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/PieChartVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        logo.setImage(new Image("img/ministere.jpg", 200, 124, false, true));
        visibelButton();
        setChart();
        nomDericteur.setText(e.getNom()+" "+e.getPrenom());
        nomEtablissement.setText(e.getEtablissement().getNom());

    }

    @FXML
    private void actionRecherche(ActionEvent event) throws IOException {
        AnchorPane anc = FXMLLoader.load(getClass().getResource("/vue/RechercheVue.fxml"));
        mainpane.getChildren().setAll(anc);
    }

    @FXML
    private void deconnect(ActionEvent event) throws BackingStoreException {
        try {

            Node source = (Node) event.getSource();
            dialogStage = (Stage) source.getScene().getWindow();
            dialogStage.close();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/vue/Login2Vue.fxml")));
            dialogStage.setScene(scene);
            dialogStage.show();
            dialogStage.setTitle("لوحة تسجيل الدخول");
        } catch (Exception e) {
            e.printStackTrace();
        }
        preference.clear();
    }

    public static void infoBox(String infoMessage, String titleBar, String headerMessage) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titleBar);
        alert.setHeaderText(headerMessage);
        alert.setContentText(infoMessage);
        alert.showAndWait();
    }
      @FXML
    private CategoryAxis xAxes = new CategoryAxis();

    @FXML
    private NumberAxis yAxes = new NumberAxis();

    
    @FXML
    private BarChart<String, Number> mChart = new BarChart<String, Number>(xAxes, yAxes);
        
    private void setChart(){     
        
        mChart.getData().clear();
        
        XYChart.Series chartSeries = new XYChart.Series();
        
        chartSeries.getData().add(new XYChart.Data("عدد أنواع الموظفين", ps.countProfil()));
        chartSeries.getData().add(new XYChart.Data("عدد الموظفين", es.countEmploye()));
        chartSeries.getData().add(new XYChart.Data("عدد التلاميذ", ets.countEtudiant()));
        
        mChart.getData().addAll(chartSeries);
        mChart.setTitle("نظرة شاملة عن المؤسسة");
    }
    
}
