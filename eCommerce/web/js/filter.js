/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* Funzione  per l’evento ready del document*/
$(document).ready(function ()
{
   // Aggancio l'event handler alla pressione di un tasto nella text field con etichetta filtra
   $("#filtra").keyup(function()
    {  
        // Preleva il valore inserito nel campo input
        var q = $("#filtra").val();
       
        // Congigurazione della richiesta ajax
        $.ajax(
        {
            url: "filter.json",
            data:{
              cmd: "search",
              q: q
            },
            dataType: 'json',
            success : function(data, state){
                popolaTabells(data);
            },
            error : function(data, state){
            }
        });
        
       // Funzione che viene richiamata in caso di successo
        function popolaTabells(listaAuto)
        {
            /* Cancella le righe della tabella (ma non l'intestazione) e un eventuale messaggio scritto precedentemente */
            $("tr:not(.intestazione)").remove();
            $("#none").remove();
            
            /* Se ci sono oggetti che corrispondono alla ricerca allora creo una riga della tabella per ciascuno di essi */
            if(listaAuto.length != 0)
            {
                // Per ogni veicolo trovato dal database
                for(var oggetto in listaAuto)
                {
                    // creo la rifga della tabella
                    var tr = document.createElement("tr"); 
                    // Colonna per il nome dell'auto
                    var td = document.createElement("td");
                    var txt = document.createTextNode(listaAuto[oggetto].nomeAuto);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    // Colonna per la foto dell'auto
                    var td = document.createElement("td");
                    var x = document.createElement("IMG");
                    x.setAttribute("src", listaAuto[oggetto].urlImmagine);
                    x.setAttribute("alt", "Foto di " + listaAuto[oggetto].nomeAuto);
                    td.appendChild(x);
                    tr.appendChild(td);
                    // Colonna per la quantità dell'auto
                    var td = document.createElement("td");
                    var txt = document.createTextNode(listaAuto[oggetto].quantita);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    // Colonna per il prezzo dell'auto
                    var td = document.createElement("td");
                    var txt = document.createTextNode(listaAuto[oggetto].prezzoUnitario);
                    td.appendChild(txt);
                    tr.appendChild(td);
                    // Colonna per il link utilizzato er l'acquisto
                    var td = document.createElement("td");
                    var a = document.createElement("A");
                    a.setAttribute("href", "cliente.html?idAuto="+ listaAuto[oggetto].id);
                    var x = document.createElement("IMG");
                    x.setAttribute("src", "Images/aggiungi.png");
                    x.setAttribute("alt", "Aggiungi");
                    x.setAttribute("width", 40);
                    x.setAttribute("height", 40);
                    a.appendChild(x);
                    td.appendChild(a);
                    tr.appendChild(td);
                    // Aggiungo la riga appena creata come figlio della tabella
                    document.getElementById("tabella").appendChild(tr);
                } 
            }else{
                    /* Nel caso in cui la lista è vuota mosto un messaggio all'utente */
                    var div = document.getElementById("noelements")
                    var p = document.createElement("p");
                    var txt = document.createTextNode("Non ci sono oggetti che corrispondono alla tua ricerca");
                    p.appendChild(txt);
                    p.setAttribute("id", "none");
                    div.appendChild(p);
            } 
            
        }
    }); 
});