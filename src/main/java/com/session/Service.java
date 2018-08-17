/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;
import com.entities.Question;
import com.entities.Activite;
import com.entities.ActiviteConcurrent;
import com.entities.Administrateur;
import com.entities.Affectation;
import com.entities.AffectationConsultee;
import com.entities.Agence;
import com.entities.ConcurrentParActivite;
import com.entities.DetailsTache;
import com.entities.Filiale;
import com.entities.Groupement;
import com.entities.OperationnelMarketing;
import com.entities.Plateforme;
import com.entities.QuestionGroupement;
import com.entities.Questionnaire;
import com.entities.QuestionnaireGroupement;
import com.entities.Reclamation;
import com.entities.Reponse;
import com.entities.ResponsableMarketing;
import com.entities.Scrap;
import com.entities.Tache;
import com.entities.Utilisateur;
import com.entities.UtilisateurAgence;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Administrateur
 */
@Stateless
public class Service {
    @PersistenceContext(name = "digikeypfe")
    EntityManager em;
    
    public Utilisateur getUtilisateur(String login,String mdp){
        Query q=em.createQuery("select u from Utilisateur u where u.login = :ll and u.mdp = :mm");
        q.setParameter("ll", login);
        q.setParameter("mm", mdp);
        
        return (Utilisateur) q.getSingleResult();
    }
    
    public Boolean verifMail(String mail){
        Query q = em.createQuery("select u from Utilisateur u where u.email = :mm and u.statut = :ss", Utilisateur.class);
        q.setParameter("mm", mail);
        q.setParameter("ss", "Actif");
        List<Utilisateur> user = (List<Utilisateur>) q.getResultList();
        if(user.isEmpty()){
            return false;
        }
        
        return true;
    }
    
    public void modifierMotPass(String mail,String mp){
        Query q = em.createQuery("select u from Utilisateur u where u.email = :ss");
        q.setParameter("ss", mail);
        Utilisateur user = (Utilisateur) q.getSingleResult();
        user.setMdp(mp);
        em.merge(user);   
    }
    
    public void ajoutPlateforme(Plateforme p)
    {
        em.persist(p);
    }
    public void ajoutAdmin(Administrateur u)
    {
        em.persist(u);
    }
    
    public List<Filiale> getListFiliale(){
        Query q= em.createQuery("select a from Filiale a");
        return (List<Filiale>) q.getResultList();
    }
    
    public List<Activite> getListActivite(){
        Query q= em.createQuery("select a from Activite a");
        return (List<Activite>) q.getResultList();
    }
    
    public Filiale getFilialeParNom(String nom){
        Query q= em.createQuery("select a from Filiale a where a.nomFiliale like :us");
        q.setParameter("us", nom);
        return (Filiale) q.getSingleResult();
    }
    
    public void ajoutActivite(Activite activite){     
        //activite.setFiliale(filiale);
        em.persist(activite);
    }
    
    public List<Agence> getListAgence(){
        Query q= em.createQuery("select a from Agence a");
        return (List<Agence>) q.getResultList();
    }
    
    public void ajoutAgence(Agence agence){
        em.persist(agence);
    }
    
    public void ajoutFiliale(Filiale filiale) {     
        
        em.persist(filiale);
        
    }
    
    public List<UtilisateurAgence> getListAgents(){
        Query q= em.createQuery("select a from UtilisateurAgence a");
        return (List<UtilisateurAgence>) q.getResultList();
    }
    
    public List<ResponsableMarketing> getListResponsables(){
        Query q= em.createQuery("select a from ResponsableMarketing a");
        return (List<ResponsableMarketing>) q.getResultList();
    }
    
    public List<OperationnelMarketing> getListOperationnels(){
        Query q= em.createQuery("select a from OperationnelMarketing a");
        return (List<OperationnelMarketing>) q.getResultList();
    }
    
    public Agence getAgenceParNom(String nom){
        Query q= em.createQuery("select a from Agence a where a.nom like :us");
        q.setParameter("us", nom);
        return (Agence) q.getSingleResult();
    }
    
    public void ajoutAgent(UtilisateurAgence u)
    {
        em.persist(u);
    }
    
    public void ajoutRespMarketing(ResponsableMarketing resp,Filiale filiale){
        resp.setFiliale(filiale);
        em.persist(resp);
    }
    
    public void ajoutOpMarketing(OperationnelMarketing op){
        
        em.persist(op);
    }
    
