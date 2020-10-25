# openboot
Chatbot for quickly finding answers to questions.

## Stack
- Java 11
- Spring Boot
- MongoDB
- Openshift
- Docker
- Kubernates
- Python

## How to use?

### Step 1. Clone repository
```
git clone https://github.com/openboot/openboot
```

### Step 2. Build project
```
./gradlew build --info
```

### Step 3. Create Docker images
```
docker-compose build
```

### Step 4. Up services with Docker containers
```
docker-compose up
```

### Step 5. Open browser
```
http://localhost:8080/api/chat/v1/bot?question=Как активировать карту
```

## Team
- [Sherzod Mamamdaliev](http://github.com/egnaf) &mdash; Product owner
- [Dmitrii Lebedev](https://github.com/lmaridae) &mdash; Java software engineer
- [Maria Fjodorowa](http://github.com/MariaFjodorowa) &mdash; Data scientist
- [Vasily Gavrilov](https://github.com/KtoYaTo) &mdash; UI/UX designer
- Alexander Dronov &mdash; Business analyst

## Contribute
For any problems, comments, or feedback please create an issue [here](https://github.com/openboot/openboot/issues).

## License
This software is released under the [MIT](http://mitlicense.org) license.
