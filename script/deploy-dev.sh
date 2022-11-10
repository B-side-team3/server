#!/bin/bash

HOST_NAME=$(hostname -s)
PROCESS_NAME='server-dev.jar'

# PROCESS_ID 확인 (pid 내림차순으로 뽑아 1행의 pid 출력)
echo "> 현재 구동중인 애플리케이션 pid 확인"
PROCESS_ID=$(ps -ef --sort=-pid | grep server-dev.*.jar | grep -v grep | awk 'NR == 1 {print $2}')

echo "현재 구동 중인 애플리케이션 pid: $PROCESS_ID"

#돌고 있는 프로세스 kill
if [[ "$PROCESS_ID" ]]; then
  kill -9 "$PROCESS_ID"
fi

echo "> 새 애플리케이션 배포"

BUILD_PATH="/home/service/server/deploy/${PROCESS_NAME}"

JENKINS_NODE_COOKIE=dontKillMe nohup /usr/bin/java -jar -Dspring.profiles.active=dev $BUILD_PATH >> /dev/null 2>&1 &

# 실행 여부 확인
LOOP_COUNT=0
while true; do
  PROCESS_CHECK_CMD=$(sudo netstat -lntp | grep 8080)
  if [ -n "${PROCESS_CHECK_CMD}" ]; then
    BUILD_SUCCESS=1
    break
  fi

  sleep 2.5
  ((LOOP_COUNT += 1))

  if [ "$LOOP_COUNT" -ge 40 ]; then
    BUILD_SUCCESS=0
    break
  fi
done

# 성공 여부 체크
releaseDay=$(date "+%Y.%m.%d %A %H시%M분%S초")
if [ $BUILD_SUCCESS -eq 1 ]; then
  message="\
  ※ ${HOST_NAME} ${releaseDay} server-dev 배포\n\n\
  빌드 성공"
else
  message="\
  ※ ${HOST_NAME} ${releaseDay} server-dev 배포\n\n\
  빌드 실패"
fi

echo "${message}"

