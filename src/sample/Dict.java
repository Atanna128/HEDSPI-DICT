package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;
import java.lang.String;


class Dict {

    public String open = "{";
    public String next = ";";
    public String end = "}";
    String dictname;
    TreeMap<Word> dictionary = new TreeMap<>();
//    TreeMap<String, String> dict = new TreeMap<>();
    // # name # {word ; meaning}


    public TreeMap<String,String> read(String filename) throws FileNotFoundException {
        TreeMap<String,String> a = new TreeMap<>();
        String dictname;
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        dictname = getdictname(scanner);
        a = inputdata(scanner);
//        getalldata(a);
        scanner.close();
        return  a;
    }

    public String getdictname(Scanner scanner) {
        String check;
        String dictname = "";
        String errorstring = "";
        int errorcode = 0;

        while (scanner.hasNext()) {
            check = scanner.next();
            if (check.equals("#")) {
                while (!(check = scanner.next()).equals("#")) {
                    dictname = dictname + " " + check;
                } // lấy xong dict-name trong # #
                break;
            } else {
                errorcode++;
                errorstring = errorstring + " " + check;
            }
        }
        if (errorcode > 0)
            System.out.println("Dictionary should start with # dict-name #" + "\nMisformatted string : " + errorstring);

        return dictname;
    }

    public void getalldata(TreeMap<String,String> a) {
        // System.out.println("Word : Meaning");
        for (Map.Entry<String, String> entry : a.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }

    public TreeMap<String,String> inputdata(Scanner scanner) {

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
                a.put(word, meaning);

            }
        } catch (NoSuchElementException e) {
            System.out.println("Unnecessary text found at the end of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  a;
    }

    private static void createnewdict() throws IOException {
        Dict dictionary = new Dict();
        String getname;
        System.out.println("Enter new dictionary name");
        Scanner scanner = new Scanner(System.in);
        getname = scanner.nextLine();
        dictionary.create(getname);
        // dictionary

        scanner.close();
    }


    void create(String name) throws IOException { // create a text file for dictionary
        String filename = name + ".txt";
        File file = new File(filename);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write("#" + name + "#");

        fw.close();
    }
}

