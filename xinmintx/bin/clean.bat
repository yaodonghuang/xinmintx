@echo off
echo.
echo [??] ???????
echo.

%~d0
cd %~dp0

cd ..
call mvn clean

pause