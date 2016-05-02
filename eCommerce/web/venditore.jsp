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
              <!-- Titolo usato per la risoluzione piccola e intermedia -->
              <div id="logout"><a href="logout.html">Logout</a></div>    
              
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
                <p>Inserisci nel form tutte le informazioni riguardo il veicolo che desideri vendere.</p>

                <!-- Form per l’inserimento di un nuovo oggetto all’interno della pagina venditore.html -->
                <form action="venditore.html" method="post">
                    <fieldset>
                    <label for="nomeoggetto">Nome</label>
                    <input type="text" name="nomeoggetto" id="nomeoggetto" value="" />
                    <div class="spazio"></div>
                    <label for="urlimmagine">URL immmagine</label>
                    <input type="text" name="urlimmagine" id="urlimmagine" value="" />
                    <div class="spazio"></div>
                    <!-- Per la descrizione uso una text area in quanto ci si aspetta un testo pseudo-lungo -->
                    <label for="descrizione">Descrizione</label>
                    <textarea rows="4" cols="20" name="descrizione" id="descrizione"></textarea>
                    <div class="spazio"></div>
                    <!-- L'attributo step permette di inserire numeri decimali -->
                    <label for="prezzo">Prezzo per unità</label>
                    <input type="number" step="0.01" min="5" name="prezzo" id="prezzo" >
                    <div class="spazio"></div>
                    <label for="quantita">Quantità</label>
                    <input type="number" name="quantita" id="quantita" min="1" max="1000">
     
                    <input type="submit" value="Invia" id="submit" name="submit"/>
                    <input type="reset" value="Reset" id="reset"/> <!-- Bottone di reset -->
                    </fieldset>
                </form>   
            </div>
            
            <!-- Icludo la jsp che contiene il footer (comune a tutte le pagine) -->
            <jsp:include page="footer.jsp" />
            
        </div>
    </body>
</html>
 