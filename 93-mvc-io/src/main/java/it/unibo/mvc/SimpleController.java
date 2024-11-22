package it.unibo.mvc;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * 
 *
 */
public final class SimpleController implements Controller {

    private final List<String> history = new LinkedList<>();
    private String nexString; 

    /**
     * {@inheritDoc}
     */
    @Override
    public void setNextString(final String string) {
        Objects.requireNonNull(string);
        this.nexString = string;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getNextString() {
        if (nexString == null) {
            throw new IllegalStateException("Current string unset!");
        }
        return this.nexString;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<String> getHistory() {
        return List.copyOf(this.history);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void printCurrentString() {
        if (nexString == null) {
            throw new IllegalStateException("Current string unset!");
        }
        System.out.println(nexString);  // NOPMD needed for the exercise
        history.add(nexString);
    }

}
