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
public class Matiere_premier extends Motherobj<Matiere_premier>{
    int id;
    String nom;
    
    public Matiere_premier() {
        
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

    public Matiere_premier(int id, String nom) {
        this.id = id;
        this.nom = nom;
    }
    
      public void insert(String nom) throws ParseException, Exception{
        Matiere_premier mp = new Matiere_premier();
        mp.setNom(nom);
        mp.create(new Connect().getConnectionPsql(), "id");
      }
    
}
