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
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.event.TabChangeEvent;

@ManagedBean
@ViewScoped
public class ParametrerBean implements Serializable{
    
    @EJB
    private Service s;
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private Map<String,Map<String,String>> agenceAgents = new HashMap<String, Map<String,String>>();
    private Map<String,Map<String,String>> filialeRespos = new HashMap<String, Map<String,String>>();
    private String fil; 
    private String act; 
    private String plat;
    private String ag;
    private String agt;
    private String filR;
    private String res;
    private Map<String,String> fils;
    private Map<String,String> acts;
    private List<String> plats; 
    private Map<String,String> ags;
    private Map<String,String> agts;
    private Map<String,String> respos;
    
    private List<Filiale>filiales;
    private Filiale filiale;
    private List<Activite>activites;
    private List<Agence> agences;
    private Agence agence;
    private List<UtilisateurAgence> agents;
    private UtilisateurAgence ua;
    private List<ResponsableMarketing>rms;
    private ResponsableMarketing rm;
    private List<Plateforme>reseauSocials;
    private Activite activite;
    private Plateforme plateforme;
    private List<Affectation> affs;
    
   // private TrancheAges t;
    private String borneMin;
    private String borneMax;
  //  private List<TrancheAges>tranches;
    
    //question
    private Question question;
    private String nomQuestion;
    private String typeQuestion;
    private List<Question> questions;
    private String ques;
    private Map<String,String> quest;
    
    //reponse
    private Reponse reponse;
    private List<Reponse> reponses;
    private Boolean reponseCible;
    private String valeurReponse;
    private Question quesRep;
    private Map<String,String> rep;
    private String reponseVal;
    
    //groupement
    private Groupement groupement;
    private List<Groupement> groupements;
    private String nomGroupement;
    private Map<String,String> group;
    private String grou;
    
    //questionnaire
    private Questionnaire questionnaire;
    private String nomQuestionnaire;
    private List<Questionnaire> questionnaires;
    private Map<String,String> questionna;
    private String qu;
    
    //questionnaire groupement
    private QuestionnaireGroupement qg;
    private List<QuestionnaireGroupement> listqg;
    private List<QuestionnaireGroupement> quesGrou;
     
    //questiongroupement
    private QuestionGroupement queg;
    private List<QuestionGroupement> ltqg;
    private Boolean cib;
    
  //  private Gouvernorat g;
    private String pays;
    private String ville;
 //   private List<Gouvernorat>gouvs;
    
    //affectations consultées
    private List<AffectationConsultee> affCon;
    private AffectationConsultee afC;
    
    private String actC;
    private String concurrent;
    private ActiviteConcurrent ac;
    private ConcurrentParActivite cpa;
    private List<ConcurrentParActivite> cpas;
    private List<ActiviteConcurrent> acs;
    
    private List<OperationnelMarketing>ops;
    private List<String> nomsOps;
    private String[] nomsSelected;
    
