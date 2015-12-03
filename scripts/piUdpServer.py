#!/usr/bin/python

import socket

from RPIO import PWM

from time import sleep

def translate(value, inMin, inMax, outMin, outMax):
    # Figure out how 'wide' each range is
    inSpan = inMax - inMin
    outSpan = outMax - outMin

    # Convert the input range into a 0-1 range (float)
    valueScaled = float(value - inMin) / float(inSpan)

    # Convert the 0-1 range into a value in the output range.
    return outMin + (valueScaled * outSpan)
 
# Setup PWM and DMA channel 1
PWM.setup()
PWM.init_channel(1)
PWM.set_loglevel(PWM.LOG_LEVEL_ERRORS)

UDP_IP = "192.168.240.1"
UDP_PORT = 5005
 
sock = socket.socket(socket.AF_INET, # Internet
                     socket.SOCK_DGRAM) # UDP
sock.bind((UDP_IP, UDP_PORT))

PWM.add_channel_pulse(1, 17, 0,100)
sleep(0.5)
PWM.add_channel_pulse(1, 17, 0,200)
sleep(0.5)
PWM.add_channel_pulse(1, 17, 0,150)
sleep(0.5)
PWM.add_channel_pulse(1, 22, 0,60)
sleep(0.5)
PWM.add_channel_pulse(1, 22, 0,240)
sleep(0.5)
PWM.add_channel_pulse(1, 22, 0,140)
sleep(0.5)
PWM.add_channel_pulse(1, 23, 0,50)
sleep(0.5)
PWM.add_channel_pulse(1, 23, 0,230)
sleep(0.5)
PWM.add_channel_pulse(1, 23, 0,130)
sleep(0.5)
PWM.add_channel_pulse(1, 23, 0,230)

#print 'server is running...'

while True:
 data, addr = sock.recvfrom(1024) # buffer size is 1024 bytes
 data = data.decode("utf-8").split(':')
# print "steering:", data[0]
# print "gas:", data[1]
 PWM.add_channel_pulse(1, 17, 0, int(translate(int(data[0]),0,180,70,230)))
 PWM.add_channel_pulse(1, 18, 0, int(translate(int(data[1]),0,180,70,230)))
 if len(data) > 2:
  PWM.add_channel_pulse(1, 22, 0, int(translate(int(data[2]),0,180,55,235)))
  PWM.add_channel_pulse(1, 23, 0, int(translate(int(data[3]),0,180,50,235)))
