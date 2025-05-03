# Spring-API

## 로컬 구성 (개별구성)
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

## 로컬 구성 (docker compose 사용)
### build
- bootJar 실행
### docker-compose
- cd {docker-compose 경로}
- docker-compose up --build

<hr>

## aws 구성
### aws 설정
- 서버접속
  - 로컬의 pem 위치로 이동
  - sudo ssh -i "XXX.pem" ubuntu@ec2-XXX-XXX-XXX-XXX.ap-northeast-2.compute.amazonaws.com
- Docker 설치
  - sudo apt install -y docker.io
- Docker Compose 설치
  - sudo apt install -y docker-compose
- Docker 실행 및 부팅 시 자동 시작 설정
  - sudo systemctl enable docker
  - sudo systemctl start docker
- 현재 사용자에게 Docker 권한 부여
  - sudo usermod -aG docker $USER
- 설치 확인
  - docker -v
  - docker-compose -v
- 도커 정리
  - docker system prune -a

### SSH 연결을 위한 키 준비
- github 설정
  - repo > Settings > Secrets and variables > Actions 로 이동
  - Secrets 탭에서 'New repository secret' 버튼 클릭하여 아래 내용 하나씩 등록
    - EC2_HOST
      - EC2 퍼블릭 IP
      - Ex. 43.201.xxx.xxx
    - EC2_USER
      - 보통은 ubuntu 
    - EC2_KEY
      - PEM 파일의 내용 전체