package com.bachue.snr.prosnr01.business.consulta.cuentaCupos;

import com.b2bsg.common.dataAccess2.DAOManager;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.file.excel.ExcelUtils;

import com.b2bsg.common.logging.LoggerHandler;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.business.cuentaCupos.BaseCuentaCupo;
import com.bachue.snr.prosnr01.business.utilidades.EncabezadoPdf;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;
import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.DaoCreator;
import com.bachue.snr.prosnr01.dao.DaoManagerFactory;

import com.bachue.snr.prosnr01.model.cuentaCupos.ConsultaCuentaCupos;
import com.bachue.snr.prosnr01.model.cuentaCupos.DetalleConsultaCuentaCupos;
import com.bachue.snr.prosnr01.model.sdb.acc.CuentaCupo;
import com.bachue.snr.prosnr01.model.sdb.acc.NotaCreditoCuentaCupo;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;

import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import java.math.BigDecimal;

import java.time.LocalDateTime;

import java.util.Collection;
import java.util.Date;
import java.util.Map;


/**
 * Clase para el manejo de la logica de negocio de consulta cuenta cupos.
 *
 * @author Manuel Blanco
 */
public class ConsultaCuentaCuposBusiness extends BaseCuentaCupo
{
	/** Constante clh_LOGGER. */
	private static final LoggerHandler clh_LOGGER = (LoggerHandler)com.bachue.snr.prosnr01.common.utils.LoggingHelper
			.getLogger(ConsultaCuentaCuposBusiness.class);

