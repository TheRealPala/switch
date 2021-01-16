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
public class Switch {
    private Vector<String> macs; // mac sorgenti letti dal file
    private Vector<String> macd; // mac destinatari letti dal file
    private Vector<String> porta; //porte lette dal file
    private Vector<String> port; //elenco porte elaborate da nPort()
    private int nport;
    private Tabella t;
    Switch(){
        macs = new Vector(); //inizializzazione vettori dinamici per lettura da file e per elaborazione porte
        macd = new Vector();
        porta = new Vector();
        port = new Vector();
        new Reader(porta, macs, macd); //lettura da file (mette le porte lette dentro porta, i mac sorgenti dentro macs ed i mac destinatari dentro macd)
        nport = calcPorte(); //calcolo numero effettivo di porte e salvataggio di quest'ultime dentro port[]
        t = new Tabella(porta, macs, macd, nport, port);
    }
    private int calcPorte(){ //analizza le porte dei messaggi, estrapola quali sono i numeri delle porte, li salva in una collections dinamica: port[] e ritorna il numero effettivo di quest'ultime.
        int n = 1;          // Il metodo potrebbe ottenere anche il numero effettivo delle porte ma, dopo l'ultima modifica, questo dato viene passato come parametro. 
        port.add(porta.elementAt(0));
        boolean flag = false;
        for(int i = 1; i < porta.size(); ++i){
            String str = porta.elementAt(i);
            for(String iter : port){
                flag = str.equalsIgnoreCase(iter);
                if(flag)
                    break;
            }
            if(flag == false){
                n += 1;
                port.add(str);
            } 
        }
        bubblesort();
        return n;
    } 
    private void bubblesort(){ //mette in ordine crescente le porte salvate da calcPorte(): l'ordinamento viene fatto perchè, a livello visivo, l'output finale risulta più intuitivo ed estetiamente migliore.
        String tmp = "";
        for(int i = 0; i < port.size(); ++i){
            for(int j = 0; j < port.size(); ++j){
                if(Integer.parseInt(port.elementAt(i))<= Integer.parseInt(port.elementAt(j))){
                    tmp = port.elementAt(i);
                    port.set(i, port.elementAt(j));
                    port.set(j, tmp);  
                }
            }
        }
    }
}
