from nltk import word_tokenize
from nltk.corpus import stopwords




class SimilarityJaccard(object):
    def __init__(self):
        self.stopWords = set(stopwords.words('english'))

    def calculateSimilarity(self, s1, s2):
        set1 = set([i.lower() for i in s1 if i.lower() not in self.stopWords])
        set2 = set([i.lower() for i in s2 if i.lower() not in self.stopWords])
        return float(len(set1.intersection(set2))) / len(set1.union(set2))


"""
instance = SimilarityJaccard("apple banana cat dog", "apple elephant cat dog")
print instance.calculateSimilarity()
"""
