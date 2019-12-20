package de.jakobniklas.javalib.exception;

public class RequestTokenException extends Exception
{
    public RequestTokenException(String message)
    {
        super(message);

        this.printStackTrace();
    }
}
