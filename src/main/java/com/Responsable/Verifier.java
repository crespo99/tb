/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Responsable;

import com.entities.Affectation;
import com.entities.DetailsTache;
import com.entities.Groupement;
import com.entities.Question;
import com.entities.QuestionGroupement;
import com.entities.QuestionReponse;
import com.entities.Questionnaire;
import com.entities.QuestionnaireGroupement;
import com.entities.Reclamation;
import com.entities.Reponse;
import com.entities.Tache;
import com.entities.Utilisateur;
import com.session.Service;
import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@ManagedBean(name = "verifier")
@SessionScoped
public class Verifier implements Serializable{
    @EJB
    Service s;
    
    private String login;
    private String mdp;
    private Boolean skip;
    private String description;
    
    private Utilisateur u;
    
    private Map<String, String> valQues;
    private HashMap<String, String> beya;
    private Map<String, String> treeMap;
    private List<DetailsTache> listDet;
    
    private Map<String, String> map;
    
    private Tache tache;
    private DetailsTache det;
    private String valeur;
    private String[] valeurs;
    private String rep;
    private String repon;
    
    private Reclamation reclamation;
    private List<Reclamation> reclamations;
    
    //dépense
    private Double depenseRecrut;
    private Double depenseSponsor;
    private Double depenseCommunityManager;
    private double depenseTotal;
    private BigDecimal cout;
    
    
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
    
    public void goToVerif(Affectation af) throws IOException
    {   
        depenseCommunityManager =null;
        depenseRecrut = null;
        depenseSponsor = null;
        depenseTotal = 0;
        cout = null;
        
        affectation = af;
        reponse = new Reponse();
        reclamation = new Reclamation();
        repon = null;
        description = null;
        listGroup = new ArrayList<Groupement>();
        reclamations = new ArrayList<Reclamation>();
        listQuRep = new ArrayList<QuestionReponse>();
        listQuesGroup = new ArrayList<QuestionnaireGroupement>();
        listQuestions = new ArrayList<Question>();
        listQuestionGroup = new ArrayList<QuestionGroupement>();
        questionnaire = new Questionnaire();
        lqg = new ArrayList<QuestionGroupement>();
        listDet = new ArrayList<DetailsTache>();
        
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login = sess.getAttribute("login").toString();
        mdp = sess.getAttribute("mdp").toString();
        
        det = new DetailsTache();
        u = s.getUtilisateur(login, mdp);
        
        tache = s.getTacheParAff(affectation);
        System.out.println("tache id :"+tache.getIdTache());
        System.out.println("list tache "+tache.getListDetailsTache().size());
        listDet = s.getDetTache(tache);
        System.out.println("list details tache"+listDet.size());
                
        questionnaire = s.getQuestionnaireParAff(affectation);System.out.println(questionnaire.getNomQuestionnaire());
        listQuesGroup = s.getListGroupParQues(questionnaire);System.out.println(listQuesGroup.size());
        valeur = null;

        valQues = new TreeMap<>();
        treeMap = new TreeMap<>();
        map = new TreeMap<>();
        
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("verification.xhtml");
    }
    
    public void ajoutQues(QuestionGroupement quest)
    {
        lqg.add(quest);
        System.out.println(lqg.size());
    }
    
    public DetailsTache getValSaisie(QuestionGroupement qg)
    {
        for(DetailsTache d:listDet)
        {
            if(d.getQuesGrou().getIdQuesGroup() == qg.getIdQuesGroup())
            {
                return d;
            }
        }
        return null;
    }
    
    public List<String> getListReponse(QuestionGroupement q){
          List<String> ls = new ArrayList<String>();
        for (Reponse r:s.getReponsesParQues(q.getQuestion()))
          {
              ls.add(r.getValeurReponse());
          }
        return ls;
    }
    
    public void getReponseValSaisie()
    {
        det = s.getValSaisieReponse(repon);
    }
    
    public void reclamer(QuestionGroupement qg,DetailsTache d)
    {
        reclamation = new Reclamation();
        reclamation.setDateReclamation(new Date());
        reclamation.setDescription(description);
        reclamation.setTraitee(false);
        reclamation.setDetailsTache(d);
        reclamation.setSujet(qg.getGroupement().getNomGroupement().concat("-"+qg.getQuestion().getNomQuestion()));
       /// s.ajoutReclamation(reclamation);
        reclamations.add(reclamation);
        FacesMessage fm=new FacesMessage("Réclamation",reclamation.getSujet()+"\n"+reclamation.getDescription());
                fm.setSeverity(FacesMessage.SEVERITY_WARN);
                FacesContext.getCurrentInstance().addMessage(null, fm); 
        description = "";
        
    }
    
    
    
