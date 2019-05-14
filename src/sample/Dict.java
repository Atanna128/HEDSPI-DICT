package sample;

import java.io.File;
import java.io.FileNotFoundException;

import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.String;


class Dict {

    private String open = "{";
    private String next = ";";
    private String end = "}";
    String dictname;
    TreeMap<String,String> dictionary = new TreeMap<>();
    // Format of sample dictionary   # name # {word ; meaning}


     TreeMap<String,String> read(File filename) throws FileNotFoundException {
        TreeMap<String,String> a;
        String dictname;
//        File file = new File(filename);
        Scanner scanner = new Scanner(filename);
        dictname = getdictname(scanner);
        a = inputdata(scanner);
//        getalldata(a);
        scanner.close();
        return  a;
    }

     String getdictname(Scanner scanner) {
        String check;
        String dictname = "";
        String errorstring = "";


        while (scanner.hasNext()) {
            check = scanner.next();
            if (check.equals("#")) {
                while (!(check = scanner.next()).equals("#")) {
                    dictname = dictname + " " + check;
                } // lấy xong dict-name trong # #
                break;
            } else {
                errorstring = errorstring + " " + check;
            }
        }
        if (!errorstring.equals(""))
            System.out.println("Dictionary should start with # dict-name #" + "\nMisformatted string : " + errorstring);

        return dictname;
    }


     TreeMap<String,String> inputdata(Scanner scanner) {
        TreeMap<String,String> a = new TreeMap<>();
        String get;
        String word;
        String meaning;
        try {
            while (scanner.hasNext()) {
                word = "";
                meaning = "";
                while (!(scanner.next().equals(open))) {
                }
                while (!((get = scanner.next()).equals(next))) {
                    word = word + " " + get;
                }
                while (!((get = scanner.next()).equals(end))) {
                    meaning = meaning + " " + get;
                }
                a.put(word,meaning);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Unnecessary text found at the end of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }


}

