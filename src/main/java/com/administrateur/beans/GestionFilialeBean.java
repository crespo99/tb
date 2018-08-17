/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.administrateur.beans;

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
public class GestionFilialeBean implements Serializable{
    
    private List<Filiale>filiales;
    private Filiale filiale;
    private Map<String,Map<String,String>> data = new HashMap<String, Map<String,String>>();
    private Map<String,String>pays;
    private Map<String,String>villes;
    private String pay;
    private String num;
    @EJB
    private Service s;
    public GestionFilialeBean() {
    }
    @PostConstruct
    public void init()
    {
        filiales=new ArrayList<Filiale>();
        filiales=s.getListFiliale();
        filiale=new Filiale();
        pays=new HashMap<String, String>();
        villes=new HashMap<String, String>();
        pays.put("Tunisie", "Tunisie");
        pays.put("Maroc", "Maroc");
        pays.put("Algérie", "Algérie");
        pays.put("Libye", "Libye");
        pays.put("France", "France");
        pays.put("Chine", "Chine");
        pays.put("Sénégal", "Sénégal");
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
    public void ajouter()
    {
        if("".equals(filiale.getAdresse()) || "".equals(filiale.getEmail()) || filiale.getNomFiliale()=="" ||filiale.getTelephone()=="" ||filiale.getVille()=="" || filiale.getVille()==null)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Completez votre saisie !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(filiale.getTelephone().length()<8)
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Mettez le téléphone sous format international !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else if(!validerEmail(filiale.getEmail()))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,"Echec d'enregistrement", "Vérifiez l'email !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        else if(s.getListFiliale().contains(filiale))
        {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN,"Echec d'enregistrement", "Filiale existe déjà !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        else
        {
            filiale.setPays(pay);
            filiale.setTelephone(num+filiale.getTelephone());
            filiales.add(filiale);
            s.ajoutFiliale(filiale);
            
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Succès", "Filiale ajouté !");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            filiale=new Filiale();
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
    public List<Filiale> getFiliales() {
        return filiales;
    }
    
    public void modifierFiliale(Filiale fil)
    {
        s.updateFiliale(fil);
    }
    
    public void setFiliales(List<Filiale> filiales) {
        this.filiales = filiales;
    }

    public Filiale getFiliale() {
        return filiale;
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

    public Service getS() {
        return s;
    }

    public void setS(Service s) {
        this.s = s;
    }

    public Map<String, Map<String, String>> getData() {
        return data;
    }

    public void setData(Map<String, Map<String, String>> data) {
        this.data = data;
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
    
}
