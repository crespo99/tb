package com.entities;

import com.entities.Question;
import com.entities.QuestionReponse;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Reponse.class)
public class Reponse_ { 

    public static volatile SingularAttribute<Reponse, String> valeurReponse;
    public static volatile SingularAttribute<Reponse, Question> questionReponse;
    public static volatile ListAttribute<Reponse, QuestionReponse> ListQuestionLiee;
    public static volatile SingularAttribute<Reponse, Boolean> reponseCible;
    public static volatile SingularAttribute<Reponse, Integer> idReponse;

}