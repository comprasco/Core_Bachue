package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.model.sdb.pgn.OficinaOrigen;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_PGN_OFICINA_ORIGEN.
 *
 * @author lchacon
 */
public class OficinaOrigenDAO extends PaisDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_OFICINA_ORIGEN, ID_TIPO_OFICINA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_VEREDA, NOMBRE, NUMERO,  NOMBRE_TITULAR, DIRECCION, TELEFONO, CORREO_ELECTRONICO, FAX, ID_UNICO, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, NOTIFICAR_CORRESPONDENCIA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION,ACTIVO FROM SDB_PGN_OFICINA_ORIGEN WHERE ID_OFICINA_ORIGEN=? AND VERSION = ?";
	private static final String cs_FIND_BY_ID_ONLY = "SELECT ID_OFICINA_ORIGEN, ID_TIPO_OFICINA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_VEREDA, NOMBRE, NUMERO,  NOMBRE_TITULAR, DIRECCION, TELEFONO, CORREO_ELECTRONICO, FAX, ID_UNICO, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, NOTIFICAR_CORRESPONDENCIA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION,ACTIVO FROM SDB_PGN_OFICINA_ORIGEN WHERE ID_OFICINA_ORIGEN=?";

	/** Constante cs_FIND_BY_ID_CIRCULO. */
	private static final String cs_FIND_BY_ID_CIRCULO = "SELECT * FROM SDB_PGN_OFICINA_ORIGEN SPOO INNER JOIN SDB_PGN_CIRCULO_REGISTRAL SPCR ON SPOO.ID_OFICINA_ORIGEN = SPCR.ID_OFICINA_ORIGEN AND SPOO.VERSION = SPCR.VERSION WHERE SPCR.ID_CIRCULO = ? ";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT ID_OFICINA_ORIGEN, ID_TIPO_OFICINA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_VEREDA, NOMBRE, NUMERO,  NOMBRE_TITULAR, DIRECCION, TELEFONO, CORREO_ELECTRONICO, FAX, ID_UNICO, VERSION, ID_USUARIO_CREACION, FECHA_CREACION, NOTIFICAR_CORRESPONDENCIA, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION,ACTIVO FROM SDB_PGN_OFICINA_ORIGEN ORDER BY NOMBRE ASC";

	/** Constante cs_FIND_ALL_CRITERIOS. */
	private static final String cs_FIND_ALL_CRITERIOS = "SELECT * FROM SDB_PGN_OFICINA_ORIGEN WHERE ACTIVO = 'S' ";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_OFICINA_ORIGEN SET ID_TIPO_OFICINA=?, ID_PAIS=?, ID_DEPARTAMENTO=?, ID_MUNICIPIO=?, ID_VEREDA=?, NOMBRE=?, NUMERO=?,  NOMBRE_TITULAR=?, DIRECCION=?, TELEFONO=?, NOTIFICAR_CORRESPONDENCIA=?, CORREO_ELECTRONICO=?, FAX=?, ID_UNICO=?, ACTIVO=?,ID_USUARIO_MODIFICACION=?, IP_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP WHERE ID_OFICINA_ORIGEN=? AND VERSION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_OFICINA_ORIGEN(ID_OFICINA_ORIGEN, ID_TIPO_OFICINA, ID_PAIS, ID_DEPARTAMENTO, ID_MUNICIPIO, ID_VEREDA, NOMBRE, NUMERO,  NOMBRE_TITULAR, DIRECCION, TELEFONO, NOTIFICAR_CORRESPONDENCIA, CORREO_ELECTRONICO, FAX, ID_UNICO, ACTIVO, VERSION, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION)VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP)";

	/** Constante cs_FIND_FILTERS. */
	private static final String cs_FIND_FILTERS = "SELECT O.ID_OFICINA_ORIGEN, O.ID_TIPO_OFICINA, O.ID_PAIS, O.ID_DEPARTAMENTO, O.ID_MUNICIPIO, O.ID_VEREDA, O.NOMBRE, NUMERO,  O.NOMBRE_TITULAR, O.DIRECCION, O.TELEFONO "
		+ ",O.CORREO_ELECTRONICO, O.FAX, O.ID_UNICO , O.VERSION, O.ID_USUARIO_CREACION, O.FECHA_CREACION, O.NOTIFICAR_CORRESPONDENCIA, O.IP_CREACION, O.ID_USUARIO_MODIFICACION, O.FECHA_MODIFICACION, O.IP_MODIFICACION,ACTIVO "
		+ "FROM SDB_PGN_OFICINA_ORIGEN O " + ",(Select ID_OFICINA_ORIGEN,max(VERSION) as MVERSION  "
		+ "  from SDB_PGN_OFICINA_ORIGEN   " + "  where ID_TIPO_OFICINA= ?  " + "  AND ID_PAIS= ? "
		+ "  AND ID_DEPARTAMENTO= ?  " + "  AND ID_MUNICIPIO= ? " + "  and activo = 'S'"
		+ "GROUP BY ID_OFICINA_ORIGEN) P  " + "WHERE O.ID_TIPO_OFICINA= ?  " + "AND O.ID_PAIS= ? "
		+ "AND O.ID_DEPARTAMENTO= ? " + "AND O.ID_MUNICIPIO= ?  " + "AND O.ID_OFICINA_ORIGEN = P.ID_OFICINA_ORIGEN "
		+ "and O.VERSION = P.MVERSION " + "and O.activo = 'S' " + "ORDER BY O.NOMBRE ASC";

	/** Constante cs_FIND_SECUENCE_OFICINA_ORIGEN. */
	private static final String cs_FIND_SECUENCE_OFICINA_ORIGEN = "SELECT SEC_PGN_OFICINA_ORIGEN_ID_OFICINA_ORIGEN.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_MAX_VERSION. */
	private static final String cs_FIND_MAX_VERSION = "SELECT MAX(VERSION) FROM SDB_PGN_OFICINA_ORIGEN WHERE ID_OFICINA_ORIGEN = ?";

	/** Constante cs_FIND_BY_SOLICITUD. */
	private static final String cs_FIND_BY_SOLICITUD = "SELECT OFI.* FROM SDB_ACC_SOLICITUD SOL INNER JOIN SDB_BGN_DOCUMENTO DOC ON SOL.ID_DOCUMENTO = DOC.ID_DOCUMENTO INNER JOIN SDB_PGN_OFICINA_ORIGEN OFI ON DOC.ID_OFICINA_ORIGEN = OFI.ID_OFICINA_ORIGEN WHERE SOL.ID_SOLICITUD = ? ORDER BY OFI.VERSION DESC";

	/** Constante cs_FIND_BY_TIPO_OFICINA. */
	private static final String cs_FIND_BY_TIPO_OFICINA = "SELECT * FROM SDB_PGN_OFICINA_ORIGEN WHERE ID_TIPO_OFICINA = ?";

	/**
	 * Fill oficina origen.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param aoo_oficinaOrigen correspondiente al valor del tipo de objeto OficinaOrigen
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	public void fillOficinaOrigen(java.sql.ResultSet ars_rs, OficinaOrigen aoo_oficinaOrigen)
	    throws java.sql.SQLException
	{
		if(aoo_oficinaOrigen != null)
		{
			aoo_oficinaOrigen.setIdOficinaOrigen(ars_rs.getString("CODIGO_OFICINA_ORIGEN"));

			fillPais(ars_rs, aoo_oficinaOrigen);
		}
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @param aoo_oficinaOrigen correspondiente al valor del tipo de objeto OficinaOrigen
	 * @return devuelve el valor del objeto collection de OficinaOrigen
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	public Collection<OficinaOrigen> findAllCriterios(OficinaOrigen aoo_oficinaOrigen)
	    throws B2BException
	{
		Collection<OficinaOrigen> lcoo_object;
		lcoo_object                                = new ArrayList<OficinaOrigen>();

		if(aoo_oficinaOrigen != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;
				boolean       lb_idTipoOficina;
				boolean       lb_idPais;
				boolean       lb_idDepartamento;
				boolean       lb_idMunicipio;
				boolean       lb_idOficinaOrigen;

				lb_idTipoOficina       = aoo_oficinaOrigen.isTipoOficinaValid();
				lb_idPais              = aoo_oficinaOrigen.isIdPaisValid();
				lb_idDepartamento      = aoo_oficinaOrigen.isIdDepartamentoValid();
				lb_idMunicipio         = aoo_oficinaOrigen.isIdMunicipioValid();
				lb_idOficinaOrigen     = aoo_oficinaOrigen.isIdOficinaOrigenValid();

				lsb_query     = new StringBuilder();
				li_cont       = 1;

				lsb_query.append(cs_FIND_ALL_CRITERIOS);

				if(lb_idTipoOficina)
					lsb_query.append(" AND ID_TIPO_OFICINA = ? ");

				if(lb_idPais)
					lsb_query.append(" AND ID_PAIS = ? ");

				if(lb_idDepartamento)
					lsb_query.append(" AND ID_DEPARTAMENTO = ? ");

				if(lb_idMunicipio)
					lsb_query.append(" AND ID_MUNICIPIO = ? ");

				if(lb_idOficinaOrigen)
					lsb_query.append(" AND ID_OFICINA_ORIGEN = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				if(lb_idTipoOficina)
					lps_ps.setString(li_cont++, aoo_oficinaOrigen.getIdTipoOficina());

				if(lb_idPais)
					lps_ps.setString(li_cont++, aoo_oficinaOrigen.getIdPais());

				if(lb_idDepartamento)
					lps_ps.setString(li_cont++, aoo_oficinaOrigen.getIdDepartamento());

				if(lb_idMunicipio)
					lps_ps.setString(li_cont++, aoo_oficinaOrigen.getIdMunicipio());

				if(lb_idOficinaOrigen)
					lps_ps.setString(li_cont++, aoo_oficinaOrigen.getIdOficinaOrigen());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcoo_object.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllCriterios", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcoo_object.isEmpty())
			lcoo_object = null;

		return lcoo_object;
	}

	/**
	 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<OficinaOrigen> findAllOO()
	    throws B2BException
	{
		Collection<OficinaOrigen> ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ls_object     = new ArrayList<OficinaOrigen>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllOO", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Método para encontrar en la base de datos todos los registros que coincidan con un id pais, unid municipio y una ofician origen
	 * específicos.
	 *
	 * @param at_param objeto del cual se extraen los datos para la consulta
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<OficinaOrigen> findByFilters(OficinaOrigen at_param)
	    throws B2BException
	{
		Collection<OficinaOrigen> ls_object;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		ls_object     = new ArrayList<OficinaOrigen>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			int li_column;

			li_column     = 1;
			lps_ps        = getConnection().prepareStatement(cs_FIND_FILTERS);

			lps_ps.setString(li_column++, at_param.getIdTipoOficina());
			lps_ps.setString(li_column++, at_param.getIdPais());
			lps_ps.setString(li_column++, at_param.getIdDepartamento());
			lps_ps.setString(li_column++, at_param.getIdMunicipio());
			lps_ps.setString(li_column++, at_param.getIdTipoOficina());
			lps_ps.setString(li_column++, at_param.getIdPais());
			lps_ps.setString(li_column++, at_param.getIdDepartamento());
			lps_ps.setString(li_column++, at_param.getIdMunicipio());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByFilters", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(ls_object.isEmpty())
			ls_object = null;

		return ls_object;
	}

	/**
	 * Metodo para buscar de la base de datos todos los registros que coinciadan con un id oficina origen específico.
	 *
	 * @param aoo_param Objeto contenedor del id oficina origen a utilizar como filtro en la consulta
	 * @return Oficina origen resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public OficinaOrigen findById(OficinaOrigen aoo_param)
	    throws B2BException
	{
		return (aoo_param != null) ? findById(aoo_param.getIdOficinaOrigen(), aoo_param.getVersion()) : null;
	}

	/**
	 * Metodo para buscar de la base de datos todos los registros que coinciadan con un id oficina origen específico.
	 *
	 * @param as_idOficinaOrigen Objeto contenedor del id oficina origen a utilizar como filtro en la consulta
	 * @param abd_version correspondiente al valor del tipo de objeto BigDecimal
	 * @return Oficina origen resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public OficinaOrigen findById(String as_idOficinaOrigen, BigDecimal abd_version)
	    throws B2BException
	{
		OficinaOrigen loo_object;

		loo_object = null;

		if(StringUtils.isValidString(as_idOficinaOrigen) && (abd_version != null))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idOficinaOrigen);
				lps_ps.setBigDecimal(li_contador++, abd_version);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					loo_object = getObjetFromResultSet(lrs_rs);
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

		return loo_object;
	}

	/** {@inheritdoc} */
	public OficinaOrigen findByIdCirculo(String as_idCirculo)
	    throws B2BException
	{
		OficinaOrigen loo_object;

		loo_object = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_CIRCULO);

				lps_ps.setString(li_contador++, as_idCirculo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					loo_object = getObjetFromResultSet(lrs_rs);
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

		return loo_object;
	}

	/**
	 * Find by id oficina tipo nombre parcial.
	 *
	 * @param loo_oficinaOrigen de loo oficina origen
	 * @return el valor de oficina origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<OficinaOrigen> findByIdOficinaTipoNombreParcial(OficinaOrigen loo_oficinaOrigen)
	    throws B2BException
	{
		Collection<OficinaOrigen> lcoo_object;

		lcoo_object = new ArrayList<OficinaOrigen>();

		if(loo_oficinaOrigen != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_contador;

				lsb_sb          = new StringBuilder(cs_FIND_BY_TIPO_OFICINA);
				li_contador     = 1;

				{
					String ls_idOficinaOrigen;
					String ls_nombre;

					ls_idOficinaOrigen     = loo_oficinaOrigen.getIdOficinaOrigen();
					ls_nombre              = loo_oficinaOrigen.getNombre();

					if(StringUtils.isValidString(ls_idOficinaOrigen))
						lsb_sb.append(" AND ID_OFICINA_ORIGEN = ?");

					if(StringUtils.isValidString(ls_nombre))
						lsb_sb.append(" AND NOMBRE LIKE ?");

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					lps_ps.setString(li_contador++, loo_oficinaOrigen.getIdTipoOficina());

					if(StringUtils.isValidString(ls_idOficinaOrigen))
						lps_ps.setString(li_contador++, loo_oficinaOrigen.getIdOficinaOrigen());

					if(StringUtils.isValidString(ls_nombre))
						lps_ps.setString(li_contador++, ls_nombre);

					lrs_rs = lps_ps.executeQuery();

					if(lrs_rs.next())
						lcoo_object.add(getObjetFromResultSet(lrs_rs));
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdOficinaTipoNombreParcial", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcoo_object.isEmpty())
			lcoo_object = null;

		return lcoo_object;
	}

	/**
	 * Find by id only.
	 *
	 * @param as_idOficinaOrigen correspondiente al valor de as id oficina origen
	 * @return el valor de oficina origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficinaOrigen findByIdOnly(String as_idOficinaOrigen)
	    throws B2BException
	{
		OficinaOrigen loo_object;

		loo_object = null;

		if(StringUtils.isValidString(as_idOficinaOrigen))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_ONLY);

				lps_ps.setString(li_contador++, as_idOficinaOrigen);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					loo_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdOnly", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return loo_object;
	}

	/**
	 * Find by id with max version.
	 *
	 * @param as_idOficinaOrigen de as id oficina origen
	 * @return el valor de oficina origen
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficinaOrigen findByIdWithMaxVersion(String as_idOficinaOrigen)
	    throws B2BException
	{
		return StringUtils.isValidString(as_idOficinaOrigen)
		? findById(as_idOficinaOrigen, findMaxVersion(as_idOficinaOrigen)) : null;
	}

/**
 * Metodo para buscar de la base de datos todos los registros que coinciadan con un id Solicitud específico.
 *
 * @param as_idSolicitud de as id solicitud
 * @return Oficina origen resultante de la consulta
 * @throws B2BException Señala que se ha producido una excepción
 */
	public OficinaOrigen findBySolicitud(String as_idSolicitud)
	    throws B2BException
	{
		OficinaOrigen loo_object;

		loo_object = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_SOLICITUD);

				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					loo_object = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return loo_object;
	}

	/**
	 * Metodo encargado de consultar la versión máxima de un id oficina origen.
	 *
	 * @param as_idOficinaOrigen Objeto contenedor del id oficina origen a utilizar como filtro en la consulta
	 * @return versión máxima de un id oficina origen
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public BigDecimal findMaxVersion(String as_idOficinaOrigen)
	    throws B2BException
	{
		BigDecimal lbd_version;

		lbd_version = null;

		if(StringUtils.isValidString(as_idOficinaOrigen))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_VERSION);

				lps_ps.setString(li_contador++, as_idOficinaOrigen);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lbd_version = lrs_rs.getBigDecimal(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lbd_version;
	}

	/**
	 * Metodo para insertar o actualizar registros en la tabla SDB_PGN_OFICINA_ORIGEN.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto OficinaOrigen
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insertOrUpdate(OficinaOrigen at_param, boolean ab_query)
	    throws B2BException
	{
		if(at_param != null)
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

				lps_ps = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE_OFICINA_ORIGEN);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;
						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							at_param.setIdOficinaOrigen(lo_o.toString());
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}

					lps_ps.setString(li_column++, at_param.getIdOficinaOrigen());
				}

				lps_ps.setString(li_column++, at_param.getIdTipoOficina());
				lps_ps.setString(li_column++, at_param.getIdPais());
				lps_ps.setString(li_column++, at_param.getIdDepartamento());
				lps_ps.setString(li_column++, at_param.getIdMunicipio());
				lps_ps.setString(li_column++, at_param.getIdVereda());
				lps_ps.setString(li_column++, at_param.getNombre());
				lps_ps.setString(li_column++, at_param.getNumero());
				lps_ps.setString(li_column++, at_param.getNombreTitular());
				lps_ps.setString(li_column++, at_param.getDireccion());
				lps_ps.setString(li_column++, at_param.getTelefono());
				lps_ps.setString(li_column++, at_param.getNotificarCorrespondencia());
				lps_ps.setString(li_column++, at_param.getCorreoElectronico());
				lps_ps.setString(li_column++, at_param.getFax());
				lps_ps.setString(li_column++, at_param.getIdUnico());
				lps_ps.setString(li_column++, at_param.getActivo());

				if(ab_query)
				{
					lps_ps.setBigDecimal(li_column++, at_param.getVersion());
					lps_ps.setString(li_column++, at_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, at_param.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, at_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, at_param.getIpModificacion());
					lps_ps.setString(li_column++, at_param.getIdOficinaOrigen());
					lps_ps.setBigDecimal(li_column++, at_param.getVersion());
				}

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "insertOrUpdate", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_secuencia);
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de oficina origen.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de OficinaOrigen
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see OficinaOrigen
	 */
	private OficinaOrigen getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		OficinaOrigen ls_tipoActo;

		ls_tipoActo = new OficinaOrigen();

		ls_tipoActo.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
		ls_tipoActo.setIdTipoOficina(ars_rs.getString("ID_TIPO_OFICINA"));
		ls_tipoActo.setIdPais(ars_rs.getString("ID_PAIS"));
		ls_tipoActo.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		ls_tipoActo.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		ls_tipoActo.setIdVereda(ars_rs.getString("ID_VEREDA"));
		ls_tipoActo.setNombre(ars_rs.getString("NOMBRE"));
		ls_tipoActo.setNumero(ars_rs.getString("NUMERO"));
		ls_tipoActo.setNombreTitular(ars_rs.getString("NOMBRE_TITULAR"));
		ls_tipoActo.setDireccion(ars_rs.getString("DIRECCION"));
		ls_tipoActo.setTelefono(ars_rs.getString("TELEFONO"));
		ls_tipoActo.setCorreoElectronico(ars_rs.getString("CORREO_ELECTRONICO"));
		ls_tipoActo.setFax(ars_rs.getString("FAX"));
		ls_tipoActo.setIdUnico(ars_rs.getString("ID_UNICO"));
		ls_tipoActo.setVersion(getBigDecimal(ars_rs, "VERSION"));
		ls_tipoActo.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		ls_tipoActo.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		ls_tipoActo.setNotificarCorrespondencia(ars_rs.getString("NOTIFICAR_CORRESPONDENCIA"));
		ls_tipoActo.setIpCreacion(ars_rs.getString("IP_CREACION"));
		ls_tipoActo.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		ls_tipoActo.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		ls_tipoActo.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		ls_tipoActo.setActivo(ars_rs.getString("ACTIVO"));

		return ls_tipoActo;
	}
}
