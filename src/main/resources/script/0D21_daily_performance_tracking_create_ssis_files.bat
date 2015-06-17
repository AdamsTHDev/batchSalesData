
call 0D99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.ssis.DailyPerformanceTrackingByLotTele "%INPUT_PATH%"
java -cp %CLASS_PATH% com.adms.batch.sales.data.ssis.DailyPerformanceTrackingByLotOto "%INPUT_PATH%"

pause.