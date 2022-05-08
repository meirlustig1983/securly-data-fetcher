FROM openjdk:11

WORKDIR /

ADD build/libs/securly-data-fetcher-1.3.7-SNAPSHOT.jar //

EXPOSE 8080

ENTRYPOINT [ "java","-jar","/securly-data-fetcher-1.3.7-SNAPSHOT.jar"]