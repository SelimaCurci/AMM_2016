/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model;

import amm.model.factory.CarSaleFactory;
import java.util.ArrayList;

/**
 *
 * @author selima
 */
/** Classe che rappresenta un venditore */
public class Seller extends User{
    /* Attributi */
    /* Costruttore vuoto*/
    public Seller(){
        super();
    }
    
    /* Metodi */

    /** Restituisce la lista di oggetti messi in vendita dal venditore
     *  @return oggetti messi in vendita dal venditore
     */
    public ArrayList<CarSale> getAutoInVendita() {
        return CarSaleFactory.getInstance().getAutoSaleBySeller(getId());
    }
    
    /** All'atto della vendita di un oggetto, dopo aver verificato il saldo dell'utente, il corrispettivo viene 
     *  versato al venditore  
     *  @param auto oggetto che l'utente desidera comprare
    */
    public void vendi(CarSale auto) {
        this.contoUtente.versa(auto.getPrezzoUnitario());
    }
}
