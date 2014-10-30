from lib import adb_helper
from lib import ym_testcase
def test():
    print 'test'


def test_android_device_online():
    assert adb_helper.wait_for_device()


class testtest(ym_testcase.YmTestCase):
    def test_1(self):
        print self.get_own_device()
        print 1
        
    def test_2(self):
        print self.get_own_device()
        print 2
        
    def test_3(self):
        print self.get_own_device()
        print 3    