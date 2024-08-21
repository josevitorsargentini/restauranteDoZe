@echo off
setlocal

set H2_JAR=%~dp0..\..\webapp\WEB-INF\lib\h2-2.3.232.jar
set DB_PATH=%~dp0restauranteZe
set SCHEMA_SQL=%~dp0schema.sql
set DATA_SQL=%~dp0data.sql
set DB_FILE=%DB_PATH%.mv.db

REM Verifica se o servidor H2 está rodando
for /f "tokens=2" %%i in ('netstat -aon ^| findstr :9092') do (
    set PID=%%i
)
if "%PID%"=="" (
    echo Starting H2 server...

    REM Inicia o servidor H2
    start "" java -cp "%H2_JAR%" org.h2.tools.Server

    REM Aguarda o servidor H2 iniciar
    timeout /t 30
) else (
    echo H2 server is already running.
)

REM Verifica se o banco de dados existe
if exist "%DB_FILE%" (
    echo Deleting existing database...
    del "%DB_FILE%"
)

REM Verifica se o arquivo schema.sql existe
if not exist "%SCHEMA_SQL%" (
    echo Schema file not found: %SCHEMA_SQL%
    exit /b 1
)

REM Cria o esquema do banco de dados
echo Creating database schema...
java -cp "%H2_JAR%" org.h2.tools.RunScript -url jdbc:h2:file:%DB_PATH% -user sa -password "" -script "%SCHEMA_SQL%"
echo jdbc:h2:file:%DB_PATH%

REM Verifica se o arquivo data.sql existe
if not exist "%DATA_SQL%" (
    echo Data file not found: %DATA_SQL%
    exit /b 1
)

REM Carrega os dados
echo Loading data...
java -cp "%H2_JAR%" org.h2.tools.RunScript -url jdbc:h2:file:%DB_PATH% -user sa -password "" -script "%DATA_SQL%"

echo Done.
