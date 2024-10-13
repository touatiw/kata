# Tennis Score Application

## Description
This Java app tracks points in a tennis game.

## Prerequisites
Before you begin, make sure you have installed the following:

- **JDK 17** or later : [download](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- **Maven** : [downlaod](https://maven.apache.org/install.html)

## Steps to compile and run the application

### 1. Clone the project
Clone this repository to your local machine:

git clone https://github.com/touatiw/kata-tennis-score.git

cd kata-tennis-score
 
### 2. Compile project and run unit tests
mvn clean package

### 3. Run the application
java -jar target/kata-tennis-game-1.0-SNAPSHOT.jar {list of won points}

Example:

java -jar target/kata-tennis-game-1.0-SNAPSHOT.jar A B A A B B A B A B A A

