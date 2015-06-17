
set ROOT_PATH=T:\Business Solution\Share\AutomateReport
set BATCH_PATH=%ROOT_PATH%\batch
set CLASS_PATH="%BATCH_PATH%\java;%BATCH_PATH%\java\batchSalesData-0.0.1-SNAPSHOT-shaded.jar"

set COMM_DATA_PATH=%ROOT_PATH%\CommData
set TSR_UPDATE_PATH=%COMM_DATA_PATH%\TSR_Update\Employees_for_batch.xlsx
set MTI_DDOP_MAPPING_PATH=%COMM_DATA_PATH%\MTI_DDOP_Mapping
set MTL_BILLING_PATH=%COMM_DATA_PATH%\MTL_Billing
set MTL_PENDING_UW_PATH=%COMM_DATA_PATH%\MTL_Pending_Pre_UW
set FWD_PRODUCTION_PATH=%COMM_DATA_PATH%\FWD_Daily_Production
set QA_COF_PATH=%COMM_DATA_PATH%\QA_COF

cd %BATCH_PATH%
