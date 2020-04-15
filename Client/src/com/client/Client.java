package com.client;

import java.io.*;
import java.net.*;

     public class Client
     {
    	 String xmString = "";
    	 String xmlUTF8="";
         private Socket socket;                  /*定义socket连接*/
         private BufferedReader reader;          /*从字符输入流读取文本*/
         private PrintWriter writer;             /*打印到文本输出流*/

         public Client(int serverPort) {                 /*定义客户端端口号*/
             try {                                                       /*抛异常*/
                socket = new Socket("120.53.13.79", serverPort);
				
				  reader = new BufferedReader( 
						  new InputStreamReader(socket.getInputStream())); /*标准输入输出流*/
                 writer = new PrintWriter(socket.getOutputStream());
 
                 while (true)
                {
 
                    BufferedReader in = new BufferedReader(
                             new InputStreamReader(System.in));
                     String message = in.readLine();
                    writer.println(message);
                     writer.flush();                             /*不关闭流，清空输入缓存区*/
 
                     if ("finish".equals(message))
                     {
                         System.out.println("服务器已停止监听");
                         break;
                     }
                     String received = reader.readLine();
                    System.out.println("返回的数据" + received);
                }

                 writer.close();
                reader.close();            /*关闭流*/
                socket.close();

            }
             catch (UnknownHostException ex)
             {
                ex.printStackTrace();
            }
            catch (IOException ex)
             {
                 ex.printStackTrace();       /*在命令行打印异常信息在程序中出错的位置及原因*/
             }
         }

 

         public static void main(String[] args)
         {
              new Client(2333);      /*绑定端口号*/
        }
     }
