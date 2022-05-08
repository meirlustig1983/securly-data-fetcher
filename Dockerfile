FROM openjdk:11

WORKDIR /

ADD build/libs/securly-data-fetcher-1.3.9-SNAPSHOT.jar //

EXPOSE 8080

ENTRYPOINT [ "java","-jar","/securly-data-fetcher-1.3.9-SNAPSHOT.jar"]