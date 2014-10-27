# this test case framework work under python nose . But if I want to let it work under multi-process
# I can not use nose~

def setUp():
    init_env()
    print 'entry setup'


def tearDown():
    print 'entry teardown'
    
def init_env():
    pass

def destory_env():
    pass

def init_devs():
    pass