    private String acc;
    private String tr;
    private List<String>trs;
    private List<SelectItem> groupAct;
   
    
    @PostConstruct
    public void init()
    {
        ques = null;
        qu=null;
        grou=null;
        reponseVal=null;
        fil=null;
        act=null;
        plat=null;
        ag=null;
        agt=null;
        filR=null;
        res=null;
        actC=null;
        cib=null;
        concurrent=null;
        affs=new ArrayList<Affectation>();
        affs=s.getListAffectations();
        filiales=new ArrayList<Filiale>();
        filiales=s.getListFiliale();
        filiale=new Filiale();       
        activite=new Activite();       
        fils  = new HashMap<String, String>();
        quest  = new HashMap<String, String>();
        questionna = new HashMap<String, String>();
        group=new HashMap<String, String>();
        rep= new HashMap<String, String>();
        listqg = new ArrayList<QuestionnaireGroupement>();
        quesGrou = new ArrayList<QuestionnaireGroupement>();
        queg = new QuestionGroupement();
        ltqg = new ArrayList<QuestionGroupement>();
        affCon = new ArrayList<AffectationConsultee>();
        
        for(Filiale f:filiales)
        {
            fils.put(f.getNomFiliale(),f.getNomFiliale());
        }
        for(int i=0;i<filiales.size();i++)
        {
            activites=new ArrayList<Activite>();
            activites=s.getListActivitesParFiliale(filiales.get(i).getNomFiliale());            
            Map<String,String> map = new HashMap<String, String>();
            for(Activite a:activites)
            {
                map.put(a.getNomActivite(), a.getNomActivite());
            }
            data.put(filiales.get(i).getNomFiliale(), map);
        }
        plats =new ArrayList<String>();
        reseauSocials=new ArrayList<Plateforme>();
        reseauSocials=s.getListPlateformes();
        for(Plateforme p:reseauSocials)
        {
            plats.add(p.getNomPlateforme());
        }
        
        ags=new HashMap<String, String>();
        agts=new HashMap<String, String>();
        agence=new Agence();
        ua=new UtilisateurAgence();
        agences=new ArrayList<Agence>();
        agences=s.getListAgence();
        for(Agence a:agences)
        {
            ags.put(a.getNom(),a.getNom());
        }
        for(int i=0;i<agences.size();i++)
        {
            agents=new ArrayList<UtilisateurAgence>();            
            agents=s.getListAgentsParAgence(agences.get(i).getNom());            
            Map<String,String> map = new HashMap<String, String>();
            for(UtilisateurAgence a:agents)
            {
                map.put(a.getPrenom()+" "+a.getNom(), a.getPrenom()+" "+a.getNom());
            }
            agenceAgents.put(agences.get(i).getNom(), map);                  
        }
        for(int i=0;i<filiales.size();i++)
        {
            rms=new ArrayList<ResponsableMarketing>();            
            rms=s.getListResponsablesParFiliale(filiales.get(i).getNomFiliale());
            Map<String,String> map = new HashMap<String, String>();
            for(ResponsableMarketing a:rms)
            {
                map.put(a.getPrenom()+" "+a.getNom(), a.getPrenom()+" "+a.getNom());
            }
            filialeRespos.put(filiales.get(i).getNomFiliale(), map);
        }
      //  tranches=new ArrayList<TrancheAges>();
       // tranches=s.getListTrancheAges();
      //  t=new TrancheAges();
        trs=new ArrayList<String>();
     /*   for(TrancheAges tt:tranches)
        {
            trs.add(tt.getIntervalle());
        }
        */
     //   gouvs=new ArrayList<Gouvernorat>();
     //   g=new Gouvernorat();
     //   gouvs=s.getListGouvernorats();
        
        question = new Question();
        reponse = new Reponse();
        groupement = new Groupement();
        questionnaire = new Questionnaire();
        qg = new QuestionnaireGroupement();
        afC = new AffectationConsultee();
        
        
        questions = s.getQuestions();
        System.out.println(questions.size());
        reponses = s.getReponses();
        groupements = s.getGroupements();
        questionnaires = s.getQuestionnaires();
        listqg = s.getListQuestionnaireGroupement();
        ltqg = s.getListQuestionGroupement();
        affCon = s.getListAffCons();
        
        for(Question q:questions)
        {
            quest.put(q.getNomQuestion(), q.getNomQuestion());
        }
        for(Questionnaire t:questionnaires)
        {
            questionna.put(t.getNomQuestionnaire(), t.getNomQuestionnaire());
        }
        for(Groupement g:groupements)
        {
            group.put(g.getNomGroupement(), g.getNomGroupement());
        }
        for(Reponse r:reponses)
        {
            rep.put(r.getValeurReponse(), r.getValeurReponse());
        }
        
        
        ac=new ActiviteConcurrent();
        cpa=new ConcurrentParActivite();
        cpas=new ArrayList<ConcurrentParActivite>();
        acs=new ArrayList<ActiviteConcurrent>();
        acs=s.getListActiviteConcurrent();
        cpas=s.getListConcurrentParActivite();
        
        ops=new ArrayList<OperationnelMarketing>();
        ops=s.getListOperationnels();
        nomsOps=new ArrayList<String>();
        for(OperationnelMarketing o:ops)
        {
            nomsOps.add(o.getPrenom()+" "+o.getNom());
        }
        
        groupAct = new ArrayList<SelectItem>();
        for(Filiale f:filiales)
        {
            SelectItemGroup g1 = new SelectItemGroup(f.getNomFiliale());
            List<Activite>acss=new ArrayList<Activite>();
            acss=s.getListActivitesParFiliale(f.getNomFiliale());
            SelectItem []items=new SelectItem[acss.size()];
            for(int i=0;i<acss.size();i++)
            {
                items[i]=new SelectItem(acss.get(i).getNomActivite(), acss.get(i).getNomActivite());
            }
            g1.setSelectItems(items);
            groupAct.add(g1);
        }  
    }
    
