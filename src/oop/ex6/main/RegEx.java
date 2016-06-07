package oop.ex6.main;

/**
 * RegEx depot.
 */
public class  RegEx {

    //////////////////
    // method regex //
    //////////////////

    private final static String methodModifier = "\\s*void";
    private final static String methodName = "\\s+([A-Za-z][A-Za-z0-9_]*)";
    private final static String methodVariables = "\\s*(\\(\\s*(?:|final?\\s*\" +\n \"" +
            "(?:int|String|boolean|double|char)" +
            "\\\\s+[A-Za-z][A-Za-z0-9_]*(?:\\\\s*,\\\\s*( (?:final)?\\\\s*\" +\n \" " +
            "(?:int|String|boolean|double|char)\\\\s+[A-Za-z][A-Za-z0-9_]*))*) \\\\s*)\\\\)" +
            "\\\\s*\\\\{\\\\s*\";";
    public final static String methodDecleration = methodModifier+methodName+methodVariables;
            //todo erase after check the one above
//            "\\s*void\\s+([A-Za-z][A-Za-z0-9_]*)\\s*(\\(\\s*(?:|final?\\s*" +
//            "(?:int|String|boolean|double|char)\\s+[A-Za-z][A-Za-z0-9_]*(?:\\s*,\\s*((?:final)?\\s*" +
//            "(?:int|String|boolean|double|char)\\s+[A-Za-z][A-Za-z0-9_]*))*)\\s*)\\)\\s*\\{\\s*";
    public final static String methodCalling = "([a-zA-z][\\w]*)\\s*\\(\\s*(|((" +
            "(int|boolean|double|char|String)" +
            "\\s+[a-zA-z][\\w]*)(\\s*,\\s*(int|boolean|double|char|String)\\s+[a-zA-z][\\w]*)*))\\s*\\)\\s*;";


    //////////////////////
    // "if/while" regex //
    //////////////////////

    public final static String ifDecleration = "(while|if)\\s*\\(\\s*([a-zA-z][\\w]*|true|false|(-?[1-9]+" +
            ".?[1-9]*))(| (\\s (&&|\\|\\|) \\s+([a-zA-z][\\w]*|true|false|(-?[1-9]+.?[1-9]*)))*)\\s*\\)" +
            "\\s*\\{";

    ////////////////////
    // variable regex //
    ////////////////////

    /* var declaration and assignment */

    public final static String varAssignment = "^\\s*\\w+\\s*={1}\\s*(.*);";

    public final static String varDecAndAsgn = "^\\s*(final\\s+)?(int|boolean|double|char|String)\\s+" +
            "([A-Za-z][A-Za-z0-9]*)\\s*=\\s*(.+)+\\s*;";

    public final static String varDeclaration = "^\\s*(final\\s+)?(int|boolean|double|char|String)\\s+" +
            "([A-Za-z][A-Za-z0-9]*)\\s*;";


    /* variable types */
    public final static String intValue = "^\\d+$";

    public final static String booleanValue = "^(true|false|-?[1-9]+.?[1-9]*)$";

    public final static String doubleValue = "^-?[1-9]+.?[1-9]*$";

    public final static String charValue =  "^'[^';]*'$";

    public final static String stringValue = "^\"[^\";]*\"$";


    ///////////
    // other //
    ///////////
    public final static String closeScope = "^\\s*}\\s*$";
    public final static String comment = "^\\/\\/";
}
