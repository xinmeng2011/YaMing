import socket
import json

rpc_socket = None

sequence_number = 0

def build_rpc_connect(host, port):
    s = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
    s.bind((host, port))
    s.listen(1)
    rpc_socket,addr = s.accept()
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

