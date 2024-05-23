# https://anshu-dev.medium.com/creating-a-python-web-server-from-basic-to-advanced-449fcb38e93b

import os
import serial
import time
import random
from http.server import BaseHTTPRequestHandler, HTTPServer


serialCon = serial.Serial("/dev/ttyACM0", 9600, timeout=1)
serialCon.reset_input_buffer()

host_name = '192.168.0.105'  # IP Address of Raspberry Pi
host_port = 8000


class MyServer(BaseHTTPRequestHandler):

    def do_HEAD(self):
        self.send_response(200)
        self.send_header('Content-type', 'text/html')
        self.end_headers()

    def _redirect(self, path):
        self.send_response(303)
        self.send_header('Content-type', 'text/html')
        self.send_header('Location', path)
        self.end_headers()

    def do_GET(self):
        if self.path == '/':
            self.path = '/remoteWithCircle.html'
        try:
            file_to_open = open(self.path[1:]).read()
            self.send_response(200)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(bytes(file_to_open, 'utf-8'))
        except:
            self.send_response(404)
            self.send_header('Content-type', 'text/html')
            self.end_headers()
            self.wfile.write(b'404 - Not Found')

    def do_POST(self):

        content_length = int(self.headers['Content-Length'])
        post_data = self.rfile.read(content_length).decode("utf-8")
        post_data = post_data.split("=")[1] #post_data is the same as text of button

        #change if/elif statements here to correspond to correct function robot is meant to do
        #print(post_data) #checking what code is being sent
        
        if post_data == 'Forward':
            serialCon.write(str(1001).encode('utf-8'))
        elif post_data == "Backward":
            serialCon.write(str(1002).encode('utf-8'))
        elif post_data == "Spin+Left":
            serialCon.write(str(1003).encode('utf-8'))
        elif post_data == "Spin+Right":
            serialCon.write(str(1004).encode('utf-8'))
            
        elif post_data == "Obstacle+Avoidance":
            serialCon.write(str(1010).encode('utf-8'))
        elif post_data == "Line+Tracking":
            serialCon.write(str(1011).encode('utf-8'))
        
        else:
            serialCon.write(str(1000).encode('utf-8'))

        print("LED is {}".format(post_data))
        self._redirect('/')  # Redirect back to the root url


# # # # # Main # # # # #

if __name__ == '__main__':
    http_server = HTTPServer((host_name, host_port), MyServer)
    print("Server Starts - %s:%s" % (host_name, host_port))

    try:
        http_server.serve_forever()
    except KeyboardInterrupt:
        http_server.server_close()
