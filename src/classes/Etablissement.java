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

    public Etablissement(String nom, String type, String region, String academie, String code, String ville, String telephone, String direction) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.academie = academie;
        this.code = code;
        this.ville = ville;
        this.telephone = telephone;
        this.direction = direction;
    }

    public Etablissement(int id, String nom, String type, String region, String academie, String code, String ville, String telephone, String direction) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.academie = academie;
        this.code = code;
        this.ville = ville;
        this.telephone = telephone;
        this.direction = direction;
    }

    private String nom;
    private String type;
    private String region;
    private String academie;
    private String code;
    private String ville;
    private String telephone;
    private String direction;
  

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public Etablissement(String nom, String type, String region, String academie) {
        this.nom = nom;
        this.type = type;
        this.region = region;
        this.academie = academie;
    }

    public Etablissement() {
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
