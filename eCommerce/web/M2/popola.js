/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function mostra(event){
    document.getElementById("avviso").innerHTML = "Devi compilare tutti i campi";
    var o1 = new Oggetto("Aixam GTO", "Images/Aixam_GTO.png", 1, 12650);
    var o2 = new Oggetto("BorgWard bx7", "Images/borgward_bx7.jpg", 4, 21500);
    var o3 = new Oggetto("Chery Tiggo", "Images/Chery_tiggo.jpg", 1, 15000);
    var o4 = new Oggetto("ChinaBike Cross125", "Images/chinabike_cross125.jpg", 6, 750);
    var array = new Array(o1, o2, o3, o4);
    for(var i = 0; i<4; i++){
        var tr = document.createElement("tr");
        var oggetto = array[i];
        
        var td = document.createElement("td");
        var txt = document.createTextNode(oggetto.nome);
        td.appendChild(txt);
        tr.appendChild(td);
        
        var td = document.createElement("td");
        var x = document.createElement("IMG");
        x.setAttribute("src", oggetto.url);
        x.setAttribute("alt", "Foto di " + oggetto.nome);
        td.appendChild(x);
        tr.appendChild(td);
        
        var td = document.createElement("td");
        var txt = document.createTextNode(oggetto.q);
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
    
    /*
     *for(var i = 0; i<4; i++){
        var tr = document.createElement("tr");
        var oggetto = array[i];
        for(var j = 0; j<5; j++){            
             var td = document.createElement("td");
             var txt = document.createTextNode("Prova");
             td.appendChild(txt);
             tr.appendChild(td);
        }
        document.getElementById("tabella").appendChild(tr);
    }*/
    
}

function Oggetto(nome, url, q, prezzo){
    this.nome=nome;
    this.url=url;
    this.q=q;
    this.prezzo=prezzo; 
}

