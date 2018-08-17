/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Responsable;

import java.io.IOException;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrateur
 */
@ManagedBean(name = "acceuilBeanO")
@SessionScoped
public class AcceuilBeanO implements Serializable{
    
    String login;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
    public AcceuilBeanO() {
    }
    public void verifier() throws IOException
    {
        HttpSession sess = (HttpSession) FacesContext.getCurrentInstance()
                .getExternalContext().getSession(false);
        login=sess.getAttribute("login").toString();

        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.redirect("ListeTaches.xhtml");
    }
    
    
}
