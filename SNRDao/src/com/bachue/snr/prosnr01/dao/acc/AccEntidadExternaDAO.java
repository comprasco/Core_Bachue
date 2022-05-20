package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.pgn.PaisDAO;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_ENTIDAD_EXTERNA.
 *
 * @author Manuel Blanco
 *
 */
public class AccEntidadExternaDAO extends PaisDAO
{
	/** Constante cs_FIND_BY_TIPO_DOC_NUM_DOC. */
	private static final String cs_FIND_BY_TIPO_DOC_NUM_DOC = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA WHERE ID_DOCUMENTO_TIPO = ? AND NUMERO_DOCUMENTO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA WHERE ID_ENTIDAD_EXTERNA = ?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA";

	/** Constante cs_FIND_ALL_ENTIDADES_CONVENIO. */
	private static final String cs_FIND_ALL_ENTIDADES_CONVENIO = "SELECT EE.ID_ENTIDAD_EXTERNA AS CODIGO_ENTIDAD, EE.NOMBRE AS NOMBRE_ENTIDAD, P.ID_PAIS AS CODIGO_PAIS, P.NOMBRE AS NOMBRE_PAIS, "
		+ "D.ID_DEPARTAMENTO AS CODIGO_DEPARTAMENTO, D.NOMBRE AS NOMBRE_DEPARTAMENTO, M.ID_MUNICIPIO AS CODIGO_MUNICIPIO, M.NOMBRE AS NOMBRE_MUNICIPIO "
		+ "FROM SDB_ACC_ENTIDAD_EXTERNA EE " + "INNER JOIN SDB_PGN_PAIS P ON (P.ID_PAIS = EE.ID_PAIS) "
		+ "INNER JOIN SDB_PGN_DEPARTAMENTO D ON (D.ID_DEPARTAMENTO = EE.ID_DEPARTAMENTO AND D.ID_PAIS = P.ID_PAIS ) "
		+ "INNER JOIN SDB_PGN_MUNICIPIO M ON (M.ID_MUNICIPIO = EE.ID_MUNICIPIO AND M.ID_DEPARTAMENTO = D.ID_DEPARTAMENTO AND M.ID_PAIS = P.ID_PAIS) "
		+ "WHERE EXISTS(SELECT 1 FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO EEC WHERE EEC.ID_ENTIDAD_EXTERNA = EE.ID_ENTIDAD_EXTERNA AND EEC.ACTIVO = 'S') AND EE.ACTIVO = 'S'";

	/** Constante cs_FIND_BY_TIPO_ENTIDAD. */
	private static final String cs_FIND_BY_TIPO_ENTIDAD = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA WHERE ID_TIPO_ENTIDAD = ? ";

	/** Constante cs_FIND_BY_TIPO_OFICINA_ACTIVO_ENTIDAD_EXENTA. */
	private static final String cs_FIND_BY_TIPO_OFICINA_ACTIVO_ENTIDAD_EXENTA = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA WHERE ID_TIPO_OFICINA = ? AND ACTIVO = 'S' AND ENTIDAD_EXENTA = 'S'";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_ENTIDAD_EXTERNA_ID_ENTIDAD_EXTERNA.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_NOMBRE_LIKE. */
	private static final String cs_FIND_BY_NOMBRE_LIKE = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA WHERE NOMBRE LIKE ? ORDER BY NOMBRE ASC";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ENTIDAD_EXTERNA(ID_ENTIDAD_EXTERNA, ID_DOCUMENTO_TIPO, NUMERO_DOCUMENTO, NOMBRE, ID_REPRESENTANTE_LEGAL, ID_ACTIVIDAD_ECONOMICA, ID_TIPO_OFICINA, ID_TIPO_ENTIDAD, ENTIDAD_EXENTA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, DIRECCION, CORREO_ELECTRONICO, TELEFONO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ENTIDAD_EXTERNA SET ID_DOCUMENTO_TIPO = ?, NUMERO_DOCUMENTO = ?, NOMBRE = ?, ID_REPRESENTANTE_LEGAL = ?, ID_ACTIVIDAD_ECONOMICA = ?, ID_TIPO_OFICINA = ?, ID_TIPO_ENTIDAD = ?, ENTIDAD_EXENTA = ?, ID_PAIS = ?, ID_DEPARTAMENTO = ?, ID_MUNICIPIO = ?, DIRECCION = ?, CORREO_ELECTRONICO = ?, TELEFONO = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_ENTIDAD_EXTERNA = ? ";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de AccEntidadExterna resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<AccEntidadExterna> findAll(boolean ab_activo, boolean ab_numeroDocumentoValido)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcaee_caee;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcaee_caee     = new ArrayList<AccEntidadExterna>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder(cs_FIND_ALL);

			if(ab_activo)
				lsb_consulta.append(" WHERE ACTIVO = 'S'");

