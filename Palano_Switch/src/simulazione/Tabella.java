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
    /*
    Nota bene: La tabella è fatta da un array dinamico contenente tanti oggetti di tipo campo, ogni oggetto ha due attributi: 
    La porta dalla quale è arrivato il messaggio ed un vettore dinamico di strighe contenente i MAC sorgenti da mappare.
    I mac sorgenti sono contenuti in un array dinamico per gestire il caso in cui ad una porta corrispondano più indirizzi MAC. 
    */
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
        for(int i = 0; i < nport; ++i){ 
            c.add(new Campo());         //Riempo l'array di oggetti di tipo campo (array che riassume la tabella) con tanti oggetti di tipo campo (inizialmente vuoti) quante sono il numero delle porte dello switch.
            c.elementAt(i).setPorta(port.elementAt(i));//Successivamente vado a popolare l'attributo porta della singola cella con le porte fornitemi dal parametro port (vettore dinamico di strighe).
        }
        creaTab();
        stampaTab();
    }
    
    private void creaTab(){
        /*
        Si noti che il metodo è rallentato appositamente per favorire la lettura dell'output (vedi rallenta()).
        Il metodo creaTab crea la tabella dello switch mappando la porta dei messaggi all'indirizzo mac sorgente.
        */
        int dest [] = new int [1];
        int in = 0;
        String portFile = "";
        for(int i = 0; i < p.size(); ++i){
            System.out.println("------------------------\nMessaggio numero " + (i + 1) + ": \n");
            in = find(p.elementAt(i)); //cerca la posizione della porta letta dal file (p.elementAt(i) all'interno del vettore campo. Se non la trova ritorna -1
            if(in == -1){
                System.out.println("Errore!\nPorta non trovata!"); //se la porta non viene trovata, mando in output un messaggio di errore e passo al messaggio successivo (continue)
                continue;
            }
            else{
                c.elementAt(in).setSrc(macs.elementAt(i));
            }
            System.out.println("Ricevuto messaggio da " + macs.elementAt(i) + " sulla  porta " + p.elementAt(i) + " con destinatario " + macd.elementAt(i));
            if(thereIs(macd.elementAt(i), dest, i)){ // se thereIs ritorna true, vuoldire che il mac è contenuto nella tabella, altrimenti il mac non è disponibile nella tabella e quindi mando il messaggio in broadcast 
                System.out.println("Inoltro messaggio sulla porta " + c.elementAt(dest[0]).getPorta() + " a : " + macd.elementAt(i) + "\nDispositivi collegati sulla porta " + c.elementAt(dest[0]).getPorta() + ": ");
                c.elementAt(dest[0]).stampaSrc();
            }
            else{
                System.out.println("Indrizzo destinatario non presente sulla tabella dei mac-address!\nInvio del messaggio in broadcast!");
            }
            System.out.println("------------------------\n");
            rallenta(Thread.currentThread(), 350);
        }
    }
    private void stampaTab(){ //Stampa la tabella creata
        System.out.println("Stampa tabella: ");
        for(int i = 0; i < c.size(); ++i ){
            c.elementAt(i).stampaCampi();
        }
    }
    private int find(String port){ //metodo a cui viene passata una porta dal file, e ritorna la posizione di quest'ultima all'interno del vettore di oggetti campo, se non trova la posizione della porta nel vettore, ritorna -1
        int index = 0;
        for(int i = 0; i < c.size(); ++i){
            if(port.equalsIgnoreCase(c.elementAt(i).getPorta())){
                index = i;
                return index;
            }
        }
        return -1;
    }
    private boolean thereIs(String macdst, int dest [], int index){ //metodo che va a vedere se il mac destinatario esiste nella tabella:
        boolean cmp = true;                                         // se esiste, salva la posizione di quest'ultimo dentro dest[0] e successivamente returna true, altrimenti false:
        Vector<String> strtmp = new Vector();                       // in questo caso il mac non è contenuto nella tabella e quindi mando il messaggio in broadcast
        for(int i = 0; i < c.size(); ++i){
            if( i != index ){
                c.elementAt(i).cpySrc(strtmp); //copia mac sorgenti sorgenti dell' i-esimo campo in strtmp (vettore di stringhe temporaneo)
                for(String tmp : strtmp ){
                    if(tmp.equalsIgnoreCase(macdst)){
                        dest[0] = i;
                        return true;
                    }
                }
            }
            else{
                cmp = false;
            }
        }
        return cmp;
    }
    private void rallenta(Thread t, int milliseconds){ //metodo che rallenta il thread che gli viene passqato tra i parametri di tanti millisecondi quanti sono quelli che gli si passa con il parametro "millisecond" 
        try{
            t.sleep(milliseconds);
        }
        catch(InterruptedException e){
            System.out.println("Errore di sincronizazione!");
        }
    }
}


