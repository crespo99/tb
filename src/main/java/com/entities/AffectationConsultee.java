/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Administrateur
 */
@Entity
@IdClass(AffectationConsulteeID.class)
@Table(name = "AffectationConsultee")
public class AffectationConsultee implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "idAffectation")
    private Integer idAffectation;

    @Id
    @Column(name = "idOperMark")
    private Integer idOperMark;

    public AffectationConsultee() {
    }

    public Integer getIdAffectation() {
        return idAffectation;
    }

    public void setIdAffectation(Integer idAffectation) {
        this.idAffectation = idAffectation;
    }

    public Integer getIdOperMark() {
        return idOperMark;
    }

    public void setIdOperMark(Integer idOperMark) {
        this.idOperMark = idOperMark;
    }

    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idOperMark",referencedColumnName = "idUtilisateur",insertable = false,updatable = false)
    private OperationnelMarketing operateurMark;

    public OperationnelMarketing getOperateurMark() {
        return operateurMark;
    }

    public void setOperateurMark(OperationnelMarketing operateurMark) {
        this.operateurMark = operateurMark;
    }
    
    @ManyToOne(fetch = FetchType.EAGER,cascade = CascadeType.REMOVE)
    @JoinColumn(name = "idAffectation",referencedColumnName = "idAffectation",insertable = false,updatable = false)
    private Affectation affectationConsultee;

    public Affectation getAffectationConsultee() {
        return affectationConsultee;
    }

    public void setAffectationConsultee(Affectation affectationConsultee) {
        this.affectationConsultee = affectationConsultee;
    }
    
    
    
}
