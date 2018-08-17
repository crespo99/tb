package com.entities;

import com.entities.DetailsTache;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Reclamation.class)
public class Reclamation_ { 

    public static volatile SingularAttribute<Reclamation, Date> dateReclamation;
    public static volatile SingularAttribute<Reclamation, String> description;
    public static volatile SingularAttribute<Reclamation, DetailsTache> detailsTache;
    public static volatile SingularAttribute<Reclamation, String> sujet;
    public static volatile SingularAttribute<Reclamation, Boolean> traitee;
    public static volatile SingularAttribute<Reclamation, Integer> idReclamation;

}