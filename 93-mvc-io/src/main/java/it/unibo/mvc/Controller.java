package it.unibo.mvc;

import java.util.List;

/**
 *
 */
public interface Controller {
    /**
     * A method for setting the next string to print.
     * @param string to be set
     * @throws IllegalArgumentException if the parameter is null
     */
    void setNextString(String string);

    /**
     * A method for getting the next string to print.
     * @return the next string to print
     */
    String getNextString();

    /**
     * A method for getting the history of the printed strings.
     * @return a list of strings printed
     */
    List<String> getHistory();

    /**
     * A method that prints the current string.
     */
    void printCurrentString();
}
