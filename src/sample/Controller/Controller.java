package sample.Controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.image.*;
import javafx.stage.Stage;
import sample.Model.Dict;
import sample.InitializeDict;

import java.io.*;
import java.net.URL;
import java.util.*;


public class Controller extends InitializeDict implements Initializable {
    @FXML
    public TextField inputText;
    public TextArea outputText;
    public ImageView editbutton;
    public ListView<String> dictList;
    public ImageView deletebutton;
    public ChoiceBox<String> choicebox;

    public TextField wordAdd;
    public TextArea meaningAdd;

    public Scene addScene;
    private static TreeMap<String,String> dictionary;
    private static String dictname;
    public static ArrayList<String> order = new ArrayList<>();



    // Trong controller này có 2 kiểu đối tượng TreeMap và ListView. Treemap là nơi danh sách các từ được load vào, đồng
    // thời được add vào ListView để hiển thị.
    // Trên lý thuyết, để tối ưu thì ta phải coi TreeMap như 1 ObservableList và truyền cho ListView. Khi đó, nếu có
    // bất cứ thay đổi nào ở ObservableList thì ListView sẽ tự cập nhật, và khi đấy có thể lược bỏ hàm updateListView()

    // hàm initialize là hàm khởi tạo, ở đây tất cả các chức năng ( trừ addWord/addDict) đều tương tác với nội dung
    // từ điển ( TreeMap) vì vậy phải load toàn bộ dữ liệu của từ điển ( Trong file thuộc folder listDictionary) vào
    // 2 kiểu đối tượng TreeMap / String dictname


    //WORKING :
    // open new dictionary from primaryStage
    // fix resizable primaryStage

    //done
    public void addWordScene(ActionEvent event) throws IOException {
        FXMLLoader addParent = new  FXMLLoader(getClass().getResource("sample/Fxml/addWord.fxml"));
        addScene =new Scene(addParent.load());
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(addScene);
        window.show();
    }

    //done
    public void addDictScene(ActionEvent event) throws IOException {
//        // copy từ main
//        Parent addParent = FXMLLoader.load(getClass().getResource("addDict.fxml"));
//        Scene addScene =new Scene(addParent);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(addScene);
//        window.show();
    }

    public void backToMainScene(ActionEvent event) throws IOException {
//        Parent addParent = FXMLLoader.load(getClass().getResource("../Fxml/Main.fxml"));
//        Scene addScene =new Scene(addParent);
//        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
//        window.setScene(addScene);
//        window.show();
    }

    public void AddtoMainScene(ActionEvent event) throws IOException {
        String wordadd = wordAdd.getText();
        String meaningadd = meaningAdd.getText();
        dictionary.put(wordadd,meaningadd);
        updateToFile(dictname);
        backToMainScene(event);
    }

    //done
    // set editable for the word's meaning
    public void edit(MouseEvent mouseEvent){
        if(outputText.isEditable()){  // khóa edit để bắt đầu update từ
            outputText.setEditable(false);
            editMeaning();
        }else {
            outputText.setEditable(true); // mở để bắt đầu edit
        }
    }

