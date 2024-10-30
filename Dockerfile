# Sử dụng image chính thức của Maven để build project
FROM maven:3.8.5-openjdk-17 AS build

# Thiết lập thư mục làm việc trong container
WORKDIR /app

# Copy toàn bộ project vào container
COPY . .

# Build project và tạo file JAR
RUN mvn clean package -DskipTests

# Tạo image cuối cùng sử dụng OpenJDK để chạy file JAR
FROM openjdk:17-jdk-slim

# Tạo thư mục để chứa ứng dụng
WORKDIR /app

# Copy file JAR từ giai đoạn build vào thư mục làm việc của image
COPY --from=build /app/target/*.jar app.jar

# Expose port của ứng dụng nếu cần
EXPOSE 8080

# Lệnh để chạy ứng dụng
ENTRYPOINT ["java", "-jar", "app.jar"]
