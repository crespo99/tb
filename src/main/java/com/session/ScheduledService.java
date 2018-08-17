/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.session;

/**
 *
 * @author Administrateur
 */

import com.entities.Activite;
import com.entities.Affectation;
import com.entities.Reclamation;
import com.entities.Scrap;
import com.entities.Tache;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;


@Singleton
public class ScheduledService {
    
    @EJB
    Service s;
    private List<Affectation> listAff;
    private List<Affectation> listAffNS;
    private List<Affectation> listAffNV;
    private List<Reclamation> listRec;
    private List<Affectation> listAffSc;
      String cmm = "py -3.6-32 D:\\PFA\\job\\job.py";
	@Schedule(dayOfMonth="1", hour="3",persistent = false)
	public void timerBean() throws InterruptedException {
		System.out.println("Called 1");
		
                
      listAff=s.getListAffectations();
      for(Affectation a:listAff){
          
          
 
          Tache ta = new Tache();
          ta.setAffectationTache(a);
          ta.setDateTache(new Date());
          ta.setEtat("En cours de saisie");
          s.ajoutTache(ta);
          
          System.out.println("creation nouvelle tache");
      }
      
                
        Thread.sleep(1000);
	}
	
        
        @Schedule(dayOfMonth="15-Last", hour="7",persistent = false)
	public void testSaisie() throws InterruptedException {
		System.out.println("Called 2");
		
                
        listAffNS=s.getTacheNnSaisie();
        
        if (!listAffNS.isEmpty())
        {for(Affectation a:listAffNS){
            
            
            try{
            String host ="smtp.gmail.com" ;
            String user = "digkey.pgh@gmail.com ";
            String pass = "marwenwaelakrem";
            String to = a.getAgent().getEmail();
            String from = "digkey.pgh@gmail.com ";
            String subject = "Tâche non saisie";
            String messageText = "Bonjour, "
                    + "Je vous informe que vous n'avez pas encore fait la saisie de la tâche mensuelle concernant l'activité "+a.getActivite().getNomActivite()
                    +" sur la plateforme "+a.getPlateforme().getNomPlateforme()+".\n Merci de faire cette tâche dans le plus bref délais.";
            boolean sessionDebug = false;

                    Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
            
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            
            
          System.out.println("Alerte envoyée");
      }}
      
                
        Thread.sleep(1000);
	}
	
	
        @Schedule(dayOfMonth = "1-Last",hour = "7", persistent = false)
	public void alertValid() throws InterruptedException {
		System.out.println("Called 3");
		
                
        listAffNV=s.getTacheNnValid();
        
        if (!listAffNV.isEmpty())
        {for(Affectation a:listAffNV){
            
            
            try{
            String host ="smtp.gmail.com" ;
            String user = "digkey.pgh@gmail.com ";
            String pass = "marwenwaelakrem";
            String to = a.getResponsable().getEmail();
            String from = "digkey.pgh@gmail.com ";
            String subject = "Tâche non saisie";
            String messageText = "Bonjour, "
                    + "Je vous informe que vous n'avez pas encore fait la validation de la tâche mensuelle concernant l'activité "+a.getActivite().getNomActivite()
                    +" sur la plateforme "+a.getPlateforme().getNomPlateforme()+".\n Vueillez s'il vous plaît valider cette tâche dans le plus bref délais.";
            boolean sessionDebug = false;

                    Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
            
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            
            
          System.out.println("Alerte envoyée");
      }}
      
                
        Thread.sleep(1000);
	}
        
        
        @Schedule(dayOfMonth = "1-Last",hour = "11", persistent = false)
	public void alertReclamation() throws InterruptedException {
		System.out.println("Called 4");
		
                
        listRec=s.getListRec();
        
        if (!listRec.isEmpty())
        {for(Reclamation a:listRec){
            
            
            try{
            String host ="smtp.gmail.com" ;
            String user = "digkey.pgh@gmail.com ";
            String pass = "marwenwaelakrem";
            String to = a.getDetailsTache().getTache().getAffectationTache().getAgent().getEmail();
            String from = "digkey.pgh@gmail.com ";
            String subject = "Réclamation non traité";
            String messageText = "Bonjour, "
                    + "Je vous informe que vous n'avez pas encore corriger la réclamation concernant l'activité "+a.getDetailsTache().getTache().getAffectationTache().getActivite().getNomActivite()
                    +" sur la plateforme "+a.getDetailsTache().getTache().getAffectationTache().getPlateforme().getNomPlateforme()+".\n Vueillez s'il vous plaît valider cette tâche dans le plus bref délais.";
            boolean sessionDebug = false;

                    Properties props = System.getProperties();

            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", host);
            props.put("mail.smtp.port", "587");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.required", "true");

            java.security.Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
            Session mailSession = Session.getDefaultInstance(props, null);
            mailSession.setDebug(sessionDebug);
            Message msg = new MimeMessage(mailSession);
            msg.setFrom(new InternetAddress(from));
            InternetAddress[] address = {new InternetAddress(to)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject(subject); msg.setSentDate(new Date());
            msg.setText(messageText);
            
           Transport transport=mailSession.getTransport("smtp");
           transport.connect(host, user, pass);
           transport.sendMessage(msg, msg.getAllRecipients());
           transport.close();
           System.out.println("message send successfully");
           
        }catch(Exception ex)
        {
            System.out.println(ex);
        }
            
            
          System.out.println("Alerte envoyée");
      }}
      
                
        Thread.sleep(1000);
	}
        
        
        @Schedule(dayOfMonth="5", hour="7",persistent = false)
	public void Scrap() throws InterruptedException {
		System.out.println("Called 5");
		
              listAffSc = s.getAffParPlat("Facebook");
              
              
        
            try {
            
	    // run the Unix "ps -ef" command
            // using the Runtime exec method:
        	for(Affectation a:listAffSc)
              {
                 cmm = cmm + " " + a.getActivite().getNomActivite();
                 Process p = Runtime.getRuntime().exec(cmm);
                 
                 Integer id = s.getIdLastScrap();
                 Scrap scrap = s.getLastScrap(id);
                 scrap.setAff(a);
                 s.updateScrap(scrap);
              }

            System.exit(0);
        }
        catch (IOException e) {
            System.out.println("exception happened - here's what I know: ");
            e.printStackTrace();
            System.exit(-1);
        }
            
              
              
              
          System.out.println("Scrap");     
        Thread.sleep(1000);
	}
        
	/*public void testTimeFinished(Auction a){
		Date current = new Date();
		if(a.getEndDate().before(current)){
			a.setPublished(false);
			
			User u = auctionService.getAuctionWinner(a);
			if(u!=null){
				a.setWinner(u);
				auctionService.save(a);
			}
			
		}
	}*/

}
