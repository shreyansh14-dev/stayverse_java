@echo off
echo StayVerse: SUPREME RESET SUCCESS
if exist bin ( rd /s /q bin )
mkdir bin
echo [1/2] Compiling...
javac -cp "lib/*;src" -d bin src/com/stayverse/app/StayVerseApp.java src/com/stayverse/ctrl/*.java src/com/stayverse/model/*.java src/com/stayverse/util/*.java src/com/stayverse/view/*.java src/com/stayverse/view/auth/*.java src/com/stayverse/view/booking/*.java src/com/stayverse/view/components/*.java
if %errorlevel% neq 0 (
    echo [ERROR] Build Failed. 
    pause
    exit /b
)
echo [2/2] Launching...
java -cp "lib/*;bin" com.stayverse.app.StayVerseApp
pause
