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
public class Campo {
    private String porta;
    private Vector<String>src;
    Campo(){
        porta = "";
        src = new Vector();
    }
    public void cpySrc(Vector<String> v){
        for(int i = 0; i < src.size(); ++i){
            v.add(src.elementAt(i));
        }
    }
    public String getPorta(){
        return porta;
    }
    public void setPorta(String s){
        porta = s;
    }
    public void setSrc(String s){
        src.add(s);
    }
    public void stampaSrc(){
        for(int i = 0; i < src.size(); ++i){
            System.out.println(src.elementAt(i));
        }
    }
    public void stampaCampi(){
        System.out.println("\n--------------------Porta: " + porta);
        System.out.println("Indirizzi connessi: ");
        stampaSrc();
        System.out.println("--------------------\n");
    }
}