    //done
    public void delete(MouseEvent event){
        try {
            String word = inputText.getText();
            String name = dictList.getSelectionModel().getSelectedItem();
            System.out.println("|"+ word + "|");
            if (dictionary.containsKey(name)){
                dictionary.remove(name);
                System.out.println("remove " + name);
            }else if (dictionary.containsKey(word)){
                System.out.println("remove " + word);
                dictionary.remove(word);
            }else if (!dictionary.containsKey(word)){
                System.out.println("not contain");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        outputText.clear();
        inputText.clear();
        updateListView();
        updateToFile(dictname);

    }

    //done
    private void updateListView() {
        dictList.getItems().clear(); // xóa ListView + add lại sau khi đã chỉnh sửa treemap
        for (Map.Entry<String,String> entry: dictionary.entrySet()) {
            String key = entry.getKey();
            dictList.getItems().add(key);
        }
    }

    //done
    private void editMeaning(){
        String word;
        String meaning;
        word =  inputText.getText();
        meaning = outputText.getText();
        if (dictionary.containsKey(word)){ // if trong trường hợp click edit khi chưa search xong 1 từ nào
            dictionary.replace(word,meaning); // update từ
        }
        updateToFile(dictname);
    }

    //done
    private void updateToFile(String filename) {
        // mở file và viết lại vào theo format định sẵn ( dựa trên Treemap / dictname đã đc gán giá trị trong hàm getDict()
        try {
            FileWriter writer = new FileWriter(getfinalpath("src/sample/listDictionary/" + filename));
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(" # " + dictname + " # "); // follow the format
            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();
                buffer.write(" { " + key + " ; " + value + " } "); // follow the format
                buffer.newLine();
            }
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void searching(Event event){
        String input = inputText.getText();
        String meaning;

        meaning = getMeaning(input);

        if (! (meaning == null)) {
            outputText.setEditable(true);
            outputText.setText(meaning);
        }else{
            outputText.clear();
        }
        outputText.setEditable(false);
        autocomplete(input);
    }

    private String getMeaning(String word){
        String notfound = null;
        Set<String> keys = dictionary.keySet();
        for (String key : keys ){
            if (key.equals(word)) return dictionary.get(key);
        }
        return notfound;
    }

    private void autocomplete(String word) {
        String recentword =  word + "(.*)";
        dictList.getItems().clear();
        for (Map.Entry<String,String> entry: dictionary.entrySet()) {
            String key = entry.getKey();
            if (key.matches(recentword)) {
                dictList.getItems().add(key);
            }
        }
    }

    public void getListItem(Event event){
        dictList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                String word = dictList.getSelectionModel().getSelectedItem();
                String meaning = getMeaning(word);
                outputText.setEditable(true);
                outputText.setText(meaning);
                outputText.setEditable(false);
            }
        });
    }

    public void openDictionary(Event event){
        choicebox.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                String name = choicebox.getSelectionModel().getSelectedItem();
                String abc = "src/sample/listDictionary/" + name;
                getDict(abc);
                dictname = name;
                updateRecentOpen(dictname);
                updateListView();
                outputText.clear();
                inputText.clear();
            }
        });

    }

    //initialize
    //list all file in folder listDictionary
    @Override
    public void listFile(File dir) {
        String getname;
        int i = 0;
        int index = i;
        File[] files = dir.listFiles();// đưa ra toàn bộ danh sách các từ điển có trong folder
        for (File file: files) {
            getname = file.getName();
            choicebox.getItems().add(getname);
            if (dictname.equals(getname)){
                index = i;
            }
            i++;
        }
        choicebox.getSelectionModel().select(index);
    }

    //initialize
    private void getDict(String filename){
        try {
            Dict dict = new Dict();
            Scanner scanner = new Scanner(new File(getfinalpath(filename)));
            dictionary = dict.read(new File(getfinalpath(filename)));
            dictname = dict.getdictname(scanner);
            for (Map.Entry<String,String> entry: dictionary.entrySet()) {
                String key   = entry.getKey();
//                System.out.println(key);
//                String value = entry.getValue();
                dictList.getItems().add(key);
            }
        }catch (FileNotFoundException e){
            System.out.println("Error in getdict(). File not found . . . ");
            e.printStackTrace();
        }
    }

    public String getRecentOpen(){
        String defaultfile = "textfield";
        String name = defaultfile;
        try{
            Scanner scanner = new Scanner(new File(getfinalpath("src/sample/recentOpen")));
            name = scanner.nextLine();
        }catch (FileNotFoundException e){
            System.out.println("File recentOpen not found");
        }
        return name;
    }

    private void updateRecentOpen(String name){
        try {
            FileWriter writer = new FileWriter(getfinalpath("src/sample/recentOpen"));
            BufferedWriter buffer = new BufferedWriter(writer);
            buffer.write(name);
            buffer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //initialize
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String foldername ="src/sample/listDictionary/";
        getDict("src/sample/listDictionary" + getRecentOpen());
        listFile(new File(getfinalpath(foldername)));

    }

}
