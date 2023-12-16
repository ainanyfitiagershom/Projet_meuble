<%-- 
    Document   : matiereS
    Created on : 12 déc. 2023, 22:15:19
    Author     : mac
--%>

<%@page import="entite.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% Matiere_premier [] mp = (Matiere_premier[]) request.getAttribute("matiere_premier") ; %>
<% Style [] style = (Style[]) request.getAttribute("style") ; %>

<!DOCTYPE html>
<html>
   
    
    <%@include file="Template/Head.jsp" %>
    <body>
      
        
       <%@include file="Template/sideBar.jsp" %>
       
       <%@include file="Template/project1.jsp" %>
        
  
                <div class="details">
                <div class="recentOrders">
                      <div class="cardHeader">
                        <h2>Matiere style</h2>
                      
                    </div>
  
                    
                     <form action="matiereS" method="post">
          
                        <select name="idmp">
                            <%
                            for(int i = 0 ; i<mp.length ; i++){ %>
                                <option value="<%=mp[i].getId() %>"> <%=mp[i].getNom()%> </option>
                            <%}
                                %>
               
                        </select>
                
                                <br> <br>
                             <%   for(int i = 0; i<style.length; i++){ %>
                             
                                   <p> <input type="checkbox" id="" name="idstyle" value="<%=style[i].getId() %>">
                             <%=style[i].getNom() %> </p>
                                <% } %>
                                                                   <br> <br>
                        <input type="submit" value="insert">
                    </form>
                  
                    <table>
                        <thead>
                            <tr>
                                <td>Style Choisi</td>
                                <td>Matiere</td>
                               
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
                
        
        
          <%@include file="Template/Footer.jsp" %>
    </body>
</html>
