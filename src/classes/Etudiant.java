/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;
import javax.persistence.Column;
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
    private String numInscription;
    private String nomComplet;
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    private String lieuNaissance;
    private String dernierNiveau;
    @Column(name = "cin", unique = true)
    private String cin;
    @Temporal(TemporalType.DATE)
    private Date dateSortie;
    private String decision;
    private int numDossier;
 

    public Etudiant() {
    }

    public Etudiant(int id, String numInscription, String nomComplet, Date dateNaissance, String lieuNaissance, String dernierNiveau, String cin, Date dateSortie, String decision, int numDossier) {
        this.id = id;
        this.numInscription = numInscription;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dernierNiveau = dernierNiveau;
        this.cin = cin;
        this.dateSortie = dateSortie;
        this.decision = decision;
        this.numDossier = numDossier;
    }

    public Etudiant(String numInscription, String nomComplet, Date dateNaissance, String lieuNaissance, String dernierNiveau, String cin, Date dateSortie, String decision, int numDossier) {
        this.numInscription = numInscription;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.dernierNiveau = dernierNiveau;
        this.cin = cin;
        this.dateSortie = dateSortie;
        this.decision = decision;
        this.numDossier = numDossier;
    }

    public Etudiant(int id, String nomComplet, Date dateNaissance, String cin) {
        this.id = id;
        this.nomComplet = nomComplet;
        this.dateNaissance = dateNaissance;
        this.cin = cin;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumInscription() {
        return numInscription;
    }

    public void setNumInscription(String numInscription) {
        this.numInscription = numInscription;
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

    public String getDernierNiveau() {
        return dernierNiveau;
    }

    public void setDernierNiveau(String dernierNiveau) {
        this.dernierNiveau = dernierNiveau;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateSortie() {
        return dateSortie;
    }

    public void setDateSortie(Date dateSortie) {
        this.dateSortie = dateSortie;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public int getNumDossier() {
        return numDossier;
    }

    public void setNumDossier(int numDossier) {
        this.numDossier = numDossier;
    }
    
    
  
}
