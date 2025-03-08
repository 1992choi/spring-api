# Spring-API

## 환경셋팅
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