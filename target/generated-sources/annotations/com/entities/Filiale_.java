package com.entities;

import com.entities.Activite;
import com.entities.ResponsableMarketing;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Filiale.class)
public class Filiale_ { 

    public static volatile SingularAttribute<Filiale, String> nomFiliale;
    public static volatile SingularAttribute<Filiale, String> ville;
    public static volatile SingularAttribute<Filiale, Integer> idFiliale;
    public static volatile ListAttribute<Filiale, ResponsableMarketing> ListUtilisateurPGH;
    public static volatile SingularAttribute<Filiale, String> adresse;
    public static volatile ListAttribute<Filiale, Activite> ListActivite;
    public static volatile SingularAttribute<Filiale, String> telephone;
    public static volatile SingularAttribute<Filiale, String> email;
    public static volatile SingularAttribute<Filiale, String> pays;

}