			if(ab_numeroDocumentoValido)
			{
				lsb_consulta.append(ab_activo ? " AND" : " WHERE");
				lsb_consulta.append(" NUMERO_DOCUMENTO IS NOT NULL");
			}

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcaee_caee.add(getObjectFromResultSet(lrs_rs));
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

		if(lcaee_caee.isEmpty())
			lcaee_caee = null;

		return lcaee_caee;
	}

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExterna> findAllAccEntidad()
	    throws B2BException
	{
		return findAll(false, false);
	}

	/**
	 * Método encargado de consultar con base al id entidad externa.
	 *
	 * @param as_idEntidadExterna Variable String que contiene el id entidad externa.
	 * @return Objeto resultante de la consulta.
	 * @throws B2BException
	 */
	public AccEntidadExterna findByIdAccEntidadExterna(String as_idEntidadExterna)
	    throws B2BException
	{
		AccEntidadExterna lucc_return;

		lucc_return = null;

		if(StringUtils.isValidString(as_idEntidadExterna))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;
				lps_ps      = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_cont++, as_idEntidadExterna);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lucc_return = getObjectFromResultSet(lrs_rs);
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

		return lucc_return;
	}

	/**
	 * Busca una entidad externa por el id tipo entidad
	 *
	 * @param as_idTipoEntidad id del tipo entidad externa
	 * @param ab_orden objeto de tipo boolean
	 * @param ab_activo objeto de tipo boolean
	 * @return Colección de entidades externas resultantes de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public Collection<AccEntidadExterna> findByIdTipoEntidad(
	    String as_idTipoEntidad, boolean ab_orden, boolean ab_activo
	)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lucc_return;

		lucc_return = new ArrayList<AccEntidadExterna>();

		if(StringUtils.isValidString(as_idTipoEntidad))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_cont;

				li_cont     = 1;
				lsb_sb      = new StringBuilder(cs_FIND_BY_TIPO_ENTIDAD);

				if(ab_activo)
					lsb_sb.append(" AND ACTIVO = 'S' ");

				if(ab_orden)
					lsb_sb.append(" ORDER BY NOMBRE ASC ");
				else
					lsb_sb.append(" ORDER BY NOMBRE DESC ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_cont++, as_idTipoEntidad);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lucc_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTipoEntidad", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lucc_return.isEmpty())
			lucc_return = null;

		return lucc_return;
	}

	public Collection<AccEntidadExterna> findByIdTipoOficinaActivoEntidadExenta(String as_idTipoOficina)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcaee_return;

		lcaee_return = new ArrayList<AccEntidadExterna>();

		if(StringUtils.isValidString(as_idTipoOficina))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;

				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TIPO_OFICINA_ACTIVO_ENTIDAD_EXENTA);

				lps_ps.setString(li_cont++, as_idTipoOficina);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaee_return.add(getObjectFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdTipoOficinaActivoEntidadExenta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcaee_return.isEmpty())
			lcaee_return = null;

		return lcaee_return;
	}

	/**
	 * Metodo para traer todos los registros que conincidan con el argumento enviado  de la tabla SDB_ACC_ENTIDAD_EXTERNA.
	 *
	 * @param as_like Objeto de tipo String que contiene llave por la cual buscar
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AccEntidadExterna> findByNombreEntidadLike(String as_like)
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcaee_result;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcaee_result     = new ArrayList<AccEntidadExterna>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_NOMBRE_LIKE);

			lps_ps.setString(1, as_like);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcaee_result.add(getObjectFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findbyLike", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcaee_result.isEmpty())
			lcaee_result = null;

		return lcaee_result;
	}

	/**
	 * Busca una entidad externa por el id documento
	 *
	 * @param as_tipoDocumento tipo de documento a utilizar como filtro en la busqueda
	 * @param as_numeroDocumento número de documento a utilizar como filtro en la busqueda
	 * @return Entidad externa resultante de la busqueda
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public AccEntidadExterna findByTipoDocNumDoc(String as_tipoDocumento, String as_numeroDocumento)
	    throws B2BException
	{
		AccEntidadExterna lucc_return;

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
	 * Método que consulta SDB_ACC_ENTIDAD_EXTERNA, SDB_ACC_ENTIDAD_EXTERNA_CONVENIO y extrae todas las entidades con convenio activas.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AccEntidadExterna> findEntidadesConvenio()
	    throws B2BException
	{
		Collection<AccEntidadExterna> lcee_return;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lcee_return     = new ArrayList<AccEntidadExterna>();
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ENTIDADES_CONVENIO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcee_return.add(getEntidadesConvenio(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findEntidadesConvenio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcee_return.isEmpty())
			lcee_return = null;

		return lcee_return;
	}

	/**
	 * Inserta un registro en la tabla
	 *
	 * @param aaee_entidadExterna Objeto contenedor de los datos a insertar en el registro
	 * @return Secuencia generada para el registro
	 * @throws B2BException
	 */
	public long insert(AccEntidadExterna aaee_entidadExterna)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(aaee_entidadExterna != null)
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

				lps_ps.setString(li_column++, aaee_entidadExterna.getIdDocumentoTipo());
				lps_ps.setString(li_column++, aaee_entidadExterna.getNumeroDocumento());
				lps_ps.setString(li_column++, aaee_entidadExterna.getNombre());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdRepresentanteLegal());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdActividadEconomica());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdTipoOficina());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdTipoEntidad());
				lps_ps.setString(li_column++, aaee_entidadExterna.getEntidadExenta());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdPais());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdDepartamento());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdMunicipio());
				lps_ps.setString(li_column++, aaee_entidadExterna.getDireccion());
				lps_ps.setString(li_column++, aaee_entidadExterna.getCorreoElectronico());
				lps_ps.setString(li_column++, aaee_entidadExterna.getTelefono());
				lps_ps.setString(li_column++, aaee_entidadExterna.getActivo());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIpCreacion());

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
	 * Actualiza un registro de la tabla
	 *
	 * @param aaee_entidadExterna Objeto contenedor del registro a actualizar
	 * @throws B2BException Si ocurre un error en la base de datos
	 */
	public void update(AccEntidadExterna aaee_entidadExterna)
	    throws B2BException
	{
		if(aaee_entidadExterna != null)
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

				lps_ps.setString(li_column++, aaee_entidadExterna.getIdDocumentoTipo());
				lps_ps.setString(li_column++, aaee_entidadExterna.getNumeroDocumento());
				lps_ps.setString(li_column++, aaee_entidadExterna.getNombre());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdRepresentanteLegal());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdActividadEconomica());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdTipoOficina());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdTipoEntidad());
				lps_ps.setString(li_column++, aaee_entidadExterna.getEntidadExenta());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdPais());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdDepartamento());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdMunicipio());
				lps_ps.setString(li_column++, aaee_entidadExterna.getDireccion());
				lps_ps.setString(li_column++, aaee_entidadExterna.getCorreoElectronico());
				lps_ps.setString(li_column++, aaee_entidadExterna.getTelefono());
				lps_ps.setString(li_column++, aaee_entidadExterna.getActivo());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIpModificacion());
				lps_ps.setString(li_column++, aaee_entidadExterna.getIdEntidadExterna());

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
	 * Retorna el valor de entidades convenio.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de entidades convenio
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see EntidadExterna
	 */
	private AccEntidadExterna getEntidadesConvenio(ResultSet ars_rs)
	    throws SQLException
	{
		AccEntidadExterna lr_datos;

		lr_datos = new AccEntidadExterna();

		lr_datos.setIdEntidadExterna(ars_rs.getString("CODIGO_ENTIDAD"));
		lr_datos.setNombre(ars_rs.getString("NOMBRE_ENTIDAD"));

		fillPais(ars_rs, lr_datos);

		return lr_datos;
	}

	/**
	 * Extrae los resultados de la consulta y los almacena en un objeto AccEntidadExterna
	 *
	 * @param ars_rs Objeto contenedor de los resultados de la consulta
	 * @return AccEntidadExterna resultante de la consulta
	 * @throws SQLException Si ocurre un error en la extracción de la información
	 */
	private AccEntidadExterna getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AccEntidadExterna laee_entidadExterna;

		laee_entidadExterna = new AccEntidadExterna();

		laee_entidadExterna.setIdEntidadExterna(ars_rs.getString("ID_ENTIDAD_EXTERNA"));
		laee_entidadExterna.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		laee_entidadExterna.setNumeroDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));
		laee_entidadExterna.setNombre(ars_rs.getString("NOMBRE"));
		laee_entidadExterna.setIdRepresentanteLegal(ars_rs.getString("ID_REPRESENTANTE_LEGAL"));
		laee_entidadExterna.setIdActividadEconomica(ars_rs.getString("ID_ACTIVIDAD_ECONOMICA"));
		laee_entidadExterna.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		laee_entidadExterna.setIdTipoEntidad(ars_rs.getString("ID_TIPO_ENTIDAD"));
		laee_entidadExterna.setEntidadExenta(ars_rs.getString("ENTIDAD_EXENTA"));
		laee_entidadExterna.setIdPais(ars_rs.getString("ID_PAIS"));
		laee_entidadExterna.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		laee_entidadExterna.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		laee_entidadExterna.setTelefono(ars_rs.getString("TELEFONO"));
		laee_entidadExterna.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		laee_entidadExterna.setDireccion(ars_rs.getString("DIRECCION"));
		laee_entidadExterna.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, laee_entidadExterna);

		return laee_entidadExterna;
	}
}
