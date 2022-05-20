package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.pgn.CamposConsulta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase de manejo de datos para la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA.
 *
 * @author Julian Vaca
 */
public class CampoCriterioBusquedaDAO extends AuditoriaDao
{
	/** Constante cs_CAMPOS_POR_CRITERIO. */
	private static final String cs_CAMPOS_POR_CRITERIO = " SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA WHERE ID_TIPO_CRITERIO_BUSQUEDA = ? ORDER BY ORDEN_PANTALLA ASC";

	/** Constante cs_CAMPOS_POR_TIPO_CAMPO. */
	private static final String cs_CAMPOS_POR_TIPO_CAMPO = " SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_CAMPOS_POR_TIPO_CAMPO_CRITERIO_SERVICIO. */
	private static final String cs_CAMPOS_POR_TIPO_CAMPO_CRITERIO_SERVICIO = " SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE ID_TIPO_CRITERIO_BUSQUEDA = ? AND NOMBRE_CRITERIO_SERVICIO = ?";

	/** Constante cs_TAMANIO_TABLA_POR_CRITERIO_BUSQUEDA. */
	private static final String cs_TAMANIO_TABLA_POR_CRITERIO_BUSQUEDA = "SELECT COUNT(*) AS TAMANIO FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA WHERE ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_ETIQUETA_CAMPO_BY_TIPO_CRITERIO. */
	private static final String cs_ETIQUETA_CAMPO_BY_TIPO_CRITERIO = "SELECT ETIQUETA_CAMPO FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE ID_TIPO_CRITERIO_BUSQUEDA = ? ORDER BY ORDEN_PANTALLA ASC";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "ORDER BY ID_TIPO_CRITERIO_BUSQUEDA, ID_CAMPO_CRITERIO_BUSQUEDA";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE ID_TIPO_CRITERIO_BUSQUEDA = ? AND ID_CAMPO_CRITERIO_BUSQUEDA=?";

	/** Constante cs_FIND_ALL_BY_TIPO_CRITERIO_BUSQUEDA. */
	private static final String cs_FIND_ALL_BY_TIPO_CRITERIO_BUSQUEDA = "SELECT * FROM SDB_PGN_CAMPO_CRITERIO_BUSQUEDA "
		+ "WHERE ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_CAMPO_CRITERIO_BUSQUEDA(ID_CAMPO_CRITERIO_BUSQUEDA, ID_TIPO_CRITERIO_BUSQUEDA, ETIQUETA_CAMPO, NOMBRE_CAMPO, TIPO_DATO, LONGITUD, NOMBRE_TABLA, OBLIGATORIO, ACTIVO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, METODO_CONSULTA, TIPO_CAMPO, PARAMETRICA, LLAVE_PK, DESCRIPTIVO,NOMBRE_CRITERIO_SERVICIO)"
		+ "VALUES(?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_CAMPO_CRITERIO_BUSQUEDA SET  ETIQUETA_CAMPO=?, NOMBRE_CAMPO=?, TIPO_DATO=?, LONGITUD=?, ACTIVO=?, NOMBRE_TABLA=?, OBLIGATORIO=?, ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, METODO_CONSULTA=?, TIPO_CAMPO=?, PARAMETRICA=?, LLAVE_PK=?, DESCRIPTIVO=?, NOMBRE_CRITERIO_SERVICIO=? WHERE ID_CAMPO_CRITERIO_BUSQUEDA=? AND ID_TIPO_CRITERIO_BUSQUEDA=?";

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_DEPARTAMENTO
	 * que coincida con un id_pais y un id_departamento especifico.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @return el valor de etiqueta campo by id tipo criterio busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> getEtiquetaCampoByIdTipoCriterioBusqueda(String as_param)
	    throws B2BException
	{
		Collection<String> lcs_cs;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		lcs_cs     = new ArrayList<String>();
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_ETIQUETA_CAMPO_BY_TIPO_CRITERIO);

