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
@Table(name = "DetailsTache")
public class DetailsTache implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idDetailsTache")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDetailsTache;
    
    @Column(name = "valeurSaisie")
    private String valeurSaisie;
    
    @Column(name = "valeurReponse")
    private String valeurReponse;
    

    public DetailsTache() {
    }

    public Integer getIdDetailsTache() {
        return idDetailsTache;
    }

    public void setIdDetailsTache(Integer idDetailsTache) {
        this.idDetailsTache = idDetailsTache;
    }

    public String getValeurReponse() {
        return valeurReponse;
    }

    public void setValeurReponse(String valeurReponse) {
        this.valeurReponse = valeurReponse;
    }

    public String getValeurSaisie() {
        return valeurSaisie;
    }

    public void setValeurSaisie(String valeurSaisie) {
        this.valeurSaisie = valeurSaisie;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idTache")
    private Tache tache;

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    } 
 
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "detailsTache")
    private List<Reclamation> listReclamations;

    public List<Reclamation> getListReclamations() {
        return listReclamations;
    }

    public void setListReclamations(List<Reclamation> listReclamations) {
        this.listReclamations = listReclamations;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idQuesGroup")
       private QuestionGroupement quesGrou;

    public QuestionGroupement getQuesGrou() {
        return quesGrou;
    }

    public void setQuesGrou(QuestionGroupement quesGrou) {
        this.quesGrou = quesGrou;
    }
    
    
}
