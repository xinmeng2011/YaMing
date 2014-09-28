
from subprocess import Popen, PIPE, STDOUT


def execute(cmd):
    p = Popen(cmd, shell=True, stdin=PIPE, stdout=PIPE, stderr=STDOUT,universal_newlines=True)
    ret = p.communicate()
    return ret[0]