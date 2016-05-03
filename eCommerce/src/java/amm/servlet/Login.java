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
import amm.model.factory.CarSaleFactory;
import amm.model.factory.BuyersFactory;
import amm.model.factory.SellersFactory;
import java.io.IOException;
import java.util.ArrayList;
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
@WebServlet(name = "Login", urlPatterns = {"/login.html"})
public class Login extends HttpServlet {

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
                    // Prelevo le liste degli utenti (clienti e venditori)
                    ArrayList<Buyer> listaClienti = BuyersFactory.getInstance().getListaClienti();
                    ArrayList<Seller> listaVenditori = SellersFactory.getInstance().getListaVenditori();
                    
                    /* Controllo se le l'utente esiste nella lista dei clienti, in caso affermativo imposto la variabile
                       di sessione utente con l'oggetto che lo rappresenta, in modo da poter recuperare in seguito le
                       informazioni che lo riguardano e lo rimando alla pagina cliente */
                    for(Buyer c : listaClienti){
                        if(c.getUsername().equals(username) && c.getPassword().equals(password)){
                            session.setAttribute("utente", c);
                            session.setAttribute("login", true);
                            ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleList(); 
                            request.setAttribute("listaAuto", listaAuto);
                            request.getRequestDispatcher("cliente.jsp").forward(request, response);
                        }
                    }
                    
                    /* Se l'utente è gia stato trovato nella lista dei clienti non eseguo controlli ulteriori, in caso 
                       contrario eseguo lo stesso procedimento sulla lista dei venditori */
                    if(session.getAttribute("utente") == null)
                        for(Seller v : listaVenditori){
                            if(v.getUsername().equals(username) && v.getPassword().equals(password)){
                                session.setAttribute("utente", v);
                                session.setAttribute("login", true);
                                request.getRequestDispatcher("venditore.jsp").forward(request, response);
                            }
                        }
                    
                    /* Se arrivo qui significa che l'utente non esiste e quindi lo rimando al form di login e gli 
                       stampo un messaggio di errore*/
                    request.setAttribute("errore", true);
                    request.getRequestDispatcher("login.jsp").forward(request, response);   
                    
                }
                // Se le crredenziali sono nulle rimando al form di login con un messaggio di errore
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
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
                /* Se invece si tratta di un cliente, viene rimandato alla jsp cliente.jsp, alla quale passo la lista
                   delle auto in vendita per poterle stampare in una tabella*/
                else{
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleList(); 
                    request.setAttribute("listaAuto", listaAuto);
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