    public ParametrerBean() {
    }
    public void onFilialeChange() {
        if(fil !=null && !fil.equals(""))
            acts = data.get(fil);
        else
            acts = new HashMap<String, String>();
    }
    
    
    public void onAgenceChange() {
        if(ag !=null && !ag.equals(""))
            agts = agenceAgents.get(ag);
        else
            agts = new HashMap<String, String>();
    }
    public void onFiliale2Change() {
        if(fil !=null && !fil.equals(""))
            respos = filialeRespos.get(fil);
        else
            respos = new HashMap<String, String>();
    }
    public void ajouterAffectation()
    {
        
        if(act==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir l'activité à affecter !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(qu==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir le questionnaire de saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(agt==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir l'agent de saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(plat==null)
        {
           
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir la plateforme !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(res==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir le vis-à-vis filiale de validation !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(nomsSelected.length==0)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir au moins un opérationnel marketing !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            Affectation saisie=new Affectation();
            activite=s.getActiviteParNom(act);
            filiale=s.getFilialeParNom(fil);
            questionnaire = s.getQuestionnaireParNom(qu);
            ua=s.getAgentParNom(agt.split(" ")[1], agt.split(" ")[0]);
            rm=s.getResponsableParNom(res.split(" ")[1], res.split(" ")[0]);
            plateforme=s.getPlateformeParNom(plat);
            
            
            saisie.setAgent(ua);
            saisie.setActivite(activite);
            saisie.setPlateforme(plateforme);
            saisie.setQuestionnaireAffectation(questionnaire);
            saisie.setUrlPlateforme(plat);
            saisie.setDateAffectation(new Date());
            saisie.setResponsable(rm);
            if(affs.contains(saisie)) 
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Affectation de saisie existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                System.out.println("id plateforme = "+saisie.getPlateforme().getNomPlateforme());
                s.ajoutAffectation(saisie);
                affs.add(saisie);
                System.out.println("idActivite = "+saisie.getActivite().getIdActivite());
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Affectation de saisie ajoutée !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            AffectationConsultee consultation=new AffectationConsultee();
            
          /*  validation.setUtilisateur(rm);
            validation.setActivite(activite);
            validation.setPlateforme(plateforme);
            validation.setDateAff(new Date());
            validation.setType("Validation");*/
            
            for(String ch:nomsSelected)
            {
                OperationnelMarketing o=new OperationnelMarketing();
                o=s.getOperationnelParNom(ch.split(" ")[1], ch.split(" ")[0]);
                consultation.setAffectationConsultee(saisie);
                
                consultation.setOperateurMark(o);
               /* consultation.setUtilisateur(o);
                consultation.setActivite(activite);
                consultation.setPlateforme(plateforme);
                consultation.setDateAff(new Date());
                consultation.setType("Consultation");*/
                if(affCon.contains(consultation))
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Affectation de consultation existe déjà !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    s.ajoutAffCon(saisie,o);
                    affCon.add(consultation);
                    consultation=new AffectationConsultee();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Affectation de consultation ajoutée !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
           ua=new UtilisateurAgence();
           rm=new ResponsableMarketing();
           saisie=new Affectation();
           
            
        }
    }
   /* public void ajoutTranche()
    {
        if(borneMax.equals("") || borneMax==null || borneMin.equals("") || borneMin==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            if(Integer.parseInt(borneMin)>=Integer.parseInt(borneMax))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "La borne min est > à la borne max !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                t.setIntervalle(borneMin+"-"+borneMax);
                if(tranches.contains(t))
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Tranche d'âges existe déjà !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    tranches.add(t);
                    trs.add(t.getIntervalle());
                    s.ajoutTrancheAges(t);
                    t=new TrancheAges();
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Tranche d'âges ajoutée !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
            }
            
        }
        
    }*/
  /*  public void ajoutGouvernorat()
    {
        if(pays.equals("") || pays==null || ville.equals("") || ville==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }      
        else
        {
            g.setPays(pays);
            g.setVille(ville);
            if(gouvs.contains(g))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Gouvernorat existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                gouvs.add(g);
                s.ajoutGouvernorat(g);
                g=new Gouvernorat();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Gouvernorat ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }      
    }*/
    
    public void ajoutQuestion(){
        if(nomQuestion.equals("") || nomQuestion==null || typeQuestion.equals("") || typeQuestion==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            question.setNomQuestion(nomQuestion);
            question.setTypeQuestion(typeQuestion);
            if(questions.contains(question))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Question existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                questions.add(question);
                s.ajoutQuestion(question);
                question = new Question();
                nomQuestion =null;
                typeQuestion=null;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Question ajoutée !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                
            }
        }
        
    }
    
    public void getNomQuestions()
    {
        this.quest = quest;
    }
    
    public void ajoutReponse()
    {
        if( reponseCible==null || valeurReponse.equals("") || valeurReponse==null || ques==null || ques.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {   
           
            reponse.setValeurReponse(valeurReponse);
            reponse.setReponseCible(reponseCible);
            question =s.getQuestionParNom(ques);
            System.out.println(s.getQuestionParNom(ques).getNomQuestion());
            reponse.setQuestionReponse(question);  
            System.out.println(reponse.getValeurReponse());
            if(reponses.contains(reponse))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Réponse existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                reponses.add(reponse);
                System.out.println(question.getIdQuestion());
                s.ajoutReponse(reponse);
                reponse = new Reponse();
                question = new Question();
                reponseCible= null;
                valeurReponse = null;
                ques = null;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Réponse ajoutée !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            
            
        }
        
        
    }
    
    
    public void ajoutGroupement()
    {
       if( nomGroupement==null || nomGroupement.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       else
       {
            groupement.setNomGroupement(nomGroupement);
            if(groupements.contains(groupement))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Groupement existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                groupements.add(groupement);
                s.ajoutGroupement(groupement);
                nomGroupement = null;
                groupement = new Groupement();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Groupement ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
       }
    }
    
    public void ajoutQuestionnaire()
    {
      if( nomQuestionnaire==null || nomQuestionnaire.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       else
       {
            questionnaire.setNomQuestionnaire(nomQuestionnaire);
            if(questionnaires.contains(questionnaire))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Questionnaire existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                questionnaires.add(questionnaire);
                s.ajoutQuestionnaire(questionnaire);
                questionnaire = new Questionnaire();
                nomQuestionnaire = null;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Questionnaire ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
       }  
    }
    
    public void gererQuestionnaire()
    {
        if( qu==null || qu.equals("") || grou.equals("") || grou.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
       {    groupement = s.getGroupementParNom(grou);  System.out.println(groupement.getIdGroupement());
            questionnaire = s.getQuestionnaireParNom(qu);  System.out.println(questionnaire.getIdQuestionnaire());
            if(listqg.contains(qg))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Groupement existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                
                listqg.add(qg);
               /* qg.setGroupementQuestionnaire(groupement);
                qg.setQuestionnaire(questionnaire);
                s.ajoutQuestionnaireGroupement(qg);*/
               s.ajoutQG(questionnaire, groupement);
               quesGrou = s.getListGroupementParQues(questionnaire);
                questionnaire = new Questionnaire();
                groupement = new Groupement();
                qg = new QuestionnaireGroupement();
                qu = null;
                grou = null;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Groupement ajouté ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
       } 
    }
    
    public void consulterQuestionnaire()
    {
       if( qu==null || qu.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec", "Choisir un questionnaire !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       else
       {    questionnaire = s.getQuestionnaireParNom(qu);
           quesGrou = s.getListGroupementParQues(questionnaire);
       }
    }
    
    public void gererGroupement()
    {
        if( ques==null || ques.equals("") || grou.equals("") || grou.equals("") || cib == null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
       {    
            groupement = s.getGroupementParNom(grou);  System.out.println(groupement.getIdGroupement());
            question = s.getQuestionParNom(ques);  System.out.println(question.getIdQuestion());
            
            queg.setGroupement(groupement);
            queg.setQuestion(question);
            queg.setQuestionObligatoire(cib);
            
            if(ltqg.contains(queg))
            {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Questionn existe déjà !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
            else
            {
                ltqg.add(queg);
               
                s.ajoutQuesG(queg);
                queg = new QuestionGroupement();
                question = new Question();
                groupement = new Groupement();
                ques = null;
                grou = null;
                cib= null;
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Questionnaire ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
       }
        
    }
    
    public void consulterGroupement()
    {
        if( grou==null || grou.equals("") )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec", "Choisir un groupement !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
       else
       {    groupement = s.getGroupementParNom(grou);
           ltqg = s.getListQuestionsParGrou(groupement);
       }
    }
    
    public void ajoutConcurrent()
    {
        if(actC==null )
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Choisir une activité !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }      
        else if(concurrent.equals("") || concurrent==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            ac.setNom(concurrent);
            
            if(acs.contains(ac))
            { 
                activite=s.getActiviteParNom(actC);
                cpa.setActivite(activite);
                ac=s.getActiviteConcurrentParNom(concurrent);
                cpa.setActiviteConcurrent(ac);
                if(cpas.contains(cpa))
                {
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Concurrent existe déjà !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                }
                else
                {
                    
                    //cpa.setActiviteConcurrent(ac);
                    cpas.add(cpa);
                    s.ajoutConcurrentParActivite(cpa);
                    FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Concurrent par activité ajouté !");
                    FacesContext.getCurrentInstance().addMessage(null, msg);
                    cpa=new ConcurrentParActivite();
                    ac=new ActiviteConcurrent();
                    activite=new Activite();
                }                
            }
            else
            {
                s.ajoutActiviteConcurrent(ac);
                ActiviteConcurrent aa=new ActiviteConcurrent();
                aa=s.getActiviteConcurrentParNom(concurrent);
                acs.add(aa);
                //activite=s.getActiviteParNom(actC);
                Activite aaa=new Activite();
                aaa=s.getActiviteParNom(actC);
                cpa.setActivite(aaa);
                cpa.setActiviteConcurrent(aa);
                s.ajoutConcurrentParActivite(cpa);
                cpas.add(cpa);
                cpa=new ConcurrentParActivite();
                ac=new ActiviteConcurrent();
                activite=new Activite();
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Concurrent par activité ajouté !");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        }      
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

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public String getFil() {
        return fil;
    }

    public void setFil(String fil) {
        this.fil = fil;
    }

    public String getAct() {
        return act;
    }

    public void setAct(String act) {
        this.act = act;
    }

    public String getPlat() {
        return plat;
    }

    public void setPlat(String plat) {
        this.plat = plat;
    }

    public Map<String, String> getFils() {
        return fils;
    }

    public void setFils(Map<String, String> fils) {
        this.fils = fils;
    }

    public Map<String, String> getActs() {
        return acts;
    }

    public void setActs(Map<String, String> acts) {
        this.acts = acts;
    }

    public List<String> getPlats() {
        return plats;
    }

    public void setPlats(List<String> plats) {
        this.plats = plats;
    }

    public List<Plateforme> getReseauSocials() {
        return reseauSocials;
    }

    public void setReseauSocials(List<Plateforme> reseauSocials) {
        this.reseauSocials = reseauSocials;
    }

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }

    public Map<String, Map<String, String>> getAgenceAgents() {
        return agenceAgents;
    }

    public void setAgenceAgents(Map<String, Map<String, String>> agenceAgents) {
        this.agenceAgents = agenceAgents;
    }

    public String getAg() {
        return ag;
    }

    public void setAg(String ag) {
        this.ag = ag;
    }

    public String getAgt() {
        return agt;
    }

    public void setAgt(String agt) {
        this.agt = agt;
    }

    public Map<String, String> getAgs() {
        return ags;
    }

    public void setAgs(Map<String, String> ags) {
        this.ags = ags;
    }

    public Map<String, String> getAgts() {
        return agts;
    }

    public void setAgts(Map<String, String> agts) {
        this.agts = agts;
    }

    public List<Agence> getAgences() {
        return agences;
    }

    public void setAgences(List<Agence> agences) {
        this.agences = agences;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

    public List<UtilisateurAgence> getAgents() {
        return agents;
    }

    public void setAgents(List<UtilisateurAgence> agents) {
        this.agents = agents;
    }

    public UtilisateurAgence getUa() {
        return ua;
    }

    public void setUa(UtilisateurAgence ua) {
        this.ua = ua;
    }

    public String getFilR() {
        return filR;
    }

    public void setFilR(String filR) {
        this.filR = filR;
    }

    public Map<String, String> getRespos() {
        return respos;
    }

    public void setRespos(Map<String, String> respos) {
        this.respos = respos;
    }

    public List<ResponsableMarketing> getRms() {
        return rms;
    }

    public void setRms(List<ResponsableMarketing> rms) {
        this.rms = rms;
    }

    public ResponsableMarketing getRm() {
        return rm;
    }

    public void setRm(ResponsableMarketing rm) {
        this.rm = rm;
    }

    public String getRes() {
        return res;
    }

    public void setRes(String res) {
        this.res = res;
    }

    public Map<String, Map<String, String>> getFilialeRespos() {
        return filialeRespos;
    }

    public void setFilialeRespos(Map<String, Map<String, String>> filialeRespos) {
        this.filialeRespos = filialeRespos;
    }

    public Plateforme getPlateforme() {
        return plateforme;
    }

    public void setPlateforme(Plateforme plateforme) {
        this.plateforme = plateforme;
    }

    public List<Affectation> getAffs() {
        return affs;
    }

    public void setAffs(List<Affectation> affs) {
        this.affs = affs;
    }

  /*  public TrancheAges getT() {
        return t;
    }

    public void setT(TrancheAges t) {
        this.t = t;
    }
*/
    public String getBorneMin() {
        return borneMin;
    }

    public void setBorneMin(String borneMin) {
        this.borneMin = borneMin;
    }

    public String getBorneMax() {
        return borneMax;
    }

    public void setBorneMax(String borneMax) {
        this.borneMax = borneMax;
    }

/*    public List<TrancheAges> getTranches() {
        return tranches;
    }

    public void setTranches(List<TrancheAges> tranches) {
        this.tranches = tranches;
    }

    public Gouvernorat getG() {
        return g;
    }

    public void setG(Gouvernorat g) {
        this.g = g;
    }
*/
    public String getPays() {
        return pays;
    }

    public void setPays(String pays) {
        this.pays = pays;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }
/*
    public List<Gouvernorat> getGouvs() {
        return gouvs;
    }

    public void setGouvs(List<Gouvernorat> gouvs) {
        this.gouvs = gouvs;
    }
*/
    public String getActC() {
        return actC;
    }

    public void setActC(String actC) {
        this.actC = actC;
    }

    public ActiviteConcurrent getAc() {
        return ac;
    }

    public void setAc(ActiviteConcurrent ac) {
        this.ac = ac;
    }

    public ConcurrentParActivite getCpa() {
        return cpa;
    }

    public void setCpa(ConcurrentParActivite cpa) {
        this.cpa = cpa;
    }

    public List<ConcurrentParActivite> getCpas() {
        return cpas;
    }

    public void setCpas(List<ConcurrentParActivite> cpas) {
        this.cpas = cpas;
    }

    public String getConcurrent() {
        return concurrent;
    }

    public void setConcurrent(String concurrent) {
        this.concurrent = concurrent;
    }

    public List<ActiviteConcurrent> getAcs() {
        return acs;
    }

    public void setAcs(List<ActiviteConcurrent> acs) {
        this.acs = acs;
    }

    public List<OperationnelMarketing> getOps() {
        return ops;
    }

    public void setOps(List<OperationnelMarketing> ops) {
        this.ops = ops;
    }

    public List<String> getNomsOps() {
        return nomsOps;
    }

    public void setNomsOps(List<String> nomsOps) {
        this.nomsOps = nomsOps;
    }

    public String[] getNomsSelected() {
        return nomsSelected;
    }

    public void setNomsSelected(String[] nomsSelected) {
        this.nomsSelected = nomsSelected;
    }

    public String getAcc() {
        return acc;
    }

    public void setAcc(String acc) {
        this.acc = acc;
    }

    public List<String> getTrs() {
        return trs;
    }

    public String getTr() {
        return tr;
    }

    public void setTr(String tr) {
        this.tr = tr;
    }

    public void setTrs(List<String> trs) {
        this.trs = trs;
    }

    public List<SelectItem> getGroupAct() {
        return groupAct;
    }

    public void setGroupAct(List<SelectItem> groupAct) {
        this.groupAct = groupAct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public String getNomQuestion() {
        return nomQuestion;
    }

    public void setNomQuestion(String nomQuestion) {
        this.nomQuestion = nomQuestion;
    }

    public String getTypeQuestion() {
        return typeQuestion;
    }

    public void setTypeQuestion(String typeQuestion) {
        this.typeQuestion = typeQuestion;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Reponse getReponse() {
        return reponse;
    }

    public void setReponse(Reponse reponse) {
        this.reponse = reponse;
    }

    public List<Reponse> getReponses() {
        return reponses;
    }

    public void setReponses(List<Reponse> reponses) {
        this.reponses = reponses;
    }

    public Boolean getReponseCible() {
        return reponseCible;
    }

    public void setReponseCible(Boolean reponseCible) {
        this.reponseCible = reponseCible;
    }

    public String getValeurReponse() {
        return valeurReponse;
    }

    public void setValeurReponse(String valeurReponse) {
        this.valeurReponse = valeurReponse;
    }

    public Question getQuesRep() {
        return quesRep;
    }

    public void setQuesRep(Question quesRep) {
        this.quesRep = quesRep;
    }

    public Groupement getGroupement() {
        return groupement;
    }

    public void setGroupement(Groupement groupement) {
        this.groupement = groupement;
    }

    public List<Groupement> getGroupements() {
        return groupements;
    }

    public void setGroupements(List<Groupement> groupements) {
        this.groupements = groupements;
    }

    public String getNomGroupement() {
        return nomGroupement;
    }

    public void setNomGroupement(String nomGroupement) {
        this.nomGroupement = nomGroupement;
    }

    public String getQues() {
        return ques;
    }

    public void setQues(String ques) {
        this.ques = ques;
    }

    public Map<String, String> getQuest() {
        return quest;
    }

    public void setQuest(Map<String, String> quest) {
        this.quest = quest;
    }

    public Questionnaire getQuestionnaire() {
        return questionnaire;
    }

    public void setQuestionnaire(Questionnaire questionnaire) {
        this.questionnaire = questionnaire;
    }

    public String getNomQuestionnaire() {
        return nomQuestionnaire;
    }

    public void setNomQuestionnaire(String nomQuestionnaire) {
        this.nomQuestionnaire = nomQuestionnaire;
    }

    public List<Questionnaire> getQuestionnaires() {
        return questionnaires;
    }

    public void setQuestionnaires(List<Questionnaire> questionnaires) {
        this.questionnaires = questionnaires;
    }

    public Map<String, String> getRep() {
        return rep;
    }

    public void setRep(Map<String, String> rep) {
        this.rep = rep;
    }

    public String getReponseVal() {
        return reponseVal;
    }

    public void setReponseVal(String reponseVal) {
        this.reponseVal = reponseVal;
    }

    public Map<String, String> getGroup() {
        return group;
    }

    public void setGroup(Map<String, String> group) {
        this.group = group;
    }

    public String getGrou() {
        return grou;
    }

    public void setGrou(String grou) {
        this.grou = grou;
    }

    public Map<String, String> getQuestionna() {
        return questionna;
    }

    public void setQuestionna(Map<String, String> questionna) {
        this.questionna = questionna;
    }

    public String getQu() {
        return qu;
    }

    public void setQu(String qu) {
        this.qu = qu;
    }

    public QuestionnaireGroupement getQg() {
        return qg;
    }

    public void setQg(QuestionnaireGroupement qg) {
        this.qg = qg;
    }

    public List<QuestionnaireGroupement> getListqg() {
        return listqg;
    }

    public void setListqg(List<QuestionnaireGroupement> listqg) {
        this.listqg = listqg;
    }

    public List<QuestionnaireGroupement> getQuesGrou() {
        return quesGrou;
    }

    public void setQuesGrou(List<QuestionnaireGroupement> quesGrou) {
        this.quesGrou = quesGrou;
    }

    public QuestionGroupement getQueg() {
        return queg;
    }

    public void setQueg(QuestionGroupement queg) {
        this.queg = queg;
    }

    public List<QuestionGroupement> getLtqg() {
        return ltqg;
    }

    public void setLtqg(List<QuestionGroupement> ltqg) {
        this.ltqg = ltqg;
    }

    public Boolean getCib() {
        return cib;
    }

    public void setCib(Boolean cib) {
        this.cib = cib;
    }

    public List<AffectationConsultee> getAffCon() {
        return affCon;
    }

    public void setAffCon(List<AffectationConsultee> affCon) {
        this.affCon = affCon;
    }

    public AffectationConsultee getAfC() {
        return afC;
    }

    public void setAfC(AffectationConsultee afC) {
        this.afC = afC;
    }
    
    public void onChange() {
        reponses = s.getReponses();
        groupements = s.getGroupements();
        questionnaires = s.getQuestionnaires();
        listqg = s.getListQuestionnaireGroupement();
        ltqg = s.getListQuestionGroupement();
        affCon = s.getListAffCons();
    }
    
}
