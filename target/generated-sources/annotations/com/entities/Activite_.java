package com.entities;

import com.entities.Affectation;
import com.entities.ConcurrentParActivite;
import com.entities.Filiale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Activite.class)
public class Activite_ { 

    public static volatile ListAttribute<Activite, ConcurrentParActivite> ListConcurrent;
    public static volatile ListAttribute<Activite, Affectation> listAffact;
    public static volatile SingularAttribute<Activite, String> nomActivite;
    public static volatile SingularAttribute<Activite, Filiale> filiale;
    public static volatile SingularAttribute<Activite, Integer> idActivite;

}