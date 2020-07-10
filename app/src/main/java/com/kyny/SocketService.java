package com.kyny;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;

import com.kyny.studyretrofit.utils.FileUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadPoolExecutor;

public class SocketService extends Service {
    private static OutputStreamWriter osw;

    ServerSocket serverSocket;
    private static Socket socket;
    String msg="";
    String data="";
    Handler handler = new Handler()
    {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
           String content= (String) msg.obj;
//           setMsg(content);

        }
    };
    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("start","start************");
        startService();

    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

//    @Override
////    public IBinder onBind(Intent intent) {
////            return Throwable  new Exception("eeee");
//////            return new MsgBinder();
////
////    }
    /**
     * 启动服务监听，等待客户端连接
     */
    public String startService() {
        String content=null;
        try {
            // 创建ServerSocket
            serverSocket = new ServerSocket(9999);
            Log.i("郭绪雄","--开启服务器，监听端口 9999--");

            content= startReader(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
            msg="连接失败";
        }
        return msg;
    }

    public String getMsg() {
        return msg;
    }

    private String startReader(final ServerSocket socket1) {
        String mContent;
        final FileUtils fileUtils=new FileUtils();
        final StringBuffer stringBuffer=new StringBuffer();
        new Thread(){
            @Override
            public void run() {

                BufferedInputStream reader;


                try {

                    while (true) {
                        // 监听端口，等待客户端连接
                        socket = socket1.accept();
                        //获取输入流
                        reader = new BufferedInputStream( socket.getInputStream());
                        System.out.println("*等待客户端输入*");
                        byte [] data = new byte[1024];
                        int size = 0;
                        Log.i("debug","*waiting for input*");
                        while((size = reader.read(data)) !=-1){
                            String s = new String(data, 0, size);
                              msg=msg+s;
//                            socket.shutdownInput();
                            System.out.println("获取到客户端的信息********************");
                            fileUtils.writeData(s);
                              if(s.endsWith("end"))
                            {
//                                Message message = new Message();
//                                message.what = 1;
//                                message.obj = stringBuffer.toString();
//                                handler.sendMessage(message);
//                                size=-1;
                                System.out.println("获取到客户端的信息：" + msg);
//                                final StringBuffer stringBuffer=new StringBuffer();
                                Sendmsg("success");
                                break;
                            }
//                            String msgSend  = "Success";
//
//                            try {
//                                osw = new OutputStreamWriter(socket.getOutputStream(),
//                                        "UTF-8");
//                                PrintWriter pw = new PrintWriter(osw,true);
//                                pw.println(msgSend);
//                                pw.flush();
//                                Log.i("debug","info to client：" + msgSend);
////                                return true;
//                            } catch (IOException e) {
//                                e.printStackTrace();
////                                return  false;
//                            }
//                            Sendmsg();
//                            String msgSend    = "我是手机，我已经接收到你的消息: "+msg;
//                            /* 服务器向客户端发送数据  */
//                            osw = new OutputStreamWriter(socket.getOutputStream(),
//                                    "UTF-8");
//                            PrintWriter pw = new PrintWriter(osw,true);
//                            pw.println(msgSend);
//                            pw.flush();
//                            System.out.println(""+msgSend);
//                            Log.i("debug","info to client：" + msgSend);
                        }

                        //socket.shutdownOutput();
                        //lblTitle.append("向客户端发送：" + msgSend);

                    }
                } catch (IOException e) {
                    //lblTitle.append(":"+e.toString());
                    e.printStackTrace();
                    Log.i("debug", e.toString());

                }
            }
        }.start();
        return msg;
    }
    public static boolean Sendmsg(String content)  {
        String msgSend  = content;

        try {
            osw = new OutputStreamWriter(socket.getOutputStream(),
                    "UTF-8");
            PrintWriter pw = new PrintWriter(osw,true);
            pw.println(msgSend);
            pw.flush();
            Log.i("debug","info to client：" + msgSend);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return  false;
        }



    }

}
