/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@IdClass(QuestionnaireGroupementID.class)
@Table(name = "QuestionnaireGroupement")
public class QuestionnaireGroupement implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id 
    @Column(name = "idQuestionnaire")
    private Integer idQuestionnaire;
    
    @Id 
    @Column(name = "idGroupement")
    private Integer idGroupement;

    public QuestionnaireGroupement() {
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
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idQuestionnaire",referencedColumnName = "idQuestionnaire",insertable = false,updatable = false)
    private Questionnaire questionnaire;

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idGroupement",referencedColumnName = "idGroupement",insertable = false,updatable = false)
    private Groupement groupementQuestionnaire;

    public Groupement getGroupementQuestionnaire() {
        return groupementQuestionnaire;
    }

    public void setGroupementQuestionnaire(Groupement groupementQuestionnaire) {
        this.groupementQuestionnaire = groupementQuestionnaire;
    }

    
    
    
}
