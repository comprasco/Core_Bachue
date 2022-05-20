package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.ErrorKeys;
import com.bachue.snr.prosnr01.common.constants.FormatoFechaCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr16.model.sdb.acc.Mensaje;

import java.math.BigInteger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_MENSAJE.
 *
 * @author Sebastian Sanchez
 */
public class MensajeDAO extends AuditoriaDao
{
	/** Constante  cs_FIND_ALL. */
	private static final String cs_FIND_ALL = "SELECT * FROM SDB_ACC_MENSAJE ";

	/** Constante cs_FIND_MENSAJES_FILTER. */
	private static final String cs_FIND_MENSAJES_FILTER = "SELECT MEN.* FROM SDB_ACC_MENSAJE MEN "
		+ "INNER JOIN SDB_PGN_CANAL CNL ON CNL.ID_CANAL = MEN.ID_CANAL "
		+ "INNER JOIN SDB_PGN_ESTADOS EST ON MEN.ID_ESTADO = EST.ID_ESTADO";

	/** Constante cs_FIND_BY_ID . */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_MENSAJE WHERE ID_MENSAJE=?";
	private static final String cs_FIND_BY_IDENTIFICADOR_MENSAJE = "SELECT * FROM SDB_ACC_MENSAJE WHERE IDENTIFICADOR_MENSAJE=?";

