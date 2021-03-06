<%-- 
    Document   : descrizione.jsp
    Created on : 25-apr-2016, 20.43.21
    Author     : selima
    jsp che viene mostrata per descrizione.html
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Descrizione Autontheline.com</title>
        <!-- Metainformazioni sulla pagina -->
        <meta charset="UTF-8">
        <meta name="keywords" content="Auto, Moto, Nuovo, Usato, Vendita, Autontheline.com, Occasioni, Convenienza">
        <meta name="description" content="Sito per il commercio online di auto e veicoli nuovi e usati">
        <meta name="author" content="Selima Curci">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
         <!-- Link al foglio di stile comune a tutte le pagine -->
        <link href="style.css" rel="stylesheet" type="text/css" media="screen">
        <!-- Icona del sito -->
        <link rel="icon" href="Images/auto.jpeg" type="image/jpeg" />
    </head>
    <body>
        <div id="page">
            <header id="header">
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
                        <li class="currentpage"> <a href="descrizione.html">Descrizione</a> </li>
                        <li> <a href="login.html">Login</a> </li>
                    </ul>
                </nav>
                
                <div class="menu">
                    <a href="login.html"  class="login">Login</a>
                </div>
            </header>

            <!-- Sommario con link interni alle sezioni-->
            <div id="sidebar1">
                <ul class="sezione">
                    <li class="categoria"> <a href="#nuovo">Veicoli nuovi</a> </li>
                    <li>
                        <ul class="sottosezioni">
                            <li><a href="#auto1">Auto</a></li>
                            <li><a href="#moto1">Moto</a></li>
                            <li><a href="#altro1">Altro</a></li>
                        </ul>
                    <li>
                    <li class="categoria"> <a href="#usato">Veicoli usati</a> </li>
                    <li>   
                        <ul class="sottosezioni">
                            <li><a href="#auto2">Auto</a></li>
                            <li><a href="#moto2">Moto</a></li>
                            <li><a href="#altro2">Altro</a></li>
                        </ul>
                    </li>    
                    <li class="categoria"> <a href="#accessori">Ricambi e accessori</a> </li>
                    <li>    
                        <ul class="sottosezioni">
                            <li><a href="#auto3">Auto</a></li>
                            <li><a href="#moto3">Moto</a></li>
                            <li><a href="#altro3">Altro</a></li>
                        </ul>
                    </li>
                    <li class="categoria"> <a href="#info">Informazioni</a> </li>
                </ul>
                
                 <!-- Storia della società --> 
                <p><em>AUTONTHELINE</em> nasce nel 1989 d.C. in Lituania, fondata da George Athelstan all'età di 19 anni.
                La società ha intrapreso un profondo cammino verso il successo in Europa per poi decidere che la Cina era più economica. Attualmete non è quotata in borsa 
                e non intende esserlo in futuro. Per chi fosse interessato a lavorare con noi invii il curriculum e forse 
                verrà preso in considerazione. Grazie per l'attenzione.</p>
               
            </div>

            <div id="central">
                <!-- Sommario per la risoluzione più bassa -->
                <ul id="sommario">
                    <li class="categoria"> <aside><a href="#nuovo">Veicoli nuovi</a></aside> </li>
                    <li class="categoria"> <aside><a href="#usato">Veicoli usati</a></aside> </li>    
                    <li class="categoria"> <aside><a href="#accessori">Ricambi e accessori</a></aside> </li>
                    <li class="categoria"> <aside><a href="#info">Informazioni</a></aside> </li>
                </ul>
                
                 <!-- Introduzione e descrizione al sito -->
                <div id="introduzione">
                <p>Vuoi vendere o comprare un auto? <strong>Autontheline.com</strong> sarà la tua risposta! Trova subito auto nuove e usate, moto, camper e veicoli commerciali.
                Interfaccia utente intuitiva ed efficacie per esperti e venditori alle prime armi. Potrai accedere facilmente sia
                come venditore che come cliente. I venditori potranno inserire i propri annunci in maniera semplice e completamente gratuita.
                Sistema di logistica interattivo e multifunzionale per mettere in contatto e soddisfare le 
                esigenze anche dei clienti più difficili e degli abitanti più sfortunati in termini di locazione geografica. 
                Non sai come spendere i tuoi soldi? Oppure sei stanco di avere le tasche vuote? Noi trasformeremo il 
                vecchio rottame di tuo nonno nel tappeto volante di qualcun altro, realizzando sogni irrealizzabili. 
                Solo su <strong>AUTONTHELINE</strong>! Nella sezione Auto Usate trovi migliaia di annunci con foto di auto usate sicure con garanzia di concessionari e 
                privati di tutte le marche in tutto il globo.
                </p>
                </div>
                
                <!-- Sezione veicoli nuovi-->
                <div id="sez_nuovo">
                    <h2 id='nuovo'>VEICOLI NUOVI</h2>
                    <p>Vendita veicoli nuovi e nuovissimi : auto, moto e 2-6-9-15 ruote per le persone più bisognose di viaggiare. 
                        Vedi un muro e vuoi salirci? La categoria <strong>ALTRO</strong> risponderà alle tue salite nel modo giusto. Vuoi verticalizzare
                    la tua vita in un piano orizzontale di strade catramate? Sgomma su <strong>AUTONTHELINE.COM</strong>, la via giusta per 
                    il viaggio sbagliato. Hai problemi di sonno? Su <strong>AUTONTHELINE.COM</strong> potrai svegliarti più tardi per andare a 
                    lavoro grazie alle moto più veloci del più veloce e-commerce veloce dell'alta velocità. Per te le migliori
                    offerte e occasioni rintracciabili sul pianeta, solo oggi, solo adesso, <em>"only for you"</em>, chiama ora, VIVI.</p>
                    <h3 id='auto1'>Auto</h3>
                    <p class="sottosezione">Cerca la tua nuova auto tra i tanti modelli offerti da autontheline. Ogni giorno migliaia di offerte ti 
                    sorprenderano al punto tale che ti convincerai a cambiare auto in qualsiasi momento. Potrai pagare attraverso un 
                    nuovo circuito di moneta virtuale che renderà più semplice e più veloce ogni trattazione messa in atto all'interno
                    della piattaforma. Per chiarire quasiasi dubbio chiamate e scriveteci senza alcun problema (ma non esagerate). 
                    Tra i migliori marchi dei sette mari offerti solo per voi:</p>
                    <div class="marche_auto">
                    <ul class="elenco">
                        <li>
                            <ul class="sottolista">
                                <li>Aixam</li> 
                                <li>Amphicar</li> 
                                <li>Borgward</li> 
                                <li>Casalini</li> 
                                <li>Chery</li> 
                                <li>Daimler</li> 
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>DRmotor</li> 
                                <li>Fisker</li> 
                                <li>IsoRivolta</li> 
                                <li>Lifan</li> 
                                <li>Melex</li> 
                                <li>PGO</li> 
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>Savel</li> 
                                <li>TownLife</li> 
                                <li>UAZ</li> 
                                <li>VAZ</li> 
                                <li>Zastova</li> 
                            </ul>
                        </li>
                    </ul>
                    </div>
                    
                    <h3 id='moto1'>Moto</h3>
                    <p class="sottosezione">Sogni le due ruote? Su autontheline potrai volare via sulla tua moto nuova. Potrai provare l'acqua 
                    di montagna e i brividi sulla pelle, i moscerini negli occhi e il mal di schiena. Potrai impennare dove 
                    e quando vuoi, ricordati però di atterrare ogni volta. Soddisfatti o soddisfatti. </p>
                    <div class="marche_moto">
                    <ul class="elenco">
                        <li>
                            <ul class="sottolista">
                                <li>Access</li>
                                <li>Adly</li>
                                <li>Baotian</li>
                                <li>ChinaBike</li>
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>D-RAD</li>
                                <li>EcoMobile</li>
                                <li>Horex</li>
                                <li>IFA</li>
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>Leonard</li>
                                <li>PGO</li>
                                <li>REX</li>
                                <li>XingFu</li>
                            </ul>
                        </li>
                    </ul>
                    </div>
                   
                    
                    <h3 id='altro1'>Altro</h3>
                    <p class="sottosezione">Hai le idee confuse? Cerca tra le stranezze proposte da autontheline. Troverai quel che cerchi ancora 
                    prima di sapere cosa stai cercando. In alcuni periodi dell'anno potresti riuscire ad acquistare un disco 
                    volante nuovo di zecca mai atterrato su un pianeta del nostro sistema solare. E tanto altro ancora. 
                    Per scoprire le novità più nuove del nuovo conosciuto segui la nostra pagina facebook : <em>www.facebook.org</em>.</p>
                </div>
                <div class="spazio"></div>
                <!-- Sezione veicoli usati-->
                <div id="sez_usato">
                    <h2 id='usato'>VEICOLI USATI</h2>
                    <p>Vendita veicoli usati. Cerca la tua auto d´occasione o vendi macchine private in tutto l'Universo conosciuto.
                    Hai il garage vuoto? Riempitelo. Hai il garage pieno? Svuotatelo. Hai pochi soldi? Non possiamo aiutarti. 
                    Ma se hai una macchina vecchia noi saremo la luce fuori dal tuo tunnel immenso e infinito, colmo di indecisione 
                    e sofferenza inaudita. Non avere paura di abbandonare il tuo catorcio per un nuovissimo catorcio peggiore del precedente. 
                    E' il momento, lasciati andare, segui l'onda dei milioni e milioni di milioni. Forse con euri in tasca riempi il 
                    garage, ma questa è solo una supposizione statistica. Per maggiori dettagli: <strong>STUDIA</strong>.  </p>
                    <h3 id='auto2'>Auto</h3>
                    <p class="sottosezione">Cerca la tua auto usata e riusata e strausata tra i tanti modelli offerti da furbi ma onestamente fraudolenti venditori. 
                    Tra i peggiori marchi del Bengala alle migliori offerte australiane:</p>
                    <div class="marche_auto">
                    <ul class="elenco">
                        <li>
                            <ul class="sottolista">
                                <li>Aixam</li> 
                                <li>Amphicar</li> 
                                <li>Borgward</li> 
                                <li>Casalini</li> 
                                <li>Chery</li> 
                                <li>Daimler</li> 
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>DRmotor</li>  
                                <li>Fisker</li> 
                                <li>IsoRivolta</li> 
                                <li>Lifan</li> 
                                <li>Melex</li> 
                                <li>PGO</li> 
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>Savel</li> 
                                <li>TownLife</li> 
                                <li>UAZ</li> 
                                <li>VAZ</li> 
                                <li>Zastova</li> 
                            </ul>
                        </li>
                    </ul>
                    </div>
                    
                    <h3 id='moto2'>Moto</h3>
                    <p class="sottosezione">Sogni le due ruote? Su autontheline vorresti volare via sulla tua moto nuova, ma non puoi; 
                    perciò accontentati di una usata perchè è la tua unica chance. Potrai provare 1/5 dei brivisdi della velocità, 
                    i moscerini negli occhi e il mal di testa. Potrai impennare dove e quando vuoi (più o meno), ricordati 
                    però di atterrare ogni volta. Soddisfatti o soddisfatti. Ci estraniamo da ogni responsabilità. </p>
                    <div class="marche_moto">
                    <ul class="elenco">
                        <li>
                            <ul class="sottolista">
                                <li>Access</li>
                                <li>Adly</li>
                                <li>Baotian</li>
                                <li>ChinaBike</li>
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>D-RAD</li>
                                <li>EcoMobile</li>
                                <li>Horex</li>
                                <li>IFA</li>
                            </ul>
                        </li>
                        <li>
                            <ul class="sottolista">
                                <li>Leonard</li>
                                <li>PGO</li>
                                <li>REX</li>
                                <li>XingFu</li>
                            </ul>
                        </li>
                    </ul>
                    </div>
                    
                    <h3 id='altro2'>Altro</h3>
                    <p class="sottosezione">Hai le idee confuse? Cerca tra le stranezze proposte da autontheline. Troverai quel che cerchi ancora 
                    prima di sapere cosa stai cercando. In alcuni periodi dell'anno potresti riuscire ad acquistare un disco 
                    volante nuovo di zecca mai atterrato su un pianeta del nostro sistema solare. E tanto altro ancora. 
                    Per scoprire le novità più nuove del nuovo conosciuto segui la nostra pagina facebook : <em>www.facebook.org</em>.</p>
                </div>
                <div class="spazio"></div>
                <!-- Sezione ricambi e accessori per veicoli -->
                <div id="sez_acessori">
                    <h2 id='accessori'>RICAMBI E ACCESSORI</h2>
                    <p>Non puoi più volare via per un problemino, per un problemone, per una problematica o per qualsiasi sia 
                        il tuo reale problema ? <strong>AUTONTHELINE.COM</strong> non può aiutarti. Siamo profondamente dispiaciuti, al massimo possiamo 
                    offrirti qualche ricambio originale, di concorrenza o contraffatto, a seconda del tuo budget. Non aspettarti 
                    miracoli, ma solo ulteriori problemi, che ovviamente richiederanno ulteriori spese dispersive, espansive e 
                    compulsive, IVA inclusa ed esentasse. Non emettiamo fatture ma solide realtà.</p>
                    <h3 id="auto3">Auto</h3>
                    <p class="sottosezione">Acessori e ricambi di qualità per la tua auto.</p>
                    <h3 id='moto3'>Moto</h3>
                    <p class="sottosezione">Acessori e ricambi di qualità per la tua moto.</p>
                    <h3 id='altro3'>Altro</h3>
                    <p class="sottosezione">Acessori e ricambi di qualità per i veicoli più disparati. Occasioni introvabili e rare a portata di click.</p>
                </div>
                
                 <a href="#header" id="su"></a> <!-- Link interno che permette di tornare a inizio pagina -->
            </div>

            <div class="clear"></div>
            
            <!-- Sezione informazioni aggiuntive e contatti -->
            <footer id="footer">
                <h3 id="info">Informazioni</h3>
                <p>Per qualsiasi informazione chiamate al numero verde <strong>800-900-313</strong> o scrivete a 
                   <strong>autontheline.vr6@gmail.com</strong>. Per qualsiasi lamentela rivolgersi all'ufficio reclami.
                </p>
       
                <div class="social">
                    <ul> 
                        <li id="facebook"><a href="https://www.facebook.com/">facebook</a></li>  
                        <li id="twitter"><a href="https://twitter.com/?lang=it">twitter</a></li>  
                        <li id="linkedin"><a href="https://it.linkedin.com/">linkedin</a></li> 
                    </ul>
                </div>
                 <div id="valida">
                    <a href="http://jigsaw.w3.org/css-validator/check/referer">
                    <img style="border:0;width:88px;height:31px"
                        src="http://jigsaw.w3.org/css-validator/images/vcss"
                        alt="CSS Valido!" />
                    </a>
                </div>
            </footer>
        </div>
    </body>
</html>
