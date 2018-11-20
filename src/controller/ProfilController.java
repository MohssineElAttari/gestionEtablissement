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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import services.ProfilService;

/**
 * FXML Controller class
 *
 * @author dell
 */
public class ProfilController implements Initializable {

    ProfilService ps = new ProfilService();
    ObservableList<Profil> profilList = FXCollections.observableArrayList();
    private static int index;

    @FXML
    private TextField code;
    @FXML
    private TextField libelle;
    @FXML
    private TableView<Profil> profils;
    @FXML
    private TableColumn<Profil, String> cId;
    @FXML
    private TableColumn<Profil, String> cCode;
    @FXML
    private TableColumn<Profil, String> cLibelle;

    @FXML
    private void saveAction(ActionEvent event) {
        String m = code.getText().toString();
        String r = libelle.getText().toString();

        ps.create(new Profil(m, r));
        load();
        clean();

    }

    @FXML
    private void delete() {
        ps.delete(ps.findById(index));
        profilList.clear();
        load();
        clean();

    }

    @FXML
    private void update() {

        Profil p2 = ps.findById(index);

        p2.setCode(code.getText());
        p2.setLibelle(libelle.getText());

        ps.update(p2);
        profilList.clear();
        load();
        clean();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        load();
        profils.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                TablePosition pos = (TablePosition) profils.getSelectionModel().getSelectedCells().get(0);
                int row = pos.getRow();
                Profil items = profils.getItems().get(row);
                code.setText(items.getCode());
                libelle.setText(items.getLibelle());
                index = items.getId();
                load();
            }
        });
    }

    public void clean() {
        code.setText("");
        libelle.setText("");

    }

    public void load() {
        profilList.clear();
        cId.setCellValueFactory(new PropertyValueFactory<>("id"));
        cCode.setCellValueFactory(new PropertyValueFactory<>("Code"));
        cLibelle.setCellValueFactory(new PropertyValueFactory<>("libelle"));
        for (Profil p : ps.findAll()) {
            profilList.add(new Profil(p.getId(), p.getCode(), p.getLibelle()));
        }

        profils.setItems(profilList);

    }

}
