package com.adms.batch.sales.test;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.ListOrderedMap;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.SheetConditionalFormatting;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;

import com.adms.batch.sales.domain.Campaign;
import com.adms.batch.sales.domain.IncentiveCriteria;
import com.adms.batch.sales.domain.ReconfirmStatus;
import com.adms.batch.sales.domain.Sales;
import com.adms.batch.sales.domain.Tsr;

public class ReportIncentiveSydney extends AbstractImportSalesJob {

	private int totalMonth;

	private void sortData(Map<String, ProductionByTsr> allTsrMap)
	{
		List<ProductionByTsr> productionByTsrList = new ArrayList<ReportIncentiveSydney.ProductionByTsr>();
		for (String tsrCode : allTsrMap.keySet())
		{
			productionByTsrList.add(allTsrMap.get(tsrCode));
		}
		allTsrMap.clear();

		Collections.sort(productionByTsrList, new Comparator<ProductionByTsr>()
		{
			public int compare(ProductionByTsr o1, ProductionByTsr o2)
			{
				return -(o1.getTotal().tarp.compareTo(o2.getTotal().getTarp()));
			}
		});

		for (ProductionByTsr productionByTsr : productionByTsrList)
		{
			allTsrMap.put(productionByTsr.getTsr().getTsrCode(), productionByTsr);
		}
	}

	private Map<String, Map<String, Map<String, ProductionByTsr>>> aggregateData()
			throws Exception
	{
		Map<String, Map<String, Map<String, ProductionByTsr>>> map = new ListOrderedMap<String, Map<String, Map<String, ProductionByTsr>>>();

		for (int monthIdx = 0; monthIdx < this.totalMonth; monthIdx++)
		{
			Date date = DateUtils.addMonths(getStartDate(), monthIdx);
			SimpleDateFormat df = new SimpleDateFormat("MMM-yy", Locale.US);

			df = new SimpleDateFormat("yyyyMM", Locale.US);
			String monthQuery = df.format(date);

			List<Sales> salesList = getSalesService().findSalesRecordBySaleMonth(monthQuery);

			System.out.println(salesList.size());
			for (Sales sales : salesList)
			{
				log("start");
				log("Sales     --> " + sales);

				Campaign campaign = sales.getListLot().getCampaign();
				ReconfirmStatus qaStatus = sales.getQaStatus();
				String qaReason = sales.getQaReason();

				log("Campaign  --> " + campaign);
				log("QA Status --> " + qaStatus);
				log("QA Reason --> " + qaReason);

				log("start ic");
				List<IncentiveCriteria> incentiveCriteriaList = getIncentiveCriteriaService().findBySydneyCriteria(campaign.getCampaignCode(), qaStatus.getReconfirmStatus(), qaReason);
				log("finish ic");
				if (CollectionUtils.isNotEmpty(incentiveCriteriaList))
				{
					IncentiveCriteria incentiveCriteria = incentiveCriteriaList.get(0);

					if ("Y".equalsIgnoreCase(incentiveCriteria.getIsCount()))
					{
						log("start process sale");
						Map<String, Map<String, ProductionByTsr>> campaignMap = map.get(campaign.getCampaignName());

						if (campaignMap == null)
						{
							campaignMap = new ListOrderedMap<String, Map<String, ProductionByTsr>>();
							map.put(campaign.getCampaignName(), campaignMap);
						}

						Map<String, ProductionByTsr> allTsrMap = campaignMap.get("TSR");
						if (allTsrMap == null)
						{
							allTsrMap = new ListOrderedMap<String, ReportIncentiveSydney.ProductionByTsr>();
							campaignMap.put("TSR", allTsrMap);
						}

						Tsr tsr = sales.getTsr();
						ProductionByTsr tsrProd = allTsrMap.get(tsr.getTsrCode());
						if (tsrProd == null)
						{
							tsrProd = new ProductionByTsr(this.totalMonth);
							tsrProd.setTsr(tsr);
							allTsrMap.put(tsr.getTsrCode(), tsrProd);
						}

						ProductionMonth tsrPodMonth = tsrProd.getProdMonthList().get(monthIdx);
						tsrPodMonth.setSales(tsrPodMonth.getSales() + 1);
						tsrProd.getTotal().setSales(tsrProd.getTotal().getSales() + 1);
						tsrPodMonth.setTarp(tsrPodMonth.getTarp().add(sales.getAnnualFyp()));
						tsrProd.getTotal().setTarp(tsrProd.getTotal().getTarp().add(sales.getAnnualFyp()));

						Map<String, ProductionByTsr> allSupMap = campaignMap.get("SUP");
						if (allSupMap == null)
						{
							allSupMap = new ListOrderedMap<String, ReportIncentiveSydney.ProductionByTsr>();
							campaignMap.put("SUP", allSupMap);
						}

						Tsr sup = sales.getSupervisor();
						ProductionByTsr supProd = allSupMap.get(sup.getTsrCode());
						if (supProd == null)
						{
							supProd = new ProductionByTsr(this.totalMonth);
							supProd.setTsr(sup);
							allSupMap.put(sup.getTsrCode(), supProd);
						}

						ProductionMonth supProdMonth = supProd.getProdMonthList().get(monthIdx);
						supProdMonth.setSales(supProdMonth.getSales() + 1);
						supProd.getTotal().setSales(supProd.getTotal().getSales() + 1);
						supProdMonth.setTarp(supProdMonth.getTarp().add(sales.getAnnualFyp()));
						supProd.getTotal().setTarp(supProd.getTotal().getTarp().add(sales.getAnnualFyp()));
						if (getQcReconfirmService().countReconfirmByxReference(sales.getxReference()) > 0)
						{
							supProdMonth.setReconfirm(supProdMonth.getReconfirm() + 1);
							supProd.getTotal().setReconfirm(supProd.getTotal().getReconfirm() + 1);
						}

						log("finish process sale");
					}
				}
			}
		}

		for (String campaignName : map.keySet())
		{
			Map<String, Map<String, ProductionByTsr>> campaignMap = map.get(campaignName);

			sortData(campaignMap.get("TSR"));
			sortData(campaignMap.get("SUP"));
		}

		return map;
	}

