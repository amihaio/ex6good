package oop.ex6.main;

import oop.ex6.sJavaobjects.ClassObject;
import oop.ex6.sJavaobjects.VariableObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SourceFileParser {
    private static SourceFileParser ourInstance = new SourceFileParser();

    public static SourceFileParser getInstance() {
        return ourInstance;
    }

    private SourceFileParser() {

    }


    /**
     * @param fileName the name of the Command file
     * @return a list of the file’s lines
     * @throws IOException
     */
    public static List<String> getFileLines(String fileName) throws IOException {
        List<String> fileLinesList;
        Path filePath = Paths.get(fileName);
        fileLinesList = Files.readAllLines(filePath);
        return fileLinesList;
    }

    public static void createClassObject ( List<String> fileLines){
        ArrayList<VariableObject> globalVariables = new ArrayList<>();

        Pattern varAssignment = Pattern.compile(RegEx.varAssignment);
        Pattern varDefinition = Pattern.compile(RegEx.varDefined);
        Pattern methodSignature = Pattern.compile(RegEx.methodSignature);

        for (String line : fileLines){
            Matcher isVarAssignment = varAssignment.matcher(line);
            Matcher isVarDefinition = varDefinition.matcher(line);
            Matcher isMethodSignature = methodSignature.matcher(line);
            if (isVarAssignment.matches()){
                VariableObject curVar = new VariableObject(isVarAssignment);
                globalVariables.add(curVar);
            }
            else if (isVarDefinition.matches()){
                VariableObject curVar = new VariableObject(isVarDefinition);
                globalVariables.add(curVar);
            }
            else if (isMethodSignature.matches()){
                continue; // TODO!!!
            }
            else{
                //TODO: Exception - invalid line
            }

        }


    }

/*
    public static String recognizeVariablesandMethods{


    }*/



   /*     // String to be scanned to find the pattern.
        String line = "This order was placed for QT3000! OK?";
        String pattern = "(.*)(\\d+)(.*)";

        // Create a Pattern object
        Pattern r = Pattern.compile(pattern);

        // Now create matcher object.
        Matcher m = r.matcher(line);
        if (m.find( )) {
            System.out.println("Found value: " + m.group(0) );
            System.out.println("Found value: " + m.group(1) );
            System.out.println("Found value: " + m.group(2) );
        } else {
            System.out.println("NO MATCH");
        }*/



    ArrayList<ArrayList<String>> methods = new ArrayList<>();

    //loops over source file and adds variables and methods according to source file.
    // returns class object with the arrays in it.
    // do not insert lines of comments - begin with ///

    // (int|boolean|String)\s+((?:[A-Z][a-z]*))(\s*[=]\s*)([A-Z]|[a-z]|[0-9])*
    // https://txt2re.com/index-java.php3?s=int%20Name%20=%206&3&8&1&9&-23
    // http://regexr.com/


}
