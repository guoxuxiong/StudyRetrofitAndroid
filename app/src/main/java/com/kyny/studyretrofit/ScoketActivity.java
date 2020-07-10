package com.kyny.studyretrofit;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.kyny.MsgBinder;
import com.kyny.SocketService;
import com.kyny.studyretrofit.utils.FileUtils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ScoketActivity extends AppCompatActivity {
    OutputStreamWriter osw;
    public  static TextView lblTitle;
    Handler handler = null;
    private ServerSocket serverSocket;
    SocketService mSocketService;
    Socket socket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoket);
        lblTitle =(TextView)findViewById(R.id.receive);
        lblTitle.setText("这是显示的内容:");
//        startService();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                lblTitle.append("\n获取到客户端的信息：" + msg.obj);
            }
        };
//绑定Service
        FileUtils fileUtils=new FileUtils();
        File file=new File("/sdcard/test/data.txt");
        String fileContent = fileUtils.getFileContent(file);
        System.out.println("测试"+fileContent);
        Intent intent = new Intent(ScoketActivity.this,SocketService.class);
        startService(intent);


        //        ServiceConnection connection = null;
//
//        connection=new ServiceConnection() {
//            @Override
//            public void onServiceConnected(ComponentName componentName, IBinder service) {
//                mSocketService=((MsgBinder)service).getService();
//            }
//
//            @Override
//            public void onServiceDisconnected(ComponentName componentName) {
//
//            }
//        };
//        bindService(intent, connection, Context.BIND_AUTO_CREATE);
//
////        String msg = mSocketService.getMsg();
////        Log.i("guoxuxiong",msg+"***************");
//
    }



    /**
     * 启动服务监听，等待客户端连接
     */
    private  void startService() {
        lblTitle.append("\n启动服务监听：");
        try {
            // 创建ServerSocket
            serverSocket = new ServerSocket(9999);
            Log.i("郭绪雄","--开启服务器，监听端口 9999--");

            startReader(serverSocket);
        } catch (IOException e) {
            e.printStackTrace();
            lblTitle.append(e.toString());
        }
    }

    /**
     * 从参数的Socket里获取最新的消息
     */
    private  void startReader(final ServerSocket socket1) {


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
                            String msg  = new String(data,0,size);
                            Message message = new Message();
                            message.what = 1;
                            message.obj = msg;
                            handler.sendMessage(message);

                            System.out.println("获取到客户端的信息：" + msg);
                            Log.i("debug","info from client：" + msg);
                            socket.shutdownInput();
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
    }

    public void Sendmsg(View view) throws IOException{
        SocketService.Sendmsg("sss");
//        mSocketService.Sendmsg("Sucess");
//        String msgSend    = "我是手机，我已经接收到你的消息: ";
//
//        osw = new OutputStreamWriter(socket.getOutputStream(),
//                                    "UTF-8");
//                            PrintWriter pw = new PrintWriter(osw,true);
//                            pw.println(msgSend);
//                            pw.flush();
//                            System.out.println(""+msgSend);
//                            Log.i("debug","info to client：" + msgSend);
    }
}
