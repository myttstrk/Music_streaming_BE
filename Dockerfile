# Sử dụng image OpenJDK chính thức làm base image
FROM openjdk:17-jdk-slim

# Thiết lập thư mục làm việc
WORKDIR /app

# Sao chép file JAR từ máy local vào Docker image
COPY target/musicstreaming-0.0.1-SNAPSHOT.jar /app/app.jar

# Sao chép thư mục resources vào Docker image
COPY src/main/resources /app/src/main/resources

# Expose cổng mà ứng dụng sẽ chạy
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
