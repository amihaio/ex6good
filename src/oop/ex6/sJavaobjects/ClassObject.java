package oop.ex6.sJavaobjects;

import java.util.ArrayList;

public class ClassObject {

    // a list of the global variables objects
    ArrayList<VariableObject> globalVariableList;
    // a list of the methods objects
    ArrayList<MethodObject> methodsList;

    /**
     * constructor - initialize the object data members
     */
    public ClassObject(){
        globalVariableList = new ArrayList<>();
        methodsList = new ArrayList<>();
    }

    /**
     * This method go over the methods list and the variables list and for each object in the lists
     * verify the object is valid (by calling for validate() methods).
     * @return true if all of the object from the both lists are valid, else - return false
     */
    private boolean validate(){
        boolean validationStatus;
        for (MethodObject curMethod : methodsList){
            validationStatus = curMethod.validate();
            if (!validationStatus)
                return false;
        }
        for (VariableObject curVar : globalVariableList){
            validationStatus = curVar.validate();
            if (!validationStatus)
                return false;
        }
        return true;
    }

    /**
     * @return list of the global variables objects
     */
    public ArrayList<VariableObject> getGlobalVariableList() {
        return globalVariableList;
    }

    /**
     * @return list of the methods objects
     */
    public ArrayList<MethodObject> getMethodsList() {
        return methodsList;
    }

}
