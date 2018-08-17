/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent;

import com.entities.Activite;
import com.entities.Plateforme;
import com.entities.Reclamation;
import com.entities.Utilisateur;
import com.session.Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;


@ManagedBean
@ViewScoped
public class AcceuilBean implements Serializable{
    
    @EJB
    Service s;
    
    /**
     * Creates a new instance of AccueilBean
     */
    String login;
    String mdp;
    private Utilisateur u;
    
    
    public String getLogin() {
        return login;
    }
    
    private List<Reclamation> reclamations;
    private List<Activite>activitesReclamees;
    private List<Plateforme>platReclamees;
    
    public void setLogin(String login) {
        this.login = login;
    }
    public AcceuilBean() {
    }
    
    @PostConstruct()
    public void init(){
        reclamations = new ArrayList<Reclamation>();
        
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login = sess.getAttribute("login").toString();
        mdp = sess.getAttribute("mdp").toString();
        u = s.getUtilisateur(login, mdp);
        reclamations = s.getListReclamationsParUtilisateur(u);
        activitesReclamees=new ArrayList<Activite>();
        platReclamees=new ArrayList<Plateforme>();
        for(Reclamation r:reclamations)
        {
            activitesReclamees.add(r.getDetailsTache().getTache().getAffectationTache().getActivite());
            platReclamees.add(r.getDetailsTache().getTache().getAffectationTache().getPlateforme());
        }
    }
    public void saisie() throws IOException
    {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login=sess.getAttribute("login").toString();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("ListeTaches.xhtml");
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }

    public List<Activite> getActivitesReclamees() {
        return activitesReclamees;
    }

    public void setActivitesReclamees(List<Activite> activitesReclamees) {
        this.activitesReclamees = activitesReclamees;
    }

    public List<Plateforme> getPlatReclamees() {
        return platReclamees;
    }

    public void setPlatReclamees(List<Plateforme> platReclamees) {
        this.platReclamees = platReclamees;
    }
    
    
}
