#!/bin/bash

echo "✅ Generating .env file with environment variables..."

# .env 파일을 새로 생성하면서 덮어씀
cat <<EOF > /home/ubuntu/app/.env
export JWT_SECRET=${JWT_SECRET}
export DB_URL=${DB_URL}
export DB_USERNAME=${DB_USERNAME}
export DB_PASSWORD=${DB_PASSWORD}
export AWS_ACCESS_KEY_ID=${AWS_ACCESS_KEY_ID}
export AWS_SECRET_ACCESS_KEY=${AWS_SECRET_ACCESS_KEY}
EOF

echo "✅ .env file created at /home/ubuntu/app/.env"
