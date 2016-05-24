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
/** Classe che rapresenta un veicolo messo in vendita .*/
public class CarSale {
    /* Attributi */
    private int id;
    private int idVenditore; // id del venditore che ha messo in vendita l'oggetto
    private String nomeAuto;
    private String urlImmagine;
    private String descrizione;
    private double prezzoUnitario;
    private int quantita; // numero esemplari disponibili
    
    /** Costruttore vuoto */
    public CarSale(){
         this.id = 0;
         this.idVenditore = 0;
         this.nomeAuto = "";
         this.urlImmagine = "";
         this.descrizione = "";
         this.prezzoUnitario = 0.0;
         this.quantita = 0;
    }
    /** Costruttore alternativo */
    public CarSale(int id, int idVenditore, String nome, String url, String text, double prezzo, int qta){
         this.id = id;
         this.idVenditore = idVenditore;
         this.nomeAuto = nome;
         this.urlImmagine = url;
         this.descrizione = text;
         this.prezzoUnitario = prezzo;
         this.quantita = qta;
    }
    
    /** Restituisce l'id dell'oggeeto
     *  @return the id
     */
    public int getId() {
        return id;
    }

    /** Setta l'id dell'oggetto al valore specificato come parametro
     *  @param id l'id da settare
     */
    public void setId(int id) {
        this.id = id;
    }
    
    /** Restituisce l'id del venditore che ha messo in vendita l'oggetto
     *  @return idVenditore
     */
    public int getIdVenditore() {
        return idVenditore;
    }

    /** Setta l'id del venditore al valore specificato come parametro
     *  @param idVenditore l'id del venditore da settare
     */
    public void setIdVenditore(int idVenditore) {
        this.idVenditore = idVenditore;
    }
    
    /** Restituisce il nome dell'oggetto
     *  @return nome dell'oggetto
     */
    public String getNomeAuto() {
        return nomeAuto;
    }

    /** Setta il nome dell'oggetto alla stringa specificata come parametro
     *  @param nome nome da settare
     */
    public void setNomeAuto(String nome) {
        this.nomeAuto = nome;
    }
    
    /** Restitusce la url dell'imagine che rappresenta l'oggetto
     *  @return url dell'immagine
     */
    public String getUrlImmagine() {
        return urlImmagine;
    }

    /** Setta la url dell'oggetto alla stringa specificata come parametro
     *  @param url url da settare
     */
    public void setUrlImmagine(String url) {
        this.urlImmagine = url;
    }
    
    /** Restituisce la descrizione dell'oggetto
     *  @return descrizione dell'oggetto
     */
    public String getDescrizione() {
        return descrizione;
    }

    /** Setta la descrizione dell'oggetto alla stringa specificata come parametro
     *  @param text descrizione da settare
     */
    public void setDescrizione(String text) {
        this.descrizione = text;
    }
    
    /** Restituisce il prezzo unitario dell'oggetto
     *  @return prezzo unitario dell'oggetto
     */
    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    /** Setta il prezzo unitario dell'oggetto al valore specificato come parametro
     * @param prezzo prezzo da settare
     */
    public void setPrezzoUnitario(double prezzo) {
        this.prezzoUnitario = prezzo;
    }
    
    /** Restituisce il numero di esemplari disponibili dell'ogetto
    *   @return quantità disponibile dell'oggetto
    */
    public int getQuantita() {
        return quantita;
    }

    /** Setta numero di esemplari disponibili dell'oggetto al valore specificato come parametro
     *  @param qta quantità disponibile da settare
     */
    public void setQuantita(int qta) {
        this.quantita = qta;
    } 
    
    @Override
    public String toString(){
       return nomeAuto;
    }
}