			lps_ps.setString(li_contador++, as_param);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcs_cs.add(lrs_rs.getString("ETIQUETA_CAMPO"));
		}
		catch(SQLException lse_e)
		{
			logError(this, "getEtiquetaCampoByIdTipoCriterioBusqueda", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcs_cs.isEmpty())
			lcs_cs = null;

		return lcs_cs;
	}

	/**
	 * Metodo para encontrar todos los registros de la tabla SDB_PGN_DEPARTAMENTO
	 * que coincida con un id_pais y un id_departamento especifico.
	 *
	 * @param acc_cc correspondiente al valor del tipo de objeto CamposConsulta
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<CamposConsulta> buscarCamposPorCriterio(CamposConsulta acc_cc)
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_ccc;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lccc_ccc     = new ArrayList<CamposConsulta>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_CAMPOS_POR_CRITERIO);

			lps_ps.setString(li_contador++, acc_cc.getIdTipoCriterioBusqueda());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccc_ccc.add(getCamposConsulta(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarCamposPorCriterio", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lccc_ccc.isEmpty())
			lccc_ccc = null;

		return lccc_ccc;
	}

	/**
	 * Metodo encargado de buscar en la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA por tipo y campo.
	 *
	 * @param acc_cc Argumento de tipo CamposConsulta que contiene los criterios de búsqueda.
	 * @return Objeto de tipo CamposConsulta que contiene los resultados de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CamposConsulta buscarCamposPorTipoCampo(CamposConsulta acc_cc)
	    throws B2BException
	{
		CamposConsulta    lcc_camposConsulta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcc_camposConsulta     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			if(acc_cc != null)
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_CAMPOS_POR_TIPO_CAMPO);

				lps_ps.setString(li_contador++, acc_cc.getIdTipoCriterioBusqueda());
				lps_ps.setString(li_contador++, acc_cc.getIdCampoCriterioBusqueda());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_camposConsulta = getCamposConsulta(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarCamposPorTipoCampo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcc_camposConsulta;
	}

	/**
	 * Buscar campos por tipo campo criterio servicio.
	 *
	 * @param as_tipoCriterio de as tipo criterio
	 * @param as_campoCriterioServicio de as campo criterio servicio
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta buscarCamposPorTipoCampoCriterioServicio(
	    String as_tipoCriterio, String as_campoCriterioServicio
	)
	    throws B2BException
	{
		CamposConsulta    lcc_camposConsulta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcc_camposConsulta     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			if(StringUtils.isValidString(as_tipoCriterio) && StringUtils.isValidString(as_campoCriterioServicio))
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_CAMPOS_POR_TIPO_CAMPO_CRITERIO_SERVICIO);

				lps_ps.setString(li_contador++, as_tipoCriterio);
				lps_ps.setString(li_contador++, as_campoCriterioServicio);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_camposConsulta = getCamposConsulta(lrs_rs);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarCamposPorTipoCampo", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcc_camposConsulta;
	}

	/**
	 * Metodo encargado de buscar en la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA por idTipoCriterioBusqueda.
	 *
	 * @param acc_cc Argumento de tipo CamposConsulta que contiene los criterios de búsqueda.
	 * @return Objeto de tipo CamposConsulta que contiene los resultados de la búsqueda.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public CamposConsulta buscarTamanoDeTablaPorIdTipoCriterioBusqueda(CamposConsulta acc_cc)
	    throws B2BException
	{
		CamposConsulta    lcc_camposConsulta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcc_camposConsulta     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			if(acc_cc != null)
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_TAMANIO_TABLA_POR_CRITERIO_BUSQUEDA);

				lps_ps.setString(li_contador++, acc_cc.getIdTipoCriterioBusqueda());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_camposConsulta = getCamposConsulta(lrs_rs, true);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "buscarTamanoDeTablaPorIdTipoCriterioBusqueda", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcc_camposConsulta;
	}

	/**
	 * Método para consultar todos los registros de la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA.
	 *
	 * @return Collection<CamposConsulta> Colección de CamposConsulta que contiene los criterios de búsqueda
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAll()
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_datos;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lccc_datos     = new ArrayList<CamposConsulta>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lccc_datos.add(getCamposConsulta(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lccc_datos))
			lccc_datos = null;

		return lccc_datos;
	}

	/**
	 * Método de consulta para encontrar todos los camposs criterio busqueda por medio del tipo criterio de búsqueda.
	 *
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<CamposConsulta> findAllByTipoCriterioBusqueda(String as_tipoCriterioBusqueda)
	    throws B2BException
	{
		Collection<CamposConsulta> lccc_camposConsulta;
		PreparedStatement          lps_ps;
		ResultSet                  lrs_rs;

		lccc_camposConsulta     = new ArrayList<CamposConsulta>();
		lps_ps                  = null;
		lrs_rs                  = null;

		try
		{
			if(StringUtils.isValidString(as_tipoCriterioBusqueda))
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_TIPO_CRITERIO_BUSQUEDA);

				lps_ps.setString(1, as_tipoCriterioBusqueda);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lccc_camposConsulta.add(getCamposConsulta(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByTipoCriterioBusqueda", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lccc_camposConsulta;
	}

	/**
	 * Método para buscar los campos de la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA basados en su llave primaria y foránea.
	 *
	 * @param as_tipoCriterioBusqueda de as tipo criterio busqueda
	 * @param as_campoCriterioBusqueda de as campo criterio busqueda
	 * @return el valor de campos consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public CamposConsulta findById(String as_tipoCriterioBusqueda, String as_campoCriterioBusqueda)
	    throws B2BException
	{
		CamposConsulta    lcc_camposConsulta;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lcc_camposConsulta     = null;
		lps_ps                 = null;
		lrs_rs                 = null;

		try
		{
			if(
			    StringUtils.isValidString(as_tipoCriterioBusqueda)
				    && StringUtils.isValidString(as_campoCriterioBusqueda)
			)
			{
				int li_contador;

				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_tipoCriterioBusqueda);
				lps_ps.setString(li_contador++, as_campoCriterioBusqueda);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcc_camposConsulta = getCamposConsulta(lrs_rs);
			}
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

		return lcc_camposConsulta;
	}

	/**
	 * Metodo para insertar un registro en la base de datos de la tabla SDB_PGN_CAMPO_CRITERIO_BUSQUEDA.
	 *
	 * @param acc_param de acc param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(CamposConsulta acc_param)
	    throws B2BException
	{
		if(acc_param != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setString(li_column++, acc_param.getIdCampoCriterioBusqueda());
				lps_ps.setString(li_column++, acc_param.getIdTipoCriterioBusqueda());
				lps_ps.setString(li_column++, acc_param.getEtiquetaCampo());
				lps_ps.setString(li_column++, acc_param.getNombreCampo());
				lps_ps.setString(li_column++, acc_param.getTipoDato());
				setLong(lps_ps, acc_param.getLongitud(), li_column++);
				lps_ps.setString(li_column++, acc_param.getNombreTabla());
				lps_ps.setString(li_column++, acc_param.getObligatorio());
				lps_ps.setString(li_column++, acc_param.getActivo());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, acc_param.getIpCreacion());
				lps_ps.setString(li_column++, acc_param.getMetodoConsulta());
				lps_ps.setString(li_column++, acc_param.getTipoCampo());
				lps_ps.setString(li_column++, acc_param.getParametrica());
				lps_ps.setString(li_column++, acc_param.getLlavePk());
				lps_ps.setString(li_column++, acc_param.getDescriptivo());
				lps_ps.setString(li_column++, acc_param.getNombreCriterioServicio());

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
	 * Metodo para actualizar un registro en la base de datos de la tabla SDB_PGN_CANAL_ORIGEN_SERVICIO.
	 *
	 * @param acc_param de acc param
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(CamposConsulta acc_param)
	    throws B2BException
	{
		if(acc_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, acc_param.getEtiquetaCampo());
				lps_ps.setString(li_column++, acc_param.getNombreCampo());
				lps_ps.setString(li_column++, acc_param.getTipoDato());
				setLong(lps_ps, acc_param.getLongitud(), li_column++);
				lps_ps.setString(li_column++, acc_param.getActivo());
				lps_ps.setString(li_column++, acc_param.getNombreTabla());
				lps_ps.setString(li_column++, acc_param.getObligatorio());
				lps_ps.setString(li_column++, acc_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, acc_param.getIpModificacion());
				lps_ps.setString(li_column++, acc_param.getMetodoConsulta());
				lps_ps.setString(li_column++, acc_param.getTipoCampo());
				lps_ps.setString(li_column++, acc_param.getParametrica());
				lps_ps.setString(li_column++, acc_param.getLlavePk());
				lps_ps.setString(li_column++, acc_param.getDescriptivo());
				lps_ps.setString(li_column++, acc_param.getNombreCriterioServicio());
				lps_ps.setString(li_column++, acc_param.getIdCampoCriterioBusqueda());
				lps_ps.setString(li_column++, acc_param.getIdTipoCriterioBusqueda());

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
	 *   Metodo sobrecargado para obtener el tamaño de la tabla.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @return el valor de campos consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CamposConsulta getCamposConsulta(ResultSet ars_rs)
	    throws SQLException
	{
		return getCamposConsulta(ars_rs, false);
	}

	/**
	 *   Metodo para obtener objeto de campos consulta.
	 *
	 * @param ars_rs objeto que representa el resultado de una sentencia sql en la base de datos
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de campos consulta
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private CamposConsulta getCamposConsulta(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		CamposConsulta lc_c;
		lc_c = new CamposConsulta();

		if(!ab_b)
		{
			lc_c.setIdCampoCriterioBusqueda(ars_rs.getString("ID_CAMPO_CRITERIO_BUSQUEDA"));
			lc_c.setIdTipoCriterioBusqueda(ars_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
			lc_c.setEtiquetaCampo(ars_rs.getString("ETIQUETA_CAMPO"));
			lc_c.setNombreCampo(ars_rs.getString("NOMBRE_CAMPO"));
			lc_c.setTipoDato(ars_rs.getString("TIPO_DATO"));
			lc_c.setTipoCampo(ars_rs.getString("TIPO_CAMPO"));
			lc_c.setLongitud(getLong(ars_rs, "LONGITUD"));
			lc_c.setNombreTabla(ars_rs.getString("NOMBRE_TABLA"));
			lc_c.setObligatorio(ars_rs.getString("OBLIGATORIO"));
			lc_c.setEstado(ars_rs.getString("ACTIVO"));
			lc_c.setMetodoConsulta(ars_rs.getString("METODO_CONSULTA"));
			lc_c.setParametrica(ars_rs.getString("PARAMETRICA"));
			lc_c.setLlavePk(ars_rs.getString("LLAVE_PK"));
			lc_c.setDescriptivo(ars_rs.getString("DESCRIPTIVO"));
			lc_c.setTipoCampo(ars_rs.getString("TIPO_CAMPO"));
			lc_c.setNombreCriterioServicio(ars_rs.getString("NOMBRE_CRITERIO_SERVICIO"));
			fillAuditoria(ars_rs, lc_c);
		}
		else
			lc_c.setTamanoDeTabla(ars_rs.getInt("TAMANIO"));

		return lc_c;
	}
}
