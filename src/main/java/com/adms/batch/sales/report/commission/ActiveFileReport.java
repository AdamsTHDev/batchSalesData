package com.adms.batch.sales.report.commission;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections4.CollectionUtils;

import com.adms.batch.sales.data.AbstractImportSalesJob;
import com.adms.batch.sales.domain.VSalesComm;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;
import com.adms.imex.excelformat.SimpleMapDataHolder;

public class ActiveFileReport extends AbstractImportSalesJob {

	private DataHolder toRecordDataHolder(VSalesComm salesComm)
	{
		if (salesComm != null)
		{
			DataHolder recordDataHolder = new SimpleMapDataHolder();
			DataHolder dataHolder = new SimpleMapDataHolder();

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getCampaignName());
			recordDataHolder.put("campaignName", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getCampaignCode());
			recordDataHolder.put("campaignCode", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getListlotName());
			recordDataHolder.put("listLotName", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getApproveDate());
			recordDataHolder.put("approveDate", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getxReference());
			recordDataHolder.put("xReference", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getItemNo());
			recordDataHolder.put("itemNo", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getCustomerFullName());
			recordDataHolder.put("customerFullName", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getProduct());
			recordDataHolder.put("product", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getPremium());
			recordDataHolder.put("premium", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getAnnualPremium());
			recordDataHolder.put("annualPremium", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getProtectAmount());
			recordDataHolder.put("protectAmount", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getPaymentChannel());
			recordDataHolder.put("paymentChannel", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getPaymentFrequency());
			recordDataHolder.put("paymentFrequency", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getSaleDate());
			recordDataHolder.put("saleDate", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getTsrCode());
			recordDataHolder.put("tsrCode", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getTsrFullName());
			recordDataHolder.put("tsrFullName", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getSupCode());
			recordDataHolder.put("supCode", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getSupFullName());
			recordDataHolder.put("supFullName", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getQaStatus());
			recordDataHolder.put("qaStatus", dataHolder);

			dataHolder = new SimpleMapDataHolder();
			dataHolder.setValue(salesComm.getQaReasonStatus());
			recordDataHolder.put("qaReasonStatus", dataHolder);

			return recordDataHolder;
		}

		return null;
	}

	private DataHolder toFileDataHolder(List<VSalesComm> salesCommList, List<Object> salesCancelList)
	{
		DataHolder fileDataHolder = new SimpleMapDataHolder();

		DataHolder activeSheetDataHolder = new SimpleMapDataHolder();
		if (CollectionUtils.isNotEmpty(salesCommList))
		{
			List<DataHolder> activeList = new ArrayList<DataHolder>();
			for (VSalesComm salesComm : salesCommList)
			{
				activeList.add(toRecordDataHolder(salesComm));
			}
			activeSheetDataHolder.putDataList("activeList", activeList);
		}
		fileDataHolder.put("Active", activeSheetDataHolder);

		/*DataHolder cancelSheetDataHolder = new SimpleMapDataHolder();
		if (CollectionUtils.isNotEmpty(salesCancelList))
		{
			List<DataHolder> cancelList = new ArrayList<DataHolder>();
			for (VSalesComm salesCancel : salesCancelList)
			{
				cancelList.add(toRecordDataHolder(salesCancel));
			}
			cancelSheetDataHolder.putDataList("cancelList", cancelList);
		}
		fileDataHolder.put("cancelList", cancelSheetDataHolder);*/

		return fileDataHolder;
	}

	private void genActiveFile(String fileFormat, String outputFileName, List<VSalesComm> salesCommList, List<Object> cancelList)
			throws Exception
	{
		if (CollectionUtils.isNotEmpty(salesCommList) || CollectionUtils.isNotEmpty(cancelList))
		{
			DataHolder fileDataHolder = toFileDataHolder(salesCommList, cancelList);

			OutputStream output = new FileOutputStream(outputFileName);
			new ExcelFormat(URLClassLoader.getSystemResourceAsStream(fileFormat)).writeExcel(output, fileDataHolder);
			output.close();
		}
	}

	private List<VSalesComm> getSaleCommisstionList(String campaignName) throws Exception
	{
		return getVSalesCommService().findSaleCommByCampaignName(campaignName);
	}

	public static void main(String[] args) throws Exception
	{
		ActiveFileReport batch = new ActiveFileReport();
		
		String outputPath = "C:/Users/kampon.pan/Desktop/testComm/activefile/";

		List<VSalesComm> mtlKbHip = batch.getSaleCommisstionList("MTL HIP");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "01_MTLKBHIP.xlsx", mtlKbHip, null);

		List<VSalesComm> mtlKbHrc = batch.getSaleCommisstionList("MTL DDOP HRC");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "02_MTLKBHRC.xlsx", mtlKbHrc, null);

		List<VSalesComm> mtlKbPom = batch.getSaleCommisstionList("POM DDOP");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "03_MTLKBPOM.xlsx", mtlKbPom, null);

		List<VSalesComm> mtlBlHip = batch.getSaleCommisstionList("HIP List Purchase");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "04_MTLBLHIP.xlsx", mtlBlHip, null);

		List<VSalesComm> mtlBlPom = batch.getSaleCommisstionList("POM – Broker Leads PA");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "05_MTLBLPOM.xlsx", mtlBlPom, null);

		List<VSalesComm> msigBlHib = batch.getSaleCommisstionList("MSIG HIB Broker");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "06_MSIGBLHIB.xlsx", msigBlHib, null);

		List<VSalesComm> msigBlPom = batch.getSaleCommisstionList("MSIG POM PA");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "07_MSIGBLPOM.xlsx", msigBlPom, null);

		List<VSalesComm> msigUobHib = batch.getSaleCommisstionList("MSIG PA-HIB UOB Credit Card");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "08_MSIGUOBHIB.xlsx", msigUobHib, null);

		List<VSalesComm> mtiKbSc = batch.getSaleCommisstionList("Safety care DDOP (Selection)");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "09_MTIKBSC.xlsx", mtiKbSc, null);

		List<VSalesComm> mtiKbPa = batch.getSaleCommisstionList("MTI DDOP-PA Cash Back");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "10_MTIKBPA.xlsx", mtiKbPa, null);

		List<VSalesComm> mtiKbPom = batch.getSaleCommisstionList("MTI DDOP-POM PA Cash Back");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "11_MTIKBPOM.xlsx", mtiKbPom, null);

		List<VSalesComm> fwdTvdEnd = batch.getSaleCommisstionList("FWD-Endowment 15/7");
		batch.genActiveFile("FileFormat_ActiveFile.xml", outputPath + "12_FWDTVDEND.xlsx", fwdTvdEnd, null);

	}

}
