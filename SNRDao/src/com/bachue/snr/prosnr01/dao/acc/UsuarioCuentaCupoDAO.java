package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.Persona;
import com.bachue.snr.prosnr01.model.sdb.acc.UsuarioCuentaCupo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.Collection;
import java.util.LinkedList;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_USUARIO_CUENTA_CUPO.
 *
 * @author Manuel Blanco
 *
 */
public class UsuarioCuentaCupoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_TIPO_DOC_NUM_DOC. */
	private static final String cs_FIND_BY_TIPO_DOC_NUM_DOC = "SELECT * FROM SDB_ACC_USUARIO_CUENTA_CUPO WHERE TIPO_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ?";

	/** Constante cs_FIND_BY_ID_CUENTA_AND_DOC. */
	private static final String cs_FIND_BY_ID_CUENTA_AND_DOC = "SELECT * FROM SDB_ACC_USUARIO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ? AND TIPO_DOCUMENTO = ? AND NUMERO_DOCUMENTO = ?";

	/** Constante cs_FIND_BY_ID_CUENTA_CUPO. */
	private static final String cs_FIND_BY_ID_CUENTA_CUPO = "SELECT * FROM SDB_ACC_USUARIO_CUENTA_CUPO WHERE ID_CUENTA_CUPO = ?";

	/** Constante cs_FIND_BY_CORREO_ELECTRONICO. */
	private static final String cs_FIND_BY_CORREO_ELECTRONICO = "SELECT * FROM SDB_ACC_USUARIO_CUENTA_CUPO WHERE CORREO_ELECTRONICO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_USUARIO_CUENTA_CUPO(ID_USUARIO,ID_CUENTA_CUPO,TIPO_DOCUMENTO,NUMERO_DOCUMENTO,PRIMER_NOMBRE,SEGUNDO_NOMBRE,PRIMER_APELLIDO,SEGUNDO_APELLIDO,RAZON_SOCIAL,CORREO_ELECTRONICO,ESTADO,TIPO_USUARIO,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_USUARIO_CUENTA_CUPO SET ID_CUENTA_CUPO = ?, TIPO_DOCUMENTO = ?, NUMERO_DOCUMENTO = ?, PRIMER_NOMBRE = ?, SEGUNDO_NOMBRE = ?, PRIMER_APELLIDO = ?, SEGUNDO_APELLIDO = ?, RAZON_SOCIAL = ?, CORREO_ELECTRONICO = ?, ESTADO = ?, TIPO_USUARIO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_USUARIO = ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_USUARIO_CUENTA_CUPO_ID_USUARIO.NEXTVAL FROM DUAL";

	/**
	 * Inserta un registro en la tabla.
	 *
	 * @param aucc_usuarioCuentaCupo Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long insert(UsuarioCuentaCupo aucc_usuarioCuentaCupo)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(aucc_usuarioCuentaCupo != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setString(li_column++, StringUtils.getString(ll_secuencia));
				}

				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIdCuentaCupo());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getTipoDocumento());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getNumeroDocumento());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getPrimerNombre());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getSegundoNombre());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getPrimerApellido());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getSegundoApellido());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getRazonSocial());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getCorreoElectronico());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getEstado());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getTipoUsuario());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIpCreacion());

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

		return ll_secuencia;
	}

	/**
	 * Actualiza un registro de la tabla.
	 *
	 * @param aucc_usuarioCuentaCupo Objeto contenedor del registro a actualizar
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public void update(UsuarioCuentaCupo aucc_usuarioCuentaCupo)
	    throws B2BException
	{
		if(aucc_usuarioCuentaCupo != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIdCuentaCupo());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getTipoDocumento());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getNumeroDocumento());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getPrimerNombre());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getSegundoNombre());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getPrimerApellido());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getSegundoApellido());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getRazonSocial());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getCorreoElectronico());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getEstado());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getTipoUsuario());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIpModificacion());
				lps_ps.setString(li_column++, aucc_usuarioCuentaCupo.getIdUsuario());

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
	 * Busca un usuario de cuenta cupo por el id documento.
	 *
	 * @param as_tipoDocumento tipo de documento a utilizar como filtro en la busqueda
	 * @param as_numeroDocumento número de documento a utilizar como filtro en la busqueda
	 * @return Cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public UsuarioCuentaCupo findByTipoDocNumDoc(String as_tipoDocumento, String as_numeroDocumento)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(StringUtils.isValidString(as_tipoDocumento) && StringUtils.isValidString(as_numeroDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TIPO_DOC_NUM_DOC);

				lps_ps.setString(li_cont++, as_tipoDocumento);
				lps_ps.setString(li_cont++, as_numeroDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTipoDocNumDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Busca un usuario asociado a un correo electrónico específico.
	 *
	 * @param as_correoElectronico correo a utilizar como filtro en la busqueda
	 * @return UsuarioCuentaCupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en base de datos
	 */
	public UsuarioCuentaCupo findByCorreoElectronico(String as_correoElectronico)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(StringUtils.isValidString(as_correoElectronico))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CORREO_ELECTRONICO);

				lps_ps.setString(li_cont++, as_correoElectronico);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCorreoElectronico", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Busca un usuario administrado cuenta cupo por el id documento.
	 *
	 * @param as_tipoDocumento tipo de documento a utilizar como filtro en la busqueda
	 * @param as_numeroDocumento número de documento a utilizar como filtro en la busqueda
	 * @return Cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public UsuarioCuentaCupo findAdminByTipoDocNumDoc(String as_tipoDocumento, String as_numeroDocumento)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(StringUtils.isValidString(as_tipoDocumento) && StringUtils.isValidString(as_numeroDocumento))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder(cs_FIND_BY_TIPO_DOC_NUM_DOC);
				li_cont       = 1;

				lsb_query.append(" AND TIPO_USUARIO = 'ADMINISTRADOR' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_tipoDocumento);
				lps_ps.setString(li_cont++, as_numeroDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAdminByTipoDocNumDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Find admin by tipo doc num doc nombres.
	 *
	 * @param ap_persona de ap persona
	 * @return el valor de usuario cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCuentaCupo findAdminByTipoDocNumDocNombres(Persona ap_persona)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(ap_persona != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;
				String        ls_segundoNombre;
				String        ls_segundoApellido;

				lsb_query              = new StringBuilder(cs_FIND_BY_TIPO_DOC_NUM_DOC);
				li_cont                = 1;
				ls_segundoNombre       = ap_persona.getSegundoNombre();
				ls_segundoApellido     = ap_persona.getSegundoApellido();

				lsb_query.append(" AND PRIMER_NOMBRE = ? ");

				if(StringUtils.isValidString(ls_segundoNombre))
					lsb_query.append(" AND SEGUNDO_NOMBRE = ? ");

				lsb_query.append(" AND PRIMER_APELLIDO = ? ");

				if(StringUtils.isValidString(ls_segundoApellido))
					lsb_query.append(" AND SEGUNDO_APELLIDO = ? ");

				lsb_query.append(" AND TIPO_USUARIO = 'ADMINISTRADOR' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, ap_persona.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, ap_persona.getNumeroDocumento());
				lps_ps.setString(li_cont++, ap_persona.getPrimerNombre());

				if(StringUtils.isValidString(ls_segundoNombre))
					lps_ps.setString(li_cont++, ls_segundoNombre);

				lps_ps.setString(li_cont++, ap_persona.getPrimerApellido());

				if(StringUtils.isValidString(ls_segundoApellido))
					lps_ps.setString(li_cont++, ls_segundoApellido);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAdminByTipoDocNumDocNombres", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Find admin by tipo doc num doc razon social.
	 *
	 * @param ap_persona de ap persona
	 * @return el valor de usuario cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCuentaCupo findAdminByTipoDocNumDocRazonSocial(Persona ap_persona)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(ap_persona != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder(cs_FIND_BY_TIPO_DOC_NUM_DOC);
				li_cont       = 1;

				lsb_query.append(" AND RAZON_SOCIAL = ? ");
				lsb_query.append(" AND TIPO_USUARIO = 'ADMINISTRADOR' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, ap_persona.getIdDocumentoTipo());
				lps_ps.setString(li_cont++, ap_persona.getNumeroDocumento());
				lps_ps.setString(li_cont++, ap_persona.getRazonSocial());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAdminByTipoDocNumDocRazonSocial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Find admin by tipo doc num doc correo.
	 *
	 * @param as_tipoDocumento de as tipo documento
	 * @param as_numeroDocumento de as numero documento
	 * @param as_correoElectronico de as correo electronico
	 * @return el valor de usuario cuenta cupo
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public UsuarioCuentaCupo findAdminByTipoDocNumDocCorreo(
	    String as_tipoDocumento, String as_numeroDocumento, String as_correoElectronico
	)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(
		    StringUtils.isValidString(as_tipoDocumento) && StringUtils.isValidString(as_numeroDocumento)
			    && StringUtils.isValidString(as_correoElectronico)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder(cs_FIND_BY_TIPO_DOC_NUM_DOC);
				li_cont       = 1;

				lsb_query.append(" AND CORREO_ELECTRONICO = ? AND TIPO_USUARIO = 'ADMINISTRADOR' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_tipoDocumento);
				lps_ps.setString(li_cont++, as_numeroDocumento);
				lps_ps.setString(li_cont++, as_correoElectronico);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAdminByTipoDocNumDocCorreo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Busca una cuenta cupo por el id cuenta cupo.
	 *
	 * @param as_idCuentaCupo id de cuenta cupo a utilizar como filtro en la busqueda
	 * @return Colección de cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public Collection<UsuarioCuentaCupo> findByIdCuentaCupo(String as_idCuentaCupo)
	    throws B2BException
	{
		Collection<UsuarioCuentaCupo> lcucc_return;

		lcucc_return = new LinkedList<UsuarioCuentaCupo>();

		if(StringUtils.isValidString(as_idCuentaCupo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CUENTA_CUPO);

				lps_ps.setString(1, as_idCuentaCupo);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcucc_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCuentaCupo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcucc_return.isEmpty())
			lcucc_return = null;

		return lcucc_return;
	}

	/**
	 * Busca una cuenta cupo por el id.
	 *
	 * @param as_idCuentaCupo id cuenta cupo a utilizar como filtro en la busqueda
	 * @param as_tipoDocumento tipo de documento a utilizar como filtro en la busqueda
	 * @param as_numeroDocumento número de documento a utilizar como filtro en la busqueda
	 * @return Cuenta cupo resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public UsuarioCuentaCupo findByIdCuentaAndDoc(
	    String as_idCuentaCupo, String as_tipoDocumento, String as_numeroDocumento
	)
	    throws B2BException
	{
		UsuarioCuentaCupo lucc_return;

		lucc_return = null;

		if(
		    StringUtils.isValidString(as_idCuentaCupo) && StringUtils.isValidString(as_tipoDocumento)
			    && StringUtils.isValidString(as_numeroDocumento)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CUENTA_AND_DOC);

				lps_ps.setString(li_cont++, as_idCuentaCupo);
				lps_ps.setString(li_cont++, as_tipoDocumento);
				lps_ps.setString(li_cont++, as_numeroDocumento);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCuentaAndDoc", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lucc_return;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto UsuarioCuentaCupo.
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return Usuario cuenta cupo resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private UsuarioCuentaCupo getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		UsuarioCuentaCupo lucc_usuarioCuentaCupo;

		lucc_usuarioCuentaCupo = new UsuarioCuentaCupo();

		lucc_usuarioCuentaCupo.setIdUsuario(ars_rs.getString("ID_USUARIO"));
		lucc_usuarioCuentaCupo.setIdCuentaCupo(ars_rs.getString("ID_CUENTA_CUPO"));
		lucc_usuarioCuentaCupo.setTipoDocumento(ars_rs.getString("TIPO_DOCUMENTO"));
		lucc_usuarioCuentaCupo.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		lucc_usuarioCuentaCupo.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lucc_usuarioCuentaCupo.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lucc_usuarioCuentaCupo.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lucc_usuarioCuentaCupo.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lucc_usuarioCuentaCupo.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lucc_usuarioCuentaCupo.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		lucc_usuarioCuentaCupo.setEstado(ars_rs.getString("ESTADO"));
		lucc_usuarioCuentaCupo.setTipoUsuario(ars_rs.getString("TIPO_USUARIO"));

		fillAuditoria(ars_rs, lucc_usuarioCuentaCupo);

		return lucc_usuarioCuentaCupo;
	}
}
