/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlet;

import connect.Connect;
import entite.Etudiant;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mirado
 */
public class EtudiantsServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        try{
            Etudiant etudiant=new Etudiant();
            Connection connection=new Connect().getConnectionPsql();
            Etudiant[] etudiants=etudiant.read(connection);
            request.setAttribute("etudiants",etudiants);
            RequestDispatcher dispatcher = request.getRequestDispatcher("formulaire.jsp");
            dispatcher.forward(request, response);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    
//      protected void doPost(HttpServletRequest request, HttpServletResponse response)
//            throws  ServletException, IOException {
//          int numero = Integer.parseInt("numero");
//          String nom = request.getParameter("nom");
//          String prenom = request.getParameter("prenom");
//          Date date = Date.valueOf(request.getParameter("date"));
//          
//    }
    
    

}
