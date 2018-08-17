package com.entities;

import com.entities.QuestionGroupement;
import com.entities.QuestionReponse;
import com.entities.Reponse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Question.class)
public class Question_ { 

    public static volatile ListAttribute<Question, QuestionGroupement> listGroupement;
    public static volatile SingularAttribute<Question, String> typeQuestion;
    public static volatile SingularAttribute<Question, Integer> idQuestion;
    public static volatile ListAttribute<Question, Reponse> listReponse;
    public static volatile SingularAttribute<Question, String> nomQuestion;
    public static volatile ListAttribute<Question, QuestionReponse> listreponse;

}