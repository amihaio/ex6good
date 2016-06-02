package oop.ex6.sJavaobjects;


import java.util.ArrayList;

public class InnerBooleanBlock {
    private ArrayList<VariableObject> globalVariables;
    private ArrayList<MethodObject> methods;
    private ArrayList<VariableObject> localVariables;
    private ArrayList<String> blockLines;

    public InnerBooleanBlock(ArrayList<VariableObject> globalVariables, ArrayList<MethodObject> methods,
                             ArrayList<String> blockLines) {
        this.globalVariables = globalVariables;
        this.methods = methods;
        this.blockLines = blockLines;
    }

    private void validate() {
        for (String line : blockLines) {

        }
    }

}
