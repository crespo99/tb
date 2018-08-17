package com.entities;

import com.entities.Question;
import com.entities.Reponse;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(QuestionReponse.class)
public class QuestionReponse_ { 

    public static volatile SingularAttribute<QuestionReponse, Boolean> cible;
    public static volatile SingularAttribute<QuestionReponse, Integer> idQuestion;
    public static volatile SingularAttribute<QuestionReponse, Question> ques;
    public static volatile SingularAttribute<QuestionReponse, Reponse> rep;
    public static volatile SingularAttribute<QuestionReponse, Integer> idReponse;

}