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
@Table(name="Activite")

public class Activite implements Serializable{
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "idActivite")
    private Integer idActivite;
        
    @Column(name = "nomActivite")
    private String nomActivite;
    
    
    public Activite() {
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public String getNomActivite() {
        return nomActivite;
    }
    
    public void setNomActivite(String nomActivite) {
        this.nomActivite = nomActivite;
    }

    public Activite(String nomActivite) {
        this.nomActivite = nomActivite;
    }
    
      @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Activite))
            return false;
        Activite a=(Activite)obj;
        return (a.nomActivite.equals(this.nomActivite));
    }  

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.nomActivite);
        return hash;
    }
    @ManyToOne(fetch=FetchType.EAGER,cascade = CascadeType.REMOVE)
    //@OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name="idFiliale",referencedColumnName="idFiliale",insertable=true,updatable=true)
    private Filiale filiale;

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }
    
     @OneToMany(fetch=FetchType.LAZY,mappedBy="activite")
    private List<ConcurrentParActivite> ListConcurrent;

    public List<ConcurrentParActivite> getListConcurrent() {
        return ListConcurrent;
    }

    public void setListConcurrent(List<ConcurrentParActivite> ListConcurrent) {
        this.ListConcurrent = ListConcurrent;
    }

    

    @Override
    public String toString() {
        return "Activite{" + "nomActivite=" + nomActivite + '}';
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "activite")
    private List<Affectation> listAffact;

    public List<Affectation> getListAffact() {
        return listAffact;
    }

    public void setListAffact(List<Affectation> listAffact) {
        this.listAffact = listAffact;
    }
    
    
    
}
