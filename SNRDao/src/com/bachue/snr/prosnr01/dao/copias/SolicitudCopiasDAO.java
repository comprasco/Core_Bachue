package com.bachue.snr.prosnr01.dao.copias;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.copias.SolicitudCopias;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades SolicitudCopiasDAO.
 *
 * @author  Heiner Emanuel Castañeda
 * Fecha de Creacion: 12/02/2020
 */
public class SolicitudCopiasDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_COPIAS (ID_SOLICITUD_COPIAS,NIR,ID_TURNO,ID_SOLICITUD,"
		+ " ID_TIPO_DOCUMENTAL,ID_ECM_ORIGINAL,DOC_NAME_ORIGINAL,ID_ECM_ENTREGA,DOC_NAME_ENTREGA,NUMERO_FOLIOS,NUMERO_COPIAS,"
		+ " ID_CIRCULO,ESTADO_IMPRESION,TIEMPO_VENCIMIENTO_TRAMITE,DIAS_VENCIMIENTO,REPOSITORIO,ACTIVO,IDENTIFICADOR_COPIAS_SE,MATRICULA_OWCC,ID_USUARIO_CREACION,"
		+ " FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_SOLICITUD_COPIAS SET ID_SOLICITUD_COPIAS=?,NIR=?,ID_TURNO=?,ID_SOLICITUD=?,"
		+ " ID_TIPO_DOCUMENTAL=?,ID_ECM_ORIGINAL=?,DOC_NAME_ORIGINAL=?,ID_ECM_ENTREGA=?,DOC_NAME_ENTREGA=?,NUMERO_FOLIOS=?,NUMERO_COPIAS?,"
		+ " ID_CIRCULO=?,ESTADO_IMPRESION=?,TIEMPO_VENCIMIENTO_TRAMITE=?,DIAS_VENCIMIENTO=?,REPOSITORIO=?,ACTIVO=?,IDENTIFICADOR_COPIAS_SE=?,MATRICULA_OWCC=?,ID_USUARIO_MODIFICACION=?,"
		+ " FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_SOLICITUD_COPIAS=?";

	/** Constante cs_UPDATE_SOLICITUD_BY_COPIAS_SE. */
	private static final String cs_UPDATE_SOLICITUD_BY_COPIAS_SE = "UPDATE SDB_ACC_SOLICITUD_COPIAS SET NIR=?,ID_TURNO=?,ID_SOLICITUD=?,ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP WHERE IDENTIFICADOR_COPIAS_SE=?";

	/** Constante cs_UPDATE. */
	private static final String cs_DELETE = "DELETE FROM SDB_ACC_SOLICITUD_COPIAS WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_SOLICITUD_COPIAS_ID_SOLICITUD_COPIAS.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_SOLICITUD_COPIAS WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_DOCUMENTAL. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_DOCUMENTAL = "SELECT * FROM SDB_ACC_SOLICITUD_COPIAS WHERE ID_SOLICITUD = ? AND ID_TIPO_DOCUMENTAL = ?";

	/** Constante cs_FIND_BY_IDENTIFICADOR_SOLICITUD_SE. */
	private static final String cs_FIND_BY_IDENTIFICADOR_SOLICITUD_SE = "SELECT * FROM SDB_ACC_SOLICITUD_COPIAS WHERE IDENTIFICADOR_COPIAS_SE = ?";

	/**
	 * Metodo encargado de eliminar de la tabla SDB_ACC_SOLICITUD_COPIAS por el ID_SOLICITUD.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setString(1, as_idSolicitud);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de consultar de la tabla SDB_ACC_SOLICITUD_COPIAS por el ID_SOLICITUD.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudCopias> findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<SolicitudCopias> lcsc_solicitudCopias;

		lcsc_solicitudCopias = new ArrayList<SolicitudCopias>();

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(1, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_solicitudCopias.add(getSolicitudCopias(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcsc_solicitudCopias.isEmpty())
			lcsc_solicitudCopias = null;

		return lcsc_solicitudCopias;
	}

	/**
	 * Metodo encargado de consultar de la tabla SDB_ACC_SOLICITUD_COPIAS por el ID_SOLICITUD y el ID_TIPO_DOCUMENTAL.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @param as_tipoDocumental Argumento de tipo <code>String</code> que contiene el ID_TIPO_DOCUMENTAL a consultar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public SolicitudCopias findByIdSolicitudTipoDoc(String as_idSolicitud, String as_tipoDocumental)
	    throws B2BException
	{
		SolicitudCopias lsc_solicitudCopias;

		lsc_solicitudCopias = null;

		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPO_DOCUMENTAL);

				lps_ps.setString(li_contador++, as_idSolicitud);
				lps_ps.setString(li_contador++, as_tipoDocumental);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lsc_solicitudCopias = getSolicitudCopias(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudTipoDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lsc_solicitudCopias;
	}

	/**
	 * Metodo encargado de consultar de la tabla SDB_ACC_SOLICITUD_COPIAS por el IDENTIFICADOR_COPIAS_SE.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @param as_identificadorCopiasSE Argumento de tipo <code>Long</code> que contiene el IDENTIFICADOR_COPIAS_SE a consultar.
	 * @return
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<SolicitudCopias> findByIdentificadorCopiasSE(String as_identificadorCopiasSE)
	    throws B2BException
	{
		Collection<SolicitudCopias> lcsc_solicitudCopias;
		PreparedStatement           lps_ps;
		ResultSet                   lrs_rs;

		lcsc_solicitudCopias     = new ArrayList<SolicitudCopias>();
		lps_ps                   = null;
		lrs_rs                   = null;

		if(StringUtils.isValidString(as_identificadorCopiasSE))
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_IDENTIFICADOR_SOLICITUD_SE);

				setLong(lps_ps, NumericUtils.getLongWrapper(as_identificadorCopiasSE), 1);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_solicitudCopias.add(getSolicitudCopias(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCopiasSE", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		if(lcsc_solicitudCopias.isEmpty())
			lcsc_solicitudCopias = null;

		return lcsc_solicitudCopias;
	}

	/**
	 * Inserta o actualiza un registro en la tabla.
	 *
	 * @param asc_parametros Objeto contenedor de la información a almacenar
	 * @param ab_query true para insertar, false para actualizar
	 * @return Objeto final insertado o actualizado en la base de datos
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudCopias
	 */
	public SolicitudCopias insertOrUpdate(SolicitudCopias asc_parametros, boolean ab_query)
	    throws B2BException
	{
		SolicitudCopias lp_return;

		lp_return = null;

		if(asc_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lrs_rs            = null;
			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
						{
							asc_parametros.setIdSolicitudCopias(((BigDecimal)lo_o).toString());

							lps_ps.setString(li_column++, asc_parametros.getIdSolicitudCopias());
						}
						else
							throw new B2BException(ErrorKeys.ERROR_SIN_SECUENCIA_SOLICITUD_COPIAS);
					}
				}

				lps_ps.setString(li_column++, asc_parametros.getNir());
				lps_ps.setString(li_column++, asc_parametros.getTurno());
				lps_ps.setString(li_column++, asc_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, asc_parametros.getIdTipoDocumental());
				lps_ps.setString(li_column++, asc_parametros.getIdEcmOriginal());
				lps_ps.setString(li_column++, asc_parametros.getDocNameOriginal());
				lps_ps.setString(li_column++, asc_parametros.getIdEcmEntrega());
				lps_ps.setString(li_column++, asc_parametros.getDocNameEntrega());
				setLong(lps_ps, asc_parametros.getNumeroFolios(), li_column++);
				setLong(lps_ps, asc_parametros.getNumeroCopias(), li_column++);
				lps_ps.setString(li_column++, asc_parametros.getIdCirculo());
				lps_ps.setString(li_column++, asc_parametros.getEstadoImpresion());
				setDate(lps_ps, asc_parametros.getTiempoVencimientoTramite(), li_column++);
				setLong(lps_ps, asc_parametros.getDiasVencimiento(), li_column++);
				lps_ps.setString(li_column++, asc_parametros.getRepositorio());
				lps_ps.setString(li_column++, asc_parametros.getActivo());
				setLong(lps_ps, asc_parametros.getIdentificadorCopiasSE(), li_column++);
				lps_ps.setString(li_column++, asc_parametros.getMatriculaOwcc());

				if(ab_query)
				{
					lps_ps.setString(li_column++, asc_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, asc_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, asc_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, asc_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setString(li_column++, asc_parametros.getIdSolicitudCopias());

				lps_ps.executeUpdate();

				lp_return = asc_parametros;
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return lp_return;
	}

	/**
	 * Método encargado de actualizar los datos de la solicitud y turno para los registros identificados con copias SE
	 *
	 * @param asc_parametros Objeto que contiene los datos para realizar la actualización.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateSolicitudByCopiasSE(SolicitudCopias asc_parametros)
	    throws B2BException
	{
		if(asc_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;

			lps_secuencia     = null;
			lps_ps            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_UPDATE_SOLICITUD_BY_COPIAS_SE);

				lps_ps.setString(li_column++, asc_parametros.getNir());
				lps_ps.setString(li_column++, asc_parametros.getTurno());
				lps_ps.setString(li_column++, asc_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, asc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, asc_parametros.getIpModificacion());
				setLong(lps_ps, asc_parametros.getIdentificadorCopiasSE(), li_column++);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateSolicitudByCopiasSE", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Metodo encargado de consultar de la tabla SDB_ACC_SOLICITUD_COPIAS por el IDENTIFICADOR_COPIAS_SE.
	 *
	 * @param as_idSolicitud Argumento de tipo <code>String</code> que contiene el ID_SOLICITUD a consultar.
	 * @param al_identificadorCopiasSE Argumento de tipo <code>Long</code> que contiene el IDENTIFICADOR_COPIAS_SE a consultar.
	 * @return
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public boolean validarNumeroFolios(Long al_identificadorCopiasSE)
	    throws B2BException
	{
		boolean           lb_return;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lb_return     = false;
		lps_ps        = null;
		lrs_rs        = null;

		if(NumericUtils.isValidLong(al_identificadorCopiasSE))
		{
			try
			{
				Collection<SolicitudCopias> lcsc_solicitudCopias;
				StringBuilder               lsb_sb;

				lcsc_solicitudCopias     = new ArrayList<SolicitudCopias>();
				lsb_sb                   = new StringBuilder(cs_FIND_BY_IDENTIFICADOR_SOLICITUD_SE);

				lsb_sb.append(" AND NVL(NUMERO_FOLIOS,0) <> 0");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				setLong(lps_ps, al_identificadorCopiasSE, 1);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcsc_solicitudCopias.add(getSolicitudCopias(lrs_rs));

				lb_return = CollectionUtils.isValidCollection(lcsc_solicitudCopias);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCopiasSE", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}

		return lb_return;
	}

	private SolicitudCopias getSolicitudCopias(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudCopias lsc_solicitudCopias;

		lsc_solicitudCopias = null;

		if(ars_rs != null)
		{
			lsc_solicitudCopias = new SolicitudCopias();

			lsc_solicitudCopias.setIdSolicitudCopias(ars_rs.getString("ID_SOLICITUD_COPIAS"));
			lsc_solicitudCopias.setNir(ars_rs.getString("NIR"));
			lsc_solicitudCopias.setTurno(ars_rs.getString("ID_TURNO"));
			lsc_solicitudCopias.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
			lsc_solicitudCopias.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
			lsc_solicitudCopias.setIdEcmOriginal(ars_rs.getString("ID_ECM_ORIGINAL"));
			lsc_solicitudCopias.setDocNameOriginal(ars_rs.getString("DOC_NAME_ORIGINAL"));
			lsc_solicitudCopias.setIdEcmEntrega(ars_rs.getString("ID_ECM_ENTREGA"));
			lsc_solicitudCopias.setDocNameEntrega(ars_rs.getString("DOC_NAME_ENTREGA"));
			lsc_solicitudCopias.setNumeroFolios(getLong(ars_rs, "NUMERO_FOLIOS"));
			lsc_solicitudCopias.setNumeroCopias(getLong(ars_rs, "NUMERO_COPIAS"));
			lsc_solicitudCopias.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			lsc_solicitudCopias.setEstadoImpresion(ars_rs.getString("ESTADO_IMPRESION"));
			lsc_solicitudCopias.setTiempoVencimientoTramite(ars_rs.getTimestamp("TIEMPO_VENCIMIENTO_TRAMITE"));
			lsc_solicitudCopias.setDiasVencimiento(getLong(ars_rs, "DIAS_VENCIMIENTO"));
			lsc_solicitudCopias.setRepositorio(ars_rs.getString("REPOSITORIO"));
			lsc_solicitudCopias.setActivo(ars_rs.getString("ACTIVO"));
			lsc_solicitudCopias.setIdentificadorCopiasSE(getLong(ars_rs, "IDENTIFICADOR_COPIAS_SE"));
			lsc_solicitudCopias.setMatriculaOwcc(ars_rs.getString("MATRICULA_OWCC"));
			lsc_solicitudCopias.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			lsc_solicitudCopias.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			lsc_solicitudCopias.setIpCreacion(ars_rs.getString("IP_CREACION"));
			lsc_solicitudCopias.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
			lsc_solicitudCopias.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
			lsc_solicitudCopias.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		}

		return lsc_solicitudCopias;
	}
}
