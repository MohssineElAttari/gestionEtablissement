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

/**
 *
 * @author dell
 */
@Entity
public class Employe {

    @Id
    @GeneratedValue
    private int id;

    private String nom;
    private String prenom;
    private String email;
    private String password;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateNaissance;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEmbauche;
    @ManyToOne
    private Profil profil;

    public Employe() {
    }

    public Employe(String nom, String prenom, Date dateEmbauche, String email, String password, Profil profil) {

        this.nom = nom;
        this.prenom = prenom;
        this.dateEmbauche = dateEmbauche;
        this.email = email;
        this.password = password;
        this.profil = profil;
    }

    public Employe(String nom, String prenom, String email, String password, Date dateNaissance, Date dateEmbauche, Profil profil) {
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.profil = profil;
    }

    public Employe(int id, String nom, String prenom, String email, String password, Date dateNaissance, Date dateEmbauche, Profil profil) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.password = password;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.profil = profil;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Employe(String nom, String prenom, Date dateEmbauche, String email, String password) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateEmbauche = dateEmbauche;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public Profil getProfil() {
        return profil;
    }

    public void setProfil(Profil profil) {
        this.profil = profil;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Employe(int id, String nom, String prenom, String email, Date dateNaissance, Date dateEmbauche, Profil profil) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
        this.dateNaissance = dateNaissance;
        this.dateEmbauche = dateEmbauche;
        this.profil = profil;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
