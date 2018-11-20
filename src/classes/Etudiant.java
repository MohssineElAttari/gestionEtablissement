/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author dell
 */
@Entity
public class Etudiant {

    @Id
    @GeneratedValue
    private int id;
    private String nomComplet;  
    private String lieuNaissance;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
  
    private String cin;
    private String niveauEtude;
    @ManyToOne
    private Etablissement etablissement;

    public Etudiant() {
    }

    public Etudiant(int id, String nomComplet,  String lieuNaissance ,Date dateNaissance, String cin, String niveauEtude, Etablissement etablissement) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.cin = cin;
        this.niveauEtude = niveauEtude;
        this.etablissement = etablissement;
    }

    public Etudiant(String nomComplet, String lieuNaissance, Date dateNaissance, String cin, String niveauEtude, Etablissement etablissement) {
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.cin = cin;
        this.niveauEtude = niveauEtude;
        this.etablissement = etablissement;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNiveauEtude() {
        return niveauEtude;
    }

    public void setNiveauEtude(String niveauEtude) {
        this.niveauEtude = niveauEtude;
    }

    public Etablissement getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(Etablissement etablissement) {
        this.etablissement = etablissement;
    }

  
    
}
