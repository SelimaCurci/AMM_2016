/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.servlet;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import amm.model.CarSale;
import amm.model.User;
import amm.model.Buyer;
import amm.model.Seller;
import amm.model.factory.CarSaleFactory;
import amm.model.factory.SellersFactory;
import javax.servlet.http.HttpSession;

/**
 *
 * @author selima
 */
/* Servet che risponde alla url cliente.html */
@WebServlet(name = "Cliente", urlPatterns = {"/cliente.html"})
public class Cliente extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(false);
        
        // Se la sessione non esiste mostro la jsp di accesso negato
        if(session == null){
           request.setAttribute("utente", "Cliente");
           //response.setStatus(403);
           request.getRequestDispatcher("accessoNegato.jsp").forward(request, response);
        }
        
        // Se l'utente è un venditore non può accedere a questa pagina
        if(((User)session.getAttribute("utente") instanceof Seller) || session.getAttribute("utente") == null){
            request.setAttribute("utente", "Cliente");
            request.getRequestDispatcher("accessoNegato.jsp").forward(request, response);
        }
        else{
            // Prelevo tutta la lista degli oggetti messi in vendita per stamparli in una tabella nella jsp
            ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleList(); 
            
            // Se l'utente clicca il tasto per confermare l'acquisto, lo gestisco 
            if( request.getParameter("conferma") != null)
            { 
                    // Prelevo tutte le info del ciente dall'oggetto conservato in sessione 
                    Buyer buyer = (Buyer)session.getAttribute("utente");
                    int id = -1;

                    // Verifico che il valore passato come id dell'auto sia un intero, altrimenti il metodo genera un'eccezione
                    try{
                      id = Integer.parseInt(request.getParameter("idSel"));
                    }catch(RuntimeException e){
                      request.setAttribute("pagamento", false);
                      request.setAttribute("errore", true); 
                      request.setAttribute("listaAuto", listaAuto);
                      request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    }
                    
                    // Recupero l'oggetto selezionato gtramite l'id passato come parametro
                    CarSale autoSelezionata = CarSaleFactory.getInstance().getAutoSaleById(id);
                    
                    // Se l'id non è valido la transazione viene interrotta
                    if(autoSelezionata == null){
                      request.setAttribute("pagamento", false);
                      request.setAttribute("errore", true); 
                      request.setAttribute("listaAuto", listaAuto);
                      request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    }
                    
                    /* L'oggetto contiene un attributo che mi permette di risalire al suo venditore */
                    int idVenditore = autoSelezionata.getIdVenditore();
                    Seller seller = (Seller)SellersFactory.getInstance().getSellerById(idVenditore);

                    request.setAttribute("utente", "cliente");
                    request.setAttribute("auto", autoSelezionata);
                    /* Se il saldo del cliente è sufficiente imposto pagamento a true per poterlo comunicare all'utente,
                       faccio un versamento al venditore dell'oggetto in questione e chiamo il metodo remove. Questo metodo
                       elimina l'oggetto dalla lista se la sua quantità era pari a uno, altrimenti semplicemente scala di un
                       unità il numero di esemplari disponibili */ 
                    if(buyer.compra(autoSelezionata)){
                       request.setAttribute("pagamento", true);
                       seller.vendi(autoSelezionata);
                       CarSaleFactory.getInstance().removeAuto(autoSelezionata.getId());
                    }
                    else{ // Se i soldi non sono sufficienti imposto pagamento a false, e poi avviserò con un messaggio il cliente
                       request.setAttribute("pagamento", false); 
                    }
                   
                    /* Questo attributo mi serve nella jsp cliente per stampare o meno i messaggi relativi all'esito
                       della transazione */
                    request.setAttribute("conferma", true); 
                    request.setAttribute("listaAuto", listaAuto); // Devo ripassare la lista auto alla jsp
                    request.setAttribute("conto", buyer.getSaldoUtente());
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
            }
            /* Se questa condizione è vera siamo nella fase di selezione del veicolo da acqiustare, è la fase precedente 
               a una eventuale conferma. L'utente ha selezionato un oggetto dalla tabella fornendoci il suo id come parametro*/
            if(request.getParameter("idAuto") != null)
            {
                int idAutoSelezionata = -1;
                
                /* Verifico che l'id sia effettivamente un intero per poter chiamare il metodo parseInt con successo,
                   se così non fosse l'utente malintenzionato viene rispedito alla tabella */
                try{
                  idAutoSelezionata = Integer.parseInt(request.getParameter("idAuto"));
                }catch(RuntimeException e){ 
                  request.setAttribute("errore", true);
                  request.setAttribute("listaAuto", listaAuto);
                  request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }
                
                // Risalgo all'oggetto selezionato tramite il suo id
                CarSale autoSelezionata = CarSaleFactory.getInstance().getAutoSaleById(idAutoSelezionata);
                
                /* Verifico che l'id passato corrisponda a un oggetto reale. In caso affermativo il cliente viene rimandato
                   a una pagina dove compare il riepilogo dell'oggetto selezionato, dove, se desidera potrà confermare l'acquisto.
                   Altrimenti l'utente viene rimandato alla tabella.
                */
                if(autoSelezionata!=null){
                    // Imposto gli attributi necessari per la jsp del riepilogo
                    request.setAttribute("utente", "cliente");
                    session.setAttribute("autoSelezionata", autoSelezionata);
                    request.setAttribute("auto", session.getAttribute("autoSelezionata"));
                    request.getRequestDispatcher("riepilogo.jsp").forward(request, response);
                }
                else{
                    request.setAttribute("listaAuto", listaAuto);
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }
            }
            
            /* In condizioni "normali" passo semplicemente la lista degli oggetti in vendita alla jsp che si occupa di 
               visualizzarli in una tabella */
            request.setAttribute("listaAuto", listaAuto);
            request.getRequestDispatcher("cliente.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
