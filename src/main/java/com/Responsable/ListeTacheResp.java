/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Responsable;

import com.entities.Affectation;
import com.entities.Utilisateur;
import com.session.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@ManagedBean(name = "ListeTacheResp")
public class ListeTacheResp implements Serializable {

    @EJB
    Service s;
    private List<Affectation> affectationsResp;
    String login;
    String mdp;
    private Utilisateur u;
    private Date dateCourante;
    @PostConstruct
    public void init(){
       
         dateCourante=new Date();
         
        affectationsResp = new ArrayList<Affectation>();
        
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                   .getExternalContext().getSession(false);
        login = session.getAttribute("login").toString();
        mdp = session.getAttribute("mdp").toString();
        u=s.getUtilisateur(login, mdp);
        affectationsResp = (List<Affectation>)s.getTacheNnSaisieResp(u);
        
        System.out.println(affectationsResp.size());
        
    }

    public List<Affectation> getAffectationsResp() {
        return affectationsResp;
    }

    public void setAffectationsResp(List<Affectation> affectationsResp) {
        this.affectationsResp = affectationsResp;
    }

    public Date getDateCourante() {
        return dateCourante;
    }

    public void setDateCourante(Date dateCourante) {
        this.dateCourante = dateCourante;
    }
    
    
}