	/**
	 * Genera el documento de los movimientos de la cuenta cupo a consultar
	 *
	 * @param accc_datosConsulta Objeto contenedor de la información de la cuenta cupo
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @return Arreglo de bytes representando el archivo generado
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized byte[] generarDocumentoMovimientos(ConsultaCuentaCupos accc_datosConsulta, String as_userId)
	    throws B2BException
	{
		byte[]     lba_return;
		DAOManager ldm_manager;

		lba_return      = null;
		ldm_manager     = DaoManagerFactory.getDAOManager();

		try
		{
			if(accc_datosConsulta == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			ConsultaCuentaCupos lccc_movimientos;
			String              ls_tipoArchivo;

			lccc_movimientos     = obtenerMovimientos(
				    accc_datosConsulta.getIdCuentaCupo(), accc_datosConsulta.getFechaInicial(),
				    accc_datosConsulta.getFechaFinal(), ldm_manager
				);
			ls_tipoArchivo       = StringUtils.getStringNotNull(accc_datosConsulta.getTipoArchivoReporte());

			switch(ls_tipoArchivo)
			{
				case IdentificadoresCommon.EXCEL_TXT:
					lba_return = generarMovimientosExcel(lccc_movimientos, as_userId);

					break;

				case IdentificadoresCommon.PDF_TXT:
					lba_return = generarMovimientosPdf(lccc_movimientos, as_userId, ldm_manager);

					break;

				default:
					throw new B2BException(ErrorKeys.ERROR_SIN_SELECCION_TIPO_ARCHIVO);
			}

			if(lba_return == null)
				throw new B2BException(ErrorKeys.ERROR_GENERANDO_PLANTILLA);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoMovimientos", lb2be_e);

			throw lb2be_e;
		}
		catch(IOException lioe_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("generarDocumentoMovimientos", lioe_e);

			throw new B2BException(lioe_e.getLocalizedMessage());
		}
		finally
		{
			ldm_manager.close();
		}

		return lba_return;
	}

	/**
	 * Busca los datos de la ultima recarga de una cuenta cupo
	 *
	 * @param as_idCuentaCupo id de la cuenta cupo a consultar
	 * @return Objeto contenedor de lso resultados de la consulta
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	public synchronized ConsultaCuentaCupos obtenerDatosUltimaRecarga(String as_idCuentaCupo)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		ConsultaCuentaCupos lccc_movimientos;

		ldm_manager          = DaoManagerFactory.getDAOManager();
		lccc_movimientos     = null;

		try
		{
			if(!StringUtils.isValidString(as_idCuentaCupo))
				throw new B2BException(ErrorKeys.ERROR_SIN_ID_CUENTA_CUPO);

			CuentaCupo lcc_cuentaCupo;

			lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(ldm_manager).findById(as_idCuentaCupo);

			if(lcc_cuentaCupo != null)
			{
				NotaCreditoCuentaCupo lnccc_notaCredito;

				lnccc_notaCredito = DaoCreator.getNotaCreditoCuentaCupoDAO(ldm_manager)
						                          .findLatestOrOldestByIdCuentaCupo(as_idCuentaCupo);

				if(lnccc_notaCredito != null)
				{
					lccc_movimientos = new ConsultaCuentaCupos();

					lccc_movimientos.setFechaUltimaRecarga(lnccc_notaCredito.getFecha());
					lccc_movimientos.setValorUltimaRecarga(lnccc_notaCredito.getValorRecarga());
					lccc_movimientos.setSaldoActual(lcc_cuentaCupo.getSaldo());
				}
				else
					throw new B2BException(ErrorKeys.ERROR_SALDO_NO_DISPONIBLE);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTENTE);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerDatosUltimaRecarga", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_movimientos;
	}

	/**
	 * Consulta los movimientos de una cuenta cupo en un lapso determinada
	 *
	 * @param accc_datosConsulta Objeto contenedor de lso datos a utilizar en la consulta
	 * @return ConsultaCuentaCupos con los movimientos consultados
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public synchronized ConsultaCuentaCupos obtenerMovimientosCuentaCupo(ConsultaCuentaCupos accc_datosConsulta)
	    throws B2BException
	{
		DAOManager          ldm_manager;
		ConsultaCuentaCupos lccc_movimientos;

		ldm_manager = DaoManagerFactory.getDAOManager();

		try
		{
			if(accc_datosConsulta == null)
				throw new B2BException(ErrorKeys.ERROR_PARAMETROS_NO_VALIDOS);

			lccc_movimientos = obtenerMovimientos(
				    accc_datosConsulta.getIdCuentaCupo(), accc_datosConsulta.getFechaInicial(),
				    accc_datosConsulta.getFechaFinal(), ldm_manager
				);
		}
		catch(B2BException lb2be_e)
		{
			ldm_manager.setRollbackOnly();

			clh_LOGGER.error("obtenerMovimientosCuentaCupo", lb2be_e);

			throw lb2be_e;
		}
		finally
		{
			ldm_manager.close();
		}

		return lccc_movimientos;
	}

	/**
	 * Agrega una celda con un contenido especifico a una tabla
	 *
	 * @param appt_tabla Tabla a asociar la celda nueva
	 * @param af_fuente Estilo a aplicar al contenido de la celda
	 * @param ai_colspan Tamaño de la celda
	 * @param abd_contenido Contenido a agregar dentro de la celda
	 */
	private void agregarCeldaContenido(PdfPTable appt_tabla, Font af_fuente, int ai_colspan, BigDecimal abd_contenido)
	{
		if(appt_tabla != null)
		{
			StringBuilder lsb_contenido;

			lsb_contenido = new StringBuilder();

			if(abd_contenido != null)
			{
				lsb_contenido.append(IdentificadoresCommon.SIGNO_PESOS);
				lsb_contenido.append(abd_contenido);
			}

			appt_tabla.addCell(crearCeldaPdf(lsb_contenido.toString(), af_fuente, ai_colspan));
		}
	}

	/**
	 * Agrega una celda con un contenido especifico a una tabla
	 *
	 * @param appt_tabla Tabla a asociar la celda nueva
	 * @param af_fuente Estilo a aplicar al contenido de la celda
	 * @param ai_colspan Tamaño de la celda
	 * @param ad_contenido Contenido a agregar dentro de la celda
	 */
	private void agregarCeldaContenido(PdfPTable appt_tabla, Font af_fuente, int ai_colspan, Date ad_contenido)
	{
		if(appt_tabla != null)
			appt_tabla.addCell(
			    crearCeldaPdf(
			        (ad_contenido != null)
			        ? StringUtils.getString(ad_contenido, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO)
			        : IdentificadoresCommon.DATO_INVALIDO, af_fuente, ai_colspan
			    )
			);
	}

	/**
	 * Agrega una celda con un contenido especifico a una tabla
	 *
	 * @param appt_tabla Tabla a asociar la celda nueva
	 * @param af_fuente Estilo a aplicar al contenido de la celda
	 * @param ai_colspan Tamaño de la celda
	 * @param as_contenido Contenido a agregar dentro de la celda
	 */
	private void agregarCeldaContenido(PdfPTable appt_tabla, Font af_fuente, int ai_colspan, String as_contenido)
	{
		if(appt_tabla != null)
			appt_tabla.addCell(crearCeldaPdf(as_contenido, af_fuente, ai_colspan));
	}

