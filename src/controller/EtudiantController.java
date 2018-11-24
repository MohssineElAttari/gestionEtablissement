package controller;

import classes.Etablissement;
import classes.Etudiant;
import classes.Profil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
//import javafx.scene.control.Alert;
//import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import services.EtablissementService;
import services.EtudiantService;
import services.ProfilService;

/**
 * FXML Controller class
 *
 * @author mohss
 */
public class EtudiantController implements Initializable {

    Preferences userPreferences = Preferences.userRoot();
    String info = userPreferences.get("idEmploye", "-1");
    
    EtudiantService es = new EtudiantService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    File myFile;
    Date dt = new Date();
    Date dt1 = new Date();
    private static int index;

    @FXML
    private TextField nom;
    @FXML
    private TextField numInscription;

    @FXML
    private DatePicker dateNaissance;

    @FXML
    private TextField lieuNaissance;

    @FXML
    private TextField cin;

    @FXML
    private TextField dernierNiveau;

    @FXML
    private TextField numDossier;

    @FXML
    private TextField decision;

    @FXML
    private DatePicker dateSortie;

    @FXML
    private TableView etudiants;

    @FXML
    private TableColumn<Etudiant, String> cNumInscription;
    @FXML
    private TableColumn<Etudiant, String> cDernierNiveau;
    @FXML
    private TableColumn<Etudiant, LocalDate> cDateSortie;
    @FXML
    private TableColumn<Etudiant, String> cDecision;
    @FXML
    private TableColumn<Etudiant, String> cNumDossier;
    @FXML
    private TableColumn<Etudiant, String> cNom;
    @FXML
    private TableColumn<Etudiant, LocalDate> cDateNaissance;
    @FXML
    private TableColumn<Etudiant, String> cLieuNaissance;
    @FXML
    private TableColumn<Etudiant, String> cCin;

    @FXML
    private void importM(ActionEvent event) throws FileNotFoundException, IOException {
        FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);

        if (selectedFile != null) {
            myFile = new File(selectedFile.getAbsolutePath());
        } else {

        }

        FileInputStream fis = new FileInputStream(myFile);

        // Finds the workbook instance for XLSX file
        XSSFWorkbook myWorkBook = new XSSFWorkbook(fis);

        // Return first sheet from the XLSX workbook
        XSSFSheet mySheet = myWorkBook.getSheetAt(1);

        //XSSFRow row = mySheet.getRow(1);
        //XSSFCell cell = row.getCell(2);
        for (int i = 1; i < mySheet.getLastRowNum(); i++) {
            XSSFRow rowx = mySheet.getRow(i);
            //for(int j=1;j<rowx.getLastCellNum();j++) {
            XSSFCell cell2 = rowx.getCell(2);
            if (cell2 == null || cell2.getCellType() == CellType.BLANK) {
                continue;
            } else {
                XSSFCell cell1 = rowx.getCell(1);
                String num = cell1.getStringCellValue();
                String nomc = cell2.getStringCellValue();
                XSSFCell cell3 = rowx.getCell(3);
                Date dateN = cell3.getDateCellValue();
                XSSFCell cell4 = rowx.getCell(4);
                String lieuN = cell4.getStringCellValue();
                XSSFCell cell5 = rowx.getCell(5);
                String dernierN = cell5.getStringCellValue();
                XSSFCell cell6 = rowx.getCell(6);
                int cinE = (int) cell6.getNumericCellValue();
                XSSFCell cell7 = rowx.getCell(7);
                Date dateS = cell7.getDateCellValue();
                XSSFCell cell8 = rowx.getCell(8);
                String dec = cell8.getStringCellValue();
                XSSFCell cell9 = rowx.getCell(9);
                int numD = (int) cell9.getNumericCellValue();
                es.create(new Etudiant(num, nomc, dateN, lieuN, dernierN, cinE, dateS, dec, numD));
                load();

            }
        }

    }

    @FXML
    private void saveAction(ActionEvent event) {
        String i = numInscription.getText().toString();;
        String n = nom.getText().toString();
        LocalDate d = dateNaissance.getValue();
        Instant instant = Instant.from(d.atStartOfDay(ZoneId.systemDefault()));
        dt = Date.from(instant);
        String l = lieuNaissance.getText().toString();
        int c = Integer.parseInt(cin.getText().toString());
        String der = dernierNiveau.getText().toString();
        String num = numDossier.getText().toString();
        LocalDate d1 = dateSortie.getValue();
        Instant instant1 = Instant.from(d1.atStartOfDay(ZoneId.systemDefault()));
        dt1 = Date.from(instant1);
        String dec = decision.getText().toString();

        es.create(new Etudiant(i, n, dt, l, der, c, dt1, dec, Integer.parseInt(num)));

        load();
        clean();
    }

