<%-- 
    Document   : cliente
    Created on : 16-apr-2016, 12.08.20
    Author     : selima
    jsp che mostra all'utente l'elenco degli oggetti messi in vendita (in una tabella)
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Portale cliente</title>
        <!--Metainformazioni sulla pagina -->
        <meta charset="UTF-8">
        <meta name="keywords" content="Cliente, Offerte, Compra, Auto, Nuovo, Usato">
        <meta name="description" content="Portale cliente autontheline.com.">
        <meta name="author" content="Selima Curci">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- Link al foglio di stile comune a tutte le pagine -->
        <link href="style.css" rel="stylesheet" type="text/css" media="screen">
        <!-- Icona del sito -->
        <link rel="icon" href="Images/auto.jpeg" type="image/jpeg" />
        <script type="text/javascript" src="js/jquery-1.12.4.min.js"></script>
        <script type="text/javascript" src="js/filter.js"></script>
    </head>
    <body>
        <div id="page">
            
            <header>
              <!-- E' possibile effettuare il logout da questo link, che 
                   richiamerà la servlet Logout
              -->
              <c:if test="${sessionScope.utente != null}">
                  <div id="logout"><a href="logout.html">Logout</a></div>
              </c:if>
              
              <!-- Titolo usato per la risoluzione piccola e intermedia -->
              <h1 class="logo">AUTONTHELINE</h1>
              
              <!-- Sezione di navigazione -->
                <nav>
                    <ul>
                        <li> <a href="login.html">Login</a> </li>
                        <li> <a href="descrizione.html">Descrizione</a> </li>
                        <li class="currentpage"> <a href="cliente.html">Cliente</a> </li>
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
                        </ul>
                      </li>
                    </ul>
                </div>
            </header>
            
            <div id="sidebar1">
                
            </div>

            <div id="central">
                <h1>Lista dei veicoli in vendita</h1>
                
                <!-- Se l'attributo conferma è uguale a true significa che l'utente, dopo aver visto il riepilogo 
                     dell'oggetto selezionato, ha cliccato sul pulsante per confermare l'acquiisto. In questo caso
                     devo comunicargli se è andato o meno a buon fine tramite un messaggio
                -->
                <c:if test="${conferma==true}">
                    <c:choose>
                        <c:when test="${pagamento == true}" >
                            <p class="ok"> Pagamento avvenuto con successo! </p>
                        </c:when>
                        <c:otherwise>
                            <p class="errore"> Il tuo saldo non è sufficiente, riprova in un secondo momento! </p>
                        </c:otherwise>
                    </c:choose>
                </c:if>
                         
                <!-- Se ci sono stati errori durante il pagamento o la selezione, per colpa di parametri erratti avviso il cliente
                -->           
                <c:if test="${errore == true}" >
                    <p class="errore"> Si è verificato un errore nel pagamento, riprova! </p>
                </c:if>
                 
                <!--
                   Quanto l’utente scrive nel textfield, ad ogni pressione di un tasto la pagina invia una richiesta
                   ajax all’indirizzo filter.json, passando come parametro la stringa inserita dall’utente
                -->
                <div class="spazio"></div>
                <label class="filter "for="filtra">Filtra</label>
                <input class="filter" type="text" name="filtra" id="filtra" value="" />
                <div class="spazio"></div> 
                <p id="avviso"></p>
                <!-- Tabella con i veicoli in vendita nel sito -->
                <table id="tabella">
                    <tr class="intestazione"> 
                        <th> Nome </th> 
                        <th> Foto </th> 
                        <th> Esemplari </th>
                        <th> Prezzo </th>
                        <th> Compra</th>
                    </tr>
                    <!--  Scorre la lista degli oggetti passatagli come attributo e li inserisce uno ad uno nella  
                          tabella
                    -->
                    <c:forEach var="auto" items="${listaAuto}" >
                        <tr> 
                            <td> ${auto.nomeAuto} </td> 
                            <td><img title="${auto.nomeAuto}" alt="Foto dell'auto ${auto.nomeAuto}" src="${auto.urlImmagine}" /></td> 
                            <td> ${auto.quantita} </td>
                            <td> ${auto.prezzoUnitario}</td>
                            <td><a href="cliente.html?idAuto=${auto.id}"><img src="Images/aggiungi.png" width="40" height="40" title="Aggiungi" alt="Aggiungi"/></a></td>
                        </tr>
                    </c:forEach>
                </table>
                
                <div id="noelements"></div>
                
                <c:if test="${size == 0}">
                    <p class="errore">Non ci sono oggetti in vendita ! Siamo dispiaciuti.</p>
                </c:if>
                
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
