Anish Prasad, Hossam Basiony, and Rahil Mehta - Alex Golovin and Navee Kaur were part of the project but worked on the research portion
# 461SchedulingTool
This program is a scheduling tool for project management. It is a final project for CSS461 Software Project Management at the University of Washington Bothell. It can be used to track users, projects, and tasks and perform CPM/PERT calculations.

# Program Details
The program is entirely written in Java. The program consists of Application, Project, SchedulingObject, Task, and User classes. The Test class was used for testing before the Application class was written for command-line interactions. Application is the main class and it is responsible for presenting options and prompts to the user. SchedulingObject is the superclass Project, Task, and User inherit from. A project belongs to a user and a project has multiple tasks. 

# Compilation and Execution Instructions
The program should be compiled using the JDK version 8. If the compiler defaults to JDK 9, the ```--release 8``` should be used.
This is an example of how to compile the program:
```
javac --release 8 Application.java Project.java SchedulingObject.java Task.java User.java