    public List<Affectation> getListAffectationsConsultees(Utilisateur op){
            Query q = em.createQuery("select a from Affectation a where a.utilisateur.idUtilisateur = :user and a.idAffectation in (select t.affectation.idAffectation from TacheAffectation t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP))");
            q.setParameter("user", op.getIdUtilisateur());
            return( List<Affectation>) q.getResultList() ;        
    }
    
    public List<Affectation> getListAffectations(){
        Query q = em.createQuery("select a from Affectation a");
        return (List<Affectation>) q.getResultList();
    }
    
    public List<Activite> getListActivitesParFiliale(String nomFil)
    {
        Query q= em.createQuery("select a from Activite a where a.filiale.nomFiliale like :nomF");
        q.setParameter("nomF", nomFil);
        return (List<Activite>) q.getResultList();
    }
    
    public List<Plateforme> getListPlateformes(){
        Query q= em.createQuery("select p from Plateforme p ");
        return (List<Plateforme>) q.getResultList();
    }
    
    public List<UtilisateurAgence> getListAgentsParAgence(String nomAgence)
    {
        Query q= em.createQuery("select a from UtilisateurAgence a where a.agence.nom like :nomA");
        q.setParameter("nomA", nomAgence);
        return (List<UtilisateurAgence>) q.getResultList();
    }
    
    public List<ResponsableMarketing> getListResponsablesParFiliale(String nomF)
    {
        Query q= em.createQuery("select r from ResponsableMarketing r where r.filiale.nomFiliale like :nomF");
        q.setParameter("nomF", nomF);
        return (List<ResponsableMarketing>) q.getResultList();
    }
    
    public List<ActiviteConcurrent> getListActiviteConcurrent()
    {
        Query q= em.createQuery("select g from ActiviteConcurrent g");       
        return (List<ActiviteConcurrent>) q.getResultList();
    }
    
    public List<ConcurrentParActivite> getListConcurrentParActivite()
    {
        Query q= em.createQuery("select g from ConcurrentParActivite g");       
        return (List<ConcurrentParActivite>) q.getResultList();
    }
    
    public Activite getActiviteParNom(String nom){
        Query q= em.createQuery("select a from Activite a where a.nomActivite like :us");
        q.setParameter("us", nom);
        return (Activite) q.getSingleResult();
    }
    
    public UtilisateurAgence getAgentParNom(String nom,String prenom)
    {
        Query q= em.createQuery("select u from UtilisateurAgence u where u.nom like :us and u.prenom like :up");
        q.setParameter("us", nom);
        q.setParameter("up", prenom);
        return (UtilisateurAgence) q.getSingleResult();
    }
    
    public ResponsableMarketing getResponsableParNom(String nom,String prenom)
    {
        Query q= em.createQuery("select r from ResponsableMarketing r where r.nom like :us and r.prenom like :up");
        q.setParameter("us", nom);
        q.setParameter("up", prenom);
        return (ResponsableMarketing) q.getSingleResult();
    }
    
    public Plateforme getPlateformeParNom(String nom)
    {
        Query q= em.createQuery("select p from Plateforme p where p.nomPlateforme like :us");
        q.setParameter("us", nom);
        return (Plateforme) q.getSingleResult();
    }
    
    public void ajoutAffectation(Affectation af)
    {
       /* Affectation af = new Affectation();
        af.setActivite(a);
        af.setAgent(ag);
        af.setPlateforme(p);
        af.setResponsable(rm);
        af.setQuestionnaireAffectation(q);
        af.setDateAffectation(new Date());
        af.setUrlPlateforme(urlPlateforme);*/
        em.persist(af);
        em.flush();
    }
    
    public OperationnelMarketing getOperationnelParNom(String nom,String prenom)
    {
        Query q= em.createQuery("select u from OperationnelMarketing u where u.nom like :us and u.prenom like :up");
        q.setParameter("us", nom);
        q.setParameter("up", prenom);
        return (OperationnelMarketing) q.getSingleResult();
    }
    
    public void ajoutConcurrentParActivite(ConcurrentParActivite con){
        con.setIdActivite(con.getActivite().getIdActivite());
        con.setIdActiviteConcurrent(con.getActiviteConcurrent().getIdActiviteConcurrent());
        em.persist(con);
    }
    
    public ActiviteConcurrent getActiviteConcurrentParNom(String nom){
        Query q= em.createQuery("select a from ActiviteConcurrent a where a.nom like :us");
        q.setParameter("us", nom);
        return (ActiviteConcurrent) q.getSingleResult();
    }
    
    public void ajoutActiviteConcurrent(ActiviteConcurrent act){
        em.persist(act);
    }

