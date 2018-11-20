package controller;

import classes.Etablissement;
import classes.Etudiant;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EtablissementService;
import services.EtudiantService;
import services.ProfilService;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class EtudiantController implements Initializable {

    EtudiantService es = new EtudiantService();
    EtablissementService ets = new EtablissementService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    Date dt = new Date();
    private static int index;
    @FXML
    private TextField nom;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private TextField lieuNaissance;
    @FXML
    private TextField cin;
    @FXML
    private TextField niveauEtude;
    @FXML
    private ComboBox<Etablissement> etablissement;
    @FXML
    private TableView etudiants;
    @FXML

    private TableColumn<Etudiant, String> cId;
    @FXML
    private TableColumn<Etudiant, String> cNom;
    @FXML
    private TableColumn<Etudiant, String> cDateNaissance;
    @FXML
    private TableColumn<Etudiant, String> cLieuNaissance;
    @FXML
    private TableColumn<Etudiant, String> cCin;
    @FXML
    private TableColumn<Etudiant, String> cNiveauEtude;
    @FXML
    private TableColumn<Etablissement, String> cEtablissement;

    @FXML
    private void saveAction(ActionEvent event) {
        String n = nom.getText().toString();
        LocalDate d = dateNaissance.getValue();
        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);
        String l = lieuNaissance.getText().toString();
        String c = cin.getText().toString();
        String niveau = niveauEtude.getText().toString();
        Etablissement e = etablissement.getValue();
        es.create(new Etudiant(n, l, dt, c, niveau, e));
        load();
        clean();

    }

    
    
    public void fillComboBox(){
    for(Etablissement e:ets.findAll())
    {
       etablissements.add(e);
    }
     etablissement.setItems(etablissements);
    }
    @FXML
    private void delete() {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("تأكيد");
//        alert.setHeaderText("تأكيد الحدف");
//        alert.setContentText("هل أنت متأكد من إزالة هدا الطالب ؟");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            etudiantList.clear();
            load();
            clean();
//        } else {
//            // ... user chose CANCEL or closed the dialog
//        }

    }

    @FXML
    private void update() {
//        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
//        alert.setTitle("تأكيد");
//        alert.setHeaderText(" تأكيد التعديل ؟");
//        alert.setContentText("هل أنت متأكد من تعديل معلومات هدا الطالب");
//
//        Optional<ButtonType> result = alert.showAndWait();
//        if (result.get() == ButtonType.OK) {
            Etudiant e = es.findById(index);
            e.setNomComplet(nom.getText());
            Instant instant = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt = Date.from(instant);
            e.setDateNaissance(dt);
            e.setLieuNaissance(lieuNaissance.getText());
            e.setCin(cin.getText());
            e.setNiveauEtude(niveauEtude.getText());
            e.setEtablissement(etablissement.getValue());
            
            es.update(e);
            etudiantList.clear();
            load();
            clean();
        
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        etudiants.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) etudiants.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Etudiant item = (Etudiant) etudiants.getItems().get(row);
                nom.setText(item.getNomComplet());

                lieuNaissance.setText(item.getLieuNaissance());
                cin.setText(item.getCin());
                niveauEtude.setText(item.getNiveauEtude());
                etablissement.setValue(item.getEtablissement());
                index = item.getId();
                //la convertion de la date a LocalDate
                Date date = item.getDateNaissance();
//                System.out.println("date = "+date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date), formatter);
                dateNaissance.setValue(localDate);
//                System.out.println(localDate.MIN);
                load();
            }
        });
    }

    public void clean() {
        nom.setText("");
        dateNaissance.setValue(null);
        lieuNaissance.setText("");
        cin.setText("");
        niveauEtude.setText("");
        etablissement.setValue(null);
    }

    public void load() {
        etudiantList.clear();
        etablissements.clear();
        fillComboBox();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));

        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cLieuNaissance.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        cCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cNiveauEtude.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        cEtablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Etudiant e : es.findAll()) {
            etudiantList.add(new Etudiant(e.getId(), e.getNomComplet(), e.getLieuNaissance(), e.getDateNaissance(), e.getCin(), e.getNiveauEtude(), e.getEtablissement()));
        }

        etudiants.setItems(etudiantList);
    }
}
