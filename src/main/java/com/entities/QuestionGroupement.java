/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "QuestionGroupement")
public class QuestionGroupement implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idQuesGroup")
    private Integer idQuesGroup;
    
    
    @Column(name ="QuestionObligatoire")
    private Boolean QuestionObligatoire;

    public Boolean getQuestionObligatoire() {
        return QuestionObligatoire;
    }

    public void setQuestionObligatoire(Boolean QuestionObligatoire) {
        this.QuestionObligatoire = QuestionObligatoire;
    }

    public QuestionGroupement() {
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idQuestion")
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idGroupement")
    private Groupement groupement;

    public Groupement getGroupement() {
        return groupement;
    }

    public void setGroupement(Groupement groupement) {
        this.groupement = groupement;
    }

    public Integer getIdQuesGroup() {
        return idQuesGroup;
    }

    public void setIdQuesGroup(Integer idQuesGroup) {
        this.idQuesGroup = idQuesGroup;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "quesGrou")
    private List<DetailsTache> detQues;

    public List<DetailsTache> getDetQues() {
        return detQues;
    }

    public void setDetQues(List<DetailsTache> detQues) {
        this.detQues = detQues;
    }
    
    
}
