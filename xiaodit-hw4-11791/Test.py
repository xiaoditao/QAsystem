from scipy.stats import ttest_ind
from scipy.stats import ks_2samp
from scipy.stats import wilcoxon
class Test(object):
    def __init__(self):
        pass

    def ksTest(self, listA, listB):
        value, pvalue = ttest_ind(listA, listB)
        return pvalue


    def tTest(self, listA, listB):
        value, pvalue = ks_2samp(listA, listB)
        return pvalue


    def wilcoxonTest(self, listA, listB):
        value, pvalue = wilcoxon(listA, listB)
        return pvalue