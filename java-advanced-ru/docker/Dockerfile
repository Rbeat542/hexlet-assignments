FROM eclipse-temurin:20-jdk

ARG GRADLE_VERSION=8.4
RUN apt-get update && apt-get install -y wget unzip make
RUN wget -q https://services.gradle.org/distributions/gradle-${GRADLE_VERSION}-bin.zip && \
    unzip gradle-${GRADLE_VERSION}-bin.zip -d /opt && \
    ln -s /opt/gradle-${GRADLE_VERSION}/bin/gradle /usr/bin/gradle

WORKDIR /app

COPY . .

RUN gradle :app:bootJar --no-daemon

RUN cp app/build/libs/*.jar app.jar

EXPOSE 3000

ENTRYPOINT ["java", "-jar", "app.jar"]