package ua.station.model.exception;

/**
 * Created by sa on 06.11.17.
 */
public class EmailExistsException extends Exception {
    public EmailExistsException(String s) {
        super(s);
    }
}
