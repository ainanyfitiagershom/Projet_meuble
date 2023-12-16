/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import connect.Connect;
import entite.Matiere_par_style;
import entite.Matiere_premier;
import entite.Matiere_style;
import entite.Produit;
import entite.Style;
import java.io.IOException;
import java.sql.Connection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author mac
 */
public class Matiere_par_S extends HttpServlet{
    
    
      protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
            System.out.print("Tonga");
            Style[] styles = null;
          
              String error = "";
              
              Connection con = null;
             
        try{
              con = new Connect().getConnectionPsql();
              styles = new Style().read(con);
             
         } catch(Exception e){
             e.printStackTrace();
             error = e.getMessage();
         
         } 
        finally{
           if(con != null) {
               try{
                   con.close();
               
               } catch(Exception ex) {
                   ex.printStackTrace();
               }
           }
           
         }
        System.out.print("Tonga oa");
          
            request.setAttribute("styles", styles);
            request.setAttribute("sect", "mat_par_style.jsp");
            request.setAttribute("error",error);
              RequestDispatcher req = request.getRequestDispatcher("mat_par_style.jsp");
              req.forward(request, response);
                   
         
  }
}
