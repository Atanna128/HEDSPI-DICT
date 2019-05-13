package sample;

import java.io.File;

public abstract class InitializeDict {

    public void listFile(File dir){
        String getname;
        File[] files = dir.listFiles();
        for (File file: files) {
        }
    }

    public String getfinalpath(String getfile) {
        File file = new File(getfile);
        String pathname = file.getAbsolutePath();

        String out = "";
        for (int i = 0 ; i < getfile.length() ; i++){
            if (getfile.charAt(i) ==  '\\'){
                out = out + "/";
            }else out = out + getfile.charAt(i);
        }
        return  out;
    }
}
