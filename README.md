# Guestbook Service

Guestbook Service is a service that allows guests to leave some comments

## Local Docker Setup

### Build

```
docker build -t guestbook:dev .
```

### Setup

```
docker network create --driver bridge guestbook-net
docker run --name guestbook_pg --network guestbook-net -e POSTGRES_PASSWORD=GuestsAreAwesome -e POSTGRES_DB=guestbook_db -d postgres
```

### Run

```
docker run --name guestbook1 --network guestbook-net -e SPRING_PROFILES_ACTIVE=docker -e PORT=8080 -p 9000:8080 -d guestbook:dev
```

## Instructions
This exercise focuses on the configuration of Docker to manage the deployment of your application both locally and remotely. Keep it simple. Use the stories and acceptance criteria to develop the Guestbook Service.

1. Create a repository.
1. Submit the link to your repository.
1. Submit the URL to your live app.

## Requirements

* Must have API specs for all endpoints, use Spring RestDocs.
* Must have integration tests and unit tests.
* As always TDD is expected.
* Utilize Spring Profiles and Docker enable multiple run environments.
* A local application running in Docker that uses Docker Postgres as DB. Provide instructions on how to run this.
* A deployed application on Heroku that uses Heroku Postgres as DB.

## Features

* Any visitor can post their name and a comment to the Guestbook.
* All visitors can see a list of every entry in the Guestbook.
