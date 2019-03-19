#!/usr/bin/env python

import sys
from deiis.rabbit import Message, MessageBus
from deiis.model import Serializer, DataSet, Question
host = os.environ.get('RABBITMQ_HOST')

if __name__ == '__main__':
    if len(sys.argv) == 1:
        print 'Usage: python pipeline.py <data.json>'
        exit(1)
    filename = sys.argv[1]
    print 'Processing ' + filename
    fp = open(filename, 'r')
    dataset = Serializer.parse(fp, DataSet)
    fp.close()


    pipeline = ['expand.none','mmr.core', 'tiler.concat', 'results']
    count=0
    bus = MessageBus(host=host)
    for index in range(0,10):
        question = dataset.questions[index]

        message = Message(body=question, route=pipeline)
        bus.publish("splitter", message)
        count = count + 1

    print 'Sent {} questions for ranking.'.format(count)
    print 'Done.'
