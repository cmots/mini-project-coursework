/**
 * @author: cmots
 * @date: 2019/5/13 14:47
 * @description: controller and service of txt files
 */
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class TxtController {

    private final String pathname = "src\\resources\\sampleQandA.txt";

    private int emptyTag = 0;

    private ArrayList<String> strings;

    public ArrayList<String> getStrings() {
        return strings;
    }

    public int getEmptyTag() {
        return emptyTag;
    }

    public TxtController() throws Exception {
        readTxt();
    }
    /**
     * to read a txt file and it's the necessary of this class
     * @author: cmots
     * @param
     * @return
     * @throws Exception FileNotFoundException
     */
    public void readTxt() throws Exception{
        File filename = new File(this.pathname);
        InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line = "";
        line = bufferedReader.readLine();
        this.strings = new ArrayList<>();
        strings.add(line);
        if("".equals(line)){
            emptyTag = 1;
        }
        while(line!=null){
            line=bufferedReader.readLine();
            if(!"".equals(line)) {
                this.strings.add(line);
            }
        }
    }
}
