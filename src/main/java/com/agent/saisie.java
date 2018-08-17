/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.agent;

import com.entities.Affectation;
import com.entities.DetailsTache;
import com.entities.Filiale;
import com.entities.Groupement;
import com.entities.Question;
import com.entities.QuestionGroupement;
import com.entities.QuestionReponse;
import com.entities.Questionnaire;
import com.entities.QuestionnaireGroupement;
import com.entities.Reponse;
import com.entities.Tache;
import com.entities.Utilisateur;
import com.entities.UtilisateurAgence;
import com.session.Service;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.FlowEvent;

/**
 *
 * @author Administrateur
 */

@ManagedBean(name = "saisie")
@SessionScoped
public class saisie implements Serializable{
   
    @EJB
    Service s;
    
    private String login;
    private String mdp;
    private Boolean skip;
    
    private Utilisateur u;
    
    private Map<String, String> valQues;
    private HashMap<String, String> beya;
    private Map<String, String> treeMap;
    
    private Map<String, String> map;
    
    private Tache tache;
    private DetailsTache det;
    private String valeur;
    private String[] valeurs;
    private String rep;
    
    private Affectation affectation;
    private Questionnaire questionnaire;
    private List<Groupement> listGroup;    
    private List<Question> listQuestions;
    private List<QuestionnaireGroupement> listQuesGroup;
    private List<QuestionGroupement> lqg;
    private Question question;
    private List<QuestionGroupement> listQuestionGroup;
    private Reponse reponse;
    private QuestionReponse quRep;
    private List<QuestionReponse> listQuRep;
    
    
    
    
    
    
    
    public saisie() {
    }
    @PostConstruct
    public void init(){
        listGroup = new ArrayList<Groupement>();
        listQuRep = new ArrayList<QuestionReponse>();
        listQuesGroup = new ArrayList<QuestionnaireGroupement>();
        listQuestions = new ArrayList<Question>();
        listQuestionGroup = new ArrayList<QuestionGroupement>();
        questionnaire = new Questionnaire();
        lqg = new ArrayList<QuestionGroupement>();
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login = sess.getAttribute("login").toString();
        mdp = sess.getAttribute("mdp").toString();
        
        det = new DetailsTache();
        u = s.getUtilisateur(login, mdp);
        
        
        //questionnaire = s.getQuestionnaireParAff(affectation);
       // listQuesGroup = s.getListGroupParQues(questionnaire);
        valeur = null;
        
        //tache = new Tache();
        //tache.setDateTache(new Date());
       // tache.setEtat("Saisie");
       // tache.setAffectationTache(affectation);
       // s.ajoutTache(tache);
        valeurs = new String[20];
        valQues = new TreeMap<>();
        treeMap = new TreeMap<>();
        map = new TreeMap<>();
    }
    
    public void ajoutDetailTache() throws IOException{
       /* for(QuestionGroupement a:lqg)
        {
            det = new DetailsTache();
            det.setTache(tache);
            det.setQuesDet(a.getQuestion());
            
            det.setValeurSaisie("0");
            //System.out.println(lqg.indexOf(a));
            s.ajoutDetailTache(det);
            //System.out.println(valeurs.length);
        }
        //valeurs = new String[20];
        lqg = new ArrayList<QuestionGroupement>();
        beya = new HashMap<String, String>();
        System.out.println(beya.get("21"));*/
        
        tache = new Tache();
        tache.setDateTache(new Date());
        tache.setEtat("Saisie");
        tache.setAffectationTache(affectation);
        s.ajoutTache(tache);
        System.out.println("enregistrement tache");
       
        for (Map.Entry mapentry : treeMap.entrySet()) {
           System.out.println("clé 1: "+mapentry.getKey()
                              + " | valeur 1: " + mapentry.getValue());

            det = new DetailsTache();
            det.setTache(tache);
            det.setQuesGrou(s.getQuesGroupe(s.getQuestionParId(mapentry.getKey().toString().charAt(1)),s.getGroupementParId(mapentry.getKey().toString().charAt(0))));
            det.setValeurReponse(null);
            if(mapentry.getValue() == null)
            det.setValeurSaisie(null);
            else
            { det.setValeurSaisie(mapentry.getValue().toString());}
            
            s.ajoutDetailTache(det);
            
        }
       
        for (Map.Entry mapentry : valQues.entrySet()) {
           System.out.println("clé 1: "+mapentry.getKey()
                              + " | valeur 1: " + mapentry.getValue());

           
            det = new DetailsTache();
            det.setTache(tache);
            det.setQuesGrou(s.getQuesGroupe(s.getQuestionParId(mapentry.getKey().toString().charAt(1)),s.getGroupementParId(mapentry.getKey().toString().charAt(0))));
            det.setValeurReponse(mapentry.getKey().toString().substring(2));
            if(mapentry.getValue() == null)
            det.setValeurSaisie(null);
            else
            { det.setValeurSaisie(mapentry.getValue().toString());}
            
            s.ajoutDetailTache(det);
            
        }
        treeMap = new TreeMap<>();
        map= new TreeMap<>();
        valQues = new TreeMap<>();
        
        FacesMessage msg = new FacesMessage("Enregistré avec succès", "Bravo : "+u.getPrenom());
            FacesContext.getCurrentInstance().addMessage(null, msg);
            FacesContext fc = FacesContext.getCurrentInstance();
            ExternalContext ec = fc.getExternalContext();
            ec.redirect("ListeTaches.xhtml");
        
    }
    