	private void copyCellStyle(Cell source, Cell target)
	{
		target.setCellStyle(source.getCellStyle());
		if (source.getCellType() == Cell.CELL_TYPE_STRING)
		{
			target.setCellValue(source.getStringCellValue());
		}
	}

	private void generateReport(OutputStream output)
			throws Exception
	{
		InputStream template = Thread.currentThread().getContextClassLoader().getResourceAsStream("template/SYDNEY.xlsx");
		Workbook wb = WorkbookFactory.create(template);
		Sheet tSheet = wb.getSheetAt(0);
		SheetConditionalFormatting scf = tSheet.getSheetConditionalFormatting();

		Row tHeaderRow = tSheet.getRow(1);
		Cell tHeaderCell = tHeaderRow.getCell(0);

		Row tCampaignRow = tSheet.getRow(3);
		Cell tCampaignCell = tCampaignRow.getCell(0);
		Cell tCampaignCellName = tCampaignRow.getCell(3);

		Map<String, Map<String, Map<String, ProductionByTsr>>> aggregateData = aggregateData();

		if (aggregateData != null)
		{
			for (String campaignName : aggregateData.keySet())
			{
				Map<String, Map<String, ProductionByTsr>> campaignMap = aggregateData.get(campaignName);
				Sheet campaignSheet = wb.createSheet(campaignName);
				campaignSheet.getSheetConditionalFormatting().addConditionalFormatting(scf.getConditionalFormattingAt(0));

				campaignSheet.addMergedRegion(new CellRangeAddress(1, 1, 0, 11));
				Row headerRow = campaignSheet.createRow(1);
				Cell headerCell = headerRow.createCell(0);
				copyCellStyle(tHeaderCell, headerCell);

				campaignSheet.addMergedRegion(new CellRangeAddress(3, 3, 0, 2));
				campaignSheet.addMergedRegion(new CellRangeAddress(3, 3, 3, 11));
				Row campaignRow = campaignSheet.createRow(3);
				Cell campaignCell = campaignRow.createCell(0);
				Cell campaignCellName = campaignRow.createCell(3);
				copyCellStyle(tCampaignCell, campaignCell);
				copyCellStyle(tCampaignCellName, campaignCellName);
				campaignCellName.setCellValue(campaignName);

				// ====================== TSR Header ======================
				Row tTsrHeaderRow1 = tSheet.getRow(6);
				Row tTsrHeaderRow2 = tSheet.getRow(7);
				Row tTsrHeaderRow3 = tSheet.getRow(8);
				Cell tTsrHeaderNoCell1 = tTsrHeaderRow1.getCell(0);
				Cell tTsrHeaderNoCell2 = tTsrHeaderRow2.getCell(0);
				Cell tTsrHeaderNoCell3 = tTsrHeaderRow3.getCell(0);
				Cell tTsrHeaderTsrCodeCell1 = tTsrHeaderRow1.getCell(1);
				Cell tTsrHeaderTsrCodeCell2 = tTsrHeaderRow2.getCell(1);
				Cell tTsrHeaderTsrCodeCell3 = tTsrHeaderRow3.getCell(1);
				Cell tTsrHeaderTsrNameCell1 = tTsrHeaderRow1.getCell(2);
				Cell tTsrHeaderTsrNameCell2 = tTsrHeaderRow2.getCell(2);
				Cell tTsrHeaderTsrNameCell3 = tTsrHeaderRow3.getCell(2);
				Cell tTsrHeaderStatusCell1 = tTsrHeaderRow1.getCell(3);
				Cell tTsrHeaderStatusCell2 = tTsrHeaderRow2.getCell(3);
				Cell tTsrHeaderStatusCell3 = tTsrHeaderRow3.getCell(3);
				Cell tTsrHeaderFloorSuccessCell = tTsrHeaderRow1.getCell(4);
				Cell tTsrHeaderMonthCell = tTsrHeaderRow2.getCell(4);
				Cell tTsrHeaderSalesCell = tTsrHeaderRow3.getCell(4);
				Cell tTsrHeaderTarpCell = tTsrHeaderRow3.getCell(5);
				Cell tTsrHeaderTotalCell = tTsrHeaderRow1.getCell(10);
				Cell tTsrHeaderTotalSalesCell = tTsrHeaderRow3.getCell(10);
				Cell tTsrHeaderTotalTarpCell = tTsrHeaderRow3.getCell(11);

				Row tsrHeaderRow1 = campaignSheet.createRow(6);
				Row tsrHeaderRow2 = campaignSheet.createRow(7);
				Row tsrHeaderRow3 = campaignSheet.createRow(8);

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 8, 0, 0));
				Cell tsrHeaderNoCell1 = tsrHeaderRow1.createCell(0);
				Cell tsrHeaderNoCell2 = tsrHeaderRow2.createCell(0);
				Cell tsrHeaderNoCell3 = tsrHeaderRow3.createCell(0);
				copyCellStyle(tTsrHeaderNoCell1, tsrHeaderNoCell1);
				copyCellStyle(tTsrHeaderNoCell2, tsrHeaderNoCell2);
				copyCellStyle(tTsrHeaderNoCell3, tsrHeaderNoCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 8, 1, 1));
				Cell tsrHeaderTsrCodeCell1 = tsrHeaderRow1.createCell(1);
				Cell tsrHeaderTsrCodeCell2 = tsrHeaderRow2.createCell(1);
				Cell tsrHeaderTsrCodeCell3 = tsrHeaderRow3.createCell(1);
				copyCellStyle(tTsrHeaderTsrCodeCell1, tsrHeaderTsrCodeCell1);
				copyCellStyle(tTsrHeaderTsrCodeCell2, tsrHeaderTsrCodeCell2);
				copyCellStyle(tTsrHeaderTsrCodeCell3, tsrHeaderTsrCodeCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 8, 2, 2));
				Cell tsrHeaderTsrNameCell1 = tsrHeaderRow1.createCell(2);
				Cell tsrHeaderTsrNameCell2 = tsrHeaderRow2.createCell(2);
				Cell tsrHeaderTsrNameCell3 = tsrHeaderRow3.createCell(2);
				copyCellStyle(tTsrHeaderTsrNameCell1, tsrHeaderTsrNameCell1);
				copyCellStyle(tTsrHeaderTsrNameCell2, tsrHeaderTsrNameCell2);
				copyCellStyle(tTsrHeaderTsrNameCell3, tsrHeaderTsrNameCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 8, 3, 3));
				Cell tsrHeaderStatusCell1 = tsrHeaderRow1.createCell(3);
				Cell tsrHeaderStatusCell2 = tsrHeaderRow2.createCell(3);
				Cell tsrHeaderStatusCell3 = tsrHeaderRow3.createCell(3);
				copyCellStyle(tTsrHeaderStatusCell1, tsrHeaderStatusCell1);
				copyCellStyle(tTsrHeaderStatusCell2, tsrHeaderStatusCell2);
				copyCellStyle(tTsrHeaderStatusCell3, tsrHeaderStatusCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 6, 4, (3 + (this.totalMonth * 2))));
				for (int i = 4; i <= (3 + (this.totalMonth * 2)); i++)
				{
					Cell tsrHeaderFloorSuccessCell = tsrHeaderRow1.createCell(i);
					copyCellStyle(tTsrHeaderFloorSuccessCell, tsrHeaderFloorSuccessCell);
				}

				for (int i = 0; i < this.totalMonth; i++)
				{
					campaignSheet.addMergedRegion(new CellRangeAddress(7, 7, ((i * 2) + 4), ((i * 2) + 5)));
					Cell tsrHeaderMonthCell1 = tsrHeaderRow2.createCell((i * 2) + 4);
					Cell tsrHeaderMonthCell2 = tsrHeaderRow2.createCell((i * 2) + 5);
					copyCellStyle(tTsrHeaderMonthCell, tsrHeaderMonthCell1);
					copyCellStyle(tTsrHeaderMonthCell, tsrHeaderMonthCell2);
					Date date = DateUtils.addMonths(getStartDate(), i);
					SimpleDateFormat df = new SimpleDateFormat("MMM-yy", Locale.US);
					tsrHeaderMonthCell1.setCellValue(df.format(date));

					Cell tsrHeaderSalesCell = tsrHeaderRow3.createCell((i * 2) + 4);
					Cell tsrHeaderTarpCell = tsrHeaderRow3.createCell((i * 2) + 5);
					copyCellStyle(tTsrHeaderSalesCell, tsrHeaderSalesCell);
					copyCellStyle(tTsrHeaderTarpCell, tsrHeaderTarpCell);
				}

				campaignSheet.addMergedRegion(new CellRangeAddress(6, 7, ((this.totalMonth * 2) + 4), ((this.totalMonth * 2) + 5)));
				Cell tsrHeaderTotalCell11 = tsrHeaderRow1.createCell((this.totalMonth * 2) + 4);
				Cell tsrHeaderTotalCell21 = tsrHeaderRow2.createCell((this.totalMonth * 2) + 4);
				Cell tsrHeaderTotalCell12 = tsrHeaderRow1.createCell((this.totalMonth * 2) + 5);
				Cell tsrHeaderTotalCell22 = tsrHeaderRow2.createCell((this.totalMonth * 2) + 5);
				copyCellStyle(tTsrHeaderTotalCell, tsrHeaderTotalCell11);
				copyCellStyle(tTsrHeaderTotalCell, tsrHeaderTotalCell21);
				copyCellStyle(tTsrHeaderTotalCell, tsrHeaderTotalCell12);
				copyCellStyle(tTsrHeaderTotalCell, tsrHeaderTotalCell22);

				Cell tsrHeaderTotalSalesCell = tsrHeaderRow3.createCell((this.totalMonth * 2) + 4);
				Cell tsrHeaderTotalTarpCell = tsrHeaderRow3.createCell((this.totalMonth * 2) + 5);
				copyCellStyle(tTsrHeaderTotalSalesCell, tsrHeaderTotalSalesCell);
				copyCellStyle(tTsrHeaderTotalTarpCell, tsrHeaderTotalTarpCell);

				// ==================== TSR Data ===================
				Row tTsrDataRow1 = tSheet.getRow(9);
				Row tTsrDataRow2 = tSheet.getRow(10);
				Row tTsrDataRow3 = tSheet.getRow(11);

				int rowOffset = 8;
				int tsrRecorcCount = 0;
				ProductionByTsr sumProdByTsr = new ProductionByTsr(this.totalMonth);
				Map<String, ProductionByTsr> allTsrMap = campaignMap.get("TSR");
				for (String tsrCode : allTsrMap.keySet())
				{
					tsrRecorcCount++;

					Tsr tsr = getTsrService().findTsrByTsrCode(tsrCode);
					ProductionByTsr tsrProd = allTsrMap.get(tsrCode);

					Row tsrDataRow = campaignSheet.createRow(rowOffset + tsrRecorcCount);

					Cell tsrDataNoCell = tsrDataRow.createCell(0);
					Cell tsrDataTsrCodeCell = tsrDataRow.createCell(1);
					Cell tsrDataNameCell = tsrDataRow.createCell(2);
					Cell tsrDataStatusCell = tsrDataRow.createCell(3);
					if (tsrRecorcCount == 1)
					{
						copyCellStyle(tTsrDataRow1.getCell(0), tsrDataNoCell);
						copyCellStyle(tTsrDataRow1.getCell(1), tsrDataTsrCodeCell);
						copyCellStyle(tTsrDataRow1.getCell(2), tsrDataNameCell);
						copyCellStyle(tTsrDataRow1.getCell(3), tsrDataStatusCell);
					}
					else if (tsrRecorcCount < allTsrMap.size())
					{
						copyCellStyle(tTsrDataRow2.getCell(0), tsrDataNoCell);
						copyCellStyle(tTsrDataRow2.getCell(1), tsrDataTsrCodeCell);
						copyCellStyle(tTsrDataRow2.getCell(2), tsrDataNameCell);
						copyCellStyle(tTsrDataRow2.getCell(3), tsrDataStatusCell);
					}
					else
					{
						copyCellStyle(tTsrDataRow3.getCell(0), tsrDataNoCell);
						copyCellStyle(tTsrDataRow3.getCell(1), tsrDataTsrCodeCell);
						copyCellStyle(tTsrDataRow3.getCell(2), tsrDataNameCell);
						copyCellStyle(tTsrDataRow3.getCell(3), tsrDataStatusCell);
					}
					tsrDataNoCell.setCellValue(tsrRecorcCount);
					tsrDataTsrCodeCell.setCellValue(tsr.getTsrCode());
					tsrDataNameCell.setCellValue(tsr.getFullName());
					tsrDataStatusCell.setCellValue(tsr.getTsrStatus().getStatusCode());

					// TSR Month Column
					for (int i = 0; i < this.totalMonth; i++)
					{
						Cell tsrDataSalesCell = tsrDataRow.createCell((i * 2) + 4);
						Cell tsrDataTarpCell = tsrDataRow.createCell((i * 2) + 5);
						if (tsrRecorcCount == 1)
						{
							copyCellStyle(tTsrDataRow1.getCell(4), tsrDataSalesCell);
							copyCellStyle(tTsrDataRow1.getCell(5), tsrDataTarpCell);
						}
						else if (tsrRecorcCount < allTsrMap.size())
						{
							copyCellStyle(tTsrDataRow2.getCell(4), tsrDataSalesCell);
							copyCellStyle(tTsrDataRow2.getCell(5), tsrDataTarpCell);
						}
						else
						{
							copyCellStyle(tTsrDataRow3.getCell(4), tsrDataSalesCell);
							copyCellStyle(tTsrDataRow3.getCell(5), tsrDataTarpCell);
						}
						tsrDataSalesCell.setCellValue(tsrProd.getProdMonthList().get(i).getSales());
						tsrDataTarpCell.setCellValue(tsrProd.getProdMonthList().get(i).getTarp().doubleValue());

						sumProdByTsr.getProdMonthList().get(i).setSales(sumProdByTsr.getProdMonthList().get(i).getSales() + tsrProd.getProdMonthList().get(i).getSales());
						sumProdByTsr.getProdMonthList().get(i).setTarp(sumProdByTsr.getProdMonthList().get(i).getTarp().add(tsrProd.getProdMonthList().get(i).getTarp()));
					}

					// TSR Total Column
					Cell tsrDataTotalSalesCell = tsrDataRow.createCell((this.totalMonth * 2) + 4);
					Cell tsrDataTotalTarpCell = tsrDataRow.createCell((this.totalMonth * 2) + 5);
					if (tsrRecorcCount == 1)
					{
						copyCellStyle(tTsrDataRow1.getCell(10), tsrDataTotalSalesCell);
						copyCellStyle(tTsrDataRow1.getCell(11), tsrDataTotalTarpCell);
					}
					else if (tsrRecorcCount < allTsrMap.size())
					{
						copyCellStyle(tTsrDataRow2.getCell(10), tsrDataTotalSalesCell);
						copyCellStyle(tTsrDataRow2.getCell(11), tsrDataTotalTarpCell);
					}
					else
					{
						copyCellStyle(tTsrDataRow3.getCell(10), tsrDataTotalSalesCell);
						copyCellStyle(tTsrDataRow3.getCell(11), tsrDataTotalTarpCell);
					}
					tsrDataTotalSalesCell.setCellValue(tsrProd.getTotal().getSales());
					tsrDataTotalTarpCell.setCellValue(tsrProd.getTotal().getTarp().doubleValue());

					sumProdByTsr.getTotal().setSales(sumProdByTsr.getTotal().getSales() + tsrProd.getTotal().getSales());
					sumProdByTsr.getTotal().setTarp(sumProdByTsr.getTotal().getTarp().add(tsrProd.getTotal().getTarp()));
				}

				// ==================== TSR Sum ====================
				Row tTsrSumRow = tSheet.getRow(12);
				Cell tTsrSumTotalCell = tTsrSumRow.getCell(0);
				Cell tTsrSumSalesCell = tTsrSumRow.getCell(4);
				Cell tTsrSumTarpCell = tTsrSumRow.getCell(5);
				Cell tTsrSumTotalSalesCell = tTsrSumRow.getCell(10);
				Cell tTsrSumTotalTarpCell = tTsrSumRow.getCell(11);

				rowOffset++;
				Row tsrSumRow = campaignSheet.createRow(rowOffset + tsrRecorcCount);
				campaignSheet.addMergedRegion(new CellRangeAddress(rowOffset + tsrRecorcCount, rowOffset + tsrRecorcCount, 0, 3));
				Cell tsrSumTotalCell0 = tsrSumRow.createCell(0);
				Cell tsrSumTotalCell1 = tsrSumRow.createCell(1);
				Cell tsrSumTotalCell2 = tsrSumRow.createCell(2);
				Cell tsrSumTotalCell3 = tsrSumRow.createCell(3);
				copyCellStyle(tTsrSumTotalCell, tsrSumTotalCell0);
				copyCellStyle(tTsrSumTotalCell, tsrSumTotalCell1);
				copyCellStyle(tTsrSumTotalCell, tsrSumTotalCell2);
				copyCellStyle(tTsrSumTotalCell, tsrSumTotalCell3);

				// TSR Month Column
				for (int i = 0; i < this.totalMonth; i++)
				{
					Cell tsrSumSalesCell = tsrSumRow.createCell((i * 2) + 4);
					Cell tsrSumTarpCell = tsrSumRow.createCell((i * 2) + 5);
					copyCellStyle(tTsrSumSalesCell, tsrSumSalesCell);
					copyCellStyle(tTsrSumTarpCell, tsrSumTarpCell);

					tsrSumSalesCell.setCellValue(sumProdByTsr.getProdMonthList().get(i).getSales());
					tsrSumTarpCell.setCellValue(sumProdByTsr.getProdMonthList().get(i).getTarp().doubleValue());
				}

				// TSR Total Column
				Cell tsrSumTotalSalesCell = tsrSumRow.createCell((this.totalMonth * 2) + 4);
				Cell tsrSumTotalTarpCell = tsrSumRow.createCell((this.totalMonth * 2) + 5);
				copyCellStyle(tTsrSumTotalSalesCell, tsrSumTotalSalesCell);
				copyCellStyle(tTsrSumTotalTarpCell, tsrSumTotalTarpCell);

				tsrSumTotalSalesCell.setCellValue(sumProdByTsr.getTotal().getSales());
				tsrSumTotalTarpCell.setCellValue(sumProdByTsr.getTotal().getTarp().doubleValue());

				// ====================== SUP ======================
				Row tSupHeaderRow1 = tSheet.getRow(15);
				Row tSupHeaderRow2 = tSheet.getRow(16);
				Row tSupHeaderRow3 = tSheet.getRow(17);
				Cell tSupHeaderNoCell1 = tSupHeaderRow1.getCell(0);
				Cell tSupHeaderNoCell2 = tSupHeaderRow2.getCell(0);
				Cell tSupHeaderNoCell3 = tSupHeaderRow3.getCell(0);
				Cell tSupHeaderSupCodeCell1 = tSupHeaderRow1.getCell(1);
				Cell tSupHeaderSupCodeCell2 = tSupHeaderRow2.getCell(1);
				Cell tSupHeaderSupCodeCell3 = tSupHeaderRow3.getCell(1);
				Cell tSupHeaderSupNameCell1 = tSupHeaderRow1.getCell(2);
				Cell tSupHeaderSupNameCell2 = tSupHeaderRow2.getCell(2);
				Cell tSupHeaderSupNameCell3 = tSupHeaderRow3.getCell(2);
				Cell tSupHeaderStatusCell1 = tSupHeaderRow1.getCell(3);
				Cell tSupHeaderStatusCell2 = tSupHeaderRow2.getCell(3);
				Cell tSupHeaderStatusCell3 = tSupHeaderRow3.getCell(3);
				Cell tSupHeaderFloorSuccessCell = tSupHeaderRow1.getCell(4);
				Cell tSupHeaderMonthCell = tSupHeaderRow2.getCell(4);
				Cell tSupHeaderSalesCell = tSupHeaderRow3.getCell(6);
				Cell tSupHeaderReconfirmCell = tSupHeaderRow3.getCell(7);
				Cell tSupHeaderTarpCell = tSupHeaderRow3.getCell(8);
				Cell tSupHeaderTotalCell = tSupHeaderRow1.getCell(12);
				Cell tSupHeaderTotalSalesCell = tSupHeaderRow3.getCell(12);
				Cell tSupHeaderTotalReconfirmCell = tSupHeaderRow3.getCell(13);
				Cell tSupHeaderTotalListConvCell = tSupHeaderRow3.getCell(14);
				Cell tSupHeaderTotalTarpCell = tSupHeaderRow3.getCell(15);

				Row supHeaderRow1 = campaignSheet.createRow(tsrRecorcCount + 12);
				Row supHeaderRow2 = campaignSheet.createRow(tsrRecorcCount + 13);
				Row supHeaderRow3 = campaignSheet.createRow(tsrRecorcCount + 14);

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 14, 0, 0));
				Cell supHeaderNoCell1 = supHeaderRow1.createCell(0);
				Cell supHeaderNoCell2 = supHeaderRow2.createCell(0);
				Cell supHeaderNoCell3 = supHeaderRow3.createCell(0);
				copyCellStyle(tSupHeaderNoCell1, supHeaderNoCell1);
				copyCellStyle(tSupHeaderNoCell2, supHeaderNoCell2);
				copyCellStyle(tSupHeaderNoCell3, supHeaderNoCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 14, 1, 1));
				Cell supHeaderSupCodeCell1 = supHeaderRow1.createCell(1);
				Cell supHeaderSupCodeCell2 = supHeaderRow2.createCell(1);
				Cell supHeaderSupCodeCell3 = supHeaderRow3.createCell(1);
				copyCellStyle(tSupHeaderSupCodeCell1, supHeaderSupCodeCell1);
				copyCellStyle(tSupHeaderSupCodeCell2, supHeaderSupCodeCell2);
				copyCellStyle(tSupHeaderSupCodeCell3, supHeaderSupCodeCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 14, 2, 2));
				Cell supHeaderSupNameCell1 = supHeaderRow1.createCell(2);
				Cell supHeaderSupNameCell2 = supHeaderRow2.createCell(2);
				Cell supHeaderSupNameCell3 = supHeaderRow3.createCell(2);
				copyCellStyle(tSupHeaderSupNameCell1, supHeaderSupNameCell1);
				copyCellStyle(tSupHeaderSupNameCell2, supHeaderSupNameCell2);
				copyCellStyle(tSupHeaderSupNameCell3, supHeaderSupNameCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 14, 3, 3));
				Cell supHeaderStatusCell1 = supHeaderRow1.createCell(3);
				Cell supHeaderStatusCell2 = supHeaderRow2.createCell(3);
				Cell supHeaderStatusCell3 = supHeaderRow3.createCell(3);
				copyCellStyle(tSupHeaderStatusCell1, supHeaderStatusCell1);
				copyCellStyle(tSupHeaderStatusCell2, supHeaderStatusCell2);
				copyCellStyle(tSupHeaderStatusCell3, supHeaderStatusCell3);

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 12, 4, (2 + (totalMonth * 3))));
				for (int i = 4; i <= (2 + (totalMonth * 3)); i++)
				{
					Cell supHeaderFloorSuccessCell = supHeaderRow1.createCell(i);
					copyCellStyle(tSupHeaderFloorSuccessCell, supHeaderFloorSuccessCell);
				}

				for (int i = 0; i < totalMonth; i++)
				{
					Date date = DateUtils.addMonths(getStartDate(), i);
					SimpleDateFormat df = new SimpleDateFormat("MMM-yy", Locale.US);

					if (i == 0)
					{
						campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 13, tsrRecorcCount + 13, 4, 5));
						Cell supHeaderMonthCell1 = supHeaderRow2.createCell(4);
						Cell supHeaderMonthCell2 = supHeaderRow2.createCell(5);
						copyCellStyle(tSupHeaderMonthCell, supHeaderMonthCell1);
						copyCellStyle(tSupHeaderMonthCell, supHeaderMonthCell2);
						supHeaderMonthCell1.setCellValue(df.format(date));

						Cell supHeaderSalesCell = supHeaderRow3.createCell(4);
						Cell supHeaderTarpCell = supHeaderRow3.createCell(5);
						copyCellStyle(tSupHeaderSalesCell, supHeaderSalesCell);
						copyCellStyle(tSupHeaderTarpCell, supHeaderTarpCell);
					}
					else
					{
						campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 13, tsrRecorcCount + 13, ((i * 3) + 3), ((i * 3) + 5)));
						Cell supHeaderMonthCell1 = supHeaderRow2.createCell((i * 3) + 3);
						Cell supHeaderMonthCell2 = supHeaderRow2.createCell((i * 3) + 4);
						Cell supHeaderMonthCell3 = supHeaderRow2.createCell((i * 3) + 5);
						copyCellStyle(tSupHeaderMonthCell, supHeaderMonthCell1);
						copyCellStyle(tSupHeaderMonthCell, supHeaderMonthCell2);
						copyCellStyle(tSupHeaderMonthCell, supHeaderMonthCell3);
						supHeaderMonthCell1.setCellValue(df.format(date));

						Cell supHeaderSalesCell = supHeaderRow3.createCell((i * 3) + 3);
						Cell supHeaderReconfirmCell = supHeaderRow3.createCell((i * 3) + 4);
						Cell supHeaderTarpCell = supHeaderRow3.createCell((i * 3) + 5);
						copyCellStyle(tSupHeaderSalesCell, supHeaderSalesCell);
						copyCellStyle(tSupHeaderReconfirmCell, supHeaderReconfirmCell);
						copyCellStyle(tSupHeaderTarpCell, supHeaderTarpCell);
					}
				}

				campaignSheet.addMergedRegion(new CellRangeAddress(tsrRecorcCount + 12, tsrRecorcCount + 13, ((this.totalMonth * 3) + 3), ((this.totalMonth * 3) + 6)));
				Cell supHeaderTotalCell11 = supHeaderRow1.createCell((this.totalMonth * 3) + 3);
				Cell supHeaderTotalCell12 = supHeaderRow2.createCell((this.totalMonth * 3) + 3);
				Cell supHeaderTotalCell21 = supHeaderRow1.createCell((this.totalMonth * 3) + 4);
				Cell supHeaderTotalCell22 = supHeaderRow2.createCell((this.totalMonth * 3) + 4);
				Cell supHeaderTotalCell31 = supHeaderRow1.createCell((this.totalMonth * 3) + 5);
				Cell supHeaderTotalCell32 = supHeaderRow2.createCell((this.totalMonth * 3) + 5);
				Cell supHeaderTotalCell41 = supHeaderRow1.createCell((this.totalMonth * 3) + 6);
				Cell supHeaderTotalCell42 = supHeaderRow2.createCell((this.totalMonth * 3) + 6);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell11);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell12);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell21);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell22);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell31);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell32);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell41);
				copyCellStyle(tSupHeaderTotalCell, supHeaderTotalCell42);

				Cell supHeaderTotalSalesCell = supHeaderRow3.createCell((totalMonth * 3) + 3);
				Cell supHeaderTotalReconfirmCell = supHeaderRow3.createCell((totalMonth * 3) + 4);
				Cell supHeaderTotalListConvCell = supHeaderRow3.createCell((totalMonth * 3) + 5);
				Cell supHeaderTotalTarpCell = supHeaderRow3.createCell((totalMonth * 3) + 6);
				copyCellStyle(tSupHeaderTotalSalesCell, supHeaderTotalSalesCell);
				copyCellStyle(tSupHeaderTotalReconfirmCell, supHeaderTotalReconfirmCell);
				copyCellStyle(tSupHeaderTotalListConvCell, supHeaderTotalListConvCell);
				copyCellStyle(tSupHeaderTotalTarpCell, supHeaderTotalTarpCell);

				// ==================== SUP Data ===================
				Row tSupDataRow1 = tSheet.getRow(18);
				Row tSupDataRow2 = tSheet.getRow(19);
				Row tSupDataRow3 = tSheet.getRow(20);

				rowOffset = 5 + rowOffset + tsrRecorcCount;
				int supRecorcCount = 0;
				ProductionByTsr sumProdBySup = new ProductionByTsr(this.totalMonth);
				Map<String, ProductionByTsr> allSupMap = campaignMap.get("SUP");
				for (String supCode : allSupMap.keySet())
				{
					supRecorcCount++;

					Tsr sup = getTsrService().findTsrByTsrCode(supCode);
					ProductionByTsr supProd = allSupMap.get(supCode);

					Row supDataRow = campaignSheet.createRow(rowOffset + supRecorcCount);

					Cell supDataNoCell = supDataRow.createCell(0);
					Cell supDataTsrCodeCell = supDataRow.createCell(1);
					Cell supDataNameCell = supDataRow.createCell(2);
					Cell supDataStatusCell = supDataRow.createCell(3);
					if (supRecorcCount == 1)
					{
						copyCellStyle(tSupDataRow1.getCell(0), supDataNoCell);
						copyCellStyle(tSupDataRow1.getCell(1), supDataTsrCodeCell);
						copyCellStyle(tSupDataRow1.getCell(2), supDataNameCell);
						copyCellStyle(tSupDataRow1.getCell(3), supDataStatusCell);
					}
					else if (supRecorcCount < allSupMap.size())
					{
						copyCellStyle(tSupDataRow2.getCell(0), supDataNoCell);
						copyCellStyle(tSupDataRow2.getCell(1), supDataTsrCodeCell);
						copyCellStyle(tSupDataRow2.getCell(2), supDataNameCell);
						copyCellStyle(tSupDataRow2.getCell(3), supDataStatusCell);
					}
					else
					{
						copyCellStyle(tSupDataRow3.getCell(0), supDataNoCell);
						copyCellStyle(tSupDataRow3.getCell(1), supDataTsrCodeCell);
						copyCellStyle(tSupDataRow3.getCell(2), supDataNameCell);
						copyCellStyle(tSupDataRow3.getCell(3), supDataStatusCell);
					}
					supDataNoCell.setCellValue(supRecorcCount);
					supDataTsrCodeCell.setCellValue(sup.getTsrCode());
					supDataNameCell.setCellValue(sup.getFullName());
					supDataStatusCell.setCellValue(sup.getTsrStatus().getStatusCode());

					// SUP Month Column
					for (int i = 0; i < this.totalMonth; i++)
					{
						if (i == 0)
						{
							Cell supDataSalesCell = supDataRow.createCell(4);
							Cell supDataTarpCell = supDataRow.createCell(5);
							if (supRecorcCount == 1)
							{
								copyCellStyle(tSupDataRow1.getCell(4), supDataSalesCell);
								copyCellStyle(tSupDataRow1.getCell(5), supDataTarpCell);
							}
							else if (supRecorcCount < allSupMap.size())
							{
								copyCellStyle(tSupDataRow2.getCell(4), supDataSalesCell);
								copyCellStyle(tSupDataRow2.getCell(5), supDataTarpCell);
							}
							else
							{
								copyCellStyle(tSupDataRow3.getCell(4), supDataSalesCell);
								copyCellStyle(tSupDataRow3.getCell(5), supDataTarpCell);
							}
							supDataSalesCell.setCellValue(supProd.getProdMonthList().get(i).getSales());
							supDataTarpCell.setCellValue(supProd.getProdMonthList().get(i).getTarp().doubleValue());

							sumProdBySup.getProdMonthList().get(i).setSales(sumProdBySup.getProdMonthList().get(i).getSales() + supProd.getProdMonthList().get(i).getSales());
							sumProdBySup.getProdMonthList().get(i).setTarp(sumProdBySup.getProdMonthList().get(i).getTarp().add(supProd.getProdMonthList().get(i).getTarp()));
						}
						else
						{
							Cell supDataSalesCell = supDataRow.createCell((i * 3) + 3);
							Cell supDataReconfirmCell = supDataRow.createCell((i * 3) + 4);
							Cell supDataTarpCell = supDataRow.createCell((i * 3) + 5);
							if (supRecorcCount == 1)
							{
								copyCellStyle(tSupDataRow1.getCell(6), supDataSalesCell);
								copyCellStyle(tSupDataRow1.getCell(7), supDataReconfirmCell);
								copyCellStyle(tSupDataRow1.getCell(8), supDataTarpCell);
							}
							else if (supRecorcCount < allSupMap.size())
							{
								copyCellStyle(tSupDataRow2.getCell(6), supDataSalesCell);
								copyCellStyle(tSupDataRow2.getCell(7), supDataReconfirmCell);
								copyCellStyle(tSupDataRow1.getCell(8), supDataTarpCell);
							}
							else
							{
								copyCellStyle(tSupDataRow3.getCell(6), supDataSalesCell);
								copyCellStyle(tSupDataRow3.getCell(7), supDataReconfirmCell);
								copyCellStyle(tSupDataRow1.getCell(8), supDataTarpCell);
							}
							supDataSalesCell.setCellValue(supProd.getProdMonthList().get(i).getSales());
							supDataReconfirmCell.setCellValue(supProd.getProdMonthList().get(i).getReconfirm());
							supDataTarpCell.setCellValue(supProd.getProdMonthList().get(i).getTarp().doubleValue());

							sumProdBySup.getProdMonthList().get(i).setSales(sumProdBySup.getProdMonthList().get(i).getSales() + supProd.getProdMonthList().get(i).getSales());
							sumProdBySup.getProdMonthList().get(i).setReconfirm(sumProdBySup.getProdMonthList().get(i).getReconfirm() + supProd.getProdMonthList().get(i).getReconfirm());
							sumProdBySup.getProdMonthList().get(i).setTarp(sumProdBySup.getProdMonthList().get(i).getTarp().add(supProd.getProdMonthList().get(i).getTarp()));
						}
					}

					// SUP Total Column
					Cell supDataTotalSalesCell = supDataRow.createCell((this.totalMonth * 3) + 3);
					Cell supDataTotalReconfirmCell = supDataRow.createCell((this.totalMonth * 3) + 4);
					Cell supDataTotalListConvCell = supDataRow.createCell((this.totalMonth * 3) + 5);
					Cell supDataTotalTarpCell = supDataRow.createCell((this.totalMonth * 3) + 6);
					if (supRecorcCount == 1)
					{
						copyCellStyle(tSupDataRow1.getCell(12), supDataTotalSalesCell);
						copyCellStyle(tSupDataRow1.getCell(13), supDataTotalReconfirmCell);
						copyCellStyle(tSupDataRow1.getCell(14), supDataTotalListConvCell);
						copyCellStyle(tSupDataRow1.getCell(15), supDataTotalTarpCell);
					}
					else if (supRecorcCount < allSupMap.size())
					{
						copyCellStyle(tSupDataRow2.getCell(12), supDataTotalSalesCell);
						copyCellStyle(tSupDataRow2.getCell(13), supDataTotalReconfirmCell);
						copyCellStyle(tSupDataRow2.getCell(14), supDataTotalListConvCell);
						copyCellStyle(tSupDataRow2.getCell(15), supDataTotalTarpCell);
					}
					else
					{
						copyCellStyle(tSupDataRow3.getCell(12), supDataTotalSalesCell);
						copyCellStyle(tSupDataRow3.getCell(13), supDataTotalReconfirmCell);
						copyCellStyle(tSupDataRow3.getCell(14), supDataTotalListConvCell);
						copyCellStyle(tSupDataRow3.getCell(15), supDataTotalTarpCell);
					}
					supDataTotalSalesCell.setCellValue(supProd.getTotal().getSales());
					supDataTotalReconfirmCell.setCellValue(supProd.getTotal().getReconfirm());
					supDataTotalListConvCell.setCellValue(supProd.getTotal().getListConv().doubleValue());
					supDataTotalTarpCell.setCellValue(supProd.getTotal().getTarp().doubleValue());

					sumProdBySup.getTotal().setSales(sumProdBySup.getTotal().getSales() + supProd.getTotal().getSales());
					sumProdBySup.getTotal().setReconfirm(sumProdBySup.getTotal().getReconfirm() + supProd.getTotal().getReconfirm());
					// sumProdBySup.getTotal().setListConv(listConv); // TODO
					sumProdBySup.getTotal().setTarp(sumProdBySup.getTotal().getTarp().add(supProd.getTotal().getTarp()));
				}

				// ==================== SUP Sum ====================
				Row tSupSumRow = tSheet.getRow(21);
				Cell tSupSumTotalCell = tSupSumRow.getCell(0);
				Cell tSupSumSalesCell = tSupSumRow.getCell(6);
				Cell tSupSumReconfirmCell = tSupSumRow.getCell(7);
				Cell tSupSumTarpCell = tSupSumRow.getCell(8);
				Cell tSupSumTotalSalesCell = tSupSumRow.getCell(12);
				Cell tSupSumTotalReconfirmCell = tSupSumRow.getCell(13);
				Cell tSupSumTotalListConvCell = tSupSumRow.getCell(14);
				Cell tSupSumTotalTarpCell = tSupSumRow.getCell(15);

				rowOffset++;
				Row supSumRow = campaignSheet.createRow(rowOffset + supRecorcCount);
				campaignSheet.addMergedRegion(new CellRangeAddress(rowOffset + supRecorcCount, rowOffset + supRecorcCount, 0, 3));
				Cell supSumTotalCell0 = supSumRow.createCell(0);
				Cell supSumTotalCell1 = supSumRow.createCell(1);
				Cell supSumTotalCell2 = supSumRow.createCell(2);
				Cell supSumTotalCell3 = supSumRow.createCell(3);
				copyCellStyle(tSupSumTotalCell, supSumTotalCell0);
				copyCellStyle(tSupSumTotalCell, supSumTotalCell1);
				copyCellStyle(tSupSumTotalCell, supSumTotalCell2);
				copyCellStyle(tSupSumTotalCell, supSumTotalCell3);

				// SUP Month Column
				for (int i = 0; i < this.totalMonth; i++)
				{
					if (i == 0)
					{
						Cell supSumSalesCell = supSumRow.createCell(4);
						Cell supSumTarpCell = supSumRow.createCell(5);
						copyCellStyle(tSupSumSalesCell, supSumSalesCell);
						copyCellStyle(tSupSumTarpCell, supSumTarpCell);

						supSumSalesCell.setCellValue(sumProdBySup.getProdMonthList().get(i).getSales());
						supSumTarpCell.setCellValue(sumProdBySup.getProdMonthList().get(i).getTarp().doubleValue());
					}
					else
					{
						Cell supSumSalesCell = supSumRow.createCell((i * 3) + 3);
						Cell supSumReconfirmCell = supSumRow.createCell((i * 3) + 4);
						Cell supSumTarpCell = supSumRow.createCell((i * 3) + 5);
						copyCellStyle(tSupSumSalesCell, supSumSalesCell);
						copyCellStyle(tSupSumReconfirmCell, supSumReconfirmCell);
						copyCellStyle(tSupSumTarpCell, supSumTarpCell);

						supSumSalesCell.setCellValue(sumProdBySup.getProdMonthList().get(i).getSales());
						supSumReconfirmCell.setCellValue(sumProdBySup.getProdMonthList().get(i).getReconfirm());
						supSumTarpCell.setCellValue(sumProdBySup.getProdMonthList().get(i).getTarp().doubleValue());
					}
				}

				// SUP Total Column
				Cell supSumTotalSalesCell = supSumRow.createCell((this.totalMonth * 3) + 3);
				Cell supSumTotalReconfirmCell = supSumRow.createCell((this.totalMonth * 3) + 4);
				Cell supSumTotalListConvCell = supSumRow.createCell((this.totalMonth * 3) + 5);
				Cell supSumTotalTarpCell = supSumRow.createCell((this.totalMonth * 3) + 6);
				copyCellStyle(tSupSumTotalSalesCell, supSumTotalSalesCell);
				copyCellStyle(tSupSumTotalReconfirmCell, supSumTotalReconfirmCell);
				copyCellStyle(tSupSumTotalListConvCell, supSumTotalListConvCell);
				copyCellStyle(tSupSumTotalTarpCell, supSumTotalTarpCell);

				supSumTotalSalesCell.setCellValue(sumProdBySup.getTotal().getSales());
				supSumTotalReconfirmCell.setCellValue(sumProdBySup.getTotal().getReconfirm());
				// supSumTotalListConvCell.setCellValue(sumProdBySup.getTotal().getListConv());
				// TODO
				supSumTotalTarpCell.setCellValue(sumProdBySup.getTotal().getTarp().doubleValue());
			}
		}

		wb.removeSheetAt(0);

		wb.write(output);
	}

	private Date getStartDate()
	{
		Calendar c = Calendar.getInstance(Locale.US);
		c.set(2014, 5, 1);
		return c.getTime();
	}

	class ProductionByTsr {
		Tsr tsr;
		List<ProductionMonth> prodMonthList = new ArrayList<ReportIncentiveSydney.ProductionMonth>();
		ProductionMonth total;

		public ProductionByTsr(int totalMonth)
		{
			for (int i = 0; i < totalMonth; i++)
			{
				prodMonthList.add(new ProductionMonth());
			}

			this.total = new ProductionMonth();
		}

		public Tsr getTsr()
		{
			return tsr;
		}

		public void setTsr(Tsr tsr)
		{
			this.tsr = tsr;
		}

		public List<ProductionMonth> getProdMonthList()
		{
			return prodMonthList;
		}

		public void setProdMonthList(List<ProductionMonth> prodMonthList)
		{
			this.prodMonthList = prodMonthList;
		}

		public ProductionMonth getTotal()
		{
			return total;
		}

		public void setTotal(ProductionMonth total)
		{
			this.total = total;
		}

	}

	class ProductionMonth {
		String monthLabel;
		int sales = 0;
		BigDecimal tarp = new BigDecimal(0.0);
		int reconfirm = 0;
		BigDecimal listConv = new BigDecimal(0.0);

		public String getMonthLabel()
		{
			return monthLabel;
		}

		public void setMonthLabel(String monthLabel)
		{
			this.monthLabel = monthLabel;
		}

		public int getSales()
		{
			return sales;
		}

		public void setSales(int sales)
		{
			this.sales = sales;
		}

		public BigDecimal getTarp()
		{
			return tarp;
		}

		public void setTarp(BigDecimal tarp)
		{
			this.tarp = tarp;
		}

		public int getReconfirm()
		{
			return reconfirm;
		}

		public void setReconfirm(int reconfirm)
		{
			this.reconfirm = reconfirm;
		}

		public BigDecimal getListConv()
		{
			return listConv;
		}

		public void setListConv(BigDecimal listConv)
		{
			this.listConv = listConv;
		}

	}

	public static void main(String[] args)
			throws Exception
	{
		ReportIncentiveSydney batch = new ReportIncentiveSydney();
		batch.setEnableLog(false);
		batch.totalMonth = 4;
		batch.generateReport(new FileOutputStream("d:/testSydneyOutput.xlsx"));
	}

}
