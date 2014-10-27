import socket
import json
import time
from util import  os_util
from lib import ym_device_manager
from multiprocessing import Process
rpc_socket = None

sequence_number = 0

def build_rpc_connect(host, port):
    os_util.print_flush(host+ "  " + str(port))
    s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    s.bind((host, port))
    s.listen(5)
    # wake up instrumentation by adb command
    while True:
        rpc_socket,addr = s.accept()
        ym_device_manager.insert_client_to_list(rpc_socket)
    print "got connection form ",rpc_socket.getpeername() 
    
def invoke(method, *args):
    if not rpc_socket:
        return
    data = {'sequence_number': sequence_number,
            'method': method,
            'arguments': args}
    request = json.dumps(data)
    rpc_socket.send(request + '\n')
    rpc_socket.flush()
    response = rpc_socket.readline()
    print response
    sequence_number += 1
    result = json.loads(response)
    print result

def wait_for_connect_other_process(port):
    p = Process(target=build_rpc_connect, args=("127.0.0.1", port))
    p.start()
    
    
if __name__ == "__main__":
    wait_for_connect_other_process(6688)
    import time
    time.sleep(10)
    print "end"