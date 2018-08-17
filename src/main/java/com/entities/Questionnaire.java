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
@Table(name="Questionnaire")
public class Questionnaire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idQuestionnaire")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestionnaire;
    @Column(name = "nomQuestionnaire")
    private String nomQuestionnaire;

    public Questionnaire() {
    }
         
    public Integer getIdQuestionnaire() {
        return idQuestionnaire;
    }

    public void setIdQuestionnaire(Integer idQuestionnaire) {
        this.idQuestionnaire = idQuestionnaire;
    }

    public String getNomQuestionnaire() {
        return nomQuestionnaire;
    }

    public void setNomQuestionnaire(String nomQuestionnaire) {
        this.nomQuestionnaire = nomQuestionnaire;
    }
   
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestionnaire != null ? idQuestionnaire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the idQuestionnaire fields are not set
        if (!(object instanceof Questionnaire)) {
            return false;
        }
        Questionnaire other = (Questionnaire) object;
        if ((this.idQuestionnaire == null && other.idQuestionnaire != null) || (this.idQuestionnaire != null && !this.idQuestionnaire.equals(other.idQuestionnaire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Questionnaire[ idQuestionnaire=" + idQuestionnaire + " ]";
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "questionnaire")
    private List<QuestionnaireGroupement> ListGroupement;

    public List<QuestionnaireGroupement> getListGroupement() {
        return ListGroupement;
    }

    public void setListGroupement(List<QuestionnaireGroupement> ListGroupement) {
        this.ListGroupement = ListGroupement;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "questionnaireAffectation")
    private List<Affectation> listAffQuest;

    public List<Affectation> getListAffQuest() {
        return listAffQuest;
    }

    public void setListAffQuest(List<Affectation> listAffQuest) {
        this.listAffQuest = listAffQuest;
    }
       
}
