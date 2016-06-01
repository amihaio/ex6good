package oop.ex6.sJavaobjects;

import java.util.ArrayList;

public class ClassObject implements SJavaObjectInterface {

    ArrayList<VariableObject> globalVariableList;
    ArrayList<MethodObject> methodList;

    public ClassObject(ArrayList<String> var , ArrayList<ArrayList<String>> methods){
        globalVariableList = makeGlobalVariableList(var);
        methodList = makeMethodList(methods);

    }

    private ArrayList<MethodObject> makeMethodList(ArrayList<ArrayList<String>> methods) {

        return null;
    }

    private ArrayList<VariableObject> makeGlobalVariableList(ArrayList<String> varaiblesList) {
        return null;
    }

    @Override
    public void run() {

    }


}
