from unittest import TestLoader
from unittest import suite
class YMTestLoader(TestLoader):
    
    def loadTestsFromTestCase(self, testCaseClass,device):
        """Return a suite of all tests cases contained in testCaseClass"""
        if issubclass(testCaseClass, suite.TestSuite):
            raise TypeError("Test cases should not be derived from TestSuite." \
                                " Maybe you meant to derive from TestCase?")
        testCaseNames = self.getTestCaseNames(testCaseClass)
        if not testCaseNames and hasattr(testCaseClass, 'runTest'):
            testCaseNames = ['runTest']
        devices=[device]
        devices = devices* len(testCaseNames)
        print devices
        print testCaseNames
        loaded_suite = self.suiteClass(map(testCaseClass, testCaseNames,devices))
        return loaded_suite
    
ym_loader=YMTestLoader()