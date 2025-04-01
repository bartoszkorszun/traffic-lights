package com.example.threads;

import com.example.enums.SystemStateEnum;
import com.example.utils.ClearConsole;
import com.example.utils.SharedScanner;
import com.example.utils.SystemState;

public class StateChangeThread extends Thread {

    private static StateChangeThread stateChangeThreadInstance = null;

    private StateChangeThread() {}

    public static StateChangeThread getInstance() {
        if (stateChangeThreadInstance == null) {
            stateChangeThreadInstance = new StateChangeThread();
        }
        return stateChangeThreadInstance;
    }

    @Override
    public void run() {
        while (true) {         
            if (SystemState.getState() == SystemStateEnum.SYSTEM) {
                String input = SharedScanner.getScannerInput();
                if (input.isEmpty()) {
                    ClearConsole.clearConsole(); 
                    SystemThread.setOnDisplay(false);
                    MenuThread.setOnDisplay(true);
                    SystemState.setState(SystemStateEnum.MENU);
                    System.out.println("System state changed to MENU.");
                }
            }
        }
    }
} 
