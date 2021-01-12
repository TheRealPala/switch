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
    private Vector<String> macs;
    private Vector<String> macd;
    private Vector<String> porta;
    private Tabella t;
    Switch(){
        macs = new Vector();
        macd = new Vector();
        porta = new Vector();
        new Reader(porta, macs, macd);
        //stampa();
        t = new Tabella(porta, macs, macd);
    }
    private void stampa(){
        for(int i = 0; i < macs.size(); ++i){
            System.out.println("Messaggio " + ( i + 1) + ": ");
            System.out.println("Porta: " + porta.get(i) + "\nMac sorgente: " + macs.get(i) + "\nMac destinatario: " + macd.get(i));
        }
    }
    private int nPorte(){
        int n = 1;
        Vector<String> tmp = new Vector();
        tmp.add(porta.elementAt(0));
        boolean flag = false;
        for(int i = 1; i < porta.size(); ++i){
            String str = porta.elementAt(i);
            for(String iter : tmp){
                flag = str.equalsIgnoreCase(iter);
                if(flag)
                    break;
            }
            if(flag == false){
                n += 1;
            }
            tmp.add(str);
        }
        return n;
    } 
}
