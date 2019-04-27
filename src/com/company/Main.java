package com.company;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    private static String white = "\033[1;37m";
    private static int htmlCode(String link){
        int code = 0;
        try {
            //String mLink = java.net.URLEncoder.encode(link, "UTF-8");
            //URI uri = new URI(mLink);
            //System.out.println(uri);
            if(link.isEmpty()){
                code = 1000;
            } else {
                long t= System.currentTimeMillis();
                long end = t+100;
                while(System.currentTimeMillis() < end) {
                    URL url = new URL(link);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    code = connection.getResponseCode();
                }

            }
        } catch (ConnectException b){
            code = 600;
        } catch (java.net.UnknownHostException c){
            code = 601;
        } catch (javax.net.ssl.SSLHandshakeException d){
            code = 602;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return code;
    }

    private static void formatOut(String link){
        String state = Integer.toString(htmlCode(link));

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

        System.out.printf("%s%15s%s code:%5s:%s url:%s%n",statColor, status, codeColor, state, white, link);
    }

    public static void main(String[] args) {
        ArrayList<String> links = new ArrayList<>();
        //String file = args[0];
        String file = "./ps_get_bookmark_links/test.txt";
        try (Scanner s = new Scanner(new FileReader(file))) {
            while (s.hasNext()) {
                links.add(s.nextLine());
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }

        for (String mLink : links) {
            formatOut(mLink);
        }
    }
}