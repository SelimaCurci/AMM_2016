/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.Account;
import amm.model.Buyer;
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
/** Classe per il popolamento dei clienti */
public class BuyersFactory {
    /* Attributi */
    private static BuyersFactory singleton;
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
    public static BuyersFactory getInstance() {
        if (singleton == null) {
            singleton = new BuyersFactory();
        }
        return singleton;
    }

    /* Costruttore vuoto*/
    private BuyersFactory() {

    }

    /** Restituisce la lista di tutti i clienti (inutile)
     *  @return lista dei clienti
     */
    public ArrayList<Buyer> getListaClienti() {
        ArrayList<Buyer> listaClienti = new ArrayList<>();
        
        /* Apro la connessione al db e creo lo Statement usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            Statement stmt = conn.createStatement()) {
            // Definisco la query per ottenere l'elenco di tutti i clienti
            String sql = "SELECT * FROM Buyer";
            
            ResultSet set = stmt.executeQuery(sql);
            /* Scorro tutti i risultati ottenuti aggiungendoli alla lista che poi sarà restituita al chiamante */
            while (set.next()) {
                int id = set.getInt("id");
                String nome = set.getString("nome");
                String cognome = set.getString("cognome");
                String username = set.getString("username");
                String password = set.getString("password");
                int id_conto = set.getInt("id_conto");
                Buyer buyer = new Buyer(id, nome, cognome, username, password, id_conto);
                
                listaClienti.add(buyer);
            }
        } catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            Logger.getLogger(BuyersFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return listaClienti;
    }
    
    /** Restiuisce l'utente avente l’identificatore passato per parametro 
     *  @return  restiuisce l'utente avente l’identificatore passato per parametro 
     */
    public Buyer getBuyerById(int id){
        Buyer buyer = null;
        // Definisco la query per trovare un cliente dato il suo id
        String query = "SELECT * FROM Buyer WHERE id = ?";
     
        /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(query)){
            // Si associano valori e posizioni di placeholder
            stmt.setInt(1, id);
            
            ResultSet set = stmt.executeQuery();
            /* Se l'esecuzione ha prodotto un risultato, preparo l'oggetto cliente da restituire */
            if(set.next()) {
                buyer = new Buyer();
                buyer.setId(set.getInt("id"));
                buyer.setNome(set.getString("nome"));
                buyer.setCognome(set.getString("cognome"));
                buyer.setUsername(set.getString("username"));
                buyer.setPassword(set.getString("password"));
                buyer.setIdConto(set.getInt("id_conto"));
            }
        } catch(SQLException ex){
            Logger.getLogger(BuyersFactory.class.getName()).
            log(Level.SEVERE, null, ex);
        }
        
