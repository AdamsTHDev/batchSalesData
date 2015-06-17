
call 0MCOMM99_parameter_setup.bat

java -cp %CLASS_PATH% com.adms.batch.sales.data.partner.ImportDdopMappingResult "%MTI_DDOP_MAPPING_PATH%"

pause.