import socket


host="127.0.0.1"
port=6688
s=socket.socket(socket.AF_INET,socket.SOCK_STREAM)
s.bind((host,port))
s.listen(5)
print 'waiting socket'
sock,addr=s.accept()
print "got connection form ",sock.getpeername()
while True:
    sock.send("123")
    data = sock.recv(512)
    print 'got '+ data

