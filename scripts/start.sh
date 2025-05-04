#!/usr/bin/env bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/spring-webapp.jar"

APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date +%c)

# .env 파일 불러오기
if [ -f "$PROJECT_ROOT/.env" ]; then
  echo "✅ Loading environment variables from .env"
  source "$PROJECT_ROOT/.env"
else
  echo "❌ .env 파일이 존재하지 않습니다. 종료합니다." >> $DEPLOY_LOG
  exit 1
fi

# 기존 JAR 파일 덮어쓰기
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# 로그 기록
echo "$TIME_NOW > JAR 파일 복사 완료: $JAR_FILE" >> $DEPLOY_LOG

# 실행
echo "$TIME_NOW > 애플리케이션 실행 시작" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE >> $APP_LOG 2>> $ERROR_LOG &
