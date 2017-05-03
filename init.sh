#!/bin/bash
echo "Starting API Gateway..."
open -a Safari http://localhost:9090/
./mvnw spring-boot:run
