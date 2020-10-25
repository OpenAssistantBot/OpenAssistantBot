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

### Step 2. Download model
Please download model for AI:<br>
http://files.deeppavlov.ai/embeddings/ft_native_300_ru_twitter_nltk_word_tokenize.bin


### Step 3. Build project
```
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
