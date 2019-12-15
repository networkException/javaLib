package de.jakobniklas.javalib.testing;

import de.jakobniklas.applicationlib.commonutil.Log;

public class Main
{
    public static void main(String[] args)
    {
        //new MatchableArgument("#node(hi)<java.lang.String>");

        test(() -> Log.print("from Lambda"));
    }

    public static void test(LambdaTest impl)
    {
        impl.run();
    }
}