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
        new Reader(porta, macs, macd); //lettura da file
        nport = calcPorte(); //calcolo numero effettivo di porte e salvataggio di quest'ultime dentro port[]
        t = new Tabella(porta, macs, macd, nport, port);
    }
    /*
    private void stampa(){
        for(int i = 0; i < macs.size(); ++i){
            System.out.println("Messaggio " + ( i + 1) + ": ");
            System.out.println("Porta: " + porta.get(i) + "\nMac sorgente: " + macs.get(i) + "\nMac destinatario: " + macd.get(i));
        }
    }
    */
    private int calcPorte(){ //calcola il numero effettivo di porte, le salva in una collections dinamica: port[] e ritorna il numero di porte calcolato.
        int n = 1;
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
        return n;
    } 
}