    public List<Affectation> getListTacheParUtilisateurSaisie(Utilisateur op){
            Query q = em.createQuery("select a from Affectation a where a.utilisateur.idUtilisateur = :user except select t.affectation from TacheAffectation t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP)");
            q.setParameter("user", op.getIdUtilisateur());
            
                List<Affectation>   listTaches =( List<Affectation>) q.getResultList() ;
        return listTaches;
        
    }
    
    public List<Question> getQuestions(){
        Query q = em.createQuery("select q from Question q");
        return (List<Question>) q.getResultList();
    }
    
    public void ajoutQuestion(Question q){
        em.persist(q);
    }
    
    public void ajoutQuestionnaire(Questionnaire quest){
        em.persist(quest);
    }
    
    public List<Reponse> getReponses()
    {
        Query q = em.createQuery("select r from Reponse r");
        return (List<Reponse>) q.getResultList();
    }
    
    public void ajoutReponse(Reponse rep)
    {   
        em.persist(rep);
        em.flush();
    }
    
    public List<Groupement> getGroupements()
    {
        Query q = em.createQuery("select g from Groupement g");
        return (List<Groupement>)  q.getResultList();
    }
    
    public void ajoutGroupement(Groupement gr)
    {
        em.persist(gr);
    }
    
    public Question getQuestionParNom(String nom)
    {
        Query q = em.createQuery("select q from Question q where q.nomQuestion = :qq");
        q.setParameter("qq", nom);
        return (Question) q.getSingleResult();
    }
    
    public List<Questionnaire> getQuestionnaires()
    {
        Query q = em.createQuery("select q from Questionnaire q");
        return (List<Questionnaire>) q.getResultList();
                
    }
    
    public Groupement getGroupementParNom(String nom)
    {
        Query q = em.createQuery("select g from Groupement g where g.nomGroupement = :gg");
        q.setParameter("gg",nom);
        return (Groupement) q.getSingleResult();
    }
    
    public Questionnaire getQuestionnaireParNom(String nom)
    {
        Query q = em.createQuery("select g from Questionnaire g where g.nomQuestionnaire = :gg");
        q.setParameter("gg",nom);
        return (Questionnaire) q.getSingleResult();
    }
    
    public List<QuestionnaireGroupement> getListQuestionnaireGroupement()
    {
        Query q = em.createQuery("select q from QuestionGroupement q");
        return (List<QuestionnaireGroupement>) q.getResultList();
    }
    
    public void ajoutQuestionnaireGroupement(QuestionnaireGroupement qg)
    {
        em.persist(qg);
        em.flush();
    }
    
    public void ajoutQG(Questionnaire q ,Groupement g)
    {
        QuestionnaireGroupement qg = new QuestionnaireGroupement();
        qg.setGroupementQuestionnaire(g);
        qg.setIdGroupement(g.getIdGroupement());
        qg.setQuestionnaire(q);
        qg.setIdQuestionnaire(q.getIdQuestionnaire());
        em.persist(qg);
        em.flush();
    }
    
    public void ajoutQuesG(QuestionGroupement qg)
    {
        em.persist(qg);
       
    }
    
    public List<QuestionnaireGroupement> getListGroupementParQues(Questionnaire q)
    {
        Query c = em.createQuery("select q from QuestionnaireGroupement q where q.questionnaire.idQuestionnaire = :ii");
        c.setParameter("ii", q.getIdQuestionnaire());
        return (List<QuestionnaireGroupement>) c.getResultList();
    } 
    
    public List<QuestionGroupement> getListQuestionsParGrou(Groupement g)
    {
        Query q = em.createQuery("select q from QuestionGroupement q where q.groupement.idGroupement = :tt");
        q.setParameter("tt", g.getIdGroupement());
        return (List<QuestionGroupement>) q.getResultList();
    }
    
    
    
            public List<QuestionGroupement> getListQuestionGroupement()
    {
        Query q = em.createQuery("select q from QuestionGroupement q ");
        
        return (List<QuestionGroupement>) q.getResultList();
    }
 
    public List<AffectationConsultee> getListAffCons()
    {
        Query q = em.createQuery("select a from AffectationConsultee a");
        return (List<AffectationConsultee>) q.getResultList();
    }
    
