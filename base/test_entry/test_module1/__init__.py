from lib import ym_device_manager

ym_device = None

test_value= "123"

def setUp():
    ym_device = ym_device_manager.get_free_device_node()
    if not ym_device:
        print "no device available"
    print 'module1 setup'

def tearDown():
    print 'module1 tearDown'
