/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model;

import amm.model.factory.AccountsFactory;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;


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
    public double getSaldo() {
        return saldo;
    }

    /* Questi metodi non sono più necessari in quanto l'acquisto di un oggetto viene interamente gestito all'interno
       di una transazione */
    /** Versa la cifra specificata nel conto
     *  @param vesamento cifra da versare
     */
    /*
    public boolean versa(double versamento) {
        boolean alter = false;
       
        try {
            Connection conn = DriverManager.getConnection(AccountsFactory.getInstance().getConnectionString(), "selimacurci", "0000");
            // Si mettono dei punti di domanda al posto dei valori
            String sql = "UPDATE Account SET saldo = ?  WHERE id = ? ";
            // Si crea un prepared statement
            PreparedStatement stmt = conn.prepareStatement(sql);
            // Si associano valori e posizioni di placeholder
            stmt.setDouble(1, saldo+versamento);
            stmt.setInt(2, id);
            int rows = stmt.executeUpdate();
            if(rows == 1) {
                alter = true;
            }
            stmt.close();
            // chiusura della connessione
            conn.close();
        }catch (SQLException ex) {
            // nel caso la query fallisca (p.e. errori di sintassi)
            // viene sollevata una SQLException
            //Logger.getLogger(CarSaleFactory.class.getName()).
            //log(Level.SEVERE, null, ex);
        } 
        
        return alter;       
    }*/
    
    /** Se il saldo corrente è sufficiente prelieva la cifra specificata come parametro e restituisce true,
     * in caso contrario restituisce false
     *  @param prelievo cifra da prelevare
     */
    /*public boolean preleva(double prelievo) {
        
        if(this.saldo >= prelievo){
            boolean alter = false;
       
            try {
                Connection conn = DriverManager.getConnection(AccountsFactory.getInstance().getConnectionString(), "selimacurci", "0000");
                // Si mettono dei punti di domanda al posto dei valori
                String sql = " UPDATE Account SET saldo = ?  WHERE id = ? ";
                // Si crea un prepared statement
                PreparedStatement stmt = conn.prepareStatement(sql);
                // Si associano valori e posizioni di placeholder
                stmt.setDouble(1, saldo-prelievo);
                stmt.setInt(2, id);
                int rows = stmt.executeUpdate();
                if(rows == 1) {
                    alter = true;
                }
                stmt.close();
                // chiusura della connessione
                conn.close();
            } catch (SQLException ex) {
                // nel caso la query fallisca (p.e. errori di sintassi)
                // viene sollevata una SQLException
                //Logger.getLogger(CarSaleFactory.class.getName()).
                //log(Level.SEVERE, null, ex);
            } 
        
            return alter;    
        }else
            return false;
    }*/
}


