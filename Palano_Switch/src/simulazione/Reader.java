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
    
    Reader(Vector<String> p, Vector<String> macs, Vector<String> macd){
        read(p, macs, macd);
    }
    
    private void read(Vector<String> p, Vector<String> macs, Vector<String> macd){
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
}
