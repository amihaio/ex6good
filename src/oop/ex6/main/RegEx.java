package oop.ex6.main;

public class RegEx {
    //make static

    public String methodDec = "(?:private|public|protected)?\\s+void\\s+(\\w+)?\\s\\((\\s*" +
            "(int|String|boolean|double|char)\\s+(\\w+){1},?)*\\)\\{\\s*";  //todo comma issue

    public String ifDec = "if\\s*\\(\\)\\s*{\\s*";

    public String comment = "^\\/\\/";

    public String assignment = "\\^\\s*\\w+\\s+={1}\\s+";

    //todo illegal operators and comments


}
