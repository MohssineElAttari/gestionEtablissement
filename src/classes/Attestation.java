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
import javax.persistence.Temporal;

/**
 *
 * @author dell
 */
@Entity
public class Attestation {
    @Id
    @GeneratedValue
    private int id;
    private String etudiant;
    private String employe;
    private String numero;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdition;

    public Attestation() {
    }

    public Attestation(int id, String etudiant, String employe, String numero, Date dateEdition) {
        this.id = id;
        this.etudiant = etudiant;
        this.employe = employe;
        this.numero = numero;
        this.dateEdition = dateEdition;
    }

    public Attestation(String etudiant, String employe, String numero, Date dateEdition) {
        this.etudiant = etudiant;
        this.employe = employe;
        this.numero = numero;
        this.dateEdition = dateEdition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(String etudiant) {
        this.etudiant = etudiant;
    }

    public String getEmploye() {
        return employe;
    }

    public void setEmploye(String employe) {
        this.employe = employe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }
    
    
}
