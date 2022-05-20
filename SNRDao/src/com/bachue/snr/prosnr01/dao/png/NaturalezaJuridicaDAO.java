package com.bachue.snr.prosnr01.dao.png;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.png.NaturalezaJuridica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas relacionadas con la tabla SDB_PNG_NATURALEZA_JURIDICA de la bae de datos
 *
 * @author ssanchez
 */
public class NaturalezaJuridicaDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA "
		+ "WHERE ID_NATURALEZA_JURIDICA = ? AND VERSION = ?";

	/** Constante cs_FIND_NATURALEZA_JURIDICA_CANCELACION. */
	private static final String cs_FIND_NATURALEZA_JURIDICA_CANCELACION = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA WHERE ID_GRUPO_NAT_JURIDICA = '0700' AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_NATURALEZA_BY_GRUPO_ACTO. */
	private static final String cs_FIND_NATURALEZA_BY_GRUPO_ACTO = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA WHERE ID_GRUPO_NAT_JURIDICA = ? AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_ALL_VERSION. */
	private static final String cs_FIND_ALL_VERSION = "SELECT T2.* FROM ("
		+ "SELECT ID_NATURALEZA_JURIDICA, MAX(VERSION) VERSION from SDB_PNG_NATURALEZA_JURIDICA "
		+ "GROUP by ID_NATURALEZA_JURIDICA) T1 " + "INNER JOIN SDB_PNG_NATURALEZA_JURIDICA "
		+ "T2 ON (T2.ID_NATURALEZA_JURIDICA=T1.ID_NATURALEZA_JURIDICA AND T2.VERSION=T1.VERSION) ";

	/** Constante cs_FIND_ALL_VERSION_BY_ID. */
	private static final String cs_FIND_ALL_VERSION_BY_ID = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA "
		+ "WHERE ID_NATURALEZA_JURIDICA = ? ";

	/** Constante cs_FIND_ALL_BY_MAX_VERSION. */
	private static final String cs_FIND_ALL_BY_MAX_VERSION = "SELECT  * FROM SDB_PNG_NATURALEZA_JURIDICA  SPN WHERE  VERSION = ( SELECT  MAX(VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA  SPN1 "
		+ " WHERE SPN1.ID_NATURALEZA_JURIDICA = SPN.ID_NATURALEZA_JURIDICA )  ORDER BY SPN.NOMBRE ASC";

	/** Constante cs_FIND_ALL_BY_MAX_VERSION_ID. */
	private static final String cs_FIND_ALL_BY_MAX_VERSION_ID = "SELECT  * FROM SDB_PNG_NATURALEZA_JURIDICA  SPN WHERE  VERSION = ( SELECT  MAX(VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA  SPN1 "
		+ " WHERE SPN1.ID_NATURALEZA_JURIDICA = SPN.ID_NATURALEZA_JURIDICA )  ORDER BY SPN.ID_NATURALEZA_JURIDICA ASC";

	/** Constante cs_FIND_BY_ID_MAX_VERSION. */
	private static final String cs_FIND_BY_ID_MAX_VERSION = "SELECT  * FROM SDB_PNG_NATURALEZA_JURIDICA  SPN WHERE  VERSION = ( SELECT  MAX(VERSION) FROM SDB_PNG_NATURALEZA_JURIDICA  SPN1 "
		+ "WHERE SPN1.ID_NATURALEZA_JURIDICA = SPN.ID_NATURALEZA_JURIDICA ) AND ID_NATURALEZA_JURIDICA = ?";

	/** Constante cs_FIND_ALL */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PNG_NATURALEZA_JURIDICA(ID_DOMINIO_NAT_JUR, ID_GRUPO_NAT_JURIDICA, ID_NATURALEZA_JURIDICA, VERSION, NOMBRE, HABILITADA_CALIFICACION, ALERTA_TITULAR, ID_TIPO_RRR, ID_USUARIO_CREACION, FECHA_CREACION, BUSQUEDA_TITULAR, HEREDAR_ANOTACION, CONSTRUIR_COMPLEMENTACION, REQUIERE_CIERRE_FOLIO, SUMATORIA_AREA, SUMATORIA_COEFICIENTE, IP_CREACION, VALIDAR_AREA, ACTUALIZAR_LINDERO, PROPIETARIO, ROL, HABILITA_SECUENCIA, ACTIVO, ID_DOMINIO_RRR) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PNG_NATURALEZA_JURIDICA SET ID_DOMINIO_NAT_JUR=?, ID_GRUPO_NAT_JURIDICA=?, NOMBRE=?, HABILITADA_CALIFICACION=?, ALERTA_TITULAR=?, ID_TIPO_RRR=?, BUSQUEDA_TITULAR=?, HEREDAR_ANOTACION=?, CONSTRUIR_COMPLEMENTACION=?, REQUIERE_CIERRE_FOLIO=?, SUMATORIA_AREA=?, SUMATORIA_COEFICIENTE=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, VALIDAR_AREA=?, ACTUALIZAR_LINDERO=?, PROPIETARIO=?, ROL=?, HABILITA_SECUENCIA=?, ACTIVO=?, ID_DOMINIO_RRR=? WHERE ID_NATURALEZA_JURIDICA=? AND VERSION=?";

	/** Constante cs_FIND_ALL_BY_IDSOLICITUD. */
	private static final String cs_FIND_ALL_BY_IDSOLICITUD = "SELECT * FROM SDB_PNG_NATURALEZA_JURIDICA NJ " + 
			"INNER JOIN  SDB_ACC_ACTO AA  ON NJ.ID_NATURALEZA_JURIDICA = AA.ID_TIPO_ACTO " + 
			"WHERE AA.ID_SOLICITUD = ?";
	
	/**
	 * Instancia un nuevo objeto naturaleza juridica DAO.
	 */
	public NaturalezaJuridicaDAO()
	{
	}

	/**
	 * Consulta en la base de datos una naturaleza juridica, filtrando por un id de
	 * naturaleza juridica, grupo naturaleza juridica, dominio naturaleza juridica, id tipo rrr definidos con anterioridad
	 * @param as_param objeto contenedor del id subproceso que se va a aplicar
	 * en el filtro de la consulta
	 * @return Naturaleza Juridica resultante de la ejecución de la consulta
	 * @throws B2BException
	 */
	public NaturalezaJuridica findByIdGeneral(NaturalezaJuridica as_param)
	    throws B2BException
	{
		NaturalezaJuridica ls_object;
		ls_object = null;

		if(as_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param.getIdNaturalezaJuridica());
				lps_ps.setLong(2, as_param.getVersion());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Consulta en base de datos todos los registros que se encuentren en la tabla SDB_PNG_NATURALEZA_JURIDICA
	 * @return Colección de Entidades Externas resultante de la consulta
	 * @throws B2BException
	 */
	public Collection<NaturalezaJuridica> findAll()
	    throws B2BException
	{
		Collection<NaturalezaJuridica> lcnj_cnj;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcnj_cnj     = new ArrayList<NaturalezaJuridica>();
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
				lcnj_cnj.add(getObjetFromResultSet(lrs_rs));
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

		if(lcnj_cnj.isEmpty())
			lcnj_cnj = null;

		return lcnj_cnj;
	}

	/**
	 * Retorna una Collection con todas las NaturalezaJuridica de acuerto a la maxima version
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para obtener por la maxima version, falso para obter por la
	 * maxima version del ID
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public Collection<NaturalezaJuridica> findAllMaxVersion(boolean ab_b)
	    throws B2BException
	{
		Collection<NaturalezaJuridica> lcnj_nj;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcnj_nj     = new ArrayList<NaturalezaJuridica>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			if(ab_b)
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_MAX_VERSION);
			else
				lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_MAX_VERSION_ID);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcnj_nj.add(getNaturalezaJuridica(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllMaxVersion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcnj_nj.isEmpty())
			lcnj_nj = null;

		return lcnj_nj;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection con todos los registros de tipo NaturalezaJuridica
	 *
	 * @param ab_b correspondiente al valor del tipo de objeto boolean, verdadero para ordernar por el nombre ascendente,
	 * falso para ordenar por el ID de la naturaleza juridica.
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public Collection<NaturalezaJuridica> findAllVersion(boolean ab_b)
	    throws B2BException
	{
		Collection<NaturalezaJuridica> lcnj_nj;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;
		StringBuilder                  lsb_sb;

		lcnj_nj     = new ArrayList<NaturalezaJuridica>();
		lps_ps      = null;
		lrs_rs      = null;
		lsb_sb      = new StringBuilder(cs_FIND_ALL_VERSION);

		try
		{
			if(ab_b)
				lsb_sb = lsb_sb.append("ORDER BY T2.NOMBRE ASC");
			else
				lsb_sb = lsb_sb.append("ORDER BY T2.ID_NATURALEZA_JURIDICA ASC");

			lps_ps     = getConnection().prepareStatement(lsb_sb.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcnj_nj.add(getNaturalezaJuridica(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllVersion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcnj_nj.isEmpty())
			lcnj_nj = null;

		return lcnj_nj;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection con los objetos de NaturalezaJuridica consultado por el ID de la
	 * version
	 *
	 * @param as_idAlertaN correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<NaturalezaJuridica> findAllVersionById(String as_idAlertaN)
	    throws B2BException
	{
		Collection<NaturalezaJuridica> lcnj_nj;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcnj_nj     = new ArrayList<NaturalezaJuridica>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_VERSION_BY_ID);

			lps_ps.setString(1, as_idAlertaN);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcnj_nj.add(getNaturalezaJuridica(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllVersionById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcnj_nj.isEmpty())
			lcnj_nj = null;

		return lcnj_nj;
	}
	
	/**
	 * Retorna el valor del objeto de tipo Collection con los objetos de NaturalezaJuridica consultado por el ID de la
	 * version
	 *
	 * @param as_idSolicitud correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<NaturalezaJuridica> findAllBySolicitud(String as_idSolicitud)
	    throws B2BException
	{
		Collection<NaturalezaJuridica> lcnj_nj;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lcnj_nj     = new ArrayList<NaturalezaJuridica>();
		lps_ps      = null;
		lrs_rs      = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_ALL_BY_IDSOLICITUD);

			lps_ps.setString(1, as_idSolicitud);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcnj_nj.add(getNaturalezaJuridica(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAllBySolicitud", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcnj_nj.isEmpty())
			lcnj_nj = null;

		return lcnj_nj;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param anj_parametros Objeto contenedor de los datos a utilizar como filtro en la busqueda
	 * @return Naturaleza jurídica resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public NaturalezaJuridica findById(NaturalezaJuridica anj_parametros)
	    throws B2BException
	{
		return (anj_parametros != null)
		? findById(anj_parametros.getIdNaturalezaJuridica(), anj_parametros.getVersion()) : null;
	}

	/**
	 * Busca un registro asociado a un id específico.
	 *
	 * @param as_idNaturalezaJuridica Objeto contenedor de los datos a utilizar como filtro en la busqueda
	 * @param al_version Objeto contenedor de los datos a utilizar como filtro en la busqueda
	 * @return Naturaleza jurídica resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public NaturalezaJuridica findById(String as_idNaturalezaJuridica, long al_version)
	    throws B2BException
	{
		NaturalezaJuridica lnj_return;

		lnj_return = null;

		if(StringUtils.isValidString(as_idNaturalezaJuridica) && (al_version > 0L))
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

				lps_ps.setString(li_contador++, as_idNaturalezaJuridica);
				lps_ps.setLong(li_contador++, al_version);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lnj_return = getNaturalezaJuridica(lrs_rs);
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

		return lnj_return;
	}

	/**
	 * Find by id max version.
	 *
	 * @param anj_parametros de anj parametros
	 * @return el valor de naturaleza juridica
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public NaturalezaJuridica findByIdMaxVersion(NaturalezaJuridica anj_parametros)
	    throws B2BException
	{
		if(anj_parametros != null)
			return findByIdMaxVersion(anj_parametros.getIdNaturalezaJuridica());
		else

			return null;
	}

	/**
	 * Retorna el valor del objeto de tipo Find by id max version.
	 *
	 * @param as_idNaturalezaJuridica de as id naturaleza juridica
	 * @return devuelve el valor del objeto naturaleza juridica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public NaturalezaJuridica findByIdMaxVersion(String as_idNaturalezaJuridica)
	    throws B2BException
	{
		NaturalezaJuridica lnj_naturalezaJuridica;

		lnj_naturalezaJuridica = null;

		if(StringUtils.isValidString(as_idNaturalezaJuridica))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MAX_VERSION);

				lps_ps.setString(li_contador++, as_idNaturalezaJuridica);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lnj_naturalezaJuridica = getNaturalezaJuridica(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdMaxVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lnj_naturalezaJuridica;
	}

	/**
	 * Retorna el valor del objeto de tipo Find nat cancelacion.
	 *
	 * @param as_idActo correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto naturaleza juridica
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public NaturalezaJuridica findNatCancelacion(String as_idActo)
	    throws B2BException
	{
		NaturalezaJuridica lcnj_nj;
		lcnj_nj = null;

		if(StringUtils.isValidString(as_idActo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_NATURALEZA_JURIDICA_CANCELACION);

				lps_ps.setString(1, as_idActo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcnj_nj = getNaturalezaJuridica(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNatCancelacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcnj_nj;
	}

	/**
	 * Busca si un acto determinado pertenece a un grupo de naturaleza
	 * jurídica determinada.
	 *
	 * @param as_idActo Objeto que contiene el grupo de naturaleza jurídica
	 * y el código del acto para ser utilizados en el filtro de la consulta
	 * @return Registro obtenido como resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	public NaturalezaJuridica findNatMedidaCautelar(NaturalezaJuridica as_idActo)
	    throws B2BException
	{
		NaturalezaJuridica lcnj_nj;
		lcnj_nj = null;

		if(as_idActo != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_cont;
				li_cont     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_NATURALEZA_BY_GRUPO_ACTO);

				lps_ps.setString(li_cont++, as_idActo.getIdGrupoNatJur());
				lps_ps.setString(li_cont++, as_idActo.getIdNaturalezaJuridica());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lcnj_nj = getNaturalezaJuridica(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findNatMedidaCautelar", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lcnj_nj;
	}

	/**
	 * Dependiendo del procedimiento seleccionado, inserta o actualiza un registro
	 * con la información del subproceso suministrada.
	 *
	 * @param as_param objeto contenedor de la información a actualizar o insertar
	 * @param ab_query define el proceso seleccionado, true para insertar un nuevo
	 * registro, false para actualizar un registro existente.
	 * @throws B2BException
	 */
	public void insertOrUpdate(NaturalezaJuridica as_param, boolean ab_query)
	    throws B2BException
	{
		if(as_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);

				if(ab_query)
				{
					lps_ps.setString(li_column++, as_param.getIdDominioNatJur());
					lps_ps.setString(li_column++, as_param.getIdGrupoNatJur());
					lps_ps.setString(li_column++, as_param.getIdNaturalezaJuridica());
					lps_ps.setLong(li_column++, as_param.getVersion());
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getHabilitadaCalificacion());
					lps_ps.setString(li_column++, as_param.getAlertaTitular());
					lps_ps.setString(li_column++, as_param.getIdtipoRrr());
					lps_ps.setString(li_column++, as_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, as_param.getBusquedaTitular());
					lps_ps.setString(li_column++, as_param.getHeredarAnotacion());
					lps_ps.setString(li_column++, as_param.getConstruirComplementacion());
					lps_ps.setString(li_column++, as_param.getRequiereCierreFolioString());
					lps_ps.setString(li_column++, as_param.getSumatoriaArea());
					lps_ps.setString(li_column++, as_param.getSumatoriaCoeficiente());
					lps_ps.setString(li_column++, as_param.getIpCreacion());
					lps_ps.setString(li_column++, as_param.getValidarAreaString());
					lps_ps.setString(li_column++, as_param.getActualizarLindero());
					lps_ps.setString(li_column++, as_param.getPropietario());
					lps_ps.setString(li_column++, as_param.getRol());
					lps_ps.setString(li_column++, as_param.getHabilitaSecuenciaString());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdDominioRrr());
				}
				else
				{
					lps_ps.setString(li_column++, as_param.getIdDominioNatJur());
					lps_ps.setString(li_column++, as_param.getIdGrupoNatJur());
					lps_ps.setString(li_column++, as_param.getNombre());
					lps_ps.setString(li_column++, as_param.getHabilitadaCalificacion());
					lps_ps.setString(li_column++, as_param.getAlertaTitular());
					lps_ps.setString(li_column++, as_param.getIdtipoRrr());
					lps_ps.setString(li_column++, as_param.getBusquedaTitular());
					lps_ps.setString(li_column++, as_param.getHeredarAnotacion());
					lps_ps.setString(li_column++, as_param.getConstruirComplementacion());
					lps_ps.setString(li_column++, as_param.getRequiereCierreFolioString());
					lps_ps.setString(li_column++, as_param.getSumatoriaArea());
					lps_ps.setString(li_column++, as_param.getSumatoriaCoeficiente());
					lps_ps.setString(li_column++, as_param.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, as_param.getIpModificacion());
					lps_ps.setString(li_column++, as_param.getValidarAreaString());
					lps_ps.setString(li_column++, as_param.getActualizarLindero());
					lps_ps.setString(li_column++, as_param.getPropietario());
					lps_ps.setString(li_column++, as_param.getRol());
					lps_ps.setString(li_column++, as_param.getHabilitaSecuenciaString());
					lps_ps.setString(li_column++, as_param.getActivo());
					lps_ps.setString(li_column++, as_param.getIdDominioRrr());
					lps_ps.setString(li_column++, as_param.getIdNaturalezaJuridica());
					lps_ps.setLong(li_column++, as_param.getVersion());
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
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de naturaleza juridica.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de naturaleza juridica
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see NaturalezaJuridica
	 */
	private NaturalezaJuridica getNaturalezaJuridica(ResultSet ars_rs)
	    throws SQLException
	{
		NaturalezaJuridica lnj_naturalezaJuridica;

		lnj_naturalezaJuridica = new NaturalezaJuridica();

		lnj_naturalezaJuridica.setIdDominioNatJur(ars_rs.getString("ID_DOMINIO_NAT_JUR"));
		lnj_naturalezaJuridica.setIdGrupoNatJur(ars_rs.getString("ID_GRUPO_NAT_JURIDICA"));

		{
			String ls_idNaturalezaJuridica;
			long   ll_version;

			ls_idNaturalezaJuridica     = ars_rs.getString("ID_NATURALEZA_JURIDICA");
			ll_version                  = ars_rs.getLong("VERSION");

			lnj_naturalezaJuridica.setIdNaturalezaJuridica(ls_idNaturalezaJuridica);
			lnj_naturalezaJuridica.setVersion(ll_version);

			if(StringUtils.isValidString(ls_idNaturalezaJuridica) && (ll_version > 0))
				lnj_naturalezaJuridica.setLlavePrimaria(ls_idNaturalezaJuridica + "-" + ll_version);
		}

		lnj_naturalezaJuridica.setNombre(ars_rs.getString("NOMBRE"));
		lnj_naturalezaJuridica.setHabilitadaCalificacion(ars_rs.getString("HABILITADA_CALIFICACION"));
		lnj_naturalezaJuridica.setAlertaTitular(ars_rs.getString("ALERTA_TITULAR"));
		lnj_naturalezaJuridica.setIdtipoRrr(ars_rs.getString("ID_TIPO_RRR"));
		lnj_naturalezaJuridica.setBusquedaTitular(ars_rs.getString("BUSQUEDA_TITULAR"));
		lnj_naturalezaJuridica.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lnj_naturalezaJuridica.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lnj_naturalezaJuridica.setConstruirComplementacion(ars_rs.getString("CONSTRUIR_COMPLEMENTACION"));
		lnj_naturalezaJuridica.setIdDominioRrr(ars_rs.getString("ID_DOMINIO_RRR"));

		{
			String ls_validarArea;

			ls_validarArea = ars_rs.getString("VALIDAR_AREA");

			if(StringUtils.isValidString(ls_validarArea))
				lnj_naturalezaJuridica.setValidarArea(ls_validarArea.equalsIgnoreCase(EstadoCommon.S));
			else
				lnj_naturalezaJuridica.setValidarArea(false);
		}

		{
			String ls_requiereFolio;

			ls_requiereFolio = ars_rs.getString("REQUIERE_CIERRE_FOLIO");

			if(StringUtils.isValidString(ls_requiereFolio))
				lnj_naturalezaJuridica.setRequiereCierreFolio(ls_requiereFolio.equalsIgnoreCase(EstadoCommon.S));
			else
				lnj_naturalezaJuridica.setRequiereCierreFolio(false);
		}

		{
			String ls_habilitaSecuencia;

			ls_habilitaSecuencia = ars_rs.getString("HABILITA_SECUENCIA");

			if(StringUtils.isValidString(ls_habilitaSecuencia))
				lnj_naturalezaJuridica.setHabilitaSecuencia(ls_habilitaSecuencia.equalsIgnoreCase(EstadoCommon.S));
			else
				lnj_naturalezaJuridica.setHabilitaSecuencia(false);
		}

		return lnj_naturalezaJuridica;
	}

	/**
	 * Permite almacenar los datos que retornó la consulta en los atributos
	 * de un objeto.
	 *
	 * @param lrs_rs contenedor del resultado de a consulta
	 * @return objeto contenedor de los datos que retornó la consulta
	 * @throws SQLException
	 */
	private NaturalezaJuridica getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		NaturalezaJuridica lnj_solicitud;

		lnj_solicitud = new NaturalezaJuridica();

		lnj_solicitud.setIdDominioNatJur(lrs_rs.getString("ID_DOMINIO_NAT_JUR"));
		lnj_solicitud.setIdGrupoNatJur(lrs_rs.getString("ID_GRUPO_NAT_JURIDICA"));
		lnj_solicitud.setIdNaturalezaJuridica(lrs_rs.getString("ID_NATURALEZA_JURIDICA"));
		lnj_solicitud.setVersion(lrs_rs.getLong("VERSION"));
		lnj_solicitud.setNombre(lrs_rs.getString("NOMBRE"));
		lnj_solicitud.setHabilitadaCalificacion(lrs_rs.getString("HABILITADA_CALIFICACION"));
		lnj_solicitud.setAlertaTitular(lrs_rs.getString("ALERTA_TITULAR"));
		lnj_solicitud.setIdtipoRrr(lrs_rs.getString("ID_TIPO_RRR"));
		lnj_solicitud.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lnj_solicitud.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lnj_solicitud.setBusquedaTitular(lrs_rs.getString("BUSQUEDA_TITULAR"));
		lnj_solicitud.setHeredarAnotacion(lrs_rs.getString("HEREDAR_ANOTACION"));
		lnj_solicitud.setConstruirComplementacion(lrs_rs.getString("CONSTRUIR_COMPLEMENTACION"));
		lnj_solicitud.setRequiereCierreFolioString(lrs_rs.getString("REQUIERE_CIERRE_FOLIO"));
		lnj_solicitud.setSumatoriaArea(lrs_rs.getString("SUMATORIA_AREA"));
		lnj_solicitud.setSumatoriaCoeficiente(lrs_rs.getString("SUMATORIA_COEFICIENTE"));
		lnj_solicitud.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lnj_solicitud.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lnj_solicitud.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lnj_solicitud.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		lnj_solicitud.setValidarAreaString(lrs_rs.getString("VALIDAR_AREA"));
		lnj_solicitud.setActualizarLindero(lrs_rs.getString("ACTUALIZAR_LINDERO"));
		lnj_solicitud.setPropietario(lrs_rs.getString("PROPIETARIO"));
		lnj_solicitud.setRol(lrs_rs.getString("ROL"));
		lnj_solicitud.setHabilitaSecuenciaString(lrs_rs.getString("HABILITA_SECUENCIA"));
		lnj_solicitud.setActivo(lrs_rs.getString("ACTIVO"));
		lnj_solicitud.setIdDominioRrr(lrs_rs.getString("ID_DOMINIO_RRR"));

		return lnj_solicitud;
	}
}
