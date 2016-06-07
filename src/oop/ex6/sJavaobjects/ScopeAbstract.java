package oop.ex6.sJavaobjects;

import oop.ex6.main.RegEx;
import oop.ex6.main.SourceFileParser;
import java.util.ArrayList;
import java.util.List;

/**
 * abstract class representing a scope in the sJava code.
 */
public abstract class ScopeAbstract {

    SourceFileParser parser = SourceFileParser.getInstance(); //to be used by extending classes
    ArrayList<VariableObject> localVariables = new ArrayList<>();
    ArrayList<ScopeAbstract> innerScopeList = new ArrayList<>();
    ScopeAbstract father;
    ClassObject fatherClass;
    ArrayList<String> scopeLines = new ArrayList<>();

    /**
     * default constructor
     */
    public ScopeAbstract() {
    }

    /**
     * Constructor that takes the lines of the scope (from the input file), the scopes father scope and the
     * class object wherein the scope resides.
     *
     * @param scopeLines  array of the lines of the scope
     * @param father      the father scope
     * @param fatherClass the class object
     */
    public ScopeAbstract(ArrayList<String> scopeLines, ScopeAbstract father, ClassObject fatherClass) {
        this.father = father;
        this.scopeLines = scopeLines;
        this.fatherClass = fatherClass;
    }

    /**
     * Checks validity of a line calling a method. checks if the method exists and if the parameters are
     * legal.
     *
     * @param name
     * @param parameterList
     * @return
     */
    private boolean checkMethodCall(String name, ArrayList<String> parameterList) {
        for (MethodObject method : fatherClass.getMethodsList()) {
            if (name.equals(method.getMethodName())) {
                ArrayList<VariableObject> methodParameterList = method.getParameterList();
                for (int i = 0; i <= parameterList.size(); i++) {
                    String currentParameter = parameterList.get(i);
                    boolean isParamValid = methodParameterList.get(i).setVarValueValidate(currentParameter);
                    if (!isParamValid) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }
    //todo move to parser
    /**
     * @param linesList
     * @return
     */
    public ScopeAbstract createInnerScope(List<String> linesList) {
        List<String> scopeLines = getInnerScope(linesList);
        if (scopeLines == null)
            return null;
        else {
            InnerBooleanBlock curInnerScope = new InnerBooleanBlock(scopeLines, ClassObject fatherClass);
        }
    }

    ⁠⁠⁠⁠

    /**
     * checks if assignment to an existing variable is valid. checks whether the variable was declared
     * already.
     *
     * @param varNameToCheck the name of the variable
     * @param varValue       the new value to assign to variable
     * @param currentScope   the current scope of recursion
     * @return true if assignment is valid, false otherwise
     */
    private boolean checkVarAssignment(String varNameToCheck, String varValue, ScopeAbstract currentScope) {
        for (VariableObject var : currentScope.getVariablesList()) { //check current scope variables
            if (var.getVarName().equals(varNameToCheck)) {
                return var.setVarValueValidate(varValue);
            }
        }
        if (currentScope.getFather() == null) {  //reached the methods in recursion
            for (VariableObject var : fatherClass.getGlobalVariableList()) {
                if (var.getVarName().equals(varNameToCheck)) {
                    return var.setVarValueValidate(varValue);
                }
            }
            return false;
        }
        return checkVarAssignment(varNameToCheck, varValue, currentScope.getFather());
    }

    /**
     * Check if newly declared variable name already exists in scope hierarchy.
     *
     * @param varNameToCheck name of variable to check for
     * @param currentScope   the current scope of recursion
     * @return true if method declaration is valid, false otherwise
     */
    private boolean checkVarDecleration(String varNameToCheck, ScopeAbstract currentScope) {
        for (VariableObject variable : currentScope.getVariablesList()) {
            if (variable.getVarName().equals(varNameToCheck)) {
                return false;
            }
        }
        return currentScope.getFather() == null || checkVarDecleration(varNameToCheck, currentScope
                .getFather());  //return true if reached method scope (father==null) or returns the
        // recursive call
    }

    public boolean validate() throws BadOperationException{
        //check line validity and create new scopes as needed
        for (String line : scopeLines) {
            String lineType = parser.getLineType(line);

            switch (lineType) {
                case (RegEx.varDeclaration):
                    if (!checkVarDecleration())
                        throw new BadOperationException("Invalid variable declaration");
                    else
                        createInnerScope();

                case (RegEx.methodCalling):
                    if (!checkMethodCall())
                        throw new BadOperationException("Invalid method call");

                case (RegEx.varAssignment):
                    if (!checkVarAssignment())
                        throw new BadOperationException("Invalid variable assignment");

                case (RegEx.varDecAndAsgn):
                    if (!checkVarDecleration())
                        throw new BadOperationException("Invalid variable declaration");
                    else {
                        VariableObject newVar = new VariableObject();
                        if (!newVar.setVarValueValidate())
                            throw new BadOperationException("Invalid variable assignment");
                        else
                            localVariables.add(newVar);
                    }
                default:
                    // todo use factory to create inner block. that way validate doesn't need to know all
                    // todo kinds of blocks. make sure these are the only type of lines left.
                }
            }
        // iterate over inner scopes to validate
        for (ScopeAbstract innerScope: innerScopeList){
            boolean isInnerScopeValid = innerScope.validate();
            if (!isInnerScopeValid)
                return false;
        }
        return true;
    }

    /**
     * @return list of local variables
     */
    public ArrayList<VariableObject> getVariablesList() {
        return localVariables;
    }

    /**
     * @return the father scope
     */
    public ScopeAbstract getFather() {
        return father;
    }
}
