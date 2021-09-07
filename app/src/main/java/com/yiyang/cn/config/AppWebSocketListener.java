package com.yiyang.cn.config;

import android.util.Log;

import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

public class AppWebSocketListener extends WebSocketListener {
    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        webSocket.send("hello,world");
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        Log.d("WebSocket","onMessage"+text);
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        Log.d("WebSocket","onMessage byteString: " + bytes.toString());
    }

    @Override
    public void onClosing(WebSocket webSocket, int code, String reason) {
        webSocket.close(1000, null);
        Log.d("WebSocket","onClosing: "+ code + "/" + reason);
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        Log.d("WebSocket" ,"onClosed: " + code + "/" + reason);
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        Log.d("WebSocket","onFailure: " + t.getMessage());
    }
}
