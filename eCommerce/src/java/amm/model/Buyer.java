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
    
    /* Costruttore vuoto */
    public Buyer(){
        super();
    }

    /** Se il cliente ha abbastanza soldi allora gli viene scalato un importo pari al prezzo unitario del veicolo
     *  selezionato
     *  @param auto oggetto che l'utente desidera comprare
     */
    public boolean compra(CarSale auto) {
        return this.contoUtente.preleva(auto.getPrezzoUnitario());
    }
}
