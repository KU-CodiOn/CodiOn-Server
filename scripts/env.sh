#!/bin/bash

mkdir -p /home/ubuntu/app  # 경로가 없을 경우 자동 생성

cat <<EOF > /home/ubuntu/app/.env
export JWT_SECRET=$JWT_SECRET
export DB_URL=$DB_URL
export DB_USERNAME=$DB_USERNAME
export DB_PASSWORD=$DB_PASSWORD
export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID
export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY
EOF

echo ".env 파일 생성 완료"
