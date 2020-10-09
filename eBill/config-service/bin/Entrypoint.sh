#!/bin/sh
#until nc -z config-service 8888; do
#  echo "Waiting for the Config Service"
#  sleep 3
#done

java -jar -Xms16m -Xmx128m /opt/lib/app.jar