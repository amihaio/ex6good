package oop.ex6.main;

import java.util.ArrayList;

/**
 * Created by Sapir on 6/1/2016.
 */
public class SourceFileParser {
    private static SourceFileParser ourInstance = new SourceFileParser();

    public static SourceFileParser getInstance() {
        return ourInstance;
    }

    private SourceFileParser() {
    }



    ArrayList<String> globalVaribles = new ArrayList<>();
    ArrayList<ArrayList<String>> methods = new ArrayList<>();

    //loops over source file and adds variables and methods according to source file.
    // returns class object with the arrays in it.
    // do not insert lines of comments - begin with ///

    // (int|boolean|String)\s+((?:[A-Z][a-z]*))(\s*[=]\s*)([A-Z]|[a-z]|[0-9])*
    // https://txt2re.com/index-java.php3?s=int%20Name%20=%206&3&8&1&9&-23
    // http://regexr.com/


}
