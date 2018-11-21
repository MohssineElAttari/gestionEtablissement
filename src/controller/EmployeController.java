/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Employe;
import classes.Etablissement;
import classes.Profil;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EmployeService;
import services.EtablissementService;
import services.ProfilService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EmployeController implements Initializable {

    EmployeService es = new EmployeService();
    EtablissementService ets = new EtablissementService();
    ProfilService ps = new ProfilService();
    ObservableList<Employe> employeList = FXCollections.observableArrayList();
    ObservableList<Profil> profilList = FXCollections.observableArrayList();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    private static int index;

    Date dt1 = new Date();
    Date dt2 = new Date();

    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private TextField email;
    @FXML
    private PasswordField password;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private DatePicker dateEmbauche;
    @FXML
    private ComboBox<Profil> profil;
    @FXML
    private ComboBox<Etablissement> etablissement;
    @FXML
    private TableView employes;
    @FXML
    private TableColumn<Employe, String> cId;
    @FXML
    private TableColumn<Employe, String> cNom;
    @FXML
    private TableColumn<Employe, String> cPrenom;
    @FXML
    private TableColumn<Employe, String> cEmail;
    @FXML
    private TableColumn<Employe, LocalDate> cDateNaissance;
    @FXML
    private TableColumn<Employe, LocalDate> cDateEmbauche;
    @FXML
    private TableColumn<Profil, String> cProfil;
    @FXML
    private TableColumn<Etablissement, String> cEtablissement;

    @FXML
    private void saveAction(ActionEvent event) {
        String n = nom.getText().toString();
        String p = prenom.getText().toString();
        String e = email.getText().toString();
        String pa = password.getText().toString();
        LocalDate dn = dateNaissance.getValue();
        LocalDate de = dateEmbauche.getValue();

        Instant instant = Instant.from(dn.atStartOfDay(ZoneId.systemDefault()));
        dt1 = Date.from(instant);
        Instant instant2 = Instant.from(de.atStartOfDay(ZoneId.systemDefault()));
        dt2 = Date.from(instant2);
        Profil pr = profil.getValue();
        Etablissement e1 = etablissement.getValue();
        es.create(new Employe(n, p, e, pa, dt1, dt2, pr, e1));
        load();
        clean();

    }

    public void fillComboBox() {
        for (Profil p : ps.findAll()) {
            profilList.add(p);
        }
        profil.setItems(profilList);
    }

    public void fillComboBoxEtablissement() {
        for (Etablissement et : ets.findAll()) {
            etablissements.add(et);
        }
        etablissement.setItems(etablissements);
    }

    @FXML
    private void delete(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد");
        alert.setHeaderText("تأكيد الحدف");
        alert.setContentText("هل أنت متأكد من إزالة هدا الطالب ؟");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            employeList.clear();
            load();

        } else {

        }
    }

    @FXML
    private void update(ActionEvent event) {
        Employe e = es.findById(index);
        e.setNom(nom.getText());
        e.setEmail(email.getText());
        e.setPrenom(prenom.getText());
        Instant instant = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
        dt1 = Date.from(instant);
        e.setDateNaissance(dt1);
        Instant instant2 = Instant.from(dateEmbauche.getValue().atStartOfDay(ZoneId.systemDefault()));
        dt2 = Date.from(instant2);
        e.setDateEmbauche(dt2);
        e.setProfil(profil.getValue());
        e.setEtablissement(etablissement.getValue());
        es.update(e);
        employeList.clear();
        load();
        clean();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        employes.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) employes.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Employe item = (Employe) employes.getItems().get(row);
                nom.setText(item.getNom());
                prenom.setText(item.getPrenom());
                email.setText(item.getEmail());
                profil.setValue(item.getProfil());
                etablissement.setValue(item.getEtablissement());
                index = item.getId();

                Date date1 = item.getDateNaissance();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date1), formatter);
                dateNaissance.setValue(localDate);

                Date date2 = item.getDateNaissance();
                DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate2 = LocalDate.parse(sdf2.format(date2), formatter2);
                dateEmbauche.setValue(localDate2);

                load();
            }
        });

    }

    public void clean() {
        nom.setText("");
        prenom.setText("");
        email.setText("");
        password.setText("");
        dateNaissance.setValue(LocalDate.now());
        dateEmbauche.setValue(LocalDate.now());
        profil.setValue(null);
        etablissement.setValue(null);
    }

    public void load() {
        employeList.clear();
        etablissements.clear();
        profilList.clear();
        fillComboBox();
        fillComboBoxEtablissement();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cDateEmbauche.setCellValueFactory(new PropertyValueFactory<>("dateEmbauche"));
        cProfil.setCellValueFactory(new PropertyValueFactory<>("profil"));
        cEtablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Employe e : es.findAll()) {
            employeList.add(new Employe(e.getId(), e.getNom(), e.getPrenom(), e.getEmail(), e.getPassword(), e.getDateNaissance(), e.getDateEmbauche(), e.getProfil(), e.getEtablissement()));
        }
        employes.setItems(employeList);
    }

}