	/**
	 * Crea una celda en una fila y le agrega un contenido
	 *
	 * @param ar_fila Objeto contenedor de la fila a la cual se le va a agregar el contenido
	 * @param acs_estilo Objeto contenedor del estilo a agregar a la celda
	 * @param ai_celda Número de la celda a crear
	 * @param abd_contenido Contenido a asociar a la celda
	 */
	private void agregarCeldaContenido(Row ar_fila, CellStyle acs_estilo, int ai_celda, BigDecimal abd_contenido)
	{
		StringBuilder lsb_contenido;

		lsb_contenido = new StringBuilder();

		if(abd_contenido != null)
		{
			lsb_contenido.append(IdentificadoresCommon.SIGNO_PESOS);
			lsb_contenido.append(abd_contenido);
		}

		agregarCeldaContenido(ar_fila, acs_estilo, ai_celda, lsb_contenido.toString());
	}

	/**
	 * Crea una celda en una fila y le agrega un contenido
	 *
	 * @param ar_fila Objeto contenedor de la fila a la cual se le va a agregar el contenido
	 * @param acs_estilo Objeto contenedor del estilo a agregar a la celda
	 * @param ai_celda Número de la celda a crear
	 * @param ad_contenido Contenido a asociar a la celda
	 */
	private void agregarCeldaContenido(Row ar_fila, CellStyle acs_estilo, int ai_celda, Date ad_contenido)
	{
		agregarCeldaContenido(
		    ar_fila, acs_estilo, ai_celda,
		    (ad_contenido != null) ? StringUtils.getString(ad_contenido, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO)
		                           : IdentificadoresCommon.DATO_INVALIDO
		);
	}

	/**
	 * Crea una celda en una fila y le agrega un contenido.
	 *
	 * @param ar_fila Objeto contenedor de la fila a la cual se le va a agregar el contenido
	 * @param acs_estilo Objeto contenedor del estilo a agregar a la celda
	 * @param ai_celda Número de la celda a crear
	 * @param as_contenido correspondiente al valor de contenido
	 */
	private void agregarCeldaContenido(Row ar_fila, CellStyle acs_estilo, int ai_celda, String as_contenido)
	{
		Cell lc_celda;

		lc_celda = ExcelUtils.getCell(ar_fila, ai_celda);

		if(acs_estilo != null)
			lc_celda.setCellStyle(acs_estilo);

		lc_celda.setCellValue(StringUtils.getStringNotNull(as_contenido));
	}

	/**
	 * Construye una celda con un contenido específico
	 *
	 * @param as_contenido Objeto contenedor de la información a agregar a la celda
	 * @param af_fuente Estilo a aplicar a la celda
	 * @param ai_colspan Tamaño de la celda
	 * @return Celda resultante del proceso
	 */
	private PdfPCell crearCeldaPdf(String as_contenido, Font af_fuente, int ai_colspan)
	{
		PdfPCell lpc_celda;

		if(af_fuente != null)
			lpc_celda = new PdfPCell(new Paragraph(StringUtils.getStringNotNull(as_contenido), af_fuente));
		else
			lpc_celda = new PdfPCell(new Paragraph(StringUtils.getStringNotNull(as_contenido)));

		lpc_celda.setColspan(ai_colspan);
		lpc_celda.setHorizontalAlignment(Element.ALIGN_CENTER);

		return lpc_celda;
	}

