package com.example;

public class RoadQueue {
    private static RoadQueue instance;
    
    private String[] roadQueue;
    private String[] roadNames;
    private int queueSize;
    private int front;
    private int rear;

    private RoadQueue(int size) {
        this.queueSize = size;
        this.roadQueue = new String[size];
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

    public void enqueue(String road) {
        if (isEmpty()) {
            front = 0;
            rear = 0;
            roadQueue[rear] = road;
        } else {
            rear = (rear + 1) % queueSize;
            if (rear == front) {
                System.out.println("Queue is full");
                rear = (rear - 1 + queueSize) % queueSize; 
                return;
            } else {
                roadQueue[rear] = road;
            }
        }
        System.out.println("Road added: " + road);
    }

    public void dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return;
        } else {
            String road = roadQueue[front];
            roadQueue[front] = null; 
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % queueSize;
            }
            System.out.println(road + " deleted");
        }
    }

    public String[] getRoadNames() {
        if (isEmpty()) {
            return null;
        } else {
            if (roadNames == null) {
                roadNames = new String[queueSize];
            }
            for (int i = 0; i < queueSize; i++) {
                roadNames[i] = null;
            }
            int index = 0;
            for (int i = front; i != rear; i = (i + 1) % queueSize) {
                roadNames[index++] = roadQueue[i];
            }
            roadNames[index] = roadQueue[rear];
            return roadNames;
        }
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }
}