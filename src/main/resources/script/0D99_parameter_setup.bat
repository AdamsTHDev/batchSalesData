
set VAR_MONTH=201506

set ROOT_PATH=T:\Business Solution\Share\AutomateReport
set BATCH_PATH=%ROOT_PATH%\batch
set CLASS_PATH="%BATCH_PATH%\java;%BATCH_PATH%\java\batchSalesData-0.0.1-SNAPSHOT-shaded.jar"

set INPUT_PATH=%ROOT_PATH%\DailyReport\%VAR_MONTH%
set SSIS_PATH=%ROOT_PATH%\DailyReportSSIS
set ARCHIVE_PATH=%ROOT_PATH%\DailyReportArchive

cd %BATCH_PATH%
