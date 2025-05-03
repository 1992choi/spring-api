# Java 17 이미지 설정
FROM eclipse-temurin:17-jdk AS base

# 작업 디렉토리 설정
WORKDIR /app

# 빌드된 JAR 파일을 컨테이너에 복사
COPY build/libs/spring-api.jar spring-api.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "spring-api.jar"]