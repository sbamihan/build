#!/bin/sh
until nc -z edge-service 443; do
  echo "Waiting for the Edge Service"
  sleep 3
done

java -jar -Xms16m -Xmx128m /opt/lib/app.jar