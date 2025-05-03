# 1단계: Gradle 빌드를 위한 빌드 이미지
FROM eclipse-temurin:17-jdk AS build

# 작업 디렉토리 설정
WORKDIR /app

# Gradle Wrapper와 관련 파일을 복사
COPY gradle/ gradle/
COPY gradlew gradlew
COPY build.gradle settings.gradle ./

# Gradle 의존성 다운로드
RUN ./gradlew build --no-daemon

# 애플리케이션 소스 복사
COPY . .

# 애플리케이션 빌드
RUN ./gradlew build -x test --no-daemon

# 2단계: 애플리케이션 실행을 위한 런타임 이미지
FROM eclipse-temurin:17-jdk AS runtime

WORKDIR /app

# 빌드된 JAR 파일을 복사
COPY --from=build /app/build/libs/spring-api.jar spring-api.jar

# 애플리케이션 실행
ENTRYPOINT ["java", "-jar", "spring-api.jar"]
