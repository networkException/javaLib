package de.jakobniklas.javalib.testing;

public class Main
{
    public static void main(String[] args)
    {
        /*try
        {
            HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
            server.createContext("/test", new MyHandler());
            server.setExecutor(null); // creates a default executor
            server.start();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }*/
    }

    /*static class MyHandler implements HttpHandler
    {
        @Override
        public void handle(HttpExchange t) throws IOException
        {
            if(t.getLocalAddress().toString().equals("/0:0:0:0:0:0:0:1:8000"))
            {
                String response = "This is the response";
                t.sendResponseHeaders(200, response.length());
                OutputStream os = t.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
            else
            {
                Log.print(new LogLevel("trace"), "Frontend", "Invalid access from '%s'", t.getRemoteAddress());
            }

            t.close();
        }
    }*/
}