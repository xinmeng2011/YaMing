from unittest import TestCase , TestSuite
from lib.xmlrunner import XMLTestRunner

class YmTestCase(TestCase):
    def __init__(self, method_name, device):
        super.__init__(self, method_name)
        self.own_device = device
    
    def get_own_device(self):
        return self.own_device
    
    def test_123(self):
        print "test(self)"
    
    
ts = TestSuite()

ts.addTest(YmTestCase('test_123'))
 
runner = XMLTestRunner() 
runner.run(ts)

  
class T:
    def __call__(self, *args, **kwds):
        print "__call__"
        
#t = T()

#t()
