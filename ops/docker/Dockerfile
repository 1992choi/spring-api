FROM eclipse-temurin:17-jdk

# 작업 디렉토리 설정
WORKDIR /app

# 프로젝트 전체 복사 (프로젝트 루트 기준)
COPY ../../ .

# gradlew 실행 권한 부여 및 빌드
RUN chmod +x ./gradlew && ./gradlew build --no-daemon

# 빌드된 JAR 복사
RUN cp build/libs/spring-api.jar spring-api.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "spring-api.jar"]
