package com.bachue.snr.prosnr01.dao.aut;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.aut.Opcion;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_AUT_OPCION.
 *
 * @author Duvan Beltran
 */
public class OpcionDAO extends AuditoriaDao
{
	/**Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_AUT_OPCION";

	/** Constante cs_FIND_ALL_BY_USUARIO. */
	private static final String cs_FIND_ALL_BY_USUARIO = "SELECT * FROM SDB_AUT_OPCION WHERE ID_OPCION IN (SELECT DISTINCT ID_OPCION FROM SDB_AUT_ROL_OPCION WHERE ID_ROL IN (SELECT ID_ROL FROM SDB_AUT_USUARIO_ROL WHERE ID_USUARIO = ? AND ACTIVO = 'S') AND ACTIVO = 'S') AND ACTIVO = 'S' AND ID_COMPONENTE = ?  ORDER BY DESCRIPCION ASC ";

	/**Constante cs_FIND_BY_ID*/
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_AUT_OPCION WHERE ID_OPCION = ?";

	/**Constante cs_FIND_BY_URL*/
	private static final String cs_FIND_BY_URL = "SELECT * FROM SDB_AUT_OPCION WHERE URL = ?";

	/**Constante cs_SECUENCE*/
	private static final String cs_SECUENCE = "SELECT SEC_AUT_OPCION_ID_OPCION.NEXTVAL FROM DUAL";

	/**Constante cs_INSERT*/
	private static final String cs_INSERT = "INSERT INTO SDB_AUT_OPCION (ID_OPCION, OPCION, DESCRIPCION, ID_OPCION_PADRE, TIPO, URL, FECHA_DESDE, FECHA_HASTA, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_COMPONENTE, VENTANA_NUEVA,URL_CATEDRA,OPCION_FRECUENTE ) VALUES(?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?)";

	/**Constante cs_UPDATE*/
	private static final String cs_UPDATE = "UPDATE SDB_AUT_OPCION SET OPCION = ?, DESCRIPCION = ?, ID_OPCION_PADRE = ?, TIPO = ?, URL = ?, FECHA_DESDE = ?, FECHA_HASTA = ?, ACTIVO = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, ID_COMPONENTE = ?, VENTANA_NUEVA = ?, URL_CATEDRA=?, OPCION_FRECUENTE=? WHERE ID_OPCION = ?";

	/** Constante cs_FIND_ALL_COMPONENTE. */
	private static final String cs_FIND_ALL_COMPONENTE = "SELECT * FROM SDB_AUT_OPCION WHERE TIPO='PANTALLA' AND ACTIVO='S' AND ID_COMPONENTE=? ORDER BY OPCION ASC";

	/**
	 * Consulta todas las opciones y los filtra por un id componente previamente definido
	 *
	 * @param as_idComponente String del id_componente para aplicar al filtro
	 * @return Colección de Opcion resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<Opcion> findByComponente(String as_idComponente)
	    throws B2BException
	{
		Collection<Opcion> lco_opcion;
		lco_opcion = new ArrayList<Opcion>();

		if(StringUtils.isValidString(as_idComponente))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_COMPONENTE);

				lps_ps.setString(1, as_idComponente);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lco_opcion.add(getOpcion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByComponente", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lco_opcion.isEmpty())
			lco_opcion = null;

		return lco_opcion;
	}

	/**
	 * Metodo de consulta de la tabla  SDB_AUT_OPCION
	 * @return Una colección la consulta de toda la tabla
	 * @throws B2BException en caso de errorf en la ejecución de la función
	 */
	public Collection<Opcion> findAll()
	    throws B2BException
	{
		return findAll(false);
	}

	/**
	 * Metodo de consulta de la tabla  SDB_AUT_OPCION
	 * @return Una colección la consulta de toda la tabla
	 * @throws B2BException en caso de errorf en la ejecución de la función
	 */
	public Collection<Opcion> findAll(boolean ab_orden)
	    throws B2BException
	{
		Collection<Opcion> lcro_datos;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcro_datos     = new ArrayList<Opcion>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			StringBuilder lsb_sb;

			lsb_sb = new StringBuilder(cs_FIND_ALL);

			if(ab_orden)
				lsb_sb.append(" ORDER BY OPCION ASC ");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcro_datos.add(getOpcion(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcro_datos))
			lcro_datos = null;

		return lcro_datos;
	}

	/**
	 * Busca todos los registros asociados a los roles de un usuario
	 *
	 * @param as_userId Id del usuario a utilizar como filtro en la busqueda
	 * @return Colección de opciones resultante de la busqueda
	 * @throws B2BException si ocurre un error en la base de datos
	 */
	public Collection<Opcion> findAllByUsuario(String as_userId, String as_idComponente)
	    throws B2BException
	{
		Collection<Opcion> lco_datos;

		lco_datos = new ArrayList<Opcion>();

		if(StringUtils.isValidString(as_userId))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_USUARIO);

