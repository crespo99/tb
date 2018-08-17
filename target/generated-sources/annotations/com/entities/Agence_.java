package com.entities;

import com.entities.UtilisateurAgence;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Agence.class)
public class Agence_ { 

    public static volatile SingularAttribute<Agence, String> ville;
    public static volatile SingularAttribute<Agence, String> adresse;
    public static volatile ListAttribute<Agence, UtilisateurAgence> Agents;
    public static volatile SingularAttribute<Agence, String> telephone;
    public static volatile SingularAttribute<Agence, Integer> idAgence;
    public static volatile SingularAttribute<Agence, String> nom;
    public static volatile SingularAttribute<Agence, String> email;
    public static volatile SingularAttribute<Agence, String> pays;

}