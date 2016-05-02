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
/** Rappresenta il generico utente */
public abstract class User {
    /* Attributi */
    private int id;
    private String nome;
    private String cognome;
    private String username;
    private String password;
    Account contoUtente;
    
    
    /** Costruttore vuoto*/
    public User()
    {
        this.id = 0;
        this.nome = "";
        this.cognome = "";
        this.username="";
        this.password="";
        this.contoUtente = new Account();
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
    public Account getConto() {
        return contoUtente;
    }
    
    /** Setta  il conto dell'utente al conto passata come parametro
     *  @param conto conto da settare
     */
    public void setConto(Account conto) {
        this.contoUtente =  conto;
    }
    
    /** Restituisce il saldo dell'utente
     *  @return saldo dell'utente
     */
    public double getSaldoUtente() {
        return contoUtente.getConto();
    }

    /** Versa nel conto dell'utente la cifra passata come parametro
     *  @param versamento cifra da versare
     */
    public void versa(double versamento) {
        this.contoUtente.versa(versamento);
    }
    
    /** Se possibile, preleva dal conto dell'utente la cifra passata come parametro
     *  @param prelievo cifra da prelevare
     */
    public void preleva(double prelievo) {
        this.contoUtente.preleva(prelievo);
    } 
}
