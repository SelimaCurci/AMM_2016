/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Account;
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
/** Classe per il popolamento dei conti */
public class AccountsFactory {
    /* Attributi */
    private static AccountsFactory singleton;
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
    
    /** Garantisce la presenza di una sola istanza della classe Factory all'interno dell'applicazione */
    public static AccountsFactory getInstance() {
        if (singleton == null) {
            singleton = new AccountsFactory();
        }
        return singleton;
    }

    /** Costruttore vuoto */
    private AccountsFactory() {

    }

    /** Restituisce la lista di tutti i conti (inutile)
     *  @return lista dei conti
     */
    public ArrayList<Account> getContiList() {
        ArrayList<Account> listaConti = new ArrayList<>();
        
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            Statement stmt = conn.createStatement()) {
            // Definisco la query per ottenere l'elenco di tutti i conti
            String sql = "SELECT * FROM Account";
            ResultSet set = stmt.executeQuery(sql);
            while (set.next()) {
                int idConto = set.getInt("id");
                double saldo = set.getDouble("saldo");
                Account account = new Account(idConto, saldo);
                
                listaConti.add(account);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(AccountsFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return listaConti;
    } 
    
    /** Restiuisce il conto avente l’identificatore passato per parametro 
     *  @return  restiuisce il conto avente l’identificatore passato per parametro 
     */
    public Account getAccountById(int id){
        Account account = null;
        /* Definisco la query per restituire il conto dato l'id, essendo parametrica inserisco un ? al posto dell'id 
           del conto, e poi lo setto dopo*/
        String sql = "SELECT * FROM Account WHERE id = ?";
        
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
            // Eseguo la query
            ResultSet set = stmt.executeQuery();
            /* Se l'esecuzione ha prodotto un risultato, preparo il conto da restituire */
            if(set.next()) {
                int idConto = set.getInt("id");
                double saldo = set.getDouble("saldo");
                account = new Account(idConto, saldo);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(AccountsFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        /* Nel caso non fosse stata trovata nessuna corrispondenza a questo punto viene restituito null */
        return account;
    }       
}
