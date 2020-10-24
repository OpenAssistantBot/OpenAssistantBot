FROM ubuntu:16.04
RUN apt-get clean && apt-get update
RUN apt-get install -yqq curl
RUN apt-get install -yqq build-essential libbz2-dev libssl-dev libreadline-dev git\
                         libsqlite3-dev cmake wget gfortran libatlas-base-dev libatlas-dev \
                         libatlas3-base libhdf5-dev libxslt-dev \
                         zlib1g-dev pkg-config liblapacke-dev \
                         locales

RUN curl -L https://raw.githubusercontent.com/yyuu/pyenv-installer/master/bin/pyenv-installer | bash
ENV PYENV_ROOT /root/.pyenv
ENV PATH /root/.pyenv/shims:/root/.pyenv/bin:/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
RUN pyenv install 3.6.7
RUN pyenv global 3.6.7

RUN pip  install -U pip
RUN python -m pip install -U cython
RUN python -m pip install -U numpy==1.19.1 # thanks to libatlas-base-dev (base! not libatlas-dev), it will link to atlas
COPY requirements.txt ./
RUN python -m pip install -r requirements.txt
RUN python -m pip install nmslib==2.0.6
RUN wget http://files.deeppavlov.ai/embeddings/ft_native_300_ru_twitter_nltk_word_tokenize.bin
COPY ./ ./
CMD ["python", "./vector_search.py"]
