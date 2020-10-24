import nmslib
import nltk
from nltk.corpus import stopwords
import fasttext
import numpy as np
from flask import Flask, request
import json

app = Flask(__name__)

INDEX = nmslib.init(method='hnsw', space='cosinesimil')
INDEX.loadIndex('index.nmslib', load_data=True)
MODEL = fasttext.load_model("ft_native_300_ru_twitter_nltk_word_tokenize.bin")
nltk.download('stopwords')
STOPWORDS = stopwords.words('russian')
with open('mapping.json', 'r') as f:
    MAPPING = json.load(f)


def preprocess(x):
    lemmas = x.lower().split()
    return [lemma for lemma in lemmas if lemma not in STOPWORDS]


def vectorize(x):
    vecs = []
    for word in x:
        vecs.append(MODEL[word])
    return np.mean(vecs, axis=0)


@app.route('/', methods=['GET', 'POST'])
def handle_question():
    question = json.loads(request.data)['question']
    processed = preprocess(question)
    vectorized = vectorize(processed)
    id_, distance = INDEX.knnQuery(vectorized, k=1)
    return json.dumps({'value': MAPPING[str(id_[0])],
                       'distance': str(distance[0])})


if __name__ == '__main__':
    app.run(host='0.0.0.0')
