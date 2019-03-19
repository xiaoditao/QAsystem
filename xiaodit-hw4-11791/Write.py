import csv
from scipy.stats import ttest_ind
from scipy.stats import ks_2samp
from scipy.stats import wilcoxon

class  Write(object):
	def __init__(self,data):
		self.data = data



	def writeOutput(self, meanArr, n):
		dictScoreCsv = {0: 'ROUGE-2:Baseline', 1: 'ROUGE-2:Fusion', 2: 'ROUGE-2:Ordering', 3: 'ROUGE-2:Ordering+Fusion', 4:'ROUGE-SU4:Baseline', 5:'ROUGE-SU4:Fusion', 6:'ROUGE-SU4:Ordering', 7:'ROUGE-SU4:Ordering+Fusion'}
		list1 = list(range(0,n))

		list1res = []
		for i in range(len(list1)):
			for j in range(i + 1, len(list1)):
				if i != j:
					list1res.append((list1[i], list1[j]))



		list2 = list(range(n,n*2))
		list2res = []
		for i in range(len(list2)):
			for j in range(i + 1, len(list2)):
				if i != j:
					list2res.append((list2[i], list2[j]))


		listAll = list1res + list2res



		resultsFile = open('result.csv', 'w')
		w = n+1
		h = len(list1res)*2+1


		resultsData = [[0 for x in range(w)] for y in range(h)]

		resultsData[0] = [ 'metric & model', 'mean diff', 'P(T test)', 'P(wilcoxon test)' ,'P(ks test)']
		for row in range(1,13):

			resultsData[row][0] = dictScoreCsv.get(listAll[row-1][0]) + ' ' + dictScoreCsv.get(listAll[row-1][1])
			resultsData[row][1] = meanArr[listAll[row-1][0]] - meanArr[listAll[row-1][1]]
			resultsData[row][2] = ttest_ind(self.data[listAll[row-1][0]],self.data[listAll[row-1][1]]).pvalue
			resultsData[row][3] = wilcoxon(self.data[listAll[row-1][0]],self.data[listAll[row-1][1]]).pvalue
			resultsData[row][4] = ks_2samp(self.data[listAll[row-1][0]],self.data[listAll[row-1][1]]).pvalue

		with resultsFile:
			writer = csv.writer(resultsFile)
			writer.writerows(resultsData)
		resultsFile.close()