//    public void fillComboBox() {
//        for (Etablissement e : ets.findAll()) {
//            etablissements.add(e);
//        }
//        etablissement.setItems(etablissements);
//    }
//
    @FXML
    private void delete() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد");
        alert.setHeaderText("تأكيد الحدف");
        alert.setContentText("هل أنت متأكد من إزالة هدا الطالب ؟");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            es.delete(es.findById(index));
            etudiantList.clear();
            load();
            clean();
        } else {
            // ... user chose CANCEL or closed the dialog
        }

    }

    @FXML
    private void update() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("تأكيد");
        alert.setHeaderText(" تأكيد التعديل ؟");
        alert.setContentText("هل أنت متأكد من تعديل معلومات هدا الطالب");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            Etudiant e = es.findById(index);
            e.setNumInscription(numInscription.getText());
            e.setNomComplet(nom.getText());

            Instant instant = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt = Date.from(instant);
            Instant instant2 = Instant.from(dateNaissance.getValue().atStartOfDay(ZoneId.systemDefault()));
            dt1 = Date.from(instant2);
            e.setDateNaissance(dt);
            e.setLieuNaissance(lieuNaissance.getText());
            e.setCin(Integer.parseInt(cin.getText()));
            e.setDernierNiveau(dernierNiveau.getText());
            e.setDateSortie(dt1);
            e.setDecision(decision.getText());
            e.setNumDossier(Integer.parseInt(numDossier.getText()));

            es.update(e);
            etudiantList.clear();
            load();
            clean();
        }
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
                numInscription.setText(item.getNumInscription());
                nom.setText(item.getNomComplet());
                lieuNaissance.setText(item.getLieuNaissance());
                cin.setText(String.valueOf(item.getCin()));
                decision.setText(item.getDecision());

                numDossier.setText(String.valueOf(item.getNumDossier()));
                dernierNiveau.setText(item.getDernierNiveau());

                index = item.getId();
                //la convertion de la date a LocalDate

                Date date = item.getDateNaissance();
//                System.out.println("date = "+date);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                LocalDate localDate = LocalDate.parse(sdf.format(date), formatter);
                dateNaissance.setValue(localDate);

                dateSortie.setValue(localDate);

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
        dernierNiveau.setText("");
        numInscription.setText("");
        dernierNiveau.setText("");
        numDossier.setText("");
        decision.setText("");
        dateSortie.setValue(null);
    }
//

    public void load() {
        etudiantList.clear();

        cNumInscription.setCellValueFactory(new PropertyValueFactory<>("numInscription"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nomComplet"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cLieuNaissance.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        cCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cDernierNiveau.setCellValueFactory(new PropertyValueFactory<>("dernierNiveau"));
        cNumDossier.setCellValueFactory(new PropertyValueFactory<>("numDossier"));
        cDateSortie.setCellValueFactory(new PropertyValueFactory<>("dateSortie"));
        cDecision.setCellValueFactory(new PropertyValueFactory<>("decision"));

        for (Etudiant e : es.findAll()) {
            etudiantList.add(new Etudiant(e.getId(), e.getNumInscription(), e.getNomComplet(), e.getDateNaissance(), e.getLieuNaissance(), e.getDernierNiveau(), e.getCin(), e.getDateSortie(), e.getDecision(), e.getNumDossier()));
        }

        etudiants.setItems(etudiantList);
    }
}
