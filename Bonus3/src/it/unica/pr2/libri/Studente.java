/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unica.pr2.libri;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 *
 * @author selim
 */
public class Studente extends Persona{
    public static int numStudenti =0;
   public Studente(String nome){
    super(nome);
   }
   public static Studente Studente(String name){
       //nome="";
       return new Studente(name);
   }
   public Studente geRand(int x){
       try{
       if(x<0)
       throw new Ex();
       }catch(Ex e){
       
       }
      return new Studente("");
      
   }
   public int geRand(double x){
       this.numStudenti++;
       try{
       if(x<0)
       throw new Ex();
       }catch(Ex e){
       
       }
      return 0;
      
   }
   @Override
   public Persona getRand(int x){
      return new Persona();
   }
   
     public boolean equals(Object o) {
        if (!(o instanceof Studente))
            return false;
        if(o == null) return false;
        Studente n = (Studente) o;
        return n.nome.equals(n.nome);
    }
    
    public String toString(){
         int y=0;
        return nome;
        
    }
    
    public enum Giorno {
     
    LUNEDI,
    MARTEDI,
    MERCOLEDI,
    GIOVEDI,
    VENERDI,
    SABATO,
    DOMENICA // opzionalmente può terminare con ";"
}
   public static void main(String[] args){
        Studente s=new Studente("Tullio");
        s.geRand(9);
        
        //List<Integer> intList = new ArrayList<Integer>();
        //List<? extends Number>  numList = intList;
        List<Number> list = new ArrayList<>();
        list.add(9);
        list.add(4.5);
        list.add(11);
        list.add(0);
       // Collections.sort(list, (a,b) -> a-b);
       Set<Studente> set = new HashSet<>();
       set.add(s);
       set.add(null);
       Studente carlo = new Studente("Carlo");
       set.add(carlo);
       set.add(new Studente("Aldo"));
       Studente giacomo = new Studente("Giacomo");
        set.add(giacomo);
        Studente fra = new Studente("Francesca");
         set.add(fra);
       System.out.println(set);
       //set.subSet(carlo, giacomo ).clear();
       System.out.println(set);
       Integer[] array = new Integer[]{1,2,3,4,5,6};
       System.out.println(array);
       List<Integer> listInt = Arrays.asList(array);
       System.out.println(listInt);
//       listInt.add(7);
       System.out.println(listInt);
       
      // listInt.stream().forEach(System.out.print(7));
    //   
       Studente gigi;
       gigi = new Studente("Gigi");
       Studente fabio = gigi.Studente("fabio");
       
       int prova =9;
       if(prova==0);
       System.out.println(fabio.getClass().getName());
       Integer n1 = new Integer(15);
       Integer n2 = new Integer (12);
       
       System.out.println(n1 > n2);
       
       List o = new ArrayList<String>();
       Integer[] arrayIn = new Integer[9];
       System.out.println(arrayIn instanceof Object[]);
      // boolean u = (boolean)9;
       Persona.main(null);
       
       Set<Studente> setS = new HashSet<>();
       
       HashSet<Studente> sett = (HashSet<Studente>) setS;
       
       String h = new String(fabio.toString());
       
       for( Giorno d : Giorno.values() ) {
         System.out.println(d);
       }
       Object oggetto = fabio;
        System.out.println();
       System.out.println(fabio);
       
        System.out.println(oggetto);
        Studente.Inner cc =new Studente("w"). new Inner();
   }
   
    class Inner{
   
   }
}