				lps_ps.setString(1, as_userId);
				lps_ps.setString(2, as_idComponente);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lco_datos.add(getOpcion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllByUsuario", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lco_datos.isEmpty())
			lco_datos = null;

		return lco_datos;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_AUT_OPCION
	 */
	public void insert(Opcion ao_param)
	    throws B2BException
	{
		if(ao_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCE);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							ao_param.setIdOpcion(StringUtils.getString(lo_o));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, ao_param.getIdOpcion());
				lps_ps.setString(li_column++, ao_param.getOpcion());
				lps_ps.setString(li_column++, ao_param.getDescripcion());
				lps_ps.setString(li_column++, ao_param.getIdOpcionPadre());
				lps_ps.setString(li_column++, ao_param.getTipo());
				lps_ps.setString(li_column++, ao_param.getUrl());
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ao_param.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ao_param.getFechaHasta()));
				lps_ps.setString(li_column++, ao_param.getActivo());
				lps_ps.setString(li_column++, ao_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, ao_param.getIpCreacion());
				lps_ps.setString(li_column++, ao_param.getIdComponente());
				lps_ps.setString(li_column++, ao_param.getVentanaNueva());
				lps_ps.setString(li_column++, ao_param.getUrlCatedra());
				lps_ps.setString(li_column++, ao_param.getOpcionFrecuente());

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
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_AUT_OPCION para un id_opcion específico.
	 *
	 * @param atd_param Objeto de tipo Opcion que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto Opcion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see TipoDocumental
	 */
	public Opcion findById(Opcion atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdOpcion()) : null);
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_AUT_OPCION para un id_opcion específico.
	 *
	 * @param as_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto opcion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Opcion
	 */
	public Opcion findById(String as_param)
	    throws B2BException
	{
		Opcion ltd_return;
		ltd_return = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getOpcion(lrs_rs);
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

		return ltd_return;
	}

	/**
	 * Metodo para encontrar todos los registros existentes en la tabla SDB_AUT_OPCION para un url específico.
	 *
	 * @param as_param Objeto de tipo String que contiene parametros a utilizar como filtro en la busqueda.
	 * @return devuelve el valor del objeto opcion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Opcion
	 */
	public String findUrlCatedra(String as_urlBachue)
	    throws B2BException
	{
		String ls_return;
		ls_return = null;

		if(as_urlBachue != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_URL);

				lps_ps.setString(1, as_urlBachue);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_return = getOpcion(lrs_rs).getUrlCatedra();
			}
			catch(SQLException lse_e)
			{
				logError(this, "findUrlCatedra", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ls_return;
	}

	/**
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_AUT_OPCION
	 */
	public void update(Opcion ao_param)
	    throws B2BException
	{
		if(ao_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, ao_param.getOpcion());
				lps_ps.setString(li_column++, ao_param.getDescripcion());
				lps_ps.setString(li_column++, ao_param.getIdOpcionPadre());
				lps_ps.setString(li_column++, ao_param.getTipo());
				lps_ps.setString(li_column++, ao_param.getUrl());
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ao_param.getFechaDesde()));
				lps_ps.setDate(li_column++, DateUtils.getSQLDate(ao_param.getFechaHasta()));
				lps_ps.setString(li_column++, ao_param.getActivo());
				lps_ps.setString(li_column++, ao_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, ao_param.getIpModificacion());
				lps_ps.setString(li_column++, ao_param.getIdComponente());
				lps_ps.setString(li_column++, ao_param.getVentanaNueva());
				lps_ps.setString(li_column++, ao_param.getUrlCatedra());
				lps_ps.setString(li_column++, ao_param.getOpcionFrecuente());

				lps_ps.setString(li_column++, ao_param.getIdOpcion());
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

	private Opcion getOpcion(ResultSet ars_rs)
	    throws SQLException
	{
		Opcion lr_datos;

		lr_datos = new Opcion();

		lr_datos.setIdOpcion(ars_rs.getString("ID_OPCION"));
		lr_datos.setOpcion(ars_rs.getString("OPCION"));
		lr_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lr_datos.setIdOpcionPadre(ars_rs.getString("ID_OPCION_PADRE"));
		lr_datos.setTipo(ars_rs.getString("TIPO"));
		lr_datos.setUrl(ars_rs.getString("URL"));
		lr_datos.setFechaDesde(ars_rs.getDate("FECHA_DESDE"));
		lr_datos.setFechaHasta(ars_rs.getDate("FECHA_HASTA"));
		lr_datos.setActivo(ars_rs.getString("ACTIVO"));
		lr_datos.setIdComponente(ars_rs.getString("ID_COMPONENTE"));
		lr_datos.setVentanaNueva(ars_rs.getString("VENTANA_NUEVA"));
		lr_datos.setUrlCatedra(ars_rs.getString("URL_CATEDRA"));
		lr_datos.setOpcionFrecuente(ars_rs.getString("OPCION_FRECUENTE"));

		fillAuditoria(ars_rs, lr_datos);

		return lr_datos;
	}
}
