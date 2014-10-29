from unittest import TestSuite, defaultTestLoader
from lib.xmlrunner import XMLTestRunner

TEST_SUITES =  [[],[]]
# classes of test case 

ym_test_suites = []

def init_env():
    pass

def destory_env():
    pass

def main_entry():
    init_env()
    print "whole test suite begin"
    # can be multi-process run test_cases
    testsuites = get_test_suites()
    for suite in testsuites:
        run_single_suite(suite)
    destory_env()
    
def get_test_suites():
    for s in TEST_SUITES:
        suite = TestSuite()
        for case in s:
            suite.addTests(defaultTestLoader.loadTestsFromTestCase(case))
        ym_test_suites.append(suite)

def run_single_suite(suite, output='reports', verbose=False):
    testRunner = XMLTestRunner(output=output, verbose=verbose)
    result = testRunner.run(suite)
    return result

if __name__ == "__main__":
    main_entry()