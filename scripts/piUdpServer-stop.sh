#!/bin/bash

pid=`ps aux | grep piUdpServer | awk '{print $2}'`
kill -9 $pid
