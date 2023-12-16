/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import connect.Connect;
import entite.Etudiant;
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
public class StyleController extends HttpServlet{
    
      protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
              
                String nom = request.getParameter("nom");
                Style s = new Style();
          try {
              s.insert(nom);
              RequestDispatcher req = request.getRequestDispatcher("/Style.jsp");
              req.forward(request, response);
                   
          } catch(Exception ex) {
              
          }
}
      
      
        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        try{
                Style style = new Style();
                Connection con = new Connect().getConnectionPsql();
                Style[] styles = style.read(con);
                request.setAttribute("style", styles);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/Style.jsp");
                dispatcher.forward(request, response);
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
