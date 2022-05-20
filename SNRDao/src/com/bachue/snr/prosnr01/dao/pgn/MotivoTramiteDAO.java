package com.bachue.snr.prosnr01.dao.pgn;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.IdentificadoresCommon;

import com.bachue.snr.prosnr01.dao.IBase;

import com.bachue.snr.prosnr01.model.sdb.pgn.MotivoTramite;

import java.math.BigDecimal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase para el manejo de datos para la tabla SDB_PGN_MOTIVO_TRAMITE.
 *
 * @author Sebastian Tafur
 */
public class MotivoTramiteDAO extends BaseDAO implements IBase<MotivoTramite>
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID_MOTIVO_TRAMITE = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_MOTIVO_TRAMITE = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_ETAPA_ORIGEN=? AND ID_MOTIVO=?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_UNIQUE = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ? AND ID_ETAPA_ORIGEN = ?"
		+ "AND VERSION_SUBPROCESO = ? AND ID_MOTIVO = ? AND ID_ETAPA_POSTERIOR= ?";

	/** Constante cs_FIND_BY_ID_ETAPA. */
	private static final String cs_FIND_BY_ID_ETAPA = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_ETAPA_ORIGEN=? AND ESTADO='A'";

	/** Constante cs_FIND_BY_ID_ETAPA_ID_PROCESO_AND_ID_SUBPROCESO. */
	private static final String cs_FIND_BY_ID_PROCESO_AND_ID_SUBPROCESO_ID_ETAPA = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ? AND ID_ETAPA_ORIGEN = ? AND ESTADO='A'";

	/** Constante cs_FIND_BY_KEY_ACTIVE. */
	private static final String cs_FIND_BY_KEY_ACTIVE = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_PROCESO = ? AND ID_SUBPROCESO = ? AND ID_ETAPA_ORIGEN = ? AND ID_MOTIVO = ? AND VERSION_SUBPROCESO = ?";

	/** Constante cs_FIND_BY_ID_ETAPA. */
	private static final String cs_FIND_DISTINCT_BY_ID_PROCESO_AND_ID_ETAPA = "SELECT DISTINCT ID_MOTIVO, DESCRIPCION FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_PROCESO = ? AND ID_ETAPA_ORIGEN = ? AND ESTADO='A' ORDER BY LENGTH(ID_MOTIVO),ID_MOTIVO";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_MOTIVO_TRAMITE SET DESCRIPCION = ?, ESTADO = ?, ID_ETAPA_POSTERIOR = ?, ESTADO_ACTIVIDAD = ?, INDICADOR_DEVOLUCION = ?, TIPO_COMPUERTA_LLEGADA = ?, DECISION_CALIFICACION = ?, ID_USUARIO_MODIFICACION = ?, IP_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, ID_PROCESO=?, ID_SUBPROCESO=?, VERSION_SUBPROCESO=? "
		+ "WHERE ID_MOTIVO_TRAMITE = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_MOTIVO_TRAMITE (ID_MOTIVO_TRAMITE ,ID_ETAPA_ORIGEN, ID_MOTIVO, DESCRIPCION, ESTADO, ID_ETAPA_POSTERIOR, ESTADO_ACTIVIDAD, INDICADOR_DEVOLUCION, TIPO_COMPUERTA_LLEGADA, DECISION_CALIFICACION, ID_USUARIO_CREACION, IP_CREACION, FECHA_CREACION, ID_PROCESO, ID_SUBPROCESO, VERSION_SUBPROCESO) "
		+ "VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, SYSTIMESTAMP, ?, ?, ?)";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE ";

	/** Constante cs_FIND_BY_ID_NOMBRE_ETAPA. */
	private static final String cs_FIND_BY_ID_NOMBRE_ETAPA = "SELECT X.*,Y.NOMBRE FROM SDB_PGN_MOTIVO_TRAMITE X INNER JOIN  SDB_PGN_ETAPA Y ON Y.ID_ETAPA=X.ID_ETAPA_ORIGEN ";

	/** Constante cs_FIND_DECISION_CALIFICACION_BY_ETAPA_AND_MOTIVO. */
	private static final String cs_FIND_DECISION_CALIFICACION_BY_ETAPA_AND_MOTIVO = "SELECT * FROM SDB_PGN_MOTIVO_TRAMITE WHERE ID_ETAPA_ORIGEN = ? AND ID_MOTIVO = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_PGN_MOTIVO_TRAMITE_ID_MOTIVO_TRAMITE.NEXTVAL FROM DUAL";

