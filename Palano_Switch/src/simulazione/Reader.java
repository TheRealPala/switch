/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulazione;
import java.io.*;
import java.util.*;
/**
 *
 * @author apala
 */
public class Reader {
    private int nport;
    Reader(Vector<String> p, Vector<String> macs, Vector<String> macd){
        Input in = new Input();
        nport = in.inInt("Inserire il numero di porte dello switch: ");
        read(p, macs, macd);
        checkMsg(p, macs, macd);
    }
    
    private void read(Vector<String> p, Vector<String> macs, Vector<String> macd){
        System.out.println("Si sta simulando uno switch da " + nport + " porte, i messaggi che arrivano da porte superiori alla porta numero " + nport + " verranno eliminati !" );
        try {
        File myObj = new File("dati.txt");
        Scanner myReader = new Scanner(myObj);
        while (myReader.hasNextLine()){
            String data = myReader.nextLine();
            String [] sp = data.split("\t", 3); //divide la stringa letta dal file in 3 sottostringhe: tutte le volte che incontra un '\t', divide la stringa e la aggiunge ad una cella dell'array.
            p.add(sp[0]);
            macd.add(sp[1]);
            macs.add(sp[2]);
        }
        myReader.close();
      } 
      catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
    }
    private void checkMsg(Vector<String> p, Vector<String> macs, Vector<String> macd){
        /*
        Il metodo in questione controlla che non ci siano messaggi provenienti da porte non appartenenti allo switch, nel caso le elimina e rallenta la velocità di runtime
        per favorire la lettura di eventuali messaggi eliminati.
        */
        Input in = new Input();
        for(int i = 0; i < p.size(); ++i){
            if(Integer.parseInt(p.elementAt(i)) > nport){
                System.out.println("\nIl seguente messaggio arriva da una porta non presente sullo switch e di conseguenza non verrà considerato!\nPorta\tMac destinatario\tMac sorgente");
                System.out.println(p.elementAt(i) + " \t" + macd.elementAt(i) + "\t\t" + macs.elementAt(i));
                p.remove(i);
                macs.remove(i);
                macd.remove(i);
                in.pause(250, Thread.currentThread());
                i = 0;
            }
        }
    }
}
