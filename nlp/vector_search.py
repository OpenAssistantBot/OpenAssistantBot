import nmslib
from nltk.corpus import stopwords
import fasttext
import numpy as np
from flask import Flask, request
import json

app = Flask(__name__)

INDEX = nmslib.init(method='hnsw', space='cosinesimil')
INDEX.loadIndex('index.nmslib', load_data=True)
MODEL = fasttext.load_model("model.bin")
STOPWORDS = stopwords.words('russian')


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
    return json.dumps({'similar_question': str(id_[0])})
