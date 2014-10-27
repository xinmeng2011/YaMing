from unittest import TestSuite, defaultTestLoader
from lib.xmlrunner import XMLTestRunner

TEST_SUITE =  []
# classes of test case 


def init_env():
    pass

def destory_env():
    pass

def main_entry():
    init_env()
    print "whole test suite begin"
    # can be multi-process run test_cases
    tests = get_test_cases()
    run_tests(tests)
    print "test suite end"
    destory_env()

def run_tests(tests, output='reports', verbose=False):
    suite = TestSuite()
    for test in tests:
        suite.addTests(defaultTestLoader.loadTestsFromTestCase(test))
    testRunner = XMLTestRunner(output=output, verbose=verbose)
    result = testRunner.run(suite)
    return result

def get_test_cases():
    suite = []
    for case in TEST_SUITE:
            suite.append(case)


if __name__ == "__main__":
    main_entry()