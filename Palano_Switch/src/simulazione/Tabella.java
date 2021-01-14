/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulazione;
import java.util.*;
/**
 *
 * @author apala
 */
public class Tabella {
    private Vector <Campo> c;
    private Vector<String> macs; // mac sorgenti letti dal file
    private Vector<String> macd; // mac destinatari letti dal file
    private Vector<String> p; //porte lette dal file
    private Vector<String> port; //elenco porte elaborate da nPort()
    private int nport; //numero porte;
    Tabella(Vector<String> porta, Vector<String> macs, Vector<String> macd, int nport, Vector<String> port){
        c = new Vector();
        this.p = porta;
        this.macs = macs;
        this.macd = macd;
        this.nport = nport;
        this.port = port;
        for(int i = 0; i < nport; ++i){ //Riempo l'array di oggetti di tipo campo (array che riassume la tabella) con tanti oggetti di tipo campo (inizialmente vuoti) quante sono il numero delle porte dello switch
            c.add(new Campo());
            c.elementAt(i).setPorta(port.elementAt(i));
        }
        creaTab();
        stampaTab();
    }
    
    private void creaTab(){
        int index[] = new int [0];
        int dest [] = new int [1];
        int in = 0;
        String portFile = "";
        for(int i = 0; i < p.size(); ++i){
            System.out.println("------------------------\nMessaggio numero " + (i + 1) + ": \n");
            in = find(p.elementAt(i));
            if(in == -1){
                System.out.println("Errore!");
            }
            else{
                c.elementAt(in).setSrc(macs.elementAt(i));
            }
            /*
            if(check(p.elementAt(i), index)){ 
                c.elementAt(i).setPorta(p.elementAt(i));
                c.elementAt(i).setSrc(macs.elementAt(i));
            }
            else{
                c.elementAt(index[0]).setSrc(macs.elementAt(index[0]));
            }
            */
            System.out.println("Ricevuto messaggio da " + macs.elementAt(i) + " sulla  porta " + p.elementAt(i) + " con destinatario " + macd.elementAt(i));
            if(thereIs(macd.elementAt(i), dest, i)){
                System.out.println("Inoltro messaggio sulla porta " + c.elementAt(dest[0]).getPorta() + " ai seguenti dispositivi: ");
                c.elementAt(dest[0]).stampaSrc();
            }
            else{
                System.out.println("Indrizzo destinatario non presente sulla tabella dei mac-address!\nInvio del messaggio in broadcast!");
            }
            System.out.println("------------------------\n");
        }
    }
    private void stampaTab(){
        for(int i = 0; i < c.size(); i++){
            if(c.elementAt(i).getPorta().equalsIgnoreCase("")){
                c.remove(i);
            }
        }
        System.out.println("Stampa tabella: ");
        for(int i = 0; i < c.size(); ++i ){
            c.elementAt(i).stampaCampi();
        }
    }
    private int find(String port){
        int index = 0;
        for(int i = 0; i < c.size(); ++i){
            if(port.equalsIgnoreCase(c.elementAt(i).getPorta())){
                index = i;
                return index;
            }
        }
        return -1;
    }
    private boolean thereIs(String macdst, int dest [], int index){
        boolean cmp = true;
        Vector<String> strtmp = new Vector();
        for(int i = 0; i < c.size(); ++i){
            if( i != index ){
                c.elementAt(i).cpySrc(strtmp); //copia mac sorgenti sorgenti dell' i-esimo campo in strtmp
                for(String tmp : strtmp ){
                    if(tmp.equalsIgnoreCase(macdst)){
                        dest[0] = i;
                        System.out.println("La destinazione vale: " + dest[0]);
                        return true;
                        //deve ritornare vero
                    }
                }
            }
            else{
                cmp = false;
            }
        }
        return cmp;
    }
    private boolean check(String p, int [] index){
       boolean cmp = true;
       for(int i = 0; i < c.size(); ++i){
           if(c.elementAt(i).getPorta().equalsIgnoreCase(p)){
               cmp = false;
               index[0] = i;
               break;
           }
           else{
               cmp = true;
           }
       }
       return cmp;
    }
}

