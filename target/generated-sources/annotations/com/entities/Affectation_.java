package com.entities;

import com.entities.Activite;
import com.entities.AffectationConsultee;
import com.entities.Plateforme;
import com.entities.Questionnaire;
import com.entities.ResponsableMarketing;
import com.entities.Scrap;
import com.entities.Tache;
import com.entities.UtilisateurAgence;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2018-08-14T13:06:50")
@StaticMetamodel(Affectation.class)
public class Affectation_ { 

    public static volatile SingularAttribute<Affectation, Activite> activite;
    public static volatile SingularAttribute<Affectation, String> urlPlateforme;
    public static volatile ListAttribute<Affectation, AffectationConsultee> ListConsulteurs;
    public static volatile SingularAttribute<Affectation, UtilisateurAgence> agent;
    public static volatile SingularAttribute<Affectation, ResponsableMarketing> responsable;
    public static volatile SingularAttribute<Affectation, Questionnaire> questionnaireAffectation;
    public static volatile SingularAttribute<Affectation, Date> dateAffectation;
    public static volatile ListAttribute<Affectation, Scrap> listScrap;
    public static volatile SingularAttribute<Affectation, Plateforme> plateforme;
    public static volatile SingularAttribute<Affectation, Integer> idAffectation;
    public static volatile ListAttribute<Affectation, Tache> listTaches;

}