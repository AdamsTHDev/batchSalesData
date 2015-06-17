
call 0MCOMM99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.partner.ImportMtlBilling "%MTL_BILLING_PATH%"

pause.