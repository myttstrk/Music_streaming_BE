# Sử dụng image OpenJDK chính thức làm base image
FROM openjdk:17-jdk-slim

# Đặt biến môi trường cho đường dẫn đến file JAR
ARG JAR_FILE=target/musicstreaming-0.0.1-SNAPSHOT.jar

# Sao chép file JAR từ máy local vào Docker image
COPY ${JAR_FILE} app.jar

# Expose cổng mà ứng dụng sẽ chạy
EXPOSE 8080

# Lệnh để chạy ứng dụng Spring Boot
ENTRYPOINT ["java", "-jar", "/app.jar"]
