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
@IdClass(QuestionReponseID.class)
@Table(name = "QuestionReponse")
public class QuestionReponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idQuestion")
    private Integer idQuestion;

    @Id
    @Column(name = "idReponse")
    private Integer idReponse;
    
    @Column(name = "Cible")
    private Boolean cible;

    public QuestionReponse() {
    }

    public Boolean getCible() {
        return cible;
    }

    public void setCible(Boolean cible) {
        this.cible = cible;
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
    
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "idQuestion",referencedColumnName = "idQuestion",updatable = false,insertable = false)
    private Question ques;

    public Question getQues() {
        return ques;
    }

    public void setQues(Question ques) {
        this.ques = ques;
    }
    
    @ManyToOne(cascade = CascadeType.REMOVE,fetch = FetchType.EAGER)
    @JoinColumn(name = "idReponse",referencedColumnName = "idReponse",updatable = false,insertable = false)
    private Reponse rep;

    public Reponse getRep() {
        return rep;
    }

    public void setRep(Reponse rep) {
        this.rep = rep;
    }
    
    
    
    
}
