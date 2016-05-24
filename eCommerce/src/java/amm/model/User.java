/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model;

import amm.model.factory.AccountsFactory;

/**
 *
 * @author selima
 */
/** Rappresenta il generico utente */
public abstract class User {
    /* Attributi */
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    private int idConto;
    
    
    /** Costruttore vuoto*/
    public User(){
    }
    
    /** Costruttore*/
    public User(int id, String nome, String cognome, String username, String password, int idConto)
    {
        this.id =id;
        this.nome = nome;
        this.cognome = cognome;
        this.username= username;
        this.password = password;
        this.idConto = idConto;
    }
    
    /** Restituisce l'id dell'utente
     *  @return id dell'utente
     */
    public int getId() {
        return id;
    }

    /** Setta l'id dell'utente al valore passato come parametro
     *  @param id id da settare
     */
    public void setId(int id) {
        this.id = id;
    }

    /** Restituisce il nome dell'utente
     *  @return nome dell'utente
     */
    public String getNome() {
        return nome;
    }

    /** Setta il nome dell'utente alla stringa passata come parametro
     *  @param nome nome da settare
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /** Restituisce il cognome dell'utente
     *  @return cognome dell'utente
     */
    public String getCognome() {
        return cognome;
    }

    /** Setta il cognome dell'utente alla stringa passata come parametro
     *  @param cognome cognome da settare
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /** Restituisce l'username dell'utente
     *  @return username dell'utente
     */
    public String getUsername() {
        return username;
    }

    /** Setta l'username dell'utente alla stringa passata come parametro
     *  @param username username da settare
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Restituisce la password dell'utente
     *  @return password dell'utente
     */
    public String getPassword() {
        return password;
    }
    
    /** Setta la password dell'utente alla stringa passata come parametro
     *  @param password password da settare
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Restituisce il conto dell'utente
     *  @return conto dell'utente
     */
    public int getIdConto() {
        return idConto;
    }
    
    /** Setta  il conto dell'utente al conto passata come parametro
     *  @param conto conto da settare
     */
    public void setIdConto(int idConto) {
        this.idConto =  idConto;
    }
    
    /** Restituisce il saldo dell'utente
     *  @return saldo dell'utente
     */
    public double getSaldoUtente() {
        Account contoUtente = AccountsFactory.getInstance().getAccountById(idConto);
        return contoUtente.getSaldo();
    }
}
