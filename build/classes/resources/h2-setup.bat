@echo off
setlocal

set H2_JAR=%~dp0..\..\webapp\WEB-INF\lib\h2-2.3.232.jar
set DB_PATH=%~dp0restauranteZe
set SCHEMA_SQL=%~dp0schema.sql
set DATA_SQL=%~dp0data.sql
set DB_FILE=%DB_PATH%.mv.db


for /f "tokens=2" %%i in ('netstat -aon ^| findstr :9092') do (
    set PID=%%i
)
if "%PID%"=="" (
    echo Starting H2 server...

    start "" java -cp "%H2_JAR%" org.h2.tools.Server

    timeout /t 5
) else (
    echo H2 server is already running.
)


if exist "%DB_FILE%" (
    echo removendo...
    del "%DB_FILE%"
)




echo exec db schema...
java -cp "%H2_JAR%" org.h2.tools.RunScript -url jdbc:h2:file:%DB_PATH% -user sa -password "" -script "%SCHEMA_SQL%"


echo exec data...
java -cp "%H2_JAR%" org.h2.tools.RunScript -url jdbc:h2:file:%DB_PATH% -user sa -password "" -script "%DATA_SQL%"

echo Done.
