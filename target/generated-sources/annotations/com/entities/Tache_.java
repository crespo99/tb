package com.entities;

import com.entities.Affectation;
import com.entities.DetailsTache;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Tache.class)
public class Tache_ { 

    public static volatile ListAttribute<Tache, DetailsTache> listDetailsTache;
    public static volatile SingularAttribute<Tache, Boolean> corrigee;
    public static volatile SingularAttribute<Tache, Date> dateTache;
    public static volatile SingularAttribute<Tache, Integer> idTache;
    public static volatile SingularAttribute<Tache, Boolean> reclame;
    public static volatile SingularAttribute<Tache, String> etat;
    public static volatile SingularAttribute<Tache, Affectation> affectationTache;

}