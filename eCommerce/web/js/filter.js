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

$(document).ready(function ()
{
   $("#filtra").keyup(function()
    {  
        // Preleva il valore
         var pattern = $('#filtra').val(); 
       
        $.ajax(
        {
            url: "filter.json",
            data:{
              cmd: "search",
              text: pattern
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
            $('#tabella').empty();
        }
    }); 
});