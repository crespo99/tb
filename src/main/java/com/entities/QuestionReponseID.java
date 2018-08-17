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
public class QuestionReponseID implements Serializable{
    private static final long serialVersionUID = 1L;
    private Integer idQuestion;
    private Integer idReponse;

    public QuestionReponseID() {
    }

    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public Integer getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Integer idReponse) {
        this.idReponse = idReponse;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + Objects.hashCode(this.idQuestion);
        hash = 13 * hash + Objects.hashCode(this.idReponse);
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
        final QuestionReponseID other = (QuestionReponseID) obj;
        if (!Objects.equals(this.idQuestion, other.idQuestion)) {
            return false;
        }
        if (!Objects.equals(this.idReponse, other.idReponse)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "QuestionReponseID{" + "idQuestion=" + idQuestion + ", idReponse=" + idReponse + '}';
    }
    
    
    
}
