package com.entities;

import com.entities.DetailsTache;
import com.entities.Groupement;
import com.entities.Question;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(QuestionGroupement.class)
public class QuestionGroupement_ { 

    public static volatile SingularAttribute<QuestionGroupement, Boolean> QuestionObligatoire;
    public static volatile SingularAttribute<QuestionGroupement, Question> question;
    public static volatile ListAttribute<QuestionGroupement, DetailsTache> detQues;
    public static volatile SingularAttribute<QuestionGroupement, Groupement> groupement;
    public static volatile SingularAttribute<QuestionGroupement, Integer> idQuesGroup;

}