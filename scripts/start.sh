#!/bin/bash

PROJECT_ROOT="/home/ubuntu/app"
JAR_FILE="$PROJECT_ROOT/spring-webapp.jar"
APP_LOG="$PROJECT_ROOT/application.log"
ERROR_LOG="$PROJECT_ROOT/error.log"
DEPLOY_LOG="$PROJECT_ROOT/deploy.log"

TIME_NOW=$(date '+%Y-%m-%d %H:%M:%S')

# .env 환경변수 로드
source $PROJECT_ROOT/.env

# 기존 jar 백업 및 복사
echo "$TIME_NOW > JAR 파일 복사" >> $DEPLOY_LOG
cp $PROJECT_ROOT/build/libs/*.jar $JAR_FILE

# 실행
echo "$TIME_NOW > JAR 파일 실행" >> $DEPLOY_LOG
nohup java -jar $JAR_FILE > $APP_LOG 2> $ERROR_LOG &

CURRENT_PID=$(pgrep -f $JAR_FILE)
echo "$TIME_NOW > 실행된 프로세스 PID: $CURRENT_PID" >> $DEPLOY_LOG
