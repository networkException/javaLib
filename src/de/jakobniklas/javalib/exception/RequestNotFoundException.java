package de.jakobniklas.javalib.exception;

public class RequestNotFoundException extends Exception
{
    public RequestNotFoundException(String message)
    {
        super(message);

        this.printStackTrace();
    }
}
