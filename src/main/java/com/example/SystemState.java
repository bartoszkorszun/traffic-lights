package com.example;

import java.util.concurrent.locks.ReentrantLock;

import com.example.enums.SystemStateEnum;

public class SystemState {
    private static SystemStateEnum state = SystemStateEnum.NOT_STARTED;
    private static final ReentrantLock stateLock = new ReentrantLock();

    public static void setState(SystemStateEnum newState) {
        stateLock.lock();
        try {
            state = newState;
        } finally {
            stateLock.unlock();
        }
    }
    
    public static SystemStateEnum getState() {
        stateLock.lock();
        try {
            return state;
        } finally {
            stateLock.unlock();
        }
    }
}
