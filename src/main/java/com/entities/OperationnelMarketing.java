/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.util.List;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("Operationnel Marketing")
public class OperationnelMarketing extends Utilisateur {
    private static final long serialVersionUID = 1L;
    

    public OperationnelMarketing() {
    }
   
    @OneToMany(fetch = FetchType.LAZY,mappedBy = "operateurMark")
    private List<AffectationConsultee> ListAffectations;

    public List<AffectationConsultee> getListAffectations() {
        return ListAffectations;
    }

    public void setListAffectations(List<AffectationConsultee> ListAffectations) {
        this.ListAffectations = ListAffectations;
    }
    
    
}
