package pong.net;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerThread extends Thread
{
    private ServerSocket socket;
    private IServerConnectionHandler handler;

    public ServerThread(int port, IServerConnectionHandler handler) throws IOException
    {
        socket = new ServerSocket(port);
        this.handler = handler;
    }

    @Override
    public void run()
    {
        try
        {
            handler.clientConnected(socket.accept());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
