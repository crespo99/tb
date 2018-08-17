/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import javax.persistence.*;


@Entity
@Table(name = "Plateforme")
public class Plateforme implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPlateforme;
    

    private String nomPlateforme;

    public Plateforme(String nomPlateforme) {
        this.nomPlateforme = nomPlateforme;
    }
    
    public Plateforme() {
    }

    public Integer getIdPlateforme() {
        return idPlateforme;
    }

    public void setIdPlateforme(Integer idPlateforme) {
        this.idPlateforme = idPlateforme;
    }

    public String getNomPlateforme() {
        return nomPlateforme;
    }

    public void setNomPlateforme(String nomPlateforme) {
        this.nomPlateforme = nomPlateforme;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "plateforme")
    private List<Affectation> listAffplat;

    public List<Affectation> getListAffplat() {
        return listAffplat;
    }

    public void setListAffplat(List<Affectation> listAffplat) {
        this.listAffplat = listAffplat;
    }
    
    
}
