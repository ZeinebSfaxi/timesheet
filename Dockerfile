From openjdk:15
EXPOSE 8081
ADD /target/timesheet-2.1-SNAPSHOT docker-service-timesheet.jar
ENTRYPOINT ["java","-jar","docker-service-timesheet.jar"]
