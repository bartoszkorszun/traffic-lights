package com.example.utils;

import java.util.Scanner;
import java.util.concurrent.locks.ReentrantLock;

public class SharedScanner {
    private static final Scanner scanner = new Scanner(System.in);
    private static final ReentrantLock scannerLock = new ReentrantLock();

    public static String getScannerInput() {
        String input = null;
        scannerLock.lock(); 
        try {
            input = scanner.nextLine();
        } finally {
            scannerLock.unlock();
        }
        return input;
    }
}
