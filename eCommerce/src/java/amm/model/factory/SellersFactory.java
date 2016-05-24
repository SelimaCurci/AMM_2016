/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Seller;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author selima
 */
/** Classe usata per popolare i venditori */
public class SellersFactory {
    /* Attributi */
    private static SellersFactory singleton;
    String connectionString; 
    
    /** Metodo set della la stringa utilizzata per la connessione al database 
     *  @param path del db
     */
    public void setConnectionString(String s){
	this.connectionString = s;
    }
    
    /** Metodo get della la stringa utilizzata per la connessione al database 
     *  @return path del db
     */
    public String getConnectionString(){
            return this.connectionString;
    } 
    
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
        ArrayList<Seller> listaVenditori = new ArrayList<>();
        
        /* Apro la connessione al db e creo lo Statement usando un try with-resources, in questo
            modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            Statement stmt = conn.createStatement()) {
            // Definisco la query per ottenere l'elenco di tutti i venditori
            String sql = "SELECT * FROM Seller";
           
            ResultSet set = stmt.executeQuery(sql);
            /* Scorro tutti i risultati ottenuti aggiungendoli alla lista che poi sarà restituita al chiamante */
            while (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String username = set.getString("username");
                String password = set.getString("password");
                int id_conto = set.getInt("id_conto");
                Seller seller = new Seller(id, nome, cognome, username, password, id_conto);
                
                listaVenditori.add(seller);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(SellersFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return listaVenditori;
    }
    
    /** Restiuisce l'utente avente l’identificatore passato per parametro 
     *  @return  restiuisce l'utente avente l’identificatore passato per parametro 
     */
    public Seller getSellerById(int id){
        // Definisco la query per trovare un venditore dato il suo id
        String query = "select * from Seller where id = ?";
        
         /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(query)){
            // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();
            
            if(set.next()) {
                Seller seller = new Seller();
                seller.setId(set.getInt("id"));
                seller.setNome(set.getString("nome"));
                seller.setCognome(set.getString("cognome"));
                seller.setUsername(set.getString("username"));
                seller.setPassword(set.getString("password"));
                seller.setIdConto(set.getInt("id_conto"));
                
                return seller; 
            }
        } catch(SQLException ex) {
            Logger.getLogger(SellersFactory.class.getName()).
            log(Level.SEVERE, null, ex);  
        }
       
        return null;
    }    
    
    /** Restiuisce l'utente avente nome utente e password passati per parametro
     *  @return  restiuisce l'utente avente nome utente e password passati per parametro
     */
    public Seller findSeller(String username, String password){
        Seller seller = null;
        // Definisco la query per trovare un venditore dati username e password
        String query = "select * from Seller where password = ? and username = ?";
        
         /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(query)){
            // Si associano valori e posizioni di placeholder
            stmt.setString(1, password);
            stmt.setString(2, username);
            
            ResultSet set = stmt.executeQuery();
            /* Se l'esecuzione ha prodotto un risultato, preparo l'oggetto venditore da restituire */
            if(set.next()) {
                seller = new Seller();
                seller.setId(set.getInt("id"));
                seller.setNome(set.getString("nome"));
                seller.setCognome(set.getString("cognome"));
                seller.setUsername(set.getString("username"));
                seller.setPassword(set.getString("password"));
                seller.setIdConto(set.getInt("id_conto"));
            }
        } catch(SQLException ex){
            Logger.getLogger(SellersFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
       
        return seller;
    }  
}
