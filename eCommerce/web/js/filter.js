/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
            $("#none").remove();
            
            if(listaAuto.length != 0)
            {
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
                    var x = document.createElement("IMG");
                    x.setAttribute("src", "Images/aggiungi.png");
                    x.setAttribute("alt", "Aggiungi");
                    x.setAttribute("width", 40);
                    x.setAttribute("height", 40);
                    a.appendChild(x);
                    td.appendChild(a);
                    tr.appendChild(td);

                    document.getElementById("tabella").appendChild(tr);
                } 
            }else{
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