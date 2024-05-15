# https://roboticsbackend.com/raspberry-pi-arduino-serial-communication/
# password for PI is 12345

# Code for Arduino Side
""" 
void setup() {
  Serial.begin(9600);
}

void loop() {
  Serial.println("Hello from Arduino!");
  delay(1000);
}
"""

# Code for Raspberry Pi Side
import serial

# 1st param = device name
# 2nd param = baud rate (bps)
# 3rd param = timeout
serialCon = serial.Serial("", 9600, timeout=1)

serialCon.reset_input_buffer() # wipes the input incase of any excess data

# Infinite loop to read data coming in
while True:
    if serialCon.in_waiting > 0:
        line = serialCon.readline().decode('utf-8').rstrip()
        print(line)

# Infinite loop to send data
while True:
        serialCon.write(b"Hello from Raspberry Pi!\n")
        line = serialCon.readline().decode('utf-8').rstrip()
        print(line)
        time.sleep(1)
