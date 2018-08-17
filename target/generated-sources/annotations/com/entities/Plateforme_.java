package com.entities;

import com.entities.Affectation;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Plateforme.class)
public class Plateforme_ { 

    public static volatile SingularAttribute<Plateforme, Integer> idPlateforme;
    public static volatile SingularAttribute<Plateforme, String> nomPlateforme;
    public static volatile ListAttribute<Plateforme, Affectation> listAffplat;

}