        return buyer;
    }  
    
    /** Restiuisce l'utente avente nome utente e password passati per parametro
     *  @return  restiuisce l'utente avente nome utente e password passati per parametro
     */
    public Buyer findBuyer(String username, String password){
        Buyer buyer = null;
        // Definisco la query per trovare un cliente dati username e password
        String query = "select * from Buyer where password = ? and username = ?";
        
         /* Apro la connessione al db e creo il prepare Statement per la query usando un try with-resources, in questo
           modo sono sicura che le risorse saranno chiuse in ogni caso */
        try(Connection conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");
            PreparedStatement stmt = conn.prepareStatement(query)){
            // Si associano valori e posizioni di placeholder
            stmt.setString(1, password);
            stmt.setString(2, username);
            
            ResultSet set = stmt.executeQuery();
            /* Se l'esecuzione ha prodotto un risultato, preparo l'oggetto cliente da restituire */
            if(set.next()){
                buyer = new Buyer();
                buyer.setId(set.getInt("id"));
                buyer.setNome(set.getString("nome"));
                buyer.setCognome(set.getString("cognome"));
                buyer.setUsername(set.getString("username"));
                buyer.setPassword(set.getString("password"));
                buyer.setIdConto(set.getInt("id_conto"));
            }
        } catch(SQLException e) {
            Logger.getLogger(BuyersFactory.class.getName()).
            log(Level.SEVERE, null, e);
        }
        
        return buyer;
    }  
    
    /** Questo metodo gestiscee la compravendita di un oggetto tramite una transazione. La procedura esegue i seguenti passi:
        1. Rimuovere l’oggetto dalla lista di quelli in vendita per il venditore
        2. Diminuire il credito del cliente del prezzo dell’oggetto
        3. Aggiungere il credito nel saldo del venditore.
        Nel caso uno  di qualsiasi questi passi dovesse fallire, il db deve essere riportato allo stato che 
        aveva prima del passo 1
     *  @param  idCar id dell'auto che il cliente desidera comprare
     *  @param  idAccountBuyer id del cliente
     *  @param  idAccountSeller id del venditore
     *  @return  restiuisce true se la transazione è andata a buon fine, altrimenti restituisce false
     */
    public boolean transazione(int idCar, int idAccountBuyer, int idAccountSeller) throws SQLException{
        boolean flag = false;
        
        Connection conn = null;
        PreparedStatement deleteCar = null;
        PreparedStatement withdrawBuyerAccount = null;
        PreparedStatement depositSellerAccount = null;
        
        // Mi ricavo il veicolo che il cliente desidera comperare dal suo id fornito come parametro
        CarSale car = CarSaleFactory.getInstance().getAutoSaleById(idCar);
        /* Mi memorizzo il numero degli esemplari  e il prezzo unitario */
        int quantita = car.getQuantita();
        double prezzo = car.getPrezzoUnitario();
        // Mi recuperoanche i dati relativi ai conti di cliente e venditore
        Account accountBuyer = AccountsFactory.getInstance().getAccountById(idAccountBuyer);
        Account accountSeller = AccountsFactory.getInstance().getAccountById(idAccountSeller);
        double balanceBuyer = accountBuyer.getSaldo();
        double balanceSeller = accountSeller.getSaldo();
        
        // Se il credito del cliente non è sufficiente la transazione fallisce ancora prima di iniziare
        if(balanceBuyer-prezzo < 0)  return flag;
        
        // Comandi sql da effetuare
        String query1 = null;
        /* Se il numero di esemplari è pari a uno il veicolo va eliminato, quindi la query da eseguire andrà
           ad eliminare l'oggetto dal db; in caso cotrario eseguirò una query di update per decrementare di una
           unità il numero di esemplari */ 
        if(quantita == 1)
            query1 = " DELETE FROM CarSale WHERE id = ? ";
        else
            query1 = " UPDATE CarSale SET quantita = ?  WHERE id = ? "; 
        /* Query  per la modifica del saldo cliente e del saldo venditore */
        String query2 = " UPDATE Account SET saldo = ?  WHERE id = ? ";
        String query3 = " UPDATE Account SET saldo = ?  WHERE id = ? ";
        
        try{ // Connessione al database
            conn = DriverManager.getConnection(connectionString, "selimacurci", "0000");

           /* Normalmente tutte le query che vengono inviate al db tramite sono automaticamente rese permanenti, per fare
               in modo che non lo siano si invoca il metodo setAutoCommit(false) */
            conn.setAutoCommit(false);
            
            deleteCar = conn.prepareStatement(query1);
            withdrawBuyerAccount = conn.prepareStatement(query2);
            depositSellerAccount = conn.prepareStatement(query3);
            
            // Si associano valori e posizioni di placeholder
            if(quantita == 1)
                deleteCar.setInt(1, idCar);
            else{
                deleteCar.setInt(1, quantita-1);
                deleteCar.setInt(2, idCar);
            }
            withdrawBuyerAccount.setDouble(1, balanceBuyer-prezzo);
            withdrawBuyerAccount.setInt(2, idAccountBuyer);
            depositSellerAccount.setDouble(1, balanceSeller+prezzo);
            depositSellerAccount.setInt(2, idAccountSeller);
            
            /* Esecuzione delle query */
            int c1 = deleteCar.executeUpdate();
            int c2 = withdrawBuyerAccount.executeUpdate();
            int c3 = depositSellerAccount.executeUpdate();
            
            /* Nel caso una qualsiasi delle query dovesse fallire, il db deve essere riportato allo stato 
               che aveva prima di iniziare la transazione */
            if(c1 != 1 || c2 != 1 || c2 != 1){ 
               conn.rollback();
            }else{
                /* Se è andato tutto bene rendo permanenti le modifiche in modo esplicito e imposto il flag a true */
                conn.commit();
                flag = true;
            }
        }catch(SQLException ex){
            if(conn != null){
                /* Nel caso qualcosa vada storto, la rollback permette di riportare il database allo stato precedente 
                   all'inizio della transazione. Imposto inoltre i flag a false in caso di errore. */
                try{
                    conn.rollback();
                }catch(SQLException e){
                    /* La roolback può a sua volta generare un'eccezione */
                    Logger.getLogger(BuyersFactory.class.getName()).
                    log(Level.SEVERE, null, e);
                }
                flag = false;
            }
        } // Il blocco finally viene eseguito in ogni caso, al suo interno chiudo tutte le risorse utilizzate
        finally{
            if(deleteCar != null) deleteCar.close();
            if(withdrawBuyerAccount != null) withdrawBuyerAccount.close();
            if(depositSellerAccount != null) depositSellerAccount.close();
            if(conn != null){
                conn.setAutoCommit(true); // Ripristina il comportamento normale
                conn.close();
            }
        }
        
        /* Verrà restituito true solo nel caso in cui tutto sia andato a buon fine */
        return flag;
    }
}