	/** Constante cs_FIND_BY_ID_EE. */
	private static final String cs_FIND_BY_ID_EE = "SELECT * FROM SDB_ACC_MENSAJE WHERE IDENTIFICADOR_EE=?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_MENSAJE SET ID_PLANTILLA=?, ID_ORIGEN=?, ID_CANAL=?, "
		+ "ID_ESTADO=?, CLASIFICACION=?, NIR=?, ID_TURNO=?, ID_CIRCULO=?, IDENTIFICADOR_EE=?, CORREO_ELECTRONICO=?, NUMERO_CELULAR=?, "
		+ "DIRECCION_CORRESPONDENCIA=?, INTENTO_ENVIO=?, FECHA_ENVIO=?, ID_USUARIO_MODIFICACION=?,IP_MODIFICACION=?,FECHA_MODIFICACION = SYSTIMESTAMP";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_MENSAJE(ID_MENSAJE, ID_PLANTILLA, ID_ORIGEN, ID_CANAL, ID_ESTADO, CLASIFICACION, NIR, ID_TURNO, ID_CIRCULO, "
		+ " IDENTIFICADOR_EE, CORREO_ELECTRONICO, NUMERO_CELULAR, DIRECCION_CORRESPONDENCIA, INTENTO_ENVIO, FECHA_ENVIO, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) "
		+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,0,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_MENSAJE_ID_MENSAJE.NEXTVAL FROM DUAL";

	/** Constante cs_COUNT_MENSAJE. */
	private static final String cs_COUNT_MENSAJE = "SELECT COUNT(0) FROM SDB_ACC_MENSAJE ";

	/**
	 * Consulta en base de datos todos los registros que se encuentren.
	 *
	 * @return Colección de Mensaje resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Mensaje> findAll()
	    throws B2BException
	{
		Collection<Mensaje> lcm_cm;
		PreparedStatement   lps_ps;
		ResultSet           lrs_rs;

		lcm_cm                                                   = new ArrayList<Mensaje>();
		lps_ps                                                   = null;
		lrs_rs                                                   = null;

		try
		{
			StringBuilder lsb_consulta;

			lsb_consulta = new StringBuilder();

			lsb_consulta.append(cs_FIND_ALL);

			lps_ps     = getConnection().prepareStatement(lsb_consulta.toString());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lcm_cm.add(getObjetFromResultSet(lrs_rs));
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

		if(lcm_cm.isEmpty())
			lcm_cm = null;

		return lcm_cm;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param am_param Objeto contenedor de los filtros a usar en la consulta
	 * @return Mensaje resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Mensaje findById(Mensaje am_param)
	    throws B2BException
	{
		return (am_param != null) ? findById(am_param.getIdMensaje(), false, false) : null;
	}

	/**
	 * Busca un registro de la tabla por medio de su id_identificador.
	 *
	 * @param as_param Objeto contenedor de los filtros a usar en la consulta
	 * @param ab_identificadorEE Filtra la busqueda por el identificador EE
	 * @param ab_identificadorMensaje Filtra la busqueda por el identificador mensaje
	 * @return Mensaje resultante de la consulta
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Mensaje findById(String as_param, boolean ab_identificadorEE, boolean ab_identificadorMensaje)
	    throws B2BException
	{
		Mensaje lm_mensaje;

		lm_mensaje = null;

		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				if(ab_identificadorEE)
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_EE);
				else if(ab_identificadorMensaje)
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_IDENTIFICADOR_MENSAJE);
				else
					lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(1, as_param);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lm_mensaje = getObjetFromResultSet(lrs_rs);
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

		return lm_mensaje;
	}

	/**
	 * Find consultar mensajes.
	 *
	 * @param ad_fechaInicio correspondiente al valor de fecha inicio
	 * @param ad_fechaFin correspondiente al valor de fecha fin
	 * @param ab_isTurno correspondiente al valor de true o false
	 * @param as_identificador correspondiente al valor de identificador
	 * @param as_correoElectronico correspondiente al valor de correo electronico
	 * @param as_telefono correspondiente al valor de telefono
	 * @param ai_paginaConsultada correspondiente al valor de pagina consultada
	 * @param ai_numeroRegistrosPorPagina correspondiente al valor de numero registros por pagina
	 * @return el valor de list resultante
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public List findConsultarMensajes(
	    Date ad_fechaInicio, Date ad_fechaFin, boolean ab_isTurno, String as_identificador, String as_correoElectronico,
	    String as_telefono, int ai_paginaConsultada, int ai_numeroRegistrosPorPagina
	)
	    throws B2BException
	{
		List              lc_resultado;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lc_resultado     = new ArrayList();
		lps_ps           = null;
		lrs_rs           = null;

		if(
		    (ai_paginaConsultada > NumericUtils.DEFAULT_INT_VALUE)
			    && (ai_numeroRegistrosPorPagina > NumericUtils.DEFAULT_INT_VALUE)
		)
		{
			try
			{
				StringBuilder lsb_sb;
				int           li_count;
				int           li_indiceResulSet;
				BigInteger    li_totalTuplas;

				lsb_sb                = new StringBuilder(cs_FIND_MENSAJES_FILTER);
				li_indiceResulSet     = (ai_numeroRegistrosPorPagina * (ai_paginaConsultada - 1)) + 1;
				li_count              = 1;
				li_totalTuplas        = BigInteger.ZERO;

				lsb_sb.append(" WHERE MEN.FECHA_ENVIO IS NOT NULL");
				lsb_sb.append(
				    " AND MEN.FECHA_ENVIO BETWEEN TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') AND TO_DATE(?, 'DD/MM/YYYY HH24:MI:SS') "
				);
				lsb_sb.append(" AND EST.ID_ESTADO = '2'");    //TODO VALIDAR ESTADO DE MENSAJES CONSULTADOS
				lsb_sb.append(" AND MEN.ID_CANAL IN ('1','3') ");

				if(StringUtils.isValidString(as_identificador))
					lsb_sb.append(" AND MEN." + (ab_isTurno ? "ID_TURNO" : "NIR") + " = ? ");

				if(StringUtils.isValidString(as_correoElectronico) && StringUtils.isValidString(as_telefono))
					lsb_sb.append(" AND (UPPER(MEN.CORREO_ELECTRONICO) = UPPER(?) OR MEN.NUMERO_CELULAR = ?) ");

				lsb_sb.append(" ORDER BY MEN.FECHA_ENVIO ASC");

				lps_ps = getConnection()
						         .prepareStatement(
						    lsb_sb.toString(), ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY
						);
				lps_ps.setFetchSize(ai_numeroRegistrosPorPagina);

				lps_ps.setString(
				    li_count++,
				    StringUtils.getString(ad_fechaInicio, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);
				lps_ps.setString(
				    li_count++, StringUtils.getString(ad_fechaFin, FormatoFechaCommon.DIA_MES_ANIO_HORA_MINUTO_SEGUNDO)
				);

				if(StringUtils.isValidString(as_identificador))
					lps_ps.setString(li_count++, as_identificador);

				if(StringUtils.isValidString(as_correoElectronico) && StringUtils.isValidString(as_telefono))
				{
					lps_ps.setString(li_count++, as_correoElectronico);

					lps_ps.setString(li_count++, as_telefono);
				}

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.absolute(li_indiceResulSet))
				{
					Collection<Mensaje> lcm_registros;
					int                 li_cont;

					lcm_registros     = new ArrayList<Mensaje>();
					li_cont           = 1;

					do
					{
						lcm_registros.add(getObjetFromResultSet(lrs_rs));

						lc_resultado.add(lcm_registros);
						li_cont++;
					}
					while(lrs_rs.next() && (li_cont <= ai_numeroRegistrosPorPagina));
				}

				if(lrs_rs.last())
					li_totalTuplas = BigInteger.valueOf(NumericUtils.getLong(lrs_rs.getRow()));

				lc_resultado.add(0, li_totalTuplas);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findConsultarMensajes", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lc_resultado.isEmpty())
			lc_resultado = null;

		return lc_resultado;
	}

	/**
	 * Consulta en base de datos la cantidad de registros que coincidan con los criterios de búsqueda.
	 *
	 * @param ab_identificadorTurno Argumento de tipo <code>boolean</code> que determina si se debe consultar por ID_TURNO o NIR.
	 * @param as_identificador Argumento de tipo <code>String</code> que contiene el ID_TURNO o NIR a consultar.
	 * @return cantidad de registros que coincidan con los criterios de búsqueda.
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public int findCountNirOIdTurno(boolean ab_identificadorTurno, String as_identificador)
	    throws B2BException
	{
		int               li_contador;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_contador     = 0;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			if(StringUtils.isValidString(as_identificador))
			{
				StringBuilder lsb_consulta;

				lsb_consulta = new StringBuilder();

				lsb_consulta.append(cs_COUNT_MENSAJE);

				lsb_consulta.append(" WHERE ");
				lsb_consulta.append(ab_identificadorTurno ? " ID_TURNO = ? " : " NIR = ? ");

				lps_ps = getConnection().prepareStatement(lsb_consulta.toString());

				lps_ps.setString(1, as_identificador);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_contador = lrs_rs.getInt(1);
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "findCountNirOIdTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return li_contador;
	}

	/**
	 * Find mensajes filter.
	 *
	 * @param as_estado de as estado
	 * @param as_canal de as canal
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Mensaje> findMensajesFilter(String as_estado, String as_canal)
	    throws B2BException
	{
		return findMensajesFilter(as_estado, as_canal, false, null);
	}

	/**
	 * Find mensajes filter.
	 *
	 * @param as_estado de as estado
	 * @param as_canal de as canal
	 * @param ab_acuseRecibido de ab acuse recibido
	 * @param as_clasificacion de as clasificacion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Mensaje> findMensajesFilter(
	    String as_estado, String as_canal, boolean ab_acuseRecibido, String as_clasificacion
	)
	    throws B2BException
	{
		Collection<Mensaje> lcm_cm;

		lcm_cm = new ArrayList<Mensaje>();

		if(StringUtils.isValidString(as_estado) && StringUtils.isValidString(as_canal))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;

				lsb_sb = new StringBuilder(cs_FIND_MENSAJES_FILTER);

				if(!ab_acuseRecibido)
				{
					lsb_sb.append(" INNER JOIN SDB_PGN_REINTENTOS REI ON MEN.ID_CANAL = REI.ID_CANAL");
					lsb_sb.append(" WHERE MEN.FECHA_ENVIO IS NULL");
					lsb_sb.append(" AND NVL(MEN.INTENTO_ENVIO,0) <= REI.NUMERO_MAXIMO_INTENTOS");
				}
				else
				{
					lsb_sb.append(" INNER JOIN SDB_ACC_MENSAJE_ACUSE_DETALLE MAD ON MEN.ID_MENSAJE = MAD.ID_MENSAJE");
					lsb_sb.append(" WHERE MEN.FECHA_ENVIO IS NOT NULL");
				}

				lsb_sb.append(" AND EST.ID_ESTADO = ? AND MEN.ID_CANAL = ?");

				if(ab_acuseRecibido)
					lsb_sb.append(" AND MEN.CLASIFICACION = ?");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(1, as_estado);
				lps_ps.setString(2, as_canal);

				if(ab_acuseRecibido)
					lps_ps.setString(3, as_clasificacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcm_cm.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMensajesFilter", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcm_cm.isEmpty())
			lcm_cm = null;

		return lcm_cm;
	}

	/**
	 * Inserta un registro
	 * con la información suministrada.
	 *
	 * @param am_param objeto contenedor de la información a insertar
	 * @return el valor de mensaje
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Mensaje insert(Mensaje am_param)
	    throws B2BException
	{
		if(am_param != null)
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
				Connection lc_c;

				lc_c       = getConnection();
				lps_ps     = lc_c.prepareStatement(cs_INSERT);

				lps_secuencia     = lc_c.prepareStatement(cs_FIND_SECUENCIA);

				lrs_rs = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					String ls_nextValue;

					ls_nextValue = StringUtils.getString(lrs_rs.getInt(1));

					am_param.setIdMensaje(ls_nextValue);
					lps_ps.setString(li_column++, ls_nextValue);
				}
				else
					throw new B2BException(ErrorKeys.ERROR_GENERANDO_SECUENCIA);

				{
					lps_ps.setString(li_column++, am_param.getIdPlantilla());
					lps_ps.setString(li_column++, am_param.getIdOrigen());
					lps_ps.setString(li_column++, am_param.getIdCanal());
					lps_ps.setString(li_column++, am_param.getIdEstado());
					lps_ps.setString(li_column++, am_param.getClasificacion());
					lps_ps.setString(li_column++, am_param.getNir());
					lps_ps.setString(li_column++, am_param.getIdTurno());
					lps_ps.setString(li_column++, am_param.getIdCirculo());
					lps_ps.setString(li_column++, am_param.getIdentificadorEE());
					lps_ps.setString(li_column++, am_param.getCorreoElectronico());
					lps_ps.setString(li_column++, am_param.getNumeroCelular());
					lps_ps.setString(li_column++, am_param.getDireccionCorrespondencia());
					setDate(lps_ps, am_param.getFechaEnvio(), li_column++);
					lps_ps.setString(li_column++, am_param.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, am_param.getIpCreacion());
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
				close(lrs_rs);

				close(lps_ps);
				close(lps_secuencia);
			}
		}

		return am_param;
	}

	/**
	 * Actualiza un registro
	 * con la información suministrada.
	 *
	 * @param am_param objeto contenedor de la información a insertar
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(Mensaje am_param)
	    throws B2BException
	{
		if(am_param != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int           li_column;
				String        ls_identificadorMensaje;
				StringBuilder lsb_query;

				li_column                   = 1;
				lsb_query                   = new StringBuilder(cs_UPDATE);
				ls_identificadorMensaje     = am_param.getIdentificadorMensaje();

				if(StringUtils.isValidString(ls_identificadorMensaje))
					lsb_query.append(", IDENTIFICADOR_MENSAJE = ? ");

				lsb_query.append(" WHERE ID_MENSAJE=?");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, am_param.getIdPlantilla());
				lps_ps.setString(li_column++, am_param.getIdOrigen());
				lps_ps.setString(li_column++, am_param.getIdCanal());
				lps_ps.setString(li_column++, am_param.getIdEstado());
				lps_ps.setString(li_column++, am_param.getClasificacion());
				lps_ps.setString(li_column++, am_param.getNir());
				lps_ps.setString(li_column++, am_param.getIdTurno());
				lps_ps.setString(li_column++, am_param.getIdCirculo());
				lps_ps.setString(li_column++, am_param.getIdentificadorEE());
				lps_ps.setString(li_column++, am_param.getCorreoElectronico());
				lps_ps.setString(li_column++, am_param.getNumeroCelular());
				lps_ps.setString(li_column++, am_param.getDireccionCorrespondencia());
				lps_ps.setInt(li_column++, am_param.getIntentoEnvio());
				setDate(lps_ps, am_param.getFechaEnvio(), li_column++);
				lps_ps.setString(li_column++, am_param.getIdUsuarioModificacion());
				lps_ps.setString(li_column++, am_param.getIpModificacion());

				if(StringUtils.isValidString(ls_identificadorMensaje))
					lps_ps.setString(li_column++, ls_identificadorMensaje);

				lps_ps.setString(li_column++, am_param.getIdMensaje());

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
	private Mensaje getObjetFromResultSet(ResultSet lrs_rs)
	    throws SQLException
	{
		Mensaje lm_mensaje;

		lm_mensaje = new Mensaje();

		lm_mensaje.setIdMensaje(lrs_rs.getString("ID_MENSAJE"));
		lm_mensaje.setIdPlantilla(lrs_rs.getString("ID_PLANTILLA"));
		lm_mensaje.setIdOrigen(lrs_rs.getString("ID_ORIGEN"));
		lm_mensaje.setIdCanal(lrs_rs.getString("ID_CANAL"));
		lm_mensaje.setIdEstado(lrs_rs.getString("ID_ESTADO"));
		lm_mensaje.setClasificacion(lrs_rs.getString("CLASIFICACION"));
		lm_mensaje.setNir(lrs_rs.getString("NIR"));
		lm_mensaje.setIdTurno(lrs_rs.getString("ID_TURNO"));
		lm_mensaje.setIdCirculo(lrs_rs.getString("ID_CIRCULO"));
		lm_mensaje.setIdentificadorEE(lrs_rs.getString("IDENTIFICADOR_EE"));
		lm_mensaje.setCorreoElectronico(lrs_rs.getString("CORREO_ELECTRONICO"));
		lm_mensaje.setNumeroCelular(lrs_rs.getString("NUMERO_CELULAR"));
		lm_mensaje.setDireccionCorrespondencia(lrs_rs.getString("DIRECCION_CORRESPONDENCIA"));
		lm_mensaje.setIntentoEnvio(lrs_rs.getInt("INTENTO_ENVIO"));
		lm_mensaje.setFechaEnvio(lrs_rs.getTimestamp("FECHA_ENVIO"));
		lm_mensaje.setIdUsuarioCreacion(lrs_rs.getString("ID_USUARIO_CREACION"));
		lm_mensaje.setFechaCreacion(lrs_rs.getTimestamp("FECHA_CREACION"));
		lm_mensaje.setIpCreacion(lrs_rs.getString("IP_CREACION"));
		lm_mensaje.setIdUsuarioModificacion(lrs_rs.getString("ID_USUARIO_MODIFICACION"));
		lm_mensaje.setFechaModificacion(lrs_rs.getTimestamp("FECHA_MODIFICACION"));
		lm_mensaje.setIpModificacion(lrs_rs.getString("IP_MODIFICACION"));
		lm_mensaje.setIdentificadorMensaje(lrs_rs.getString("IDENTIFICADOR_MENSAJE"));

		return lm_mensaje;
	}
}
