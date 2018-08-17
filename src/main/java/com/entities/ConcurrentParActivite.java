/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@IdClass(ConcurrentParActiviteID.class)

@Table(name = "ConcurrentParActivite")
public class ConcurrentParActivite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    private Integer idActivite;
    @Id
    private Integer idActiviteConcurrent;

    public ConcurrentParActivite() {
    }

    public Integer getIdActivite() {
        return idActivite;
    }

    public void setIdActivite(Integer idActivite) {
        this.idActivite = idActivite;
    }

    public Integer getIdActiviteConcurrent() {
        return idActiviteConcurrent;
    }

    public void setIdActiviteConcurrent(Integer idActiviteConcurrent) {
        this.idActiviteConcurrent = idActiviteConcurrent;
    }

    public ConcurrentParActivite(ActiviteConcurrent activiteConcurrent) {
        this.activiteConcurrent = activiteConcurrent;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof ConcurrentParActivite)) {
            return false;
        }
        ConcurrentParActivite cpa=(ConcurrentParActivite) obj;
        return (cpa.getActivite().equals(this.getActivite()) && cpa.getActiviteConcurrent().equals(this.getActiviteConcurrent()));
    }

    
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name="idActivite",referencedColumnName="idActivite",insertable=false,updatable=false)
    private Activite activite;

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name="idActiviteConcurrent",referencedColumnName="idActiviteConcurrent",insertable=false,updatable=false)
    private ActiviteConcurrent activiteConcurrent;

    public ActiviteConcurrent getActiviteConcurrent() {
        return activiteConcurrent;
    }

    public void setActiviteConcurrent(ActiviteConcurrent activiteConcurrent) {
        this.activiteConcurrent = activiteConcurrent;
    }

    @Override
    public String toString() {
        return "ConcurrentParActivite{" + "idActivite=" + idActivite + ", idActiviteConcurrent=" + idActiviteConcurrent + '}';
    }
    
    
}
