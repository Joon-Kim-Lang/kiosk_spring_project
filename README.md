# Study cafe kiosk

![issue badge](https://img.shields.io/github/issues/Joon-Kim-Lang/kiosk_spring_project)
![star badge](https://img.shields.io/github/stars/Joon-Kim-Lang/kiosk_spring_project)
![spring badge](https://img.shields.io/badge/spring--boot-2.4.1-green)

> ## Study cafe kiosk function Backend system implementation using java spring
>
> <br>

## Team

---

- **kks** : Developer
  - `Seat system Part`
  - `git Manage Part`
- **khj** : Project Manager
  - `DB management `
  - `Daemon Thread implementation`

## Environment

---

- **java 14**
- **spring-boot 2.4.1**
- **jpa 2.4.1**
- **junit 5.7**

## Description

---

## flow chart

<img src="https://user-images.githubusercontent.com/57718605/107868014-b4724d80-6ec3-11eb-9aae-ad5d0aee19a3.png" width="800" height="800">
<br>
<br>

## client

---

### 1. 시작 page

- 결제하기 : 회원 등록
- 문열기 : 등록된 회원 확인
- 좌석선택 : 선택 변경 가능 및 좌석 조회
- 나가기 : 정액권 시간 갱신

<img src="https://user-images.githubusercontent.com/57718605/107867284-02378780-6ebd-11eb-9c02-86244d182a83.png" width="800" height="200">

### 2. 결제하기 Page -> 상품 선택 및 좌석 선택 page

   <img src="https://user-images.githubusercontent.com/57718605/107867512-e46b2200-6ebe-11eb-8752-c8afc71145b5.PNG" width="250" height="300">

   <img src="https://user-images.githubusercontent.com/57718605/107867620-d7026780-6ebf-11eb-98f8-2c7599b4270c.PNG" width="550" height="300">

### 3. 상품 선택 전 후

<img src="https://user-images.githubusercontent.com/57718605/107867809-517fb700-6ec1-11eb-92e4-db57bcfa64a3.png" width="600" height="300">

## Server

---

- Seat db : 좌석현황 데이터베이스
- payment db : 결제 정보 데이터베이스
- 정액권 db : 정액권 관리 데이터 베이스

![image](https://user-images.githubusercontent.com/57718605/107868094-70337d00-6ec4-11eb-88ad-7d8368ba3c39.png)

## dependencies

---

- spring-boot-starter-thymeleaf
- spring-boot-starter-web
- spring-boot-devtools
- spring-boot-starter-data-jpa
- h2database
- spring-boot-starter-test
