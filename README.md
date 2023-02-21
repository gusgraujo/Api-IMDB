# BeeWatching project

## About the project

This project was built to create an application that communicates with the IMDB API and provides a limited set of information to a specific application called BeeWatching. The goal is to enable users of BeeWatching to access essential information about movies and TV shows, such as ratings, reviews, and plot summaries, without overwhelming them with unnecessary details.

This project utilizes Java and Spring Boot technology. The application is equipped with a Swagger extension to test the endpoints and a Postman file that allows you to test it yourself.

### Pre-requisites 

* Java 11


## Diagram
![Diagram](./docs/Diagram_arch.jpg)

## Setup API and database

**1-** Download [docker desktop](https://www.docker.com/products/docker-desktop/) on your computer. 

**2-** Clone the git repository to your computer.

**3-** Open a terminal in the root of your repository and make this sequence of commands:

* To free your docker container with you have the services already running.
> docker compose down
* To build the application and install the dependencies.

> docker compose build
* To set up the container that we build it.
> docker compose up

**4-** Enter the [swagger](http://localhost:8080/swagger-ui.html#/) link to see all the endpoints available.

**5-**  Enjoy :tada:

