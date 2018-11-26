/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import classes.Etablissement;
import classes.Etablissement;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.EtablissementService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class EtablissementController implements Initializable {

    EtablissementService es = new EtablissementService();
    ObservableList<Etablissement> etablissementList = FXCollections.observableArrayList();
    private static int index;

    @FXML
    private TextField nom;
    @FXML
    private TextField type;
    @FXML
    private TextField region;
    @FXML
    private TextField academie;
    @FXML
    private TextField code;
    @FXML
    private TextField ville;
    @FXML
    private TextField telephone;
    @FXML
    private TextField direction;
    @FXML
    private Button btnEtablissement;

    @FXML
    private TableView<Etablissement> etablissements;
    @FXML
    private TableColumn<Etablissement, String> cId;
    @FXML
    private TableColumn<Etablissement, String> cNom;
    @FXML
    private TableColumn<Etablissement, String> cType;
    @FXML
    private TableColumn<Etablissement, String> cRegion;
    @FXML
    private TableColumn<Etablissement, String> cAcademie;
    @FXML
    private TableColumn<Etablissement, String> cCode;
    @FXML
    private TableColumn<Etablissement, String> cVille;
    @FXML
    private TableColumn<Etablissement, String> cTelephone;
    @FXML
    private TableColumn<Etablissement, String> cDirection;

    @FXML
    private void saveAction(ActionEvent event) {

        String n = nom.getText().toString();
        String t = type.getText().toString();
        String r = region.getText().toString();
        String a = academie.getText().toString();
        String c = code.getText().toString();
        String v = ville.getText().toString();
        String te = telephone.getText().toString();
        String d = direction.getText().toString();
        if (nom.getText().isEmpty() || type.getText().isEmpty() || region.getText().isEmpty() || academie.getText().isEmpty() || code.getText().isEmpty() || ville.getText().isEmpty() || telephone.getText().isEmpty() || direction.getText().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("تحذير");
            alert.setHeaderText(" تحذير");
            alert.setContentText("المرجو ملئ كل الحقول");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK) {
            }

        } else {
            es.create(new Etablissement(n, t, r, a, c, v, te, d));
            load();
            clean();

            int nb = es.countEtablissement();
            if (nb > 0) {
                btnEtablissement.setVisible(false);
            } else {
                btnEtablissement.setVisible(true);
            }

        }

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
            etablissementList.clear();
            load();
            clean();
            int nb = es.countEtablissement();
            if (nb > 0) {
                btnEtablissement.setVisible(false);
            } else {
                btnEtablissement.setVisible(true);
            }
        } else {

        }
    }

    @FXML
    private void update(ActionEvent event) {
        Etablissement e = es.findById(index);
        e.setNom(nom.getText());
        e.setType(type.getText());
        e.setRegion(region.getText());
        e.setAcademie(academie.getText());
        e.setCode(code.getText());
        e.setDirection(direction.getText());
        e.setTelephone(telephone.getText());
        e.setVille(ville.getText());
        es.update(e);
        etablissementList.clear();
        load();
        clean();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        int nb = es.countEtablissement();
        if (nb > 0) {
            btnEtablissement.setVisible(false);
        } else {
            btnEtablissement.setVisible(true);
        }
        etablissements.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) etablissements.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Etablissement items = etablissements.getItems().get(row);
                nom.setText(items.getNom());
                type.setText(items.getType());
                region.setText(items.getRegion());
                academie.setText(items.getAcademie());
                code.setText(items.getCode());
                ville.setText(items.getVille());
                telephone.setText(items.getTelephone());
                direction.setText(items.getDirection());
                index = items.getId();
                load();
            }
        });
    }

    public void clean() {
        nom.setText("");
        type.setText("");
        region.setText("");
        academie.setText("");
        code.setText("");
        ville.setText("");
        telephone.setText("");
        direction.setText("");

    }

    public void load() {
        etablissementList.clear();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        cType.setCellValueFactory(new PropertyValueFactory<>("type"));
        cRegion.setCellValueFactory(new PropertyValueFactory<>("region"));
        cAcademie.setCellValueFactory(new PropertyValueFactory<>("academie"));
        cCode.setCellValueFactory(new PropertyValueFactory<>("code"));
        cVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        cTelephone.setCellValueFactory(new PropertyValueFactory<>("telephone"));
        cDirection.setCellValueFactory(new PropertyValueFactory<>("direction"));
        for (Etablissement e : es.findAll()) {
            etablissementList.add(new Etablissement(e.getId(), e.getNom(), e.getType(), e.getRegion(), e.getAcademie(), e.getCode(), e.getVille(), e.getTelephone(), e.getDirection()));
        }

        etablissements.setItems(etablissementList);

    }
}
