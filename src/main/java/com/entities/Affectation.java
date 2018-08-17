/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Administrateur
 */
@Entity
@Table(name = "Affectation")
public class Affectation implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idAffectation")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAffectation;
    
    @Column(name = "urlPlateforme")
    private String urlPlateforme;
    
    @Column(name = "dateAffectation")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateAffectation;

    public Affectation() {
    }

    public Integer getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public String getUrlPlateforme() {
        return urlPlateforme;
    }

    public void setUrlPlateforme(String urlPlateforme) {
        this.urlPlateforme = urlPlateforme;
    }

    public Date getDateAffectation() {
        return dateAffectation;
    }

    public void setDateAffectation(Date dateAffectation) {
        this.dateAffectation = dateAffectation;
    }

    @Override
    public String toString() {
        return "Affectation{" + "idAffectation=" + idAffectation + '}';
    }
    
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idQuestionnaire")
    private Questionnaire questionnaireAffectation;

    public Questionnaire getQuestionnaireAffectation() {
        return questionnaireAffectation;
    }

    public void setQuestionnaireAffectation(Questionnaire questionnaireAffectation) {
        this.questionnaireAffectation = questionnaireAffectation;
    }
    
    @ManyToOne
    @JoinColumn(name="idPlateforme")
    private Plateforme plateforme;

    public Plateforme getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(Plateforme plateforme) {
        this.plateforme = plateforme;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idActivite")
    private Activite activite;

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idAgent")
    private UtilisateurAgence agent;

    public UtilisateurAgence getAgent() {
        return agent;
    }

    public void setAgent(UtilisateurAgence agent) {
        this.agent = agent;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idRespMark")
    private ResponsableMarketing responsable;

    public ResponsableMarketing getResponsable() {
        return responsable;
    }

    public void setResponsable(ResponsableMarketing responsable) {
        this.responsable = responsable;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "affectationConsultee")
    private List<AffectationConsultee> ListConsulteurs;

    public List<AffectationConsultee> getListConsulteurs() {
        return ListConsulteurs;
    }

    public void setListConsulteurs(List<AffectationConsultee> ListConsulteurs) {
        this.ListConsulteurs = ListConsulteurs;
    }
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "affectationTache")
    private List<Tache> listTaches;

    public List<Tache> getListTaches() {
        return listTaches;
    }

    public void setListTaches(List<Tache> listTaches) {
        this.listTaches = listTaches;
    }
    
    @OneToMany(mappedBy = "aff")
    private List<Scrap> listScrap;

    public List<Scrap> getListScrap() {
        return listScrap;
    }

    public void setListScrap(List<Scrap> listScrap) {
        this.listScrap = listScrap;
    }
    
    
    
}
