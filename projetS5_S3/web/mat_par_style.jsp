<%-- 
    Document   : formulaire
    Created on : 12 déc. 2023, 13:30:00
    Author     : mac
--%>
  <%@page import="entite.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html>
  
    <%@include file="Template/Head.jsp" %>
<body>
   
       <%@include file="Template/sideBar.jsp" %>
       <section>
          <!-- ================ Order Details List ================= -->
            <div class="details">
                <div class="recentOrders">
                    <div class="cardHeader">
                        <h2>Choisir une style pour avoir les matieres.</h2>
                    </div>
                        <% 
                            Style[] styles = (Style[]) request.getAttribute("styles");
                        %>
                        
                        <form action="styleservlet" method="get">
                            <select id="style" name="idstyle" style="width: 150px;">
                           
                               <%
                            if(styles!=null) {
                            for(Style style: styles){ %>
                                <option value="<%=style.getId() %>"> <%=style.getNom()%> </option>
                            <%}}
                                %>
                       
               
                        </select>
                            
             
           
                            <input type="submit" value="insert">
                        </form><!-- comment -->
                        
                        
                       

                        <table style="width: 200px; height: 60px">
                        <thead>
                         
                            
                            
                            <tr>
                                <td>Numero</td>
                                <td>Nom</td>
                                
                            </tr>
                        </thead>
                        <tbody>   
                            
                       <% Matiere_par_style[] mss = (Matiere_par_style[]) request.getAttribute("ms"); 
                        if(mss != null){ %>
                            <% for(Matiere_par_style ms: mss) { %>
                            
                         <tr>
                             <td><%=ms.getIdmp()%></td>
                            <td><%=ms.getNommp()%></td>
                           
                        </tr>
                        
                            <%
                            }
                            %>
                               <%
                             }
                             %>
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
