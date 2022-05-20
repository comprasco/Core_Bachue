package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.BooleanUtils;
import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.sdb.acc.AccCompletitudDocumental;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.math.BigDecimal;
import java.math.BigInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para poder realizar transacciones con la base de datos en la tabla SDB_ACC_COMPLETITUD_DOCUMENTAL.
 *
 * @author Julian Vaca
 */
public class AccCompletitudDocumentalDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_COMPLETITUD = ?";

	/** Constante cs_FIND_BY_ACTO_SOLICITUD. */
	private static final String cs_FIND_BY_ACTO_SOLICITUD = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_SOLICITUD = ? AND ID_ACTO = ? AND ID_TIPO_ACTO = ? ";

	/** Constante cs_FIND_BY_ACTO_SOLICITUD_TIPO_DOC. */
	private static final String cs_FIND_BY_ACTO_SOLICITUD_TIPO_DOC = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_SOLICITUD = ? AND ID_ACTO = ? AND ID_TIPO_ACTO = ? AND ID_TIPO_DOCUMENTAL = ? ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_TURNO = "SELECT PTD.NOMBRE, ACD.* FROM SDB_ACC_COMPLETITUD_DOCUMENTAL ACD INNER JOIN SDB_PGN_TIPO_DOCUMENTAL PTD ON PTD.ID_TIPO_DOCUMENTAL = ACD.ID_TIPO_DOCUMENTAL WHERE ACD.ID_TURNO = ? ";

	/** Constante cs_FIND_BY_TURNO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_NOMBRES = "SELECT PTD.NOMBRE, ACD.* FROM SDB_ACC_COMPLETITUD_DOCUMENTAL ACD INNER JOIN SDB_PGN_TIPO_DOCUMENTAL PTD ON PTD.ID_TIPO_DOCUMENTAL = ACD.ID_TIPO_DOCUMENTAL WHERE ACD.ID_SOLICITUD = ? ";

	/** Constante cs_FIND_BY_PROCESO_SOLICITUD. */
	private static final String cs_FIND_BY_PROCESO_SOLICITUD = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_SOLICITUD = ? AND ID_PROCESO = ? ";

	/** Constante cs_FIND_BY_TURNO_DIGITALIZADO. */
	private static final String cs_FIND_BY_TURNO_DIGITALIZADO = "SELECT PTD.NOMBRE, ACD.* FROM SDB_ACC_COMPLETITUD_DOCUMENTAL ACD INNER JOIN SDB_PGN_TIPO_DOCUMENTAL PTD ON PTD.ID_TIPO_DOCUMENTAL = ACD.ID_TIPO_DOCUMENTAL WHERE ACD.ID_TURNO = ? AND ACD.DIGITALIZADO = ? ORDER BY ACD.FECHA_CREACION DESC";

	/** Constante cs_FIND_ALL_SOLICITUD. */
	private static final String cs_FIND_ALL_SOLICITUD = "SELECT * FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_ALL_SOLICITUD_PROCESO. */
	private static final String cs_FIND_ALL_SOLICITUD_PROCESO = "SELECT PTD.NOMBRE, ACD.* FROM SDB_ACC_COMPLETITUD_DOCUMENTAL ACD INNER JOIN SDB_PGN_TIPO_DOCUMENTAL PTD ON PTD.ID_TIPO_DOCUMENTAL = ACD.ID_TIPO_DOCUMENTAL "
		+ "WHERE ACD.ID_SOLICITUD = ? AND ACD.ID_PROCESO = ? AND ACD.ID_SUBPROCESO = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "  INSERT INTO SDB_ACC_COMPLETITUD_DOCUMENTAL (ID_TIPO_ACTO,ID_ACTO,ID_SOLICITUD,ID_SOLICITUD_VINCULADA,ID_TURNO,ID_SOLICITUD_PRINCIPAL,ID_COMPLETITUD,ID_USUARIO_CREACION,OBSERVACIONES,OBSERVACIONES_RECEPCION,OBLIGATORIO,ID_PROCESO,ID_SUBPROCESO,FECHA_CREACION,ID_TIPO_DOCUMENTAL,NUMERO_PAGO,ENTREGADO,OBLIGATORIO_TIPO_DOCUMENTAL,ID_TURNO_CERTIFICADO,IP_CREACION,MEDIO_RECEPCION,ID_DOCUMENTO_SALIDA,DIGITALIZADO)"
		+ "VALUES  ( ?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_COMPLETITUD_DOCUMENTAL SET ID_TIPO_ACTO = ?,"
		+ "ID_ACTO = ?,ID_SOLICITUD = ?,ID_SOLICITUD_VINCULADA = ?, ID_TURNO= ?, ID_SOLICITUD_PRINCIPAL = ?,ID_USUARIO_MODIFICACION = ?,OBSERVACIONES = ?,"
		+ "OBSERVACIONES_RECEPCION = ?,OBLIGATORIO = ?,ID_PROCESO = ?,ID_SUBPROCESO = ?,FECHA_MODIFICACION = SYSTIMESTAMP,"
		+ "ID_TIPO_DOCUMENTAL = ?,NUMERO_PAGO = ?,ENTREGADO = ?,OBLIGATORIO_TIPO_DOCUMENTAL = ?,ID_TURNO_CERTIFICADO = ?,"
		+ "IP_MODIFICACION = ?,MEDIO_RECEPCION = ?, ID_DOCUMENTO_SALIDA = ?, DIGITALIZADO = ?, ID_ECM = ?, "
		+ "ID_NOMBRE_DOCUMENTO = ?, FECHA_DIGITALIZACION = ? WHERE ID_COMPLETITUD = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = " SELECT SEC_ACC_COMPLETITUD_DOCUMENTAL_ID_COMPLETITUD.NEXTVAL FROM DUAL";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = " DELETE FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_COMPLETITUD = ?";

	/** Constante cs_DELETE_BY_ID_SOLICITUD. */
	private static final String cs_DELETE_BY_ID_SOLICITUD = " DELETE FROM SDB_ACC_COMPLETITUD_DOCUMENTAL WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_SECUENCE_COMPLETITUD. */
	private static final String cs_FIND_SECUENCE_COMPLETITUD = " SELECT SEC_ACC_COMPLETITUD_DOCUMENTAL_ID_COMPLETITUD.NEXTVAL FROM DUAL";

	/**
	 * Metodo encargado de borrar todos los registros de la tabla SDB_ACC_COMPLETITUD_DOCUMENTAL por ID_COMPLETITUD.
	 *
	 * @param aacd_parametros Argumento de tipo AccCompletitudDocumental que contiene el ID_SOLICITUD para borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(TipoDocumental aacd_parametros)
	    throws B2BException
	{
		if(aacd_parametros != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(1, aacd_parametros.getIdCompletitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Metodo encargado de borrar todos los registros de la tabla SDB_ACC_COMPLETITUD_DOCUMENTAL por ID_SOLICITUD.
	 *
	 * @param aacd_parametros Argumento de tipo AccCompletitudDocumental que contiene el ID_SOLICITUD para borrar.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteByIdSolicitud(AccCompletitudDocumental aacd_parametros)
	    throws B2BException
	{
		PreparedStatement lps_ps;

		lps_ps = null;

		if(aacd_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID_SOLICITUD);

				lps_ps.setString(1, aacd_parametros.getIdSolicitud());

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
	 * Método para poder obtener todos los registros de la tabla.
	 *
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findAll()
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacd_proceso;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacd_proceso     = new ArrayList<AccCompletitudDocumental>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcacd_proceso.add(getObjetFromResultSet(lrs_rs));
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

		if(lcacd_proceso.isEmpty())
			lcacd_proceso = null;

		return lcacd_proceso;
	}

	/**
	 * Método para encontrar todos los registros por el Id Solicitud.
	 *
	 * @param as_idSolicitud String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findAllByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacdp_proceso;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacdp_proceso     = new ArrayList<AccCompletitudDocumental>();
		lps_ps             = null;
		lrs_rs             = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_ALL_SOLICITUD);

				lps_ps.setString(li_count++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacdp_proceso.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcacdp_proceso.isEmpty())
			lcacdp_proceso = null;

		return lcacdp_proceso;
	}

	/**
	 * Método para encontrar todos los registros por el Id Solicitud, proceso y subproceso.
	 *
	 * @param acd_completitud String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findAllByIdSolicitudProceso(AccCompletitudDocumental acd_completitud)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacdp_proceso;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacdp_proceso     = new ArrayList<AccCompletitudDocumental>();
		lps_ps             = null;
		lrs_rs             = null;

		if(acd_completitud != null)
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_ALL_SOLICITUD_PROCESO);

				lps_ps.setString(li_count++, acd_completitud.getIdSolicitud());
				lps_ps.setString(li_count++, acd_completitud.getIdProceso());
				lps_ps.setString(li_count++, acd_completitud.getIdSubproceso());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacdp_proceso.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdSolicitudProceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcacdp_proceso.isEmpty())
			lcacdp_proceso = null;

		return lcacdp_proceso;
	}

	/**
	 * Método para encontrar todos los registros por el Id Solicitud y el Id Proceso.
	 *
	 * @param as_idSolicitud String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findAllByProcesoSolicitud(String as_idSolicitud, String as_idProceso)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacdp_parametro;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacdp_parametro     = new ArrayList<AccCompletitudDocumental>();
		lps_ps               = null;
		lrs_rs               = null;

		if(StringUtils.isValidString(as_idSolicitud) && StringUtils.isValidString(as_idProceso))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_PROCESO_SOLICITUD);

				lps_ps.setString(li_count++, as_idSolicitud);
				lps_ps.setString(li_count++, as_idProceso);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacdp_parametro.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByProcesoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcacdp_parametro.isEmpty())
			lcacdp_parametro = null;

		return lcacdp_parametro;
	}

	/**
	 * Método para encontrar los registros por el idActo, idTipoActo, idSolicitud.
	 *
	 * @param aacd_param objeto AccCompletitudDocumental con los datos para filtrar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findByActoSolicitud(AccCompletitudDocumental aacd_param)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacd_object;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;
		int                                  li_count;

		lcacd_object     = new ArrayList<AccCompletitudDocumental>();
		lps_ps           = null;
		lrs_rs           = null;
		li_count         = 1;

		if(aacd_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ACTO_SOLICITUD);

				lps_ps.setString(li_count++, aacd_param.getIdSolicitud());
				lps_ps.setString(li_count++, aacd_param.getIdActo());
				lps_ps.setString(li_count++, aacd_param.getIdTipoActo());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacd_object.add(getObjetFromResultSet(lrs_rs));

				if(!CollectionUtils.isValidCollection(lcacd_object))
					lcacd_object = null;
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcacd_object;
	}

	/**
	 * Método para encontrar los registros por el idActo, idTipoActo, idSolicitud y idtipoDocumental.
	 *
	 * @param aacd_param objeto AccCompletitudDocumental con los datos para filtrar en la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findByActoSolicitudTipoDocumental(AccCompletitudDocumental aacd_param)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacd_object;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;
		int                                  li_count;

		lcacd_object     = new ArrayList<AccCompletitudDocumental>();
		lps_ps           = null;
		lrs_rs           = null;
		li_count         = 1;

		if(aacd_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ACTO_SOLICITUD_TIPO_DOC);

				lps_ps.setString(li_count++, aacd_param.getIdSolicitud());
				lps_ps.setString(li_count++, aacd_param.getIdActo());
				lps_ps.setString(li_count++, aacd_param.getIdTipoActo());
				lps_ps.setString(li_count++, aacd_param.getIdTipoDocumental());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacd_object.add(getObjetFromResultSet(lrs_rs));

				if(!CollectionUtils.isValidCollection(lcacd_object))
					lcacd_object = null;
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcacd_object;
	}

	/**
	 * Método para encontrar un registro por el idCompletitud en la BD.
	 *
	 * @param aacd_param objeto AccCompletitudDocumental
	 * @return objeto AccCompletitudDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccCompletitudDocumental
	 */
	public AccCompletitudDocumental findById(AccCompletitudDocumental aacd_param)
	    throws B2BException
	{
		return findById((aacd_param != null) ? aacd_param.getIdCompletitud() : null);
	}

	/**
	 * Método para encontrar un registro por el idCompletitud en la BD.
	 *
	 * @param as_param objeto AccCompletitudDocumental
	 * @return objeto AccCompletitudDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AccCompletitudDocumental
	 */
	public AccCompletitudDocumental findById(String as_param)
	    throws B2BException
	{
		AccCompletitudDocumental lacd_object;
		PreparedStatement        lps_ps;
		ResultSet                lrs_rs;

		lacd_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(as_param != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lacd_object = getObjetFromResultSet(lrs_rs);
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

		return lacd_object;
	}

	/**
	 * Método para encontrar todos los registros por el Id Turno.
	 *
	 * @param as_idSolicitud String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findByIdSolicitudNombres(String as_idSolicitud)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacdp_proceso;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacdp_proceso     = new ArrayList<AccCompletitudDocumental>();
		lps_ps             = null;
		lrs_rs             = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_NOMBRES);

				lps_ps.setString(li_count++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacdp_proceso.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitudNombres", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcacdp_proceso.isEmpty())
			lcacdp_proceso = null;

		return lcacdp_proceso;
	}

	/**
	 * Método para encontrar todos los registros por el Id Turno.
	 *
	 * @param as_idTurno String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<AccCompletitudDocumental> findByIdTurno(String as_idTurno)
	    throws B2BException
	{
		Collection<AccCompletitudDocumental> lcacdp_proceso;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lcacdp_proceso     = new ArrayList<AccCompletitudDocumental>();
		lps_ps             = null;
		lrs_rs             = null;

		if(StringUtils.isValidString(as_idTurno))
		{
			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_TURNO);

				lps_ps.setString(li_count++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcacdp_proceso.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurno", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcacdp_proceso.isEmpty())
			lcacdp_proceso = null;

		return lcacdp_proceso;
	}

	/**
	 * Método para encontrar todos los registros por el Id Turno.
	 *
	 * @param as_idTurno String con el cual realizar la busqueda a la base de datos
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 *
	 */
	public Collection<TipoDocumental> findByIdTurnoDigitalizado(String as_idTurno, String as_digitalizado)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_tiposDocumentales;

		lctd_tiposDocumentales = null;

		if(StringUtils.isValidString(as_idTurno) && StringUtils.isValidString(as_digitalizado))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lrs_rs     = null;
			lps_ps     = null;

			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_BY_TURNO_DIGITALIZADO);

				lps_ps.setString(li_count++, as_idTurno);
				lps_ps.setString(li_count++, as_digitalizado);
				lrs_rs                     = lps_ps.executeQuery();
				lctd_tiposDocumentales     = new ArrayList<TipoDocumental>();

				while(lrs_rs.next())
					lctd_tiposDocumentales.add(getObjectFromResultSet(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTurnoDigitalizado", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lctd_tiposDocumentales;
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuenceCompletitud()
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
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE_COMPLETITUD);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuenceCompletitud", lse_e);

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
	 * Método para insertar un registro AccCompletitudDocumental en la Base de datos.
	 *
	 * @param aacd_parametros Objeto AccCompletitudDocumental con los datos a insertar en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(AccCompletitudDocumental aacd_parametros)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		PreparedStatement lps_secuencia;
		ResultSet         lrs_rs;

		lps_ps            = null;
		lps_secuencia     = null;
		lrs_rs            = null;

		if(aacd_parametros != null)
		{
			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					aacd_parametros.setIdCompletitud(lrs_rs.getString("NEXTVAL"));

				lps_ps.setString(li_column++, aacd_parametros.getIdTipoActo());
				lps_ps.setString(li_column++, aacd_parametros.getIdActo());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitudVinculada());
				lps_ps.setString(li_column++, aacd_parametros.getIdTurno());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitudPrincipal());
				lps_ps.setString(li_column++, aacd_parametros.getIdCompletitud());
				lps_ps.setString(li_column++, aacd_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aacd_parametros.getObservaciones());
				lps_ps.setString(li_column++, aacd_parametros.getObservacionesRecepcion());
				lps_ps.setString(li_column++, aacd_parametros.getObligatorio());
				lps_ps.setString(li_column++, aacd_parametros.getIdProceso());
				lps_ps.setString(li_column++, aacd_parametros.getIdSubproceso());
				lps_ps.setString(li_column++, aacd_parametros.getIdTipoDocumental());
				lps_ps.setObject(li_column++, aacd_parametros.getNumeroPago());
				lps_ps.setString(li_column++, aacd_parametros.getEntregado());
				lps_ps.setString(li_column++, aacd_parametros.getObligatorioTipoDocumental());
				lps_ps.setString(li_column++, aacd_parametros.getIdTurnoCertificadoCorrecciones());
				lps_ps.setString(li_column++, aacd_parametros.getIpCreacion());
				lps_ps.setString(li_column++, aacd_parametros.getMedioRecepcion());

				{
					BigInteger lbi_idDocumentoSalida;

					lbi_idDocumentoSalida = aacd_parametros.getIdDocumentoSalida();

					if(lbi_idDocumentoSalida != null)
						setDouble(lps_ps, NumericUtils.getDoubleWrapper(lbi_idDocumentoSalida), li_column++);
					else
						setDouble(lps_ps, null, li_column++);
				}

				lps_ps.setString(li_column++, aacd_parametros.getDigitalizado());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lps_secuencia);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método para ACTUALIZAR un registro AccCompletitudDocumental en la Base de datos.
	 *
	 * @param aacd_parametros Objeto AccCompletitudDocumental con los datos a actualizar en la BD
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(AccCompletitudDocumental aacd_parametros)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps     = null;
		lrs_rs     = null;

		if(aacd_parametros != null)
		{
			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aacd_parametros.getIdTipoActo());
				lps_ps.setString(li_column++, aacd_parametros.getIdActo());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitudVinculada());
				lps_ps.setString(li_column++, aacd_parametros.getIdTurno());
				lps_ps.setString(li_column++, aacd_parametros.getIdSolicitudPrincipal());
				lps_ps.setString(li_column++, aacd_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aacd_parametros.getObservaciones());
				lps_ps.setString(li_column++, aacd_parametros.getObservacionesRecepcion());
				lps_ps.setString(li_column++, aacd_parametros.getObligatorio());
				lps_ps.setString(li_column++, aacd_parametros.getIdProceso());
				lps_ps.setString(li_column++, aacd_parametros.getIdSubproceso());
				lps_ps.setString(li_column++, aacd_parametros.getIdTipoDocumental());
				lps_ps.setObject(li_column++, aacd_parametros.getNumeroPago());
				lps_ps.setString(li_column++, aacd_parametros.getEntregado());
				lps_ps.setString(li_column++, aacd_parametros.getObligatorioTipoDocumental());
				lps_ps.setString(li_column++, aacd_parametros.getIdTurnoCertificadoCorrecciones());
				lps_ps.setString(li_column++, aacd_parametros.getIpModificacion());
				lps_ps.setString(li_column++, aacd_parametros.getMedioRecepcion());

				{
					BigInteger lbi_idDocumentoSalida;

					lbi_idDocumentoSalida = aacd_parametros.getIdDocumentoSalida();

					if(lbi_idDocumentoSalida != null)
						setDouble(lps_ps, NumericUtils.getDoubleWrapper(lbi_idDocumentoSalida), li_column++);
					else
						setDouble(lps_ps, null, li_column++);
				}

				lps_ps.setString(li_column++, aacd_parametros.getDigitalizado());
				lps_ps.setString(li_column++, aacd_parametros.getIdEcm());
				lps_ps.setString(li_column++, aacd_parametros.getIdNombreDocumento());
				setDate(lps_ps, aacd_parametros.getFechaDigitalizacion(), li_column++);
				lps_ps.setString(li_column++, aacd_parametros.getIdCompletitud());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
				close(lrs_rs);
			}
		}
	}

	/**
	 * Método para extraer los datos de lo que retorna la BD, las agrega a un objeto y las retorna.
	 *
	 * @param ars_rs Resultset de lo que retorna la BD
	 * @return objeto AccCompletitudDocumental
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoDocumental getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		return getObjectFromResultSet(ars_rs, false);
	}

	/**
	 * Método para extraer los datos de lo que retorna la BD, las agrega a un objeto y las retorna.
	 *
	 * @param ars_rs Resultset de lo que retorna la BD
	 * @param ab_obligatorio bandera que indica si se debe traer el check de la pantalla
	 * @return objeto AccCompletitudDocumental
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private TipoDocumental getObjectFromResultSet(ResultSet ars_rs, boolean ab_obligatorio)
	    throws SQLException
	{
		TipoDocumental lacd_completitudDocumental;
		Object         lo_o;

		lacd_completitudDocumental = new TipoDocumental();

		lacd_completitudDocumental.setIdCompletitud(ars_rs.getString("ID_COMPLETITUD"));
		lacd_completitudDocumental.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lacd_completitudDocumental.setIdActo(ars_rs.getString("ID_ACTO"));
		lacd_completitudDocumental.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lacd_completitudDocumental.setObligatorio(ars_rs.getString("OBLIGATORIO"));

		if(ab_obligatorio)
		{
			lacd_completitudDocumental.setSeleccionado(
			    BooleanUtils.getBooleanValue(ars_rs.getString("OBLIGATORIO_TIPO_DOCUMENTAL"))
			);
			lacd_completitudDocumental.setAgregadoPantalla(true);
		}

		lacd_completitudDocumental.setObservaciones(ars_rs.getString("OBSERVACIONES"));
		lacd_completitudDocumental.setMedioRecepcion(ars_rs.getString("MEDIO_RECEPCION"));

		lo_o = ars_rs.getObject("CONTINUAREGISTRO");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lacd_completitudDocumental.setContinuarRegistro(((BigDecimal)lo_o).toBigInteger());

		lacd_completitudDocumental.setObservacionesContinuacion(ars_rs.getString("OBSERVACIONES_CONTINUACION"));

		lo_o = ars_rs.getObject("NUMERO_PAGO");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lacd_completitudDocumental.setNumeroPago(((BigDecimal)lo_o).toBigInteger());

		lacd_completitudDocumental.setNombreTipoDocumental(ars_rs.getString("NOMBRE"));
		lacd_completitudDocumental.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lacd_completitudDocumental.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lacd_completitudDocumental.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lacd_completitudDocumental.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lacd_completitudDocumental.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lacd_completitudDocumental.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lacd_completitudDocumental.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lacd_completitudDocumental.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lacd_completitudDocumental.setObligatorioTipoDocumental(ars_rs.getString("OBLIGATORIO_TIPO_DOCUMENTAL"));
		lacd_completitudDocumental.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lacd_completitudDocumental.setIdTurnoCertificadoCorrecciones(ars_rs.getString("ID_TURNO_CERTIFICADO"));

		lacd_completitudDocumental.setIdTurno(ars_rs.getString("ID_TURNO"));
		lacd_completitudDocumental.setObservacionesRecepcion(ars_rs.getString("OBSERVACIONES_RECEPCION"));
		lacd_completitudDocumental.setEntregado(ars_rs.getString("ENTREGADO"));
		lacd_completitudDocumental.setIdSolicitudVinculada(ars_rs.getString("ID_SOLICITUD_VINCULADA"));
		lacd_completitudDocumental.setDigitalizado(ars_rs.getString("DIGITALIZADO"));

		return lacd_completitudDocumental;
	}

	/**
	 * Método para extraer los datos de lo que retorna la BD, las agrega a un objeto y las retorna.
	 *
	 * @param ars_rs Resultset de lo que retorna la BD
	 * @return objeto AccCompletitudDocumental
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private AccCompletitudDocumental getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccCompletitudDocumental lacd_completitudDocumental;
		Object                   lo_o;

		lacd_completitudDocumental = new AccCompletitudDocumental();

		lacd_completitudDocumental.setIdCompletitud(ars_rs.getString("ID_COMPLETITUD"));
		lacd_completitudDocumental.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lacd_completitudDocumental.setIdActo(ars_rs.getString("ID_ACTO"));
		lacd_completitudDocumental.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		lacd_completitudDocumental.setObligatorio(ars_rs.getString("OBLIGATORIO"));
		lacd_completitudDocumental.setObservaciones(ars_rs.getString("OBSERVACIONES"));

		lo_o = ars_rs.getObject("CONTINUAREGISTRO");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lacd_completitudDocumental.setContinuarRegistro(((BigDecimal)lo_o).toBigInteger());

		lacd_completitudDocumental.setObservacionesContinuacion(ars_rs.getString("OBSERVACIONES_CONTINUACION"));

		lo_o = ars_rs.getObject("NUMERO_PAGO");

		if((lo_o != null) && lo_o instanceof BigDecimal)
			lacd_completitudDocumental.setNumeroPago(((BigDecimal)lo_o).toBigInteger());

		lacd_completitudDocumental.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lacd_completitudDocumental.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lacd_completitudDocumental.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lacd_completitudDocumental.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lacd_completitudDocumental.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lacd_completitudDocumental.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lacd_completitudDocumental.setObligatorioTipoDocumental(ars_rs.getString("OBLIGATORIO_TIPO_DOCUMENTAL"));
		lacd_completitudDocumental.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		lacd_completitudDocumental.setIdTurnoCertificadoCorrecciones(ars_rs.getString("ID_TURNO_CERTIFICADO"));

		lacd_completitudDocumental.setIdTurno(ars_rs.getString("ID_TURNO"));
		lacd_completitudDocumental.setObservacionesRecepcion(ars_rs.getString("OBSERVACIONES_RECEPCION"));
		lacd_completitudDocumental.setEntregado(ars_rs.getString("ENTREGADO"));
		lacd_completitudDocumental.setIdSolicitudVinculada(ars_rs.getString("ID_SOLICITUD_VINCULADA"));
		lacd_completitudDocumental.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lacd_completitudDocumental.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lacd_completitudDocumental.setMedioRecepcion(ars_rs.getString("MEDIO_RECEPCION"));
		lacd_completitudDocumental.setDigitalizado(ars_rs.getString("DIGITALIZADO"));
		lacd_completitudDocumental.setIdEcm(ars_rs.getString("ID_ECM"));
		lacd_completitudDocumental.setIdNombreDocumento(ars_rs.getString("ID_NOMBRE_DOCUMENTO"));
		lacd_completitudDocumental.setIdSolicitudPrincipal(ars_rs.getString("ID_SOLICITUD_PRINCIPAL"));
		lacd_completitudDocumental.setFechaDigitalizacion(ars_rs.getTimestamp("FECHA_DIGITALIZACION"));

		return lacd_completitudDocumental;
	}
}
