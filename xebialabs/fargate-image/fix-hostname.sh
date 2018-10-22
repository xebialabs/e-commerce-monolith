#!/bin/sh
myip="$(/sbin/ifconfig eth0 | grep 'inet addr' | cut -d: -f2 | awk '{print $1}')"
myhostname="$(hostname)"
echo "$myip $myhostname" >> /etc/hosts
su jhipster ./entrypoint.sh
