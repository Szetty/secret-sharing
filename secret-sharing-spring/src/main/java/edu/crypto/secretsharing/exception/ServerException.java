package edu.crypto.secretsharing.exception;

/**
 * Created by szede_000 on 1/17/2016.
 */
public class ServerException extends Exception {

    public ServerException(String message) {
        super(message);
    }

    public ServerException(String message, Throwable cause) {
        super(message, cause);
    }
}