    public Boolean verifListe(Question q)
    {
        if(q == null)
        {
            System.out.println("question null");
            return false;
        }
        
        else {if(q.getTypeQuestion().compareTo("Liste") == 0)
        {
            System.out.println("type question liste");
            return true;
        }
        }
        System.out.println("type question n'est pas liste"+q.getNomQuestion());
            return false;
    }
    
    public Boolean verif(Map.Entry<String,String> g, Map.Entry<String,String> s){
        System.out.println("size valQues"+valQues.size());
        System.out.println("size treemap"+treeMap.size());
        if(s.getKey().charAt(0) == g.getKey().charAt(0))
            return true;
        else 
            return false;
    }
    
    public String getNomQues(char c){  
        System.out.println("size valQues"+valQues.size());
        System.out.println("size treemap"+treeMap.size());
        return (String) s.getQuestionParId(c).getNomQuestion();
    }
    
    public String getNomGroup(char c){  
        System.out.println("size valQues"+valQues.size());
        System.out.println("size treemap"+treeMap.size());
        return (String) s.getGroupementParId(c).getNomGroupement();
        
    }
    
    public List<String> getListReponse(QuestionGroupement q){
          List<String> ls = new ArrayList<String>();
          System.out.println("size valQues"+valQues.size());
          System.out.println("size treemap"+treeMap.size());
        for (Reponse r:s.getReponsesParQues(q.getQuestion()))
          {
              ls.add(r.getValeurReponse());
          }
        return ls;
    }
    
    public List<QuestionGroupement> getListQuestion(Groupement g)
    {   System.out.println("size valQues"+valQues.size());
    System.out.println("size treemap"+treeMap.size());
        System.out.println("list questions par groupement :  "+s.getListQuestionsParGrou(g).size());
        return (List<QuestionGroupement>) s.getListQuestionsParGrou(g);
    }
    
    public String onFlowProcess(FlowEvent event) {
        if(skip) {
            //skip = false;   //reset in case user goes back
            //return "confirm";
        }
        else {
            
            return event.getNewStep();
        
        }
        return "confirm";
    }
    
    public String avancer(FlowEvent event)
    {
        return event.getNewStep();
    }
    
    
    public void ajoutQues(QuestionGroupement quest)
    {
        lqg.add(quest);
        System.out.println(lqg.size());
    }
    
    public void goToSaisie(Affectation af) throws IOException
    {
        affectation = af;
        System.out.println("go to saisie   :  "+affectation.getPlateforme().getNomPlateforme());
        
        listGroup = new ArrayList<Groupement>();
        listQuRep = new ArrayList<QuestionReponse>();
        listQuesGroup = new ArrayList<QuestionnaireGroupement>();
        listQuestions = new ArrayList<Question>();
        listQuestionGroup = new ArrayList<QuestionGroupement>();
        questionnaire = new Questionnaire();
        lqg = new ArrayList<QuestionGroupement>();
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login = sess.getAttribute("login").toString();
        mdp = sess.getAttribute("mdp").toString();
        
        det = new DetailsTache();
        u = s.getUtilisateur(login, mdp);
        
        
        questionnaire = s.getQuestionnaireParAff(affectation);System.out.println(questionnaire.getNomQuestionnaire());
        listQuesGroup = s.getListGroupParQues(questionnaire);System.out.println(listQuesGroup.size());
        valeur = null;
        
        
        valeurs = new String[20];
        valQues = new TreeMap<>();
        treeMap = new TreeMap<>();
        map = new TreeMap<>();
        
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("saisie.xhtml");
    }

    public Affectation getAffectation() {
        return affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public List<Groupement> getListGroup() {
        return listGroup;
    }

    public void setListGroup(List<Groupement> listGroup) {
        this.listGroup = listGroup;
    }

    public List<Question> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<Question> listQuestions) {
        this.listQuestions = listQuestions;
    }

    public List<QuestionnaireGroupement> getListQuesGroup() {
        return listQuesGroup;
    }

    public void setListQuesGroup(List<QuestionnaireGroupement> listQuesGroup) {
        this.listQuesGroup = listQuesGroup;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public List<QuestionGroupement> getListQuestionGroup() {
        return listQuestionGroup;
    }

    public void setListQuestionGroup(List<QuestionGroupement> listQuestionGroup) {
        this.listQuestionGroup = listQuestionGroup;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public QuestionReponse getQuRep() {
        return quRep;
    }

    public void setQuRep(QuestionReponse quRep) {
        this.quRep = quRep;
    }

    public List<QuestionReponse> getListQuRep() {
        return listQuRep;
    }

    public void setListQuRep(List<QuestionReponse> listQuRep) {
        this.listQuRep = listQuRep;
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

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public String[] getValeurs() {
        return valeurs;
    }

    public void setValeurs(String[] valeurs) {
        this.valeurs = valeurs;
    }

    

    public Map<String, String> getValQues() {
        return valQues;
    }

    public void setValQues(Map<String, String> valQues) {
        this.valQues = valQues;
    }

    public DetailsTache getDet() {
        return det;
    }

    public void setDet(DetailsTache det) {
        this.det = det;
    }

    public List<QuestionGroupement> getLqg() {
        return lqg;
    }

    public void setLqg(List<QuestionGroupement> lqg) {
        this.lqg = lqg;
    }
    
    public Map<String, String> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(Map<String, String> treeMap) {
        this.treeMap = treeMap;
    }

    public HashMap<String, String> getBeya() {
        return beya;
    }

    public void setBeya(HashMap<String, String> beya) {
        this.beya = beya;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    
}
