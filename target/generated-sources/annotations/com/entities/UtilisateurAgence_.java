package com.entities;

import com.entities.Affectation;
import com.entities.Agence;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(UtilisateurAgence.class)
public class UtilisateurAgence_ extends Utilisateur_ {

    public static volatile SingularAttribute<UtilisateurAgence, Agence> agence;
    public static volatile ListAttribute<UtilisateurAgence, Affectation> listAffagent;

}