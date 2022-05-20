package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.Componente;

import org.apache.commons.io.IOUtils;

import java.io.ByteArrayInputStream;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


/**
 * Clase que contiene todos las propiedades de AdministracionComponentesDAO.
 *
 * @author Sebastian Sanchez
 */
public class ComponentesDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM  SDB_AUT_COMPONENTE ORDER BY NOMBRE";

	/** Constante cs_FIND_ALL_ACTIVO. */
	private static final String cs_FIND_ALL_ACTIVO = "SELECT * FROM SDB_AUT_COMPONENTE WHERE ACTIVO = 'S' ORDER BY LENGTH(ID_COMPONENTE), ID_COMPONENTE";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM  SDB_AUT_COMPONENTE WHERE ID_COMPONENTE = ? ";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_COMPONENTE "
		+ "(ID_COMPONENTE, NOMBRE, FECHA_DESDE, FECHA_HASTA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, IMAGEN, URL, BOTON_COMPONENTE) "
		+ "VALUES(?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_AUT_COMPONENTE SET NOMBRE=?, FECHA_DESDE=?, FECHA_HASTA=?, ACTIVO=?, "
		+ "ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, URL=?, BOTON_COMPONENTE=? ";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_AUT_COMPONENTE_ID_COMPONENTE.NEXTVAL FROM DUAL";

	/**Constante cs_FIND_USUARIO_COMPONENTE**/
	private static final String cs_FIND_USUARIO_COMPONENTE = "SELECT DISTINCT SAC.ID_COMPONENTE, SAC.NOMBRE, SAC.FECHA_DESDE, SAC.FECHA_HASTA, SAC.ACTIVO, "
		+ "SAC.ID_USUARIO_CREACION, SAC.FECHA_CREACION, SAC.IP_CREACION, SAC.ID_USUARIO_MODIFICACION, SAC.FECHA_MODIFICACION, SAC.IP_MODIFICACION, SAC.URL, SAC.BOTON_COMPONENTE "
		+ " FROM ( SELECT DISTINCT SAC.ID_COMPONENTE, SAC.NOMBRE, SAC.FECHA_DESDE, SAC.FECHA_HASTA, SAC.ACTIVO, "
		+ "  SAC.ID_USUARIO_CREACION, SAC.FECHA_CREACION, SAC.IP_CREACION, SAC.ID_USUARIO_MODIFICACION, SAC.FECHA_MODIFICACION, SAC.IP_MODIFICACION, SAC.URL, SAC.BOTON_COMPONENTE "
		+ "   FROM SDB_AUT_COMPONENTE SAC INNER JOIN SDB_AUT_ROL SAR ON (SAC.ID_COMPONENTE=SAR.ID_COMPONENTE) INNER JOIN SDB_AUT_USUARIO_ROL SAUR ON (SAUR.ID_ROL=SAR.ID_ROL) WHERE SAUR.ID_USUARIO = ? "
		+ "UNION ALL SELECT DISTINCT SAC.ID_COMPONENTE, SAC.NOMBRE, SAC.FECHA_DESDE, SAC.FECHA_HASTA, SAC.ACTIVO, "
		+ "  SAC.ID_USUARIO_CREACION, SAC.FECHA_CREACION, SAC.IP_CREACION, SAC.ID_USUARIO_MODIFICACION, SAC.FECHA_MODIFICACION, SAC.IP_MODIFICACION, SAC.URL, SAC.BOTON_COMPONENTE "
		+ "   FROM SDB_AUT_COMPONENTE SAC WHERE SAC.ID_COMPONENTE IN ('3','6')) SAC";

	/**
	 * Consulta en base de datos todos los registros que se encuentren
	 * @return Colección de Administracion Componentes resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Componente> findAll()
	    throws B2BException
	{
		Collection<Componente> lcac_cac;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lcac_cac     = new ArrayList<Componente>();
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
				lcac_cac.add(getObjetFromResultSet(lrs_rs));
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

		if(lcac_cac.isEmpty())
			lcac_cac = null;

		return lcac_cac;
	}

	/**
	 * Retorna el valor del objeto de Collection de Componente.
	 *
	 * @return devuelve el valor de Collection de Componente
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Componente
	 */
	public Collection<Componente> findAllActivo()
	    throws B2BException
	{
		Collection<Componente> lc_componente;
		PreparedStatement      lps_ps;
		ResultSet              lrs_rs;

		lc_componente     = new ArrayList<Componente>();
		lps_ps            = null;
		lrs_rs            = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL_ACTIVO);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_componente.add(getObjetFromResultSet(lrs_rs, true));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllActivo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lc_componente.isEmpty())
			lc_componente = null;

		return lc_componente;
	}

	/**
	 * Busca todos los registros asociados a los roles de un usuario
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @return Mapa de opciones resultante de la busqueda
	 * @throws B2BException si ocurre un error en la base de datos
	 */
	public Map<String, Componente> findAllComponenteUsuario(String as_userId)
	    throws B2BException
	{
		Map<String, Componente> lmsc_datos;

		lmsc_datos = new HashMap<String, Componente>(1);

		if(StringUtils.isValidString(as_userId))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_USUARIO_COMPONENTE);

				lps_ps.setString(1, as_userId);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lmsc_datos.put(lrs_rs.getString("ID_COMPONENTE"), getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllComponenteUsuario", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lmsc_datos.isEmpty())
			lmsc_datos = null;

		return lmsc_datos;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_componente
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Registro resultante de la consulta
	 * @throws B2BException
	 */
	public Componente findById(String as_param)
	    throws B2BException
	{
		return findById(as_param, false);
	}

	/**
	 * Busca un registro de la tabla por medio de su id_componente.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @param ab_activo de ab activo, true para filtrar por registros activos, false para no filtrar por el estado
	 * @return Registro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Componente findById(String as_param, boolean ab_activo)
	    throws B2BException
	{
		Componente lac_object;

		lac_object = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID);

				if(ab_activo)
					lsb_query.append(" AND ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_object = getObjetFromResultSet(lrs_rs);
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

		return lac_object;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_componente.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @param ab_activo de ab activo, true para filtrar por registros activos, false para no filtrar por el estado
	 * @return Registro resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Componente findByIdWithImage(String as_param, boolean ab_activo)
	    throws B2BException
	{
		Componente lac_object;

		lac_object = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;

				lsb_query = new StringBuilder(cs_FIND_BY_ID);

				if(ab_activo)
					lsb_query.append(" AND ACTIVO = 'S' ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lac_object = getObjetFromResultSet(lrs_rs, true);
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

		return lac_object;
	}

	/**
	 * Inserta un registro
	 * con la información de la administracion componentes suministrada.
	 *
	 * @param as_param objeto contenedor de la información a insertar
	 * @throws B2BException
	 */
	public void insert(Componente ac_param)
	    throws B2BException
	{
		if(ac_param != null)
		{
			int               li_column;
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			li_column         = 1;

			try
			{
				Connection lc_C;

				lc_C       = getConnection();
				lps_ps     = lc_C.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_C.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
					lps_ps.setString(li_column++, StringUtils.getString(lrs_rs.getInt(1)));
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					byte[] lba_imagen;

					lba_imagen = ac_param.getImagen();

					lps_ps.setString(li_column++, ac_param.getNombre());
					setDate(lps_ps, ac_param.getFechaDesde(), li_column++);
					setDate(lps_ps, ac_param.getFechaHasta(), li_column++);
					lps_ps.setString(li_column++, ac_param.getActivo());
					lps_ps.setString(li_column++, ac_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, ac_param.getIpCreacion());

					if(lba_imagen != null)
						lps_ps.setBinaryStream(li_column++, new ByteArrayInputStream(lba_imagen));
					else
						lps_ps.setBinaryStream(li_column++, null);

					lps_ps.setString(li_column++, ac_param.getUrl());
					lps_ps.setString(li_column++, ac_param.getBotonComponente());
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
				close(lrs_rs);
				close(lps_secuencia);
			}
		}
	}

	/**
	 * Actualiza un registro
	 * con la información de la administracion componentes suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar
	 * @throws B2BException
	 */
	public void update(Componente ac_param)
	    throws B2BException
	{
		if(ac_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;
				boolean       lb_conImagen;

				li_column        = 1;
				lsb_query        = new StringBuilder(cs_UPDATE);
				lb_conImagen     = ac_param.getImagen() != null;

				if(lb_conImagen)
					lsb_query.append(", IMAGEN = ? ");

				lsb_query.append(" WHERE ID_COMPONENTE=? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, ac_param.getNombre());
				setDate(lps_ps, ac_param.getFechaDesde(), li_column++);
				setDate(lps_ps, ac_param.getFechaHasta(), li_column++);
				lps_ps.setString(li_column++, ac_param.getActivo());
				lps_ps.setString(li_column++, ac_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ac_param.getIpModificacion());

				if(lb_conImagen)
					lps_ps.setBinaryStream(li_column++, new ByteArrayInputStream(ac_param.getImagen()));

				lps_ps.setString(li_column++, ac_param.getUrl());
				lps_ps.setString(li_column++, ac_param.getBotonComponente());
				lps_ps.setString(li_column++, ac_param.getIdComponente());

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
	 * @param ars_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 * @throws B2BException
	 */
	private Componente getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		return getObjetFromResultSet(ars_rs, false);
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param ars_rs contenedor del resultado de a consulta
	 * @param ab_traerImagen Valiable de tipo boolean que indica si se debe traer el campo IMAGEN.
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 * @throws B2BException
	 */
	private Componente getObjetFromResultSet(ResultSet ars_rs, boolean ab_traerImagen)
	    throws SQLException, B2BException
	{
		Componente lc_componente;

		lc_componente = new Componente();

		try
		{
			lc_componente.setIdComponente(ars_rs.getString("ID_COMPONENTE"));
			lc_componente.setNombre(ars_rs.getString("NOMBRE"));
			lc_componente.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
			lc_componente.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
			lc_componente.setActivo(ars_rs.getString("ACTIVO"));
			lc_componente.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			lc_componente.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			lc_componente.setIpCreacion(ars_rs.getString("IP_CREACION"));
			lc_componente.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
			lc_componente.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
			lc_componente.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
			lc_componente.setUrl(ars_rs.getString("URL"));
			lc_componente.setBotonComponente(ars_rs.getString("BOTON_COMPONENTE"));

			if(ab_traerImagen)
			{
				java.sql.Blob lb_blob;
				byte[]        lba_data;

				lb_blob = ars_rs.getBlob("IMAGEN");

				try
				{
					lba_data = IOUtils.toByteArray(lb_blob.getBinaryStream());
				}
				catch(Exception e)
				{
					lba_data = null;
				}

				lc_componente.setImagen(lba_data);
			}
		}
		catch(Exception le_e)
		{
			throw new B2BException(ErrorKeys.CONTAINER_ERROR, le_e);
		}

		return lc_componente;
	}
}
