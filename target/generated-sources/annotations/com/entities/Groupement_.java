package com.entities;

import com.entities.QuestionGroupement;
import com.entities.QuestionnaireGroupement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Groupement.class)
public class Groupement_ { 

    public static volatile ListAttribute<Groupement, QuestionnaireGroupement> ListQuestionnaire;
    public static volatile SingularAttribute<Groupement, Integer> idGroupement;
    public static volatile SingularAttribute<Groupement, String> nomGroupement;
    public static volatile ListAttribute<Groupement, QuestionGroupement> listQuestion;

}