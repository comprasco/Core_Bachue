package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExterna;
import com.bachue.snr.prosnr01.model.sdb.acc.AccEntidadExternaConvenio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_ENTIDAD_EXTERNA_CONVENIO.
 *
 * @author Sebastian Sanchez
 */
public class AccEntidadExternaConvenioDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO ";

	/** Constante  cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = " SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO WHERE ID_ENTIDAD_EXTERNA = ? AND NUMERO_CONVENIO= ?";

	/** Constante  cs_FIND_BY_NUMERO_CONVENIO. */
	private static final String cs_FIND_BY_NUMERO_CONVENIO = " SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO WHERE NUMERO_CONVENIO= ?";

	/** Constante  cs_FIND_BY_ID_ENTIDAD_EXTERNA. */
	private static final String cs_FIND_BY_ID_ENTIDAD_EXTERNA = " SELECT * FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO WHERE ID_ENTIDAD_EXTERNA= ?";

	/** Constante  cs_FIND_BY_NOMBRE_ENTIDAD. */
	private static final String cs_FIND_BY_NOMBRE_ENTIDAD = " SELECT SAEEC.* FROM SDB_ACC_ENTIDAD_EXTERNA_CONVENIO SAEEC "
		+ " INNER JOIN SDB_ACC_ENTIDAD_EXTERNA SAEE ON SAEE.ID_ENTIDAD_EXTERNA = SAEEC.ID_ENTIDAD_EXTERNA "
		+ " WHERE SAEE.NOMBRE LIKE ? ORDER BY SAEE.NOMBRE ASC";

	/** Constante  cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_ENTIDAD_EXTERNA_CONVENIO(ID_ENTIDAD_EXTERNA, NUMERO_CONVENIO, NOMBRE_CONVENIO, "
		+ "DOCUMENTO_APROBADOR, IDENTIFICACION_DOCUMENTO_APROBADOR, FECHA_CREACION_CONVENIO,"
		+ "FECHA_VENCIMIENTO, ID_TIPO_ENTIDAD, ACTIVO, NUMERO_DOCUMENTO_RENOVACION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?)";

	/** Constante  cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_ENTIDAD_EXTERNA_CONVENIO SET NOMBRE_CONVENIO=?, "
		+ "DOCUMENTO_APROBADOR=?, IDENTIFICACION_DOCUMENTO_APROBADOR=?,"
		+ "FECHA_CREACION_CONVENIO=?, FECHA_VENCIMIENTO=?, ID_TIPO_ENTIDAD=?, ACTIVO=?, NUMERO_DOCUMENTO_RENOVACION=?, ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, "
		+ "FECHA_MODIFICACION = SYSTIMESTAMP WHERE ID_ENTIDAD_EXTERNA=? AND NUMERO_CONVENIO=?";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @param ab_accion correspondiente al valor del tipo de objeto boolean
	 * @return Colección de AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findAll(boolean ab_accion)
	    throws B2BException
	{
		Collection<AccEntidadExternaConvenio> lcaeec_caeec;
		PreparedStatement                     lps_ps;
		ResultSet                             lrs_rs;

		lcaeec_caeec     = new ArrayList<AccEntidadExternaConvenio>();
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder(cs_FIND_ALL);

			if(ab_accion)
				lsb_sb.append(" WHERE ACTIVO = 'S' ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcaeec_caeec.add(getObjetFromResultSet(lrs_rs));
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

		if(lcaeec_caeec.isEmpty())
			lcaeec_caeec = null;

		return lcaeec_caeec;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_numeroConvenio Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExternaConvenio findByConvenioNumero(String as_numeroConvenio)
	    throws B2BException
	{
		AccEntidadExternaConvenio laeec_entidadExternaConvenio;

		laeec_entidadExternaConvenio = null;

		if(StringUtils.isValidString(as_numeroConvenio))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_NUMERO_CONVENIO);

				lps_ps.setString(1, as_numeroConvenio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laeec_entidadExternaConvenio = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByConvenioNumero", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return laeec_entidadExternaConvenio;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aaeec_param Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AccEntidadExternaConvenio findById(AccEntidadExternaConvenio aaeec_param)
	    throws B2BException
	{
		AccEntidadExternaConvenio laeec_entidadExternaConvenio;

		laeec_entidadExternaConvenio = null;

		if(aaeec_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, aaeec_param.getIdEntidadExterna());
				lps_ps.setString(2, aaeec_param.getNumeroConvenio());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					laeec_entidadExternaConvenio = getObjetFromResultSet(lrs_rs);
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

		return laeec_entidadExternaConvenio;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_numeroConvenio Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findByNumeroConvenio(String as_numeroConvenio)
	    throws B2BException
	{
		Collection<AccEntidadExternaConvenio> lcaeec_entidadExternaConvenio;

		lcaeec_entidadExternaConvenio = new ArrayList<AccEntidadExternaConvenio>();

		if(StringUtils.isValidString(as_numeroConvenio))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_NUMERO_CONVENIO);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_numeroConvenio);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_entidadExternaConvenio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNumeroConvenio", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaeec_entidadExternaConvenio;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param aaee_entidad Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findByIdEntidad(AccEntidadExterna aaee_entidad)
	    throws B2BException
	{
		Collection<AccEntidadExternaConvenio> lcaeec_entidadExternaConvenio;

		lcaeec_entidadExternaConvenio = new ArrayList<AccEntidadExternaConvenio>();

		if(aaee_entidad != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_ID_ENTIDAD_EXTERNA);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, aaee_entidad.getIdEntidadExterna());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_entidadExternaConvenio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdEntidad", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaeec_entidadExternaConvenio;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_nombreEntidad Objeto contenedor de los filtros a usar en la consulta
	 * @return AccEntidadExternaConvenio resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AccEntidadExternaConvenio> findByNombreEntidad(String as_nombreEntidad)
	    throws B2BException
	{
		Collection<AccEntidadExternaConvenio> lcaeec_entidadExternaConvenio;

		lcaeec_entidadExternaConvenio = new ArrayList<AccEntidadExternaConvenio>();

		if(StringUtils.isValidString(as_nombreEntidad))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			int               li_column;
			StringBuilder     lsb_sb;

			lps_ps        = null;
			lrs_rs        = null;
			li_column     = 1;
			lsb_sb        = new StringBuilder(cs_FIND_BY_NOMBRE_ENTIDAD);

			try
			{
				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, as_nombreEntidad);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcaeec_entidadExternaConvenio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByNombreEntidad", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcaeec_entidadExternaConvenio;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param aaeec_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(AccEntidadExternaConvenio aaeec_param)
	    throws B2BException
	{
		if(aaeec_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;

			lps_ps        = null;
			li_column     = 1;

			try
			{
				StringBuilder lsb_query;

				lsb_query     = new StringBuilder(cs_INSERT);

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				{
					lps_ps.setString(li_column++, aaeec_param.getIdEntidadExterna());
					lps_ps.setString(li_column++, aaeec_param.getNumeroConvenio());
					lps_ps.setString(li_column++, aaeec_param.getNombreConvenio());
					lps_ps.setString(li_column++, aaeec_param.getDocumentoAprobador());
					lps_ps.setString(li_column++, aaeec_param.getIdentificadorDocumentoAprobador());
					lps_ps.setDate(li_column++, DateUtils.getSQLDate(aaeec_param.getFechaCreacionConvenio()));
					lps_ps.setDate(li_column++, DateUtils.getSQLDate(aaeec_param.getFechaVencimiento()));
					lps_ps.setString(li_column++, aaeec_param.getIdTipoEntidad());
					lps_ps.setString(li_column++, aaeec_param.getActivo());
					lps_ps.setString(li_column++, aaeec_param.getNumeroDocumentoRenovacion());
					lps_ps.setString(li_column++, aaeec_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aaeec_param.getIpCreacion());
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
				close(lps_ps);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param aaeec_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(AccEntidadExternaConvenio aaeec_param)
	    throws B2BException
	{
		if(aaeec_param != null)
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

				{
					lps_ps.setString(li_column++, aaeec_param.getNombreConvenio());
					lps_ps.setString(li_column++, aaeec_param.getDocumentoAprobador());
					lps_ps.setString(li_column++, aaeec_param.getIdentificadorDocumentoAprobador());
					lps_ps.setDate(li_column++, DateUtils.getSQLDate(aaeec_param.getFechaCreacionConvenio()));
					lps_ps.setDate(li_column++, DateUtils.getSQLDate(aaeec_param.getFechaVencimiento()));
					lps_ps.setString(li_column++, aaeec_param.getIdTipoEntidad());
					lps_ps.setString(li_column++, aaeec_param.getActivo());
					lps_ps.setString(li_column++, aaeec_param.getNumeroDocumentoRenovacion());
					lps_ps.setString(li_column++, aaeec_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aaeec_param.getIpCreacion());
					lps_ps.setString(li_column++, aaeec_param.getIdEntidadExterna());
					lps_ps.setString(li_column++, aaeec_param.getNumeroConvenio());
				}

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
	private AccEntidadExternaConvenio getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		AccEntidadExternaConvenio ls_solicitud;

		ls_solicitud = new AccEntidadExternaConvenio();

		ls_solicitud.setIdEntidadExterna(lrs_rs.getString("ID_ENTIDAD_EXTERNA"));
		ls_solicitud.setNumeroConvenio(lrs_rs.getString("NUMERO_CONVENIO"));
		ls_solicitud.setNombreConvenio(lrs_rs.getString("NOMBRE_CONVENIO"));
		ls_solicitud.setDocumentoAprobador(lrs_rs.getString("DOCUMENTO_APROBADOR"));
		ls_solicitud.setIdentificadorDocumentoAprobador(lrs_rs.getString("IDENTIFICACION_DOCUMENTO_APROBADOR"));
		ls_solicitud.setFechaCreacionConvenio(lrs_rs.getTimestamp("FECHA_CREACION_CONVENIO"));
		ls_solicitud.setFechaVencimiento(lrs_rs.getTimestamp("FECHA_VENCIMIENTO"));
		ls_solicitud.setIdTipoEntidad(lrs_rs.getString("ID_TIPO_ENTIDAD"));
		ls_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		ls_solicitud.setNumeroDocumentoRenovacion(lrs_rs.getString("NUMERO_DOCUMENTO_RENOVACION"));
		ls_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		ls_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		ls_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		ls_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));

		return ls_solicitud;
	}
}
