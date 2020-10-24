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
### 1. NLP Service.
Returns most similar question from db and cosine distance to it.
!!! Downloads a lot of staff. Downloading Fasttext model takes 20 minutes.
Downloading Ubuntu image takes time too.
```
cd ./nlp
docker build --tag nlp .
docker run -p  127.0.0.1:80:5000 nlp:latest
```
After 'Running on http://0.0.0.0:5000/' appeared in logs, you can send requests to the nlp service.
E. g. 
```
import requests
res = requests.get('http://127.0.0.1:80/', data=json.dumps({'question':'изменить номер смс'}))
print(res.json())

>>> {'value': 'Как поменять номер для SMS-уведомлений', 'distance': '0.079695225'}
```

### 2.
### 3.
### 4.
### 5.

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
