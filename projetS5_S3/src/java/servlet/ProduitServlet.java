/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import connect.Connect;
import generalise.Motherobj;
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
public class ProduitServlet extends HttpServlet{
    
                protected void doPost(HttpServletRequest request, HttpServletResponse response)
                  throws  ServletException, IOException {

                String nom = request.getParameter("nom");
                Produit produit = new Produit();

                try {
                    produit.insert(nom);
                    RequestDispatcher req = request.getRequestDispatcher("/produit.jsp");
                    req.forward(request, response);

                } catch(Exception ex) {

                }


                }
                
//                 request.setAttribute("styles", styles);
//            request.setAttribute("ms", mss);
//            request.setAttribute("sect", "mat_par_style.jsp");
//            request.setAttribute("error",error);
//              RequestDispatcher req = request.getRequestDispatcher("mat_par_style.jsp");
//              req.forward(request, response);
//                
                
                 protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
        try{
                Produit p = new Produit();
                Connection con = new Connect().getConnectionPsql();
                Produit[] produits = p.read(con);
                String error = "";
                request.setAttribute("pro", produits);
                request.setAttribute("sect", "produit.jsp");
                request.setAttribute("error", error);
                RequestDispatcher dispatcher = request.getRequestDispatcher("/produit.jsp");
                dispatcher.forward(request, response);
                
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
}
