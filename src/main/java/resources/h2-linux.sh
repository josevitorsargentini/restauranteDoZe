#!/bin/bash

H2_JAR="$(dirname "$0")/../../webapp/WEB-INF/lib/h2-2.3.232.jar"
DB_PATH="$(dirname "$0")/restauranteZe"
SCHEMA_SQL="$(dirname "$0")/schema.sql"
DATA_SQL="$(dirname "$0")/data.sql"
DB_FILE="${DB_PATH}.mv.db"


PID=$(lsof -ti :9092)

if [ -z "$PID" ]; then
    echo "Starting H2 server..."
    java -cp "$H2_JAR" org.h2.tools.Server &
    sleep 5
else
    echo "H2 server is already running."
fi

if [ -f "$DB_FILE" ]; then
    echo "REMOVENDO..."
    rm -f "$DB_FILE"
fi


echo "SCHEMA"
java -cp "$H2_JAR" org.h2.tools.RunScript -url jdbc:h2:file:"$DB_PATH" -user sa -password "" -script "$SCHEMA_SQL"


echo "SCRIPT"
java -cp "$H2_JAR" org.h2.tools.RunScript -url jdbc:h2:file:"$DB_PATH" -user sa -password "" -script "$DATA_SQL"

echo "Done."
