/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import connect.Connect;
import entite.Matiere_premier;
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
public class MatiereP extends HttpServlet{
    
     protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
              
                String nom = request.getParameter("nom");
                Matiere_premier s = new Matiere_premier();
          try {
              s.insert(nom);
              RequestDispatcher req = request.getRequestDispatcher("/matiereP.jsp");
              req.forward(request, response);
                   
          } catch(Exception ex) {
              
          }
}
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        try{
                Matiere_premier mp = new Matiere_premier();
                Connection con = new Connect().getConnectionPsql();
                Matiere_premier[] mps = mp.read(con);
                request.setAttribute("mp", mps);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/matiereP.jsp");
                dispatcher.forward(request, response);
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
    
}
