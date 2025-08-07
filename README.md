# CCTV 프로젝트

이 프로젝트는 백엔드와 프론트엔드가 분리된 구조로 구성되어 있습니다.

## 프로젝트 구조

```
cctv/
├── backend/          # Spring Boot 백엔드 애플리케이션
│   ├── .idea/        # IntelliJ IDEA 설정
│   ├── .vscode/      # VS Code 설정
│   ├── build/        # Gradle 빌드 결과물
│   ├── .gradle/      # Gradle 캐시
│   ├── gradle/       # Gradle Wrapper
│   ├── src/          # 소스 코드
│   ├── build.gradle  # Gradle 빌드 설정
│   ├── settings.gradle
│   ├── gradlew       # Gradle Wrapper 스크립트
│   ├── gradlew.bat
│   └── HELP.md
├── frontend/         # 프론트엔드 애플리케이션 (미구현)
├── .gitattributes
├── .gitignore
└── README.md
```

## 백엔드 실행

```bash
cd backend
./gradlew bootRun
```

## 프론트엔드

프론트엔드 폴더가 생성되었으며, 필요에 따라 React, Vue, Angular 등의 프레임워크를 선택하여 개발할 수 있습니다.

## 개발 환경

- Backend: Spring Boot
- Frontend: (선택 예정)
