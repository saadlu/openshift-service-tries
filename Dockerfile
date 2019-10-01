FROM adoptopenjdk:11.0.4_11-jdk-hotspot

COPY gradle /build/gradle
COPY src /build/src
COPY build.gradle /build/build.gradle
COPY gradlew /build/gradlew
COPY settings.gradle /build/settings.gradle

WORKDIR /build

RUN ./gradlew clean build

RUN ls /build/build/libs/service-tries-0.0.1-SNAPSHOT.jar

FROM adoptopenjdk:11.0.4_11-jre-hotspot

COPY --from=0 /build/build/libs/service-tries-0.0.1-SNAPSHOT.jar /webapp/service.jar

CMD java -jar /webapp/service.jar
