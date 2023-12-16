/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import connect.Connect;
import generalise.Motherobj;
import java.text.ParseException;

/**
 *
 * @author mac
 */
public class Style extends Motherobj<Style>{
    int id;
    String nom;
    
    public Style() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Style(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
     public void insert(String nom) throws ParseException, Exception{
        Style s = new Style();
        s.setNom(nom);
        s.create(new Connect().getConnectionPsql(), "id");
    }
    
    
}