	/**
	 * Genera el reporte de movimientos solciitado en formato excel
	 *
	 * @param accc_movimientos Objeto contenedor de la información a ingresar en el documento
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @return Arreglo de bytes representando el archivo generado
	 * @throws B2BException Si no se cumple una regla de negocio
	 * @throws IOException Si ocurre un error generando el documento
	 */
	private byte[] generarMovimientosExcel(ConsultaCuentaCupos accc_movimientos, String as_userId)
	    throws B2BException, IOException
	{
		byte[] lba_return;

		lba_return = null;

		if(accc_movimientos != null)
		{
			int                    li_fila;
			Workbook               lw_libro;
			Sheet                  ls_hoja;
			Row                    lr_fila;
			Map<String, CellStyle> lmscs_estilos;
			CellStyle              lcs_estiloNormal;
			CellStyle              lcs_estiloTitulo;

			li_fila              = 0;
			lw_libro             = ExcelUtils.createWorkbook(true);
			ls_hoja              = ExcelUtils.getSheet(lw_libro, IdentificadoresCommon.MOVIMIENTOS_CUENTA_CUPO);
			lr_fila              = ExcelUtils.getRow(ls_hoja, li_fila++);
			lmscs_estilos        = ExcelUtils.createStyles(lw_libro, true);
			lcs_estiloNormal     = lmscs_estilos.get("normal");
			lcs_estiloTitulo     = lmscs_estilos.get("title");

			{
				int li_celdaTitulo;

				li_celdaTitulo = 0;

				agregarCeldaContenido(
				    lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.NUMERO_NOTA_CREDITO
				);
				agregarCeldaContenido(
				    lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.FECHA_MOVIMIENTO
				);
				agregarCeldaContenido(
				    lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.RECIBO_DE_CAJA
				);
				agregarCeldaContenido(lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.VALOR_RECARGA);
				agregarCeldaContenido(lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.VALOR_CONSUMO);
				agregarCeldaContenido(lr_fila, lcs_estiloTitulo, li_celdaTitulo++, IdentificadoresCommon.SALDO);
			}

			{
				Collection<DetalleConsultaCuentaCupos> lcdccc_detalleMovimientos;
				CellStyle                              lcs_estiloFecha;

				lcdccc_detalleMovimientos     = accc_movimientos.getDetalleMovimientos();
				lcs_estiloFecha               = lmscs_estilos.get("date");

				if(!CollectionUtils.isValidCollection(lcdccc_detalleMovimientos))
					throw new B2BException(ErrorKeys.ERROR_SIN_MOVIMIENTOS_CUENTA_CUPO);

				for(DetalleConsultaCuentaCupos ldccc_movimiento : lcdccc_detalleMovimientos)
				{
					if(ldccc_movimiento != null)
					{
						int li_celda;

						li_celda     = 0;
						lr_fila      = ExcelUtils.getRow(ls_hoja, li_fila++);

						agregarCeldaContenido(
						    lr_fila, lcs_estiloNormal, li_celda++, ldccc_movimiento.getIdNotaCredito()
						);
						agregarCeldaContenido(
						    lr_fila, lcs_estiloFecha, li_celda++, ldccc_movimiento.getFechaMovimiento()
						);
						agregarCeldaContenido(lr_fila, lcs_estiloNormal, li_celda++, ldccc_movimiento.getReciboCaja());
						agregarCeldaContenido(
						    lr_fila, lcs_estiloNormal, li_celda++, ldccc_movimiento.getValorRecarga()
						);
						agregarCeldaContenido(
						    lr_fila, lcs_estiloNormal, li_celda++, ldccc_movimiento.getValorConsumo()
						);
						agregarCeldaContenido(lr_fila, lcs_estiloNormal, li_celda++, ldccc_movimiento.getValorSaldo());
					}
				}
			}

			{
				int li_celda;

				li_celda     = 2;
				lr_fila      = ExcelUtils.getRow(ls_hoja, li_fila++);

				agregarCeldaContenido(lr_fila, lcs_estiloTitulo, li_celda++, IdentificadoresCommon.TOTALES_DOS_PUNTOS);
				agregarCeldaContenido(lr_fila, lcs_estiloNormal, li_celda++, accc_movimientos.getValorRecargaTotal());
				agregarCeldaContenido(lr_fila, lcs_estiloNormal, li_celda++, accc_movimientos.getValorConsumoTotal());
				agregarCeldaContenido(lr_fila, lcs_estiloNormal, li_celda++, accc_movimientos.getValorSaldoTotal());
			}

			ExcelUtils.autoAjustarTamanoColumnas(lw_libro);

			{
				ByteArrayOutputStream lbaos_output;

				lbaos_output = new ByteArrayOutputStream();

				ExcelUtils.write(lw_libro, lbaos_output);

				lba_return = lbaos_output.toByteArray();
			}
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_MOVIMIENTOS_CUENTA_CUPO);

		return lba_return;
	}

