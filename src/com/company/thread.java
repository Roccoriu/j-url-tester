package com.company;

import java.io.IOException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

public class thread implements Runnable {

    private static String white = "\033[1;37m";

    private static String urlink = "";
    private static int code = 0;

    public thread(String url){
        urlink = url;
    }

    public void run() {
        try {
            URL url = new URL(urlink);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            code = (connection.getResponseCode());

        } catch (ConnectException b) {
            code = (600);
        } catch (java.net.UnknownHostException c) {
            code = (601);
        } catch (javax.net.ssl.SSLHandshakeException d) {
            code = (602);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String state = Integer.toString(code);

        String status = "";
        String statColor = "";
        String codeColor = "\033[0;33m";

        if(state.indexOf("1",0) == 0) {
            status = "Informational";
            statColor = "\033[1;33m";
        }else if(state.indexOf("2", 0) == 0){
            status = "Success";
            statColor = "\033[0;32m";
        }else if(state.indexOf("3", 0) == 0){
            status = "Redirection";
            statColor = "\033[0;32m";
        }else if(state.indexOf("4",0) == 0){
            status = "Client Err";
            statColor = "\033[0;31m";
        }else if(state.indexOf("5", 0) == 0){
            status = "Server Error";
            statColor = "\033[0;31m";
        }else if(state.indexOf("6", 0) == 0){
            status = "Unknown Error";
            statColor = "\033[0;31m";
        }

        System.out.printf("%s%15s%s code:%5s:%s url:%s%n",statColor, status, codeColor, state, white, urlink);
    }
}


