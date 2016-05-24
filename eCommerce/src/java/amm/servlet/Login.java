/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.servlet;

import amm.model.CarSale;
import amm.model.User;
import amm.model.Seller;
import amm.model.Buyer;
import amm.model.factory.AccountsFactory;
import amm.model.factory.CarSaleFactory;
import amm.model.factory.BuyersFactory;
import amm.model.factory.SellersFactory;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author selima
 */
/** Servlet che risponde alla url login.html */
@WebServlet(name = "Login", urlPatterns = {"/login.html"}, loadOnStartup = 0)
public class Login extends HttpServlet {
    /* Costanti necessarie per generare la stringa di connessione */
    private static final String JDBC_DRIVER = "org.apache.derby.jdbc.EmbeddedDriver";
    private static final String DB_CLEAN_PATH = "../../web/WEB-INF/db/ammdb";
    private static final String DB_BUILD_PATH = "WEB-INF/db/ammdb";
    
    /*L'inizializzazione del Driver va eseguita solo una volta per tutta la vita dell’applicazione. Per fare in modo che 
      venga eseguita come prima cosa l'abbiamo inserita nel metodo init() della Servlet Login. Inoltre facciamo in modo
      che la Servlet Login sia la prima ad essere caricata a prescindere dagli eventi. In questo modo siamo sicuri che 
      il Driver venga inizializzato correttamente.*/
    @Override 
    public void init(){
        /* Generazione della stringa usata per connettersi al database (path) */
        String dbConnection = "jdbc:derby:" + this.getServletContext().getRealPath("/") + DB_BUILD_PATH;
        try {
            Class.forName(JDBC_DRIVER);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Setto la stringa di connessione in tutte le classi factory che avranno bisogno di connettersi al database */
        CarSaleFactory.getInstance().setConnectionString(dbConnection);
        SellersFactory.getInstance().setConnectionString(dbConnection);
        BuyersFactory.getInstance().setConnectionString(dbConnection);
        AccountsFactory.getInstance().setConnectionString(dbConnection);
    }
    
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
        
        // Creo la sessione
        HttpSession session = request.getSession(true);
        
        /* Se l'attributo di sessione è null significa che l'utente non è ancora loggato, devo quindi gestire la sua
           richiesta di autenticazione */
        if(session.getAttribute("utente") == null){
            /* Se questa condizione risulta vera significa che il pulsante per effettuare il login è stato premuto. 
               Devo quindi verificare le credenziali inviate dall'utente. */
            if(request.getParameter("submit") != null){
                // Prelevo il nome utente e la password 
                String username = request.getParameter("username");
                String password = request.getParameter("pswd");   
                
                // Verifico che siano diverse da null
                if (username != null && password != null ){
                    
                    /* Controllo se le l'utente esista tra i clienti registrati, in caso affermativo imposto la variabile
                       di sessione utente con l'oggetto che lo rappresenta, in modo da poter recuperare in seguito le
                       informazioni che lo riguardano e lo rimando alla pagina cliente */
                    Buyer buyer = BuyersFactory.getInstance().findBuyer(username, password);
                    if(buyer != null){
                        session.setAttribute("utente", buyer);
                        /* Preparo la lista di tutti i veicoli in vendita da passare alla jsp del cliente */
                        ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleList(); 
                        int size = listaAuto.size();
                        request.setAttribute("listaAuto", listaAuto);
                        request.setAttribute("size", size);
                        request.getRequestDispatcher("cliente.jsp").forward(request, response);
                    }
                    
                    /* Se l'utente cìnon è un cliente, potrebbe essere un venditore, perciò devo anche verificare se è presente
                       tra i venditori registrati. In caso affermativo imposto la variabile di sessione utente con l'oggetto 
                       che lo rappresenta, in modo da poter recuperare in seguito le  informazioni che lo riguardano e lo 
                       rimando al portale dei venditori */
                    Seller seller = SellersFactory.getInstance().findSeller(username, password);
                    if(seller != null){
                        /* Preparo la lista di tutti i veicoli messi in vendita dal venditore autenticato da passare alla jsp 
                           del venditore. Questa lista servirà per fare in modo che il venditore possa selezionare un'oggetto
                           per eventuali modifiche o per l'eliminazione */
                        ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(seller.getId());
                        session.setAttribute("utente", seller);
                        request.setAttribute("listaAuto", listaAuto);
                        
                        /* Se la lista è null significa che l'utente non ha oggetti in vendita, lo memorizzo così non gli
                           mostro le funzionalità di modifica e eliminazione nella jsp*/
                        if(listaAuto != null)
                            request.setAttribute("listaSize", listaAuto.size());
                        else
                            request.setAttribute("listaSize", 0);  
                        request.getRequestDispatcher("venditore.jsp").forward(request, response);
                    }
                    
                    /* Se arrivo qui significa che l'utente non esiste e quindi lo rimando al form di login e gli 
                       stampo un messaggio di errore*/
                    request.setAttribute("errore", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);   
                    
                }
                // Se le credenziali sono nulle rimando al form di login con un messaggio di errore
                else{
                    request.setAttribute("errore", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);   
                }
            }
            // Richiamo la jsp per il login
            request.getRequestDispatcher("login.jsp").forward(request, response);    
        }
        /* In caso contrario l'utente ha già effettuato l'accesso e quindi lo rimando alla pagina corretta settando gli
           attributi necessari */
        else{
                // Se l'utente è un venditpre viene rimandato alla jsp venditore.jsp
                if((User)session.getAttribute("utente") instanceof Seller){
                    int id = ((User)session.getAttribute("utente")).getId();
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                    request.setAttribute("listaAuto", listaAuto);
                    if(listaAuto != null)
                            request.setAttribute("listaSize", listaAuto.size());
                        else
                            request.setAttribute("listaSize", 0); 
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
                /* Se invece si tratta di un cliente, viene rimandato alla jsp cliente.jsp, alla quale passo la lista
                   delle auto in vendita per poterle stampare in una tabella*/
                else{
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleList(); 
                    int size = listaAuto.size();
                    request.setAttribute("listaAuto", listaAuto);
                    request.setAttribute("size", size);
                    request.getRequestDispatcher("cliente.jsp").forward(request, response);
                }

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
