package com.entities;

import com.entities.Affectation;
import com.entities.QuestionnaireGroupement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Questionnaire.class)
public class Questionnaire_ { 

    public static volatile SingularAttribute<Questionnaire, String> nomQuestionnaire;
    public static volatile SingularAttribute<Questionnaire, Integer> idQuestionnaire;
    public static volatile ListAttribute<Questionnaire, QuestionnaireGroupement> ListGroupement;
    public static volatile ListAttribute<Questionnaire, Affectation> listAffQuest;

}