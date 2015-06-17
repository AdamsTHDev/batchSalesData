
call 0MCOMM99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.partner.ImportMtlPendingPreUw "%MTL_PENDING_UW_PATH%"

pause.