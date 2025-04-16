package com.example;

import com.example.enums.RoadStateEnum;

public final class Road {
    private String name;
    private RoadStateEnum state;
    private int timeLeft;

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
            switchState();
        } else {
            this.timeLeft--;
        }
    }

    private void switchState() {
        Road[] roads = RoadQueue.getInstance().getRoadQueue();

        if (this.state == RoadStateEnum.OPEN) {
            this.state = RoadStateEnum.CLOSED;

            Road nextRoad = findNextRoad();
            if (nextRoad != null) {
                nextRoad.makeOpen();
            }

            if (RoadQueue.getNumberOfRoads() > 1) {
                this.timeLeft = TrafficLights.getInterval() * (RoadQueue.getNumberOfRoads() - 1);
            } else {
                this.timeLeft = TrafficLights.getInterval();
            }
        } else {
            boolean hasOpenRoad = false;

            if (roads != null) {
                for (Road road : roads) {
                    if (road != null && road.getState() == RoadStateEnum.OPEN) {
                        hasOpenRoad = true;
                        break;
                    }
                }
            }

            if (!hasOpenRoad) {
                this.state = RoadStateEnum.OPEN;
                this.timeLeft = TrafficLights.getInterval();

                updateOtherRoadsTimings();
            } else {
                this.timeLeft = TrafficLights.getInterval();
            }
        }
    }

    private Road findNextRoad() {
        Road[] roads = RoadQueue.getInstance().getRoadQueue();
        if (roads == null) return null;

        int thisIndex = -1;
        for (int i = 0; i < roads.length; i++) {
            if (roads[i] == this) {
                thisIndex = i;
                break;
            }
        }

        if (thisIndex == -1) return null;

        int nextIndex = (thisIndex + 1) % roads.length;
        while (nextIndex != thisIndex) {
            if (roads[nextIndex] != null) {
                return roads[nextIndex];
            }
            nextIndex = (nextIndex + 1) % roads.length;
        }

        return null;
    }

    private void updateOtherRoadsTimings() {
        Road[] roads = RoadQueue.getInstance().getRoadQueue();
        if (roads == null) return;

        int thisIndex = -1;
        for (int i = 0; i < roads.length; i++) {
            if (roads[i] == this) {
                thisIndex = i;
                break;
            }
        }

        if (thisIndex == -1) return;

        int position = 1;
        int currentIndex = (thisIndex + 1) % roads.length;
        while (currentIndex != thisIndex) {
            if (roads[currentIndex] != null) {
                roads[currentIndex].setState(RoadStateEnum.CLOSED);
                roads[currentIndex].setTimeLeft(TrafficLights.getInterval() * position);
                position++;
            }
            currentIndex = (currentIndex + 1) % roads.length;
        }
    }

    public void makeOpen() {
        this.state = RoadStateEnum.OPEN;
        this.timeLeft = TrafficLights.getInterval();
    }

    public void forceClose(int interval) {
        this.state = RoadStateEnum.CLOSED;
        this.timeLeft = interval * RoadQueue.getNumberOfRoads();
    }

    public void setTimeLeft(int timeLeft) {
        this.timeLeft = timeLeft;
    }

    public int getTimeLeft() {
        return timeLeft;
    }

    public void setState(RoadStateEnum state) {
        this.state = state;
    }

    public RoadStateEnum getState() {
        return state;
    }

    public String getName() {
        return name;
    }
    
    public String getStateString() {
        return state == RoadStateEnum.CLOSED ? "closed" : "open";
    }

    @Override
    public String toString() {
        if (state == RoadStateEnum.OPEN) {
            if (timeLeft <= 1) {
                return "\u001B[33m" + "Road \"" + name + "\" will be " + getStateString() + " for " + timeLeft + "s." + "\u001B[0m";
            }
            return "\u001B[32m" + "Road \"" + name + "\" will be " + getStateString() + " for " + timeLeft + "s." + "\u001B[0m";
        } else {
            return "\u001B[31m" + "Road \"" + name + "\" will be " + getStateString() + " for " + timeLeft + "s." + "\u001B[0m";
        }
    }
}