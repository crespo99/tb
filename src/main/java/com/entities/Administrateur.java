/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities;

import javax.persistence.*;

@Entity
@DiscriminatorValue("Administrateur")
public class Administrateur extends Utilisateur {

    private static final long serialVersionUID = 1L;
    
}
