package com.example;

public class RoadQueue {
    private static RoadQueue instance;
    
    private String[] roadQueue;
    private int queueSize;
    private int front;
    private int rear;

    private RoadQueue(int size) {
        this.queueSize = size;
        this.roadQueue = new String[size];
        this.front = -1;
        this.rear = -1;
    }

    public static RoadQueue createRoadQueue(int size) {
        if (instance == null) {
            instance = new RoadQueue(size);
        }
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
            } else {
                roadQueue[rear] = road;
            }
        }
    }

    public String dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        } else {
            String road = roadQueue[front];
            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % queueSize;
            }
            return road;
        }
    }

    public boolean isEmpty() {
        return front == -1 && rear == -1;
    }
}