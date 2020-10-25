# OpenAssistantBot
Chatbot for quickly finding answers to questions.

## Stack
- Java 11
- Spring Boot
- Hibernate
- MongoDB
- Openshift
- Docker
- Gradle
- Python

## How to use?

### Step 1. Clone repository
```
git clone https://github.com/openboot/openboot
```

### Step 2. Download model and install Java
Please download model for AI and move to **nlp** directory:<br>
http://files.deeppavlov.ai/embeddings/ft_native_300_ru_twitter_nltk_word_tokenize.bin

Install Java 11. On Ubuntu:
```
sudo apt install openjdk-11-jre
```

### Step 3. Build project

```
cd open-assistant-service
./gradlew build --info

cd ..

cd telegram-bot-service
./gradlew build --info
```

### Step 4. Create Docker images
```
docker-compose build
```

### Step 5. Up services with Docker containers
```
docker-compose up
```

### Step 6. Open browser
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
