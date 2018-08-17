/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent;

import com.entities.Activite;
import com.entities.Affectation;
import com.entities.Filiale;
import com.entities.Plateforme;
import com.entities.Utilisateur;
import com.session.Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@ManagedBean(name = "listeTache",eager = true)
@ViewScoped
public class ListeTaches implements Serializable{
    
    @EJB
    Service s;

    public ListeTaches() {
    }
    
    
    private Filiale filiale;
    private Activite activite;
    private Plateforme plateforme;
    private Utilisateur u;
    private List<Affectation> affectations;
    private List<Affectation> affectationsResp;
    String login;
    String mdp;
    String role;
    private Affectation affectation;
    
    private Date dateCourante;
    @PostConstruct
    public void init() {
       
         dateCourante=new Date();
         affectations = new ArrayList<Affectation>();
         affectationsResp = new ArrayList<Affectation>();
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                   .getExternalContext().getSession(false);
        login = session.getAttribute("login").toString();
        mdp = session.getAttribute("mdp").toString();
        role = session.getAttribute("role").toString();
        u=s.getUtilisateur(login, mdp);
        switch(role)
        {
            case "Filiale":
                affectationsResp = (List<Affectation>)s.getTacheNnSaisieResp(u);
                break;
            default:
                affectations = (List<Affectation>)s.getTacheNnSaisieAgent(u);
                break;
        }
        
        
        System.out.println(affectationsResp.size());
        
        System.out.println(affectations.size());
        
    }

    public void saisie() throws IOException
    {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login=sess.getAttribute("login").toString();
        sess.setAttribute("plateforme", u);
        
        System.out.println("saisie fct : "+affectations.size());
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("saisie.xhtml");
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public Plateforme getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(Plateforme plateforme) {
        this.plateforme = plateforme;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public List<Affectation> getAffectations() {
        return affectations;
    }

    public void setAffectations(List<Affectation> affectations) {
        this.affectations = affectations;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }

    public Date getDateCourante() {
        return dateCourante;
    }

    public void setDateCourante(Date dateCourante) {
        this.dateCourante = dateCourante;
    }

    public List<Affectation> getAffectationsResp() {
        return affectationsResp;
    }

    public void setAffectationsResp(List<Affectation> affectationsResp) {
        this.affectationsResp = affectationsResp;
    }
    
    
    
}
