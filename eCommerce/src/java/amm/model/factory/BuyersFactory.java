/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Buyer;
import java.util.ArrayList;

/**
 *
 * @author selima
 */
/** Classe per il popolamento dei clienti */
public class BuyersFactory {
    /* Attributi */
    private static BuyersFactory singleton;
    private  ArrayList<Buyer> listaClienti;
    
    /* Garantisce la presenza di una sola istanza della classe Factory all'interno dell'applicazione */
    public static BuyersFactory getInstance() {
        if (singleton == null) {
            singleton = new BuyersFactory();
        }
        return singleton;
    }

    /* Costruttore vuoto*/
    private BuyersFactory() {

    }

    /** Restituisce la lista di tutti i clienti
     *  @return lista dei clienti
     */
    public ArrayList<Buyer> getListaClienti() {
        // In questo modo la lista viene creata una sola volta
        if(listaClienti == null){ 
            listaClienti = new ArrayList<>(); 

            // Cliente 1
            Buyer cliente_1 = new Buyer();
            cliente_1.setUsername("SelimaCurci");
            cliente_1.setPassword("333");
            cliente_1.setNome("Selima");
            cliente_1.setCognome("Curci");
            cliente_1.setId(3);
            cliente_1.setConto(AccountsFactory.getInstance().getAccountById(3));
            listaClienti.add(cliente_1);

            // Cliente 2
            Buyer cliente_2 = new Buyer();
            cliente_2.setUsername("GiuliaLisci");
            cliente_2.setPassword("444");
            cliente_2.setNome("Giulia");
            cliente_2.setCognome("Lisci");
            cliente_2.setId(4);
            cliente_2.setConto(AccountsFactory.getInstance().getAccountById(4));
            listaClienti.add(cliente_2);
        }
        return listaClienti;
    }
    
    /** Restiuisce l'utente avente l’identificatore passato per parametro 
     *  @return  restiuisce l'utente avente l’identificatore passato per parametro 
     */
    public Buyer getBuyerById(int id){
        for(Buyer c : getListaClienti()){
            if(c.getId() == id)
                return c;
        }
        return null; // Se l'oggetto non è presente nella lista allora restituisco null
    }    
}
