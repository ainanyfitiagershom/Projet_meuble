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
public class Matiere_style extends Motherobj<Matiere_style>{
     int id;
    int idmp;
    int idstyle;
    
    public Matiere_style() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdmp() {
        return idmp;
    }

    public void setIdmp(int idmp) {
        this.idmp = idmp;
    }

    public int getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(int idstyle) {
        this.idstyle = idstyle;
    }

    public Matiere_style(int id, int idmp, int idstyle) {
        this.id = id;
        this.idmp = idmp;
        this.idstyle = idstyle;
    }
    
    public Matiere_style(String idmp, String idstyle) {
        this.idmp = Integer.valueOf(idmp);
        this.idstyle = Integer.valueOf(idstyle);
    }
    
      public void insert(int idmp, int idstyle) throws ParseException, Exception{
      Matiere_style ms = new Matiere_style();
       ms.setIdstyle(idstyle);
       ms.setIdmp(idmp);
       ms.create(new Connect().getConnectionPsql(), "id");
     }
    
}
