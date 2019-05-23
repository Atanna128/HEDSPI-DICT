package sample.Model;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.String;


 public class Dict {

    private String open = "{";
    private String next = ";";
    private String end = "}";
    String dictname;
    TreeMap<String,String> dictionary = new TreeMap<>();
    // Format of sample dictionary   # name # {word ; meaning}


     public TreeMap<String,String> read(File filename) throws FileNotFoundException {
        TreeMap<String,String> map;
        Scanner scanner = new Scanner(filename);
        map = inputdata(scanner);
        scanner.close();
        return  map;
    }

     public String getdictname(Scanner scanner) {
        String check;
        String dictname = "";
        int i = 0;

        while (scanner.hasNext()) {
            check = scanner.next();
            if (check.equals("#")) {
                while (!(check = scanner.next()).equals("#")) {
                    if (i == 0 ) {
                        dictname = dictname  + check;
                    }
                    else dictname = dictname + " " + check;
                    i=1;
                } // lấy dict-name trong # #
                break;
            }
        }
//        this.dictname = dictname;
        return dictname;
    }


      public TreeMap<String,String> inputdata(Scanner scanner) {
        TreeMap<String,String> a = new TreeMap<>();
        String get;
        String name;
        String meaning;
        int i = 0;
        int j = 0;
        Word word = new Word();
        try {
            while (scanner.hasNext()) {
                word.reset();
                name = "";
                meaning = "";
                while (!(scanner.next().equals(open))) {
                }
                while (!((get = scanner.next()).equals(next))) {
                    if (i == 0){
                        name = name + get;
                    }
                    else name = name + " " + get;
                    i = 1;
                }


                while (!((get = scanner.next()).equals(end))) {
                    if(get.equals("+")||get.equals("--")||get.equals("*")) {
                        meaning = meaning +  "\n";
                    }
                    if (j == 0){
                        meaning = meaning + get;
                    }
                    else meaning = meaning + " " + get;
                    j = 1;
                }
                i=0;
                j=0;
                word.setName(name);
                word.setMeaning(meaning);
                a.put(word.getName(),word.getMeaning());
            }
        } catch (NoSuchElementException e) {
            System.out.println("No word found!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }


}

