@echo off
setlocal enabledelayedexpansion

set "OUTPUT=igoo-all-source.txt"
set "ROOT=%CD%"

if exist "%OUTPUT%" del "%OUTPUT%"

echo Dang gop cac file .java...

for /R "src" %%F in (*.java) do (
    echo.>> "%OUTPUT%"
    echo.>> "%OUTPUT%"
    echo // ============================================================>> "%OUTPUT%"
    set "FILEPATH=%%F"
    set "FILEPATH=!FILEPATH:%ROOT%\=!"
    echo // FILE: !FILEPATH!>> "%OUTPUT%"
    echo // ============================================================>> "%OUTPUT%"
    type "%%F" >> "%OUTPUT%"
)

echo.
echo Da tao: %OUTPUT%
for %%A in ("%OUTPUT%") do echo Kich thuoc: %%~zA bytes
pause