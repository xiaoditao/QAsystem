from numpy import genfromtxt


class LoadData(object):
	def __init__(self, filePath):
		self.filePath = filePath


	def loadData(self):
		self.data = genfromtxt(self.filePath, delimiter=',')[1:].T
		mean = self.data.mean(axis=1)
		return mean,self.data