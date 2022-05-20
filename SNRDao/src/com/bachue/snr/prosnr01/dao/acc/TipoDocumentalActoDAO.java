package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.registro.TipoDocumentalActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoActo;
import com.bachue.snr.prosnr01.model.sdb.pgn.TipoDocumental;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades de TipoDocumentalActoDAO.
 *
 * @author debeltran
 */
public class TipoDocumentalActoDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_SUBPROCESO. */
	private static final String cs_FIND_BY_SUBPROCESO = "SELECT PTD.* FROM SDB_PGN_TIPO_DOCUMENTAL PTD INNER JOIN SDB_PGN_TIPO_DOCUMENTAL_ACTO ATDA "
		+ "ON PTD.ID_TIPO_DOCUMENTAL = ATDA.ID_TIPO_DOCUMENTAL WHERE ATDA.ID_PROCESO = ? AND ATDA.ID_SUBPROCESO = ?";

	/** Constante cs_FIND_BY_ACTO_VERSION. */
	private static final String cs_FIND_BY_ACTO_VERSION = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL_ACTO WHERE ID_TIPO_ACTO = ? AND VERSION = ? ";

	/** Constante cs_SECUENCE. */
	private static final String cs_SECUENCE = "SELECT SEC_PGN_TIPO_DOCUMENTAL_ACTO_ID_TIPO_DOCUMENTAL_ACTO.NEXTVAL FROM DUAL";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_PGN_TIPO_DOCUMENTAL_ACTO (ID_TIPO_DOCUMENTAL_ACTO, ID_TIPO_DOCUMENTAL, ID_TIPO_ACTO, ID_USUARIO_CREACION, FECHA_CREACION, OBLIGATORIO, IP_CREACION, VERSION, ID_PROCESO, ID_SUBPROCESO, ACTIVO) VALUES (?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_PGN_TIPO_DOCUMENTAL_ACTO SET ID_TIPO_DOCUMENTAL=?, ID_TIPO_ACTO=?, OBLIGATORIO=?, ID_USUARIO_MODIFICACION=?, FECHA_MODIFICACION=SYSTIMESTAMP, IP_MODIFICACION=?, VERSION=?, ID_PROCESO=?, ID_SUBPROCESO=?,ACTIVO=? WHERE ID_TIPO_DOCUMENTAL_ACTO=?";

	/** Constante cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL_ACTO ORDER BY ID_TIPO_DOCUMENTAL_ACTO ASC";

	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_PGN_TIPO_DOCUMENTAL_ACTO WHERE ID_TIPO_DOCUMENTAL_ACTO =?";

	/**
	 *Metodo para encontrar todos los registros de Tipo Documental Acto
	 * @return una coleccion con los registros de tipo documental acto
	 * @throws B2BException
	 */
	public Collection<TipoDocumentalActo> findAll()
	    throws B2BException
	{
		Collection<TipoDocumentalActo> lctd_ctd;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		lctd_ctd     = new ArrayList<TipoDocumentalActo>();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_ALL);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lctd_ctd.add(getTipoDocumentalActo(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findAll", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lps_ps);
			close(lrs_rs);
		}

		if(!CollectionUtils.isValidCollection(lctd_ctd))
			lctd_ctd = null;

		return lctd_ctd;
	}

	/**
	 * Retorna el valor del objeto de Collection de TipoDocumentalActo filtrado por id tipo acto y version
	 *
	 * @param ata_ta correspondiente al valor del tipo de objeto TipoActo
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoDocumentalActo> findByActoVersion(TipoActo ata_ta)
	    throws B2BException
	{
		Collection<TipoDocumentalActo> lctda_ctda;

		lctda_ctda = new ArrayList<TipoDocumentalActo>();

		if(ata_ta != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_ACTO_VERSION);

				lps_ps.setString(li_column++, ata_ta.getIdTipoActo());
				lps_ps.setLong(li_column++, ata_ta.getVersion());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctda_ctda.add(getTipoDocumentalActo(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByActoVersion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(!CollectionUtils.isValidCollection(lctda_ctda))
				lctda_ctda = null;
		}

		return lctda_ctda;
	}

	/**
	 * Método pra encontrar el todos los registros de tipo Documental Acto caracterizados por su ID
	 * @param atd_param Tipo Documental Acto con la información de búsqueda
	 * @return TipoDocumentalActo con la información del registro
	 * @throws B2BException
	 */
	public TipoDocumentalActo findById(TipoDocumentalActo atd_param)
	    throws B2BException
	{
		return ((atd_param != null) ? findById(atd_param.getIdTipoDocumentalActo()) : null);
	}

	/**
	 * Método de consulta para encontar los tipoDocumentalActo por medio de su identificador único
	 * @param as_param long con el identificador de referencia para la búsqueda
	 * @return TipoDocumentalActo con la información de la búsqueda
	 * @throws B2BException
	 */
	public TipoDocumentalActo findById(long as_param)
	    throws B2BException
	{
		TipoDocumentalActo ltd_return;
		ltd_return = null;

		if(as_param != 0)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, String.valueOf(as_param));

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ltd_return = getTipoDocumentalActo(lrs_rs);
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
	 * Retorna el valor del objeto de Collection de TipoDocumental filtrado por subproceso
	 *
	 * @param atd_arg correspondiente al valor del tipo de objeto TipoDocumental
	 * @return devuelve el valor de Collection de TipoDocumental
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see Collection
	 */
	public Collection<TipoDocumental> findBySubproceso(TipoDocumental atd_arg)
	    throws B2BException
	{
		Collection<TipoDocumental> lctd_ctd;

		lctd_ctd = new ArrayList<TipoDocumental>();

		if(atd_arg != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_FIND_BY_SUBPROCESO);

				lps_ps.setString(li_column++, atd_arg.getIdProceso());
				lps_ps.setString(li_column++, atd_arg.getIdSubproceso());

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lctd_ctd.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findBySubproceso", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}

			if(!CollectionUtils.isValidCollection(lctd_ctd))
				lctd_ctd = null;
		}

		return lctd_ctd;
	}

	/**
	 *Método para insertar un nuevo tipo documental Acto
	 * @param atd_param la información a insertar.
	 * @throws B2BException
	 */
	public void insert(TipoDocumentalActo atd_param)
	    throws B2BException
	{
		if(atd_param != null)
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
							atd_param.setIdTipoDocumentalActo(NumericUtils.getLong(lo_o.toString()));
						else
							throw new B2BException(ErrorKeys.PGN_LINEA_PRODUCCION_SEQUENCE);
					}
				}

				lps_ps = getConnection().prepareStatement(cs_INSERT);

				lps_ps.setLong(li_column++, atd_param.getIdTipoDocumentalActo());
				lps_ps.setString(li_column++, atd_param.getIdTipoDocumental());
				lps_ps.setString(li_column++, atd_param.getIdTipoActo());

				lps_ps.setString(li_column++, atd_param.getIdUsuarioCreacion());

				lps_ps.setString(li_column++, atd_param.getObligatorio());
				lps_ps.setString(li_column++, atd_param.getIpCreacion());
				setLong(lps_ps, atd_param.getVersion(), li_column++);

				lps_ps.setString(li_column++, atd_param.getIdProceso());
				lps_ps.setString(li_column++, atd_param.getIdSubproceso());
				lps_ps.setString(li_column++, atd_param.getActivo());

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
	 *Método para modificar un Tipo Documental acto
	 * @param atd_param TipoDocumental Acto con la información a modificar
	 * @throws B2BException
	 */
	public void update(TipoDocumentalActo atd_param)
	    throws B2BException
	{
		if(atd_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_column++, atd_param.getIdTipoDocumental());
				lps_ps.setString(li_column++, atd_param.getIdTipoActo());
				lps_ps.setString(li_column++, atd_param.getObligatorio());

				lps_ps.setString(li_column++, atd_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, atd_param.getIpModificacion());

				{
					Long ll_version;
					long ll_ver;
					
					ll_version = atd_param.getVersion();
					ll_ver = 0;
					
					if(NumericUtils.isValidLong(ll_version))
						ll_ver = NumericUtils.getLong(ll_version);
					
					lps_ps.setLong(li_column++, ll_ver);
				}
				
				lps_ps.setString(li_column++, atd_param.getIdProceso());
				lps_ps.setString(li_column++, atd_param.getIdSubproceso());
				lps_ps.setString(li_column++, atd_param.getActivo());

				lps_ps.setLong(li_column++, atd_param.getIdTipoDocumentalActo());

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
	 * Retorna el valor de TipoDocumental
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de TipoDocumental
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private TipoDocumental getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		TipoDocumental ltd_datosTD;

		ltd_datosTD = new TipoDocumental();

		ltd_datosTD.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		ltd_datosTD.setTipoDocumental(ars_rs.getString("NOMBRE"));

		return ltd_datosTD;
	}

	/**
	 * Retorna el valor de tipo documental acto.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de tipo documental acto
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private TipoDocumentalActo getTipoDocumentalActo(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		TipoDocumentalActo ltda_datos;

		ltda_datos = new TipoDocumentalActo();

		ltda_datos.setIdTipoDocumentalActo(ars_rs.getLong("ID_TIPO_DOCUMENTAL_ACTO"));
		ltda_datos.setIdTipoDocumental(ars_rs.getString("ID_TIPO_DOCUMENTAL"));
		ltda_datos.setIdTipoActo(ars_rs.getString("ID_TIPO_ACTO"));
		ltda_datos.setObligatorio(ars_rs.getString("OBLIGATORIO"));
		ltda_datos.setVersion(getLong(ars_rs, "VERSION"));
		ltda_datos.setIdProceso(ars_rs.getString("ID_PROCESO"));
		ltda_datos.setIdSubproceso(ars_rs.getString("ID_SUBPROCESO"));
		ltda_datos.setActivo(ars_rs.getString("ACTIVO"));

		fillAuditoria(ars_rs, ltda_datos);

		return ltda_datos;
	}
}
