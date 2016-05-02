/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.servlet;

import amm.model.CarSale;
import amm.model.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import amm.model.Buyer;
import amm.model.factory.CarSaleFactory;
/**
 *
 * @author selim
 */
/* Servet che risponde alla url venditore.html */
@WebServlet(name = "Venditore", urlPatterns = {"/venditore.html"})
public class Venditore extends HttpServlet {

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
           request.setAttribute("utente", "Venditore");
           //response.setStatus(403);
           request.getRequestDispatcher("accessoNegato.jsp").forward(request, response);
        }
        // Se l'utente è un cliente non può accedere a questa pagina
        if(((User)session.getAttribute("utente") instanceof Buyer) || session.getAttribute("utente") == null){
            request.setAttribute("utente", "Venditore");
            request.getRequestDispatcher("accessoNegato.jsp").forward(request, response);
        }
        
        // Variabile usata per controllare i campi inviati dal venditore
        boolean controllo = true;
       
        /* Se la condizione è vera significa che l'utente ha inviato i dati relativi all'inserimento di un nuovo
           oggetto in vendita. Quindi devo gestirli in modo appropriato. */
        if(request.getParameter("submit") != null){
                   CarSale nuovaAuto = new CarSale();
                   int quantita = 0;
                   double prezzo = 0.0;
                   
                   /* Preparo il parametro auto da passare alla pagina di riepilogo verificando che tutti i campi siano
                      corretti. In caso non fosse così l'inserimento non andrà a buon fine */         
                   if(request.getParameter("nomeoggetto")!=null && request.getParameter("descrizione")!=null
                           && request.getParameter("urlimmagine")!=null ){
                       nuovaAuto.setNomeAuto(request.getParameter("nomeoggetto"));
                       nuovaAuto.setDescrizione(request.getParameter("descrizione"));
                       nuovaAuto.setUrlImmagine(request.getParameter("urlimmagine"));
                   }else{
                       controllo = false;
                   }
                   try{
                       quantita = Integer.parseInt(request.getParameter("quantita"));
                   }catch(RuntimeException e){
                       controllo = false;
                   }
                   try{
                       prezzo = Double.parseDouble(request.getParameter("prezzo"));
                   }catch(RuntimeException e){
                       controllo = false;
                   }
                   
                   if(quantita > 0 )
                       nuovaAuto.setQuantita(quantita);
                   else
                       controllo = false;
                   
                   if(prezzo > 0 )
                       nuovaAuto.setPrezzoUnitario(prezzo);
                   else
                       controllo = false;
                   
                   // Se controllo è ancora true i campi sono stati tutti validati e posso inserire l'oggetto
                   if(controllo == true){
                       nuovaAuto.setIdVenditore(((User)session.getAttribute("utente")).getId());
                       nuovaAuto.setId(CarSaleFactory.getInstance().getNewId());
                       if(CarSaleFactory.getInstance().addAuto(nuovaAuto)){
                           request.setAttribute("auto", nuovaAuto);
                           request.setAttribute("conferma", true);
                       }
                       else{ 
                           request.setAttribute("conferma", false);
                       }
                   } // In caso contario mostrerò un messaggio per avvertire l'utente dell'errore
                   else{
                       request.setAttribute("conferma", false);
                   }
                   
                   request.setAttribute("utente", "venditore");
                   request.getRequestDispatcher("riepilogo.jsp").forward(request, response);
            }
            
             // jsp che mostra il form per l'inserimento
            request.getRequestDispatcher("venditore.jsp").forward(request, response);
        
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