/**
 * Método para traer de la base de datos todos lo registros de la tabla SDB_PGN_MOTIVO_TRAMITE.
 *
 * @return devuelve el valor del objeto collection
 * @throws B2BException Señala que se ha producido una excepción
 */
	public Collection<MotivoTramite> findAll()
	    throws B2BException
	{
		Collection<MotivoTramite> lcmt_cmt;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcmt_cmt     = new ArrayList<MotivoTramite>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmt_cmt.add(getObjetFromResultSet(lrs_rs));
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

		if(lcmt_cmt.isEmpty())
			lcmt_cmt = null;

		return lcmt_cmt;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coíncida con una ID_ETAPA y un ID_MOTIVO específico.
	 *
	 * @param amt_param correspondiente al valor del tipo de objeto MotivoTramite
	 * @return devuelve el valor del objeto motivo tramite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MotivoTramite findById(MotivoTramite amt_param)
	    throws B2BException
	{
		return (amt_param != null) ? findById(amt_param.getIdEtapaOrigen(), amt_param.getIdMotivo()) : null;
	}

	/**
	 * Método para encontrar todos los registros de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * que coíncida con una ID_ETAPA y un ID_MOTIVO específico.
	 *
	 * @param al_idEtapaOrigen correspondiente al valor del tipo de objeto id etapa
	 * @param al_idMotivo correspondiente al valor del tipo de objeto id motivo
	 * @return devuelve el valor del objeto motivo tramite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MotivoTramite findById(long al_idEtapaOrigen, long al_idMotivo)
	    throws B2BException
	{
		MotivoTramite lmt_data;

		lmt_data = null;

		if((al_idEtapaOrigen > 0L) && (al_idMotivo > 0L))
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

				lps_ps.setLong(li_contador++, al_idEtapaOrigen);
				lps_ps.setLong(li_contador++, al_idMotivo);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmt_data = getObjetFromResultSet(lrs_rs);
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

		return lmt_data;
	}

	/**
	 * Método encargado de buscar un motivo trámite por su id
	 *
	 * @param as_idMotivoTramite correspondiente al id del motivo trámite
	 * @return el valor de motivo tramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotivoTramite findById(String as_idMotivoTramite)
	    throws B2BException
	{
		MotivoTramite lmt_data;

		lmt_data = null;

		if(StringUtils.isValidString(as_idMotivoTramite))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_MOTIVO_TRAMITE);

				lps_ps.setString(li_contador++, as_idMotivoTramite);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmt_data = getObjetFromResultSet(lrs_rs);
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

		return lmt_data;
	}

	/**
	 * Método para traer de la base de los registros de motivos por su id proceso, id subproceso y id etapa.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @param al_idEtapa de al id etapa
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> findByIdProcesoIdSubProcesoAndIdEtapa(
	    String as_idProceso, String as_idSubProceso, long al_idEtapa
	)
	    throws B2BException
	{
		Collection<MotivoTramite> lcmt_cmt;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcmt_cmt     = new ArrayList<MotivoTramite>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_PROCESO_AND_ID_SUBPROCESO_ID_ETAPA);

			lps_ps.setString(li_contador++, as_idProceso);
			lps_ps.setString(li_contador++, as_idSubProceso);
			lps_ps.setLong(li_contador++, al_idEtapa);
			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmt_cmt.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdProcesoIdSubProcesoAndIdEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcmt_cmt.isEmpty())
			lcmt_cmt = null;

		return lcmt_cmt;
	}

	/**
	 * Find by key active.
	 *
	 * @param as_idProceso de as id proceso
	 * @param as_idSubProceso de as id sub proceso
	 * @param al_idEtapa de al id etapa
	 * @param al_idMotivo de al id motivo
	 * @return el valor de motivo tramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotivoTramite findByKeyActive(
	    String as_idProceso, String as_idSubProceso, long al_idEtapa, long al_idMotivo, Long al_versionSubProceso
	)
	    throws B2BException
	{
		MotivoTramite     lmt_mt;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lmt_mt     = null;
		lps_ps     = null;
		lrs_rs     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_KEY_ACTIVE);

			lps_ps.setString(li_contador++, as_idProceso);
			lps_ps.setString(li_contador++, as_idSubProceso);
			lps_ps.setLong(li_contador++, al_idEtapa);
			lps_ps.setLong(li_contador++, al_idMotivo);
			setLong(lps_ps, al_versionSubProceso, li_contador++);
			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmt_mt = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByKeyActive", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lmt_mt;
	}

	/**
	 * Método para encontrar un registro en específico  de la tabla SDB_PGN_MOTIVO_TRAMITE
	 * consultando los índices de la tabla.
	 *
	 * @param as_idProceso correspondiente al id del proceso
	 * @param as_idSubproceso correspondiente al id del subproceso
	 * @param al_idEtapaOrigen correspondiente al valor del tipo de objeto id etapa
	 * @param al_version correspondiente al valor de la versión del subproceso
	 * @param al_idMotivo correspondiente al valor del tipo de objeto id motivo
	 * @param abd_etapaPosterior de abd etapa posterior
	 * @return el valor de motivo tramite
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public MotivoTramite findByUnique(
	    String as_idProceso, String as_idSubproceso, Long al_idEtapaOrigen, Long al_version, Long al_idMotivo,
	    BigDecimal abd_etapaPosterior
	)
	    throws B2BException
	{
		MotivoTramite lmt_data;

		lmt_data = null;

		if(
		    NumericUtils.isValidLong(al_idEtapaOrigen) && NumericUtils.isValidLong(al_version)
			    && NumericUtils.isValidLong(al_idMotivo) && NumericUtils.isValidBigDecimal(abd_etapaPosterior)
			    && StringUtils.isValidString(as_idProceso) && StringUtils.isValidString(as_idSubproceso)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_UNIQUE);

				lps_ps.setString(li_contador++, as_idProceso);
				lps_ps.setString(li_contador++, as_idSubproceso);
				setLong(lps_ps, al_idEtapaOrigen, li_contador++);
				setLong(lps_ps, al_version, li_contador++);
				setLong(lps_ps, al_idMotivo, li_contador++);
				lps_ps.setBigDecimal(li_contador++, abd_etapaPosterior);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lmt_data = getObjetFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByUnique", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lmt_data;
	}

	/**
	 * Método para encontrar Decisión calificación.
	 *
	 * @param amt_param correspondiente al valor del tipo de objeto MotivoTramite
	 * @return devuelve el valor del objeto motivo tramite
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public MotivoTramite findDecisionCalificacion(MotivoTramite amt_param)
	    throws B2BException
	{
		MotivoTramite     lmt_data;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lps_ps       = null;
		lrs_rs       = null;
		lmt_data     = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_DECISION_CALIFICACION_BY_ETAPA_AND_MOTIVO);

			lps_ps.setLong(li_contador++, amt_param.getIdEtapaOrigen());
			lps_ps.setLong(li_contador++, amt_param.getIdMotivo());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lmt_data = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDecisionCalificacion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lmt_data;
	}

	/**
	 * Método para traer de la base de los registros diferentes de motivos por su id proceso y id etapa.
	 *
	 * @param as_idProceso de as id proceso
	 * @param al_idEtapa de al id etapa
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> findDistinctByIdProcesoAndIdEtapa(String as_idProceso, long al_idEtapa)
	    throws B2BException
	{
		Collection<MotivoTramite> lcmt_cmt;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcmt_cmt     = new ArrayList<MotivoTramite>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_DISTINCT_BY_ID_PROCESO_AND_ID_ETAPA);

			lps_ps.setString(li_contador++, as_idProceso);
			lps_ps.setLong(li_contador++, al_idEtapa);
			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmt_cmt.add(getObjetFromResultSetIdMotivoDescripcion(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findDistinctByIdProcesoAndIdEtapa", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(lcmt_cmt.isEmpty())
			lcmt_cmt = null;

		return lcmt_cmt;
	}

/**
 * Retorna el valor del objeto de tipo Find etapa by id.
 *
 * @return devuelve el valor del objeto collection
 * @throws B2BException Señala que se ha producido una excepción
 */
	public Collection<MotivoTramite> findEtapaById()
	    throws B2BException
	{
		Collection<MotivoTramite> lcmt_cmt;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lps_ps       = null;
		lrs_rs       = null;
		lcmt_cmt     = new ArrayList<MotivoTramite>();

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_BY_ID_NOMBRE_ETAPA);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmt_cmt.add(getObjetFromResultSetNombre(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findEtapaById", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lcmt_cmt;
	}

	/**
	 * Método para encontrar los motivos tramite a partir del ID_ETAPA.
	 *
	 * @param as_etapa String para hacer el filtro en la BD
	 * @param ls_accion String para hacer un filtro especifico en la BD
	 * @param ab_isRepConstancia correspondiente al valor del tipo de objeto boolean
	 * @param ab_isCalificacion correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<MotivoTramite> findMotivosByEtapa(
	    String as_etapa, String ls_accion, boolean ab_isRepConstancia, boolean ab_isCalificacion
	)
	    throws B2BException
	{
		Collection<MotivoTramite> lcmt_cmt;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;
		StringBuilder             lsb_query;

		lcmt_cmt      = new ArrayList<MotivoTramite>();
		lps_ps        = null;
		lrs_rs        = null;
		lsb_query     = new StringBuilder(cs_FIND_BY_ID_ETAPA);

		try
		{
			if(StringUtils.isValidString(ls_accion))
			{
				if(ls_accion.equalsIgnoreCase(IdentificadoresCommon.MAYOR_VALOR_VENCIDO))
					lsb_query = lsb_query.append(" AND ID_MOTIVO = 60");
				else if(ls_accion.equalsIgnoreCase(IdentificadoresCommon.REPRODUCCION_CONSTANCIA))
				{
					lsb_query     = new StringBuilder(cs_FIND_ALL);

					lsb_query = lsb_query.append(
						    " WHERE ID_ETAPA_ORIGEN = ? AND ID_MOTIVO IN (60,70,160) AND ID_PROCESO='6' AND ID_SUBPROCESO='4'"
						);
				}
				else if(ls_accion.equalsIgnoreCase(IdentificadoresCommon.CERTIFICADO_ANTIGUO_SISTEMA))
					lsb_query = lsb_query.append(" AND ID_MOTIVO <> 30");
				else if(ls_accion.equalsIgnoreCase(IdentificadoresCommon.ANALISIS_DE_RECURSOS_RESOLUCION_DE_RECURSOS))
					lsb_query = lsb_query.append(" AND ID_MOTIVO <> 50");
				else if(ab_isCalificacion)
				{
					if(!ab_isRepConstancia)
						lsb_query = lsb_query.append(" AND ID_MOTIVO <> 160");
				}
			}
			else if(ab_isCalificacion)
			{
				if(!ab_isRepConstancia)
					lsb_query = lsb_query.append(" AND ID_MOTIVO <> 160");
			}

			lps_ps = getConnection().prepareStatement(lsb_query.toString());
			lps_ps.setString(1, as_etapa);
			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcmt_cmt.add(getObjetFromResultSet(lrs_rs));
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

		if(lcmt_cmt.isEmpty())
			lcmt_cmt = null;

		return lcmt_cmt;
	}

	/**
	 * Retorna el valor de la secuencia.
	 *
	 * @return devuelve el valor del objeto int
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findSecuence", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_secuencia;
	}

	/**
	 * Método para insertar o actualizar registros en la base de datos.
	 *
	 * @param amt_parametros correspondiente al valor del tipo de objeto MotivoTramite
	 * @param ab_query indica si se desea insertar o actualizar(true inserta, false modifica)
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	@Override
	public void insertOrUpdate(MotivoTramite amt_parametros, boolean ab_query)
	    throws B2BException
	{
		if(amt_parametros != null)
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
					lps_ps.setString(li_column++, StringUtils.getString(findSecuence()));
					lps_ps.setLong(li_column++, amt_parametros.getIdEtapaOrigen());
					lps_ps.setLong(li_column++, amt_parametros.getIdMotivo());
				}

				lps_ps.setString(li_column++, amt_parametros.getDescripcion());
				lps_ps.setString(li_column++, amt_parametros.getEstado());
				lps_ps.setBigDecimal(li_column++, amt_parametros.getIdEtapaPosterior());
				lps_ps.setString(li_column++, amt_parametros.getEstadoActividad());
				lps_ps.setString(li_column++, amt_parametros.getIndicadorDevolucion());
				lps_ps.setString(li_column++, amt_parametros.getTipoCompuertaLlegada());
				lps_ps.setString(li_column++, amt_parametros.getDecisionCalificacion());

				if(!ab_query)
				{
					lps_ps.setString(li_column++, amt_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, amt_parametros.getIpModificacion());
				}
				else
				{
					lps_ps.setString(li_column++, amt_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, amt_parametros.getIpCreacion());
				}

				lps_ps.setString(li_column++, amt_parametros.getIdProceso());
				lps_ps.setString(li_column++, amt_parametros.getIdSubproceso());
				setLong(lps_ps, amt_parametros.getVersionSubproceso(), li_column++);

				if(!ab_query)
					lps_ps.setString(li_column++, amt_parametros.getIdMotivoTramite());

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
	 * Retorna el valor de MotivoTramite.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de MotivoTramite
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see MotivoTramite
	 */
	private MotivoTramite getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		MotivoTramite lmt_datos;

		lmt_datos = new MotivoTramite();

		lmt_datos.setIdEtapaOrigen(ars_rs.getLong("ID_ETAPA_ORIGEN"));
		lmt_datos.setIdMotivo(ars_rs.getLong("ID_MOTIVO"));
		lmt_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lmt_datos.setEstado(ars_rs.getString("ESTADO"));
		lmt_datos.setIdEtapaPosterior(ars_rs.getBigDecimal("ID_ETAPA_POSTERIOR"));
		lmt_datos.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lmt_datos.setIndicadorDevolucion(ars_rs.getString("INDICADOR_DEVOLUCION"));
		lmt_datos.setDecisionCalificacion(ars_rs.getString("DECISION_CALIFICACION"));
		lmt_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lmt_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lmt_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lmt_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lmt_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lmt_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lmt_datos.setIdMotivoTramite(ars_rs.getString("ID_MOTIVO_TRAMITE"));
		lmt_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lmt_datos.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lmt_datos.setVersionSubproceso(getLong(ars_rs, "VERSION_SUBPROCESO"));
		lmt_datos.setTipoCompuertaLlegada(ars_rs.getString("TIPO_COMPUERTA_LLEGADA"));
		lmt_datos.setFlujoDefecto(ars_rs.getString("FLUJO_DEFECTO"));
		lmt_datos.setDescriptorFin(ars_rs.getString("DESCRIPTOR_FIN"));

		return lmt_datos;
	}

	/**
	 * Retorna el valor de MotivoTramite cuando se tiene el id motivo y la descripcion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de MotivoTramite
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see MotivoTramite
	 */
	private MotivoTramite getObjetFromResultSetIdMotivoDescripcion(ResultSet ars_rs)
	    throws SQLException
	{
		MotivoTramite lmt_datos;

		lmt_datos = new MotivoTramite();

		lmt_datos.setIdMotivo(ars_rs.getLong("ID_MOTIVO"));
		lmt_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));

		return lmt_datos;
	}

	/**
	 * Método que asigna los resultados de una consulta a los atributos de un nuevo objeto de motivo trámite.
	 *
	 * @param ars_rs objeto que recoge el resultado de la consulta
	 * @return el valor de objet from result set nombre
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private MotivoTramite getObjetFromResultSetNombre(ResultSet ars_rs)
	    throws SQLException
	{
		MotivoTramite lmt_datos;

		lmt_datos = new MotivoTramite();

		lmt_datos.setIdEtapaOrigen(ars_rs.getLong("ID_ETAPA_ORIGEN"));
		lmt_datos.setIdMotivo(ars_rs.getLong("ID_MOTIVO"));
		lmt_datos.setDescripcion(ars_rs.getString("DESCRIPCION"));
		lmt_datos.setEstado(ars_rs.getString("ESTADO"));
		lmt_datos.setIdEtapaPosterior(ars_rs.getBigDecimal("ID_ETAPA_POSTERIOR"));
		lmt_datos.setEstadoActividad(ars_rs.getString("ESTADO_ACTIVIDAD"));
		lmt_datos.setIndicadorDevolucion(ars_rs.getString("INDICADOR_DEVOLUCION"));
		lmt_datos.setDecisionCalificacion(ars_rs.getString("DECISION_CALIFICACION"));
		lmt_datos.setEtapaNombre(ars_rs.getString("NOMBRE"));
		lmt_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lmt_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lmt_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lmt_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lmt_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lmt_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		lmt_datos.setIdMotivoTramite(ars_rs.getString("ID_MOTIVO_TRAMITE"));
		lmt_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		lmt_datos.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		lmt_datos.setVersionSubproceso(getLong(ars_rs, "VERSION_SUBPROCESO"));
		lmt_datos.setTipoCompuertaLlegada(ars_rs.getString("TIPO_COMPUERTA_LLEGADA"));
		lmt_datos.setFlujoDefecto(ars_rs.getString("FLUJO_DEFECTO"));
		lmt_datos.setDescriptorFin(ars_rs.getString("DESCRIPTOR_FIN"));

		return lmt_datos;
	}
}
