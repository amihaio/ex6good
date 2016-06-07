package oop.ex6.sJavaobjects;


import java.util.ArrayList;
import java.util.regex.Matcher;

public class MethodObject extends ScopeAbstract {

    private String parameterStringList;//todo erase
    private ArrayList<VariableObject> parameterList = new ArrayList<>();
    private String methodName;

    public MethodObject(Matcher regexGroups, ArrayList<String> scopeLines, ClassObject fatherClass) {
        this.fatherClass = fatherClass;
        this.scopeLines = scopeLines;
        this.methodName = regexGroups.group(0);
        String parameterListString = regexGroups.group(1);
        String[] parameterListSplit = parameterListString.split("\\s*,\\s*");
        for (String singleParameter : parameterListSplit) {
            String[] singleParameterList = singleParameter.split("\\s+");
            if (singleParameterList[0].equals("final")) {
                parameterList.add(new VariableObject(singleParameterList[1], singleParameterList[2], true));
            } else {
                parameterList.add(new VariableObject(singleParameterList[0], singleParameterList[1], false));
            }
        }
    }
    //todo make general constructor

    public String getMethodName() {
        return methodName;
    }

    public ArrayList<VariableObject> getParameterList() {
        return parameterList;
    }

}
