/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulazione;
import java.security.SecureRandom;
import java.util.*;
/**
 *
 * @author apala
 * La seguente classe e' di proprieta di Alessandro Palano.
 * Contiene vari metodi che velocizzano la programmazione Java, evitando di dover scrivere numerose volte le stesse parti di codice
 */
public class Input {

    private String randStr(int dim){ //dim vuole la lunghezza della stringa che viene generata
        /** 
         * Restituisce una stringa alfanumerica casuale, vuole come parametro un
         * numero intero che idenfica la lunghezza della stringa da generare.
         * @param dim   Lunghezza stringa da generare
         * @returns     Stringa generata
         */
        String str = "";
        int cmp = dim % 8;
        if(cmp == 0){
            str = RandGen();
            for(int i = 0; i < dim - 1; i++){
                str = str + RandGen();
            }
        }
        else{
            int volte =  (int) dim / 8;
            volte += 1;
            str = RandGen();
            for(int i = 0; i < volte - 1; i++){
                str = str + RandGen();
            }
            str = str.substring(0, dim);
        }
        return str;
    }
    
    private String RandGen(){ // funzionale a randStr();
        SecureRandom rnd = new SecureRandom();
        byte[] token = new byte[16];
        rnd.nextBytes(token);
        String ret = token.toString();
        ret = ret.substring(3);
        return ret;
    }
    public void pause(int t, Thread th){
        /** 
         *Mette in pausa un determinato Thread per un tempo t, gestisce eventuali eccezioni generate.
         * @param t     Tempo in millisecondi da aspettare
         * @param th    Thread da rallentare
         * @throws      InterruptedException
         */
        try{
            th.sleep(t);
        }
        catch(InterruptedException e){
            System.out.println("Errore di temporizzazione! ");
        }
    }
    public int randInt(int max, int min){
        /** 
         *Restituisce un numero intero casuale compreso tra due numeri.
         * @param max     Valore minimo generabile casualmente
         * @param th      Valore massimo generabile casualmente
         * @return        Numero intero generato
         */
        Random r = new Random();
        int val = 0;
        if(max < min){
            max += min;
            min = max - min;
            max -= min;
        }
        if(max == min){
            return r.nextInt(max) + 1;
        }
        else{
            val = r.nextInt(max - (min - 1));
        }
        val += min;
        return val;
    }
    public double randDouble(double max, double min){
        /** 
         *Restituisce un numero Double casuale compreso tra due numeri.
         * @param max     Valore minimo generabile casualmente
         * @param th      Valore massimo generabile casualmente
         * @return        Numero Double generato
         */
        Random r = new Random();
        double val = 0;
        if(max < min){
            max += min;
            min = max - min;
            max -= min;
        }
        if(max == min){
            return ((r.nextDouble() * (max - 1)) + 1);
        }
        else{
            val = (r.nextDouble() * (max - (min - 1)));
        }
        val += min;
        return val;
    }
    public short inShort(String str){
        /** 
         *Input di un dato di tipo short maggiore di zero, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        Numero short maggiore di 0
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' negativo o ugale a 0
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        short numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextShort();
                if(numc <= 0)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public short inShortM(String str, short max, short min){
        /** 
         *Input di un dato di tipo short compreso tra due numeri, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param max     Numero di tipo short identificante il numero massimo che puo' essere inserito
         * @param min     Numero di tipo short identificante il numero minimo che puo' essere inserito
         * @return        Numero short compreso tra max e min
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' maggiore di max o minore di min
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        short numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextShort();
                if(numc < min || numc > max)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public short inShortM(String str, short min){
        /** 
         *Input di un dato di tipo short piu' grande di un numero (da dare come parametro), gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param min     Numero short che identifica il numero sopra il quale si deve trovare l'input
         * @return        Numero short maggiore di min
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' minore o uguale a min
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        short numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextShort();
                if(numc <= min)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public double inDouble(String str){
        /** 
         *Input di un dato di tipo double piu' grande di zero, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        Numero double maggiore di zero
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' minore o uguale a 0
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */ 
        double numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextDouble();
                if(numc <= 0)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
     public double inDoubleM(String str, double max, double min){
         /**
         * Input di un dato di tipo double compreso tra due numeri, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param max     Numero di tipo double identificante il numero sotto al quale si deve trovare l'input
         * @param min     Numero di tipo double identificante il numero sopra al quale si deve trovare l'input
         * @return        Numero double compreso tra max e min
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' maggiore di max o minore di min
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        double numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextDouble();
                if(numc < min || numc > max)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public double inDoubleN(String str){
         /**
         * Input di un dato di tipo double senza alcun vincolo riguardante il valore del numero, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        Numero double non vincolato
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito non e' conforme al tipo di dato double
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        double numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextDouble();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public double inDoubleM(String str, double min){
        /** 
         *Input di un dato di tipo double piu' grande di un numero (da dare come parametro), gestisce eventuali eccezioni richiedendo in input il dato in questione.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param min     Numero short che identifica il numero sopra il quale si deve trovare l'input
         * @return        Numero double maggiore di min
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' minore o uguale a min
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        double numc = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numc = sc.nextDouble();
                if(numc <= min)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numc;
    }
    public int inInt(String str){
        /** 
         *Input di un dato di tipo int piu' grande di zero, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        Numero int maggiore di zero
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' minore o uguale a 0
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */ 
        int numa = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numa = sc.nextInt();
                if(numa <= 0)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numa;
    }
    public int inIntM(String str, int max, int min){ 
        /**
         * Input di un dato di tipo int compreso tra due numeri, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param max     Numero di tipo double identificante il numero sotto al quale si deve trovare l'input
         * @param min     Numero di tipo double identificante il numero sopra al quale si deve trovare l'input
         * @return        Numero int compreso tra max e min
         * @throws        ArithmeticException: Il metodo genera la suddetta eccezione se il dato inserito e' maggiore di max o minore di min
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido 
         */
        int numa = 0;
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(str);
                numa = sc.nextInt();
                if(numa < min || numa > max)
                        throw new ArithmeticException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                sc.nextLine();
            }
        }while(cmp);
        return numa;
    }
     public boolean sn_in(String str){
       /**
         * Metodo di tipo booleanp che stampa una frase in ouput e aspetta una risposta da parte dell'utente che puo' essere si' o no, in base alla risposta restistuisce TREUE (si) o FALSE (no).
         * @param str     Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        True o False
         */
       String sn;
       Scanner s = new Scanner(System.in);
       while(true){
           System.out.println(str);
           sn = s.nextLine();
           if(sn.equalsIgnoreCase("si") || sn.equalsIgnoreCase("s"))
               return true;
           else if(sn.equalsIgnoreCase("no") || sn.equalsIgnoreCase("n"))
               return false;
           else{
               System.out.println("Input errato!!! Riprovare!");
           }
        }
    }
     public String inStr(String s){
         /**
         * Input di una stringa, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param s       Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @return        Stringa presa in input
         * @throws        ArithmeticException
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido o se la stringa e' vuota
         */
        String str = "";
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(s);
                str = sc.nextLine();
                if(str.isBlank() || str.isEmpty())
                    throw new InputMismatchException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!\n");
                //sc.nextLine();
            }
        }while(cmp);
        return str;
     }
     public String inStrP(String s, String a, String b){
         /**
         * Input di una stringa che deve essere uguale ad una delle stringhe passate come parametri, gestisce eventuali eccezioni richiedendo in input il dato.
         * @param s       Stringa da mostrare in output per far capire in runtime che cosa si deve inserire
         * @param a       Prima stringa da confrontare con quella presa da input
         * @param b       Seconda stringa da confrontare con quella presa da input
         * Se la stringa presa in input e' diversa sia da a che da b, viene richesto l'input
         * @return        Stringa presa in input
         * @throws        ArithmeticException
         * @throws        InputMismtachExeption: Si genera l'eccezione in questione se l'utente mette in input un dato non valido, se la stringa e' vuota o se e' diversa da a e da b
         */
        String str = "";
        boolean cmp = false;
        Scanner sc = new Scanner(System.in);
        do{
            cmp = false;
            try{
                System.out.println(s);
                str = sc.nextLine();
                if((!((str.equalsIgnoreCase(a)) || (str.equalsIgnoreCase(b)))) ||str.isBlank() || str.isEmpty() )
                    throw new InputMismatchException();
            }
            catch(ArithmeticException | InputMismatchException e){
                cmp = true;
                System.out.println("Input non corretto!");
                //sc.nextLine();
            }
        }while(cmp);
        return str;
     }
     /*
     boolean thereisN(Docenti[] d, int dim, String nome){
         boolean th = false;
         for(int i = 0; i < dim; i++){
             if(nome.equalsIgnoreCase(d[i].getName())){
                 th = true;
             }
         }
         return th;
     }
     boolean thereisS(Docenti[] d, int dim, String materia){
         boolean th = false;
         for(int i = 0; i < dim; i++){
             if(materia.equalsIgnoreCase(d[i].getMat())){
                 th = true;
             }
         }
         return th;
     }
     */
      public int scelta(){
       /**
         * Metodo di tipo int che viene utilizzato come menu a scelta multipla per gestire l'interazione con l'utente: stampa in output una stringa e prende in input un numero, nel caso di errori di inserimento si chiede nuovamente l'input dell'utente.
         * @return        Stringa presa in input
         */
       String sn;
       Scanner s = new Scanner(System.in);
       while(true){
           System.out.println("\nOpzioni disponibili:\n1) Aggiungi prodotti\n2) Stampa di tutti i prodotti\n3) Stampa dati distributore\n4) Registrati\n5) Comprare snack/bibite \n6) Comprare tabacchi\n7) Rifornimento prodotti\n8) Uscire");//6) Importo totale parziale (senza spedizione)\n7) Uscire");
           sn = s.nextLine();
           if(sn.equalsIgnoreCase("1") || sn.equalsIgnoreCase("Aggungi prodotto") || sn.equalsIgnoreCase("Aggungi"))
               return 1;
           else if(sn.equalsIgnoreCase("2") || sn.equalsIgnoreCase("Stampa di tutti i prodotti") || sn.equalsIgnoreCase("tuti i prodotti"))
               return 2;
           else if(sn.equalsIgnoreCase("3") || sn.equalsIgnoreCase("Stampa dati distributore") || sn.equalsIgnoreCase("dati distributore"))
               return 3;
           else if(sn.equalsIgnoreCase("4") || sn.equalsIgnoreCase("Registrati") || sn.equalsIgnoreCase("Registrarsi"))
               return 4;
           else if(sn.equalsIgnoreCase("5") || sn.equalsIgnoreCase("Comprare snack/bibite") || sn.equalsIgnoreCase("Comprare cibo"))
               return 5;
           else if(sn.equalsIgnoreCase("6") || sn.equalsIgnoreCase("Rifornire tabacchi") || sn.equalsIgnoreCase("tabacchi") )
               return 6;
           else if(sn.equalsIgnoreCase("7") || sn.equalsIgnoreCase("Rifornire prodotti") || sn.equalsIgnoreCase("Rifornire") )
               return 7;
           else if(sn.equalsIgnoreCase("8") || sn.equalsIgnoreCase("Uscire"))
               return 8;
           else{
               System.out.println("Input errato!!! Riprovare!");
           }
       }
   }
      /*
    public String cla(String str){
       String sn;
       Scanner s = new Scanner(System.in);
       System.out.println(str);
       while(true){
           System.out.println("\nOpzioni disponibili:\n1) Merendina\n2) Bibita"); //\n3) Elimina prodotto:  \n4) Esci "); //\n5) Stampa dei pazienti sovrappeso \n6) Uscire\n" ); // 5) Iva totale\n6) Importo totale parziale (senza spedizione)\n7) Uscire");
           sn = s.nextLine();
           if(sn.equalsIgnoreCase("1") || sn.equalsIgnoreCase("Merendina") || sn.equalsIgnoreCase("Snack"))
               return "Merendina";
           else if(sn.equalsIgnoreCase("2") || sn.equalsIgnoreCase("Bibita") || sn.equalsIgnoreCase("Bevanda"))
               return "Bibita";
           /*
           else if(sn.equalsIgnoreCase("4") || sn.equalsIgnoreCase("Stampa dei pazienti sottopeso") || sn.equalsIgnoreCase("sottopeso"))
               return 4;
           else if(sn.equalsIgnoreCase("5") || sn.equalsIgnoreCase("Stampa dei pazienti sovrappeso") || sn.equalsIgnoreCase("sovrappeso"))
               return 5;
           
           else if(sn.equalsIgnoreCase("6") || sn.equalsIgnoreCase("Importo totale") || sn.equalsIgnoreCase("totale") )
               return 6; 
            
           else if(sn.equalsIgnoreCase("4") || sn.equalsIgnoreCase("Uscire"))
               return 4;
            
           else{
               System.out.println("Input errato!!! Riprovare!\n" + str);
           }
       }
   }
   public String clb(String str){
       String sn;
       Scanner s = new Scanner(System.in);
          System.out.println(str);
       while(true){
           System.out.println("\nOpzioni disponibili:\n1) Sigarette\n2) Tabacco sfuso"); //\n3) Elimina prodotto:  \n4) Esci "); //\n5) Stampa dei pazienti sovrappeso \n6) Uscire\n" ); // 5) Iva totale\n6) Importo totale parziale (senza spedizione)\n7) Uscire");
           sn = s.nextLine();
           if(sn.equalsIgnoreCase("1") || sn.equalsIgnoreCase("Sigarette") || sn.equalsIgnoreCase("siga"))
               return "Sigarette";
           else if(sn.equalsIgnoreCase("2") || sn.equalsIgnoreCase("Tabacco") || sn.equalsIgnoreCase("Tabacco sfuso"))
               return "Tabacco sfuso";
           /*
           else if(sn.equalsIgnoreCase("4") || sn.equalsIgnoreCase("Stampa dei pazienti sottopeso") || sn.equalsIgnoreCase("sottopeso"))
               return 4;
           else if(sn.equalsIgnoreCase("5") || sn.equalsIgnoreCase("Stampa dei pazienti sovrappeso") || sn.equalsIgnoreCase("sovrappeso"))
               return 5;
           
           else if(sn.equalsIgnoreCase("6") || sn.equalsIgnoreCase("Importo totale") || sn.equalsIgnoreCase("totale") )
               return 6; 
            
           else if(sn.equalsIgnoreCase("4") || sn.equalsIgnoreCase("Uscire"))
               return 4;
            
           else{
               System.out.println("Input errato!!! Riprovare!\n" + str);
           }
       }
   }
   */
      
}
