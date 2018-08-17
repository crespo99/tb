/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Tache")
public class Tache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idTache")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTache;
    
    @Column(name = "dateTache")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateTache;
    
    @Column(name = "etat")
    private String etat;
  
    @Column(name = "reclamee")
    private Boolean reclame;
    
    @Column(name = "corrigee")
    private Boolean corrigee;

    public Tache() {
    }

    public Integer getIdTache() {
        return idTache;
    }

    public void setIdTache(Integer idTache) {
        this.idTache = idTache;
    }

    public Date getDateTache() {
        return dateTache;
    }

    public void setDateTache(Date dateTache) {
        this.dateTache = dateTache;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public Boolean getReclame() {
        return reclame;
    }

    public void setReclame(Boolean reclame) {
        this.reclame = reclame;
    }

    public Boolean getCorrigee() {
        return corrigee;
    }

    public void setCorrigee(Boolean corrigee) {
        this.corrigee = corrigee;
    }

    @Override
    public String toString() {
        return "Tache{" + "idTache=" + idTache + '}';
    }
    
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idAffectation")
    private Affectation  affectationTache;

    public Affectation getAffectationTache() {
        return affectationTache;
    }

    public void setAffectationTache(Affectation affectationTache) {
        this.affectationTache = affectationTache;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "tache")
    private List<DetailsTache> listDetailsTache;

    public List<DetailsTache> getListDetailsTache() {
        return listDetailsTache;
    }

    public void setListDetailsTache(List<DetailsTache> listDetailsTache) {
        this.listDetailsTache = listDetailsTache;
    }
    
    
}
