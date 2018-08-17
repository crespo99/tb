package com.entities;

import com.entities.Groupement;
import com.entities.Questionnaire;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(QuestionnaireGroupement.class)
public class QuestionnaireGroupement_ { 

    public static volatile SingularAttribute<QuestionnaireGroupement, Questionnaire> questionnaire;
    public static volatile SingularAttribute<QuestionnaireGroupement, Groupement> groupementQuestionnaire;
    public static volatile SingularAttribute<QuestionnaireGroupement, Integer> idGroupement;
    public static volatile SingularAttribute<QuestionnaireGroupement, Integer> idQuestionnaire;

}