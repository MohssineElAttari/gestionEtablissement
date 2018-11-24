package controller;

import classes.Etablissement;
import classes.Etudiant;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import services.EtablissementService;
import services.EtudiantService;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class RechercheController implements Initializable {

    EtudiantService es = new EtudiantService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    @FXML
    private TextField sershFieldnomComplet;
    @FXML
    private TableView etudiants;
    @FXML
    private TableColumn<Etudiant, String> cId;
    @FXML
    private TableColumn<Etudiant, String> cNomComplet;
    @FXML
    private TableColumn<Etudiant, String> cCin;
    @FXML
    private TableColumn<Etudiant, String> cDateNaissance;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        load();
        // 1. Wrap the ObservableList in a FilteredList (initially display all data).
        FilteredList<Etudiant> filteredData = new FilteredList<>(etudiantList, e -> true);
        sershFieldnomComplet.setOnKeyReleased(e -> {
            sershFieldnomComplet.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredData.setPredicate((Predicate<? super Etudiant>) etudiant -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true;
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    if (etudiant.getNomComplet().toLowerCase().contains(lowerCaseFilter)) {
                        return true;
                    }
                    return false;
                });
            });
            SortedList<Etudiant> storData = new SortedList<>(filteredData);
            storData.comparatorProperty().bind(etudiants.comparatorProperty());
            etudiants.setItems(storData);
        });
    }

    public void load() {
        etudiantList.clear();
        // 0. Initialize the columns.
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNomComplet.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        for (Etudiant e : es.findAll()) {
            etudiantList.add(new Etudiant(e.getId(), e.getNomComplet(), e.getDateNaissance(), e.getCin()));
        }

        etudiants.setItems(etudiantList);

    }

}
