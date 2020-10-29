FROM openjdk:8-jdk-alpine
ADD target/*.jar /opt/lib/app.jar
ADD Entrypoint.sh /opt/bin/Entrypoint.sh
RUN chmod 755 /opt/bin/Entrypoint.sh
ENTRYPOINT [ "sh", "-c", "/opt/bin/Entrypoint.sh" ]