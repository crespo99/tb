package com.entities;

import com.entities.ConcurrentParActivite;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(ActiviteConcurrent.class)
public class ActiviteConcurrent_ { 

    public static volatile SingularAttribute<ActiviteConcurrent, Integer> idActiviteConcurrent;
    public static volatile ListAttribute<ActiviteConcurrent, ConcurrentParActivite> ListActParConc;
    public static volatile SingularAttribute<ActiviteConcurrent, String> nom;

}