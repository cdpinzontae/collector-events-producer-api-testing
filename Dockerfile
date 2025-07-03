# Copy Dependencies
FROM 277983268692.dkr.ecr.us-east-1.amazonaws.com/java:17-amazoncorretto-jdk AS base
ENV APP_HOME=/opt/app
WORKDIR $APP_HOME
COPY build.gradle gradlew gradlew.bat $APP_HOME/
COPY gradle $APP_HOME/gradle
COPY . .
RUN chmod +x ./gradlew
