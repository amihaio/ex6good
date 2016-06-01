package oop.ex6.sJavaobjects;

import oop.ex6.main.RegEx;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//maybe have local and global variable objects
//or hve a flag denoting if local or global


public class VariableObject {

    private String varSourceLine; // TODO: Do we need this field?
    private String varName;
    private String varType;
    private boolean isInitialized = false;  // TODO: Do we need this flag?
    private String varValue;
    private boolean isGlobal; // TODO: Do we need this flag?

    public VariableObject(Matcher m){
        varType = m.group(0);
        varName = m.group(1);

        if(m.group().length() == 3) {
            varValue = m.group(2);
            isInitialized = true;
            varValidation();
        }
    }

    public void varValidation (){
        switch (varType){
            case "int":
                /* //TODO: maybe this will also work
                try{
                    Integer.parseInt(varValue);
                } catch......
                 */
                Pattern intVal = Pattern.compile(RegEx.intValue);
                Matcher isINT = intVal.matcher(varValue);
                if(!isINT.matches()){
                    //TODO: Exception invalid value is assigned to a int variable
                }
                break;
            case "boolean": //TODO : boolean expression 3<5 check in instructions
                Pattern booleanVal = Pattern.compile(RegEx.booleanValue);
                Matcher isBooleanVal = booleanVal.matcher(varValue);
                if(!isBooleanVal.matches()){
                    //TODO: Exception - invalid value is assigned to a boolean variable
                }
                break;
            case "double":
                //if (Double.parseDouble(varValue))
                //TODO: Exception - invalid value is assigned to a double variable

                break;
            case "char":
                Pattern charVal = Pattern.compile(RegEx.charValue);
                Matcher isCharVal = charVal.matcher(varValue);
                if(!isCharVal.matches()){
                    //TODO: Exception - invalid value is assigned to a char variable
                }
                break;
            case "String":
                Pattern stringVal = Pattern.compile(RegEx.stringValue);
                Matcher isStringVal = stringVal.matcher(varValue);
                if(!isStringVal.matches()){
                    //TODO: Exception - invalid value is assigned to a string variable
                }
                break;
        }

    }

    public String getVarName() {
        return varName;
    }

    public String getVarType() {
        return varType;
    }

    public boolean getInitializedFlag() {
        return isInitialized;
    }

    public String getVarValue() {
        return varValue;
    }

    public void setVarValue(String strVal) {
        varValue = strVal;
        varValidation();
        isInitialized = true;
    }

}
