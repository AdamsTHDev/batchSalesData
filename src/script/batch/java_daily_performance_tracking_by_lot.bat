
set VAR_MONTH=201412
set VAR_CP=TELE\MTLKBANK
set VAR_CPCODE=021DP1714L04

set ROOT_PATH=D:\Work\Report
set BATCH_PATH=%ROOT_PATH%\DailyReport\batch
set CLASS_PATH="%BATCH_PATH%\batchSalesData-0.0.1-SNAPSHOT-shaded.jar"

set INPUT_PATH=%ROOT_PATH%\DailyReport\%VAR_MONTH%\%VAR_CP%
set SSIS_PATH=%ROOT_PATH%\DailyReportSSIS
set ARCHIVE_PATH=%ROOT_PATH%\DailyReportArchive
set OUTPUT_PATH=%ROOT_PATH%\DailyReportFinal\%VAR_MONTH%\%VAR_CP%

cd %BATCH_PATH%

java -cp %CLASS_PATH% com.adms.batch.sales.test.DailyPerformanceTrackingByLotTele "%INPUT_PATH%"
::java -cp %CLASS_PATH% com.adms.batch.sales.test.DailyPerformanceTrackingByLotOto "%INPUT_PATH%"

dtexec /f "C:\Users\kampon.pan\documents\visual studio 2010\Projects\Data Management\Data Management\Import_DailyPerformanceTracking.dtsx"

ROBOCOPY "%SSIS_PATH%\%VAR_MONTH%" "%ARCHIVE_PATH%\%VAR_MONTH%" /E /IS /MOVE

java -cp %CLASS_PATH% com.adms.batch.sales.report.partner.DailyPerformanceTrackingReport "%VAR_CPCODE%" "%OUTPUT_PATH%"

pause.