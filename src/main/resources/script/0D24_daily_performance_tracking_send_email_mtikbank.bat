
call 0D99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.report.partner.automail.MtiEmailSender

pause.