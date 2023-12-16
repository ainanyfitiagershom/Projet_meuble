<%-- 
    Document   : matiereP
    Created on : 12 déc. 2023, 21:51:06
    Author     : mac
--%>


<%@page import="entite.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
   
    
    <%@include file="Template/Head.jsp" %>
    <body>
      
        
       <%@include file="Template/sideBar.jsp" %>
       
       <%@include file="Template/project1.jsp" %>
    
     
      
        
       
       
       
       
       
         <div class="details">
                <div class="recentOrders">
                      <div class="cardHeader">
                        <h2>Liste des Matieres premiers</h2>
                      
                    </div>
                    
                    
  <form action="matiereP" method="post">
           <label>Nom:</label>
           <input type="text" name="nom">
           
           <input type="submit" value="insert">
       </form>
                  
                    <table>
                        <thead>
                            <tr>
                                <td>Numero</td>
                                <td>Nom</td>
                               
                            </tr>
                        </thead>
                        <tbody>    
        
       
       
          <% 
                if(request.getAttribute("mp")!=null){
                Matiere_premier[] mp=(Matiere_premier[])request.getAttribute("mp");
                    for(Matiere_premier p: mp){ %>
                        <tr>
                            <td><%=p.getId()%></td>
                            <td><%=p.getNom()%></td>
                           
                        </tr><%
                    }
                }%>
                            
               
                        </tbody>
                    </table>
                </div>
                </div>
       
        
          <%@include file="Template/Footer.jsp" %>
    </body>
</html>
