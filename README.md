# Spring-API
## 환경 구성
### 로컬
- mysql
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

- Redis
  - 컨테이너 생성 및 실행
    - docker run --name my-redis -d -p 6379:6379 redis
  - 컨테이너 접속
    - docker exec -it my-redis redis-cli
  - 키 확인
    - keys *
  - 모든 데이터 삭제
    - FLUSHALL

- Kafka
  - TBD

- 서버기동 (with Docker Compose)
  - build
    - bootJar 실행
  - Docker Compose
    - cd {docker-compose 경로}
    - docker-compose up -d --build

### aws
- aws 구성
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



<hr>



### Endpoints
- Spring boot
  - http://localhost:8080
- Kafka UI
  - http://localhost:8085
- Kibana
  - http://localhost:5601
    - Dev Tools 사용 방법
      - menu > Management > Dev Tools
      - access 로그 확인 쿼리 예시
        - ```
          -- 조회 건수 제한
          GET access-log-2025-05-12/_search?size=50
        
          -- 필드를 이용한 키워드 검색(= request 필드의 값 대상으로 조회. [Ex. 요청이 /member로 들어온 것 조회])
          GET access-log-2025-05-12/_search
          {
            "query": {
              "match": {
                "request": "/member"
              }
            }
          }
          ```
    - 시각화 데이터 확인      
      - index pattern 생성
        - index patterns > create index pattern
          - index pattern name > access-log* 입력 후 생성
          - time field > logged_at 선택 (시계열로 보여지기 위해 순서가 보장된 필드로 선택)
      - menu > Discover 에서 시각화 데이터 확인 가능

<hr>



### TODO
- 테스트 코드
  - 테스트 코드 작성 및 활성화
  - RestDocs 활성화
- GitHub Actions
  - 프리티어 스펙 이슈로 GitHub Actions 비활성화
    - deploy.yml 일부 주석 상태
    - 낮은 스펙으로 배포했을 때는 성공
- 환경 분리
  - application.yml / docker-compose.yml 분리