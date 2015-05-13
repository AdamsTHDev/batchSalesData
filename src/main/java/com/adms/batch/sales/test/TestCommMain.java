package com.adms.batch.sales.test;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.data.ImportCof;
import com.adms.batch.sales.data.ImportQcReconfirm;
import com.adms.batch.sales.data.ImportSalesReportByRecords;
import com.adms.batch.sales.data.ImportTsrMonthly;
import com.adms.batch.sales.data.partner.ImportDdopMappingResult;
import com.adms.batch.sales.data.partner.ImportFwdDailyProductionReport;
import com.adms.batch.sales.data.partner.ImportMtlBilling;
import com.adms.batch.sales.data.partner.ImportMtlPendingPreUw;


public class TestCommMain extends AbstractImportSalesJob {

	public static void main(String[] args) throws Exception
	{
		String[] s = new String[1];
		
//		s[0] = "D:/Work/Report/TSR Update/Employees_201505_for_batch.xlsx";
//		new ImportTsrMonthly().main(s);

//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MSIGUOB/Reprot UOB - End of Lot 3.Mar 15/2. 02.03.2015 - 02.04.2015";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MTIKBANK/MTI_PA Cash Back  End of Lot 3.March 2015/2.02032015 - 02042015";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MTIKBANK/MTI_POM PA Cash Back End of Lot 3.March 2015/2.02.03.15 - 02.04.15";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MTIKBANK/MTI_SAFETY_CARE_End of Lot  3.March 2015/2.02032015 - 02042015";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MTLKBANK/Report End of lot_MTL DDOP POM PA_03.Mar 15/2. 02032015-02042015";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201504/TELE/MTLKBANK/Report_End of Lot _MTL_HIP_3.Mar 2015/2.02032015 - 02042015";
//		new ImportSalesReportByRecords().main(s);

//		s[0] = "D:/Work/Report/DailyReport/201505/TELE/AUTO_MSIGUOB";
////		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201505/TELE/AUTO_MTIKBANK";
//		new ImportSalesReportByRecords().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201505/TELE/AUTO_MTLKBANK";
//		new ImportSalesReportByRecords().main(s);
		
//		s[0] = "D:/Work/Report/DailyReport/201505/OTO";
//		new ImportSalesReportByRecords().main(s);

		/* MTI DDOP Mapping */
//		s[0] = "D:/Work/Report/DailyReport/MTI_DDOP_Mapping";
//		new ImportDdopMappingResult().main(s);

		/* FWD TVD Production */
//		s[0] = "D:/Work/Report/DailyReport/FWD_Daily_Production/201504";
//		new ImportFwdDailyProductionReport().main(s);
//		s[0] = "D:/Work/Report/DailyReport/FWD_Daily_Production/201505";
//		new ImportFwdDailyProductionReport().main(s);

		/* MTL Billing */
//		s[0] = "D:/Work/Report/DailyReport/MTL_Billing/201504";
//		new ImportMtlBilling().main(s);
//		s[0] = "D:/Work/Report/DailyReport/MTL_Billing/201505";
//		new ImportMtlBilling().main(s); 


		/* MTL Pending UW */
//		s[0] = "D:/Work/Report/DailyReport/MTL_Pending_Pre_UW";
//		new ImportMtlPendingPreUw().main(s);


		/* COF */
//		s[0] = "D:/Work/Report/DailyReport/QA_COF/201503";
//		new ImportCof().main(s);
//		s[0] = "D:/Work/Report/DailyReport/QA_COF/201504";
//		new ImportCof().main(s);
//		s[0] = "D:/Work/Report/DailyReport/QA_COF/201505";
//		new ImportCof().main(s);


		/* QC Reconfirm */
//		s[0] = "D:/Work/Report/DailyReport/201502/TELE/MTLKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/MTLKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201502/TELE/MTIKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/MTIKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201502/TELE/MSIGUOB";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/MSIGUOB";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201502/OTO/FWDTVD";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/OTO/FWDTVD";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201502/OTO/MSIGBL";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/OTO/MSIGBL";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201502/OTO/MTLBL";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/OTO/MTLBL";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/AUTO_MSIGUOB";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/AUTO_MTIKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/TELE/AUTO_MTLKBANK";
//		new ImportQcReconfirm().main(s);
//		s[0] = "D:/Work/Report/DailyReport/201503/OTO/31032015_Report_OTO_ALL/QC Reconfirm";
//		new ImportQcReconfirm().main(s);
	}

}
