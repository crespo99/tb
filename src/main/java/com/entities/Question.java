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
@Table(name = "Question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idQuestion")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idQuestion;
    @Column(name = "nomQuestion")
    private String nomQuestion;
    @Column(name = "typeQuestion")
    private String typeQuestion;

    public Question() {
    }
    
    public Integer getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Integer idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getNomQuestion() {
        return nomQuestion;
    }

    public void setNomQuestion(String nomQuestion) {
        this.nomQuestion = nomQuestion;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idQuestion != null ? idQuestion.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Question)) {
            return false;
        }
        Question other = (Question) object;
        if ((this.idQuestion == null && other.idQuestion != null) || (this.idQuestion != null && !this.idQuestion.equals(other.idQuestion))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Question[ idQuestion=" + idQuestion + " ]";
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "question")
    private List<QuestionGroupement> listGroupement;

    public List<QuestionGroupement> getListGroupement() {
        return listGroupement;
    }

    public void setListGroupement(List<QuestionGroupement> listGroupement) {
        this.listGroupement = listGroupement;
    }
    
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "questionReponse")  
    private List<Reponse> listReponse;

    public List<Reponse> getListReponse() {
        return listReponse;
    }

    public void setListReponse(List<Reponse> listReponse) {
        this.listReponse = listReponse;
    }
    
    
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "ques")
    private List<QuestionReponse> listreponse;

    public List<QuestionReponse> getListreponse() {
        return listreponse;
    }

    public void setListreponse(List<QuestionReponse> listreponse) {
        this.listreponse = listreponse;
    }
    
    
    
    
}
