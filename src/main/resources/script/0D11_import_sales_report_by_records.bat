
call 0D99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.ImportSalesReportByRecords "%INPUT_PATH%"

pause.