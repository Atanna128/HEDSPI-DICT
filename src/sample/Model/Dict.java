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

        while (scanner.hasNext()) {
            check = scanner.next();
            if (check.equals("#")) {
                while (!(check = scanner.next()).equals("#")) {
                    dictname = dictname + " " + check;
                } // lấy dict-name trong # #
                break;
            }
        }
        return dictname;
    }


      public TreeMap<String,String> inputdata(Scanner scanner) {
        TreeMap<String,String> a = new TreeMap<>();
        String get;
        String name;
        String meaning;
        Word word = new Word();
        try {
            while (scanner.hasNext()) {
                word.reset();
                name = "";
                meaning = "";
                while (!(scanner.next().equals(open))) {
                }
                while (!((get = scanner.next()).equals(next))) {
                    name = name + " " + get;
                }
                while (!((get = scanner.next()).equals(end))) {
                    meaning = meaning + " " + get;
                }
                word.setName(name);
                word.setMeaning(meaning);
                a.put(word.getName(),word.getMeaning());
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unnecessary text found at the end of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }


}

