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
import java.util.ArrayList;
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
        
       
        /* Se la condizione è vera significa che l'utente ha inviato i dati relativi all'inserimento di un nuovo
           oggetto in vendita. Quindi devo gestirli in modo appropriato. */
        if(request.getParameter("aggiungi") != null){
                /* Inserisco l'oggetto se i campi sono tutti corretti a prescindere dal fatto che l'utente selezioni 
                   nuovo */    
                /*if(!request.getParameter("idoggetto").equals("Nuovo")){
                    request.setAttribute("errore", true);
                    request.setAttribute("messaggioErrore", "Errore nella selezione. Scegli \"Nuovo\" per inserire un nuovo oggetto.");
                    int id = ((User)session.getAttribute("utente")).getId();
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                    request.setAttribute("listaAuto", listaAuto);
                    if(listaAuto != null)
                        request.setAttribute("listaSize", listaAuto.size());
                    else
                    request.setAttribute("listaSize", 0); 
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }*/
                    
                CarSale nuovaAuto = new CarSale();
                int quantita = 0;
                double prezzo = 0.0;
                // Variabile usata per controllare i campi inviati dal venditore
                boolean controllo = true;
                   
                /* Preparo il parametro auto da passare alla pagina di riepilogo verificando che tutti i campi siano
                   corretti. In caso non fosse così l'inserimento non andrà a buon fine */         
                if(!(request.getParameter("nomeoggetto").equals("")) && request.getParameter("nomeoggetto")!= null){
                    nuovaAuto.setNomeAuto(request.getParameter("nomeoggetto"));
                }else
                    controllo = false;
                    
                if(!(request.getParameter("descrizione").equals("")) && request.getParameter("descrizione") != null){
                    nuovaAuto.setDescrizione(request.getParameter("descrizione"));
                }else
                    controllo = false;
                   
                if(!(request.getParameter("urlimmagine").equals("")) && request.getParameter("urlimmagine") != null){
                    nuovaAuto.setUrlImmagine(request.getParameter("urlimmagine"));
                }else
                    controllo = false;
                   
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
           
        /* Se la condizione è vera significa che l'utente ha inviato i dati relativi alla modifica di un 
           oggetto in vendita. Quindi devo gestirli in modo appropriato. */
        if(request.getParameter("modifica") != null){
                int idAuto = 0;
               
                /* Devo verificare per prima cosa che il venditore abbia selezionato un oggetto valido e non la 
                   voce "nuovo", se la selezione è erratta lo avviso con un messaggio di errore */
                try{
                    idAuto = Integer.parseInt(request.getParameter("idoggetto"));
                }catch(RuntimeException e){
                    request.setAttribute("errore", true);
                    request.setAttribute("messaggioErrore", "Errore nella selezione dell'oggetto da modificare. Scegli un oggetto valido.");
                    int id = ((User)session.getAttribute("utente")).getId();
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                    request.setAttribute("listaAuto", listaAuto);
                    if(listaAuto != null)
                        request.setAttribute("listaSize", listaAuto.size());
                    else
                        request.setAttribute("listaSize", 0); 
                        request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
                    
                // Recupero il veicolo selezionato dal suo id
                CarSale car = CarSaleFactory.getInstance().getAutoSaleById(idAuto);
                /* Conto quanti campi sono stati compilati per la modifica */
                int numeroCampi = 0; 
                // Variabile usata per controllare i campi inviati dal venditore
                boolean controllo = true;   
                    
                /* Modifico solo i campi compilati dal venditore, se validi, altrimenti i vecchi rimangono invariati */
                if(!(request.getParameter("nomeoggetto").equals("")) && request.getParameter("nomeoggetto")!= null){
                    car.setNomeAuto(request.getParameter("nomeoggetto"));
                    numeroCampi++;
                }
                    
                if(!(request.getParameter("descrizione").equals("")) && request.getParameter("descrizione") != null){
                    car.setDescrizione(request.getParameter("descrizione"));
                    numeroCampi++;
                }
                  
                if(!(request.getParameter("urlimmagine").equals("")) && request.getParameter("urlimmagine") != null){
                    car.setUrlImmagine(request.getParameter("urlimmagine"));
                    numeroCampi++;
                }
                  
                if(!(request.getParameter("quantita").equals("")) && request.getParameter("quantita") != null){
                    try{
                        int quantita = Integer.parseInt(request.getParameter("quantita"));
                        if(quantita > 0 ){
                            car.setQuantita(quantita);
                            numeroCampi++;
                        }
                        else 
                            controllo = false;
                    }catch(RuntimeException e){
                        controllo = false;
                    }
                }
                    
                if(!(request.getParameter("prezzo").equals("")) && request.getParameter("prezzo") != null){
                    try{
                        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
                        if(prezzo > 0 ){
                            car.setPrezzoUnitario(prezzo);
                            numeroCampi++;
                        }
                        else
                            controllo = false;
                        if(controllo ==false)throw new NullPointerException();
                    }catch(RuntimeException e){
                        controllo = false;
                    }
                }
                    
                /* Se non ha compilato nemmeno un campo non lo rimando nemmeno al riepilogo e l'avviso del fatto
                   che non è stata eseguita nessuna modifica */
                if(numeroCampi == 0){
                    int id = ((User)session.getAttribute("utente")).getId();
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                    request.setAttribute("listaAuto", listaAuto);
                    request.setAttribute("modifica", false);
                    if(listaAuto != null)
                        request.setAttribute("listaSize", listaAuto.size());
                    else
                        request.setAttribute("listaSize", 0); 
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
                    
                // Se controllo è ancora true i campi sono stati tutti validati e posso modificare l'oggetto
                if(controllo == true){
                    car.setIdVenditore(((User)session.getAttribute("utente")).getId());
                    if(CarSaleFactory.getInstance().alterAuto(car)){
                        request.setAttribute("auto", car);
                        request.setAttribute("modifica", true);
                    }
                    else{ 
                        request.setAttribute("modifica", false);
                    } 
                } // In caso contario mostrerò un messaggio per avvertire l'utente dell'errore
                else{
                    request.setAttribute("modifica", false);
                }
                   
                request.setAttribute("utente", "venditore");
                request.getRequestDispatcher("riepilogo.jsp").forward(request, response);
        }
            
        /* Se la condizione è vera significa che l'utente ha selezionato un oggetto da eliminare */
        if(request.getParameter("elimina") != null){
                int idAuto = 0;
                /* Devo verificare per prima cosa che il venditore abbia selezionato un oggetto valido e non la 
                   voce "nuovo", se la selezione è erratta lo avviso con un messaggio di errore */
                try{
                     idAuto = Integer.parseInt(request.getParameter("idoggetto"));
                }catch(RuntimeException e){
                    request.setAttribute("errore", true);
                    request.setAttribute("messaggioErrore", "Errore nella selezione dell'oggetto da eliminare. Scegli un oggetto valido.");
                    int id = ((User)session.getAttribute("utente")).getId();
                    ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                    request.setAttribute("listaAuto", listaAuto);
                    if(listaAuto != null)
                        request.setAttribute("listaSize", listaAuto.size());
                    else
                        request.setAttribute("listaSize", 0); 
                    request.getRequestDispatcher("venditore.jsp").forward(request, response);
                }
                
                /* Elimino l'oggetto e verifico che sia andata a buon fine l'eliminazione  */
                if(CarSaleFactory.getInstance().removeAuto(idAuto)){
                    request.setAttribute("elimina", true);
                }else{
                    request.setAttribute("errore", true);
                    request.setAttribute("messaggioErrore", "Errore nell'eliminazione dell'oggetto.");
                }
                
                request.setAttribute("utente", "venditore");
                int id = ((User)session.getAttribute("utente")).getId();
                ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
                request.setAttribute("listaAuto", listaAuto);
                if(listaAuto != null)
                    request.setAttribute("listaSize", listaAuto.size());
                else
                    request.setAttribute("listaSize", 0); 
                request.getRequestDispatcher("venditore.jsp").forward(request, response);
        }
              
            // jsp che mostra il form per l'inserimento
            int id = ((User)session.getAttribute("utente")).getId();
            ArrayList<CarSale> listaAuto = CarSaleFactory.getInstance().getAutoSaleBySeller(id);
            request.setAttribute("listaAuto", listaAuto);
            if(listaAuto != null)
                request.setAttribute("listaSize", listaAuto.size());
            else
                request.setAttribute("listaSize", 0); 
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
