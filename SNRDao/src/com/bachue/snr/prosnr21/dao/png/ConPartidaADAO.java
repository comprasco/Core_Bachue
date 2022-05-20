package com.bachue.snr.prosnr21.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr21.common.constants.ErrorKeys;
import com.bachue.snr.prosnr21.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr21.model.pgn.ConPartidaA;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_CON_PARTIDA_A.
 *
 * @author Gabriel Arias
 */
public class ConPartidaADAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID_ARCHIVO. */
	private static final String cs_FIND_BY_ID_ARCHIVO = "SELECT * FROM SDB_CON_PARTIDA_A WHERE ID_ARCHIVO=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_CON_PARTIDA_A WHERE ID_PARTIDA_A=? AND ID_ARCHIVO=? AND ID_REGISTRO=?";

	/** Constante cs_FIND_DATA_REPORTE. */
	private static final String cs_FIND_DATA_REPORTE = "SELECT ID_REGISTRO || ';' ||  MONTO || ';' || REFERENCIA || ';' || PARTIDAS || ';' || CLASIFICACION_PARTIDAS_A || ';' || OBSERVACIONES || ';' || NUMERO_COMPROBANTE_SIIF || ';' || TRUNC(FECHA_COMPROBANTE_SIIF) || ';' || CASE NVL(ENVIO_SIIF,'S') WHEN 'S' THEN 'Si' ELSE 'No' END || ';' ||  CASE NVL(ACTIVO,'S') WHEN 'S' THEN 'Si' ELSE 'No' END AS DATA_REPORTE FROM SDB_CON_PARTIDA_A WHERE ID_ARCHIVO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_CON_PARTIDA_A (ID_PARTIDA_A,ID_ARCHIVO,ID_REGISTRO,MONTO,REFERENCIA,PARTIDAS,CLASIFICACION_PARTIDAS_A,DOCUMENTO_SOPORTE,ID_DOCUMENTO_SOPORTE,ID_RUBRO_PRESUPUESTAL,FECHA_ENVIO_ARCHIVO_ND,ID_USUARIO_ANALISTA_CONCILIO,FECHA_CONCILIACION_ANALISTA,OBSERVACIONES,NUMERO_COMPROBANTE_SIIF,FECHA_COMPROBANTE_SIIF,ENVIO_SIIF,CONCILIADO_ANALISTA,ACTIVO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_CON_PARTIDA_A SET MONTO=?,REFERENCIA=?,PARTIDAS=?,CLASIFICACION_PARTIDAS_A=?,DOCUMENTO_SOPORTE=?,ID_DOCUMENTO_SOPORTE=?,ID_RUBRO_PRESUPUESTAL=?,FECHA_ENVIO_ARCHIVO_ND=?,ID_USUARIO_ANALISTA_CONCILIO=?,FECHA_CONCILIACION_ANALISTA=?,OBSERVACIONES=?,NUMERO_COMPROBANTE_SIIF=?,FECHA_COMPROBANTE_SIIF=?,ENVIO_SIIF=?,CONCILIADO_ANALISTA=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_PARTIDA_A=? AND ID_ARCHIVO=? AND ID_REGISTRO=?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_CON_PARTIDA_A_ID_PARTIDA_A.NEXTVAL FROM DUAL";

	public boolean detalle()
	{
		boolean ab_detalle = false;

		return ab_detalle;
	}

	/**
	 * Find by id.
	 *
	 * @param acpa_param the aci param
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConPartidaA> findById(ConPartidaA acpa_param)
	    throws B2BException
	{
		Collection<ConPartidaA> lccpa_return;

		lccpa_return = new ArrayList<ConPartidaA>();

		if(acpa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_column++, acpa_param.getIdPartidaA());
				lps_ps.setString(li_column++, acpa_param.getIdArchivo());
				lps_ps.setInt(li_column++, acpa_param.getIdRegistro());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccpa_return.add(getObjetFromResultSet(lrs_rs));
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

		if(lccpa_return.isEmpty())
			lccpa_return = null;

		return lccpa_return;
	}

	/**
	 * Find all.
	 *
	 * @return the collection
	 * @throws B2BException the b 2 B exception
	 */
	public Collection<ConPartidaA> findByIdArchivo(String as_idArchivo)
	    throws B2BException
	{
		Collection<ConPartidaA> lccpa_return;
		PreparedStatement       lps_ps;
		ResultSet               lrs_rs;

		lccpa_return     = new ArrayList<ConPartidaA>();
		lps_ps           = null;
		lrs_rs           = null;

		if(StringUtils.isValidString(as_idArchivo))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ARCHIVO);

				lps_ps.setString(1, as_idArchivo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccpa_return.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdArchivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(!CollectionUtils.isValidCollection(lccpa_return))
			lccpa_return = null;

		return lccpa_return;
	}

	public Collection<String> findDataById(String as_idArchivo)
	    throws B2BException
	{
		Collection<String> lcs_return;

		lcs_return = new ArrayList<String>(1);

		if(StringUtils.isValidString(as_idArchivo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				Collection<String> lcs_data;

				lps_ps       = getConnection().prepareStatement(cs_FIND_DATA_REPORTE);
				lcs_data     = new ArrayList<String>(1);

				lps_ps.setString(1, as_idArchivo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcs_data.add(lrs_rs.getString(1));

				if(!lcs_data.isEmpty())
				{
					StringBuilder lsb_titulos;

					lsb_titulos = new StringBuilder();

					lsb_titulos.append("REGISTRO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("MONTO" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("REFERENCIA" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("PARTIDAS" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("CLASIFICACION PARTIDAS A" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("OBSERVACIONES" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("NUMERO COMPROBANTE SIIF" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("FECHA COMPROBANTE SIIF" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ENVIO SIIF" + IdentificadoresCommon.SIMBOLO_PUNTO_COMA_TXT);
					lsb_titulos.append("ACTIVO");

					lcs_return.add(lsb_titulos.toString());
					lcs_return.addAll(lcs_data);
				}
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

		if(lcs_return.isEmpty())
			lcs_return = null;

		return lcs_return;
	}

	/**
	 * Insert.
	 *
	 * @param acpa_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public ConPartidaA insert(ConPartidaA acpa_param)
	    throws B2BException
	{
		if(acpa_param != null)
		{
			PreparedStatement lps_insercion;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_secuencia;

			lps_insercion     = null;
			lps_secuencia     = null;
			lrs_secuencia     = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_insercion     = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);
				lrs_secuencia     = lps_secuencia.executeQuery();

				if(lrs_secuencia.next())
				{
					Object lo_o;

					lo_o = lrs_secuencia.getObject(1);

					if((lo_o != null) && lo_o instanceof BigDecimal)
						acpa_param.setIdPartidaA(lo_o.toString());
					else
						throw new B2BException(ErrorKeys.CON_INCONSISTENCIA_SEQUENCE);
				}

				lps_insercion.setString(li_column++, acpa_param.getIdPartidaA());
				lps_insercion.setString(li_column++, acpa_param.getIdArchivo());
				lps_insercion.setInt(li_column++, acpa_param.getIdRegistro());
				setDouble(lps_insercion, acpa_param.getMonto(), li_column++);
				lps_insercion.setString(li_column++, acpa_param.getReferencia());
				lps_insercion.setString(li_column++, acpa_param.getPartidas());
				lps_insercion.setString(li_column++, acpa_param.getClasificacionPartidasA());
				lps_insercion.setString(li_column++, acpa_param.getDocumentoSoporte());
				lps_insercion.setString(li_column++, acpa_param.getIdDocumentoSoporte());
				lps_insercion.setString(li_column++, acpa_param.getIdRubroPresupuestal());
				setDate(lps_insercion, acpa_param.getFechaEnvioArchivoND(), li_column++);
				lps_insercion.setString(li_column++, acpa_param.getIdUsuarioAnalistaConcilio());
				setDate(lps_insercion, acpa_param.getFechaConciliacionAnalista(), li_column++);
				lps_insercion.setString(li_column++, acpa_param.getObservaciones());
				lps_insercion.setString(li_column++, acpa_param.getNumeroComprobanteSiif());
				setDate(lps_insercion, acpa_param.getFechaComprobanteSiif(), li_column++);
				lps_insercion.setString(li_column++, acpa_param.getEnvioSiif());
				lps_insercion.setString(li_column++, acpa_param.getConciliadoAnalista());
				lps_insercion.setString(li_column++, acpa_param.getActivo());
				lps_insercion.setString(li_column++, acpa_param.getIdUsuarioCreacion());
				lps_insercion.setString(li_column++, acpa_param.getIpCreacion());

				lps_insercion.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_insercion);
				close(lps_secuencia);
				close(lrs_secuencia);
			}
		}

		return acpa_param;
	}

	/**
	 * Update.
	 *
	 * @param acpa_param the apc corte
	 * @throws B2BException the b 2 B exception
	 */
	public void update(ConPartidaA acpa_param)
	    throws B2BException
	{
		if(acpa_param != null)
		{
			PreparedStatement lps_update;

			lps_update = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();
				lps_update        = lc_connection.prepareStatement(cs_UPDATE);

				setDouble(lps_update, acpa_param.getMonto(), li_column++);
				lps_update.setString(li_column++, acpa_param.getReferencia());
				lps_update.setString(li_column++, acpa_param.getPartidas());
				lps_update.setString(li_column++, acpa_param.getClasificacionPartidasA());
				lps_update.setString(li_column++, acpa_param.getDocumentoSoporte());
				lps_update.setString(li_column++, acpa_param.getIdDocumentoSoporte());
				lps_update.setString(li_column++, acpa_param.getIdRubroPresupuestal());
				setDate(lps_update, acpa_param.getFechaEnvioArchivoND(), li_column++);
				lps_update.setString(li_column++, acpa_param.getIdUsuarioAnalistaConcilio());
				setDate(lps_update, acpa_param.getFechaConciliacionAnalista(), li_column++);
				lps_update.setString(li_column++, acpa_param.getObservaciones());
				lps_update.setString(li_column++, acpa_param.getNumeroComprobanteSiif());
				setDate(lps_update, acpa_param.getFechaComprobanteSiif(), li_column++);
				lps_update.setString(li_column++, acpa_param.getEnvioSiif());
				lps_update.setString(li_column++, acpa_param.getConciliadoAnalista());
				lps_update.setString(li_column++, acpa_param.getActivo());
				lps_update.setString(li_column++, acpa_param.getIdUsuarioModificacion());
				lps_update.setString(li_column++, acpa_param.getIpModificacion());

				lps_update.setString(li_column++, acpa_param.getIdPartidaA());
				lps_update.setString(li_column++, acpa_param.getIdArchivo());
				lps_update.setInt(li_column++, acpa_param.getIdRegistro());

				lps_update.executeUpdate();
			}

			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_update);
			}
		}
	}

	/**
	 * Gets the objet from result set.
	 *
	 * @param ars_rs the ars rs
	 * @return the objet from result set
	 * @throws SQLException the SQL exception
	 */
	private ConPartidaA getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		ConPartidaA lcpa_datos;

		lcpa_datos = new ConPartidaA();

		lcpa_datos.setIdPartidaA(ars_rs.getString("ID_PARTIDA_A"));
		lcpa_datos.setIdArchivo(ars_rs.getString("ID_ARCHIVO"));
		lcpa_datos.setIdRegistro(ars_rs.getInt("ID_REGISTRO"));
		lcpa_datos.setMonto(getDouble(ars_rs, "MONTO"));
		lcpa_datos.setReferencia(ars_rs.getString("REFERENCIA"));
		lcpa_datos.setPartidas(ars_rs.getString("PARTIDAS"));
		lcpa_datos.setClasificacionPartidasA(ars_rs.getString("CLASIFICACION_PARTIDAS_A"));
		lcpa_datos.setDocumentoSoporte(ars_rs.getString("DOCUMENTO_SOPORTE"));
		lcpa_datos.setIdDocumentoSoporte(ars_rs.getString("ID_DOCUMENTO_SOPORTE"));
		lcpa_datos.setIdRubroPresupuestal(ars_rs.getString("ID_RUBRO_PRESUPUESTAL"));
		lcpa_datos.setFechaEnvioArchivoND(ars_rs.getDate("FECHA_ENVIO_ARCHIVO_ND"));
		lcpa_datos.setIdUsuarioAnalistaConcilio(ars_rs.getString("ID_USUARIO_ANALISTA_CONCILIO"));
		lcpa_datos.setFechaConciliacionAnalista(ars_rs.getDate("FECHA_CONCILIACION_ANALISTA"));
		lcpa_datos.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lcpa_datos.setNumeroComprobanteSiif(ars_rs.getString("NUMERO_COMPROBANTE_SIIF"));
		lcpa_datos.setFechaComprobanteSiif(ars_rs.getDate("FECHA_COMPROBANTE_SIIF"));
		lcpa_datos.setEnvioSiif(ars_rs.getString("ENVIO_SIIF"));
		lcpa_datos.setConciliadoAnalista(ars_rs.getString("CONCILIADO_ANALISTA"));
		lcpa_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, lcpa_datos);

		return lcpa_datos;
	}
}
