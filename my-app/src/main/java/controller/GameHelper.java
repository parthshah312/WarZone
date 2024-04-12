package controller;

public class GameHelper {
    /**
     * it takes care whether the map is valid or not
     *
     * @param p_sample input string
     * @return true if run fine otherwise false
     */
    public static boolean isValidMap(String p_sample) {
        return p_sample != null && p_sample.matches("^[a-zA-Z.]*$");
    }

    /**
     * it takes care of the string matches
     *
     * @param p_sample input string
     * @return true if run fine otherwise false
     */
    public static boolean isValidPlayer(String p_sample) {
        return p_sample != null && p_sample.matches("[a-zA-Z0-9]+");
    }

    /**
     * it takes care of the string matches
     *
     * @param p_sample input string
     * @return true if run fine otherwise false
     */
    public static boolean isAlphabetic(String p_sample) {
        return p_sample != null && p_sample.matches("^[a-zA-Z-_]*$");
    }

    /**
     * it takes care of the string matches that for numeric id
     *
     * @param p_sample input string
     * @return true if run fine otherwise false
     */
    public static boolean isNumeric(String p_sample) {
        return p_sample != null && p_sample.matches("[0-9]+");
    }


}