/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author dell
 */
@Entity
public class Etablissement {
     @Id
    @GeneratedValue
    private int id;

    private String nom;
    private String type;
    private String region;
    private String academie;

    public Etablissement(int id, String nom, String type, String region, String academie) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.academie = academie;
    }

    @Override
    public String toString() {
        return  nom;
    }

    public String getAcademie() {
        return academie;
    }

    public void setAcademie(String academie) {
        this.academie = academie;
    }

    public Etablissement(String nom, String type, String region, String academie) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.academie = academie;
    }

    public Etablissement() {
    }

    public Etablissement(int id, String nom, String type, String region) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
    }

    public Etablissement(String nom, String type, String region) {
        this.nom = nom;
        this.type = type;
        this.region = region;
    }

    public int getId() {
        return id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
    
    
    
}
