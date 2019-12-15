package de.jakobniklas.javalib.exceptions;

public class RequestNotFoundException extends Exception
{
    public RequestNotFoundException(String message)
    {
        super(message);

        this.printStackTrace();
    }
}
