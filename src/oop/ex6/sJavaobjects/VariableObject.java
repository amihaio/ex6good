package oop.ex6.sJavaobjects;

import oop.ex6.main.RegEx;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class VariableObject {

    //TODO : MAGIC
    private String varType;
    private String varName;
    private String varValue;
    private boolean finalFlag;
    private ScopeAbstract parent;

    /**
     * Constructor - initialize the variable fields
     * @param matcher matcher object that contains the variable data
     */
    public VariableObject(Matcher matcher) {
        finalFlag = matcher.group(0).equals("final");
        varType = matcher.group(1);
        varName = matcher.group(2);
        if (matcher.group().length() == 4)
            varValue = matcher.group(3);
    }

    public VariableObject(String varName, String varType, String varValue, boolean isFinal){
        this(varName,varType,isFinal);
        this.varValue = varValue;
    }

    public VariableObject(String varName, String varType, boolean isFinal){
        finalFlag = isFinal;
        this.varName = varName;
        this.varType = varType;
    }

    /**
     * This method verify the variable is defined  correctly,
     * with value that is matching to the variable type
     * @return true is the variable is valid, else - return false
     */
    public boolean validate() {
        // the next condition verify if the final variable has a value (if not - return false)
        // not final variable without value is valid
        if (varValue == null){
            return  !finalFlag;
        }

        switch (varType) {
            case "int":
                /* //TODO: maybe this will also work
                try{
                    Integer.parseInt(varValue);
                } catch......
                 */
                Pattern intVal = Pattern.compile(RegEx.intValue);
                Matcher isINT = intVal.matcher(varValue);
                return isINT.matches();

            case "boolean": //TODO : boolean expression 3<5 check in instructions
                Pattern booleanVal = Pattern.compile(RegEx.booleanValue);
                Matcher isBooleanVal = booleanVal.matcher(varValue);
                return isBooleanVal.matches();

            case "double"://TODO
/*
                Double parserTest = Double.parseDouble(varValue);
                if( parserTest instanceof Double) {
*/
                return true;
                //TODO: Exception - invalid value is assigned to a double variable

            case "char":
                Pattern charVal = Pattern.compile(RegEx.charValue);
                Matcher isCharVal = charVal.matcher(varValue);
                return isCharVal.matches();

            case "String":
                Pattern stringVal = Pattern.compile(RegEx.stringValue);
                Matcher isStringVal = stringVal.matcher(varValue);
                return isStringVal.matches();

            default:
                Pattern variableAsValue = Pattern.compile(RegEx.variableAsValue);
                Matcher isVarAssignment = variableAsValue.matcher(varType);
                if (isVarAssignment.matches()){
                    VariableObject upperScopeVar = findVarInUpperScope();
                    if (upperScopeVar != null) {
                        varValue = upperScopeVar.getVarValue();
                        return validate();
                    }else
                        return false;
                }else
                    return false;
        }

    }

    /**
     * @param varName string , a name of variable
     * @return the variable object in case the variable exist in the parents scope
     */
    private VariableObject findVarInUpperScope (String varName){
        ScopeAbstract currentParent = parent;
        while (currentParent!= null){ // TODO: what with classObj - not a InnerBooleanBlock
            ArrayList<VariableObject> varList = currentParent.getVariablesList();
            for (VariableObject curVar : varList){
                if (curVar.getVarName().equals(varName))
                    return curVar;
            }
            currentParent = currentParent.getFather();
        }
        return null;
    }


    public String getVarName() {
        return varName;
    }

    public String getVarType() {
        return varType;
    }


    public String getVarValue() {
        return varValue;
    }

    /**
     * * This methods try to insert a new value to the variable
     * @param strVal a new assignment value
     * @return true in case the given value proper to the variable type, else false.
     */
    public boolean setVarValueValidate (String strVal) {
        if (finalFlag) { // you cannot edit a final variable
            return false;
        } else {
            varValue = strVal;
            return validate();
        }
    }

}
