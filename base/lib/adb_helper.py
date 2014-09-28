import os
from util import os_util
import time


ADB_PATH = os.path.sep.join([os.getenv('ANDROID_SDK'), 'platform-tools','adb'])
ADB = 'adb '

def shell(cmd):
    cmd = ADB + 'shell ' + cmd
    return os_util.execute(cmd)

def wait_for_device(timeout=60):
    for _ in range(timeout):
        devs = devices()
        if len(devs):
            return True
        time.sleep(1)
    return False

def devices():
    cmd = ADB + 'devices'
    ret = os_util.execute(cmd)
    print ret
    # get device list
    try:
        list_header = 'List of devices attached '
        ret = ret[ret.index(list_header) +len(list_header)+ 1 :]
        print ret
    except ValueError:
        pass
    # find online device
    dev_list = []
    for i in ret.split('\n'):
        if len(i) > 1: 
            dev_list.append(i)
    print dev_list
    return dev_list


def start_instrumentation(package):
    pass

def wait_for_launch(timeout):
    pass