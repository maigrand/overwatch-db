FROM gradle:7.6.0-jdk17 as build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar


FROM amazoncorretto:17
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/build/libs/*.jar /app/app.jar
CMD ["java", "-jar", "app/app.jar"]