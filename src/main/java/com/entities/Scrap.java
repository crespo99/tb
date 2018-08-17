/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
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

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Scrap")
public class Scrap implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idScrap")
    private Integer idScrap;
    
    @Column(name = "nbrFans")
    private String nbrFans;    

    @Column(name = "nvxFans")
    private String nvxFans;
    
    @Column(name = "impressions")
    private String impressions;
    
    @Column(name = "clics")
    private String clics;

    public Scrap() {
    }

    public Integer getIdScrap() {
        return idScrap;
    }

    public void setIdScrap(Integer idScrap) {
        this.idScrap = idScrap;
    }

    public String getNbrFans() {
        return nbrFans;
    }

    public void setNbrFans(String nbrFans) {
        this.nbrFans = nbrFans;
    }

    public String getNvxFans() {
        return nvxFans;
    }

    public void setNvxFans(String nvxFans) {
        this.nvxFans = nvxFans;
    }

    public String getImpressions() {
        return impressions;
    }

    public void setImpressions(String impressions) {
        this.impressions = impressions;
    }

    public String getClics() {
        return clics;
    }

    public void setClics(String clics) {
        this.clics = clics;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "idAffectation")
    private Affectation aff;

    public Affectation getAff() {
        return aff;
    }

    public void setAff(Affectation aff) {
        this.aff = aff;
    }
    
    
}
