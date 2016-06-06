/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.CarSale;
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
/** Classe usata per popolare gli oggetti messi in vendita */
public class CarSaleFactory {
    /* Attributi */
    private static CarSaleFactory singleton;   
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
    public static CarSaleFactory getInstance() {
        if (singleton == null) {
            singleton = new CarSaleFactory();
        }
        return singleton;
    }

    /* Costruttore vuoto*/
    private CarSaleFactory() {

    }
    
    /** Restituisce la lista di tutti gli oggetti
     *  @return lista degli oggetti
     */
    public ArrayList<CarSale> getAutoSaleList() {
        ArrayList<CarSale> listaAuto = new ArrayList<>();
        
        /* Apro la connessione al db e creo lo Statement usando un try with-resources, in questo
            modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            Statement stmt = conn.createStatement()) {
            // Definisco la query per ottenere l'elenco di tutti gli oggetti in vendita
            String sql = "SELECT * FROM CarSale";
            
            ResultSet set = stmt.executeQuery(sql);
            /* Scorro tutti i risultati ottenuti aggiungendoli alla lista che poi sarà restituita al chiamante */
            while (set.next()) {
                int id = set.getInt("id");
                String nomeAuto = set.getString("nomeAuto");
                String urlImmagine = set.getString("urlImmagine");
                String descrizione = set.getString("descrizione");
                double prezzoUnitario = set.getDouble("prezzoUnitario");
                int quantita = set.getInt("quantita");
                int id_venditore = set.getInt("id_venditore");
                CarSale car = new CarSale(id, id_venditore, nomeAuto, urlImmagine, descrizione, prezzoUnitario, quantita);
                
                listaAuto.add(car);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return listaAuto;
    }  
    
    /** Restiuisce l’oggetto avente l’identificatore passato per parametro 
     *  @return  restiuisce l’oggetto avente l’identificatore passato per parametro 
     */
    public CarSale getAutoSaleById(int id){
        CarSale car = null;
        // Definisco la query parametrica per selezionare un determinato veicolo dato l'id
        String sql = "SELECT * FROM CarSale WHERE id = ?";
        
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
           
            ResultSet set = stmt.executeQuery();
            /* Se ho ottenuto un risultato allora preparo l'oggetto da restituire al chiamante */
            if(set.next()) {
                String nomeAuto = set.getString("nomeAuto");
                String urlImmagine = set.getString("urlImmagine");
                String descrizione = set.getString("descrizione");
                double prezzoUnitario = set.getDouble("prezzoUnitario");
                int quantita = set.getInt("quantita");
                int id_venditore = set.getInt("id_venditore");
                car = new CarSale(id, id_venditore, nomeAuto, urlImmagine, descrizione, prezzoUnitario, quantita);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return car;
    }
    
    /** Restiuisce gli oggetti avente venditore passato per parametro 
     *  @return  restiuisce gli oggetti avente venditore passato per parametro 
     */
    public ArrayList<CarSale> getAutoSaleBySeller(int idVenditore){
        ArrayList<CarSale> lista = new ArrayList<>();
        
        /* Prima di eseguire la query per restituire la lista di veicoli venduti da un venditore verifico che l'id
           passato come parametro sia corretto */
        if(SellersFactory.getInstance().getSellerById(idVenditore) != null){
            // Definisco la query per selezionare tutti i veicoli messi in vendita da un determinato venditore 
            String sql = "SELECT * FROM CarSale WHERE id_venditore = ?";
            
            /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
               modo sono sicura che le risorse saranno chiuse in ogni caso */
            try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
                PreparedStatement stmt = conn.prepareStatement(sql)) {
                // Si associano valori e posizioni di placeholder
                stmt.setInt(1, idVenditore);
               
                ResultSet set = stmt.executeQuery();
                /* Scorro tutti i risultati ottenuti aggiungendoli alla lista che poi sarà restituita al chiamante */
                while (set.next()) {
                    int id = set.getInt("id");
                    String nomeAuto = set.getString("nomeAuto");
                    String urlImmagine = set.getString("urlImmagine");
                    String descrizione = set.getString("descrizione");
                    double prezzoUnitario = set.getDouble("prezzoUnitario");
                    int quantita = set.getInt("quantita");
                    int id_venditore = set.getInt("id_venditore");
                    CarSale car = new CarSale(id, id_venditore, nomeAuto, urlImmagine, descrizione, prezzoUnitario, quantita);

                    lista.add(car);
                }
            } catch (SQLException ex) {
                // nel caso la query fallisca (p.e. errori di sintassi)
                // viene sollevata una SQLException
                Logger.getLogger(CarSaleFactory.class.getName()).
                log(Level.SEVERE, null, ex);
            }
        }       
        else
            return null;
        
        return lista;
    }
    
    /** Questo metodo è utilizzato per eliminare un veicolo
     *  @param idAuto id dell'auto da eliminare 
        @return true se l'oggetto è stato eliminato correttamente altrimenti false
    */
    public boolean removeAuto(int idAuto){
        boolean remove = false;
        /* Definisco la query per eliminare il veicolo avente id passato come parametro dalla tabella*/
        String sql = "DELETE FROM CarSale WHERE id = ?";
        
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Si associano valori e posizioni di placeholder
            stmt.setInt(1, idAuto);
            
            int rows = stmt.executeUpdate();
            /* Se il numero di righe restituite dalla query di update è pari a uno significa che l'operazione è andata
               a buon fine, quindi setto il flag a true */
            if(rows == 1) 
                remove = true;
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        } 
        
        /* Se il numero di righe restituite fosse diverso da uno il metodo resituisce false al chiamante */
        return remove;
    }
    
