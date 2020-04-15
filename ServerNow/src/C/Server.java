package C;

import java.io.*;
import java.net.*;

public class Server extends ServerSocket            
{
	public static void main(String[] args) throws IOException
    {
        new Server(2333);       /*�󶨶˿ں�*/
    }
	
	
    public Server(int serverPort) throws IOException
    {
        super(serverPort);
        try
        {
            while (true)
            {
                Socket socket = accept();
                new ServerThread(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            close();
        }
    }
    class ServerThread extends Thread       /*����������߳�*/
    {
        private Socket socket;
        private BufferedReader in;
        private PrintWriter out;
        public ServerThread(Socket s) throws IOException
        {
            this.socket = s;
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream(), "UTF-8"));        /*������*/
            out = new PrintWriter(socket.getOutputStream(),true);
            start();                /*��ʼ�߳�*/
        }

        public void run()
        {
                    try {
                while (true)
                {
                    String line = in.readLine();
                    if ("finish".equals(line))
                    {
                        System.out.println("��������ֹͣ����");
                        break;
                    }
                    System.out.println("���յ������ݣ�" + line);
                    String msg = "'" + line + "'is Accpet and You can over.";
                    out.println(msg);
                    out.flush();
                }
                out.close();
                in.close();
                socket.close();

            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

}