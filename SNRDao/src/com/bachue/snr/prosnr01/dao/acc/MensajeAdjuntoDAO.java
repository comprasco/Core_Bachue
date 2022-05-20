package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAdjunto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MENSAJE_ADJUNTO.
 *
 * @author Sebastian Sanchez
 */
public class MensajeAdjuntoDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_MENSAJE_ADJUNTO ";

	/** Constante cs_FIND_BY_ID . */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MENSAJE_ADJUNTO WHERE ID_ADJUNTO=?";

	/** Constante cs_FIND_BY_ID_MENSAJE. */
	private static final String cs_FIND_ALL_BY_ID_MENSAJE = "SELECT * FROM SDB_ACC_MENSAJE_ADJUNTO WHERE ID_MENSAJE = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_MENSAJE_ADJUNTO SET ID_MENSAJE=?, DID=?, DOCNAME=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ADJUNTO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MENSAJE_ADJUNTO(ID_ADJUNTO, ID_MENSAJE, DID, DOCNAME, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ " VALUES(?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de MensajeAdjunto resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeAdjunto> findAll()
	    throws B2BException
	{
		Collection<MensajeAdjunto> lcma_cma;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lcma_cma     = new ArrayList<MensajeAdjunto>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcma_cma.add(getObjetFromResultSet(lrs_rs));
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

		if(lcma_cma.isEmpty())
			lcma_cma = null;

		return lcma_cma;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param ama_param Objeto contenedor de los filtros a usar en la consulta
	 * @return MensajeAdjunto resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeAdjunto findById(MensajeAdjunto ama_param)
	    throws B2BException
	{
		MensajeAdjunto lma_mensajeAdjunto;

		lma_mensajeAdjunto = null;

		if(ama_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, ama_param.getIdAdjunto());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lma_mensajeAdjunto = getObjetFromResultSet(lrs_rs);
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

		return lma_mensajeAdjunto;
	}

	/**
	 * Find all by id mensaje.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeAdjunto> findAllByIdMensaje(String as_idMensaje)
	    throws B2BException
	{
		Collection<MensajeAdjunto> lcma_mensajeAdjunto;

		lcma_mensajeAdjunto = new ArrayList<MensajeAdjunto>();

		if(StringUtils.isValidString(as_idMensaje))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_ID_MENSAJE);

				lps_ps.setString(1, as_idMensaje);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcma_mensajeAdjunto.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByIdMensaje", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcma_mensajeAdjunto.isEmpty())
			lcma_mensajeAdjunto = null;

		return lcma_mensajeAdjunto;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param ama_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(MensajeAdjunto ama_param)
	    throws B2BException
	{
		if(ama_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_INSERT);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ama_param.getIdAdjunto());
				lps_ps.setString(li_column++, ama_param.getIdMensaje());
				lps_ps.setString(li_column++, ama_param.getDId());
				lps_ps.setString(li_column++, ama_param.getDocName());
				lps_ps.setString(li_column++, ama_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ama_param.getIpCreacion());

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
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param ama_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(MensajeAdjunto ama_param)
	    throws B2BException
	{
		if(ama_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_UPDATE);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ama_param.getIdAdjunto());
				lps_ps.setString(li_column++, ama_param.getIdMensaje());
				lps_ps.setString(li_column++, ama_param.getDId());
				lps_ps.setString(li_column++, ama_param.getDocName());
				lps_ps.setString(li_column++, ama_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ama_param.getIpCreacion());

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
			}
		}
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private MensajeAdjunto getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		MensajeAdjunto ls_solicitud;

		ls_solicitud = new MensajeAdjunto();

		ls_solicitud.setIdAdjunto(lrs_rs.getString("ID_ADJUNTO"));
		ls_solicitud.setIdMensaje(lrs_rs.getString("ID_MENSAJE"));
		ls_solicitud.setDId(lrs_rs.getString("DID"));
		ls_solicitud.setDocName(lrs_rs.getString("DOCNAME"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
