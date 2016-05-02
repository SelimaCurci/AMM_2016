<%-- 
    Document   : accessoNegato
    Created on : 22-apr-2016, 14.14.42
    Author     : selima
    Questo jsp viene mostrta quando un utente cerca di accedere a una pagina per la quale non ha i permessi,
    oppure se non ha effettuato il login
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <!-- Stampa Cliente o Venditore a seconda del tentativo d'accesso effettuato, rispettivamente cliente.html
             e venditore.html -->
        <title>Portale ${utente}</title>
        <!-- Metainformazioni sulla pagina -->
        <meta charset="UTF-8">
        <meta name="keywords" content="Vendita, Usato, Nuovo, Auto, Veicoli">
        <meta name="description" content="Inserimento di un nuovo veicolo in vendita">
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
              <!-- Se la sessione è attiva e valida allora è possibile effettuare il logout da questo link, che 
                   richiamerà la servlet Logout
              -->
              <c:if test="${login == true}">
                  <div id="logout"><a href="logout.html">Logout</a></div>
              </c:if>
                
              <!-- Titolo usato per la risoluzione piccola e intermedia -->
              <h1 class="logo">AUTONTHELINE</h1>
              
              <!-- Sezione di navigazione -->
                <nav>
                    <ul>
                        <li> <a href="login.html">Login</a> </li>
                        <li> <a href="descrizione.html">Descrizione</a> </li>
                        <li class="currentpage"> <a href="">${utente}</a></li>
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
                            <li><a href=""> ${utente}</a></li>
                        </ul>
                      </li>
                    </ul>
                </div>
            </header>
            
            <div id="sidebar1">
                
            </div>

            <div id="central">
                
                <h1>Accesso negato</h1>
                <p id="divieto">Non hai i permessi necessari per accedere a questa pagina</p>

                
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
 