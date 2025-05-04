#!/bin/bash

echo "export JWT_SECRET=$JWT_SECRET" > /home/ubuntu/app/.env
echo "export DB_URL=$DB_URL" >> /home/ubuntu/app/.env
echo "export DB_USERNAME=$DB_USERNAME" >> /home/ubuntu/app/.env
echo "export DB_PASSWORD=$DB_PASSWORD" >> /home/ubuntu/app/.env
echo "export AWS_ACCESS_KEY_ID=$AWS_ACCESS_KEY_ID" >> /home/ubuntu/app/.env
echo "export AWS_SECRET_ACCESS_KEY=$AWS_SECRET_ACCESS_KEY" >> /home/ubuntu/app/.env
