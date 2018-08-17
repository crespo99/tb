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
public class QuestionGroupementID implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idQuestion;
    private Integer idGroupement;

    public QuestionGroupementID() {
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
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
        hash = 89 * hash + Objects.hashCode(this.idQuestion);
        hash = 89 * hash + Objects.hashCode(this.idGroupement);
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
        final QuestionGroupementID other = (QuestionGroupementID) obj;
        if (!Objects.equals(this.idQuestion, other.idQuestion)) {
            return false;
        }
        if (!Objects.equals(this.idGroupement, other.idGroupement)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuestionGroupementID{" + "idQuestion=" + idQuestion + ", idGroupement=" + idGroupement + '}';
    }
    
    
}
