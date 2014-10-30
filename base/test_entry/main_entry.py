from unittest import TestSuite
from lib.xmlrunner import XMLTestRunner
from lib import ym_test_loader
from multiprocessing import Process
import config

def init_env():
    pass

def destory_env():
    pass

def main_entry():
    i = 0
    for _ in config.ALL_TEST:
        p = Process(target=run_single_suite, args=(i,))
        p.start()
        i+=1

def run_single_suite(id):
    testclasses = config.ALL_TEST[id]
    ts = TestSuite()
    for tc in testclasses:
        ts.addTest(ym_test_loader.ym_loader.loadTestsFromTestCase(tc,id))
    runner = XMLTestRunner() 
    runner.run(ts)


if __name__ == "__main__":
    main_entry()