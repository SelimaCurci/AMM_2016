/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model;

/**
 *
 * @author selima
 */
/** Classe che rappresenta un cliente */
public class Buyer extends User {
    /* Attributi */
    
    /** Costruttore  */
    public Buyer(int id, String nome, String cognome, String username, String password, int idConto){
        super(id, nome, cognome, username, password, idConto);
    }

    public Buyer() {
    }

    /** Se il cliente ha abbastanza soldi allora gli viene scalato un importo pari al prezzo unitario del veicolo
     *  selezionato
     *  @param auto oggetto che l'utente desidera comprare
     */
    /*public boolean compra(CarSale auto) {
        Account contoUtente = AccountsFactory.getInstance().getAccountById(getIdConto());
        return contoUtente.preleva(auto.getPrezzoUnitario());
    }*/
}
