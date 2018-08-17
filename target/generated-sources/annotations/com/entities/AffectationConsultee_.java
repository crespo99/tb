package com.entities;

import com.entities.Affectation;
import com.entities.OperationnelMarketing;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(AffectationConsultee.class)
public class AffectationConsultee_ { 

    public static volatile SingularAttribute<AffectationConsultee, Integer> idOperMark;
    public static volatile SingularAttribute<AffectationConsultee, Affectation> affectationConsultee;
    public static volatile SingularAttribute<AffectationConsultee, OperationnelMarketing> operateurMark;
    public static volatile SingularAttribute<AffectationConsultee, Integer> idAffectation;

}