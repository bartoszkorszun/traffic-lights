package com.example;

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
        Road road = null;
        if (isEmpty()) {
            front = 0;
            rear = 0;

            road = new Road(roadName, TrafficLights.getInterval(), RoadStateEnum.OPEN);

            roadQueue[rear] = road;
            numberOfRoads++;
        } else {
            rear = (rear + 1) % queueSize;
            if (rear == front) {
                System.out.println("Queue is full");
                rear = (rear - 1 + queueSize) % queueSize; 
                return;
            } else {
                int prev = (rear - 1 + queueSize) % queueSize;
                int timeInterval = roadQueue[prev].getTimeLeft();
                RoadStateEnum state = roadQueue[prev].getState();
                if (numberOfRoads == 1) {
                    if (state == RoadStateEnum.OPEN) {
                        state = RoadStateEnum.CLOSED;
                    } else {
                        state = RoadStateEnum.OPEN;
                    }
                    road = new Road(roadName, timeInterval, state);
                } else {
                    if (state == RoadStateEnum.CLOSED) road = new Road(roadName, timeInterval + TrafficLights.getInterval(), RoadStateEnum.CLOSED);
                    else road = new Road(roadName, timeInterval, RoadStateEnum.CLOSED);
                    
                    int next = (rear + 1) % queueSize;
                    while (next != rear) {
                        if (roadQueue[next] == null) {
                            next = (next + 1) % queueSize;
                            continue;
                        }
                        if (roadQueue[next].getState() == RoadStateEnum.OPEN) {
                            break;
                        } else {
                            roadQueue[next].increaseTimeLeft();
                            next = (next + 1) % queueSize;
                        }
                    }
                }

                roadQueue[rear] = road;
                numberOfRoads++;
            }
        }
        System.out.println("Road added: " + road);
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        } else {
            Road road = roadQueue[front];
            roadQueue[front] = null; 
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % queueSize;
            }
            numberOfRoads--;
            System.out.println(road.getName() + " deleted");
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