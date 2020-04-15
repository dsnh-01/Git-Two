package com.client;

import java.io.*;
import java.net.*;

     public class Client
     {
    	 String xmString = "";
    	 String xmlUTF8="";
         private Socket socket;                  /*����socket����*/
         private BufferedReader reader;          /*���ַ���������ȡ�ı�*/
         private PrintWriter writer;             /*��ӡ���ı������*/

         public Client(int serverPort) {                 /*����ͻ��˶˿ں�*/
             try {                                                       /*���쳣*/
                socket = new Socket("120.53.13.79", serverPort);
				
				  reader = new BufferedReader( 
						  new InputStreamReader(socket.getInputStream())); /*��׼���������*/
                 writer = new PrintWriter(socket.getOutputStream());
 
                 while (true)
                {
 
                    BufferedReader in = new BufferedReader(
                             new InputStreamReader(System.in));
                     String message = in.readLine();
                    writer.println(message);
                     writer.flush();                             /*���ر�����������뻺����*/
 
                     if ("finish".equals(message))
                     {
                         System.out.println("��������ֹͣ����");
                         break;
                     }
                     String received = reader.readLine();
                    System.out.println("���ص�����" + received);
                }

                 writer.close();
                reader.close();            /*�ر���*/
                socket.close();

            }
             catch (UnknownHostException ex)
             {
                ex.printStackTrace();
            }
            catch (IOException ex)
             {
                 ex.printStackTrace();       /*�������д�ӡ�쳣��Ϣ�ڳ����г����λ�ü�ԭ��*/
             }
         }

 

         public static void main(String[] args)
         {
              new Client(2333);      /*�󶨶˿ں�*/
        }
     }
