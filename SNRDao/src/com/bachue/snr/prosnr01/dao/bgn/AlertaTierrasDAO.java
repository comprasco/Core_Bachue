package com.bachue.snr.prosnr01.dao.bgn;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.bng.AlertaTierras;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;


/**
 * Clase que contiene todos las propiedades AlertaTierrasDAO.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 1/04/2020
 */
public class AlertaTierrasDAO extends AuditoriaDao
{
	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_BGN_ALERTA_TIERRAS";

	/** Constante cs_FIND_ALL_FILTER_SNR. */
	private static final String cs_FIND_ALL_FILTER_SNR = "SELECT AT.*, EE.NOMBRE AS NOM_ENTIDAD, CE.NOMBRE_COMUNIDAD AS NOM_COMUNIDAD_ETNICA "
		+ "FROM SDB_BGN_ALERTA_TIERRAS AT"
		+ " INNER JOIN SDB_VW_ENTIDADES_ESPECIALES EE ON AT.ID_ENTIDAD = EE.ID_ENTIDAD_EXTERNA"
		+ " INNER JOIN SDB_BNG_ALERTA_T_COMUNIDAD ATC ON AT.ID_ALERTA_TIERRAS = ATC.ID_ALERTA_TIERRA"
		+ " INNER JOIN SDB_PNG_COMUNIDADES_ETNICAS CE ON ATC.ID_COMUNIDAD_ETNICA = CE.ID_COMUNIDAD";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_MORE_PARAMETERS_BY_ID = "SELECT AT.*, EE.NOMBRE AS NOM_ENTIDAD, OO.NOMBRE AS NOM_OFICINA_ORIGEN, DOCP.NOMBRE AS NOM_TIPO_DOCUMENTO_PUBLICO"
		+ " FROM SDB_BGN_ALERTA_TIERRAS AT"
		+ " INNER JOIN SDB_VW_ENTIDADES_ESPECIALES EE ON AT.ID_ENTIDAD = EE.ID_ENTIDAD_EXTERNA"
		+ " INNER JOIN SDB_PGN_OFICINA_ORIGEN OO ON AT.DOCUMENTO_OFICINA_ORIGEN = OO.ID_OFICINA_ORIGEN"
		+ " INNER JOIN SDB_PGN_TIPO_DOCUMENTO_PUBLICO DOCP ON AT.DOCUMENTO_TIPO = DOCP.ID_TIPO_DOCUMENTO"
		+ " WHERE AT.ID_ALERTA_TIERRAS = ?";

