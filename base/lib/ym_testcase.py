from unittest import TestCase , TestSuite, defaultTestLoader
from lib.xmlrunner import XMLTestRunner
import ym_test_loader
class YmTestCase(TestCase):
    def __init__(self, method_name, device=None):
        super(YmTestCase,self).__init__(method_name)
        self.own_device = device
    
    def get_own_device(self):
        return self.own_device
    
#     def test_123(self):
#         print self.own_device
#         print "test(self)"
#         
#     def test_456(self):
#         print self.own_device
#         print "456"
    
    
# ts = TestSuite()
# 
# ts.addTest(ym_test_loader.ym_loader.loadTestsFromTestCase(YmTestCase, "lll"))
#  
# runner = XMLTestRunner() 
# runner.run(ts)