   public void ajoutAffCon(Affectation af,OperationnelMarketing op)
   {
       AffectationConsultee afc = new AffectationConsultee();
       afc.setAffectationConsultee(af);
       afc.setIdAffectation(af.getIdAffectation());
       afc.setOperateurMark(op);
       afc.setIdOperMark(op.getIdUtilisateur());
       em.persist(afc);
       em.flush();
   }
   public Questionnaire getQuestionnaireParAff(Affectation af)
   {
       Query q = em.createQuery("select a.questionnaireAffectation from Affectation a where a.idAffectation = :aa");
       q.setParameter("aa", af.getIdAffectation());
       return (Questionnaire) q.getSingleResult();
   }
   
   public List<QuestionnaireGroupement> getListGroupParQues(Questionnaire ques)
   {
       Query q = em.createQuery("select q from QuestionnaireGroupement q where q.idQuestionnaire = :qq ");
       q.setParameter("qq", ques.getIdQuestionnaire());
       return (List<QuestionnaireGroupement>) q.getResultList();
   }
   
   public Affectation getAff(Utilisateur u)
   {
       Query q = em.createQuery("select a from Affectation a where a.agent.idUtilisateur = :uu");
       q.setParameter("uu", u.getIdUtilisateur());
       return (Affectation) q.getSingleResult();
   }
   
   public void ajoutTache(Tache t)
   {
       em.persist(t);
   }
   
   public void ajoutDetailTache(DetailsTache d)
   {
       em.persist(d);
   }
   
   public List<Reponse> getReponsesParQues(Question a)
   {
       Query q = em.createQuery("select r from Reponse r where r.questionReponse.idQuestion = :dd");
       q.setParameter("dd", a.getIdQuestion());
       return (List<Reponse>) q.getResultList();
   }
   public Question getQuestionParId(char c)
   {
       int id = (int)Character.getNumericValue(c);
       return (Question) em.find(Question.class, id);
       
   }
   
   public Groupement getGroupementParId(char c)
   {
       int id = (int)Character.getNumericValue(c);
       return (Groupement) em.find(Groupement.class, id);
       
   }
   
   public List<Affectation> getTacheNnSaisieAgent(Utilisateur u )
   {
       System.out.println("debut service ");
       Query q = em.createQuery("select a from Affectation a where a.agent.idUtilisateur = :user except select t.affectationTache from Tache t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) ");
       System.out.println("excution service:");
       q.setParameter("user", u.getIdUtilisateur());System.out.println("valeur de puis service "+q.getResultList().size());
       return (List<Affectation>) q.getResultList();
       
   }
   
   public List<Affectation> getTacheNnSaisieResp(Utilisateur u )
   {
       System.out.println("debut service ");
       Query q = em.createQuery("select t.affectationTache from Tache t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and t.affectationTache.responsable.idUtilisateur = :user and t.corrigee is NULL and t.reclame is NULL");
       System.out.println("excution service:");
       q.setParameter("user", u.getIdUtilisateur());System.out.println("valeur de puis service "+q.getResultList().size());
       
       return (List<Affectation>) q.getResultList();
       
   }
   
   public List<Affectation> getTacheNnSaisie()
   {
       System.out.println("debut service ");
       Query q = em.createQuery("select t.affectationTache from Tache t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and t.etat = :ss");
       q.setParameter("ss", "En cours de saisie");
       System.out.println("excution service:");
       System.out.println("valeur de puis service "+q.getResultList().size());
       return (List<Affectation>) q.getResultList();
       
   }
   
   public List<Affectation> getTacheNnValid()
   {
       System.out.println("debut service ");
       Query q = em.createQuery("select t.affectationTache from Tache t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and t.reclame = :nn and t.corrigee = :nn");
       q.setParameter("nn", "NULL");
       System.out.println("excution service:");
       System.out.println("valeur de puis service "+q.getResultList().size());
       return (List<Affectation>) q.getResultList();
       
   }
   
   public List<Reclamation> getListRec()
   {
       Query q = em.createQuery("select r from Reclamation r where FUNCTION('MONTH',r.dateReclamation)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',r.dateReclamation)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and r.traitee = false");
       return (List<Reclamation>) q.getResultList();
   }
   
   public List<Reclamation> getListRecParAgent(Utilisateur u)
   {
       Query q = em.createQuery("select r from Reclamation r where FUNCTION('MONTH',r.dateReclamation)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',r.dateReclamation)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and r.detailsTache.tache.affectationTache.agent.idUtilisateur = :uu");
       q.setParameter("uu", u.getIdUtilisateur());
       return (List<Reclamation>) q.getResultList();
   }
   
