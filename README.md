[![Open in Visual Studio Code](https://classroom.github.com/assets/open-in-vscode-2e0aaae1b6195c2367325f4f02e2d04e9abb55f0b24a779b69b11b9e10269abc.svg)](https://classroom.github.com/online_ide?assignment_repo_id=16246153&assignment_repo_type=AssignmentRepo)

[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/INcAwgxk)
# Portfolio project IDATT1003
This file uses Mark Down syntax. For more information see [here]([x]https://www.markdownguide.org/basic-syntax/).

[//]: # (TODO: Fill inn your name and student ID)

STUDENT NAME = Yazan Zarka  
STUDENT ID = 530542

## Requirements
    
[//] Make sure you have the following installed:
* `Apache Maven 3.9.9 (8e8579a9e76f7d015ee5ec7bfcdc97d260186937)`
* `Oracle OpenJDK 23`

## Project description

[//] Food Management System is a system that is built with scability in mind. The system is built to manage food items in a restaurant. The system is built with the following features:
- Manage Groceries (Add, Remove, List)
- Manage Recipes (Add, Remove, List)
- Store Groceries in Storage Units (Add, Remove, List)



## Project structure

[//] All modules follow the following structure:
* Module
    * src
        * main
            * java
                * org.ntnu.packageName
                  
        * test
            * java
                * org.ntnu.packageName

[//] Food Management System is built modular with SOLID principles without Dependency Injection. The system is divided into the following modules:


* Application (Main Module)
    * Application - The main class of the system
    * Menus - A directory containing the menu contexts of the system and commands
    * Commands - A directory containing the commands of the system
    * Core - A directory containing the Bootstrapper and the Application Context
    * Containers - A directory containing the Food Storage Containers of the system.
* Food - Contains the classes for managing food items 
* Console - Contains the classes for managing the console input and output
* Unit - Contains SI units

## Link to repository

[//] https://github.com/NTNU-IDI/idatt1003-mappe-2024-yazanzarka1.git

## How to run the project


[//] you run the project by running the following command in the terminal:
` ./BuildAndRun.ps1 
`


## How to run the tests

[//] you run the tests by running the following command in the terminal:
` ./RunTests.ps1 
`

## References

[//]: # (TODO: Include references here, if any. For example, if you have used code from the course book, include a reference to the chapter.
Or if you have used code from a website or other source, include a link to the source.)