package oop.ex6.main;

import oop.ex6.sJavaobjects.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class SourceFileParser {
    private static SourceFileParser ourInstance = new SourceFileParser();

    public static SourceFileParser getInstance() {
        return ourInstance;
    }

    private SourceFileParser() {}

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


    /**
     * This method go over the lines of the file, and create ClassObject according to the global
     * variables and the methods that exist int the file lines.
     * @param fileLines - a list that contains the lines of the source file.
     * @return an object of ClassObject class. In case of invalid line/s - return null.
     */
    public ClassObject createClassObject (List<String> fileLines){
        ClassObject currentClass = new ClassObject();

        Pattern varAssignment = Pattern.compile(RegEx.varAssignment);
        Pattern varDefinition = Pattern.compile(RegEx.varDeclaration);
        Pattern methodSignature = Pattern.compile(RegEx.methodDecleration);//todo fix
        Pattern commentLine = Pattern.compile(RegEx.comment);
        Pattern emptyLine = Pattern.compile(RegEx.emptyLine);

        for (int i =0; i < fileLines.size(); i++){
            Matcher isVarAssignment = varAssignment.matcher(fileLines.get(i));
            Matcher isVarDefinition = varDefinition.matcher(fileLines.get(i));
            Matcher isMethodSignature = methodSignature.matcher(fileLines.get(i));
            Matcher isCommentLine = commentLine.matcher(fileLines.get(i));
            Matcher isEmptyLine = emptyLine.matcher(fileLines.get(i));

            if (isVarAssignment.matches()){
                VariableObject curVar = new VariableObject(isVarAssignment);
                currentClass.getGlobalVariableList().add(curVar);
            }
            else if (isVarDefinition.matches()){
                VariableObject curVar = new VariableObject(isVarDefinition);
                currentClass.getGlobalVariableList().add(curVar);
            }
            else if (isMethodSignature.matches()){
                List<String> innerScopeLines = getInnerScope(fileLines.subList(i, fileLines.size()));
                if (innerScopeLines == null)
                    return null;
                else {
                    int scopeSize = innerScopeLines.size();
                    Pattern returnLine = Pattern.compile(RegEx.returnLine);
                    Matcher isReturnLine = varAssignment.matcher(innerScopeLines.get(scopeSize-1));
                    if (!isReturnLine.matches())
                        return null;

                    i = i + scopeSize;
                    MethodObject curMeth= new MethodObject(isMethodSignature, innerScopeLines, currentClass);
                    currentClass.getMethodsList().add(curMeth);
                }

            }
            else{
                if (!isCommentLine.matches() && !isEmptyLine.matches()){
                    return null;
                    //TODO: invalid line return null --> the function from the main class should relate
                    //TODO: to null as invalid file
                }
            }
        }
        return currentClass;
    }

    /**
     * This method get a list of string and find the first inner scope in the lines list, the method
     * return a list of the inner scope lines.
     * @param lineList - list of lines.
     * @return a list of line that related to the inner scope, if the inner scope is invalid - return null.
     */
    public List<String> getInnerScope(List<String> lineList) throws ParsingException{
        Pattern methodSignature = Pattern.compile(RegEx.methodDecleration);
        Pattern ifWhileDeceleration = Pattern.compile(RegEx.ifWhileDecleration);
        Pattern closeScope = Pattern.compile(RegEx.closeScope);

        int scopeCounter = 1;
        int lineCounter = 1;
        String currentLine = lineList.get(lineCounter);
        Matcher isMethodSignature = methodSignature.matcher(currentLine);

        while (currentLine != null && !isMethodSignature.matches()) {
            if (getLineType(currentLine) == null)
                throw new ParsingException("")

            if (ifWhileDeceleration.matcher(currentLine).matches())
                scopeCounter++;
            else if (closeScope.matcher(currentLine).matches())
                scopeCounter--;

            if (scopeCounter == 0)
                return lineList.subList(0, lineCounter+1); // TODO: List instead of Array list? bugs?

            lineCounter++;
            currentLine = lineList.get(lineCounter);
        }
        return null;
    }


    /**
     * this method hold the valid rows types, and check if the given line is a valid line
     * @param line line line of the file
     * @return the regEx variable witch the line is matched to him
     */
    public String getLineType (String line){
        String[] validRowTypes = {RegEx.varDecAndAsgn, RegEx.varAssignment, RegEx.varDeclaration,
                RegEx.closeScope, RegEx.methodCalling, RegEx.comment, RegEx.emptyLine,
                RegEx.ifWhileDecleration};

        Pattern[] patternsList = new Pattern[validRowTypes.length];
        Matcher[] marchersList = new Matcher[patternsList.length];

        for (int i=0;  i< validRowTypes.length; i ++){
            String curRegExStr  = validRowTypes[i];
            patternsList[i] = Pattern.compile(curRegExStr);
            marchersList[i] = patternsList[i].matcher(line);
        }

        for (int j=0;  j< marchersList.length; j++){
            if (marchersList[j].matches())
                return validRowTypes[j];
        }
        return null;
    }


}⁠⁠⁠⁠