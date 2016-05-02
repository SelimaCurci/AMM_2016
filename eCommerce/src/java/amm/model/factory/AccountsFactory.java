/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Account;
import java.util.ArrayList;

/**
 *
 * @author selima
 */
/** Classe per il popolamento dei conti */
public class AccountsFactory {
    /* Attributi */
    private static AccountsFactory singleton;
    private ArrayList<Account> listaConti;
    
    /* Garantisce la presenza di una sola istanza della classe Factory all'interno dell'applicazione */
    public static AccountsFactory getInstance() {
        if (singleton == null) {
            singleton = new AccountsFactory();
        }
        return singleton;
    }

    /* Costruttore vuoto */
    private AccountsFactory() {

    }

    /** Restituisce la lista di tutti i conti
     *  @return lista dei conti
     */
    public ArrayList<Account> getContiList() {
        // In questo modo la lista viene creata una sola volta
        if(listaConti == null){
            listaConti = new ArrayList<>();
            
            Account conto_1 = new Account();
            conto_1.versa(100.0);
            conto_1.setId(0);
            listaConti.add(conto_1);

            Account conto_2 = new Account();
            conto_2.versa(230.0);
            conto_2.setId(1);
            listaConti.add(conto_2);

            Account conto_3 = new Account();
            conto_3.versa(150.0);
            conto_3.setId(2);
            listaConti.add(conto_3);

            Account conto_4 = new Account();
            conto_4.versa(1000000.0);
            conto_4.setId(3);
            listaConti.add(conto_4);

            Account conto_5 = new Account();
            conto_5.setId(4);
            listaConti.add(conto_5);
        }
        
        return listaConti;
    } 
    
    /** Restiuisce l'utente avente l’identificatore passato per parametro 
     *  @return  restiuisce l'utente avente l’identificatore passato per parametro 
     */
    public Account getAccountById(int id){
        for(Account c : getContiList()){
            if(c.getId() == id)
                return c;
        }
        return null; // Se l'oggetto non è presente nella lista allora restituisco null
    }        
}
