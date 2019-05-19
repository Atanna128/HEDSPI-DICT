package sample.Model;

public class Word {
    private String name;
    private String meaning;

    public Word(){
    }

    public Word(String name, String meaning){
        this.name = name;
        this.meaning = meaning;
    }

    public String getName(){
        return name;
    }

    public void setName(String word){
        this.name = word;
    }

    public String getMeaning(){
        return meaning;
    }

    public void setMeaning(String meaning){
        this.meaning = meaning;
    }

    public void reset(){
        this.name = "";
        this.meaning = "";
    }

    @Override
    public String toString(){
        return this.name;
    }
}
