# Spring-API

## 로컬 구성
### mysql
- 이미지 다운로드
  - docker pull mysql
- 컨테이너 생성 및 실행
  - docker run --name mysql -e MYSQL_ROOT_PASSWORD=1234 -d -p 3306:3306 mysql
- 컨테이너 관리
  - docker stop mysql
  - docker start mysql
  - docker restart mysql
- 컨테이너 접속
  - docker exec -it mysql bash
- mysql root 계정으로 접속
  - mysql -u root -p  
- 데이터베이스 생성
  - CREATE DATABASE api;
  - CREATE DATABASE apisub;

### Redis
- 컨테이너 생성 및 실행
  - docker run --name my-redis -d -p 6379:6379 redis
- 컨테이너 접속
  - docker exec -it my-redis redis-cli
- 키 확인
  - keys *
- 모든 데이터 삭제
  - FLUSHALL

<hr>

## aws 구성
### aws 설정
- 도커 설치

### docker
- docker-compose
  - cd {docker-compose 경로}
  - docker-compose up --build