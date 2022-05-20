package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.acc.MensajeAcuseDetalle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MENSAJE_ACUSE_DETALLE.
 *
 * @author Sebastian Sanchez
 */
public class MensajeAcuseDetalleDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_MENSAJE_ACUSE_DETALLE ";

	/** Constante cs_FIND_BY_ID . */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MENSAJE_ACUSE_DETALLE WHERE ID_ACUSE_DETALLE=?";

	/** Constante cs_FIND_BY_ID_MENSAJE. */
	private static final String cs_FIND_BY_ID_MENSAJE = "SELECT * FROM SDB_ACC_MENSAJE_ACUSE_DETALLE WHERE ID_MENSAJE=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_MENSAJE_ACUSE_DETALLE SET ID_MENSAJE=?, FECHA_ACUSE=?, FECHA_ENVIO_CORRESPONDENCIA=?, GUIA_CORRESPODENCIA_FISICA=?, DESCRIPCION_ACUSE_FALLIDO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ACUSE_DETALLE=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MENSAJE_ACUSE_DETALLE(ID_ACUSE_DETALLE, ID_MENSAJE, FECHA_ACUSE, FECHA_ENVIO_CORRESPONDENCIA, GUIA_CORRESPODENCIA_FISICA, DESCRIPCION_ACUSE_FALLIDO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ " VALUES(?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_MENSAJE_ACUSE_DETALLE_ID_ACUSE_DETALLE.NEXTVAL FROM DUAL";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de MensajeAcuceDetalle resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<MensajeAcuseDetalle> findAll()
	    throws B2BException
	{
		Collection<MensajeAcuseDetalle> lcmad_cmad;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;

		lcmad_cmad     = new ArrayList<MensajeAcuseDetalle>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmad_cmad.add(getObjetFromResultSet(lrs_rs));
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

		if(lcmad_cmad.isEmpty())
			lcmad_cmad = null;

		return lcmad_cmad;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param amad_param Objeto contenedor de los filtros a usar en la consulta
	 * @return MensajeAcuceDetalle resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeAcuseDetalle findById(MensajeAcuseDetalle amad_param)
	    throws B2BException
	{
		MensajeAcuseDetalle lmad_mensajeAcuceDetalle;

		lmad_mensajeAcuceDetalle = null;

		if(amad_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, amad_param.getIdMensaje());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmad_mensajeAcuceDetalle = getObjetFromResultSet(lrs_rs);
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

		return lmad_mensajeAcuceDetalle;
	}

	/**
	 * Find by id mensaje.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @return el valor de mensaje acuce detalle
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MensajeAcuseDetalle findByIdMensaje(String as_idMensaje)
	    throws B2BException
	{
		MensajeAcuseDetalle lmad_mensajeAcuceDetalle;

		lmad_mensajeAcuceDetalle = null;

		if(StringUtils.isValidString(as_idMensaje))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MENSAJE);

				lps_ps.setString(1, as_idMensaje);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmad_mensajeAcuceDetalle = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMensaje", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lmad_mensajeAcuceDetalle;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param amad_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(MensajeAcuseDetalle amad_param)
	    throws B2BException
	{
		if(amad_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			int               li_column;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					String ls_nextValue;

					ls_nextValue = StringUtils.getString(lrs_rs.getInt(1));

					amad_param.setIdAcuseDetalle(ls_nextValue);
					lps_ps.setString(li_column++, ls_nextValue);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, amad_param.getIdMensaje());
					setDate(lps_ps, amad_param.getFechaAcuseDetalle(), li_column++);
					setDate(lps_ps, amad_param.getFechaEnvioCorrespondencia(), li_column++);
					lps_ps.setString(li_column++, amad_param.getGuiaCorrespondenciaFisica());
					lps_ps.setString(li_column++, amad_param.getDescripcionAcuseFallido());
					lps_ps.setString(li_column++, amad_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, amad_param.getIpCreacion());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insert", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);

				close(lps_ps);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param amad_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(MensajeAcuseDetalle amad_param)
	    throws B2BException
	{
		if(amad_param != null)
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

				lps_ps.setString(li_column++, amad_param.getIdMensaje());
				setDate(lps_ps, amad_param.getFechaAcuseDetalle(), li_column++);
				setDate(lps_ps, amad_param.getFechaEnvioCorrespondencia(), li_column++);
				lps_ps.setString(li_column++, amad_param.getGuiaCorrespondenciaFisica());
				lps_ps.setString(li_column++, amad_param.getDescripcionAcuseFallido());
				lps_ps.setString(li_column++, amad_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, amad_param.getIpCreacion());
				lps_ps.setString(li_column++, amad_param.getIdAcuseDetalle());

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
	private MensajeAcuseDetalle getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		MensajeAcuseDetalle ls_solicitud;

		ls_solicitud = new MensajeAcuseDetalle();

		ls_solicitud.setIdAcuseDetalle(lrs_rs.getString("ID_ACUSE_DETALLE"));
		ls_solicitud.setIdMensaje(lrs_rs.getString("ID_MENSAJE"));
		ls_solicitud.setFechaAcuseDetalle(lrs_rs.getTimestamp("FECHA_ACUSE"));
		ls_solicitud.setFechaEnvioCorrespondencia(lrs_rs.getTimestamp("FECHA_ENVIO_CORRESPONDENCIA"));
		ls_solicitud.setGuiaCorrespondenciaFisica(lrs_rs.getString("GUIA_CORRESPODENCIA_FISICA"));
		ls_solicitud.setDescripcionAcuseFallido(lrs_rs.getString("DESCRIPCION_ACUSE_FALLIDO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
