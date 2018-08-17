/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name ="Reclamation")
public class Reclamation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idReclamation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReclamation;
    
    @Column(name = "sujet")
    private String sujet;
    
    @Column(name = "description")
    private String description;
    
    @Column(name = "Traitee")
    private Boolean traitee;
    
    @Column(name = "dateReclamation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateReclamation;

    public Reclamation() {
    }

    public Integer getIdReclamation() {
        return idReclamation;
    }

    public void setIdReclamation(Integer idReclamation) {
        this.idReclamation = idReclamation;
    }

    public String getSujet() {
        return sujet;
    }

    public void setSujet(String sujet) {
        this.sujet = sujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDateReclamation() {
        return dateReclamation;
    }

    public void setDateReclamation(Date dateReclamation) {
        this.dateReclamation = dateReclamation;
    }

    public Boolean getTraitee() {
        return traitee;
    }

    public void setTraitee(Boolean traitee) {
        this.traitee = traitee;
    }
    
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "idDetailsTache")
    private DetailsTache detailsTache;

    public DetailsTache getDetailsTache() {
        return detailsTache;
    }

    public void setDetailsTache(DetailsTache detailsTache) {
        this.detailsTache = detailsTache;
    }
    
    
}
