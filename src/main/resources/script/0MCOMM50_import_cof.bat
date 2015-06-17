
call 0MCOMM99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.ImportCof "%QA_COF_PATH%"

pause.