/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package amm.model.factory;

import amm.model.CarSale;
import amm.model.Seller;
import java.util.ArrayList;

/**
 *
 * @author selima
 */
/** Classe usata per popolare gli oggetti messi in vendita */
public class CarSaleFactory {
    /* Attributi */
    private static CarSaleFactory singleton;   
    private ArrayList<CarSale> listaAuto;
    
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
        // In questo modo la lista viene creata una sola volta
        if(listaAuto == null){
            listaAuto = new ArrayList<>();
            
            // Oggetto 1
            CarSale obj_1 = new CarSale();
            obj_1.setNomeAuto("Aixam GTO");
            obj_1.setUrlImmagine("Images/Aixam_GTO.png");
            obj_1.setDescrizione("...");
            obj_1.setPrezzoUnitario(12650);
            obj_1.setQuantita(1);
            obj_1.setId(0);
            obj_1.setIdVenditore(0);
            listaAuto.add(obj_1);

            // Oggetto 2
            CarSale obj_2 = new CarSale();
            obj_2.setNomeAuto("BorgWard bx7");
            obj_2.setUrlImmagine("Images/borgward_bx7.jpg");
            obj_2.setDescrizione("...");
            obj_2.setPrezzoUnitario(15000);
            obj_2.setQuantita(4);
            obj_2.setId(1);
            obj_2.setIdVenditore(0);
            listaAuto.add(obj_2);

            // Oggetto 3
            CarSale obj_3 = new CarSale();
            obj_3.setNomeAuto("Chery Tiggo");
            obj_3.setUrlImmagine("Images/Chery_tiggo.jpg");
            obj_3.setDescrizione("...");
            obj_3.setPrezzoUnitario(9500);
            obj_3.setQuantita(2);
            obj_3.setId(2);
            obj_3.setIdVenditore(0);
            listaAuto.add(obj_3);

            // Oggetto 4
            CarSale obj_4 = new CarSale();
            obj_4.setNomeAuto("ChinaBike Cross125");
            obj_4.setUrlImmagine("Images/chinabike_cross125.jpg");
            obj_4.setDescrizione("...");
            obj_4.setPrezzoUnitario(750);
            obj_4.setQuantita(1);
            obj_4.setId(3);
            obj_4.setIdVenditore(0);
            listaAuto.add(obj_4);

            // Oggetto 5
            CarSale obj_5 = new CarSale();
            obj_5.setNomeAuto("Fisker Karma");
            obj_5.setUrlImmagine("Images/fisker-karma.jpg");
            obj_5.setDescrizione("...");
            obj_5.setPrezzoUnitario(125000);
            obj_5.setQuantita(1);
            obj_5.setId(4);
            obj_1.setIdVenditore(0);
            listaAuto.add(obj_5);

            // Oggetto 6
            CarSale obj_6 = new CarSale();
            obj_6.setNomeAuto("Horex vr6");
            obj_6.setUrlImmagine("Images/HOREX_vr6.jpg");
            obj_6.setDescrizione("...");
            obj_6.setPrezzoUnitario(30000);
            obj_6.setQuantita(3);
            obj_6.setId(5);
            obj_6.setIdVenditore(1);
            listaAuto.add(obj_6);

            // Oggetto 7
            CarSale obj_7 = new CarSale();
            obj_7.setNomeAuto("Isorivolta Grifo90");
            obj_7.setUrlImmagine("Images/ISORIVOLTA_grifo_90.jpg");
            obj_7.setDescrizione("...");
            obj_7.setPrezzoUnitario(70000);
            obj_7.setQuantita(1);
            obj_7.setId(6);
            obj_7.setIdVenditore(1);
            listaAuto.add(obj_7);

            // Oggetto 8
            CarSale obj_8 = new CarSale();
            obj_8.setNomeAuto("Panda 4x4 Sisley");
            obj_8.setUrlImmagine("Images/panda4x4_sisley.jpg");
            obj_8.setDescrizione("...");
            obj_8.setPrezzoUnitario(5600);
            obj_8.setQuantita(1);
            obj_8.setId(7);
            obj_8.setIdVenditore(2);
            listaAuto.add(obj_8);
        }
        return listaAuto;
    }  
    
    /** Restiuisce l’oggetto avente l’identificatore passato per parametro 
     *  @return  restiuisce l’oggetto avente l’identificatore passato per parametro 
     */
    public CarSale getAutoSaleById(int id){
        for(CarSale auto : getAutoSaleList()){
            if(auto.getId() == id)
                return auto;
        }
        return null; // Se l'oggetto non è presente nella lista allora restituisco null
    }
    
    /** Restiuisce gli oggetti avente venditore passato per parametro 
     *  @return  restiuisce gli oggetti avente venditore passato per parametro 
     */
    public ArrayList<CarSale> getAutoSaleBySeller(int idVenditore){
        Seller venditore;
        ArrayList<CarSale> lista = new ArrayList<>();
        
        if(SellersFactory.getInstance().getSellerById(idVenditore) != null)
            venditore = SellersFactory.getInstance().getSellerById(idVenditore);
        else
            return null;
        
        for(CarSale auto : getAutoSaleList()){
            if(auto.getIdVenditore() == idVenditore)
                lista.add(auto);
        }
        return lista;
    }
    
    /** Questo metodo viene chiamato nel momento in cui, in una transazione, il pagamento è andato a buon fine. Se la
     *  quantita è pari a uno l'oggetto viene eliminato, altrimenti viene dimuito di un unità il numero degli esemplari
     *  @param idAuto id dell'auto da eliminare 
        @return true se l'oggetto è stato eliminato correttamente altrimenti false
    */
    public boolean removeAuto(int idAuto){
        CarSale auto = getAutoSaleById(idAuto);
        if(auto.getQuantita()== 1){
            return listaAuto.remove(auto);
        }
        else{
            auto.setQuantita(auto.getQuantita()-1);
            return true;
        }
    }
    
    /** Inserisce un nuovo oggetto nella lista
     *  @param auto auto da aggiungere
        @return true se l'oggetto è stato inserito correttamente altrimenti false
    */
    public boolean addAuto(CarSale auto){
        return listaAuto.add(auto);
    }
    
    /** Clacola il nuovo id per un nuovo oggetto da inserire in modo che sia diverso dagli altri
     *  @return id per il nuovo oggetto da inserire
     */
    public int getNewId(){
        int nuovoId = -1;
        
        if(listaAuto == null)
            CarSaleFactory.getInstance().getAutoSaleList();
        
        for(CarSale c: listaAuto){
           if(c.getId() > nuovoId)
                nuovoId = c.getId();
        }
        return nuovoId+1;
    }
}