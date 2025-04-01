package com.example;

import com.example.enums.SystemStateEnum;

public class SystemState {
    private static SystemStateEnum state = SystemStateEnum.NOT_STARTED;

    public static void setState(SystemStateEnum newState) {
        state = newState;
    }
    
    public static SystemStateEnum getState() {
        return state;
    }
}
