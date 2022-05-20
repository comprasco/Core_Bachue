package com.bachue.snr.prosnr01.dao.bng;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Matricula;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.dao.IBase;
import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.registro.Direccion;
import com.bachue.snr.prosnr01.model.sdb.bng.PredioRegistro;

import oracle.jdbc.OracleTypes;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_PREDIO_REGISTRO.
 *
 * @author lchacon
 */
public class PredioRegistroDAO extends AuditoriaDao implements IBase<PredioRegistro>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT BPR.ID_CIRCULO, BPR.ID_MATRICULA, BPR.ID_ZONA_REGISTRAL, NVL(BPR.NUPRE,'')NUPRE, BPR.NUMERO_PREDIAL, BPR.NUMERO_PREDIAL_ANT,"
		+ " BPR.FOLIO_GRABACION, BPR.ID_TIPO_PREDIO, BPR.ID_TIPO_USO_SUELO, BPR.ID_DOCUMENTO, BPR.RADICACION, BPR.PREDIO_DEFINITIVO, BPR.ID_ESTADO_PREDIO, "
		+ "	BPR.FECHA_APERTURA, BPR.ANOTACION_CIERRE, BPR.ID_COMPLEMENTACION, BPR.COMENTARIO, BPR.ID_DATOS_ANT_SISTEMA, BPR.TURNO_BLOQUEO, BPR.NUPRE, "
		+ " NVL(BPR.PREDIO_INCONSISTENTE,'N')PREDIO_INCONSISTENTE,BPR.ID_ESTADO_NUPRE,BPR.VERSION_DOCUMENTO,BPR.NOMBRE_PREDIO,BPR.VALOR_AVALUO,BPR.FECHA_AVALUO, BPR.ID_USUARIO_CREACION, BPR.FECHA_CREACION,BPR.IP_CREACION,BPR.ID_USUARIO_MODIFICACION,BPR.FECHA_MODIFICACION,BPR.IP_MODIFICACION, UPPER (NVL(PEP.NOMBRE,'ACTIVO')) NOMBRE"
		+ " FROM SDB_BNG_PREDIO_REGISTRO BPR  LEFT JOIN SDB_PNG_ESTADO_PREDIO PEP ON  PEP.ID_ESTADO_PREDIO = BPR.ID_ESTADO_PREDIO ";

	/** Constante cs_UPDATE_ID_ESTADO_PREDIO. */
	private static final String cs_UPDATE_ID_ESTADO_PREDIO = "UPDATE SDB_BNG_PREDIO_REGISTRO SET ID_ESTADO_PREDIO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA= ? ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BNG_PREDIO_REGISTRO SET ID_ZONA_REGISTRAL = ?, NUPRE = ?, NUMERO_PREDIAL = ?, "
		+ "NUMERO_PREDIAL_ANT = ?, FOLIO_GRABACION = ?, ID_TIPO_PREDIO = ?, ID_TIPO_USO_SUELO = ?, ID_DOCUMENTO = ?, RADICACION = ?, "
		+ "PREDIO_DEFINITIVO = ?, ID_ESTADO_PREDIO = ?, FECHA_APERTURA = ?, ANOTACION_CIERRE = ?, ID_COMPLEMENTACION = ?, COMENTARIO = ?, "
		+ "ID_DATOS_ANT_SISTEMA = ?, TURNO_BLOQUEO = ?, PREDIO_INCONSISTENTE = ?, ID_USUARIO_MODIFICACION = ?, FECHA_CREACION = SYSTIMESTAMP, IP_MODIFICACION=? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_BY_FOLIO_MATR. */
	private static final String cs_FIND_BY_FOLIO_MATR = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_NUPRE. */
	private static final String cs_FIND_BY_NUPRE = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE NUPRE = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BNG_PREDIO_REGISTRO (ID_CIRCULO,ID_MATRICULA,ID_ZONA_REGISTRAL,NUPRE,NUMERO_PREDIAL,"
		+ "NUMERO_PREDIAL_ANT,FOLIO_GRABACION,ID_TIPO_PREDIO,ID_TIPO_USO_SUELO,ID_DOCUMENTO,RADICACION,PREDIO_DEFINITIVO,ID_ESTADO_PREDIO,FECHA_APERTURA,"
		+ "ANOTACION_CIERRE,ID_COMPLEMENTACION,COMENTARIO,ID_DATOS_ANT_SISTEMA,TURNO_BLOQUEO,PREDIO_INCONSISTENTE,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE_CUARTO_ORDEN. */
	private static final String cs_UPDATE_CUARTO_ORDEN = "UPDATE SDB_BNG_PREDIO_REGISTRO SET VALOR_AVALUO = ?, FECHA_AVALUO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA= ? ";

	/** Constante cs_UPDATE_QUINTO_ORDEN. */
	private static final String cs_UPDATE_QUINTO_ORDEN = "UPDATE SDB_BNG_PREDIO_REGISTRO SET NUMERO_PREDIAL = ?, NUMERO_PREDIAL_ANT = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA= ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_BY_PERSONA_PROPIETARIO = "SELECT PR.* FROM SDB_BNG_PREDIO_REGISTRO PR INNER JOIN (SELECT DISTINCT BP.ID_CIRCULO, BP.ID_MATRICULA FROM SDB_BNG_PREDIO_REGISTRO BPR INNER JOIN SDB_BNG_PROPIETARIOS BP ON BP.ID_CIRCULO = BPR.ID_CIRCULO AND BP.ID_MATRICULA = BPR.ID_MATRICULA AND BP.ESTADO = 'A' WHERE BP.ID_PERSONA = ? ) DATA1 ON PR.ID_CIRCULO = DATA1.ID_CIRCULO AND PR.ID_MATRICULA = DATA1.ID_MATRICULA";

	/** Constante cs_FIND_DOCUMENTO. */
	private static final String cs_FIND_DOCUMENTO = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_DOCUMENTO = ? AND ID_CIRCULO = ? ";

	/** Constante cs_FIND_BY_ANT_SISTEMA. */
	private static final String cs_FIND_BY_ANT_SISTEMA = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_DATOS_ANT_SISTEMA = ? ";

	/** Constante cs_FIND_BY_ANT_SISTEMA_CALIFICACION. */
	private static final String cs_FIND_BY_ANT_SISTEMA_CALIFICACION = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_DATOS_ANT_SISTEMA = ? ";

	/** Constante CS_CONSULTA_DATOS_REGISTRALES. */
	private static final String CS_CONSULTA_DATOS_REGISTRALES = "SELECT SBPR.ID_CIRCULO || '-' || SBPR.ID_MATRICULA MATRICULA,SPCR.ID_CIRCULO CODIGO_ORIP,SPCR.NOMBRE NOMBRE_ORIP,NVL(SBPR.NUMERO_PREDIAL,' ') NUMERO_PREDIAL,SPEN.NOMBRE ESTADO_NUPRE,NVL(SBPR.NUPRE,' ') NUPRE,SBDP.DIRECCION_COMPLETA,SPD.NOMBRE DEPARTAMENTO,SPM.NOMBRE MUNICIPIO,SCPT.ILICODE GRUPO FROM SDB_BNG_PREDIO_REGISTRO SBPR INNER JOIN SDB_PGN_CIRCULO_REGISTRAL SPCR ON SPCR.ID_CIRCULO = SBPR.ID_CIRCULO INNER JOIN SDB_PGN_ZONA_REGISTRAL SPZR ON SPZR.ID_ZONA_REGISTRAL = SBPR.ID_ZONA_REGISTRAL INNER JOIN SDB_PGN_DEPARTAMENTO SPD ON SPD.ID_DEPARTAMENTO = SPZR.ID_DEPARTAMENTO INNER JOIN SDB_PGN_MUNICIPIO SPM ON SPM.ID_DEPARTAMENTO = SPD.ID_DEPARTAMENTO AND SPM.ID_MUNICIPIO = SPZR.ID_MUNICIPIO INNER JOIN SDB_COL_PREDIO_TIPO SCPT ON SCPT.ID_TIPO_PREDIO = SBPR.ID_TIPO_PREDIO INNER JOIN SDB_BNG_DIRECCION_PREDIO SBDP ON SBDP.ID_CIRCULO = SBPR.ID_CIRCULO AND SBDP.ID_MATRICULA = SBPR.ID_MATRICULA LEFT JOIN SDB_PNG_ESTADO_NUPRE SPEN ON SPEN.ID_ESTADO_NUPRE = SBPR.ID_ESTADO_NUPRE WHERE SBPR.ID_CIRCULO = ? AND SBPR.ID_MATRICULA = ?";

	/** Constante CS_CONSULTA_DATOS_REGISTRALES. */
	private static final String CS_CONSULTA_DATOS_REGISTRALES_BY_DATA_DIRECCION = "SELECT SBPR.ID_CIRCULO, SBPR.ID_MATRICULA FROM SDB_BNG_PREDIO_REGISTRO SBPR INNER JOIN SDB_PGN_ZONA_REGISTRAL SPZR ON SPZR.ID_ZONA_REGISTRAL = SBPR.ID_ZONA_REGISTRAL INNER JOIN SDB_PGN_DEPARTAMENTO SPD ON SPD.ID_PAIS = SPZR.ID_PAIS AND SPD.ID_DEPARTAMENTO = SPZR.ID_DEPARTAMENTO INNER JOIN SDB_PGN_MUNICIPIO SPM ON SPM.ID_PAIS = SPZR.ID_PAIS AND SPM.ID_DEPARTAMENTO = SPZR.ID_DEPARTAMENTO AND SPM.ID_MUNICIPIO = SPZR.ID_MUNICIPIO INNER JOIN SDB_BNG_DIRECCION_PREDIO SBDP ON SBDP.ID_CIRCULO = SBPR.ID_CIRCULO AND SBDP.ID_MATRICULA = SBPR.ID_MATRICULA WHERE SPZR.ID_PAIS = ? AND SPZR.ID_DEPARTAMENTO = ? AND SPZR.ID_MUNICIPIO = ? AND SBDP.ID_TIPO_EJE_PRINCIPAL = ? AND SBDP.DATO_EJE_PRINCIPAL = ?";

	/** Constante CS_CONSULTA_DATOS_REGISTRALES_BY_DIRECCION_COMPLETA. */
	private static final String CS_CONSULTA_DATOS_REGISTRALES_BY_DIRECCION_COMPLETA = "SELECT SBPR.ID_CIRCULO, SBPR.ID_MATRICULA FROM SDB_BNG_PREDIO_REGISTRO SBPR INNER JOIN SDB_PGN_ZONA_REGISTRAL SPZR ON SPZR.ID_ZONA_REGISTRAL = SBPR.ID_ZONA_REGISTRAL INNER JOIN SDB_PGN_DEPARTAMENTO SPD ON SPD.ID_PAIS = SPZR.ID_PAIS AND SPD.ID_DEPARTAMENTO = SPZR.ID_DEPARTAMENTO INNER JOIN SDB_PGN_MUNICIPIO SPM ON SPM.ID_PAIS = SPZR.ID_PAIS AND SPM.ID_DEPARTAMENTO = SPZR.ID_DEPARTAMENTO AND SPM.ID_MUNICIPIO = SPZR.ID_MUNICIPIO INNER JOIN SDB_BNG_DIRECCION_PREDIO SBDP ON SBDP.ID_CIRCULO = SBPR.ID_CIRCULO AND SBDP.ID_MATRICULA = SBPR.ID_MATRICULA WHERE SBDP.DIRECCION_COMPLETA = ? ";

	/** Constante cs_FIND_BY_NUMERO_PREDIAL. */
	private static final String cs_FIND_BY_NUMERO_PREDIAL = "SELECT ID_CIRCULO,ID_MATRICULA,ID_ZONA_REGISTRAL,NUPRE,NUMERO_PREDIAL,NUMERO_PREDIAL_ANT,"
		+ "FOLIO_GRABACION,ID_TIPO_PREDIO,ID_TIPO_USO_SUELO,ID_DOCUMENTO,RADICACION,PREDIO_DEFINITIVO,ID_ESTADO_PREDIO,FECHA_APERTURA,ANOTACION_CIERRE,"
		+ "ID_COMPLEMENTACION,COMENTARIO,ID_DATOS_ANT_SISTEMA,TURNO_BLOQUEO,PREDIO_INCONSISTENTE,FECHA_CREACION,ID_ESTADO_NUPRE,VERSION_DOCUMENTO,"
		+ "ID_USUARIO_CREACION,IP_CREACION,ID_USUARIO_MODIFICACION,FECHA_MODIFICACION,IP_MODIFICACION,NOMBRE_PREDIO FROM SDB_BNG_PREDIO_REGISTRO WHERE NUMERO_PREDIAL = ?";

	/** Constante cs_FIND_BY_ID_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_ID_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_PREDIO_REGISTRO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_FIND_ALL_BY_PERSONA. */
	private static final String cs_FIND_ALL_BY_PERSONA = "SELECT DISTINCT PR.ID_CIRCULO, PR.ID_MATRICULA, PR.* FROM SDB_BNG_PREDIO_REGISTRO PR INNER JOIN SDB_BNG_PROPIETARIOS PROP ON PROP.ID_CIRCULO = PR.ID_CIRCULO AND PROP.ID_MATRICULA = PR.ID_MATRICULA";

	/** Constante cs_FIND_ALL_BY_DIRECCION_COMPLETA. */
	private static final String cs_FIND_ALL_BY_DIRECCION_COMPLETA = "SELECT PR.* FROM SDB_BNG_DIRECCION_PREDIO DP INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON DP.ID_CIRCULO = PR.ID_CIRCULO AND DP.ID_MATRICULA = PR.ID_MATRICULA";

	/**
	 * Método encargado de realizar la consulta datos registrales.
	 *
	 * @param as_idCirculo Variable que contiene el id circulo del predio
	 * @param al_idMatricula Variable que contiene el id matricula del predio
	 * @return Objeto que contiene la información del predio.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Matricula consultaDatosRegistrales(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Matricula         lm_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lm_return     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			if(NumericUtils.isValidLong(al_idMatricula) && StringUtils.isValidString(as_idCirculo))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(CS_CONSULTA_DATOS_REGISTRALES);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lm_return = getMatriculaDatosRegistrales(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDatosRegistrales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lm_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioRegistro con todos los registros.
	 *
	 * @return devuelve el valor del objeto collection de PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public Collection<PredioRegistro> findAll()
	    throws B2BException
	{
		Collection<PredioRegistro> lccr_ccr;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lccr_ccr     = new ArrayList<PredioRegistro>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccr_ccr.add(getObjetFromResultSet(lrs_rs, false));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lccr_ccr))
			lccr_ccr = null;

		return lccr_ccr;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de PredioRegistro filtrado por el turno bloqueo.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param aldt_fechaInicio de aldt fecha inicio
	 * @param aldt_fechaFin de aldt fecha fin
	 * @return devuelve el valor del objeto collection de PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public Collection<PredioRegistro> findAllByCirculoRangoFechas(
	    String as_idCirculo, Date aldt_fechaInicio, Date aldt_fechaFin
	)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if((as_idCirculo != null) && (aldt_fechaInicio != null) && (aldt_fechaFin != null))
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_FIND_BY_ID);

				lsb_sb.append(
				    "WHERE BPR.ID_CIRCULO = ? AND BPR.FECHA_CREACION BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND EXISTS(SELECT 1 FROM SDB_BNG_PREDIO_SEGREGADO PS WHERE PS.ID_CIRCULO1 = BPR.ID_CIRCULO AND PS.ID_MATRICULA1 = BPR.ID_MATRICULA) AND EXISTS ( SELECT 1 FROM SDB_BNG_ANOTACION_PREDIO AP INNER JOIN SDB_PNG_NATURALEZA_JURIDICA N ON N.ID_NATURALEZA_JURIDICA = AP.ID_NATURALEZA_JURIDICA AND N.VERSION = AP.VERSION INNER JOIN SDB_COL_TIPO_RRR R ON R.ID_TIPO_RRR = N.ID_TIPO_RRR WHERE BPR.ID_CIRCULO = AP.ID_CIRCULO )"
				);

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_idCirculo);
				lps_ps.setString(
				    2, StringUtils.getString(aldt_fechaInicio, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);
				lps_ps.setString(
				    3, StringUtils.getString(aldt_fechaFin, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getObjetFromResultSet(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllByCirculoRangoFechas", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpr_return.isEmpty())
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Find all by direccion completa.
	 *
	 * @param as_direccionCompleta de as direccion completa
	 * @param as_codDepartamento de as cod departamento
	 * @param as_codMunicipio de as cod municipio
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findAllByDireccionCompleta(
	    String as_direccionCompleta, String as_codDepartamento, String as_codMunicipio
	)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_predioRegistro;

		lcpr_predioRegistro = new ArrayList<PredioRegistro>();

		if(StringUtils.isValidString(as_direccionCompleta))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           lit_count;

				lsb_sb        = new StringBuilder(cs_FIND_ALL_BY_DIRECCION_COMPLETA);
				lit_count     = 1;

				if(StringUtils.isValidString(as_codDepartamento) || StringUtils.isValidString(as_codMunicipio))
					lsb_sb.append(
					    " INNER JOIN SDB_BNG_PROPIETARIOS PROP ON PR.ID_CIRCULO = PROP.ID_CIRCULO AND PR.ID_MATRICULA = PROP.ID_MATRICULA INNER JOIN SDB_PGN_ZONA_REGISTRAL ZR ON PR.ID_ZONA_REGISTRAL = ZR.ID_ZONA_REGISTRAL"
					);

				lsb_sb.append(" WHERE DP.DIRECCION_COMPLETA = ?");

				if(StringUtils.isValidString(as_codDepartamento))
					lsb_sb.append(" AND ZR.ID_DEPARTAMENTO = ?");

				if(StringUtils.isValidString(as_codMunicipio))
					lsb_sb.append(" AND ZR.ID_MUNICIPIO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(lit_count++, as_direccionCompleta);

				if(StringUtils.isValidString(as_codDepartamento))
					lps_ps.setString(lit_count++, as_codDepartamento);

				if(StringUtils.isValidString(as_codMunicipio))
					lps_ps.setString(lit_count++, as_codMunicipio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_predioRegistro.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDireccionCompleta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcpr_predioRegistro))
			lcpr_predioRegistro = null;

		return lcpr_predioRegistro;
	}

	/**
	 * Find all by persona.
	 *
	 * @param as_idPersona de as id persona
	 * @param as_codDepartamento de as cod departamento
	 * @param as_codMunicipio de as cod municipio
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findAllByPersona(
	    String as_idPersona, String as_codDepartamento, String as_codMunicipio
	)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;

		lcpr_return = new ArrayList<PredioRegistro>();

		if(StringUtils.isValidString(as_idPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_contador;

				lsb_sb          = new StringBuilder(cs_FIND_ALL_BY_PERSONA);
				li_contador     = 1;

				if(StringUtils.isValidString(as_codDepartamento) || StringUtils.isValidString(as_codMunicipio))
					lsb_sb.append(
					    " INNER JOIN SDB_PGN_ZONA_REGISTRAL ZR ON PR.ID_ZONA_REGISTRAL = ZR.ID_ZONA_REGISTRAL"
					);

				lsb_sb.append(" WHERE PROP.ID_PERSONA = ?");

				if(StringUtils.isValidString(as_codDepartamento))
					lsb_sb.append(" AND ZR.ID_DEPARTAMENTO = ?");

				if(StringUtils.isValidString(as_codMunicipio))
					lsb_sb.append(" AND ZR.ID_MUNICIPIO = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_contador++, as_idPersona);

				if(StringUtils.isValidString(as_codDepartamento))
					lps_ps.setString(li_contador++, as_codDepartamento);

				if(StringUtils.isValidString(as_codMunicipio))
					lps_ps.setString(li_contador++, as_codMunicipio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getObjetFromResultSet(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcpr_return.isEmpty())
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByAntSistema(PredioRegistro at_param)
	    throws B2BException
	{
		PredioRegistro    lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ANT_SISTEMA);

			lps_ps.setString(1, at_param.getIdCirculo());
			lps_ps.setLong(2, at_param.getIdMatricula());
			lps_ps.setString(3, at_param.getIdAntiguoSistema());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcr_cr = getObjetFromResultSet(lrs_rs, false);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByAntSistema", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcr_cr;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro.
	 *
	 * @param apr_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByCirculoMatricula(PredioRegistro apr_param)
	    throws B2BException
	{
		return (apr_param != null) ? findByCirculoMatricula(apr_param.getIdCirculo(), apr_param.getIdMatricula()) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro filtrado por id circulo, id matricula.
	 *
	 * @param as_idCirculo de as id circulo
	 * @param al_idMatricula de al id matricula
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByCirculoMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		return ((as_idCirculo != null) && (al_idMatricula > 0))
		? findByCirculoMatricula(as_idCirculo, al_idMatricula, null) : null;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro filtrado por id circulo, id matricula y cedula catastral.
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param al_idMatricula correspondiente al valor del tipo de objeto long
	 * @param as_cedulaCatastral correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByCirculoMatricula(String as_idCirculo, long al_idMatricula, String as_cedulaCatastral)
	    throws B2BException
	{
		PredioRegistro    lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo)
				    && ((al_idMatricula > 0) || StringUtils.isValidString(as_cedulaCatastral))
			)
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA);
				li_count     = 1;

				if(al_idMatricula > 0)
					lsb_sb.append("AND ID_MATRICULA = ? ");

				if(StringUtils.isValidString(as_cedulaCatastral))
					lsb_sb.append("AND NUMERO_PREDIAL = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_idCirculo);

				if(al_idMatricula > 0)
					lps_ps.setLong(li_count++, al_idMatricula);

				if(StringUtils.isValidString(as_cedulaCatastral))
					lps_ps.setString(li_count++, as_cedulaCatastral);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcr_cr = getObjetFromResultSet(lrs_rs, false);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcr_cr;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro filtrado por id circulo, id antiguo sistema.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByDatosAntSistemaCalificacion(PredioRegistro at_param)
	    throws B2BException
	{
		PredioRegistro    lcr_cr;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcr_cr     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ANT_SISTEMA_CALIFICACION);

			lps_ps.setString(1, at_param.getIdCirculo());
			lps_ps.setString(2, at_param.getIdAntiguoSistema());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcr_cr = getObjetFromResultSet(lrs_rs, false);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDatosAntSistemaCalificacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcr_cr;
	}

	/**
	 * Find by datos registrales direccion.
	 *
	 * @param ad_direccion de ad direccion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByDatosRegistralesDireccion(Direccion ad_direccion)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(ad_direccion != null)
			{
				int           li_column;
				boolean       lb_vereda;
				boolean       lb_idComplementoEjePrincipal;
				boolean       lb_coordenadaEjePrincipal;
				boolean       lb_idTipoEjeSecundario;
				boolean       lb_datoEjeSecundario;
				boolean       lb_idComplementoEjeSecundario;
				boolean       lb_coordenadaEjeSecundario;
				boolean       lb_idComplementoDireccion;
				boolean       lb_complementoDireccion;
				boolean       lb_direccionCompleta;
				StringBuilder lsb_sb;
				String        ls_vereda;
				String        ls_idComplementoEjePrincipal;
				String        ls_coordenadaEjePrincipal;
				String        ls_idTipoEjeSecundario;
				String        ls_datoEjeSecundario;
				String        ls_idComplementoEjeSecundario;
				String        ls_coordenadaEjeSecundario;
				String        ls_idComplementoDireccion;
				String        ls_complementoDireccion;
				String        ls_direccionCompleta;

				li_column                         = 1;
				ls_vereda                         = ad_direccion.getIdVereda();
				ls_idComplementoEjePrincipal      = ad_direccion.getIdComplementoEjePrincipal();
				ls_coordenadaEjePrincipal         = ad_direccion.getIdCoordenadaEjePrincipal();
				ls_idTipoEjeSecundario            = ad_direccion.getIdTipoEjeSecundario();
				ls_datoEjeSecundario              = ad_direccion.getDatoEjeSecundario();
				ls_idComplementoEjeSecundario     = ad_direccion.getIdComplemento1EjeSecundario();
				ls_coordenadaEjeSecundario        = ad_direccion.getIdCoordenada1EjeSecundario();
				ls_idComplementoDireccion         = ad_direccion.getIdComplementoDireccion();
				ls_complementoDireccion           = ad_direccion.getComplementoDireccion();
				ls_direccionCompleta              = ad_direccion.getDireccion();
				lb_vereda                         = StringUtils.isValidString(ls_vereda);
				lb_idComplementoEjePrincipal      = StringUtils.isValidString(ls_idComplementoEjePrincipal);
				lb_coordenadaEjePrincipal         = StringUtils.isValidString(ls_coordenadaEjePrincipal);
				lb_idTipoEjeSecundario            = StringUtils.isValidString(ls_idTipoEjeSecundario);
				lb_datoEjeSecundario              = StringUtils.isValidString(ls_datoEjeSecundario);
				lb_idComplementoEjeSecundario     = StringUtils.isValidString(ls_idComplementoEjeSecundario);
				lb_coordenadaEjeSecundario        = StringUtils.isValidString(ls_coordenadaEjeSecundario);
				lb_idComplementoDireccion         = StringUtils.isValidString(ls_idComplementoDireccion);
				lb_complementoDireccion           = StringUtils.isValidString(ls_complementoDireccion);
				lb_direccionCompleta              = StringUtils.isValidString(ls_direccionCompleta);

				if(lb_direccionCompleta)
					lsb_sb = new StringBuilder(CS_CONSULTA_DATOS_REGISTRALES_BY_DIRECCION_COMPLETA);
				else
				{
					lsb_sb = new StringBuilder(CS_CONSULTA_DATOS_REGISTRALES_BY_DATA_DIRECCION);

					if(lb_vereda)
						lsb_sb.append(" AND SPZR.ID_VEREDA = ? ");

					if(lb_idComplementoEjePrincipal)
						lsb_sb.append(" AND SBDP.ID_COMPLEMENTO_EJE_PRINCIPAL = ? ");

					if(lb_coordenadaEjePrincipal)
						lsb_sb.append(" AND SBDP.ID_COORDENADA_EJE_PRINCIPAL = ? ");

					if(lb_idTipoEjeSecundario)
						lsb_sb.append(" AND SBDP.ID_TIPO_EJE_SECUNDARIO = ? ");

					if(lb_datoEjeSecundario)
						lsb_sb.append(" AND SBDP.DATO_EJE_SECUNDARIO = ? ");

					if(lb_idComplementoEjeSecundario)
						lsb_sb.append(" AND SBDP.ID_COMPLEMENTO1_EJE_SECUNDARIO = ? ");

					if(lb_coordenadaEjeSecundario)
						lsb_sb.append(" AND SBDP.ID_COORDENADA1_EJE_SECUNDARIO = ? ");

					if(lb_idComplementoDireccion)
						lsb_sb.append(" AND SBDP.ID_COMPLEMENTO_DIRECCION = ? ");

					if(lb_complementoDireccion)
						lsb_sb.append(" AND SBDP.COMPLEMENTO_DIRECCION = ? ");
				}

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(lb_direccionCompleta)
					lps_ps.setString(li_column++, ls_direccionCompleta);
				else
				{
					lps_ps.setString(li_column++, ad_direccion.getIdPais());
					lps_ps.setString(li_column++, ad_direccion.getIdDepartamento());
					lps_ps.setString(li_column++, ad_direccion.getIdMunicipio());
					lps_ps.setString(li_column++, ad_direccion.getIdTipoEjePrincipal());
					lps_ps.setString(li_column++, ad_direccion.getDatoEjePrincipal());

					if(lb_vereda)
						lps_ps.setString(li_column++, ls_vereda);

					if(lb_idComplementoEjePrincipal)
						lps_ps.setString(li_column++, ls_idComplementoEjePrincipal);

					if(lb_coordenadaEjePrincipal)
						lps_ps.setString(li_column++, ls_coordenadaEjePrincipal);

					if(lb_idTipoEjeSecundario)
						lps_ps.setString(li_column++, ls_idTipoEjeSecundario);

					if(lb_datoEjeSecundario)
						lps_ps.setString(li_column++, ls_datoEjeSecundario);

					if(lb_idComplementoEjeSecundario)
						lps_ps.setString(li_column++, ls_idComplementoEjeSecundario);

					if(lb_coordenadaEjeSecundario)
						lps_ps.setString(li_column++, ls_coordenadaEjeSecundario);

					if(lb_idComplementoDireccion)
						lps_ps.setString(li_column++, ls_idComplementoDireccion);

					if(lb_complementoDireccion)
						lps_ps.setString(li_column++, ls_complementoDireccion);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getCirculoMatriculaPredio(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDatosRegistralesDireccion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcpr_return.isEmpty())
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Retorna el valor del objeto de tipo PredioRegistro filtrado por id matricula.
	 *
	 * @param apr_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @return devuelve el valor del objeto predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByDocumento(PredioRegistro apr_param)
	    throws B2BException
	{
		PredioRegistro    lpr_Predio;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		StringBuilder     lsb_sb;

		lpr_Predio     = null;
		lps_ps         = null;
		lrs_rs         = null;
		lsb_sb         = new StringBuilder();

		try
		{
			int li_contador;
			li_contador = 1;

			lsb_sb.append(cs_FIND_DOCUMENTO);

			Long ll_idMatricula;

			boolean lb_validoMatricula;

			ll_idMatricula     = NumericUtils.getLongWrapper(apr_param.getIdMatricula());

			lb_validoMatricula = NumericUtils.isValidLong(ll_idMatricula);

			if(lb_validoMatricula)
				lsb_sb.append("AND ID_MATRICULA = ?");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());
			lps_ps.setString(li_contador++, apr_param.getIdDocumento());
			lps_ps.setString(li_contador++, apr_param.getIdCirculo());

			if(lb_validoMatricula)
				setLong(lps_ps, ll_idMatricula, li_contador++);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lpr_Predio = getObjetFromResultSet(lrs_rs, false);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByDocumento", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Consulta un registro en la tabla asociado a un id círculo e id matrícula específicos.
	 *
	 * @param as_idCirculo Id del círculo a utilizar como filtro en la consulta
	 * @param al_idMatricula Id de la matrícula a utilizar como filtro en la consulta
	 * @return PredioRegistro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioRegistro findByFolioMatricula(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		PredioRegistro lcapr_predio;

		lcapr_predio = null;

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_FOLIO_MATR);

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setLong(li_cont++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcapr_predio = getObjetFromResultSet(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByFolioMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcapr_predio;
	}

	/** {@inheritdoc} */
	@Override
	public PredioRegistro findById(PredioRegistro apr_param)
	    throws B2BException
	{
		PredioRegistro lpr_predioRegistro;

		lpr_predioRegistro = null;

		if(apr_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			StringBuilder     lsb_sb;
			int               li_column;
			boolean           lb_args;

			li_column     = 1;

			lps_ps      = null;
			lrs_rs      = null;
			lsb_sb      = new StringBuilder();
			lb_args     = apr_param.isArgs();

			try
			{
				lsb_sb.append(cs_FIND_BY_ID);

				if(lb_args)
					lsb_sb.append(" WHERE BPR.ID_CIRCULO=? AND BPR.ID_MATRICULA=?  AND BPR.NUPRE=? ");
				else
				{
					if(apr_param.isValidMatricula())
						lsb_sb.append(" WHERE BPR.ID_CIRCULO=? AND BPR.ID_MATRICULA=? ");
					else if(apr_param.isValidNupre())
						lsb_sb.append(" WHERE BPR.NUPRE=? ");
				}

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				if(lb_args)
				{
					lps_ps.setString(li_column++, apr_param.getIdCirculo());
					lps_ps.setLong(li_column++, apr_param.getIdMatricula());
					lps_ps.setString(li_column++, apr_param.getNupre());
				}
				else
				{
					if(apr_param.isValidMatricula())
					{
						lps_ps.setString(li_column++, apr_param.getIdCirculo());
						lps_ps.setLong(li_column++, apr_param.getIdMatricula());
					}
					else if(apr_param.isValidNupre())
						lps_ps.setString(li_column++, apr_param.getNupre());
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_predioRegistro = getObjetFromResultSet(lrs_rs);
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

		return lpr_predioRegistro;
	}

	/**
	 * Método encargado de consultar los predios por id circulo y id matricula.
	 *
	 * @param as_idCirculo Variable que contiene el id del circulo
	 * @param al_idMatricula Variable que contiene el id de la matricula
	 * @return Colección que contiene los datos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByIdCirculoIdMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO_MATRICULA);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getPredioRegistro(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoIdMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcpr_return))
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Consulta un registro que coincida con un número predial específico.
	 *
	 * @param as_numeroPredial correspondiente al valor del tipo de objeto String
	 * @return Predio registro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByNumeroPredial(String as_numeroPredial)
	    throws B2BException
	{
		return findByNumeroPredial(as_numeroPredial, true);
	}

	/**
	 * Consulta un registro que coincida con un número predial específico.
	 *
	 * @param as_numeroPredial correspondiente al valor del tipo de objeto String
	 * @param ab_numeroPredialAnterior de ab numero predial anterior
	 * @return Predio registro resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	public PredioRegistro findByNumeroPredial(String as_numeroPredial, boolean ab_numeroPredialAnterior)
	    throws B2BException
	{
		PredioRegistro lpr_return;

		lpr_return = null;

		if(StringUtils.isValidString(as_numeroPredial))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID);

				if(ab_numeroPredialAnterior)
					lsb_query.append(" WHERE BPR.NUMERO_PREDIAL_ANT = ? ");
				else
					lsb_query.append(" WHERE BPR.NUMERO_PREDIAL = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_numeroPredial);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_return = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumeroPredial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpr_return;
	}

	/**
	 * Método encargado de consultar predios por número predial.
	 *
	 * @param as_numeroPredial Variable que contiene el número predial
	 * @return Colección que contiene los datos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByNumeroPredialCollection(String as_numeroPredial)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_numeroPredial))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUMERO_PREDIAL);

				lps_ps.setString(1, as_numeroPredial);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getPredioRegistro(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNumeroPredialCollection", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcpr_return))
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Find by numero predial nupre.
	 *
	 * @param lpr_predioRegistro de lpr predio registro
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByNumeroPredialNupre(PredioRegistro lpr_predioRegistro)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;

		lcpr_return = new ArrayList<PredioRegistro>();

		if(lpr_predioRegistro != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				String        ls_numeroPredial;
				String        ls_numeroPredialAnt;
				String        ls_nupre;
				int           li_count;
				int           li_countParametros;

				ls_numeroPredial        = lpr_predioRegistro.getNumeroPredial();
				ls_numeroPredialAnt     = lpr_predioRegistro.getNumeroPredialAnt();
				ls_nupre                = lpr_predioRegistro.getNupre();
				li_count                = 1;
				li_countParametros      = 0;

				lsb_query = new StringBuilder(cs_FIND_BY_ID);

				lsb_query.append(" WHERE");

				if(StringUtils.isValidString(ls_numeroPredial))
				{
					lsb_query.append(" BPR.NUMERO_PREDIAL = ? ");

					li_countParametros++;
				}

				if(StringUtils.isValidString(ls_numeroPredialAnt))
				{
					lsb_query.append(((li_countParametros > 0) ? " AND" : "") + " BPR.NUMERO_PREDIAL_ANT = ?");

					li_countParametros++;
				}

				if(StringUtils.isValidString(ls_nupre))
				{
					lsb_query.append(((li_countParametros > 0) ? " AND" : "") + " BPR.NUPRE = ?");

					li_countParametros++;
				}

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				if(StringUtils.isValidString(ls_numeroPredial))
					lps_ps.setString(li_count++, ls_numeroPredial);

				if(StringUtils.isValidString(ls_numeroPredialAnt))
					lps_ps.setString(li_count++, ls_numeroPredialAnt);

				if(StringUtils.isValidString(ls_nupre))
					lps_ps.setString(li_count++, ls_nupre);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumeroPredialNupre", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcpr_return.isEmpty())
			lcpr_return = null;

		return lcpr_return;
	}

	/**
	 * Consulat un registro en la tabla asociado a un id círculo e id matrícula específicos.
	 *
	 * @param as_nupre Id del círculo a utilizar como filtro en la consulta
	 * @return PredioRegistro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PredioRegistro findByNupre(String as_nupre)
	    throws B2BException
	{
		PredioRegistro lcapr_predio;

		lcapr_predio = null;

		if(StringUtils.isValidString(as_nupre))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUPRE);

				lps_ps.setString(1, as_nupre);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcapr_predio = getObjetFromResultSet(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNupre", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcapr_predio;
	}

	/**
	 * Método encargado de consultar los predios por nupre.
	 *
	 * @param as_nupre Variable que contiene el nupre
	 * @return Colección que contiene los datos consultados
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByNupreCollection(String as_nupre)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUPRE);

			lps_ps.setString(1, as_nupre);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lcpr_return.add(getPredioRegistro(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNupreCollection", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcpr_return;
	}

	/**
	 * Método encargado de consultar los predios en los cuales la persona es propietario activo.
	 *
	 * @param as_idPersona Variable String que contiene el id de la persona.
	 * @return Colección de los predios en donde la persona es propietario.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PredioRegistro> findByPersonaPropietario(String as_idPersona)
	    throws B2BException
	{
		Collection<PredioRegistro> lcpr_return;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcpr_return     = new ArrayList<PredioRegistro>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_idPersona))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERSONA_PROPIETARIO);

				lps_ps.setString(1, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcpr_return.add(getPredioRegistro(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByPersonaPropietario", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lcpr_return))
			lcpr_return = null;

		return lcpr_return;
	}

	/** {@inheritdoc} */
	@Override
	public void insertOrUpdate(PredioRegistro apr_param, boolean ab_query)
	    throws B2BException
	{
		if(apr_param != null)
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
					lps_ps.setString(li_column++, apr_param.getIdCirculo());
					lps_ps.setLong(li_column++, apr_param.getIdMatricula());
				}

				lps_ps.setString(li_column++, apr_param.getIdZonaRegistral());
				lps_ps.setString(li_column++, apr_param.getNupre());
				lps_ps.setString(li_column++, apr_param.getNumeroPredial());
				lps_ps.setString(li_column++, apr_param.getNumeroPredialAnt());
				lps_ps.setString(li_column++, apr_param.getFolioGrabacion());
				lps_ps.setString(li_column++, apr_param.getIdTipoPredio());
				lps_ps.setString(li_column++, apr_param.getIdTipoUsoSuelo());
				lps_ps.setString(li_column++, apr_param.getIdDocumento());
				lps_ps.setString(li_column++, apr_param.getRadicacion());
				lps_ps.setString(li_column++, apr_param.getPredioDefinitivo());
				lps_ps.setString(li_column++, apr_param.getIdEstadoPredio());
				lps_ps.setDate(li_column++, DateUtils.getCleanSQLDate(apr_param.getFechaApertura()));
				lps_ps.setString(li_column++, apr_param.getAnotacionCierre());
				lps_ps.setBigDecimal(li_column++, apr_param.getIdComplementacion());
				lps_ps.setString(li_column++, apr_param.getComentario());
				lps_ps.setString(li_column++, apr_param.getIdAntiguoSistema());
				lps_ps.setString(li_column++, apr_param.getTurnoBloqueo());
				lps_ps.setString(li_column++, apr_param.getPredioInconsistente());

				if(ab_query)
				{
					lps_ps.setString(li_column++, apr_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apr_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, apr_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apr_param.getIpModificacion());
					lps_ps.setString(li_column++, apr_param.getIdCirculo());
					lps_ps.setLong(li_column++, apr_param.getIdMatricula());
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
	 * Actualiza un registro en la tabla SDB_BNG_PREDIO_REGISTRO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCuartoOrden(PredioRegistro at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_CUARTO_ORDEN);

				lps_ps.setBigDecimal(li_column++, at_param.getValorAvaluo());
				lps_ps.setObject(li_column++, at_param.getFechaAvaluoService(), OracleTypes.DATE);
				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getIdCirculo());
				lps_ps.setLong(li_column++, at_param.getIdMatricula());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCuartoOrden", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza un registro en la tabla SDB_BNG_PREDIO_REGISTRO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateIdEstadoPredio(PredioRegistro at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_ID_ESTADO_PREDIO);

				lps_ps.setString(li_column++, at_param.getIdEstadoPredio());
				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getIdCirculo());
				lps_ps.setLong(li_column++, at_param.getIdMatricula());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateIdEstadoPredio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza un registro en la tabla SDB_BNG_PREDIO_REGISTRO.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto PredioRegistro
	 * @return el valor de predio registro
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public PredioRegistro updateQuintoOrden(PredioRegistro at_param)
	    throws B2BException
	{
		if(at_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE_QUINTO_ORDEN);

				lps_ps.setString(li_column++, at_param.getNumeroPredial());
				lps_ps.setString(li_column++, at_param.getNumeroPredialAnt());
				lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, at_param.getIpModificacion());
				lps_ps.setString(li_column++, at_param.getIdCirculo());
				lps_ps.setLong(li_column++, at_param.getIdMatricula());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateQuintoOrden", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}

		return findByCirculoMatricula(at_param);
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de predio registro
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	private PredioRegistro getCirculoMatriculaPredio(ResultSet ars_rs)
	    throws SQLException
	{
		PredioRegistro lpr_datos;

		lpr_datos = new PredioRegistro();

		lpr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lpr_datos.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));

		return lpr_datos;
	}

	/**
	 * Retorna Objeto o variable de valor matricula datos registrales.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de matricula datos registrales
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Matricula getMatriculaDatosRegistrales(ResultSet ars_rs)
	    throws SQLException
	{
		Matricula lm_return;

		lm_return = new Matricula();

		lm_return.setMatricula(ars_rs.getString("MATRICULA"));
		lm_return.setCodigoOrip(ars_rs.getString("CODIGO_ORIP"));
		lm_return.setNombreOrip(ars_rs.getString("NOMBRE_ORIP"));
		lm_return.setChip(ars_rs.getString("NUMERO_PREDIAL"));
		lm_return.setEstadoNupre(ars_rs.getString("ESTADO_NUPRE"));
		lm_return.setNupre(ars_rs.getString("NUPRE"));
		lm_return.setCedulaCatastral(ars_rs.getString("NUMERO_PREDIAL"));
		lm_return.setDireccionPredio(ars_rs.getString("DIRECCION_COMPLETA"));
		lm_return.setDepartamento(ars_rs.getString("DEPARTAMENTO"));
		lm_return.setMunicipio(ars_rs.getString("MUNICIPIO"));
		lm_return.setGrupo(ars_rs.getString("GRUPO"));

		return lm_return;
	}

	/**
	 * Retorna el valor de PredioRegistro.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de PredioRegistro
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	private PredioRegistro getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjetFromResultSet(ars_rs, true);
	}

	/**
	 * Retorna el valor de PredioRegistro.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_nombreEstado true para indicar si se debe traer el nombre del estado
	 * @return el valor de PredioRegistro
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	private PredioRegistro getObjetFromResultSet(ResultSet ars_rs, boolean ab_nombreEstado)
	    throws SQLException
	{
		PredioRegistro ldp_predioRegistro;

		ldp_predioRegistro = new PredioRegistro();

		ldp_predioRegistro.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldp_predioRegistro.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		ldp_predioRegistro.setIdZonaRegistral(ars_rs.getString("ID_ZONA_REGISTRAL"));
		ldp_predioRegistro.setNupre(ars_rs.getString("NUPRE"));
		ldp_predioRegistro.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));
		ldp_predioRegistro.setNumeroPredialAnt(ars_rs.getString("NUMERO_PREDIAL_ANT"));
		ldp_predioRegistro.setFolioGrabacion(ars_rs.getString("FOLIO_GRABACION"));
		ldp_predioRegistro.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		ldp_predioRegistro.setIdTipoUsoSuelo(ars_rs.getString("ID_TIPO_USO_SUELO"));
		ldp_predioRegistro.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		ldp_predioRegistro.setRadicacion(ars_rs.getString("RADICACION"));
		ldp_predioRegistro.setPredioDefinitivo(ars_rs.getString("PREDIO_DEFINITIVO"));
		ldp_predioRegistro.setIdEstadoPredio(ars_rs.getString("ID_ESTADO_PREDIO"));
		ldp_predioRegistro.setFechaApertura(ars_rs.getTimestamp("FECHA_APERTURA"));
		ldp_predioRegistro.setAnotacionCierre(ars_rs.getString("ANOTACION_CIERRE"));
		ldp_predioRegistro.setIdComplementacion(ars_rs.getBigDecimal("ID_COMPLEMENTACION"));
		ldp_predioRegistro.setComentario(ars_rs.getString("COMENTARIO"));
		ldp_predioRegistro.setIdAntiguoSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		ldp_predioRegistro.setTurnoBloqueo(ars_rs.getString("TURNO_BLOQUEO"));
		ldp_predioRegistro.setPredioInconsistente(ars_rs.getString("PREDIO_INCONSISTENTE"));
		ldp_predioRegistro.setIdEstadoNupre(ars_rs.getString("ID_ESTADO_NUPRE"));
		ldp_predioRegistro.setVersionDocumento(ars_rs.getBigDecimal("VERSION_DOCUMENTO"));
		ldp_predioRegistro.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		ldp_predioRegistro.setValorAvaluo(ars_rs.getBigDecimal("VALOR_AVALUO"));
		ldp_predioRegistro.setFechaAvaluo(ars_rs.getTimestamp("FECHA_AVALUO"));
		ldp_predioRegistro.setNupre(ars_rs.getString("NUPRE"));

		if(ab_nombreEstado)
			ldp_predioRegistro.setNombreEstado(ars_rs.getString("NOMBRE"));

		fillAuditoria(ars_rs, ldp_predioRegistro);

		return ldp_predioRegistro;
	}

	/**
	 * Retorna el valor de predio registro.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de predio registro
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see PredioRegistro
	 */
	private PredioRegistro getPredioRegistro(ResultSet ars_rs)
	    throws SQLException
	{
		PredioRegistro lpr_datos;

		lpr_datos = new PredioRegistro();

		lpr_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lpr_datos.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lpr_datos.setIdZonaRegistral(ars_rs.getString("ID_ZONA_REGISTRAL"));
		lpr_datos.setNupre(ars_rs.getString("NUPRE"));
		lpr_datos.setNumeroPredial(ars_rs.getString("NUMERO_PREDIAL"));
		lpr_datos.setNumeroPredialAnt(ars_rs.getString("NUMERO_PREDIAL_ANT"));
		lpr_datos.setFolioGrabacion(ars_rs.getString("FOLIO_GRABACION"));
		lpr_datos.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		lpr_datos.setIdTipoUsoSuelo(ars_rs.getString("ID_TIPO_USO_SUELO"));
		lpr_datos.setIdDocumento(ars_rs.getString("ID_DOCUMENTO"));
		lpr_datos.setRadicacion(ars_rs.getString("RADICACION"));
		lpr_datos.setPredioDefinitivo(ars_rs.getString("PREDIO_DEFINITIVO"));
		lpr_datos.setIdEstadoPredio(ars_rs.getString("ID_ESTADO_PREDIO"));
		lpr_datos.setFechaApertura(ars_rs.getTimestamp("FECHA_APERTURA"));
		lpr_datos.setAnotacionCierre(ars_rs.getString("ANOTACION_CIERRE"));
		lpr_datos.setIdComplementacion(ars_rs.getBigDecimal("ID_COMPLEMENTACION"));
		lpr_datos.setComentario(ars_rs.getString("COMENTARIO"));
		lpr_datos.setIdAntiguoSistema(ars_rs.getString("ID_DATOS_ANT_SISTEMA"));
		lpr_datos.setTurnoBloqueo(ars_rs.getString("TURNO_BLOQUEO"));
		lpr_datos.setPredioInconsistente(ars_rs.getString("PREDIO_INCONSISTENTE"));
		lpr_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lpr_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lpr_datos.setIdEstadoNupre(ars_rs.getString("ID_ESTADO_NUPRE"));
		lpr_datos.setVersionDocumento(ars_rs.getBigDecimal("VERSION_DOCUMENTO"));

		return lpr_datos;
	}
}
