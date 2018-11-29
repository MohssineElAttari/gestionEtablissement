/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.util.Date;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author dell
 */
@Entity
public class Attestation {

    @EmbeddedId
    private AttestationPK id;
    @JoinColumn(name = "etudiant", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Etudiant etudiant;
    @JoinColumn(name = "employe", referencedColumnName = "id", insertable = false, updatable = false)
    @ManyToOne
    private Employe employe;
    private String numero;
    public Attestation() {
    }

    public Attestation(AttestationPK id, Etudiant etudiant, Employe employe, String numero) {
        this.id = id;
        this.etudiant = etudiant;
        this.employe = employe;
        this.numero = numero;
    }

    public Attestation(Etudiant etudiant, Employe employe, String numero) {
        this.etudiant = etudiant;
        this.employe = employe;
        this.numero = numero;
    }

    public AttestationPK getId() {
        return id;
    }

    public void setId(AttestationPK id) {
        this.id = id;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    }
