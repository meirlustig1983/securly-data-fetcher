# SECURLY HOME ASSIGNMENT

## How to run this web service?

* You have to make sure that 'JDK11' and 'DOCKER' are installed on your MAC/PC.
* Download the project from GitHub
* Go to the project folder and build the source code with 'Gradle' : ***./gradlew clean build*** 
* Create a docker image with : ***docker build -t securly-data-fetcher .***
* Go to 'docker-compose.yml' file and replace Canvas-API credentials with your credentials. {CANVAS_INSTANCE_URL , CANVAS_CLIENT_ID, CANVAS_SECRET_KEY}
* In order to start the service, run ***docker-compose up***
* Go to your favorite browser and hit ***http://localhost:8080/***
* In order to stop the service, run:  ***docker-compose stop*** (or kill)