package com.bachue.snr.prosnr01.dao.consulta.documento;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.consulta.documento.ConsultaDocumento;
import com.bachue.snr.prosnr01.model.consulta.trazabilidad.Trazabilidad;
import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.Solicitud;
import com.bachue.snr.prosnr01.model.sdb.acc.Turno;
import com.bachue.snr.prosnr01.model.sdb.bng.Documento;
import com.bachue.snr.prosnr01.model.sdb.pgn.Fases;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de documentos por NIT, por ID de la solicitud o por ID
 *
 * @author garias
 */
public class ConsultaDocumentoDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY. */
	private static final String cs_FIND_BY = "SELECT sas.NIR,spf.NOMBRE FROM SDB_ACC_PERSONA sap "
		+ "INNER JOIN SDB_ACC_SOLICITUD sas ON (sap.ID_PERSONA=sas.ID_PERSONA) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sas.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) WHERE ";

	/** Constante cs_FIND_BY_NIR. */
	private static final String cs_FIND_BY_NIR = "SELECT ID_SOLICITUD FROM SDB_ACC_SOLICITUD WHERE NIR = ?";

	/** Constante cs_FIND_BY_ID_SOL. */
	private static final String cs_FIND_BY_ID_SOL = "SELECT sas.NIR,sat.ID_TURNO,spf.NOMBRE  FROM SDB_ACC_TURNO sat "
		+ "INNER JOIN SDB_ACC_SOLICITUD sas ON (sas.ID_SOLICITUD=sat.ID_SOLICITUD) "
		+ "LEFT OUTER JOIN SDB_PGN_ETAPA spa ON (spa.ID_ETAPA=sat.ID_ETAPA_ACTUAL) "
		+ "LEFT OUTER JOIN SDB_PGN_FASES spf ON (spf.ID_FASE=spa.ID_FASE) WHERE sat.ID_SOLICITUD = ?";

	/**
	 * Instancia un nuevo objeto consulta documento DAO.
	 */
	public ConsultaDocumentoDAO()
	{
	}

	/**
	 * Retorna el valor del objeto Collection de ConsultaDocumento por el Id de documento y tipo de documento de identidad
	 *
	 * @param acd_cd correspondiente al valor del tipo de objeto ConsultaDocumento
	 * @return devuelve el valor del objeto collection de ConsultaDocumento
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see ConsultaDocumento
	 */
	public Collection<ConsultaDocumento> findAll(ConsultaDocumento acd_cd)
	    throws B2BException
	{
		Collection<ConsultaDocumento> lccd_consultaDocumento;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;
		StringBuilder                  lsb_sb;
		Documento                     ld_d;
		Persona                       lp_p;

		lccd_consultaDocumento     = new ArrayList<ConsultaDocumento>();
		lps_ps                     = null;
		lrs_rs                     = null;
		lsb_sb                     = new StringBuilder();
		ld_d                       = acd_cd.getDocumento();
		lp_p                       = acd_cd.getPersona();

		try
		{
			int li_contador;
			li_contador     = 1;

			lsb_sb = lsb_sb.append(cs_FIND_BY);

			String ls_d;
			String ls_td;

			ls_d      = ld_d.getIdDocumento();
			ls_td     = lp_p.getTipoDocIdentidad();

			if(StringUtils.isValidString(ls_d))
			{
				lsb_sb = lsb_sb.append("NUMERO_DOCUMENTO = ? ");

				if(StringUtils.isValidString(ls_td))
					lsb_sb = lsb_sb.append("AND sap.ID_DOCUMENTO_TIPO = ? ");

				lsb_sb = lsb_sb.append("AND sas.NIR IS NOT NULL ORDER BY sas.NIR ASC ");
			}

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			if(StringUtils.isValidString(ls_d))
			{
				lps_ps.setString(li_contador++, ls_d);

				if(StringUtils.isValidString(ls_td))
					lps_ps.setString(li_contador++, ls_td);
			}

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccd_consultaDocumento.add(getConsultaDocumento(lrs_rs));
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

		if(lccd_consultaDocumento.isEmpty())
			lccd_consultaDocumento = null;

		return lccd_consultaDocumento;
	}

	/**
	 * Retorna el valor del objeto de tipo Solicitud consultado por NIR
	 *
	 * @param as_parametros correspondiente al valor del tipo de objeto Solicitud
	 * @return devuelve el valor del objeto solicitud
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Solicitud
	 */
	public Solicitud findByNIR(Solicitud as_parametros)
	    throws B2BException
	{
		Solicitud         ls_s;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ls_s       = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_NIR);

			lps_ps.setString(li_contador++, as_parametros.getNir());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
			{
				ls_s = new Solicitud();
				ls_s.setIdSolicitud(lrs_rs.getString("ID_SOLICITUD"));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByNIR", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_s;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de Trazabilidad consultado por el ID de la solicitud
	 *
	 * @param at_argumentos correspondiente al valor del tipo de objeto Trazabilidad
	 * @return devuelve el valor del objeto collection de Trazabilidad
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Trazabilidad
	 */
	public Collection<Trazabilidad> findTrazabilidad(Trazabilidad at_argumentos)
	    throws B2BException
	{
		Collection<Trazabilidad> lt_trazabilidad;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;
		Solicitud                ls_s;

		lt_trazabilidad     = new ArrayList<Trazabilidad>();
		lps_ps              = null;
		lrs_rs              = null;
		ls_s                = at_argumentos.getSolicitud();

		try
		{
			int li_contador;
			li_contador = 1;

			String ls_idSolicitud;
			ls_idSolicitud     = ls_s.getIdSolicitud();

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOL);

			if(StringUtils.isValidString(ls_idSolicitud))
				lps_ps.setString(li_contador, ls_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lt_trazabilidad.add(getTrazabilidad(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findTrazabilidad", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lt_trazabilidad))
			lt_trazabilidad = null;

		return lt_trazabilidad;
	}

	/**
	 * Retorna el valor de consulta documento.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de consulta documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaDocumento
	 */
	private ConsultaDocumento getConsultaDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		ConsultaDocumento lcd_datos;
		Solicitud         ls_s;
		Fases             lf_f;

		lcd_datos     = new ConsultaDocumento();
		ls_s          = new Solicitud();
		lf_f          = new Fases();

		ls_s.setNir(ars_rs.getString("NIR"));
		lf_f.setNombre(ars_rs.getString("NOMBRE"));

		lcd_datos.setSolicitud(ls_s);
		lcd_datos.setFases(lf_f);

		return lcd_datos;
	}

	/**
	 * Retorna el valor de trazabilidad.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de trazabilidad
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see ConsultaDocumento
	 */
	private Trazabilidad getTrazabilidad(ResultSet ars_rs)
	    throws SQLException
	{
		Trazabilidad lt_datos;
		Solicitud    ls_s;
		Turno        ls_t;
		Fases        lf_f;

		lt_datos     = new Trazabilidad();
		ls_s         = new Solicitud();
		ls_t         = new Turno();
		lf_f         = new Fases();

		ls_s.setNir(ars_rs.getString("NIR"));
		ls_t.setIdTurno(ars_rs.getString("ID_TURNO"));
		lf_f.setNombre(ars_rs.getString("NOMBRE"));

		lt_datos.setSolicitud(ls_s);
		lt_datos.setTurno(ls_t);
		lt_datos.setFases(lf_f);

		return lt_datos;
	}
}
