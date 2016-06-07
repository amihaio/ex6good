package oop.ex6.main;

import java.io.IOException;
import java.util.List;

public class Sjavac {

    private SourceFileParser parser = SourceFileParser.getInstance();
    private String sourceFile;
    List<String> sJavaCode;


    public Sjavac(String sourceFile){
        this.sourceFile = sourceFile;
    }

    /**
     * validates sJava source file.
     */
    public void validate(){
        // get lines of source file
        try {
            sJavaCode = SourceFileParser.getFileLines(sourceFile);
        }
        catch (IOException e){
            System.err.println("Source file parsing error, bad source file");
        }


        parser.createClassObject()//todo create class, run its validate method.
        //  boolean isClassValid =  class.validate()
        //  if(isClassValid){
        //      print("0");
        //  else
        //      print("1");
    }

    public static void main (String[] args){
        Sjavac sJavaCompiler = new Sjavac(args[0]);
        sJavaCompiler.validate();
    }
}
