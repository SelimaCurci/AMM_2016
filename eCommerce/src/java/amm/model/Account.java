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
/** Classe che rappresenta il conto degli utenti, sia clienti che venditori */
public class Account {
    /* Attributi */
    private int id; // id del conto
    double saldo; // saldo conto
    
    /** Costruttore vuoto */
    public Account(){
        this.id = 0;
        this.saldo = 0.0;
    }
    /** Costruttore */
    public Account(int id, double saldoIniziale){
        this.id = id;
        this.saldo = saldoIniziale;
    }
    
    /** Restituisce l'id del conto
     *  @return id del conto
     */
    public int getId() {
        return id;
    }

    /** Setta l'id del conto all'intero passato come parametro
     *  @param id id da settare
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Restituisce il saldo del conto
     *  @return saldo corrente del conto
     */
    public double getConto() {
        return saldo;
    }

    /** Versa la cifra specificata nel conto
     *  @param vesamento cifra da versare
     */
    public void versa(double versamento) {
        this.saldo += versamento;
    }
    
    /** Se il saldo corrente è sufficiente prelieva la cifra specificata come parametro e restituisce true,
     * in caso contrario restituisce false
     *  @param prelievo cifra da prelevare
     */
    public boolean preleva(double prelievo) {
        if(this.saldo >= prelievo){
            this.saldo -= prelievo;
            return true;
        }else
        {
            return false;
        }
    }
}


