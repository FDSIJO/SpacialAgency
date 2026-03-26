package org.sijoexercise.exceptions;

public class BadInputsException extends Exception {

    public BadInputsException(Exception e) {
        super(e);
    }

    @Override
    public String getMessage() {
        return "Failed to map the 'inputs' resource file. Please ensure the file follows the expected format.";
    }
}
