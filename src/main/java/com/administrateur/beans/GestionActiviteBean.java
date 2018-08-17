/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administrateur.beans;

import com.entities.Activite;
import com.entities.Filiale;
import com.session.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class GestionActiviteBean implements Serializable{
    
    
    @EJB
    private Service s;
    
    private List<Filiale>filiales;
    private List<String>fs;
    private String f;
    private String fl;
    private Filiale filiale;
    private List<Activite>activites;
    private Activite activite;
    public GestionActiviteBean() {
    }
    @PostConstruct
    public void init()
    {
        filiales=new ArrayList<Filiale>();
        filiale=new Filiale();
        filiales=s.getListFiliale();
        f=new String();
        f="";
        fl=new String();
        fs=new ArrayList<String>();
        for(Filiale fl:filiales)
        {
            fs.add(fl.getNomFiliale());
        }
        
                
        activites=new ArrayList<Activite>();
        activites=s.getListActivite();
        activite=new Activite();
        //activites.add(new Activite());
    }
    public void ajouter()
    {
        if  (activite.getNomActivite().equals("") || f.equals(""))     
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(activites.contains(activite))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Activité existe déjà !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            filiale=s.getFilialeParNom(f);
            //filiale.setNomFiliale("jkkj");
            //activite.setFiliale(filiale);
            System.out.println(filiale.getIdFiliale());
            activite.setFiliale(filiale);
            activites.add(activite);
            s.ajoutActivite(activite);
            activite=new Activite();
            filiale=new Filiale();
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Activité ajouté !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
    
    public void modifierActivite(Activite act)
    {
        System.out.println("modAct  "+fl);
        Filiale fa = s.getFilialeParNom(fl);
        System.out.println("fil"+fa.getNomFiliale());
        act.setFiliale(fa);
        System.out.println("act fil  "+act.getFiliale().getNomFiliale());
        s.updateActivite(act);
        System.out.println("fin modif");
    }
    
    public List<Filiale> getFiliales() {
        return filiales;
    }

    public void setFiliales(List<Filiale> filiales) {
        this.filiales = filiales;
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }

    public List<Activite> getActivites() {
        return activites;
    }

    public void setActivites(List<Activite> activites) {
        this.activites = activites;
    }

    public Activite getActivite() {
        return activite;
    }

    public void setActivite(Activite activite) {
        this.activite = activite;
    }

    public List<String> getFs() {
        return fs;
    }

    public void setFs(List<String> fs) {
        this.fs = fs;
    }

    public String getF() {
        return f;
    }

    public void setF(String f) {
        this.f = f;
    }

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }

    public String getFl() {
        return fl;
    }

    public void setFl(String fl) {
        this.fl = fl;
    }
    
}
