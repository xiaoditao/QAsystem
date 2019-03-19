from loadData import *
from Test import *
from Write import *
from Plot import *


class SignificanceTesting(object):
	def __init__(self):
		pass



if __name__ == '__main__':
	filePath = "ROUGE_SCORES.csv"
	loadData = LoadData(filePath)
	meanArr, data = loadData.loadData()
	test = Test()
	write = Write(data)
	write.writeOutput(meanArr, 4)
	plot = Plot(data)
	plot.twoPlot()



