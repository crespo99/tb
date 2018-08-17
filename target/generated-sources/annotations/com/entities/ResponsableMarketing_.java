package com.entities;

import com.entities.Affectation;
import com.entities.Filiale;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(ResponsableMarketing.class)
public class ResponsableMarketing_ extends Utilisateur_ {

    public static volatile SingularAttribute<ResponsableMarketing, Filiale> filiale;
    public static volatile ListAttribute<ResponsableMarketing, Affectation> listAffresp;

}