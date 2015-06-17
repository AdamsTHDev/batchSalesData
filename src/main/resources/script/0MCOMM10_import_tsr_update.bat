
call 0MCOMM99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.ImportTsrMonthly "%TSR_UPDATE_PATH%"

pause.