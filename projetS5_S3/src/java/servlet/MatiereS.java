/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servlet;

import connect.Connect;
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
    
public class MatiereS extends HttpServlet{
    
      protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        Style [] styles = null;
        Matiere_premier [] mp = null;

       try {
        Connection connection=new Connect().getConnectionPsql();
         styles = new Style().read(connection) ;
         mp = new Matiere_premier().read(connection);
      
       } catch (Exception e) {
        e.printStackTrace();
        // TODO: handle exception
       }
       

        request.setAttribute("style", styles);
        request.setAttribute("matiere_premier", mp);

        RequestDispatcher dispat = request.getRequestDispatcher("matiereS.jsp");
        dispat.forward(request,response); 
        
     }
    
          protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws  ServletException, IOException {
         
              String mp = request.getParameter("idmp");
              String style = request.getParameter("idstyle");
              Connection con = null;
             
         try{
              con = new Connect().getConnectionPsql();
          Matiere_style ms = new Matiere_style(mp, style);
          
            System.out.println(ms.getIdmp() +" mmmm   "+ ms.getIdstyle());
             ms.create(con, "id");
             
              Matiere_premier [] mat = new Matiere_premier().read(con);
                     Style [] st = new Style().read(con);
                     
                       request.setAttribute("matiere_premier", mat);
                        request.setAttribute("style", st);
             
         } catch(Exception e){
             e.printStackTrace();
         
         } finally{
             try{
                if(con != null) {
                    con.close();
                 }
             
             } catch(Exception ex){
                 ex.printStackTrace();
             }
           
         }
          
              RequestDispatcher req = request.getRequestDispatcher("matiereS.jsp");
              req.forward(request, response);
                   
         
         
          
    }
}
