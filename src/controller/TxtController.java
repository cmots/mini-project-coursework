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

    private final String pathname = "src\\resource\\sampleQandA.txt";

    private int problemsNum = 0;

    private ArrayList<String> strings;

    public int getProblemsNum() {
        return problemsNum;
    }

    public ArrayList<String> getStrings() {
        return strings;
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
        if(!line.equals("")){
            updateProblemsNum();
        }
        this.strings = new ArrayList<>();
        while(line!=null){
            line=bufferedReader.readLine();
            if(line.equals("")) {
                updateProblemsNum();
            }
            else{
                this.strings.add(line);
            }
        }
    }

    /**
     * while read a empty line, it means a problem is ending
     * @author: cmots
     * @param
     * @return
     */
    public void updateProblemsNum(){
        this.problemsNum++;
    }

}
