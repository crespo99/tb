/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.Basic;
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



@Entity
@Table(name = "ActiviteConcurrent")

public class ActiviteConcurrent implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "idActiviteConcurrent")
    private Integer idActiviteConcurrent;
    
    @Column(name = "nom")
    private String nom;

    public ActiviteConcurrent() {
    }

    public Integer getIdActiviteConcurrent() {
        return idActiviteConcurrent;
    }

    public void setIdActiviteConcurrent(Integer idActiviteConcurrent) {
        this.idActiviteConcurrent = idActiviteConcurrent;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public ActiviteConcurrent(String nom) {
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ActiviteConcurrent)) {
            return false;
        }
        ActiviteConcurrent ac=(ActiviteConcurrent) obj;
        return (ac.getNom().equals(this.getNom()));
    }
    
    
    
    
    @OneToMany(fetch=FetchType.LAZY,mappedBy="activiteConcurrent")
    private List<ConcurrentParActivite> ListActParConc;

    public List<ConcurrentParActivite> getListActParConc() {
        return ListActParConc;
    }

    public void setListActParConc(List<ConcurrentParActivite> ListActParConc) {
        this.ListActParConc = ListActParConc;
    }
    
    
    
}
