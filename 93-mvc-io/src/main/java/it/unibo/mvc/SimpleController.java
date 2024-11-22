package it.unibo.mvc;

import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    List<String> history;
    String nexString; 

    @Override
    public void setNextString(String string) {
        Objects.requireNonNull(string);
        this.nexString = string;
    }

    @Override
    public String getNextString() {
        if (nexString == null) {
            throw new IllegalStateException("Current string unset!");
        }
        return this.nexString;
    }

    @Override
    public List<String> getHistory() {
        return this.history;
    }

    @Override
    public void printCurrentString() {
        if (nexString == null) {
            throw new IllegalStateException("Current string unset!");
        }
        System.out.println(nexString);
        history.add(nexString);
    }

}
