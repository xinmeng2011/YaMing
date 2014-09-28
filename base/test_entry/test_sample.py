from lib import adb_helper
def test():
    print 'test'


def test_android_device_online():
    assert adb_helper.wait_for_device()
