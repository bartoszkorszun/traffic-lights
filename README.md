# Traffic Lights

This is a simple traffic management system.

## Project Structure

```
maven-project
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── example
│   │   │           └── Main.java
│   │   │           └── TrafficLights.java
│   │   └── resources
│   └── test
│       └── java
├── pom.xml
└── README.md
```

## Building the Project

To build the project, navigate to the project directory and run:

```
mvn clean install
```

## Running the Application

After building the project, you can run the application using the following command:

```
mvn exec:java
```
or if you don't want the maven info messages
```
mvn -q exec:java
```

## Dependencies

This project uses Maven for dependency management. You can add additional dependencies in the `pom.xml` file as needed.