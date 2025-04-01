package com.example.enums;

public enum MenuOptionsEnum {
    ADD_ROAD    { @Override public void printOption() { System.out.println("1. Add road"); } },
    DELETE_ROAD { @Override public void printOption() { System.out.println("2. Delete road"); } },
    OPEN_SYSTEM { @Override public void printOption() { System.out.println("3. Open system"); } },
    EXIT        { @Override public void printOption() { System.out.println("0. Exit"); } };

    public abstract void printOption();
}
