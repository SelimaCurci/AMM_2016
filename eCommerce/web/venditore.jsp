<%-- 
    Document   : venditore
    Created on : 16-apr-2016, 12.08.31
    Author     : selima
    jsp che contiene il form per l'inserimento di un nuovo oggetto da parte del venditore
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Portale venditore</title>
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
                        <li class="currentpage"> <a href="venditore.html">Venditore</a> </li>
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
                            <li><a href="venditore.html">Venditore</a></li>
                        </ul>
                      </li>
                    </ul>
                </div>
            </header>
            
            <div id="sidebar1">
                
            </div>

            <div id="central">
                
                <h1>Aggiungi un veicolo in vendita</h1>
                <p> Compilando il seguente form hai tre possibilità, a seconda di quale azione desideri intraprendere.<br>
                    Se selezioni la voce nuovo (se ti dimentichi fa lo stesso)  e compili il form con tutte le informazioni riguardo il veicolo che desideri 
                    vendere e poi clicchi sul bottone aggiungi, se i dati sono corretti, il tuo veicolo sarà messo in vendita. <br>
                    Se selezioni un oggetto dalla lista di quelli che attualmente hai in vendita e clicchi sul pulsante
                    elimina, l'oggetto verrà rimosso. <br>
                    Se invece vuoi modificare qualche info sul tuo veicolo, lo selezioni,
                    compili i campi che desideri modificare e infine premi modifica. Attenzione alla correttezza dei dati!</p>
                
                <c:if test="${listaSize == 0}">
                    <p class="errore">Non hai nessun oggetto in vendita</p>
                </c:if>
                
                <div class="spazio"></div>
                
                 <c:if test="${errore==true}">
                    <p class="errore"> ${messaggioErrore} </p>
                </c:if>
                
                <c:if test="${elimina==true}">
                    <p class="ok"> Cancellazione avvenuta con successo! </p>
                </c:if>
                            
                <c:if test="${modifica==false}">
                    <p class="ok"> Non hai eseguito alcuna modifica all'oggetto selezionato!</p>
                </c:if>
                
                <!-- Form per l’inserimento di un nuovo oggetto all’interno della pagina venditore.html -->
                <form action="venditore.html" method="post">
                    <fieldset>
                        <label for="idoggetto">Seleziona oggetto</label>
                        <select name="idoggetto" id="idoggetto">
                            <option value="Nuovo">Nuovo</option>
                            <c:forEach var="obj" items="${listaAuto}" >
                                <option value="${obj.id}"> ${obj.nomeAuto} </option>
                            </c:forEach>
                        </select>   
                    <div class="spazio"></div>
                    
                    <label for="nomeoggetto">Nome</label>
                    <input type="text" name="nomeoggetto" id="nomeoggetto" value="${param["nomeoggetto"]}" />
                    <div class="spazio"></div>
                    <label for="urlimmagine">URL immmagine</label>
                    <input type="text" name="urlimmagine" id="urlimmagine" value="${param["urlimmagine"]}" />
                    <div class="spazio"></div>
                    <!-- Per la descrizione uso una text area in quanto ci si aspetta un testo pseudo-lungo -->
                    <label for="descrizione">Descrizione</label>
                    <textarea rows="4" cols="20" name="descrizione" id="descrizione" value="${param["descrizione"]}"></textarea>
                    <div class="spazio"></div>
                    <!-- L'attributo step permette di inserire numeri decimali -->
                    <label for="prezzo">Prezzo per unità</label>
                    <input type="number" step="0.01" min="5" name="prezzo" id="prezzo" value="${param["prezzo"]}">
                    <div class="spazio"></div>
                    <label for="quantita">Quantità</label>
                    <input type="number" name="quantita" id="quantita" min="1" max="1000" value="${param["quantita"]}">
                    <div class="spazio"></div>
                    <div class="bottoni">
                        <input type="submit" value="Aggiungi" id="aggiungi" name="aggiungi"/>
                        <!-- Se il veenditore non ha ancora oggetti in vendita non ha nemmeno la possibilità
                             di accedere alle funzionalità di eliminazione e modifica -->
                        <c:if test="${listaSize != 0}"> 
                            <input type="submit" value="Elimina" id="elimina" name="elimina"/>
                            <input type="submit" value="Modifica" id="modifica" name="modifica"/>
                        </c:if>
                        <input type="reset" value="Reset" id="reset"/> <!-- Bottone di reset -->
                    </div>
                    </fieldset>
                </form>   
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
 