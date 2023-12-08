#!/bin/bash

export DB_URL=jdbc:postgresql://localhost:5432/wallet_management
export DB_USER=postgres
export DB_PASSWORD=0000
export CLASSPATH=".:postgresql-42.6.0.jar"

javac src/CrudOperation/*.java src/Interface/*.java src/Model/*.java src/*.java
java -classpath "$CLASSPATH" -DDB_URL="$DB_URL" -DDB_USER="$DB_USER" -DDB_PASSWORD="$DB_PASSWORD" src.Main
