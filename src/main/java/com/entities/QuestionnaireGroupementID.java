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
public class QuestionnaireGroupementID implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idQuestionnaire;
    private Integer idGroupement;

    public QuestionnaireGroupementID() {
    }

    public Integer getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(Integer idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public Integer getIdGroupement() {
        return idGroupement;
    }

    public void setIdGroupement(Integer idGroupement) {
        this.idGroupement = idGroupement;
    } 

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.idQuestionnaire);
        hash = 37 * hash + Objects.hashCode(this.idGroupement);
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
        final QuestionnaireGroupementID other = (QuestionnaireGroupementID) obj;
        if (!Objects.equals(this.idQuestionnaire, other.idQuestionnaire)) {
            return false;
        }
        if (!Objects.equals(this.idGroupement, other.idGroupement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuestionnaireGroupementID{" + "idQuestionnaire=" + idQuestionnaire + ", idGroupement=" + idGroupement + '}';
    }
    
}
