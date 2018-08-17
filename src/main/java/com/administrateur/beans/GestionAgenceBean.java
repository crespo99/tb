/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administrateur.beans;

import com.entities.Activite;
import com.entities.Agence;
import com.entities.Filiale;
import com.session.Service;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class GestionAgenceBean implements Serializable{
    
    
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private Map<String,String>pays;
    private Map<String,String>villes;
    private String pay;
    private String num;
    @EJB
    private Service s;
    private List<Agence>agences;
    private Agence agence;
    public GestionAgenceBean() {
    }
    @PostConstruct
    public void init()
    {
        agence=new Agence();
        agences=new ArrayList<Agence>();
        agences=s.getListAgence();
        pays=new HashMap<String, String>();
        villes=new HashMap<String, String>();
        pays.put("Tunisie", "Tunisie");
        /*pays.put("Maroc", "Maroc");
        pays.put("Algérie", "Algérie");
        pays.put("Libye", "Libye");
        pays.put("France", "France");
        pays.put("Chine", "Chine");
        pays.put("Sénégal", "Sénégal");*/
        Map<String,String> map = new HashMap<String, String>();
        map.put("Ariana", "Ariana");
        map.put("Béja", "Béja");
        map.put("Ben Arous", "Ben Arous");
        map.put("Bizerte", "Bizerte");
        map.put("Gabès", "Gabès");
        map.put("Gafsa", "Gafsa");
        map.put("Jendouba", "Jendouba");
        map.put("Kairouan", "Kairouan");
        map.put("Kasserine", "Kasserine");
        map.put("Kébili", "Kébili");
        map.put("Kef", "Kef");
        map.put("Mahdia", "Mahdia");
        map.put("Manouba", "Manouba");
        map.put("Médenine", "Médenine");
        map.put("Monastir", "Monastir");
        map.put("Nabeul", "Nabeul");
        map.put("Sfax", "Sfax");
        map.put("Sidi Bouzid", "Sidi Bouzid");
        map.put("Siliana", "Siliana");
        map.put("Sousse", "Sousse");
        map.put("Tataouine", "Tataouine");
        map.put("Tozeur", "Tozeur");
        map.put("Tunis", "Tunis");
        map.put("Zaghouan", "Zaghouan"); 
        data.put("Tunisie", map);
        num=null;
    }
    public void onPaysChange() {
        if(pay !=null && !pay.equals(""))
        {
            villes = data.get(pay);
            if(pay.equals("Tunisie"))
            {
                num="+216";
            }
            else if(pay.equals("Maroc"))
            {
                num="+212";
            }
            else if(pay.equals("Algérie"))
            {
                num="+213";
            }
            else if(pay.equals("Libye"))
            {
                num="+218";
            }
            else if(pay.equals("France"))
            {
                num="+33";
            }
            else if(pay.equals("Chine"))
            {
                num="+86";
            }
            else if(pay.equals("Sénégal"))
            {
                num="+221";
            }
        }    
        else
        {
            villes = new HashMap<String, String>();
            num=null;
        }        
    }
    public  boolean validerEmail(String email){
        Pattern p = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,4}$");
        Matcher m = p.matcher(email.toUpperCase());
        return m.matches();
    }
    public  boolean validerTel(String tel){
        Pattern p = Pattern.compile("\\+[0-9][0-9]([0-9]){8,10}$");
        Matcher m = p.matcher(tel);
        return m.matches();
    }
    public void ajouter()
    {
        if("".equals(agence.getAdresse()) || "".equals(agence.getEmail()) || agence.getNom()=="" ||agence.getTelephone()=="" ||agence.getVille()=="" ||agence.getVille()==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(agence.getTelephone().length()<8)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Mettez le téléphone sous format international !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(!validerEmail(agence.getEmail()))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Vérifiez l'email !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(agences.contains(agence))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Agence existe déjà !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            agence.setPays(pay);
            agence.setTelephone(num+agence.getTelephone());
            s.ajoutAgence(agence);
            agences.add(agence);
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Agence ajouté !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            agence=new Agence();
            
        }
    }
    
    public void modifierAgence(Agence ag)
    {   
        
        s.updateAgence(ag);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Succès", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
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

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
    }

    public Map<String, String> getPays() {
        return pays;
    }

    public void setPays(Map<String, String> pays) {
        this.pays = pays;
    }

    public Map<String, String> getVilles() {
        return villes;
    }

    public void setVilles(Map<String, String> villes) {
        this.villes = villes;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }
    
}