	/**
	 * Genera el reporte de movimientos solciitado en formato pdf
	 *
	 * @param accc_movimientos Objeto contenedor de la información a ingresar en el documento
	 * @param as_userId Id del usuario que ejecuta la acción
	 * @param adm_manager Conexión a la base de datos
	 * @return Arreglo de bytes representando el archivo generado
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private byte[] generarMovimientosPdf(
	    ConsultaCuentaCupos accc_movimientos, String as_userId, DAOManager adm_manager
	)
	    throws B2BException
	{
		byte[]   lba_return;
		Document ld_documento;

		lba_return       = null;
		ld_documento     = new Document(PageSize.A4, 50, 50, 100, 50);

		if(accc_movimientos == null)
			throw new B2BException(ErrorKeys.ERROR_SIN_MOVIMIENTOS_CUENTA_CUPO);

		try
		{
			ByteArrayOutputStream lbaos_ficheroPdf;
			PdfWriter             lp_writer;

			lbaos_ficheroPdf     = new ByteArrayOutputStream();
			lp_writer            = PdfWriter.getInstance(ld_documento, lbaos_ficheroPdf);

			if(lp_writer != null)
			{
				lp_writer.setInitialLeading(20);

				ld_documento.open();

				EncabezadoPdf.construirEncabezadoDocumento(lp_writer, ld_documento, adm_manager);

				final int lci_tamanioTabla   = 12;
				final int lci_tamanioColumna = 2;

				Font      lf_fontTitulos;
				Font      lf_fontTextos;
				PdfPTable lpt_tabla;

				lf_fontTitulos     = FontFactory.getFont("arial", 12, Font.BOLD);
				lf_fontTextos      = FontFactory.getFont("arial", 10);
				lpt_tabla          = new PdfPTable(lci_tamanioTabla);

				lpt_tabla.setWidthPercentage(100F);

				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioTabla, IdentificadoresCommon.MOVIMIENTOS_CUENTA_CUPO
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.NUMERO_NOTA_CREDITO
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.FECHA_MOVIMIENTO
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.RECIBO_DE_CAJA
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.VALOR_RECARGA
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.VALOR_CONSUMO
				);
				agregarCeldaContenido(lpt_tabla, lf_fontTitulos, lci_tamanioColumna, IdentificadoresCommon.SALDO);

				{
					Collection<DetalleConsultaCuentaCupos> lcdccc_detalleMovimientos;

					lcdccc_detalleMovimientos = accc_movimientos.getDetalleMovimientos();

					if(CollectionUtils.isValidCollection(lcdccc_detalleMovimientos))
					{
						for(DetalleConsultaCuentaCupos ldccc_movimiento : lcdccc_detalleMovimientos)
						{
							if(ldccc_movimiento != null)
							{
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getIdNotaCredito()
								);
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getFechaMovimiento()
								);
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getReciboCaja()
								);
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getValorRecarga()
								);
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getValorConsumo()
								);
								agregarCeldaContenido(
								    lpt_tabla, lf_fontTextos, lci_tamanioColumna, ldccc_movimiento.getValorSaldo()
								);
							}
						}
					}
				}

				agregarCeldaContenido(lpt_tabla, lf_fontTitulos, 6, IdentificadoresCommon.TOTALES_DOS_PUNTOS);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTextos, lci_tamanioColumna, accc_movimientos.getValorRecargaTotal()
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTextos, lci_tamanioColumna, accc_movimientos.getValorConsumoTotal()
				);
				agregarCeldaContenido(
				    lpt_tabla, lf_fontTextos, lci_tamanioColumna, accc_movimientos.getValorSaldoTotal()
				);

				ld_documento.add(lpt_tabla);
				ld_documento.close();

				lba_return = lbaos_ficheroPdf.toByteArray();
			}
		}
		catch(B2BException lb2be_e)
		{
			clh_LOGGER.error("generarMovimientosPdf", lb2be_e);

			ld_documento.close();

			throw lb2be_e;
		}
		catch(DocumentException lde_e)
		{
			clh_LOGGER.error("generarMovimientosPdf", lde_e);

			ld_documento.close();

			throw new B2BException(lde_e.getLocalizedMessage());
		}

		return lba_return;
	}

	/**
	 * Consulta los movimientos de una cuenta cupos en un lapso determinado
	 *
	 * @param as_idCuentaCupo id de la cuenta cupo a consultar
	 * @param ad_fechaInicial fecha inicial del rang a consultar
	 * @param ad_fechaFinal fecha final del rango a consultar
	 * @param adm_manager Conexión a la base de datos
	 * @return Objeto contenedor de los resultados de la consulta
	 * @throws B2BException Si no se cumple una regla de negocio
	 */
	private ConsultaCuentaCupos obtenerMovimientos(
	    String as_idCuentaCupo, Date ad_fechaInicial, Date ad_fechaFinal, DAOManager adm_manager
	)
	    throws B2BException
	{
		ConsultaCuentaCupos lccc_movimientos;

		lccc_movimientos = null;

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			CuentaCupo lcc_cuentaCupo;

			lcc_cuentaCupo = DaoCreator.getCuentaCupoDAO(adm_manager).findById(as_idCuentaCupo);

			if(lcc_cuentaCupo == null)
				throw new B2BException(ErrorKeys.ERROR_CUENTA_CUPO_NO_EXISTENTE);
		}
		else
			throw new B2BException(ErrorKeys.ERROR_SIN_ID_CUENTA_CUPO);

