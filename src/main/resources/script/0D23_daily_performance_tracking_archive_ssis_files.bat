
call 0D99_parameter_setup.bat

ROBOCOPY "%SSIS_PATH%\%VAR_MONTH%" "%ARCHIVE_PATH%\%VAR_MONTH%" /E /IS /MOVE

pause.