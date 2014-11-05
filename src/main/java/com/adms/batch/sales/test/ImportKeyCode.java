package com.adms.batch.sales.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.domain.ListLot;
import com.adms.imex.excelformat.DataHolder;
import com.adms.imex.excelformat.ExcelFormat;

public class ImportKeyCode extends AbstractImportSalesJob {

	public static final String KEY_CODE_LIST = "keyCodeList";

	public static final String CAMPAIGN_CODE = "campaignCode";
	public static final String CAMPAIGN_NAME = "campaignName";
	public static final String CALL_CENTER = "callCenter";
	public static final String LIST_SOURCE = "listSource";
	public static final String INSURER = "insurer";
	public static final String PRODUCT_CODE = "productCode";
	public static final String PRODUCT_NAME = "productName";
	public static final String LAUNCH_DATE = "launchDate";
	public static final String END_DATE = "endDate";

	public static final String LIST_LOT_CODE = "keyCode";
	public static final String LIST_LOT_NAME = "listLotName";
	public static final String SCRIPT_ID = "scriptId";
	public static final String LOT_EFFECTIVE_DATE = "lotEffectiveDate";

	private Campaign extractCampaignRecord(DataHolder keyCodeDataHolder, Campaign campaign)
			throws Exception
	{
		log("extractCampaignRecord " + keyCodeDataHolder.printValues());

		String campaignName = keyCodeDataHolder.get(CAMPAIGN_NAME).getStringValue();
		String callCenter = keyCodeDataHolder.get(CALL_CENTER).getStringValue();
		String listSource = keyCodeDataHolder.get(LIST_SOURCE).getStringValue();
		String insurer = keyCodeDataHolder.get(INSURER).getStringValue();
		String productCode = keyCodeDataHolder.get(PRODUCT_CODE).getStringValue();
		String productName = keyCodeDataHolder.get(PRODUCT_NAME).getStringValue();
		// Date launchDate = (Date)
		// keyCodeDataHolder.get(LAUNCH_DATE).getValue();
		// String endDate = keyCodeDataHolder.get(END_DATE).getStringValue();

		campaign.setCampaignName(campaignName);
		campaign.setCallCenter(getCallCenterService().findCallCenterByCallCenterAbbr(callCenter));
		campaign.setInsurer(getInsurerService().findInsurerByInsurerAbbr(insurer));
		campaign.setListSource(getListSourceService().findListSourceByListSourceAbbr(listSource));
		campaign.setProductCode(productCode);
		campaign.setProductName(productName);
		campaign.setIsActive("Y");

		return campaign;
	}

	private ListLot extractListLotRecord(DataHolder keyCodeDataHolder, Campaign campaign, ListLot listLot)
			throws Exception
	{
		log("extractKeyCodeRecord " + keyCodeDataHolder.printValues());

		String listLotName = keyCodeDataHolder.get(LIST_LOT_NAME).getStringValue();
		String scriptId = keyCodeDataHolder.get(SCRIPT_ID).getStringValue();
		Date lotEffectiveDate = (Date) keyCodeDataHolder.get(LOT_EFFECTIVE_DATE).getValue();

		listLot.setListLotName(listLotName);
		listLot.setCampaign(campaign);
		listLot.setLotEffectiveDate(lotEffectiveDate);
		listLot.setScriptId(scriptId);

		return listLot;
	}

	private void importKeyCode(List<DataHolder> keyCodeDataHolderList)
			throws Exception
	{
		log("importKeyCode");
		Campaign campaign = null;
		for (DataHolder keyCodeDataHolder : keyCodeDataHolderList)
		{
			String campaignCode = keyCodeDataHolder.get(CAMPAIGN_CODE).getStringValue();
			if (StringUtils.isNotBlank(campaignCode))
			{
				campaign = getCampaignService().findCampaignByCampaignCode(campaignCode);

				boolean newCampaign = false;
				if (campaign == null)
				{
					campaign = new Campaign();
					campaign.setCampaignCode(campaignCode);
					newCampaign = true;
				}

				extractCampaignRecord(keyCodeDataHolder, campaign);

				if (newCampaign)
				{
					getCampaignService().addCampaign(campaign, BATCH_ID);
				}
				else
				{
					getCampaignService().updateCampaign(campaign, BATCH_ID);
				}
			}

			String keyCode = keyCodeDataHolder.get(LIST_LOT_CODE).getStringValue();
			ListLot listLot = getListLotService().findListLotByListLotCode(keyCode);

			boolean newListLot = false;
			if (listLot == null)
			{
				listLot = new ListLot();
				listLot.setListLotCode(keyCode);
				newListLot = true;
			}

			extractListLotRecord(keyCodeDataHolder, campaign, listLot);

			if (newListLot)
			{
				getListLotService().addListLot(listLot, BATCH_ID);
			}
			else
			{
				getListLotService().updateListLot(listLot, BATCH_ID);
			}
		}
	}

	private void importFile(File fileFormat, File keyCodeRecordFile, String sheetName)
	{
		log("importFile");
		InputStream input = null;
		try
		{
			ExcelFormat excelFormat = new ExcelFormat(fileFormat);

			input = new FileInputStream(keyCodeRecordFile);
			DataHolder fileDataHolder = excelFormat.readExcel(input);

			List<DataHolder> keyCodeDataHolderList = fileDataHolder.get(sheetName).getDataList(KEY_CODE_LIST);

			importKeyCode(keyCodeDataHolderList);
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				input.close();
			}
			catch (Exception e)
			{
			}
		}
	}

	public static void main(String[] args)
			throws Exception
	{
		String fileFormat = "D:/Eclipse/Workspace/ADAMS/batch-sales-data/src/main/resources/FileFormat_KeyCode.xml";
		String fileInput = "D:/Work/ADAMS/Report/Key Code Campaign Code Setup/Campaign Keycode Management Thailand2014 - Copy.xls";
		String sheetName = "2014";

		ImportKeyCode ikc = new ImportKeyCode();
		ikc.setEnableLog(true);
		ikc.importFile(new File(fileFormat), new File(fileInput), sheetName);
	}

}
