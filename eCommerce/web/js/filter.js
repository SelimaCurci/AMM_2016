/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


 /*jQuery(document).ready(function($){
    $('input#filtra').on('keyup',function(){
        var pattern = $('input#filtra').val(); //prendiamo il valore della nostro campo input
        document.getElementById("avviso").innerHTML="Hai scritto " + pattern;
        
        $.ajax({
            url: "filter.json",
            data: {
                q: pattern
            },
            dataType: 'json',
            success: function (data, state) {
                 popola(data);
            },
            error: function (data, state) {
                //alert("Chiamata fallita!!!");
            }
        });
    });
});

function popola(data){
    
    
}*/

/*$(document).ready(function ()
{
   $("#filtra").keyup(function()
    {  
        // Preleva il valore
         var text = $("#filtra").val(); 
       
        $.ajax(
        {
            url: "filter.json",
            data:{
              cmd: "search",
              text: text
            },
            dataType: 'json',
            success : function(data, state){
                popolaTabella(data);
            },
            error : function(data, state){
            }
        });
        
       // Funzione che viene richiamata in caso di successo
        function popolaTabella(listaAuto)
        {
            // Cancella la lista
            $("tr:not(.intestazione)").remove();
            
            
            // Per ogni alunno trovato dal database
            for(var oggetto in listaAuto)
            {
                  document.writeln("Nome " + oggetto.nomeAuto +  "<br/>");
               /* var tr = document.createElement("tr");
                
                var td = document.createElement("td");
                var txt = document.createTextNode(oggetto.nomeAuto);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var x = document.createElement("IMG");
                x.setAttribute("src", oggetto.url);
                x.setAttribute("alt", "Foto di " + oggetto.nome);
                td.appendChild(x);
                tr.appendChild(td);

                var td = document.createElement("td");
                var txt = document.createTextNode(oggetto.quantita);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var txt = document.createTextNode(oggetto.prezzo);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var a = document.createElement("A");
                a.setAttribute("href", "cliente.html");
                var txt = document.createTextNode("Aggiungi");
                a.appendChild(txt);
                td.appendChild(a);
                tr.appendChild(td);


                document.getElementById("tabella").appendChild(tr);
            }
        }
    }); 
});*/



$(document).ready(function ()
{
   $("#filtra").keyup(function()
    {  
        // Preleva il valore
        var text = $("#filtra").val();
       
        $.ajax(
        {
            url: "filter.json",
            data:{
              cmd: "search",
              text: text
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
            // Cancella la lista
            $("tr:not(.intestazione)").remove();
            
            
            // Per ogni alunno trovato dal database
            for(var oggetto in listaAuto)
            {
                var tr = document.createElement("tr");
                
                var td = document.createElement("td");
                var txt = document.createTextNode(listaAuto[oggetto].nomeAuto);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var x = document.createElement("IMG");
                x.setAttribute("src", listaAuto[oggetto].urlImmagine);
                x.setAttribute("alt", "Foto di " + listaAuto[oggetto].nomeAuto);
                td.appendChild(x);
                tr.appendChild(td);

                var td = document.createElement("td");
                var txt = document.createTextNode(listaAuto[oggetto].quantita);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var txt = document.createTextNode(listaAuto[oggetto].prezzoUnitario);
                td.appendChild(txt);
                tr.appendChild(td);

                var td = document.createElement("td");
                var a = document.createElement("A");
                a.setAttribute("href", "cliente.html?idAuto="+ listaAuto[oggetto].id);
                var txt = document.createTextNode("Aggiungi");
                a.appendChild(txt);
                td.appendChild(a);
                tr.appendChild(td);


                document.getElementById("tabella").appendChild(tr);
            } 
             
            
        }
    }); 
});