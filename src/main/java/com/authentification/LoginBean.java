/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.authentification;

import com.entities.*;
import com.session.Service;
import java.io.IOException;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
//import sun.security.krb5.internal.crypto.Aes128;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage;
import org.apache.commons.lang3.RandomStringUtils;



@ManagedBean
@SessionScoped
public class LoginBean implements Serializable {
    
    
    public LoginBean() {
    }
    @EJB//(lookup = "java:global/TB_Marketing/Service!com.session.ServiceLocal")
    private Service s;
    private String login;
    private String mail;
    private String mdp;
    private String role;
    private String dossier;
    private List<Reclamation> reclamations;
    private List<Activite>activitesReclamees;
    private List<Plateforme>platReclamees;
    
    private List<String> logos;
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
    @PostConstruct
    public void init(){
        logos=new ArrayList<String>();
        logos.add("Digikey-B");
        logos.add("Digikey-N");
        
    }
    public String crypter(String password){
        String crypte= "";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;  
            crypte=crypte+(char)c; 
        }
        return crypte;
    }
    public String decrypter(String password){
        String aCrypter= "";
        for (int i=0; i<password.length();i++)  {
            int c=password.charAt(i)^48;  
            aCrypter=aCrypter+(char)c; 
        }
        return aCrypter;
    }
    
    
    public String doLogin() throws IOException
    { 
        //System.out.println(s.getDate().size());
        /*FocusQualitatif fq=new FocusQualitatif();
        s.ajoutFocus(fq);*/
      /* Plateforme p=new Plateforme("Facebook");
        s.ajoutPlateforme(p);
        Plateforme p1=new Plateforme("Instagram");
        s.ajoutPlateforme(p1);
        Administrateur a=new Administrateur();
        a.setLogin("admin");
        a.setEmail("wael.weslati@ensi-uma.tn");
        a.setMdp("admin");
        a.setNom("Weslati");
        a.setPrenom("Wael");
        a.setStatut("Actif");
        a.setTelephone("54372403");
        s.ajoutAdmin(a);*/
        /*System.out.println(s.getActiviteParNom("Huile").getIdActivite());
        System.out.println(s.getActiviteConcurrentParNom("aaa").getIdActiviteConcurrent());
        System.out.println(s.getConcurrentParActiviteParId(2,15).getActiviteConcurrent().getNom());
        /*ConcurrentParActivite con=new ConcurrentParActivite();
        con.setActivite(s.getActiviteParNom("Huile"));
        con.setActiviteConcurrent(s.getActiviteConcurrentParNom("aaa"));
        s.ajoutConcurrentParActivite(con);*/
        //System.out.println(s.getActiviteConcurrentParNom("Huile S").getIdActiviteConcurrent());
        //System.out.println(s.getAgentSaisie(s.getActiviteParNom("Huile")));
        //s.modifierMotPass("wael@gmail.com", "wael");
       /* Reclamation rec = s.getReclamationParId(10);
        Tache tache = s.getTacheParReclamation(rec);
        Engagement eng = s.getEngagementParTache(tache);
        System.out.println(s.getListGeosParEngagement(eng).size());*/
       /* String str = "kjkghkkgh:jfkjdf";
        System.out.println(str.substring(str.indexOf(":")+1,str.length()));*/
        //System.out.println("nb fans ce mois  =  "+Integer.parseInt(s.getNbFan()));
        System.out.println("dernier details tache"+s.getIdLastScrap());
        
        if(login.equals(""))
        {
            FacesContext fc=FacesContext.getCurrentInstance();
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de Saisie","Identifiant incorrect!");
            fc.addMessage(null, fm);
            return "login.xhtml";
        }
        else if(mdp.equals(""))
        {
            FacesContext fc=FacesContext.getCurrentInstance();
            FacesMessage fm=new FacesMessage(FacesMessage.SEVERITY_ERROR,"Erreur de Saisie","Mot de passe incorrect!");
            fc.addMessage(null, fm);
            return "login.xhtml";
        }
        else
        {
            
            try{
                Utilisateur u=s.getUtilisateur(login, mdp); 
                //System.out.println(u.getNom()+u.getStatut()+u.getMdp());
                if(u instanceof UtilisateurAgence)
                {       
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                   .getExternalContext().getSession(false);
                   //session.setAttribute("utilisateur", u);
                   session.setAttribute("nom", u.getNom());
                   session.setAttribute("prenom", u.getPrenom());
                   session.setAttribute("login",login);
                   session.setAttribute("mdp", mdp);
                   
                   reclamations=new ArrayList<Reclamation>();
                   reclamations=s.getListReclamationsParUtilisateur(u);
                   activitesReclamees=new ArrayList<Activite>();
                   platReclamees=new ArrayList<Plateforme>();
                   for(Reclamation r:reclamations)
                   {
                       activitesReclamees.add(r.getDetailsTache().getTache().getAffectationTache().getActivite());
                       platReclamees.add(r.getDetailsTache().getTache().getAffectationTache().getPlateforme());
                   }

                   //System.out.println(u.getPrenom());
                   role="Agent";
                   dossier="Agent";
                   session.setAttribute("dossier", dossier);
                   session.setAttribute("role", role);
                   
                   return "/Agent/acceuil.xhtml?faces-redirect=true";
                   //return "/Administration/index.xhtml?faces-redirect=true";
                   
                   
                }
                else if(u instanceof ResponsableMarketing){
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
                    //System.out.println(((ResponsableMarketing) u).getFiliale().getNomFiliale());
                    session.setAttribute("nom", u.getNom());
                    session.setAttribute("prenom", u.getPrenom());
                    session.setAttribute("login",login);
                    session.setAttribute("mdp", mdp);
                   
                    role="Filiale";
                    dossier="ResponsableFiliale";
                    session.setAttribute("dossier", dossier);
                    session.setAttribute("role", role);
                    return "/ResponsableFiliale/acceuil.xhtml?faces-redirect=true";
                    //return "/Administration/index.xhtml?faces-redirect=true";
                }
                else if(u instanceof Administrateur)
                {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
                    
                    //session.setAttribute("utilisateur", u);
                    //System.out.println(s.getListAgentsParAgence("Streemerz").get(0).getNom());
                    session.setAttribute("nom", u.getNom());
                    session.setAttribute("prenom", u.getPrenom());
                    session.setAttribute("login",login);
                    session.setAttribute("mdp", mdp);
                    role="Admin";
                    dossier="Admin";
                    session.setAttribute("dossier", dossier);
                    session.setAttribute("role", role);
                    return "/Admin/acceuil.xhtml?faces-redirect=true";
                }   
                else if(u instanceof OperationnelMarketing)
                {
                    HttpSession session = (HttpSession) FacesContext.getCurrentInstance()
                    .getExternalContext().getSession(false);
                    
                    //session.setAttribute("utilisateur", u);
                    //System.out.println(s.getListAgentsParAgence("Streemerz").get(0).getNom());
                    session.setAttribute("nom", u.getNom());
                    session.setAttribute("prenom", u.getPrenom());
                    session.setAttribute("login",login);
                    session.setAttribute("mdp", mdp);
                    //System.out.println(s.getListActiviteConsultees(u).size());
                    
                    role="Operationnel";
                    dossier="OperationnelMarketing";
                    session.setAttribute("dossier", dossier);
                    session.setAttribute("role", role);
                    return "/OperationnelMarketing/acceuil.xhtml?faces-redirect=true";
                }   
            }catch(Exception g)
            {           
                System.out.println("Echec de connexion");
                FacesMessage fm=new FacesMessage("Echec de connexion","Compte introuvable!");
                fm.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, fm);
                return "login.xhtml";               
            }
            
            
        }
        
       return "login.xhtml";   
    }
    public void doLogout() throws IOException
    {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
            .getExternalContext().getSession(false);
    sess.invalidate();

    FacesContext fc = FacesContext.getCurrentInstance();
    ExternalContext ec = fc.getExternalContext();
    ec.redirect("../login.xhtml");
            
    }
   
    public Service getS() {
        return s;
    }

    public void setSs(Service s) {
        this.s = s;
    }

    public String getDossier() {
        return dossier;
    }

    public void setDossier(String dossier) {
        this.dossier = dossier;
    }

    

    public List<String> getLogos() {
        return logos;
    }

    public void setLogos(List<String> logos) {
        this.logos = logos;
    }
    
    public void sendmail(){
        
        if (s.verifMail(mail)){
                    String password = modifierMotPasse();
                try{
            String host ="smtp.gmail.com" ;
            String user = "digkey.pgh@gmail.com ";
            String pass = "marwenwaelakrem";
            String to = mail;
            String from = "digkey.pgh@gmail.com ";
            String subject = "Mot de passe oublié.";
            String messageText = "Bonjour, "
                    + "Voici votre nouveau mot de passe "+password;
            boolean sessionDebug = false;

                    Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
            
           s.modifierMotPass(mail, password);
            
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           
        }catch(Exception ex)
        {
            System.out.println(ex);
        }


                
                FacesMessage fm=new FacesMessage("Succès","Email envoyé avec votre nouveau mot de passe");
                fm.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, fm);                
                
        }
        else {
                FacesMessage fm=new FacesMessage("Echec","Compte introuvable");
                fm.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, fm);
                
        }
        mail="";
    }
    
    public String modifierMotPasse(){
        
        char[] possibleCharacters = (new String("ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?")).toCharArray();
        String randomStr = RandomStringUtils.random( 10, 0, possibleCharacters.length-1, false, false, possibleCharacters, new SecureRandom() );
        System.out.println( randomStr );
        return randomStr;
        
    }
    
    
    
    public String getPageMotPasse(){
        return "mpoublie.xhtml?faces-redirect=true";
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
