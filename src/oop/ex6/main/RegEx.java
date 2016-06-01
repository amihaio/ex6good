package oop.ex6.main;

public class RegEx {
    //make static

    public String methodDec = "(?:private|public|protected)?\\s+void\\s+(\\w+)?\\s\\((\\s*" +
            "(int|String|boolean|double|char)\\s+(\\w+){1},?)*\\)\\{\\s*";  //todo comma issue

    public String ifDec = "if\\s*\\(\\)\\s*{\\s*";

    public String comment = "^\\/\\/";

    public String assignment = "\\^\\s*\\w+\\s+={1}\\s+";

    //todo illegal operators and comments

    public static String closeScope = "^\\s*}\\s*$";
    public static String getCloseScope = "^\\s*{\\s*$";
    public static String varAssignment = "^\\s*(?:final\\s+)*(int|boolean|double|char|String)\\s+([A-Za-z][A-Za-z0-9]*)\\s*=\\s*([\\w]|[\\.[\\]\"{}()*+-?^$|])+\\s*;";
    public static String varDefined= "^\\s*(?:final\\s+)*(int|boolean|double|char|String)\\s+([A-Za-z][A-Za-z0-9]*)\\s*;";
    public static String mrthodCalling = "";
    public static String methodSignature = "";
    public static String intValue = "^\\d+$";
    public static String booleanValue = "^(true|false)$";
    public static String doubleValue = ""; // TODO
    public static String charValue =  "^'[^';]*'$";
    public static String stringValue = "^\"[^\";]*\"$";
}
  