		{
			LocalDateTime                          lldt_fechaInicial;
			LocalDateTime                          lldt_fechaFinal;
			Collection<DetalleConsultaCuentaCupos> lcdccc_movimientos;

			lldt_fechaInicial     = obtenerLocalDateTime(ad_fechaInicial);
			lldt_fechaFinal       = obtenerLocalDateTime(ad_fechaFinal);

			validarFechasConsultaCuentaCupo(as_idCuentaCupo, lldt_fechaInicial, lldt_fechaFinal, adm_manager);

			lcdccc_movimientos = DaoCreator.getMaestroMovimientoCuentaCupoDAO(adm_manager)
					                           .findConsultaMovimientos(
					    as_idCuentaCupo, lldt_fechaInicial, lldt_fechaFinal
					);

			if(CollectionUtils.isValidCollection(lcdccc_movimientos))
			{
				BigDecimal lbd_totalSaldo;
				BigDecimal lbd_totalRecargas;
				BigDecimal lbd_totalConsumos;

				lbd_totalSaldo        = BigDecimal.ZERO;
				lbd_totalRecargas     = BigDecimal.ZERO;
				lbd_totalConsumos     = BigDecimal.ZERO;

				for(DetalleConsultaCuentaCupos ldccc_detalle : lcdccc_movimientos)
				{
					if(ldccc_detalle != null)
					{
						BigDecimal lbd_valorRecargaTmp;
						BigDecimal lbd_valorConsumoTmp;

						lbd_valorRecargaTmp     = ldccc_detalle.getValorRecarga();
						lbd_valorConsumoTmp     = ldccc_detalle.getValorConsumo();

						if(NumericUtils.isValidBigDecimal(lbd_valorRecargaTmp))
						{
							ldccc_detalle.setValorRecargaTxt(IdentificadoresCommon.SIGNO_PESOS + lbd_valorRecargaTmp);

							lbd_totalRecargas     = lbd_totalRecargas.add(lbd_valorRecargaTmp);

							lbd_totalSaldo = lbd_totalSaldo.add(lbd_valorRecargaTmp);
						}
						else if(NumericUtils.isValidBigDecimal(lbd_valorConsumoTmp))
						{
							ldccc_detalle.setValorConsumoTxt(IdentificadoresCommon.SIGNO_PESOS + lbd_valorConsumoTmp);

							lbd_totalConsumos     = lbd_totalConsumos.subtract(lbd_valorConsumoTmp);

							lbd_totalSaldo = lbd_totalSaldo.subtract(lbd_valorConsumoTmp);
						}

						ldccc_detalle.setValorSaldo(new BigDecimal(lbd_totalSaldo.toString()));
					}
				}

				lccc_movimientos = new ConsultaCuentaCupos();

				lccc_movimientos.setDetalleMovimientos(lcdccc_movimientos);
				lccc_movimientos.setValorSaldoTotal(lbd_totalSaldo);
				lccc_movimientos.setValorRecargaTotal(lbd_totalRecargas);
				lccc_movimientos.setValorConsumoTotal(lbd_totalConsumos);
			}
			else
				throw new B2BException(ErrorKeys.ERROR_SIN_MOVIMIENTOS_CUENTA_CUPO);
		}

		return lccc_movimientos;
	}
}
