package com.bachue.snr.prosnr01.business.reportes;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.PrintSetup;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;


/**
 * Clase que contiene todos la funcionalidad para manejar hojas en formato xls
 *
 * @author mblanco
 */
public class ReportsSheet implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = 4811343468870533519L;

	/**
	 * Instancia un nuevo objeto reports sheet.
	 */
	public ReportsSheet()
	{
	}

	/**
	 * Retorna el valor de evening report.
	 *
	 * @param accmso_hashMapCollection correspondiente al valor del tipo de objeto Collection<Collection<Map>>
	 * @param amis_sheetNumber correspondiente al valor del tipo de objeto Map
	 * @param as_path correspondiente al valor del tipo de objeto String
	 * @param as_filName correspondiente al valor del tipo de objeto String
	 * @param aba_templateReport correspondiente al valor del tipo de objeto byte[]
	 * @param amss_tags correspondiente al valor del tipo de objeto Map
	 * @return el valor de evening report
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public synchronized byte[] getEveningReport(
	    Collection<Collection<Map<String, Object>>> accmso_hashMapCollection, Map<Integer, String> amis_sheetNumber,
	    String as_path, String as_filName, byte[] aba_templateReport, Map<String, String> amss_tags
	)
	    throws IOException
	{
		Workbook lw_workbook;
		byte[]   lba_return;

		lba_return = null;

		if((aba_templateReport != null) && (aba_templateReport.length > NumericUtils.DEFAULT_INT_VALUE))
		{
			ByteArrayOutputStream lbaos_output;

			lbaos_output     = new ByteArrayOutputStream();
			lw_workbook      = getExcelWithTemplate(
				    accmso_hashMapCollection, amis_sheetNumber, aba_templateReport, amss_tags
				);

			ExcelUtils.write(lw_workbook, lbaos_output);

			lba_return = lbaos_output.toByteArray();
		}
		else
		{
			lw_workbook = getExcel(accmso_hashMapCollection, amis_sheetNumber);

			if((lw_workbook != null) && StringUtils.isValidString(as_path) && StringUtils.isValidString(as_filName))
			{
				FileOutputStream lf_outputStream = new FileOutputStream(as_path + File.pathSeparator + as_filName);

				lw_workbook.write(lf_outputStream);
				lf_outputStream.close();
			}
		}

		return lba_return;
	}

	/**
	 * Retorna el valor de excel de un objeto tipo Workbook
	 *
	 * @param accmso_hashMapCollection correspondiente al valor del tipo de objeto Collection<Collection<Map>>
	 * @param amis_sheetNumber correspondiente al valor del tipo de objeto Map
	 * @return el valor de excel
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public synchronized Workbook getExcel(
	    Collection<Collection<Map<String, Object>>> accmso_hashMapCollection, Map<Integer, String> amis_sheetNumber
	)
	    throws IOException
	{
		int                    li_row;
		int                    li_sheet;
		String                 ls_currentSheet;
		Map<String, CellStyle> lmscs_styles;
		Sheet                  ls_sheet;
		Workbook               lw_workbook;

		ls_currentSheet     = "";

		li_sheet         = 0;
		lw_workbook      = ExcelUtils.createWorkbook(true);
		lmscs_styles     = ExcelUtils.createStyles(lw_workbook);

		if(CollectionUtils.isValidCollection(accmso_hashMapCollection))
		{
			for(Collection<Map<String, Object>> lcmso_currentHashMap : accmso_hashMapCollection)
			{
				li_row = 0;

				if(CollectionUtils.isValidCollection(amis_sheetNumber))
					ls_currentSheet = (String)amis_sheetNumber.get(new Integer(li_sheet++));

				ls_sheet = ExcelUtils.getSheet(lw_workbook, ls_currentSheet);

				ls_sheet.setDisplayGridlines(true);
				ls_sheet.setPrintGridlines(true);
				ls_sheet.setFitToPage(true);
				ls_sheet.setHorizontallyCenter(true);

				{
					PrintSetup lps_setup;

					lps_setup = ls_sheet.getPrintSetup();

					lps_setup.setLandscape(true);
					lps_setup.setFitHeight((short)1);
					lps_setup.setFitWidth((short)1);

					ls_sheet.setAutobreaks(true);
				}

				{
					CellStyle lcs_titleStyle;

					lcs_titleStyle = lmscs_styles.get("title");

					{
						int li_cell;
						Row lr_title;
						li_cell      = 0;
						lr_title     = ExcelUtils.getRow(ls_sheet, li_row++);

						lr_title.setHeightInPoints(23.75f);

						{
							Cell lc_cell;

							int  li_count;

							li_count = 0;

							for(Map<String, Object> lmso_current : lcmso_currentHashMap)
							{
								while(li_count == 0)
								{
									String ls_colmnName;

									Iterator<String> lis_iterador = lmso_current.keySet().iterator();
									li_count++;

									while(lis_iterador.hasNext())
									{
										ls_colmnName     = StringUtils.getStringUpperCase((String)lis_iterador.next());
										ls_colmnName     = ls_colmnName.replace('_', ' ');
										lc_cell          = ExcelUtils.getCell(lr_title, li_cell);

										lc_cell.setCellStyle(lcs_titleStyle);
										lc_cell.setCellValue(ls_colmnName);

										ls_sheet.setColumnWidth(li_cell++, ExcelUtils.calculateColumnWidth(28d));
									}
								}
							}
						}
					}

					CellStyle                     lcs_normalStyle;
					CellStyle                     lcs_dateStyle;
					Iterator<Map<String, Object>> limso_hashmap;

					lcs_normalStyle     = lmscs_styles.get("normal");
					lcs_dateStyle       = lmscs_styles.get("date");

					limso_hashmap = lcmso_currentHashMap.iterator();

					while(limso_hashmap.hasNext())
					{
						Map<String, Object> lmso_hashMap;

						lmso_hashMap = limso_hashmap.next();

						if((lmso_hashMap != null))
							createRow(ls_sheet, lcs_normalStyle, lcs_dateStyle, li_row++, lmso_hashMap);
					}
				}
			}
		}

		return lw_workbook;
	}

	/**
	 * Retorna el valor de excel with template.
	 *
	 * @param accmso_hashMapCollection correspondiente al valor del tipo de objeto Collection<Collection<Map>>
	 * @param amis_sheetNumber correspondiente al valor del tipo de objeto Map
	 * @param ac_templateReport correspondiente al valor del tipo de objeto byte[]
	 * @param amss_tags correspondiente al valor del tipo de objeto Map
	 * @return el valor de excel with template
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public synchronized Workbook getExcelWithTemplate(
	    Collection<Collection<Map<String, Object>>> accmso_hashMapCollection, Map<Integer, String> amis_sheetNumber,
	    byte[]                                      ac_templateReport, Map<String, String> amss_tags
	)
	    throws IOException
	{
		int                    li_row;
		int                    li_sheet;
		String                 ls_currentSheet;
		Map<String, CellStyle> lmscs_styles;
		Sheet                  ls_sheet;
		Workbook               lw_workbook;

		ls_currentSheet     = "";

		li_sheet         = 0;
		lw_workbook      = ExcelUtils.createWorkbook(true, ac_templateReport);
		lmscs_styles     = ExcelUtils.createStyles(lw_workbook);

		if(CollectionUtils.isValidCollection(accmso_hashMapCollection))
		{
			for(Collection<Map<String, Object>> lcmso_currentHashMap : accmso_hashMapCollection)
			{
				li_row = 0;

				if(CollectionUtils.isValidCollection(amis_sheetNumber))
					ls_currentSheet = amis_sheetNumber.get(new Integer(li_sheet++));

				ls_sheet = ExcelUtils.getSheet(lw_workbook, ls_currentSheet);

				ls_sheet.setDisplayGridlines(true);
				ls_sheet.setPrintGridlines(true);
				ls_sheet.setFitToPage(true);
				ls_sheet.setHorizontallyCenter(true);

				{
					PrintSetup lps_setup;

					lps_setup = ls_sheet.getPrintSetup();

					lps_setup.setLandscape(true);
					lps_setup.setFitHeight((short)1);
					lps_setup.setFitWidth((short)1);

					ls_sheet.setAutobreaks(true);
				}

				{
					CellStyle lcs_titleStyle;
					CellStyle lcs_normalStyle;
					CellStyle lcs_dateStyle;

					lcs_normalStyle     = lmscs_styles.get("normal");
					lcs_dateStyle       = lmscs_styles.get("date");
					lcs_titleStyle      = lmscs_styles.get("title");

					{
						int li_cell;
						Row lr_title;

						li_cell      = 0;
						lr_title     = null;

						{
							Cell lc_cell;

							int  li_count;

							li_count = 0;

							{
								boolean lb_break;

								lb_break = false;

								for(int i = 0; i <= ls_sheet.getLastRowNum(); i++)
								{
									lr_title = ExcelUtils.getRow(ls_sheet, i);

									for(int li_celda = 0; li_celda <= lr_title.getLastCellNum(); li_celda++)
									{
										lc_cell = lr_title.getCell(li_celda);

										if(lc_cell != null)
										{
											if(
											    (lc_cell.getCellType() != Cell.CELL_TYPE_NUMERIC)
												    && (lc_cell.getCellType() != Cell.CELL_TYPE_FORMULA)
											)
											{
												String ls_cellText;

												ls_cellText = lc_cell.getStringCellValue();

												if(StringUtils.isValidString(ls_cellText))
												{
													if(ls_cellText.equalsIgnoreCase("<TAG_INICIO_TABLA>"))
													{
														if(!lb_break)
														{
															li_cell     = li_celda;
															li_row      = i;

															lb_break = true;
														}
													}
													else
													{
														if(CollectionUtils.isValidCollection(amss_tags))
														{
															Iterator<String> lis_keys;

															lis_keys = amss_tags.keySet().iterator();

															while(lis_keys.hasNext())
															{
																String ls_key;

																ls_key = lis_keys.next();

																if(StringUtils.isValidString(ls_key))
																{
																	if(ls_cellText.contains(ls_key))
																	{
																		String ls_textoValor;

																		ls_textoValor = amss_tags.get(ls_key);

																		if(StringUtils.isValidString(ls_textoValor))
																			ls_cellText = ls_cellText.replace(
																				    ls_key, ls_textoValor
																				);
																	}
																}
															}

															lc_cell.setCellType(Cell.CELL_TYPE_STRING);

															if(StringUtils.isValidString(ls_cellText))
																lc_cell.setCellValue(
																    StringUtils.getStringNormalized(ls_cellText)
																);
															else
																lc_cell.setCellValue("");
														}
													}
												}
											}
										}

										if(lb_break)
											break;
									}

									if(lb_break)
									{
										lr_title.setHeightInPoints(23.75f);

										break;
									}
								}
							}

							for(Map<String, Object> lmso_current : lcmso_currentHashMap)
							{
								while(li_count == 0)
								{
									String ls_colmnName;

									Iterator<String> lis_iterador = lmso_current.keySet().iterator();
									li_count++;

									while(lis_iterador.hasNext())
									{
										ls_colmnName     = StringUtils.getStringUpperCase((String)lis_iterador.next());
										ls_colmnName     = ls_colmnName.replace('_', ' ');
										lc_cell          = ExcelUtils.getCell(lr_title, li_cell);

										lc_cell.setCellStyle(lcs_titleStyle);
										lc_cell.setCellValue(ls_colmnName);

										ls_sheet.setColumnWidth(li_cell++, ExcelUtils.calculateColumnWidth(28d));
									}
								}
							}
						}
					}

					Iterator<Map<String, Object>> limso_hashmap;

					limso_hashmap = lcmso_currentHashMap.iterator();

					while(limso_hashmap.hasNext())
					{
						Map<String, Object> lmso_hashMap;

						lmso_hashMap = limso_hashmap.next();

						if((lmso_hashMap != null))
							createRow(ls_sheet, lcs_normalStyle, lcs_dateStyle, ++li_row, lmso_hashMap);
					}
				}
			}
		}

		return lw_workbook;
	}

	/**
	 * Crea una nueva columna
	 *
	 * @param as_sheet correspondiente al valor del tipo de objeto Sheet
	 * @param acs_normalStyle correspondiente al valor del tipo de objeto CellStyle
	 * @param acs_dateStyle correspondiente al valor del tipo de objeto CellStyle
	 * @param ai_row correspondiente al valor del tipo de objeto int
	 * @param amso_dataReport correspondiente al valor del tipo de objeto Map
	 */
	private static void createRow(
	    Sheet as_sheet, CellStyle acs_normalStyle, CellStyle acs_dateStyle, int ai_row,
	    Map<String, Object> amso_dataReport
	)
	{
		int                                 li_cell;
		Row                                 lr_row;
		Iterator<Map.Entry<String, Object>> limeso_dataReport;
		li_cell     = 0;
		lr_row      = ExcelUtils.getRow(as_sheet, ai_row);

		lr_row.setHeightInPoints(11.25f);

		limeso_dataReport = amso_dataReport.entrySet().iterator();

		while(limeso_dataReport.hasNext())
		{
			Cell                      lc_cell;
			Map.Entry<String, Object> lmeso_value;
			Object                    lo_value;

			lmeso_value     = limeso_dataReport.next();
			lc_cell         = ExcelUtils.getCell(lr_row, li_cell++);
			lo_value        = lmeso_value.getValue();

			lc_cell.setCellStyle(acs_normalStyle);

			if(lo_value != null)
			{
				if(lo_value instanceof BigDecimal || lo_value instanceof BigInteger)
				{
					lc_cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					lc_cell.setCellValue(NumericUtils.getDoubleWrapper(lo_value).doubleValue());
				}
				else if(
				    lo_value instanceof Double || lo_value instanceof Float || lo_value instanceof Integer
					    || lo_value instanceof Long || lo_value instanceof Short
				)
				{
					lc_cell.setCellType(Cell.CELL_TYPE_NUMERIC);
					lc_cell.setCellValue(NumericUtils.getDoubleWrapper(lo_value).doubleValue());
				}
				else if(lo_value instanceof Date)
				{
					Date ld_value;

					ld_value = (Date)lo_value;

					lc_cell.setCellValue(ld_value);
					lc_cell.setCellStyle(acs_dateStyle);
				}
				else
				{
					lc_cell.setCellType(Cell.CELL_TYPE_STRING);
					lc_cell.setCellValue(StringUtils.getStringNormalized(lo_value.toString()));
				}
			}
			else
				lc_cell.setCellValue(new String());
		}
	}
}
