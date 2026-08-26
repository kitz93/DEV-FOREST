# DEV-FOREST

동아리/모임 관리 서비스. 게시판, 예약(모임), 스터디 참석, 퀴즈/랭킹 등을 제공한다.

## 폴더 구조

- `dev/` — React 프론트엔드
- `forest/` — Spring Boot 백엔드 (Spring Boot 3.4.2 / Java 21 / MyBatis / Oracle / Spring Security + JWT)

## 실행 방법

### 백엔드 (`forest/`)

1. `.env.example`을 참고해 환경변수를 설정한다.

   | 변수 | 설명 |
   |---|---|
   | `DB_URL` | Oracle JDBC URL (예: `jdbc:oracle:thin:@host:1521:xe`) |
   | `DB_USERNAME` | DB 계정 |
   | `DB_PASSWORD` | DB 비밀번호 |
   | `JWT_SECRET` | JWT 서명 키 |

2. 빌드 및 실행:

   ```bash
   cd forest
   ./gradlew bootRun
   ```

### 프론트엔드 (`dev/`)

```bash
cd dev
npm install
npm run dev
```

## 담당 모듈

| 모듈 | 담당 |
|---|---|
| `board`, `reply`, `reservation`, `studying`, `common`, `exception`, `FileService` | 본인 |
| `member`, `auth`, `token`, `quiz`, `ranking`, `theory`, `progress`, `wrong` | 팀원 |
