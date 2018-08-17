/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;


@Entity
@DiscriminatorValue("Agent")

public class UtilisateurAgence extends Utilisateur implements Serializable{
    private static final long serialVersionUID = 1L;
    public UtilisateurAgence() {
    }
   
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name = "idAgence", referencedColumnName = "idAgence", insertable = true, updatable = true)
    private Agence agence;

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "agent")
    private List<Affectation> listAffagent;

    public List<Affectation> getListAffagent() {
        return listAffagent;
    }

    public void setListAffagent(List<Affectation> listAffagent) {
        this.listAffagent = listAffagent;
    }
    
    
}
