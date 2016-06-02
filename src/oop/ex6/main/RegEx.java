package oop.ex6.main;

public class RegEx {
    //make static

    public String methodDec = "(?:private|public|protected)?\\s+void\\s+([A-Za-z][A-Za-z0-9]*)?\\s*\\(\\s*" +
            "(|(int|String|boolean|double|char)\\s+([A-Za-z][A-Za-z0-9_]*)(\\s*,\\s*" +
            "(int|String|boolean|double|char)\\s+([A-Za-z][A-Za-z0-9]*))*)\\)\\{\\s*";

    public String ifDec = "if\\s*\\(\\s*([a-zA-z][\\w]*|true|false|(-?[1-9]+.?[1-9]*))(|(\\s+(&&|\\|\\|)" +
            "\\s+([a-zA-z][\\w]*|true|false|(-?[1-9]+.?[1-9]*)))*)\\s*\\)\\s*\\{";

    public String comment = "^\\/\\/";

    public String varAssignment = "\\^\\s*\\w+\\s+={1}\\s+";

    //todo illegal operators and comments
    //todo var names cannot start with digit

    public static String closeScope = "^\\s*}\\s*$";

    public static String varDec = "^\\s*(?:final\\s+)*(int|boolean|double|char|String)\\s+" +
            "([A-Za-z][A-Za-z0-9]*)\\s*=\\s*([\\w]|[\\.[\\]\"{}()*+-?^$|])+\\s*;";

    public static String varDefined= "^\\s*(?:final\\s+)*(int|boolean|double|char|String)\\s+" +
            "([A-Za-z][A-Za-z0-9]*)\\s*;";

    public static String methodCalling = "([a-zA-z][\\w]*)\\s*\\(\\s*(|(((int|boolean|double|char|String)" +
            "\\s+[a-zA-z][\\w]*)(\\s*,\\s*(int|boolean|double|char|String)\\s+[a-zA-z][\\w]*)*))\\s*\\)\\s*;";

    public static String intValue = "^\\d+$";

    public static String booleanValue = "^(true|false|-?[1-9]+.?[1-9]*)$";

    public static String doubleValue = "^-?[1-9]+.?[1-9]*$";

    public static String charValue =  "^'[^';]*'$";

    public static String stringValue = "^\"[^\";]*\"$";

}
