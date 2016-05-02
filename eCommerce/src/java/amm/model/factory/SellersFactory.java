/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Seller;
import java.util.ArrayList;

/**
 *
 * @author selima
 */
/** Classe usata per popolare i venditori */
public class SellersFactory {
    /* Attributi */
    private static SellersFactory singleton;
    private  ArrayList<Seller> listaVenditori;
    
    /* Garantisce la presenza di una sola istanza della classe Factory all'interno dell'applicazione */
    public static SellersFactory getInstance() {
        if (singleton == null) {
            singleton = new SellersFactory();
        }
        return singleton;
    }

    /* Costruttore vuoto*/
    private SellersFactory() {

    }

     /** Restituisce la lista di tutti i venditori
     *  @return lista dei venditori
     */
    public ArrayList<Seller> getListaVenditori() {
        // In questo modo la lista viene creata una sola volta
        if(listaVenditori == null){ 
            listaVenditori = new ArrayList<>(); 
            
            // Venditore 1
            Seller venditore_1 = new Seller();
            venditore_1.setUsername("MarioRossi");
            venditore_1.setPassword("000");
            venditore_1.setNome("Mario");
            venditore_1.setCognome("Rossi");
            venditore_1.setId(0);
            venditore_1.setConto(AccountsFactory.getInstance().getAccountById(0));
            listaVenditori.add(venditore_1);

            // Venditore 2
            Seller venditore_2 = new Seller();
            venditore_2.setUsername("ElisaFarris");
            venditore_2.setPassword("111");
            venditore_2.setNome("Elisa");
            venditore_2.setCognome("Farris");
            venditore_2.setId(1);
            venditore_2.setConto(AccountsFactory.getInstance().getAccountById(1));
            listaVenditori.add(venditore_2);

            // Venditore 3
            Seller venditore_3 = new Seller();
            venditore_3.setUsername("LucianoLoi");
            venditore_3.setPassword("222");
            venditore_3.setNome("Luciano");
            venditore_3.setCognome("Loi");
            venditore_3.setId(2);
            venditore_3.setConto(AccountsFactory.getInstance().getAccountById(2));
            listaVenditori.add(venditore_3);
        }
        return listaVenditori;
    }
    
    /** Restiuisce l'utente avente l’identificatore passato per parametro 
     *  @return  restiuisce l'utente avente l’identificatore passato per parametro 
     */
    public Seller getSellerById(int id){
        for(Seller v : getListaVenditori()){
            if(v.getId() == id)
                return v;
        }
        return null; // Se l'oggetto non è presente nella lista allora restituisco null
    }    
}
