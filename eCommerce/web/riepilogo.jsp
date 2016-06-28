<%-- 
    Document   : riepilogo
    Created on : 16-apr-2016, 12.08.31
    Author     : selima
    jsp per il riepilogo su un oggetto, utilizzata sia per il cliente che per il venditore
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Riepilogo ${utente}</title>
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
                        <c:choose>
                            <c:when test="${utente == 'venditore'}" >
                              <li> <a href="venditore.html">Venditore</a> </li>
                            </c:when>
                            <c:otherwise>
                              <li> <a href="cliente.html">Cliente</a> </li>
                            </c:otherwise>
                        </c:choose>
                        <li class="currentpage"> Riepilogo</li>
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
                            <c:choose>
                            <c:when test="${utente == 'venditore'}" >
                              <li> <a href="venditore.html">Venditore</a> </li>
                            </c:when>
                            <c:otherwise>
                              <li> <a href="cliente.html">Cliente</a> </li>
                            </c:otherwise>
                            </c:choose>
                        </ul>
                      </li>
                    </ul>
                </div>
            </header>
            
            <div id="sidebar1">
                
            </div>

            <div id="central">
                
                <c:choose>
                    <c:when test="${utente == 'venditore'}" >
                        <c:if test="${conferma != null}"> 
                            <h1>Riepilogo oggetto inserito</h1>
                        </c:if>
                        <c:if test="${modifica != null}"> 
                            <h1>Riepilogo oggetto modificato</h1>
                        </c:if>
                    </c:when>
                    <c:otherwise>
                         <h1>Riepilogo oggetto da acquistare</h1>
                    </c:otherwise>
                </c:choose>
                
                <!-- Mostro i dettagli dell'oggetto in una tabella
                 -->
                
                <table>
                    <tr class="intestazione"> 
                        <th> Nome </th> 
                        <th> Foto </th> 
                        <th> Esemplari </th>
                        <th> Prezzo </th>
                    </tr>
                        <tr> 
                            <td> ${auto.nomeAuto} </td> 
                            <td> 
                                <c:if test="${ auto.urlImmagine != null}">   
                                  <img title="${auto.nomeAuto}" alt="${auto.nomeAuto}" src="${auto.urlImmagine}" />
                                </c:if>
                            </td> 
                            <td> 
                                <!-- Se si tratta del riepilogo del cliente la quantita è pari a uno altrimenti mostro
                                     gli esemplari che il venditre rende disponibile dell'oggetto in questione
                                -->
                                <c:choose>
                                    <c:when test="${utente == 'venditore'}" >
                                        ${auto.quantita} 
                                    </c:when>
                                    <c:otherwise>
                                        1
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td> ${auto.prezzoUnitario} </td>
                        </tr>
                </table>
                
                <!-- Descrizione dell'oggetto -->
                <div id="descr_oggetto">
                    <h3>Descrizione auto</h3>       
                    <p>${auto.descrizione}</p>       
                </div>
                
                <!-- Se il riepilogo è relativo all'oggetto inserito dal venditore allora mostro un messaggio per 
                     avvisarlo se l'inserimento è andato o meno a buon fine. Questo in base al valore dell'attributo
                     passato dalla servlet -->
                <c:if test="${utente == 'venditore'}">
                        <c:if test="${conferma != null}">        
                             <c:if test="${conferma == true}">     
                                     <p class="ok"> Inserimento avvenuto con successo! </p>
                             </c:if>
                        </c:if>
                               
                        <c:if test="${modifica != null}">        
                            <c:if test="${modifica != null}">        
                               <c:if test="${modifica == true}">     
                                     <p class="ok"> Modifica avvenuta con successo! </p>
                               </c:if>
                            </c:if>
                        </c:if>
                </c:if>
              
                <!-- Se il riepilogo è relativo al cliente mostro il pulsante per confermare l'acquisto -->
                <c:if test="${utente == 'cliente'}">
                    <p class="messaggio"> Clicca sul pulsante per confermare l'acquisto<p>
                    <!-- Passo l'id dell oggetto da comprare nell'url, in questo modo lo tratto come variabile di
                         pagina e permetto l'interazione da parte dell'utente con più oggetti. Facendo così sono
                         sicura al clik comprerà l'oggetto corrente.-->
                    <form action="cliente.html?idSel=${auto.id}" method="post">
                        <input type="submit" value="Conferma" id="conferma" name="conferma"/>
                    </form>
                </c:if>
                
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
 