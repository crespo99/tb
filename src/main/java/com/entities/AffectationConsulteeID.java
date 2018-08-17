/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Administrateur
 */
public class AffectationConsulteeID implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer idAffectation;
    private Integer idOperMark;

    public AffectationConsulteeID() {
    }

    public Integer getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public Integer getIdOperMark() {
        return idOperMark;
    }

    public void setIdOperMark(Integer idOperMark) {
        this.idOperMark = idOperMark;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.idAffectation);
        hash = 97 * hash + Objects.hashCode(this.idOperMark);
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
        final AffectationConsulteeID other = (AffectationConsulteeID) obj;
        if (!Objects.equals(this.idAffectation, other.idAffectation)) {
            return false;
        }
        if (!Objects.equals(this.idOperMark, other.idOperMark)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AffectationConsulteeID{" + "idAffectation=" + idAffectation + ", idOperMark=" + idOperMark + '}';
    }
    
    
}
