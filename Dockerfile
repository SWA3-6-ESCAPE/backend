FROM arm64v8/openjdk:17

COPY target/* .

ENTRYPOINT ["java", "-jar", "escape.jar", "--spring.profiles.active=prod"]

