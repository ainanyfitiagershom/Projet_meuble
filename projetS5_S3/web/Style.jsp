<%-- 
    Document   : Style
    Created on : 12 déc. 2023, 21:02:58
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
                         <h2>Liste des Styles</h2>
                    </div>
     
       <form action="Styles" method="post">
           <label>Insert Nom style:</label>
           <input type="text" name="nom">
           
           <input type="submit" value="insert">
       </form><!-- comment -->
       
       
                       
                      
                  

                    <table>
                        <thead>
                            <tr>
                                <td>Numero</td>
                                <td>Nom</td>
                               
                            </tr>
                        </thead>
                        <tbody>    
                            
   <% 
                if(request.getAttribute("style")!=null){
                Style[] styles=(Style[])request.getAttribute("style");
                    for(Style style: styles){ %>
                        <tr>
                            <td><%=style.getId()%></td>
                            <td><%=style.getNom()%></td>
                           
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

