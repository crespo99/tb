package com.entities;

import com.entities.QuestionGroupement;
import com.entities.Reclamation;
import com.entities.Tache;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(DetailsTache.class)
public class DetailsTache_ { 

    public static volatile SingularAttribute<DetailsTache, String> valeurReponse;
    public static volatile SingularAttribute<DetailsTache, Integer> idDetailsTache;
    public static volatile SingularAttribute<DetailsTache, Tache> tache;
    public static volatile ListAttribute<DetailsTache, Reclamation> listReclamations;
    public static volatile SingularAttribute<DetailsTache, String> valeurSaisie;
    public static volatile SingularAttribute<DetailsTache, QuestionGroupement> quesGrou;

}