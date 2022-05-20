package com.bachue.snr.prosnr01.dao.bgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.acc.AccAreaPredio;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.bng.DocumentoConstancia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BGN_DOCUMENTO.
 *
 * @author hcastaneda
 */
public class DocumentoDAO extends BaseDAO implements IBase<Documento>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_TIPO_OFICINA, VERSION_DOCUMENTO, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_BGN_DOCUMENTO WHERE ID_DOCUMENTO = ? ORDER BY VERSION_DOCUMENTO DESC";

	/** Constante cs_FIND_BY_ID_DOCUMENTO_VERSION. */
	private static final String cs_FIND_BY_ID_DOCUMENTO_VERSION = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_TIPO_OFICINA, VERSION_DOCUMENTO, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_BGN_DOCUMENTO WHERE ID_DOCUMENTO = ? AND VERSION_DOCUMENTO = ?";

	/** Constante cs_FIND_BY_ID_DOC_VERSION_NOMBRES. */
	private static final String cs_FIND_BY_ID_DOC_VERSION_NOMBRES = "SELECT SBD.*, SPTD.NOMBRE AS NOMBRE_DOCUMENTO,SPOO.NOMBRE AS NOMBRE_OFICINA  FROM SDB_BGN_DOCUMENTO SBD INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTD ON SPTD.ID_TIPO_DOCUMENTO = SBD.ID_TIPO_DOCUMENTO INNER JOIN SDB_PGN_OFICINA_ORIGEN SPOO ON SPOO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN WHERE SBD.ID_DOCUMENTO = ? AND SBD.VERSION_DOCUMENTO = ?";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_TIPO_OFICINA, VERSION_DOCUMENTO, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_BGN_DOCUMENTO WHERE ID_DOCUMENTO = (SELECT ID_DOCUMENTO FROM SDB_ACC_SOLICITUD WHERE ID_SOLICITUD = ? ) ORDER BY VERSION_DOCUMENTO DESC";

	/** Constante cs_FIND_MAX_VERSION. */
	private static final String cs_FIND_MAX_VERSION = "SELECT MAX(VERSION) FROM SDB_BGN_DOCUMENTO WHERE ID_DOCUMENTO = ? GROUP BY ID_DOCUMENTO";

	/** Constante cs_FIND_MAX_VERSION_DOCUMENTO. */
	private static final String cs_FIND_MAX_VERSION_DOCUMENTO = "SELECT MAX(VERSION_DOCUMENTO) FROM SDB_BGN_DOCUMENTO WHERE ID_DOCUMENTO = ? GROUP BY ID_DOCUMENTO";

	/** Constante cs_FIND_MAX_VERSION_CONSTANCIA. */
	private static final String cs_FIND_MAX_VERSION_CONSTANCIA = "SELECT MAX(SBD.VERSION_DOCUMENTO) VERSION_DOCUMENTO, SBD.ID_DOCUMENTO, SBD.NUMERO, SBD.FECHA_DOCUMENTO, SPTDP.NOMBRE NOMBRE_DOCUMENTO, NVL(SPOR.NOMBRE,SBD.COMENTARIO) NOMBRE_OFICINA  FROM SDB_BGN_DOCUMENTO SBD INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SBD.ID_TIPO_DOCUMENTO = SPTDP.ID_TIPO_DOCUMENTO LEFT OUTER JOIN SDB_PGN_OFICINA_ORIGEN SPOR ON SBD.ID_OFICINA_ORIGEN = SPOR.ID_OFICINA_ORIGEN WHERE SBD.FECHA_DOCUMENTO=? AND SBD.NUMERO=? AND SBD.ID_TIPO_DOCUMENTO=? ";

	/** Constante cs_FIND_MAX_VERSION_CONSTANCIA_BY_ID. */
	private static final String cs_FIND_MAX_VERSION_CONSTANCIA_BY_ID = "SELECT MAX(SBD.VERSION_DOCUMENTO) VERSION_DOCUMENTO, SBD.ID_DOCUMENTO, SBD.NUMERO, SBD.FECHA_DOCUMENTO, SPTDP.NOMBRE NOMBRE_DOCUMENTO, NVL(SPOR.NOMBRE,SBD.COMENTARIO) NOMBRE_OFICINA  FROM SDB_BGN_DOCUMENTO SBD INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SBD.ID_TIPO_DOCUMENTO = SPTDP.ID_TIPO_DOCUMENTO LEFT OUTER JOIN SDB_PGN_OFICINA_ORIGEN SPOR ON SBD.ID_OFICINA_ORIGEN = SPOR.ID_OFICINA_ORIGEN WHERE SBD.ID_DOCUMENTO = ? AND SBD.VERSION_DOCUMENTO= ?  GROUP BY SBD.ID_DOCUMENTO, SBD.NUMERO, SBD.FECHA_DOCUMENTO, SPTDP.NOMBRE, SPOR.NOMBRE,SBD.COMENTARIO ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_BGN_DOCUMENTO_ID_DOCUMENTO.NEXTVAL FROM DUAL";

	/** Constante cs_SELECT_DATA_BY_ID_DOCUMENTO. */
	private static final String cs_SELECT_DATA_BY_ID_DOCUMENTO = "SELECT TDP.NOMBRE || ' NÚMERO ' || D.NUMERO || ' DE ' || OO.NOMBRE || ' DE ' || M.NOMBRE DATA FROM SDB_BGN_DOCUMENTO D INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO TDP ON TDP.ID_TIPO_DOCUMENTO = D.ID_TIPO_DOCUMENTO INNER JOIN SDB_PGN_OFICINA_ORIGEN OO ON OO.ID_OFICINA_ORIGEN = D.ID_OFICINA_ORIGEN AND OO.VERSION = D.VERSION INNER JOIN SDB_PGN_MUNICIPIO M ON M.ID_PAIS = OO.ID_PAIS AND M.ID_DEPARTAMENTO = OO.ID_DEPARTAMENTO AND M.ID_MUNICIPIO = OO.ID_MUNICIPIO WHERE ID_DOCUMENTO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BGN_DOCUMENTO(ID_DOCUMENTO,VERSION_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_TIPO_OFICINA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?, ?, ?)";

	/** Constante cs_SELECT. */
	private static final String cs_SELECT = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_TIPO_OFICINA, VERSION_DOCUMENTO, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_BGN_DOCUMENTO WHERE TO_CHAR(FECHA_DOCUMENTO,'DD/MM/YYYY') = ? AND NUMERO = ? AND ID_TIPO_DOCUMENTO = ? ";

	/** Constante cs_SELECT_ANT_SISTEMA. */
	private static final String cs_SELECT_ANT_SISTEMA = "SELECT ID_DOCUMENTO, ID_TIPO_DOCUMENTO, FECHA_DOCUMENTO, NUMERO, ID_OFICINA_ORIGEN, FECHA_EJECUTORIA, COMENTARIO, OFICINA_INTERNACIONAL, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_TIPO_OFICINA, VERSION_DOCUMENTO, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_BGN_DOCUMENTO WHERE TO_CHAR(FECHA_DOCUMENTO,'DD/MM/YYYY') = ? AND NUMERO = ? AND ID_TIPO_DOCUMENTO = ? ORDER BY VERSION_DOCUMENTO DESC";

	/** Constante cs_SELECT_VALIDAR. */
	private static final String cs_SELECT_VALIDAR = "SELECT SBD.*, SPTDP.NOMBRE NOMBRE_DOCUMENTO, SPOR.NOMBRE NOMBRE_OFICINA FROM SDB_BGN_DOCUMENTO SBD INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO SPTDP ON SBD.ID_TIPO_DOCUMENTO = SPTDP.ID_TIPO_DOCUMENTO INNER JOIN SDB_PGN_OFICINA_ORIGEN SPOR ON SBD.ID_OFICINA_ORIGEN = SPOR.ID_OFICINA_ORIGEN WHERE SBD.FECHA_DOCUMENTO=? AND SBD.NUMERO=? AND SBD.ID_TIPO_DOCUMENTO=? AND SBD.ID_OFICINA_ORIGEN =? ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BGN_DOCUMENTO SET ID_TIPO_DOCUMENTO=?, FECHA_DOCUMENTO=?, NUMERO=?, ID_OFICINA_ORIGEN=?, FECHA_EJECUTORIA=?, COMENTARIO=?, OFICINA_INTERNACIONAL=?, VERSION=?, ID_USUARIO_MODIFICACION=?, FECHA_CREACION=SYSTIMESTAMP, IP_MODIFICACION = ?, ID_TIPO_OFICINA = ?, ID_PAIS = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ? WHERE ID_DOCUMENTO=? AND VERSION_DOCUMENTO = ?";

	/** Constante cs_CONSUTA_AREA_PREDIO. */
	private static final String cs_CONSUTA_AREA_PREDIO = "  SELECT BAP.COEFICIENTE, BAP.ID_AREA, NVL( BPR.ID_TIPO_USO_SUELO,' ')ID_TIPO_USO_SUELO"
		+ " FROM SDB_BNG_AREA_PREDIO  BAP LEFT JOIN SDB_BNG_PREDIO_REGISTRO BPR ON BPR.ID_CIRCULO = BAP.ID_CIRCULO AND  BPR.ID_MATRICULA = BAP.ID_MATRICULA WHERE BAP.ID_CIRCULO = ?  AND BAP.ID_MATRICULA = ?";

	/** Constante cs_DETALLE_DOCUMENTO. */
	private static final String cs_DETALLE_DOCUMENTO = "SELECT  DOC.ID_PAIS , NVL(DOC.NUMERO,' ') NUMERO,  DOC.FECHA_DOCUMENTO,  NVL(OO.NOMBRE,' ') NOMBRE_OFICINA, NVL(MUN.NOMBRE,' ') MUNICIPIO ,OO.ID_DEPARTAMENTO,OO.ID_OFICINA_ORIGEN,OO.ID_MUNICIPIO,"
		+ " DOC.ID_TIPO_DOCUMENTO,PTDP.NOMBRE AS NOMBRE_TIPO_DOCUMENTO,DOC.ID_TIPO_OFICINA,DOC.VERSION_DOCUMENTO,"
		+ " NVL(PA.NOMBRE,' ') AS NOMBRE_PAIS,NVL(DTO.NOMBRE,' ') AS NOMBRE_DEPARTAMENTO, NVL(PTO.NOMBRE,' ') AS NOMBRE_TIPO_OFICINA, NVL(PTE.NOMBRE,' ') AS NOMBRE_TIPO_ENTIDAD "
		+ " FROM SDB_BGN_DOCUMENTO DOC"
		+ " LEFT JOIN SDB_PGN_OFICINA_ORIGEN OO ON DOC.ID_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN "
		+ " LEFT JOIN SDB_PGN_DEPARTAMENTO DTO ON OO.ID_DEPARTAMENTO = DTO.ID_DEPARTAMENTO"
		+ " LEFT JOIN SDB_PGN_PAIS PA ON OO.ID_PAIS = PA.ID_PAIS"
		+ " LEFT JOIN SDB_PGN_MUNICIPIO MUN ON OO.ID_MUNICIPIO = MUN.ID_MUNICIPIO AND OO.ID_PAIS = MUN.ID_PAIS AND OO.ID_DEPARTAMENTO = MUN.ID_DEPARTAMENTO"
		+ " LEFT JOIN SDB_PGN_TIPO_OFICINA PTO ON DOC.ID_TIPO_OFICINA = PTO.ID_TIPO_OFICINA "
		+ " LEFT JOIN SDB_PGN_TIPO_ENTIDAD PTE ON PTO.ID_TIPO_ENTIDAD = PTE.ID_TIPO_ENTIDAD"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO PTDP ON PTDP.ID_TIPO_DOCUMENTO = DOC.ID_TIPO_DOCUMENTO"
		+ " WHERE DOC.ID_DOCUMENTO = ? ";

	/** Constante cs_DETALLE_OFICINA. */
	private static final String cs_DETALLE_OFICINA = " SELECT * FROM  SDB_BGN_DOCUMENTO SBD"
		+ " INNER JOIN SDB_PGN_OFICINA_ORIGEN POO ON POO.ID_OFICINA_ORIGEN = SBD.ID_OFICINA_ORIGEN AND NVL(POO.NOTIFICAR_CORRESPONDENCIA,'N') = 'S' WHERE SBD.ID_DOCUMENTO = ?";

	/** Constante cs_DOCUMENTO_CRITERIOS. */
	private static final String cs_DOCUMENTO_CRITERIOS = "SELECT * FROM SDB_BGN_DOCUMENTO WHERE ID_TIPO_DOCUMENTO =  ?  AND NUMERO = ?   ";

	/** Constante cs_VALIDAR_TIPO_OFICINA. */
	private static final String cs_VALIDAR_TIPO_OFICINA = " SELECT * FROM  SDB_BGN_DOCUMENTO  WHERE ID_DOCUMENTO = ? AND ID_TIPO_OFICINA IN (5,1,27)";

	/**
	 * Retorna el valor del objeto de tipo Consulta area predio.
	 *
	 * @param aaap_param correspondiente al valor del tipo de objeto AccAreaPredio
	 * @return devuelve el valor del objeto acc area predio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	public AccAreaPredio consultaAreaPredio(AccAreaPredio aaap_param)
	    throws B2BException
	{
		AccAreaPredio     ld_datosdocumento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ld_datosdocumento     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			int li_count;
			li_count     = 1;
			lps_ps       = getConnection().prepareStatement(cs_CONSUTA_AREA_PREDIO);

			lps_ps.setString(li_count++, aaap_param.getIdCirculo());
			setLong(lps_ps, aaap_param.getIdMatricula(), li_count++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ld_datosdocumento = getAreaPredio(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaAreaPredio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumento;
	}

	/**
	 * Retorna el valor del objeto de tipo Consulta documento.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor del objeto documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public Documento consultaDocumento(Documento ad_documento)
	    throws B2BException
	{
		Documento         ld_datosdocumento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ld_datosdocumento     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			int           li_count;
			StringBuilder lsb_sb;
			li_count     = 1;
			lsb_sb       = new StringBuilder(cs_SELECT);
			lsb_sb.append("ORDER BY VERSION_DOCUMENTO DESC");
			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(
			    li_count++, StringUtils.getString(ad_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ld_datosdocumento = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumento;
	}

	/**
	 * Retorna el valor del objeto de tipo Consulta documento validar.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return devuelve el valor del objeto documento constancia
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DocumentoConstancia
	 */
	public DocumentoConstancia consultaDocumentoValidar(DocumentoConstancia ad_documento)
	    throws B2BException
	{
		DocumentoConstancia ld_datosdocumento;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		ld_datosdocumento     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			int li_count;
			li_count     = 1;

			lps_ps = getConnection().prepareStatement(cs_SELECT_VALIDAR);

			setDate(lps_ps, ad_documento.getFechaDocumento(), li_count++);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());
			lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ld_datosdocumento = getObjetFromResultSetConstancia(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDocumentoValidar", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumento;
	}

	/**
	 * Método encargado de consultar la tabla SDB_BGN_DOCUMENTO  para una FECHA_DOCUMENTO, NUMERO, ID_TIPO_DOCUMENTO y ID_OFICINA_ORIGEN.
	 *
	 * @param ad_documento Objeto contenedor de parametros necesarios para la consulta
	 * @return Collection<Documento> coleccion de documento encontrados
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Documento> consultaDocumentos(Documento ad_documento)
	    throws B2BException
	{
		Collection<Documento> lcd_datosdocumento;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcd_datosdocumento     = new ArrayList<Documento>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int           li_count;
			StringBuilder lsb_sb;
			li_count     = 1;
			lsb_sb       = new StringBuilder(cs_SELECT);
			lsb_sb.append("AND ID_OFICINA_ORIGEN = ? ORDER BY VERSION_DOCUMENTO DESC");
			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(
			    li_count++, StringUtils.getString(ad_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());
			lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcd_datosdocumento.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDocumentos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcd_datosdocumento))
			lcd_datosdocumento = null;

		return lcd_datosdocumento;
	}

	/**
	 * Método encargado de consultar los documentos con base a la información de antiguo sistema.
	 *
	 * @param ad_documento Objeto que contiene la información del documento.
	 * @return Colección de documentos encontrados.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Documento> consultaDocumentosAntSistema(Documento ad_documento)
	    throws B2BException
	{
		Collection<Documento> lcd_datosdocumento;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		lcd_datosdocumento     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int li_count;
			li_count     = 1;
			lps_ps       = getConnection().prepareStatement(cs_SELECT_ANT_SISTEMA);

			lps_ps.setString(
			    li_count++, StringUtils.getString(ad_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());

			lrs_rs     = lps_ps.executeQuery();

			lcd_datosdocumento = new ArrayList<Documento>();

			while(lrs_rs.next())
				lcd_datosdocumento.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDocumentoAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcd_datosdocumento;
	}

	/**
	 * Metodo encargado de consultar los documentos de la tabla SDB_BGN_DOCUMENTO para una FECHA_DOCUMENTO, NUMERO y ID_TIPO_DOCUMENTO.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return Collection<DocumentoConstancia>
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DocumentoConstancia> consultaMaxDocConstancia(DocumentoConstancia ad_documento)
	    throws B2BException
	{
		Collection<DocumentoConstancia> ld_datosdocumentos;
		ld_datosdocumentos = new ArrayList<DocumentoConstancia>();

		if(ad_documento != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			String            ls_idOficinaOrigen;
			boolean           lb_oficinaOrigen;

			lps_ps                 = null;
			lrs_rs                 = null;
			ls_idOficinaOrigen     = ad_documento.getIdOficinaOrigen();
			lb_oficinaOrigen       = StringUtils.isValidString(ls_idOficinaOrigen);

			try
			{
				int           li_count;
				StringBuilder lsb_sb;

				li_count     = 1;
				lsb_sb       = new StringBuilder(cs_FIND_MAX_VERSION_CONSTANCIA);

				if(lb_oficinaOrigen)
					lsb_sb.append("AND SBD.ID_OFICINA_ORIGEN =? ");

				lsb_sb.append(
				    " GROUP BY SBD.ID_DOCUMENTO, SBD.NUMERO, SBD.FECHA_DOCUMENTO, SPTDP.NOMBRE, SPOR.NOMBRE,SBD.COMENTARIO  "
				);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setDate(lps_ps, ad_documento.getFechaDocumento(), li_count++);
				lps_ps.setString(li_count++, ad_documento.getNumero());
				lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());

				if(lb_oficinaOrigen)
					lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ld_datosdocumentos.add(getDocumentoConstancia(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaMaxDocConstancia", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(ld_datosdocumentos.isEmpty())
			ld_datosdocumentos = null;

		return ld_datosdocumentos;
	}

	/**
	 * Metodo encargado de consultar los documentos de la tabla SDB_BGN_DOCUMENTO para una ID_DOCUMENTO y VERSION_DOCUMENTO.
	 *
	 * @param ad_documento Objeto contenedor de parametros para realizar la consulta.
	 * @return Collection<DocumentoConstancia> Objeto de respuesta a la consulta.
	 * @throws B2BException Error que retorna si ocurre alguna excepción.
	 */
	public DocumentoConstancia consultaMaxDocConstanciaById(DocumentoConstancia ad_documento)
	    throws B2BException
	{
		DocumentoConstancia ld_datosdocumentos;

		ld_datosdocumentos = null;

		if(ad_documento != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_VERSION_CONSTANCIA_BY_ID);

				lps_ps.setString(li_count++, ad_documento.getIdDocumento());
				setLong(lps_ps, ad_documento.getVersionDocumento(), li_count++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_datosdocumentos = getDocumentoConstancia(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "consultaMaxDocConstanciaById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_datosdocumentos;
	}

	/**
	 * Consulta oficina doc.
	 *
	 * @param as_documento correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean consultaOficinaDoc(String as_documento)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		boolean           lb_b;

		lps_ps     = null;
		lrs_rs     = null;
		lb_b       = false;

		try
		{
			int li_count;
			li_count     = 1;
			lps_ps       = getConnection().prepareStatement(cs_DETALLE_OFICINA);

			lps_ps.setString(li_count++, as_documento);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lb_b = true;
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaOficinaDoc", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_b;
	}

	/**
	 * Retorna el valor del objeto de tipo Consultar documento criterios.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Documento> consultarDocumentoCriterios(Documento ad_documento)
	    throws B2BException
	{
		Collection<Documento> ld_datosdocumentos;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;
		StringBuilder         lsb_lsb;
		int                   li_count;
		li_count     = 1;

		ld_datosdocumentos     = new ArrayList<Documento>();
		lps_ps                 = null;
		lrs_rs                 = null;
		lsb_lsb                = new StringBuilder(cs_DOCUMENTO_CRITERIOS);

		try
		{
			if(ad_documento != null)
			{
				if(ad_documento.isFechaDocumentoValid())
					lsb_lsb = lsb_lsb.append(" AND FECHA_DOCUMENTO = ? ");

				if(ad_documento.isIdOficinaOrigenValid())
					lsb_lsb = lsb_lsb.append(" AND ID_OFICINA_ORIGEN = ? ");

				if(ad_documento.isTipoOficinaValid())
					lsb_lsb = lsb_lsb.append(" AND ID_TIPO_OFICINA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_lsb.toString());

				lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());
				lps_ps.setString(li_count++, ad_documento.getNumero());

				if(ad_documento.isFechaDocumentoValid())
					lps_ps.setDate(li_count++, DateUtils.getSQLDate(ad_documento.getFechaDocumento()));

				if(ad_documento.isIdOficinaOrigenValid())
					lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

				if(ad_documento.isTipoOficinaValid())
					lps_ps.setString(li_count++, ad_documento.getIdTipoOficina());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					ld_datosdocumentos.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarDocumentoCriterios", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumentos;
	}

	/**
	 * Retorna el valor del objeto de tipo Consultar documentos.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<Documento> consultarDocumentos(Documento ad_documento)
	    throws B2BException
	{
		Collection<Documento> ld_datosdocumentos;
		PreparedStatement     lps_ps;
		ResultSet             lrs_rs;

		ld_datosdocumentos     = new ArrayList<Documento>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int           li_count;
			StringBuilder lsb_sb;
			li_count     = 1;
			lsb_sb       = new StringBuilder(cs_SELECT);
			lsb_sb.append("AND ID_OFICINA_ORIGEN = ? ORDER BY VERSION_DOCUMENTO DESC");
			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(
			    li_count++, StringUtils.getString(ad_documento.getFechaDocumento(), FormatoFechaCommon.DIA_MES_ANIO)
			);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());
			lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ld_datosdocumentos.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarDocumentos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumentos;
	}

	/**
	 * Retorna el valor del objeto de tipo Consultar documentos constancia.
	 *
	 * @param ad_documento correspondiente al valor del tipo de objeto DocumentoConstancia
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<DocumentoConstancia> consultarDocumentosConstancia(DocumentoConstancia ad_documento)
	    throws B2BException
	{
		Collection<DocumentoConstancia> ld_datosdocumentos;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		ld_datosdocumentos     = new ArrayList<DocumentoConstancia>();
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			int li_count;
			li_count     = 1;

			lps_ps = getConnection().prepareStatement(cs_SELECT_VALIDAR);

			setDate(lps_ps, ad_documento.getFechaDocumento(), li_count++);
			lps_ps.setString(li_count++, ad_documento.getNumero());
			lps_ps.setString(li_count++, ad_documento.getIdTipoDocumento());
			lps_ps.setString(li_count++, ad_documento.getIdOficinaOrigen());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ld_datosdocumentos.add(getObjetFromResultSetConstancia(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultarDocumentosConstancia", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumentos;
	}

	/**
	 * Retorna el valor del objeto de tipo Detalle documento.
	 *
	 * @param as_idDocumento correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto registro calificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroCalificacion detalleDocumento(String as_idDocumento)
	    throws B2BException
	{
		return detalleDocumento(as_idDocumento, null);
	}

	/**
	 * Retorna el valor del objeto de tipo Detalle documento.
	 *
	 * @param as_idDocumento correspondiente al valor del tipo de objeto String
	 * @param as_versionDocumento correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto registro calificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public RegistroCalificacion detalleDocumento(String as_idDocumento, String as_versionDocumento)
	    throws B2BException
	{
		RegistroCalificacion ld_datosdocumento;
		PreparedStatement    lps_ps;
		ResultSet            lrs_rs;

		ld_datosdocumento     = null;
		lps_ps                = null;
		lrs_rs                = null;

		try
		{
			int           li_count;
			StringBuilder lsb_sb;

			li_count     = 1;
			lsb_sb       = new StringBuilder(cs_DETALLE_DOCUMENTO);

			if(StringUtils.isValidString(as_versionDocumento))
				lsb_sb.append(" AND DOC.VERSION_DOCUMENTO = ? ");

			lsb_sb.append(" ORDER BY  DOC.VERSION_DOCUMENTO DESC");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_count++, as_idDocumento);

			if(StringUtils.isValidString(as_versionDocumento))
				lps_ps.setString(li_count++, as_versionDocumento);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ld_datosdocumento = getDetalleDocumento(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "detalleDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_datosdocumento;
	}

	/**
	 * Método encargado de buscar un documento por su id.
	 *
	 * @param as_idDocumento Argumento de tipo <code>Documento</code> que contiene el documento de la operación.
	 * @return Variable de tipo <code>Documento</code> con el valor de documento.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findById(Documento ad_param)
	    throws B2BException
	{
		Documento ld_return;

		ld_return = null;

		if(ad_param != null)
		{
			String ls_idDocumento;

			ls_idDocumento     = ad_param.getIdDocumento();

			ld_return = findById(ls_idDocumento);
		}

		return ld_return;
	}

	/**
	 * Método encargado de buscar un documento por su id.
	 *
	 * @param as_idDocumento Argumento de tipo <code>String</code> que contiene el documento de la operación.
	 * @return Variable de tipo <code>Documento</code> con el valor de documento.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findById(String as_idDocumento)
	    throws B2BException
	{
		Documento         ld_documento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ld_documento     = null;
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idDocumento))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_idDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_documento = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_documento;
	}

	/**
	 * Obtiene un documento asociado a un id y version.
	 *
	 * @param ad_param id asociada al documento a buscar
	 * @return Documento resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Documento findByIdDocumentoVersion(Documento ad_param)
	    throws B2BException
	{
		return (ad_param != null) ? findByIdDocumentoVersion(ad_param.getIdDocumento(), ad_param.getVersionDocumento())
		                          : null;
	}

	/**
	 * Obtiene un documento asociado a un id y version.
	 *
	 * @param as_idDocumento id asociada al documento a buscar
	 * @param al_versionDoc version del documento a buscar
	 * @return Documento resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Documento findByIdDocumentoVersion(String as_idDocumento, Long al_versionDoc)
	    throws B2BException
	{
		Documento ld_documento;

		ld_documento = null;

		if(StringUtils.isValidString(as_idDocumento) && NumericUtils.isValidLong(al_versionDoc))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DOCUMENTO_VERSION);

				lps_ps.setString(li_contador++, as_idDocumento);
				setLong(lps_ps, al_versionDoc, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_documento = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumentoVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_documento;
	}

	/**
	 * Find by id documento version nombres.
	 *
	 * @param at_param de at param
	 * @return el valor de documento
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Documento findByIdDocumentoVersionNombres(Documento at_param)
	    throws B2BException
	{
		return (at_param != null)
		? findByIdDocumentoVersionNombres(
		    at_param.getIdDocumento(), NumericUtils.getLong(at_param.getVersionDocumento())
		) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo Documento filtrado por ID documento y version documento.
	 *
	 * @param as_idDocumento de as id documento
	 * @param abd_versionDocumento de abd version documento
	 * @return devuelve el valor del objeto documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public Documento findByIdDocumentoVersionNombres(String as_idDocumento, long abd_versionDocumento)
	    throws B2BException
	{
		Documento ld_documento;

		ld_documento = null;

		if(StringUtils.isValidString(as_idDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_DOC_VERSION_NOMBRES);

				lps_ps.setString(li_contador++, as_idDocumento);
				lps_ps.setLong(li_contador++, abd_versionDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ld_documento = getObjetFromResultSetNombres(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdDocumentoVersionNombres", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ld_documento;
	}

	/**
	 * Método encargado de buscar la información de un documento por su id.
	 *
	 * @param as_idDocumento Argumento de tipo <code>String</code> que contiene el documento de la operación.
	 * @return Variable de tipo <code>String</code> con el valor de la data del documento.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public String findDataById(String as_idDocumento)
	    throws B2BException
	{
		String            ls_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_return     = null;
		lps_ps        = null;
		lrs_rs        = null;

		if(StringUtils.isValidString(as_idDocumento))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_SELECT_DATA_BY_ID_DOCUMENTO);

				lps_ps.setString(1, as_idDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = lrs_rs.getString("DATA");
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Documento filtrado por el Id de la solicitud.
	 *
	 * @param idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto documento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Documento
	 */
	public Documento findDocumentoByIdSolicitud(String idSolicitud)
	    throws B2BException
	{
		Documento         ld_documento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ld_documento     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			int contador;
			contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

			lps_ps.setString(contador++, idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ld_documento = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ld_documento;
	}

	/**
	 * Retorna el valor maximo del id filtrado por id documento.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Long findMaxVersionByIdDocumento(Documento at_param)
	    throws B2BException
	{
		Long              ll_documento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_documento     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_MAX_VERSION);

			lps_ps.setString(1, at_param.getIdDocumento());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_documento = getLong(lrs_rs, 1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMaxVersionByIdDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ll_documento;
	}

	/**
	 * Retorna el valor de la maxima version de la version del documento.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto Documento
	 * @return devuelve el valor del objeto long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Long findMaxVersionDocumentoByIdDocumento(Documento at_param)
	    throws B2BException
	{
		Long              ll_documento;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_documento     = null;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_MAX_VERSION_DOCUMENTO);

			lps_ps.setString(1, at_param.getIdDocumento());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_documento = getLong(lrs_rs, 1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findMaxVersionByIdDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ll_documento;
	}

	/**
	 * Retorna el valor de la secuencia actual en la tabla.
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuencia()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCIA);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 *  {@inheritdoc}.
	 *
	 * @param ad_param de ad param
	 * @param ab_query de ab query
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	@Override
	public void insertOrUpdate(Documento ad_param, boolean ab_query)
	    throws B2BException
	{
		if(ad_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;
				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdDocumento());
					setLong(lps_ps, ad_param.getVersionDocumento(), li_column++);
				}

				lps_ps.setString(li_column++, ad_param.getIdTipoDocumento());
				setDate(lps_ps, ad_param.getFechaDocumento(), li_column++);
				lps_ps.setString(li_column++, ad_param.getNumero());
				lps_ps.setString(li_column++, ad_param.getIdOficinaOrigen());
				setDate(lps_ps, ad_param.getFechaEjecutoria(), li_column++);
				lps_ps.setString(li_column++, ad_param.getComentario());
				lps_ps.setString(li_column++, ad_param.getOficinaInternacional());
				lps_ps.setBigDecimal(li_column++, ad_param.getVersion());

				if(ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ad_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, ad_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, ad_param.getIpModificacion());
				}

				lps_ps.setString(li_column++, ad_param.getIdTipoOficina());
				lps_ps.setString(li_column++, ad_param.getIdPais());
				lps_ps.setString(li_column++, ad_param.getIdDepartamento());
				lps_ps.setString(li_column++, ad_param.getIdMunicipio());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, ad_param.getIdDocumento());
					setLong(lps_ps, ad_param.getVersionDocumento(), li_column++);
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Validar tipo oficina.
	 *
	 * @param as_documento correspondiente al valor del tipo de objeto String
	 * @return verdadero, si se cumple la condicion, de lo contario retorna falso
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean validarTipoOficina(String as_documento)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		boolean           lb_b;

		lps_ps     = null;
		lrs_rs     = null;
		lb_b       = false;

		try
		{
			int li_count;
			li_count     = 1;
			lps_ps       = getConnection().prepareStatement(cs_VALIDAR_TIPO_OFICINA);

			lps_ps.setString(li_count++, as_documento);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lb_b = true;
		}
		catch(SQLException lse_e)
		{
			logError(this, "validarTipoOficina", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lb_b;
	}

	/**
	 * Retorna el valor de area predio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de area predio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AccAreaPredio
	 */
	private AccAreaPredio getAreaPredio(ResultSet ars_rs)
	    throws SQLException
	{
		AccAreaPredio lms_areaPredio;

		lms_areaPredio = new AccAreaPredio();

		lms_areaPredio.setCoeficiente(getDouble(ars_rs, "COEFICIENTE"));
		lms_areaPredio.setTipoSuelo(ars_rs.getString("ID_TIPO_USO_SUELO"));
		lms_areaPredio.setIdArea(ars_rs.getString("ID_AREA"));

		return lms_areaPredio;
	}

	/**
	 * Retorna el valor de detalle documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de detalle documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	private RegistroCalificacion getDetalleDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		RegistroCalificacion lrc_return;

		lrc_return = new RegistroCalificacion();

		lrc_return.setCodDocumento(ars_rs.getString("NUMERO"));
		lrc_return.setNombreOficina(ars_rs.getString("NOMBRE_OFICINA"));
		lrc_return.setNombreMunicipio(ars_rs.getString("MUNICIPIO"));
		lrc_return.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		lrc_return.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lrc_return.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		lrc_return.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lrc_return.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		lrc_return.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		lrc_return.setVersion(getLong(ars_rs, "VERSION_DOCUMENTO"));
		lrc_return.setIdPais(ars_rs.getString("ID_PAIS"));
		lrc_return.setNombreTipoDoc(ars_rs.getString("NOMBRE_TIPO_DOCUMENTO"));
		lrc_return.setNombrePais(ars_rs.getString("NOMBRE_PAIS"));
		lrc_return.setNombreDepartamento(ars_rs.getString("NOMBRE_DEPARTAMENTO"));
		lrc_return.setNombreTipoOficina(ars_rs.getString("NOMBRE_TIPO_OFICINA"));
		lrc_return.setNombreTipoEntidad(ars_rs.getString("NOMBRE_TIPO_ENTIDAD"));

		return lrc_return;
	}

	/**
	 * Retorna el valor de documento constancia.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de documento constancia
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DocumentoConstancia
	 */
	private DocumentoConstancia getDocumentoConstancia(ResultSet ars_rs)
	    throws SQLException
	{
		DocumentoConstancia ld_documento;

		ld_documento = new DocumentoConstancia();

		ld_documento.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ld_documento.setNumero(ars_rs.getString("NUMERO"));
		ld_documento.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		ld_documento.setNombreTipoDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		ld_documento.setNombreOficinaOrigen(ars_rs.getString("NOMBRE_OFICINA"));
		ld_documento.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));

		return ld_documento;
	}

	/**
	 * Retorna el valor de Documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Documento
	 */
	private Documento getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		Documento ld_documento;

		ld_documento = new Documento();

		ld_documento.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ld_documento.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		ld_documento.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		ld_documento.setNumero(ars_rs.getString("NUMERO"));
		ld_documento.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ld_documento.setFechaEjecutoria(ars_rs.getTimestamp("FECHA_EJECUTORIA"));
		ld_documento.setComentario(ars_rs.getString("COMENTARIO"));
		ld_documento.setOficinaInternacional(ars_rs.getString("OFICINA_INTERNACIONAL"));
		ld_documento.setVersion(getBigDecimal(ars_rs, "VERSION"));
		ld_documento.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_documento.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_documento.setIdPais(ars_rs.getString("ID_PAIS"));
		ld_documento.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ld_documento.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		ld_documento.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ld_documento.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));
		ld_documento.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_documento.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_documento.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_documento.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return ld_documento;
	}

	/**
	 * Retorna el valor de DocumentoConstancia.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de DocumentoConstancia
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DocumentoConstancia
	 */
	private DocumentoConstancia getObjetFromResultSetConstancia(ResultSet ars_rs)
	    throws SQLException
	{
		DocumentoConstancia ld_documento;

		ld_documento = new DocumentoConstancia();

		ld_documento.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ld_documento.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		ld_documento.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		ld_documento.setNumero(ars_rs.getString("NUMERO"));
		ld_documento.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ld_documento.setFechaEjecutoria(ars_rs.getTimestamp("FECHA_EJECUTORIA"));
		ld_documento.setComentario(ars_rs.getString("COMENTARIO"));
		ld_documento.setOficinaInternacional(ars_rs.getString("OFICINA_INTERNACIONAL"));
		ld_documento.setVersion(getBigDecimal(ars_rs, "VERSION"));
		ld_documento.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_documento.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_documento.setIdPais(ars_rs.getString("ID_PAIS"));
		ld_documento.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ld_documento.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		ld_documento.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ld_documento.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));
		ld_documento.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_documento.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_documento.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_documento.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_documento.setNombreTipoDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		ld_documento.setNombreOficinaOrigen(ars_rs.getString("NOMBRE_OFICINA"));

		return ld_documento;
	}

	/**
	 * Retorna el valor de Documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de Documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see Documento
	 */
	private Documento getObjetFromResultSetNombres(ResultSet ars_rs)
	    throws SQLException
	{
		Documento ld_documento;

		ld_documento = new Documento();

		ld_documento.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ld_documento.setIdTipoDocumento(ars_rs.getString("ID_TIPO_DOCUMENTO"));
		ld_documento.setFechaDocumento(ars_rs.getTimestamp("FECHA_DOCUMENTO"));
		ld_documento.setNumero(ars_rs.getString("NUMERO"));
		ld_documento.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ld_documento.setFechaEjecutoria(ars_rs.getTimestamp("FECHA_EJECUTORIA"));
		ld_documento.setComentario(ars_rs.getString("COMENTARIO"));
		ld_documento.setOficinaInternacional(ars_rs.getString("OFICINA_INTERNACIONAL"));
		ld_documento.setVersion(getBigDecimal(ars_rs, "VERSION"));
		ld_documento.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ld_documento.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ld_documento.setIdPais(ars_rs.getString("ID_PAIS"));
		ld_documento.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ld_documento.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		ld_documento.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ld_documento.setVersionDocumento(getLong(ars_rs, "VERSION_DOCUMENTO"));
		ld_documento.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ld_documento.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ld_documento.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ld_documento.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ld_documento.setNombreTipoDocumento(ars_rs.getString("NOMBRE_DOCUMENTO"));
		ld_documento.setNombreOficinaOrigen(ars_rs.getString("NOMBRE_OFICINA"));

		return ld_documento;
	}
}
