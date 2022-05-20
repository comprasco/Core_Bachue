package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.acc.ParametrosMensaje;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_PARAMETROS_MENSAJE.
 *
 * @author Sebastian Sanchez
 */
public class ParametrosMensajeDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_PARAMETROS_MENSAJE ";

	/** Constante cs_FIND_BY_ID . */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_PARAMETROS_MENSAJE WHERE ID_PARAMETRO=?";

	/** Constante cs_FIND_LLAVE_VALOR_BY_ID_MENSAJE_TIPO. */
	private static final String cs_FIND_LLAVE_VALOR_BY_ID_MENSAJE_TIPO = "SELECT * FROM SDB_ACC_PARAMETROS_MENSAJE WHERE ID_MENSAJE = ? AND TIPO = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PARAMETROS_MENSAJE SET ID_MENSAJE=?, LLAVE=?, VALOR=?, TIPO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_PARAMETRO=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PARAMETROS_MENSAJE(ID_PARAMETRO, ID_MENSAJE, LLAVE, VALOR, TIPO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ " VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de ParametrosMensaje resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<ParametrosMensaje> findAll()
	    throws B2BException
	{
		Collection<ParametrosMensaje> lcpm_cpm;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcpm_cpm     = new ArrayList<ParametrosMensaje>();
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
				lcpm_cpm.add(getObjetFromResultSet(lrs_rs));
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

		if(lcpm_cpm.isEmpty())
			lcpm_cpm = null;

		return lcpm_cpm;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param apm_param Objeto contenedor de los filtros a usar en la consulta
	 * @return ParametrosMensaje resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public ParametrosMensaje findById(ParametrosMensaje apm_param)
	    throws B2BException
	{
		ParametrosMensaje lpm_parametrosMensaje;

		lpm_parametrosMensaje = null;

		if(apm_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, apm_param.getIdParametro());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpm_parametrosMensaje = getObjetFromResultSet(lrs_rs);
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

		return lpm_parametrosMensaje;
	}

	/**
	 * Find llave valor by id mensaje tipo.
	 *
	 * @param as_idMensaje de as id mensaje
	 * @param as_tipo de as tipo
	 * @return el valor de map
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Map<String, String> findLlaveValorByIdMensajeTipo(String as_idMensaje, String as_tipo)
	    throws B2BException
	{
		Map<String, String> lms_llaveValor;

		lms_llaveValor = new HashMap<String, String>();

		if(StringUtils.isValidString(as_idMensaje) && StringUtils.isValidString(as_tipo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_LLAVE_VALOR_BY_ID_MENSAJE_TIPO);

				lps_ps.setString(li_count++, as_idMensaje);
				lps_ps.setString(li_count++, as_tipo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lms_llaveValor.put("<" + lrs_rs.getString("LLAVE") + ">", lrs_rs.getString("VALOR"));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findLlaveValorByIdMensajeTipo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lms_llaveValor.isEmpty())
			lms_llaveValor = null;

		return lms_llaveValor;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param apm_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(ParametrosMensaje apm_param)
	    throws B2BException
	{
		if(apm_param != null)
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

				lps_ps.setString(li_column++, apm_param.getIdParametro());
				lps_ps.setString(li_column++, apm_param.getIdMensaje());
				lps_ps.setString(li_column++, apm_param.getLlave());
				lps_ps.setString(li_column++, apm_param.getValor());
				lps_ps.setString(li_column++, apm_param.getTipo());
				lps_ps.setString(li_column++, apm_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apm_param.getIpCreacion());

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
	 * @param apm_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(ParametrosMensaje apm_param)
	    throws B2BException
	{
		if(apm_param != null)
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

				lps_ps.setString(li_column++, apm_param.getIdMensaje());
				lps_ps.setString(li_column++, apm_param.getLlave());
				lps_ps.setString(li_column++, apm_param.getValor());
				lps_ps.setString(li_column++, apm_param.getTipo());
				lps_ps.setString(li_column++, apm_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, apm_param.getIpCreacion());
				lps_ps.setString(li_column++, apm_param.getIdParametro());

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
	private ParametrosMensaje getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		ParametrosMensaje ls_solicitud;

		ls_solicitud = new ParametrosMensaje();

		ls_solicitud.setIdParametro(lrs_rs.getString("ID_PARAMETRO"));
		ls_solicitud.setIdMensaje(lrs_rs.getString("ID_MENSAJE"));
		ls_solicitud.setLlave(lrs_rs.getString("LLAVE"));
		ls_solicitud.setValor(lrs_rs.getString("VALOR"));
		ls_solicitud.setTipo(lrs_rs.getString("TIPO"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
