/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent;

import com.entities.Reclamation;
import com.session.Service;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

/**
 *
 * @author Administrateur
 */
@ManagedBean
@SessionScoped
public class EditReclamation implements  Serializable{
    @EJB
    Service s;

    public EditReclamation() {
    }
    
    Reclamation reclamation ;
    String plateforme = "";
    String activite="";
    String filiale="";
    
    Integer nouvelleValeurInt;
    String nouvelleValeurStr;
    Double nouvelleValeurDbl;
    
    public void attributeListener(ActionEvent event) {
        reclamation = (Reclamation)event.getComponent().getAttributes().get("reclama");
        System.out.println(reclamation.getSujet());  
        plateforme = reclamation.getDetailsTache().getTache().getAffectationTache().getPlateforme().getNomPlateforme();
        activite = reclamation.getDetailsTache().getTache().getAffectationTache().getActivite().getNomActivite();
        filiale = reclamation.getDetailsTache().getTache().getAffectationTache().getActivite().getFiliale().getNomFiliale();
    }
    
    @PostConstruct
    public void init(){
        nouvelleValeurInt = null;
        nouvelleValeurStr = null;
        nouvelleValeurDbl = null;
    }
    
    public void editer() throws IOException
    {
        System.out.println("début edition");
        reclamation.getDetailsTache().setValeurSaisie(nouvelleValeurStr);
        reclamation.setTraitee(true);
        s.updateDetailTache(reclamation.getDetailsTache());
        s.updateReclamation(reclamation);
        System.out.println("fin edition");
        
        if( s.getListRecParTacheNonTraitee(reclamation.getDetailsTache().getTache()).isEmpty())
        {
            reclamation.getDetailsTache().getTache().setCorrigee(true);
            System.out.println("edit tache corrigee");
            s.updateTache(reclamation.getDetailsTache().getTache());
        }
            
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("acceuil.xhtml");
        
        nouvelleValeurInt = null;
        nouvelleValeurStr = null;
        nouvelleValeurDbl = null;
        
    }
    
    public String goTocorrection(){
        return "/Agent/correction.xhtml?faces-redirect=true";
        
    }

    public Reclamation getReclamation() {
        return reclamation;
    }

    public void setReclamation(Reclamation reclamation) {
        this.reclamation = reclamation;
    }

    public String getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(String plateforme) {
        this.plateforme = plateforme;
    }

    public String getActivite() {
        return activite;
    }

    public void setActivite(String activite) {
        this.activite = activite;
    }

    public String getFiliale() {
        return filiale;
    }

    public void setFiliale(String filiale) {
        this.filiale = filiale;
    }

    public Integer getNouvelleValeurInt() {
        return nouvelleValeurInt;
    }

    public void setNouvelleValeurInt(Integer nouvelleValeurInt) {
        this.nouvelleValeurInt = nouvelleValeurInt;
    }

    public String getNouvelleValeurStr() {
        return nouvelleValeurStr;
    }

    public void setNouvelleValeurStr(String nouvelleValeurStr) {
        this.nouvelleValeurStr = nouvelleValeurStr;
    }

    public Double getNouvelleValeurDbl() {
        return nouvelleValeurDbl;
    }

    public void setNouvelleValeurDbl(Double nouvelleValeurDbl) {
        this.nouvelleValeurDbl = nouvelleValeurDbl;
    }
    
    
}