    /** Inserisce un nuovo oggetto nella lista
     *  @param auto auto da aggiungere
        @return true se l'oggetto è stato inserito correttamente altrimenti false
    */
    public boolean addAuto(CarSale car){
        boolean insert = false;
        /* Definisco la query per aggiungere un nuovo veicolo */
        String query = "INSERT INTO CarSale "
                        + "(id, nomeAuto, urlImmagine, descrizione, prezzoUnitario, quantita, id_venditore) VALUES "
                        + "(default, ?, ?, ?, ?, ?, ?)";
        
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(query)) {
            // Si associano valori e posizioni di placeholder
            stmt.setString(1, car.getNomeAuto());
            stmt.setString(2, car.getUrlImmagine());
            stmt.setString(3, car.getDescrizione());
            stmt.setDouble(4, car.getPrezzoUnitario());
            stmt.setInt(5, car.getQuantita());
            stmt.setInt(6, car.getIdVenditore());
            
            int rows = stmt.executeUpdate();
            /* Se il numero di righe restituite dalla query di update è pari a uno significa che l'operazione è andata
               a buon fine, quindi setto il flag a true */
            if(rows == 1)
                insert = true; 
        }catch(SQLException ex){
            Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);  
        }
        
         /* Se il numero di righe restituite fosse diverso da uno il metodo resituisce false al chiamante */
        return insert;
    }
    
    /** Metodo per la modifica di un oggetto in vendita
     *  @param idAuto id dell'auto da eliminare 
        @return true se l'oggetto è stato modificato correttamente altrimenti false
    */
    public boolean alterAuto(CarSale car){
        boolean alter = false;
        /* Definisco la query per modificare l'oggetto, vengono sempre modificati tutti i campi, quelli che l'utente non
           ha specificato sono reimpostati uguali ai precedenti, in modo da eseguire una uery che vada bene sempre */
        String sql = " UPDATE CarSale "
                    + "SET nomeAuto = ?, urlImmagine = ?, descrizione = ?, prezzoUnitario = ?,"
                    + "quantita = ?, id_venditore = ? "
                    + "WHERE id = ? ";
        
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Si associano valori e posizioni di placeholder
            stmt.setString(1, car.getNomeAuto());
            stmt.setString(2, car.getUrlImmagine());
            stmt.setString(3, car.getDescrizione());
            stmt.setDouble(4, car.getPrezzoUnitario());
            stmt.setInt(5, car.getQuantita());
            stmt.setInt(6, car.getIdVenditore());
            stmt.setInt(7, car.getId());
            
            int rows = stmt.executeUpdate();
            /* Se il numero di righe restituite dalla query di update è pari a uno significa che l'operazione è andata
               a buon fine, quindi setto il flag a true */
            if(rows == 1) 
                alter = true;
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        } 
        
         /* Se il numero di righe restituite fosse diverso da uno il metodo resituisce false al chiamante */
        return alter;
    }
    
    /** Restituisce la lista di tutti gli oggetti che contengono la stringa passata come parametro nel nome o nella 
     *  descrizione
     *  @param pattern pattern da ricercare
     *  @return lista degli oggetti
     */
    public ArrayList<CarSale> getAutoSaleListByPattern(String pattern) {
        ArrayList<CarSale> lista = new ArrayList<>();
        
        String sql = "SELECT *" +
                     "FROM CarSale " + 
                     "WHERE nomeAuto LIKE ? OR descrizione LIKE ?";         
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Assegna dati
            pattern = "%"+pattern+"%";
            stmt.setString(1, pattern);
            stmt.setString(2, pattern);
            ResultSet set = stmt.executeQuery();
            
            while (set.next()) {
                int id = set.getInt("id");
                String nomeAuto = set.getString("nomeAuto");
                String urlImmagine = set.getString("urlImmagine");
                String descrizione = set.getString("descrizione");
                double prezzoUnitario = set.getDouble("prezzoUnitario");
                int quantita = set.getInt("quantita");
                int id_venditore = set.getInt("id_venditore");
                CarSale car = new CarSale(id, id_venditore, nomeAuto, urlImmagine, descrizione, prezzoUnitario, quantita);
                
                lista.add(car);
            }
        }catch(SQLException ex) {
           Logger.getLogger(CarSaleFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        System.out.println(lista);
        return lista;
    }  
}