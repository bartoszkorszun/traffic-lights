package com.example.entities;

import com.example.enums.RoadStateEnum;

public class RoadQueue {
    private static RoadQueue instance;
    
    private Road[] roadQueue;
    private int queueSize;
    private int front;
    private int rear;
    private static int numberOfRoads = 0;

    private RoadQueue(int size) {
        this.queueSize = size;
        this.roadQueue = new Road[size];
        this.front = -1;
        this.rear = -1;
    }

    public static void createRoadQueue(int size) {
        if (instance == null) {
            instance = new RoadQueue(size);
        }
    }

    public static RoadQueue getInstance() {
        return instance;
    }

    public void enqueue(String roadName) {
        if (isEmpty()) {
            front = 0;
            rear = 0;
            Road road = new Road(roadName, TrafficLights.getInterval(), RoadStateEnum.OPEN);
            roadQueue[rear] = road;
            numberOfRoads++;
            System.out.println("Road added: " + road.getName());
            return;
        } 

        int newRear = (rear + 1) % queueSize;
        if (newRear == front) {
            System.out.println("Queue is full");
            return;
        } 

        rear = newRear;

        int openRoadIndex = -1;

        for (int i = 0; i < queueSize; i++) {
            if (roadQueue[i] != null) {
                if (roadQueue[i].getState() == RoadStateEnum.OPEN) {
                    openRoadIndex = i;
                }
            }
        }

        Road road;
        if (openRoadIndex == -1) {
            road = new Road(roadName, TrafficLights.getInterval(), RoadStateEnum.OPEN);
        } else {
            int openRoadTimeLeft = roadQueue[openRoadIndex].getTimeLeft();

            int positionsAfter = 0;
            boolean passedOpenRoad = false;

            int current = front;
            while (current != newRear) {
                if (current == openRoadIndex) {
                    passedOpenRoad = true;
                }
                if (passedOpenRoad) {
                    positionsAfter++;
                }
                current = (current + 1) % queueSize;
            }

            int timeLeft = openRoadTimeLeft - (positionsAfter * TrafficLights.getInterval());
            road = new Road(roadName, timeLeft, RoadStateEnum.CLOSED);
        }

        roadQueue[rear] = road;
        numberOfRoads++;
        System.out.println("Road added: " + road.getName());
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        } 

        Road roadToRemove = roadQueue[front];
        boolean wasOpen = roadToRemove.getState() == RoadStateEnum.OPEN;

        roadQueue[front] = null; 
        if (front == rear) {
            front = -1;
            rear = -1;
        } else {
            front = (front + 1) % queueSize;

            if (wasOpen && !isEmpty()) {
                Road nextRoad = roadQueue[front];
                nextRoad.makeOpen();
                recalculateTimings();
            }
        }
        numberOfRoads--;
        System.out.println(roadToRemove.getName() + " deleted");
    }

    private void recalculateTimings() {
        if (isEmpty()) return;

        int openIndex = -1;
        for (int i = 0; i < queueSize; i++) {
            if (roadQueue[i] != null && roadQueue[i].getState() == RoadStateEnum.OPEN) {
                openIndex = i;
                break;
            }
        }

        if (openIndex == -1) {
            roadQueue[front].makeOpen();
            openIndex = front;
        }

        int interval = TrafficLights.getInterval();
        int timeLeft = roadQueue[openIndex].getTimeLeft();
        int position = 1;
        int current = (openIndex + 1) % queueSize;

        while (current != openIndex) {
            if (roadQueue[current] != null) {
                roadQueue[current].setState(RoadStateEnum.CLOSED);
                roadQueue[current].setTimeLeft(timeLeft + (position * interval));
                position++;
            }
            current = (current + 1) % queueSize;
        }
    }

    public Road[] getRoadQueue() {
        if (isEmpty()) {
            return null;
        } else {
            return roadQueue;
        }
    }

    public static int getNumberOfRoads() {
        return numberOfRoads;
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }
}