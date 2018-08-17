/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Filiale")
public class Filiale implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idFiliale")
    private Integer idFiliale;

    @Column(name = "nomFiliale")
    private String nomFiliale;
    
    @Column(name = "pays")
    private String pays;
    @Column(name = "ville")
    private String ville;
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "telephone")
    private String telephone;
    @Column(name = "email")
    private String email;

    public Filiale(){}

    public Filiale( String nomFiliale, String pays, String ville, String adresse, String telephone, String email) {
      
        this.nomFiliale = nomFiliale;
        this.pays = pays;
        this.ville = ville;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
    }

    public String getPays() {
        return pays;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
    
    public Integer getIdFiliale() {
        return idFiliale;
    }

    public void setIdFiliale(Integer idFiliale) {
        this.idFiliale = idFiliale;
    }

    public String getNomFiliale() {
        return nomFiliale;
    }

    public void setNomFiliale(String nomFiliale) {
        this.nomFiliale = nomFiliale;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    @OneToMany(mappedBy = "filiale")
    private List<ResponsableMarketing> ListUtilisateurPGH;
    
    public List<ResponsableMarketing> getListUtilisateurPGH() {
        return ListUtilisateurPGH;
    }

    public void setListUtilisateurPGH(List<ResponsableMarketing> ListUtilisateurPGH) {
        this.ListUtilisateurPGH = ListUtilisateurPGH;
    }
    
    @OneToMany(mappedBy="filiale")
    private List<Activite> ListActivite;

    public List<Activite> getListActivite() {
        return ListActivite;
    }

    public void setListActivite(List<Activite> ListActivite) {
        this.ListActivite = ListActivite;
    }
    
    @Override
    public String toString() {
        return "Filiale{" + "idFiliale=" + idFiliale + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Filiale))
            return false;
        Filiale f=(Filiale)obj;
        return (f.getNomFiliale().equals(this.getNomFiliale()));
    }
  
}
