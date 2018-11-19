package vue;

import classes.Etablissement;
import classes.Etudiant;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    EtablissementService ets = new EtablissementService();
    ObservableList<Etudiant> etudiantList = FXCollections.observableArrayList();
    ObservableList<Etablissement> etablissements = FXCollections.observableArrayList();
    
    
    @FXML
    private TextField tPrenom;
    @FXML
    private TextField tNon;
    @FXML
    private Button recherche;
    @FXML
    private TableView etudiants;
    @FXML
    private TableColumn<Etudiant, String> cId;
    @FXML
    private TableColumn<Etudiant, String> cNom;
    @FXML
    private TableColumn<Etudiant, String> cPrenom;
    @FXML
    private TableColumn<Etudiant, String> cDateNaissance;
    @FXML
    private TableColumn<Etudiant, String> cLieuNaissance;
    @FXML
    private TableColumn<Etudiant, String> cCin;
    @FXML
    private TableColumn<Etudiant, String> cNiveauEtude;
    @FXML
    private TableColumn<Etudiant, String> cEtablissement;
    @FXML
    private TextField nom;
    @FXML
    private TextField prenom;
    @FXML
    private DatePicker dateNaissance;
    @FXML
    private TextField lieuNaissance;
    @FXML
    private TextField cin;
    @FXML
    private TextField niveauEtude;
    @FXML
    private ComboBox<?> etablissement;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    public void load() {
        etudiantList.clear();
        etablissements.clear();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        cDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        cLieuNaissance.setCellValueFactory(new PropertyValueFactory<>("lieuNaissance"));
        cCin.setCellValueFactory(new PropertyValueFactory<>("cin"));
        cNiveauEtude.setCellValueFactory(new PropertyValueFactory<>("niveauEtude"));
        cEtablissement.setCellValueFactory(new PropertyValueFactory<>("etablissement"));
        for (Etudiant e : es.findAll()) {
            etudiantList.add(new Etudiant(e.getId(), e.getNom(), e.getPrenom(), e.getDateNaissance(), e.getLieuNaissance(), e.getCin(), e.getNiveauEtude(),e.getEtablissement()));
        }

        etudiants.setItems(etudiantList);
    }
}
