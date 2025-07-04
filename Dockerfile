FROM amazoncorretto:17
WORKDIR /opt/app
COPY build.gradle gradlew gradlew.bat /opt/app/
COPY gradle /opt/app/gradle
COPY . .
RUN chmod +x ./gradlew
CMD ["./gradlew", "--version"]
