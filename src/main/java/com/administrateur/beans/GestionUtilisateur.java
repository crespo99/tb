/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administrateur.beans;

import com.entities.*;
import com.session.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class GestionUtilisateur implements Serializable{
    
    @EJB
    private Service s;
    private Utilisateur utilisateur;
    private List<Utilisateur>utilisateurs;
    private UtilisateurAgence ua;
    private List<UtilisateurAgence>uas;
    private List<ResponsableMarketing>rms;
    private List<OperationnelMarketing>oms;
    private OperationnelMarketing om;
    private ResponsableMarketing rm;
    private List<Agence>agences;
    private Agence agence;
    private List<String>ag;
    private String age;
    private List<Filiale>filiales;
    private List<String> fs;
    private Filiale filiale;
    private String nom;
    private String prenom;
    private String email;
    private String tel;
    private String login;
    private String mdp;
    private String poste;
    private String nomA;
    private String nomF;
    private String ag1;
    private String fll;
    public GestionUtilisateur() {
    }
    @PostConstruct
    public void init()
    {
        agences=new ArrayList<Agence>();
        agences=s.getListAgence();
        agence=new Agence();
        ag1 = new String();
        ag1="";
        fll = new String();
        fll="";
        ag=new ArrayList<String>();
        for(Agence a:agences)
        {
            ag.add(a.getNom());
        }
        uas=new ArrayList<UtilisateurAgence>();
        uas=s.getListAgents();
        //uas=s.getListUtilisateur();
        ua=new UtilisateurAgence();
        utilisateurs=new ArrayList<Utilisateur>();
        //utilisateurs=s.getListUtilisateur();
        System.out.println(uas.size());
        rms=new ArrayList<ResponsableMarketing>();
        rms=s.getListResponsables();
        rm=new ResponsableMarketing();
        oms=new  ArrayList<OperationnelMarketing>();
        oms=s.getListOperationnels();
        om=new OperationnelMarketing();
        
        age=new String();
        poste=null;
        filiales=new ArrayList<Filiale>();
        filiale=new Filiale();
        filiales=s.getListFiliale();
        fs=new ArrayList<String>();
        for(Filiale x:filiales)
        {
            fs.add(x.getNomFiliale());
        }
    }
    public  boolean validerEmail(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
    public void ajouter()
    {
        if(nom.equals("") || prenom.equals("") || mdp.equals("") || login.equals("") || email.equals("") || tel.equals(""))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(!validerEmail(email))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Vérifiez l'email !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(tel.length()<8)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Vérifiez le numéro de téléphone !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(poste==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir le poste d'utilisateur !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if (poste!=null)
        {
            if(poste.equals("Agent"))
            {
                if(nomA==null)
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir l'agence d'utilisateur !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    ua.setEmail(email);
                    ua.setLogin(login);
                    ua.setMdp(mdp);
                    ua.setPrenom(prenom);
                    ua.setNom(nom);
                    ua.setStatut("Actif");
                    ua.setTelephone(tel);
                    agence=s.getAgenceParNom(nomA);
                    System.out.println(agence.getAdresse());
                    ua.setAgence(agence);
                    
                    if(utilisateurs.contains(ua))
                    {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Compte existe déjà !");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                    else
                    {
                        uas.add(ua);
                        utilisateurs.add(ua);
                        s.ajoutAgent(ua);
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Agent ajouté !");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        poste=null;
                        ua=new UtilisateurAgence();
                    }    
                }
                
            }
            else if(poste.equals("Responsable filiale"))
            {
                if(nomF==null)
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir la filiale d'utilisateur !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    filiale=s.getFilialeParNom(nomF);                   
                    rm.setEmail(email);
                    rm.setLogin(login);
                    rm.setMdp(mdp);
                    rm.setPrenom(prenom);
                    rm.setNom(nom);
                    rm.setStatut("Actif");
                    rm.setTelephone(tel);
                    
                    if(utilisateurs.contains(rm))
                    {
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Compte existe déjà !");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                    }
                    else
                    {
                        s.ajoutRespMarketing(rm, filiale);
                        utilisateurs.add(rm);
                        rms.add(rm);
                        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Responsable marketing ajouté !");
                        FacesContext.getCurrentInstance().addMessage(null, msg);
                        poste=null;
                        rm=new ResponsableMarketing();
                    }
                    
                }
                
            }
            else if(poste.equals("Siège"))
            {
                om.setEmail(email);
                om.setLogin(login);
                om.setMdp(mdp);
                om.setPrenom(prenom);
                om.setNom(nom);
                om.setStatut("Actif");               
                om.setTelephone(tel);
                if(utilisateurs.contains(om))
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Compte existe déjà !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    s.ajoutOpMarketing(om);
                    utilisateurs.add(om);
                    oms.add(om);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Opérationnel marketing ajouté !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    poste=null;
                    om=new OperationnelMarketing();
                }
                
            }
            
        }
    }
    public String crypter(String password){
        String crypte= "";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;  
            crypte=crypte+(char)c; 
        }
        return crypte;
    }
    
    public void modifierAgent(UtilisateurAgence utag){
        Agence aggg = s.getAgenceParNom(ag1);
        utag.setAgence(aggg);
        s.updateAgent(utag);
    }
    
    public void modifierResponsable(ResponsableMarketing utag){
        Filiale aggg = s.getFilialeParNom(fll);
        utag.setFiliale(aggg);
        s.updateResponsable(utag);
    }
    
    public void modifierOperationnel(OperationnelMarketing utag){
        
        
        s.updateOperationnel(utag);
    }
    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public List<Utilisateur> getUtilisateurs() {
        return utilisateurs;
    }

    public void setUtilisateurs(List<Utilisateur> utilisateurs) {
        this.utilisateurs = utilisateurs;
    }

    public List<Agence> getAgences() {
        return agences;
    }

    public void setAgences(List<Agence> agences) {
        this.agences = agences;
    }

    public List<Filiale> getFiliales() {
        return filiales;
    }

    public void setFiliales(List<Filiale> filiales) {
        this.filiales = filiales;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public Filiale getFiliale() {
        return filiale;
    }

    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    }

    public List<String> getAg() {
        return ag;
    }

    public void setAg(List<String> ag) {
        this.ag = ag;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public List<UtilisateurAgence> getUas() {
        return uas;
    }

    public void setUas(List<UtilisateurAgence> uas) {
        this.uas = uas;
    }

    public List<ResponsableMarketing> getRms() {
        return rms;
    }

    public void setRms(List<ResponsableMarketing> rms) {
        this.rms = rms;
    }

    public UtilisateurAgence getUa() {
        return ua;
    }

    public void setUa(UtilisateurAgence ua) {
        this.ua = ua;
    }

    public List<String> getFs() {
        return fs;
    }

    public void setFs(List<String> fs) {
        this.fs = fs;
    }

    public ResponsableMarketing getRm() {
        return rm;
    }

    public void setRm(ResponsableMarketing rm) {
        this.rm = rm;
    }

    public List<OperationnelMarketing> getOms() {
        return oms;
    }

    public void setOms(List<OperationnelMarketing> oms) {
        this.oms = oms;
    }

    public OperationnelMarketing getOm() {
        return om;
    }

    public void setOm(OperationnelMarketing om) {
        this.om = om;
    }

    public String getNomA() {
        return nomA;
    }

    public void setNomA(String nomA) {
        this.nomA = nomA;
    }

    public String getNomF() {
        return nomF;
    }

    public void setNomF(String nomF) {
        this.nomF = nomF;
    }

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAg1() {
        return ag1;
    }

    public void setAg1(String ag1) {
        this.ag1 = ag1;
    }

    public String getFll() {
        return fll;
    }

    public void setFll(String fll) {
        this.fll = fll;
    }
    
    
    
}
