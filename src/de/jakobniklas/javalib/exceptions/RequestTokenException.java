package de.jakobniklas.javalib.exceptions;

public class RequestTokenException extends Exception
{
    public RequestTokenException(String message)
    {
        super(message);

        this.printStackTrace();
    }
}
