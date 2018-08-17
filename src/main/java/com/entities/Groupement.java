/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Groupement")
public class Groupement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idGroupement")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idGroupement;
    @Column(name = "nomGroupement")
    private String nomGroupement;

    public Groupement() {
    }
 
    public Integer getIdGroupement() {
        return idGroupement;
    }

    public void setIdGroupement(Integer idGroupement) {
        this.idGroupement = idGroupement;
    }

    public String getNomGroupement() {
        return nomGroupement;
    }

    public void setNomGroupement(String nomGroupement) {
        this.nomGroupement = nomGroupement;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idGroupement != null ? idGroupement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupement)) {
            return false;
        }
        Groupement other = (Groupement) object;
        if ((this.idGroupement == null && other.idGroupement != null) || (this.idGroupement != null && !this.idGroupement.equals(other.idGroupement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Groupement[ idGroupement=" + idGroupement + " ]";
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupement")
    private List<QuestionGroupement> listQuestion;

    public List<QuestionGroupement> getListQuestion() {
        return listQuestion;
    }

    public void setListQuestion(List<QuestionGroupement> listQuestion) {
        this.listQuestion = listQuestion;
    }

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "groupementQuestionnaire")
    private List<QuestionnaireGroupement> ListQuestionnaire;

    public List<QuestionnaireGroupement> getListQuestionnaire() {
        return ListQuestionnaire;
    }

    public void setListQuestionnaire(List<QuestionnaireGroupement> ListQuestionnaire) {
        this.ListQuestionnaire = ListQuestionnaire;
    }
  
}
