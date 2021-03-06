
from subprocess import Popen, PIPE, STDOUT
import os,sys
import time
from multiprocessing import Process

def execute(cmd):
    p = Popen(cmd, shell=True, stdin=PIPE, stdout=PIPE, stderr=STDOUT,universal_newlines=True)
    ret = p.communicate()
    return ret[0]

def execute_timer(seconds,cmd):
    time.sleep(seconds)
    execute(cmd)

def run_after_sleep(cmd , time = 5):
    print "in parent process (id %s)" % os.getpid()
    p = Process(target=execute_timer, args=(time,cmd))
    p.start()
    
    
def print_flush(thing):
    print thing
    sys.stdout.flush()
    