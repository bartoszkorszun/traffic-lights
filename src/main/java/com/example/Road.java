package com.example;

import com.example.enums.RoadStateEnum;

public final class Road {
    private String name;
    private RoadStateEnum state;
    private int timeLeft;
    // public static Road previous = null;
    // public static Road next = null;

    public Road(String name, int timeLeft, RoadStateEnum state) {
        this.name = name;
        this.state = state;
        this.timeLeft = timeLeft;
    }

    public void increaseTimeLeft() {
        this.timeLeft += TrafficLights.getInterval();
    }

    public void decreaseTimeLeft() {
        if (this.timeLeft <= 0) {
            if (this.state == RoadStateEnum.OPEN) {
                this.state = RoadStateEnum.CLOSED;
                if (RoadQueue.getNumberOfRoads() > 2) this.timeLeft = TrafficLights.getInterval() * (RoadQueue.getNumberOfRoads() - 1);
                else this.timeLeft = TrafficLights.getInterval();
            } else {
                this.state = RoadStateEnum.OPEN;
                this.timeLeft = TrafficLights.getInterval();
            }
        }        
        this.timeLeft--;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public String getName() {
        return name;
    }

    public RoadStateEnum getState() {
        return state;
    }
    
    public String getStateString() {
        return state == RoadStateEnum.CLOSED ? "closed" : "open";
    }
}