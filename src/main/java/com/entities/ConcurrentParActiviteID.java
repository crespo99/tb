/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Objects;


public class ConcurrentParActiviteID implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idActivite;
    private Integer idActiviteConcurrent;

    public ConcurrentParActiviteID() {
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.idActivite);
        hash = 97 * hash + Objects.hashCode(this.idActiviteConcurrent);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ConcurrentParActiviteID other = (ConcurrentParActiviteID) obj;
        if (!Objects.equals(this.idActivite, other.idActivite)) {
            return false;
        }
        if (!Objects.equals(this.idActiviteConcurrent, other.idActiviteConcurrent)) {
            return false;
        }
        return true;
    }
    
    
}
