@echo off
echo Compiling project...
javac -d out src/model/*.java src/service/*.java src/interfaces/*.java src/exceptions/*.java src/utils/*.java src/Main.java

if %ERRORLEVEL% GEQ 1 (
    echo Compilation failed!
    pause
    exit /b %ERRORLEVEL%
)

echo Running project...
java -cp out Main
pause
