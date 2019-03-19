import matplotlib.pyplot as plt


class Plot(object):
	def __init__(self,data):
		self.data = data



	def twoPlot(self):
		plt.figure()
		plt.boxplot(self.data.T)
		plt.show()
		plt.violinplot(self.data.T)
		plt.show()