# Code for Raspberry Pi Side
import serial
import time
import random

# 1st param = device name
# 2nd param = baud rate (bps)
# 3rd param = timeout
serialCon = serial.Serial("/dev/ttyACM0", 9600, timeout=1)

serialCon.reset_input_buffer() # wipes the input incase of any excess data

# Infinite loop to read data coming in
"""while True:
    if serialCon.in_waiting > 0:
        line = serialCon.readline().decode('utf-8').rstrip()
        print(line)"""

# Infinite loop to send data
"""while True:
        serialCon.write(b"Hello from Raspberry Pi!\n")
        line = serialCon.readline().decode('utf-8').rstrip()
        print(line)
        time.sleep(1)"""
        
# Testing Serial Com with LED and BTN
while True:
        number = serialCon.read()
        print("number:",number)
        if number != b'':
            if int.from_bytes(number, byteorder='big') == 18:
                led_number = random.randint(1,3)
                print("Button has been pressed.")
                print("Sending number " + str(led_number) + " to Arduino.")
                serialCon.write(str(led_number).encode('utf-8'))
