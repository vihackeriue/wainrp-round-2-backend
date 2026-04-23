# Stage 1: Build với Maven và Java 21
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# Copy file cấu hình trước để tận dụng cache của Docker
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy toàn bộ code và build
COPY src ./src
RUN mvn clean package -DskipTests

# Stage 2: Chạy ứng dụng với JRE 21 nhẹ hơn
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app

# Copy file jar đã build từ Stage 1
# Tên file jar mặc định sẽ là artifactId-version.jar
COPY --from=build /app/target/round-2-Backend-0.0.1-SNAPSHOT.jar app.jar

# Render sẽ cấp cổng ngẫu nhiên qua biến PORT, Spring cần nhận biến này
EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]