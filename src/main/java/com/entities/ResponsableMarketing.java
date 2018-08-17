/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.*;

@Entity
@DiscriminatorValue("Responsable Filiale")


public class ResponsableMarketing extends Utilisateur {
    private static final long serialVersionUID = 1L;
    public ResponsableMarketing()
    {

    }
      
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.REMOVE)
    @JoinColumn(name = "idFiliale", referencedColumnName = "idFiliale", insertable = true, updatable = true)
    private  Filiale filiale;
    
    public Filiale getFiliale() {
        return filiale;
    }
    
    public void setFiliale(Filiale filiale) {
        this.filiale = filiale;
    } 
    
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "responsable")
    private List<Affectation> listAffresp;

    public List<Affectation> getListAffresp() {
        return listAffresp;
    }

    public void setListAffresp(List<Affectation> listAffresp) {
        this.listAffresp = listAffresp;
    }

    
    
    
}
