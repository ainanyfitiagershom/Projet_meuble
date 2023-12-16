/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import java.sql.Date;
import generalise.Motherobj;
import java.text.ParseException;
import connect.Connect;
/**
 *
 * @author 
 */
public class Etudiant extends Motherobj<Etudiant>
{ 
  int idetudiant;
  int numero;
  String nom;
  String prenom;
  Date dtn;
  public Etudiant() {
  }
  public int getIdetudiant() {
    return idetudiant;
  }
  public void setIdetudiant(int idetudiant) {
    this.idetudiant = idetudiant;
  }
  public int getNumero() {
    return numero;
  }
  public void setNumero(int numero) {
    this.numero = numero;
  }
  public String getNom() {
    return nom;
  }
  public void setNom(String nom) {
    this.nom = nom;
  }
  public String getPrenom() {
    return prenom;
  }
  public void setPrenom(String prenom) {
    this.prenom = prenom;
  }
  public Date getDtn() {
    return dtn;
  }
  public void setDtn(Date dtn) {
    this.dtn = dtn;
  }
    
    public Etudiant(int idetudiant, int numero, String nom, String prenom, Date dtn) {
        this.idetudiant = idetudiant;
        this.numero = numero;
        this.nom = nom;
        this.prenom = prenom;
        this.dtn = dtn;
        
    
    }
 
    
//    public void insertEtudiant(int numero, String nom, String prenom, Date dtn) throws ParseException, Exception{
//        Etudiant etu = new Etudiant();
//        etu.setNumero(numero);
//        etu.setNom(nom);
//        etu.setPrenom(prenom);
//        etu.setDtn(dtn);
//       
//        etu.create(new Connect().getConnectionPsql(), "idetudiant");
//    }

   
 
  
 
    
    
}