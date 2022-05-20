package com.bachue.snr.prosnr01.web.util;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import java.util.Collection;
import java.util.Map;


/**
 * Clase que contiene todos las funcionalidades para exportar archivos
 *
 * @author ccalderon
 */
public class ExportFiles implements Serializable
{
	/** Constante serialVersionUID. */
	private static final long serialVersionUID = -7632367601640979659L;

	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ExportFiles.class);

	/**
	 * Retorna el valor de XSL desde una Collection
	 *
	 * @param ac_data correspondiente al valor del tipo de objeto Collection<?>
	 * @param amss_columns correspondiente al valor del tipo de objeto Map<String,String>
	 * @return el valor de XSL from collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @throws IOException Señala que se ha producido una excepción de E / S.
	 */
	public byte[] getXSLFromCollection(Collection<?> ac_data, Map<String, String> amss_columns)
	    throws B2BException, IOException
	{
		byte[]   lba_result;
		Workbook lw_book;

		lba_result     = null;
		lw_book        = null;

		if(CollectionUtils.isValidCollection(ac_data) && CollectionUtils.isValidCollection(amss_columns))
		{
			CellStyle lcs_headerStyle;
			Row       lr_header;
			Sheet     ls_sheet;

			lw_book             = new XSSFWorkbook();
			ls_sheet            = lw_book.createSheet();
			lcs_headerStyle     = lw_book.createCellStyle();
			lr_header           = ls_sheet.createRow(0);

			lcs_headerStyle.setFillBackgroundColor(CellStyle.SOLID_FOREGROUND);

			{
				int li_count = 0;

				for(Map.Entry<String, String> lm_entry : amss_columns.entrySet())
				{
					Cell lc_cell;

					lc_cell = lr_header.createCell(li_count);

					lc_cell.setCellValue(lm_entry.getValue());
					lc_cell.setCellStyle(lcs_headerStyle);

					li_count++;
				}
			}

			{
				int li_columCounter;
				int li_rowCounter;

				li_rowCounter = 1;

				for(Object lo_object : ac_data)
				{
					Method[] lm_methods;
					Row      lr_row;

					li_columCounter     = 0;
					lm_methods          = lo_object.getClass().getMethods();
					lr_row              = ls_sheet.createRow(li_rowCounter);

					for(Map.Entry<String, String> lcs_entry : amss_columns.entrySet())
					{
						for(Method lm_method : lm_methods)
						{
							String ls_obj;

							ls_obj = "get" + lcs_entry.getKey();

							if(StringUtils.isValidString(ls_obj))
							{
								if(lm_method.getName().equalsIgnoreCase(ls_obj))
								{
									try
									{
										Object lo_tmp;

										lo_tmp = lm_method.invoke(lo_object, (Object[])null);

										if(lo_tmp != null)
										{
											if(
											    ("getAreaPredio".equalsIgnoreCase(ls_obj)
												    || "getAreaConstruida".equalsIgnoreCase(ls_obj)
												    || "getAreaPrivadaConstruida".equalsIgnoreCase(ls_obj)
												    || "getCoeficiente".equalsIgnoreCase(ls_obj))
											)
												ExcelUtils.creaCelda(
												    lr_row, li_columCounter, NumericUtils.getDouble(lo_tmp.toString()),
												    null
												);
											else
												ExcelUtils.creaCelda(lr_row, li_columCounter, lo_tmp.toString(), null);
										}

										li_columCounter++;
									}
									catch(IllegalAccessException | IllegalArgumentException | InvocationTargetException lite_e)
									{
										clh_LOGGER.error("getXSLFromCollection", lite_e);
									}
								}
							}
						}
					}

					li_rowCounter++;
				}
			}

			{
				ByteArrayOutputStream lbaos_output;

				lbaos_output = new ByteArrayOutputStream();

				ExcelUtils.write(lw_book, lbaos_output);

				lba_result = lbaos_output.toByteArray();
			}
		}
		else
			throw new B2BException(ErrorKeys.NO_SON_VALIDAS_LAS_COLECCIONES);

		return lba_result;
	}
}
