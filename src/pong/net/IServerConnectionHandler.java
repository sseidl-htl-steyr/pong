package pong.net;

import java.net.Socket;

public interface IServerConnectionHandler
{
    void clientConnected(Socket socket);
}