	/** Constante cs_FIND_BY_NUMERO_DOCUMENTO. */
	private static final String cs_FIND_BY_NUMERO_DOCUMENTO = "SELECT * FROM SDB_BGN_ALERTA_TIERRAS WHERE DOCUMENTO_NUMERO = ?";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BGN_ALERTA_TIERRAS WHERE ID_ALERTA_TIERRAS = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_BGN_ALERTA_TIERRAS (ID_ALERTA_TIERRAS,TIPO_ALERTA,ID_ENTIDAD,NUMERO_RADICADO,FECHA_RADICADO,DOCUMENTO_OFICINA_ORIGEN,DOCUMENTO_TIPO,DOCUMENTO_FECHA,DOCUMENTO_NUMERO,DOCUMENTO_ID,DOCUMENTO_DOCNAME,FECHA_FIN_ALERTA,ESTADO_ALERTA,CREADO_SNR,NIR,OBSERVACION,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,FECHA_INSCRIPCION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_BGN_ALERTA_TIERRAS SET TIPO_ALERTA = ?, ID_ENTIDAD = ?, NUMERO_RADICADO = ?, FECHA_RADICADO = ?, DOCUMENTO_OFICINA_ORIGEN = ?, DOCUMENTO_TIPO = ?, DOCUMENTO_FECHA = ?, DOCUMENTO_NUMERO = ?, FECHA_INSCRIPCION = ?, DOCUMENTO_ID = ?, DOCUMENTO_DOCNAME = ?, FECHA_FIN_ALERTA = ?, ESTADO_ALERTA = ?, CREADO_SNR = ?, NIR = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ?, OBSERVACION = ? WHERE ID_ALERTA_TIERRAS = ?";

	/** Constante cs_DELETE. */
	private static final String cs_DELETE = "DELETE FROM SDB_BGN_ALERTA_TIERRAS WHERE ID_ALERTA_TIERRAS = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_BGN_ALERTA_TIERRAS_ID_ALERTA_TIERRAS.NEXTVAL FROM DUAL";

	/**
	 * Find all.
	 *
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTierras> findAll()
	    throws B2BException
	{
		Collection<AlertaTierras> lcat_cat;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lcat_cat     = new ArrayList<AlertaTierras>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcat_cat.add(getObjectFromResultSet(lrs_rs));
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

		if(lcat_cat.isEmpty())
			lcat_cat = null;

		return lcat_cat;
	}

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @return el valor de alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTierras findById(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		AlertaTierras     lat_alertaTierras;

		lps_ps                = null;
		lrs_rs                = null;
		lat_alertaTierras     = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lat_alertaTierras = getObjectFromResultSet(lrs_rs);
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

		return lat_alertaTierras;
	}

	/**
	 * Find all filter SNR.
	 *
	 * @param lat_alertaTierras de lat alerta tierras
	 * @param ad_fechaInscripcion de ad fecha inscripcion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<AlertaTierras> findAllFilterSNR(AlertaTierras lat_alertaTierras, Date ad_fechaInscripcion)
	    throws B2BException
	{
		Collection<AlertaTierras> lcat_alertaTierras;

		lcat_alertaTierras = new ArrayList<AlertaTierras>();

		if(lat_alertaTierras != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_count;
				String        ls_idCirculo;
				long          ll_idMatricula;

				lsb_sb             = new StringBuilder(cs_FIND_ALL_FILTER_SNR);
				li_count           = 1;
				ls_idCirculo       = lat_alertaTierras.getIdCirculo();
				ll_idMatricula     = lat_alertaTierras.getIdMatricula();

				if(StringUtils.isValidString(ls_idCirculo) || (ll_idMatricula > 0))
					lsb_sb.append(
					    " INNER JOIN SBD_BNG_ALERTA_T_PREDIO ATP ON AT.ID_ALERTA_TIERRAS = ATP.ID_ALERTA_TIERRAS"
					);

				{
					StringBuilder lsb_parametros;
					String        ls_parametro;
					String        ls_estadoAlerta;
					long          ll_idEntidad;
					String        ls_documentoTipo;
					String        ls_nomComunidadEtnica;
					int           li_countParametros;

					lsb_parametros            = new StringBuilder();
					ls_parametro              = " = ?";
					ls_estadoAlerta           = lat_alertaTierras.getEstadoAlerta();
					ll_idEntidad              = lat_alertaTierras.getIdEntidad();
					ls_documentoTipo          = lat_alertaTierras.getDocumentoTipo();
					ls_nomComunidadEtnica     = lat_alertaTierras.getNomComunidadEtnica();
					li_countParametros        = 0;

					if(StringUtils.isValidString(ls_estadoAlerta))
					{
						lsb_parametros.append(" AT.ESTADO_ALERTA");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(ll_idEntidad > 0)
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " AT.ID_ENTIDAD");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(ad_fechaInscripcion != null)
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " AT.FECHA_INSCRIPCION");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(StringUtils.isValidString(ls_documentoTipo))
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " AT.DOCUMENTO_TIPO");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(StringUtils.isValidString(ls_idCirculo))
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " ATP.ID_CIRCULO");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(ll_idMatricula > 0)
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " ATP.ID_MATRICULA");
						lsb_parametros.append(ls_parametro);

						li_countParametros++;
					}

					if(StringUtils.isValidString(ls_nomComunidadEtnica))
					{
						lsb_parametros.append(((li_countParametros > 0) ? " AND" : "") + " CE.NOMBRE_COMUNIDAD");
						lsb_parametros.append(" LIKE ?");

						li_countParametros++;
					}

					{
						String ls_concatenacionParametros;

						ls_concatenacionParametros = lsb_parametros.toString();

						if(StringUtils.isValidString(ls_concatenacionParametros))
						{
							lsb_sb.append(" WHERE");
							lsb_sb.append(ls_concatenacionParametros);
						}
					}

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					if(StringUtils.isValidString(ls_estadoAlerta))
						lps_ps.setString(li_count++, ls_estadoAlerta);

					if(ll_idEntidad > 0)
						lps_ps.setLong(li_count++, ll_idEntidad);

					if(ad_fechaInscripcion != null)
						lps_ps.setString(
						    li_count++,
						    StringUtils.getString(
						        ad_fechaInscripcion, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO
						    )
						);

					if(StringUtils.isValidString(ls_documentoTipo))
						lps_ps.setString(li_count++, ls_documentoTipo);

					if(StringUtils.isValidString(ls_idCirculo))
						lps_ps.setString(li_count++, ls_idCirculo);

					if(StringUtils.isValidString(ls_nomComunidadEtnica))
						lps_ps.setString(li_count++, ls_nomComunidadEtnica);

					if(ll_idMatricula > 0)
						lps_ps.setLong(li_count++, ll_idMatricula);

					lrs_rs = lps_ps.executeQuery();

					while(lrs_rs.next())
						lcat_alertaTierras.add(getObjectFilterSNR(lrs_rs));
				}
			}
			catch(SQLException lse_e)
			{
				logError(this, "findAllFilterSNR", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcat_alertaTierras.isEmpty())
			lcat_alertaTierras = null;

		return lcat_alertaTierras;
	}

	/**
	 * Find by id.
	 *
	 * @param al_idAlertaTierras de al id alerta tierras
	 * @return el valor de alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTierras findMoreParametersById(long al_idAlertaTierras)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		AlertaTierras     lat_alertaTierras;

		lps_ps                = null;
		lrs_rs                = null;
		lat_alertaTierras     = null;

		try
		{
			int li_column;

			li_column     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_MORE_PARAMETERS_BY_ID);

			lps_ps.setLong(li_column++, al_idAlertaTierras);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lat_alertaTierras = getObjectNombres(lrs_rs);
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

		return lat_alertaTierras;
	}

	/**
	 * Find by documento.
	 *
	 * @param alt_alertaTierras de alt alerta tierras
	 * @param ab_parametrosAdicionales de ab parametros adicionales
	 * @return el valor de alerta tierras
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AlertaTierras findByDocumento(AlertaTierras alt_alertaTierras, boolean ab_parametrosAdicionales)
	    throws B2BException
	{
		AlertaTierras lat_alertaTierras;

		lat_alertaTierras = null;

		if(alt_alertaTierras != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_column;
				String        ls_documentoTipo;
				Date          ls_documentoFecha;
				String        ls_documentoOficinaOrigen;

				lsb_sb                        = new StringBuilder(cs_FIND_BY_NUMERO_DOCUMENTO);
				li_column                     = 1;
				ls_documentoTipo              = alt_alertaTierras.getDocumentoTipo();
				ls_documentoFecha             = alt_alertaTierras.getDocumentoFecha();
				ls_documentoOficinaOrigen     = alt_alertaTierras.getDocumentoOficinaOrigen();

				if(ab_parametrosAdicionales)
				{
					if(StringUtils.isValidString(ls_documentoTipo))
						lsb_sb.append(" AND DOCUMENTO_TIPO = ?");

					if(ls_documentoFecha != null)
						lsb_sb.append(" AND DOCUMENTO_FECHA = ?");

					if(StringUtils.isValidString(ls_documentoOficinaOrigen))
						lsb_sb.append(" AND DOCUMENTO_OFICINA_ORIGEN = ?");
				}

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, alt_alertaTierras.getDocumentoNumero());

				if(ab_parametrosAdicionales)
				{
					if(StringUtils.isValidString(ls_documentoTipo))
						lps_ps.setString(li_column++, alt_alertaTierras.getDocumentoTipo());

					if(ls_documentoFecha != null)
						setDate(lps_ps, alt_alertaTierras.getDocumentoFecha(), li_column++);

					if(StringUtils.isValidString(ls_documentoOficinaOrigen))
						lps_ps.setString(li_column++, alt_alertaTierras.getDocumentoOficinaOrigen());
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lat_alertaTierras = getObjectFromResultSet(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByDocumento", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lat_alertaTierras;
	}

	/**
	 * Find secuence.
	 *
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long findSecuence()
	    throws B2BException
	{
		long              ll_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		ll_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ll_secuencia = lrs_rs.getLong(1);
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

		return ll_secuencia;
	}

	/**
	 * Insert.
	 *
	 * @param aat_parametro de aat parametro
	 * @return el valor de long
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public long insert(AlertaTierras aat_parametro)
	    throws B2BException
	{
		long ll_idAlertaTierras;

		ll_idAlertaTierras = 0;

		if(aat_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				ll_idAlertaTierras = findSecuence();

				if(ll_idAlertaTierras > 0)
				{
					int           li_column;
					StringBuilder lsb_sb;
					Date          ld_fechaInscripcion;

					li_column               = 1;
					lsb_sb                  = new StringBuilder(cs_INSERT);
					ld_fechaInscripcion     = aat_parametro.getFechaInscripcion();

					if(ld_fechaInscripcion != null)
						lsb_sb.append("?)");
					else
						lsb_sb.append("SYSTIMESTAMP)");

					lps_ps = getConnection().prepareStatement(lsb_sb.toString());

					lps_ps.setLong(li_column++, ll_idAlertaTierras);
					lps_ps.setString(li_column++, aat_parametro.getTipoAlerta());
					lps_ps.setLong(li_column++, aat_parametro.getIdEntidad());
					lps_ps.setString(li_column++, aat_parametro.getNumeroRadicado());
					setDate(lps_ps, aat_parametro.getFechaRadicado(), li_column++);
					lps_ps.setString(li_column++, aat_parametro.getDocumentoOficinaOrigen());
					lps_ps.setString(li_column++, aat_parametro.getDocumentoTipo());
					setDate(lps_ps, aat_parametro.getDocumentoFecha(), li_column++);
					lps_ps.setString(li_column++, aat_parametro.getDocumentoNumero());
					lps_ps.setString(li_column++, aat_parametro.getDocumentoId());
					lps_ps.setString(li_column++, aat_parametro.getDocumentoDocName());
					setDate(lps_ps, aat_parametro.getFechaFinAlerta(), li_column++);
					lps_ps.setString(li_column++, aat_parametro.getEstadoAlerta());
					lps_ps.setString(li_column++, aat_parametro.getCreadoSNR());
					lps_ps.setString(li_column++, aat_parametro.getNir());
					lps_ps.setString(li_column++, aat_parametro.getObservacion());
					lps_ps.setString(li_column++, aat_parametro.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aat_parametro.getIpCreacion());

					if(ld_fechaInscripcion != null)
						setDate(lps_ps, aat_parametro.getFechaInscripcion(), li_column++);

					lps_ps.executeUpdate();
				}
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

		return ll_idAlertaTierras;
	}

	/**
	 * Delete.
	 *
	 * @param aat_parametro de aat parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void delete(AlertaTierras aat_parametro)
	    throws B2BException
	{
		if(aat_parametro != null)
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE);

				lps_ps.setLong(li_column++, aat_parametro.getIdAlertaTierras());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "delete", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param aat_parametro de aat parametro
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(AlertaTierras aat_parametro)
	    throws B2BException
	{
		if(aat_parametro != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, aat_parametro.getTipoAlerta());
				lps_ps.setLong(li_column++, aat_parametro.getIdEntidad());
				lps_ps.setString(li_column++, aat_parametro.getNumeroRadicado());
				setDate(lps_ps, aat_parametro.getFechaRadicado(), li_column++);
				lps_ps.setString(li_column++, aat_parametro.getDocumentoOficinaOrigen());
				lps_ps.setString(li_column++, aat_parametro.getDocumentoTipo());
				setDate(lps_ps, aat_parametro.getDocumentoFecha(), li_column++);
				lps_ps.setString(li_column++, aat_parametro.getDocumentoNumero());
				setDate(lps_ps, aat_parametro.getFechaInscripcion(), li_column++);
				lps_ps.setString(li_column++, aat_parametro.getDocumentoId());
				lps_ps.setString(li_column++, aat_parametro.getDocumentoDocName());
				setDate(lps_ps, aat_parametro.getFechaFinAlerta(), li_column++);
				lps_ps.setString(li_column++, aat_parametro.getEstadoAlerta());
				lps_ps.setString(li_column++, aat_parametro.getCreadoSNR());
				lps_ps.setString(li_column++, aat_parametro.getNir());
				lps_ps.setString(li_column++, aat_parametro.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, aat_parametro.getIpModificacion());
				lps_ps.setString(li_column++, aat_parametro.getObservacion());
				lps_ps.setLong(li_column++, aat_parametro.getIdAlertaTierras());

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
	 * Retorna Objeto o variable de valor objet from result set.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de objet from result set
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTierras getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTierras lat_alertaTierras;

		lat_alertaTierras = new AlertaTierras();

		lat_alertaTierras.setIdAlertaTierras(ars_rs.getLong("ID_ALERTA_TIERRAS"));
		lat_alertaTierras.setTipoAlerta(ars_rs.getString("TIPO_ALERTA"));
		lat_alertaTierras.setIdEntidad(ars_rs.getLong("ID_ENTIDAD"));
		lat_alertaTierras.setNumeroRadicado(ars_rs.getString("NUMERO_RADICADO"));
		lat_alertaTierras.setFechaRadicado(ars_rs.getTimestamp("FECHA_RADICADO"));
		lat_alertaTierras.setDocumentoOficinaOrigen(ars_rs.getString("DOCUMENTO_OFICINA_ORIGEN"));
		lat_alertaTierras.setDocumentoTipo(ars_rs.getString("DOCUMENTO_TIPO"));
		lat_alertaTierras.setDocumentoFecha(ars_rs.getTimestamp("DOCUMENTO_FECHA"));
		lat_alertaTierras.setDocumentoNumero(ars_rs.getString("DOCUMENTO_NUMERO"));
		lat_alertaTierras.setFechaInscripcion(ars_rs.getTimestamp("FECHA_INSCRIPCION"));
		lat_alertaTierras.setDocumentoId(ars_rs.getString("DOCUMENTO_ID"));
		lat_alertaTierras.setDocumentoDocName(ars_rs.getString("DOCUMENTO_DOCNAME"));
		lat_alertaTierras.setFechaFinAlerta(ars_rs.getTimestamp("FECHA_FIN_ALERTA"));
		lat_alertaTierras.setEstadoAlerta(ars_rs.getString("ESTADO_ALERTA"));
		lat_alertaTierras.setCreadoSNR(ars_rs.getString("CREADO_SNR"));
		lat_alertaTierras.setNir(ars_rs.getString("NIR"));
		lat_alertaTierras.setObservacion(ars_rs.getString("OBSERVACION"));

		fillAuditoria(ars_rs, lat_alertaTierras);

		return lat_alertaTierras;
	}

	/**
	 * Retorna Objeto o variable de valor object nombres.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object nombres
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTierras getObjectNombres(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTierras lat_alertaTierras;

		lat_alertaTierras = getObjectFromResultSet(ars_rs);

		lat_alertaTierras.setNomEntidad(ars_rs.getString("NOM_ENTIDAD"));
		lat_alertaTierras.setNomOficinaOrigen(ars_rs.getString("NOM_OFICINA_ORIGEN"));
		lat_alertaTierras.setNomTipoDocumentoPublico(ars_rs.getString("NOM_TIPO_DOCUMENTO_PUBLICO"));

		return lat_alertaTierras;
	}

	/**
	 * Retorna Objeto o variable de valor object filter SNR.
	 *
	 * @param ars_rs de ars rs
	 * @return el valor de object filter SNR
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private AlertaTierras getObjectFilterSNR(ResultSet ars_rs)
	    throws SQLException
	{
		AlertaTierras lat_alertaTierras;

		lat_alertaTierras = getObjectFromResultSet(ars_rs);

		lat_alertaTierras.setNomEntidad(ars_rs.getString("NOM_ENTIDAD"));
		lat_alertaTierras.setNomComunidadEtnica(ars_rs.getString("NOM_COMUNIDAD_ETNICA"));

		return lat_alertaTierras;
	}
}
