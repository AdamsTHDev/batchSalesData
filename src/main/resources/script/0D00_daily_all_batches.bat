
call 0D99_parameter_setup.bat
java -cp %CLASS_PATH% com.adms.batch.sales.data.ImportSalesReportByRecords "%INPUT_PATH%"
java -cp %CLASS_PATH% com.adms.batch.sales.data.ImportQcReconfirm "%INPUT_PATH%"
java -cp %CLASS_PATH% com.adms.batch.sales.data.ssis.DailyPerformanceTrackingByLotTele "%INPUT_PATH%"
java -cp %CLASS_PATH% com.adms.batch.sales.data.ssis.DailyPerformanceTrackingByLotOto "%INPUT_PATH%"
dtexec /f "%BATCH_PATH%\dtsx\Import_DailyPerformanceTracking.dtsx"
ROBOCOPY "%SSIS_PATH%\%VAR_MONTH%" "%ARCHIVE_PATH%\%VAR_MONTH%" /E /IS /MOVE
java -cp %CLASS_PATH% com.adms.batch.sales.report.partner.automail.MtiEmailSender
java -cp %CLASS_PATH% com.adms.batch.sales.report.partner.automail.MsigUobEmailSender

pause.
