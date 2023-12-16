/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entite;

import generalise.Motherobj;

/**
 *
 * @author mac
 */
public class Matiere_par_style extends Motherobj<Matiere_par_style>{
    int idstyle;
    String nomstyle;
    int idmp;
    String nommp;
    
    public Matiere_par_style() {
    }

    public int getIdstyle() {
        return idstyle;
    }

    public void setIdstyle(int idstyle) {
        this.idstyle = idstyle;
    }

    public String getNomstyle() {
        return nomstyle;
    }

    public void setNomstyle(String nomstyle) {
        this.nomstyle = nomstyle;
    }

    public int getIdmp() {
        return idmp;
    }

    public void setIdmp(int idmp) {
        this.idmp = idmp;
    }

    public String getNommp() {
        return nommp;
    }

    public void setNommp(String nommp) {
        this.nommp = nommp;
    }

    public Matiere_par_style(int idstyle, String nomstyle, int idmp, String nommp) {
        this.idstyle = idstyle;
        this.nomstyle = nomstyle;
        this.idmp = idmp;
        this.nommp = nommp;
    }
    
    public Matiere_par_style(String idstyle){
        
        this.idstyle = Integer.valueOf(idstyle);
    }
    
    
}
