package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.TreeMap;


class Dict {

    private String errornameformat = "Dictionary should start with # dict-name #";
    public String open = "{";
    public String next = ";";
    public String end = "}";
    String dictname;

    TreeMap<String, String> dict = new TreeMap<>();
    // # name # {word ; meaning}

    void create(String name) throws IOException { // create a text file for dictionary
        String filename = name + ".txt";
        File file = new File(filename);
        file.createNewFile();
        FileWriter fw = new FileWriter(file);
        fw.write("#" + name + "#");

        fw.close();
    }

    private void read(String filename) throws FileNotFoundException {
        String dictname;

        // try {
        File file = new File(filename);
        Scanner scanner = new Scanner(file);
        dictname = getdictname(scanner);
        System.out.println("The dictionary name is" + dictname);
        inputdata(scanner);
        getalldata();
        scanner.close();
    }

    private String getdictname(Scanner scanner) {
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
            System.out.println(errornameformat + "\nMisformatted string : " + errorstring);

        return dictname;
    }

    private void getalldata() {
        // System.out.println("Word : Meaning");
        for (Map.Entry<String, String> entry : dict.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();
            System.out.println(key + " : " + value);
        }
    }

    private void inputdata(Scanner scanner) {
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
                    word = word + get;
                }
                while (!((get = scanner.next()).equals(end))) {
                    meaning = meaning + get;
                }
                dict.put(word, meaning);

            }
        } catch (NoSuchElementException e) {
            System.out.println("Unnecessary text found at the end of file");
        } catch (Exception e) {
            e.printStackTrace();
        }
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

}

