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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Reponse")
public class Reponse implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idReponse")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idReponse;   
    @Column(name = "valeurReponse")
    private String valeurReponse;    
    @Column(name = "reponseCible")
    private Boolean reponseCible;

    public Reponse() {
    }
    
    public Integer getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(Integer idReponse) {
        this.idReponse = idReponse;
    }

    public String getValeurReponse() {
        return valeurReponse;
    }

    public void setValeurReponse(String valeurReponse) {
        this.valeurReponse = valeurReponse;
    }

    public Boolean getReponseCible() {
        return reponseCible;
    }

    public void setReponseCible(Boolean reponseCible) {
        this.reponseCible = reponseCible;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idReponse != null ? idReponse.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reponse)) {
            return false;
        }
        Reponse other = (Reponse) object;
        if ((this.idReponse == null && other.idReponse != null) || (this.idReponse != null && !this.idReponse.equals(other.idReponse))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entities.Reponse[ idReponse=" + idReponse + " ]";
    }
    
    @ManyToOne
    @JoinColumn(name = "idQuestion")
    private Question questionReponse;

    public Question getQuestionReponse() {
        return questionReponse;
    }

    public void setQuestionReponse(Question questionReponse) {
        this.questionReponse = questionReponse;
    }
    
    

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "rep")
    private List<QuestionReponse> ListQuestionLiee;

    public List<QuestionReponse> getListQuestionLiee() {
        return ListQuestionLiee;
    }

    public void setListQuestionLiee(List<QuestionReponse> ListQuestionLiee) {
        this.ListQuestionLiee = ListQuestionLiee;
    }    
    
}