   public Tache getTacheParAff(Affectation af)
   {
       Query q = em.createQuery("select t from Tache t where FUNCTION('MONTH',t.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',t.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and t.affectationTache.idAffectation =:ff");
       q.setParameter("ff", af.getIdAffectation());
       return(Tache) q.getSingleResult();
   }
   
   public List<DetailsTache> getDetTache(Tache ta)
   {
       Query q = em.createQuery("select d from DetailsTache d where d.tache.idTache = :mm");
       q.setParameter("mm", ta.getIdTache());
       System.out.println("resultat service :"+q.getResultList().size());
       return(List<DetailsTache>) q.getResultList();
   }
   
   public DetailsTache getValSaisieReponse(String nom)
   {
       Query q = em.createQuery("select d from DetailsTache d where FUNCTION('MONTH',d.tache.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',d.tache.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP) and d.valeurReponse = :vr");
       q.setParameter("vr", nom);
       return (DetailsTache) q.getSingleResult();
   }
   
   public void ajoutReclamation(Reclamation rec)
   {
       em.persist(rec);
   }

   public void updateReclamation(Reclamation rec)
   {
       em.merge(rec);
       System.out.println("mise à jour réclamation");
       em.flush();
   }
   
   public List<Reclamation> getListReclamationsParUtilisateur(Utilisateur u){
       Query q = em.createQuery("select r from Reclamation r where r.detailsTache.tache.affectationTache.agent.idUtilisateur = :user and r.traitee = false");
       q.setParameter("user", u.getIdUtilisateur());
       System.out.println("from service get reclamation  :"+q.getResultList().size());
       return (List<Reclamation>) q.getResultList();
   }
   
   public QuestionGroupement getQuesGroupe(Question a, Groupement g)
   {
       Query q = em.createQuery("select q from QuestionGroupement q where q.groupement.idGroupement = :gr  and q.question.idQuestion = :ff");
       q.setParameter("gr", g.getIdGroupement());
       q.setParameter("ff", a.getIdQuestion());
       return (QuestionGroupement) q.getSingleResult();
   }
   
   public String getNbFan()
   {
       Query q = em.createQuery("select d.valeurSaisie from DetailsTache d where d.quesGrou.question.nomQuestion = :nn and FUNCTION('MONTH',d.tache.dateTache)=FUNCTION('MONTH',CURRENT_TIMESTAMP)  and FUNCTION('YEAR',d.tache.dateTache)=FUNCTION('YEAR',CURRENT_TIMESTAMP)");
       q.setParameter("nn", "nombre des fans");
       return (String) q.getSingleResult();
   }
   
   public void updateTache(Tache ta)
   {
       em.merge(ta);
       System.out.println("mise à jour tâche");
       em.flush();
   }
   
   public void updateDetailTache(DetailsTache d)
   {
       em.merge(d);
       System.out.println("mise à jour detail tâche");
       em.flush();
   }
   
   public List<Reclamation> getListRecParTacheNonTraitee(Tache ta)
   {
       Query q = em.createQuery("select r from Reclamation r where r.detailsTache.tache.idTache = :ss and r.traitee = false");
       q.setParameter("ss", ta.getIdTache());
       System.out.println("liste reclamation par tache  :  "+q.getResultList().size());
       return (List<Reclamation>) q.getResultList();
   }
   
   public List<Affectation> getAffParPlat(String plat)
   {
       Query q = em.createQuery("select a from Affectation a where a.plateforme.nomPlateforme = :pp");
       q.setParameter("pp", plat);
       return (List<Affectation>) q.getResultList();
   }
   
   public Integer getIdLastScrap()
   {
       Query q = em.createQuery("select MAX(s.idScrap) from Scrap s");
       return (Integer) q.getSingleResult();
   }
   
   public Scrap getLastScrap(Integer id)
   {
       return (Scrap) em.find(Scrap.class, id);
   }
   
   public void updateScrap(Scrap scr)
   {
       em.merge(scr);
       em.flush();
   }
   
   public void updateFiliale(Filiale fil)
   {
       em.merge(fil);
       em.flush();
   }
   
   public void updateActivite(Activite act)
   {
       em.merge(act);
       em.flush();
   }
   
   public void updateAgence(Agence ag)
   {
       em.merge(ag);
       em.flush();
   }
   
   public void updateAgent(UtilisateurAgence ag)
   {
       em.merge(ag);
       em.flush();
   }
   
   public void updateResponsable(ResponsableMarketing ag)
   {
       em.merge(ag);
       em.flush();
   }
   
   public void updateOperationnel(OperationnelMarketing ag)
   {
       em.merge(ag);
       em.flush();
   }
}
