package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import com.company.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<String> links = new ArrayList<>();
        String file = args[0];
        //String file = "./ps_get_bookmark_links/test.txt";
        try (Scanner s = new Scanner(new FileReader(file))) {
            while (s.hasNext()) {
                links.add(s.nextLine());
            }
        }catch (FileNotFoundException f){
            f.printStackTrace();
        }

        for (String mLink : links) {
            try {
                Runnable r = new thread(mLink);
                new Thread(r).start();
                Thread.sleep(250);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}