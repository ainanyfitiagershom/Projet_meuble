<%-- 
    Document   : produit
    Created on : 12 déc. 2023, 19:08:24
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
     
        <section>
            <div class="details">
                <div class="recentOrders">
                      <div class="cardHeader">
                        <h2>Liste des produits</h2>
                      
                    </div>
  <form action="Produits" method="post">
           <label>Insert Nom produit:</label>
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
                if(request.getAttribute("pro")!=null){
                Produit[] produits=(Produit[])request.getAttribute("pro");
                    for(Produit p: produits){ %>
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
                 <% if(request.getAttribute("error") !=null) { %>
                        <p>  <%=request.getAttribute("error") %> </p>
                         <%   } %>
        </section>
          <%@include file="Template/Footer.jsp" %>
    </body>
</html>
