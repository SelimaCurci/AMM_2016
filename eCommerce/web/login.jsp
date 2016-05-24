<%-- 
    Document   : login.jsp
    Created on : 16-apr-2016, 12.05.26
    Author     : selima
    jsp per visualizzare il form di login
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <title>Login</title>
        <!-- Metainformazioni sulla pagina -->
        <meta charset="UTF-8">
        <meta name="keywords" content="Login, Autontheline.com">
        <meta name="description" content="Login su autontheline.com">
        <meta name="author" content="Selima Curci">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!-- Link al foglio di stile comune a tutte le pagine -->
        <link href="style.css" rel="stylesheet" type="text/css" media="screen">
        <!-- Icona del sito -->
        <link rel="icon" href="Images/auto.jpeg" type="image/jpeg" />
    </head>
    <body>
        <div id="page">
            
            <header>
              <!-- Titolo usato per la risoluzione piccola e intermedia -->
              <h1 class="logo">AUTONTHELINE</h1>
              
              <!-- Sezione di navigazione -->
                <nav>
                    <ul>
                        <li class="currentpage"> <a href="login.html">Login</a> </li>
                        <li> <a href="descrizione.html">Descrizione</a> </li>
                        <li> <a href="cliente.html">Cliente</a> </li>
                        <li> <a href="venditore.html">Venditore</a> </li>
                    </ul>
                </nav>
                <!-- Menù a tendina per la risoluzione più bassa -->
                <div class="menu">
                    <ul>  
                      <li id="barramenu">
                        <a href="#">Menù</a>
                        <ul>
                            <li><a href="login.html">Login</a></li>
                            <li><a href="descrizione.html">Descrizione</a></li>
                            <li><a href="cliente.html">Cliente</a></li>
                            <li><a href="venditore.html">Venditore</a></li>
                        </ul>
                      </li>
                    </ul>
                </div>
            </header>
            
            <div id="sidebar1">
                
            </div>

            <div id="central">
                <h1>Login al sito</h1>
                <p>Inserisci le tue credenziali per accedere a tutti i servizi di <strong>AUTONTHELINE.com</strong></p>

                <!-- Form per l'inserimento delle credenziali da parte dell'utente-->
                <form action="login.html" method="post">
                    <fieldset>
                    <label for="username">Username</label>
                    <input type="text" name="username" id="username" value="" />
                    <div class="spazio"></div>
                    <label for="pswd">Password</label>
                    <input type="password" name="pswd" id="pswd" value="" />
                    
                    <input type="submit" value="Login" id="submit" name="submit" />
                    </fieldset>
                </form>
                
                <div id="o">
                    <!-- Se le credenziali d'accesso sono erratte o comunque si è verificato un errore durante
                         l'autenticazione dell'utente, questo viene avvertito con un messaggio
                    -->
                    <c:if test="${errore == true}">
                       <p class="errore"> Login erratto, riprova!</p>
                    </c:if>
                </div>
                
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>

