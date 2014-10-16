
import json
import os
import socket


   
address = ('127.0.0.1', 6688)  
s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)  
s.connect(address)  
while True:
   data = s.recv(512)  
   print 'the data received is',data  
   
#s.send('hihi')  
   
s.close() 

STOP_SERVER_SIGNAL = 'shutDownServer'
class RpcClientBase(object):
    
    def __init__(self,host_ip,port):
        self._start_rpc_server()
        server_addr = host_ip,int(port)
        self.client_socket = socket.socket()
        self.client_socket.settimeout(120)
        self.client_socket.connect(server_addr)
        self.client_socket_file = self.client_socket.makefile(mode = 'w+b')
        self.id = 0
        isHandShake = os.environ.get('AP_HANDSHAKE')
        if isHandShake:
            self._authenticate(isHandShake)
        
    def _start_rpc_server(self):
        raise NotImplementedError()
    
    def _stop_rpc_server(self):
        raise NotImplementedError()
    
    def stop(self):
        self._stop_rpc_server()
        self.client_socket.close()
    
    def _rpc(self, method,*args):
        data = {'id': self.id,
                'method': method,
                'params': args}
        request = json.dumps(data)
        self.client_socket_file.write(request + '\n')
        self.client_socket_file.flush()
        if(method == STOP_SERVER_SIGNAL):
            return
        response = self.client_socket_file.readline()
        self.id += 1
        if response:
            result = json.loads(response)
            if result['error'] is not None:
                raise Exception(result['error'])
            return result['result']
        else:
            return None
    
    
    def __getattr__(self, name):
        def rpc_call(*args):
            return self._rpc(name, *args)
        return rpc_call