From openjdk:15
EXPOSE 8082
ADD /target/timesheet-2.2-SNAPSHOT.jar docker-service-timesheet.jar
ENTRYPOINT ["java","-jar","docker-service-timesheet.jar"]