    public void somme()
    {
        if(depenseCommunityManager!=null && depenseRecrut !=null && depenseSponsor !=null)
        {
            if(depenseTotal!=(double) (depenseCommunityManager)+(double) (depenseRecrut)+(double) (depenseSponsor))
            {
                
                depenseTotal=0;
                depenseTotal+=(double) (depenseCommunityManager)+(double) (depenseRecrut)+(double) (depenseSponsor);
                cout=new BigDecimal(0);
                //BigDecimal valeur = null;
                double valeur = (double)(Integer.parseInt(s.getNbFan()))/depenseTotal;
                //cout=recrutementFans.getNbrFans()/depenseTotal;
                cout = (new BigDecimal(valeur)).setScale(3, BigDecimal.ROUND_UP);
            }
        }
   
    }
    
    public void save() throws IOException
    {
        for(Reclamation r:reclamations)
        {
            s.ajoutReclamation(r);
        }
        /*DetailsTache d = new DetailsTache();
        d.setTache(tache);
        d.setValeurSaisie(valeur);*/
        if(reclamations.isEmpty())
        {
            tache.setReclame(false);
        }
        else
        {
            tache.setReclame(true);
            
        }
        s.updateTache(tache);
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("ListeTaches.xhtml");
        
    }
    
    public Affectation getAffectation() {
        return affectation;
    }

    public void setAffectation(Affectation affectation) {
        this.affectation = affectation;
    }

    public Boolean getSkip() {
        return skip;
    }

    public void setSkip(Boolean skip) {
        this.skip = skip;
    }

    public Utilisateur getU() {
        return u;
    }

    public void setU(Utilisateur u) {
        this.u = u;
    }

    public Map<String, String> getValQues() {
        return valQues;
    }

    public void setValQues(Map<String, String> valQues) {
        this.valQues = valQues;
    }

    public Map<String, String> getTreeMap() {
        return treeMap;
    }

    public void setTreeMap(Map<String, String> treeMap) {
        this.treeMap = treeMap;
    }

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    public Tache getTache() {
        return tache;
    }

    public void setTache(Tache tache) {
        this.tache = tache;
    }

    public DetailsTache getDet() {
        return det;
    }

    public void setDet(DetailsTache det) {
        this.det = det;
    }

    public String getValeur() {
        return valeur;
    }

    public void setValeur(String valeur) {
        this.valeur = valeur;
    }

    public String getRep() {
        return rep;
    }

    public void setRep(String rep) {
        this.rep = rep;
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

    public List<QuestionGroupement> getLqg() {
        return lqg;
    }

    public void setLqg(List<QuestionGroupement> lqg) {
        this.lqg = lqg;
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

    public List<DetailsTache> getListDet() {
        return listDet;
    }

    public void setListDet(List<DetailsTache> listDet) {
        this.listDet = listDet;
    }

    public String getRepon() {
        return repon;
    }

    public void setRepon(String repon) {
        this.repon = repon;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getDepenseRecrut() {
        return depenseRecrut;
    }

    public void setDepenseRecrut(Double depenseRecrut) {
        this.depenseRecrut = depenseRecrut;
    }

    public Double getDepenseSponsor() {
        return depenseSponsor;
    }

    public void setDepenseSponsor(Double depenseSponsor) {
        this.depenseSponsor = depenseSponsor;
    }

    public Double getDepenseCommunityManager() {
        return depenseCommunityManager;
    }

    public void setDepenseCommunityManager(Double depenseCommunityManager) {
        this.depenseCommunityManager = depenseCommunityManager;
    }

    public double getDepenseTotal() {
        return depenseTotal;
    }

    public void setDepenseTotal(double depenseTotal) {
        this.depenseTotal = depenseTotal;
    }

    public BigDecimal getCout() {
        return cout;
    }

    public void setCout(BigDecimal cout) {
        this.cout = cout;
    }

    public List<Reclamation> getReclamations() {
        return reclamations;
    }

    public void setReclamations(List<Reclamation> reclamations) {
        this.reclamations = reclamations;
    }
    
    
}
