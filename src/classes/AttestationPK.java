/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;

/**
 *
 * @author mohss
 */
@Embeddable
public class AttestationPK implements Serializable{
    private int employe;
    private int etudiant;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateEdition;

    public AttestationPK() {
    }

    public AttestationPK(int employe, int etudiant, Date dateEdition) {
        this.employe = employe;
        this.etudiant = etudiant;
        this.dateEdition = dateEdition;
    }

    public int getEmploye() {
        return employe;
    }

    public void setEmploye(int employe) {
        this.employe = employe;
    }

    public int getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(int etudiant) {
        this.etudiant = etudiant;
    }

    public Date getDateEdition() {
        return dateEdition;
    }

    public void setDateEdition(Date dateEdition) {
        this.dateEdition = dateEdition;
    }
    
    
}
