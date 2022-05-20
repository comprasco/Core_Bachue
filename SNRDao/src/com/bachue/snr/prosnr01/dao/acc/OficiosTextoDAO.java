package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.utils.ConversionTextos;

import com.bachue.snr.prosnr01.model.sdb.acc.OficiosTexto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_OFICIOS_TEXTO.
 *
 * @author garias
 */
public class OficiosTextoDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_OFICIO_TEXTO = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_SOLICITUD = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA_ORDER_BY_FECHA_CREACION. */
	private static final String cs_FIND_BY_TURNO_HISTORIA_ORDER_BY_FECHA_CREACION = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ? ORDER BY FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_TURNO_HISTORIA_TIPO. */
	private static final String cs_FIND_BY_TURNO_HISTORIA_TIPO = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ? AND TIPO = ?";

	/** Constante cs_FIND_BY_TURNO_HISTORIA_PLANTILLA. */
	private static final String cs_FIND_BY_TURNO_HISTORIA_PLANTILLA = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ? AND PLANTILLA = ?";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_OFICIOS_TEXTO_ID_OFICIO_TEXTO.NEXTVAL FROM DUAL";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_OFICIOS_TEXTO SET "
		+ " TIPO = ?,ARTICULO = ?,PERTINENCIA = ?,RAZON1 = ?,RAZON2 = ?,CONSIDERACION = ?,ID_TURNO_HISTORIA = ?, NUMERO_ANOTACIONES = ?,"
		+ " PLANTILLA = ?, ENCABEZADO = ?, ANTECEDENTES = ?, PRUEBAS_RECAUDADAS = ?, ANALISIS_PROBATORIO = ?, INTERVENCION_INTERESADOS = ?, "
		+ " DIAS_SUSPENSION = ?, DIAS_LETRAS = ?, RESUELVE = ?, ARGUMENTOS_RECURRENTE = ?, CONSIDERACION_SAJR = ?, FECHA_INICIO_TRASLADO = ?, FECHA_FIN_TRASLADO = ?, ID_SOLICITUD = ?,ID_PERSONA_NOTIFICAR = ?,ID_USUARIO_MODIFICACION = ?,FECHA_MODIFICACION = SYSTIMESTAMP,IP_MODIFICACION = ? WHERE ID_OFICIO_TEXTO = ?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_OFICIOS_TEXTO (ID_OFICIO_TEXTO,TIPO,"
		+ " ARTICULO,PERTINENCIA,RAZON1,RAZON2,CONSIDERACION,ID_TURNO_HISTORIA,NUMERO_ANOTACIONES,PLANTILLA,ENCABEZADO,ANTECEDENTES,"
		+ " PRUEBAS_RECAUDADAS, ANALISIS_PROBATORIO, INTERVENCION_INTERESADOS, DIAS_SUSPENSION, DIAS_LETRAS, RESUELVE, ARGUMENTOS_RECURRENTE, CONSIDERACION_SAJR, FECHA_INICIO_TRASLADO, FECHA_FIN_TRASLADO, ID_SOLICITUD, ID_PERSONA_NOTIFICAR, ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION) "
		+ " VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_BY_ID_TURNO_HISTORIA. */
	private static final String cs_DELETE_BY_ID_TURNO_HISTORIA = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ?";

	/** Constante cs_DELETE_BY_ID_TURNO_HISTORIA_PLANTILLA. */
	private static final String cs_DELETE_BY_ID_TURNO_HISTORIA_PLANTILLA = "SELECT * FROM SDB_ACC_OFICIOS_TEXTO WHERE ID_TURNO_HISTORIA = ? AND PLANTILLA = ?";

	/**
	 * Instancia un nuevo objeto oficios texto DAO.
	 */
	public OficiosTextoDAO()
	{
	}

	/**
	 * Borrar todos los oficios texto asociado a un id turno historia.
	 *
	 * @param al_idTurnoHistoria objeto contenedor del id turno historia a utilizar como filtro en la eliminación.
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public void deleteByTurnoHistoria(Long al_idTurnoHistoria)
	    throws B2BException
	{
		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID_TURNO_HISTORIA);

				setLong(lps_ps, al_idTurnoHistoria, 1);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Borrar todos los oficios texto asociado a un id turno historia y plantilla.
	 *
	 * @param al_idTurnoHistoria objeto contenedor del id turno historia a utilizar como filtro en la eliminación.
	 * @param as_idPlantilla de as id plantilla
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public void deleteByTurnoHistoriaPlantilla(Long al_idTurnoHistoria, String as_idPlantilla)
	    throws B2BException
	{
		if(NumericUtils.isValidLong(al_idTurnoHistoria) && StringUtils.isValidString(as_idPlantilla))
		{
			PreparedStatement lps_ps;

			lps_ps = null;

			try
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(cs_DELETE_BY_ID_TURNO_HISTORIA_PLANTILLA);

				setLong(lps_ps, al_idTurnoHistoria, li_column++);
				lps_ps.setString(li_column++, as_idPlantilla);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteByTurnoHistoriaPlantilla", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de OficiosTexto filtrado por ID.
	 *
	 * @param aot_parametros correspondiente al valor del tipo de objeto OficiosTexto
	 * @return devuelve el valor de OficiosTexto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public OficiosTexto findById(OficiosTexto aot_parametros)
	    throws B2BException
	{
		OficiosTexto      lpce_correo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpce_correo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aot_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setLong(li_contador++, aot_parametros.getIdOficioTexto());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
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

		return lpce_correo;
	}

	/**
	 * Find by id solicitud.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return el valor de oficios texto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficiosTexto findByIdSolicitud(String as_idSolicitud)
	    throws B2BException
	{
		OficiosTexto      lpce_correo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpce_correo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

				lps_ps.setString(li_contador++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdSolicitud", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Busca un oficio texto asociado a un id turno historia.
	 *
	 * @param aot_parametros objeto contenedor del id turno historia a utilizar como filtro en la busqueda
	 * @return Oficios texto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public OficiosTexto findByTurnoHistoria(OficiosTexto aot_parametros)
	    throws B2BException
	{
		return (aot_parametros != null) ? findByTurnoHistoria(aot_parametros.getIdTurnoHistoria()) : null;
	}

	/**
	 * Busca un oficio texto asociado a un id turno historia.
	 *
	 * @param al_idTurnoHistoria objeto contenedor del id turno historia a utilizar como filtro en la busqueda
	 * @return Oficios texto resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public OficiosTexto findByTurnoHistoria(Long al_idTurnoHistoria)
	    throws B2BException
	{
		OficiosTexto lpce_correo;

		lpce_correo = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA);

				setLong(lps_ps, al_idTurnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Find by turno historia order by fecha creacion.
	 *
	 * @param al_idTurnoHistoria correspondiente al valor de al id turno historia
	 * @return el valor de oficios texto
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public OficiosTexto findByTurnoHistoriaOrderByFechaCreacion(Long al_idTurnoHistoria)
	    throws B2BException
	{
		OficiosTexto lpce_correo;

		lpce_correo = null;

		if(NumericUtils.isValidLong(al_idTurnoHistoria))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA_ORDER_BY_FECHA_CREACION);

				setLong(lps_ps, al_idTurnoHistoria, li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Retorna el valor del objeto de OficiosTexto.
	 *
	 * @param aot_parametros correspondiente al valor del tipo de objeto OficiosTexto
	 * @return devuelve el valor de OficiosTexto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public OficiosTexto findByTurnoHistoriaPlantilla(OficiosTexto aot_parametros)
	    throws B2BException
	{
		OficiosTexto      lpce_correo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpce_correo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aot_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA_PLANTILLA);

				setLong(lps_ps, aot_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, aot_parametros.getPlantilla());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoriaPlantilla", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Retorna el valor del objeto de OficiosTexto.
	 *
	 * @param aot_parametros correspondiente al valor del tipo de objeto OficiosTexto
	 * @return devuelve el valor de OficiosTexto
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see OficiosTexto
	 */
	public OficiosTexto findByTurnoHistoriaTipo(OficiosTexto aot_parametros)
	    throws B2BException
	{
		OficiosTexto      lpce_correo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		lpce_correo     = null;
		lps_ps          = null;
		lrs_rs          = null;

		if(aot_parametros != null)
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_TURNO_HISTORIA_TIPO);

				setLong(lps_ps, aot_parametros.getIdTurnoHistoria(), li_contador++);
				lps_ps.setString(li_contador++, aot_parametros.getTipo());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getOficiosTexto(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByTurnoHistoria", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpce_correo;
	}

	/**
	 * Retorna el valor del objeto de long.
	 *
	 * @param aot_parametros correspondiente al valor del tipo de objeto OficiosTexto
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(OficiosTexto aot_parametros, boolean ab_query)
	    throws B2BException
	{
		long ll_secuencia;
		ll_secuencia = 0;

		if(aot_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;
			Connection        lc_connection;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;
			lc_connection     = getConnection();

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCE);

				if(ab_query)
				{
					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						ll_secuencia = lrs_rs.getLong(1);

						lps_ps.setLong(li_column++, ll_secuencia);
					}
				}

				lps_ps.setString(li_column++, aot_parametros.getTipo());
				lps_ps.setString(li_column++, aot_parametros.getArticulo());
				lps_ps.setString(li_column++, aot_parametros.getPertinencia());
				lps_ps.setString(li_column++, aot_parametros.getRazon1());
				lps_ps.setString(li_column++, aot_parametros.getRazon2());
				lps_ps.setString(li_column++, aot_parametros.getConsideracion());
				setLong(lps_ps, aot_parametros.getIdTurnoHistoria(), li_column++);
				setLong(lps_ps, aot_parametros.getNumeroAnotaciones(), li_column++);
				lps_ps.setString(li_column++, aot_parametros.getPlantilla());
				lps_ps.setString(li_column++, aot_parametros.getEncabezado());
				lps_ps.setString(li_column++, aot_parametros.getAntecedentes());
				lps_ps.setString(li_column++, aot_parametros.getPruebasRecaudadas());
				lps_ps.setString(li_column++, aot_parametros.getAnalisisProbatorio());
				lps_ps.setString(li_column++, aot_parametros.getIntervencionInteresados());
				setLong(lps_ps, aot_parametros.getDiasSuspension(), li_column++);
				lps_ps.setString(li_column++, aot_parametros.getDiasLetras());
				lps_ps.setString(li_column++, aot_parametros.getResuelve());
				lps_ps.setString(li_column++, aot_parametros.getArgumentosRecurrente());
				lps_ps.setString(li_column++, aot_parametros.getConsideracionSajr());
				setDate(lps_ps, aot_parametros.getFechaInicioTraslado(), li_column++);
				setDate(lps_ps, aot_parametros.getFechaFinTraslado(), li_column++);
				lps_ps.setString(li_column++, aot_parametros.getIdSolicitud());
				lps_ps.setString(li_column++, aot_parametros.getIdPersonaNotificar());

				if(ab_query)
				{
					lps_ps.setString(li_column++, aot_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, aot_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(li_column++, aot_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, aot_parametros.getIpModificacion());
				}

				if(!ab_query)
					lps_ps.setLong(li_column++, aot_parametros.getIdOficioTexto());

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
				close(lps_secuencia);
				close(lrs_rs);
			}
		}

		return ll_secuencia;
	}

	/**
	 * Retorna el valor de oficios texto.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de oficios texto
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException
	 */
	private OficiosTexto getOficiosTexto(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		OficiosTexto lot_oficiosTexto;
		String       ls_comillasApertura;
		String       ls_comillasCierre;
		String       ls_comillasDobles;
		String       ls_parentesis;
		String       ls_parentesisCorregido;

		lot_oficiosTexto           = new OficiosTexto();
		ls_comillasApertura        = "“";
		ls_comillasCierre          = "”";
		ls_comillasDobles          = "\"";
		ls_parentesis              = "(…)";
		ls_parentesisCorregido     = "(...)";

		lot_oficiosTexto.setIdOficioTexto(ars_rs.getLong("ID_OFICIO_TEXTO"));
		lot_oficiosTexto.setTipo(ars_rs.getString("TIPO"));

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("ARTICULO");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setArticulo(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("PERTINENCIA");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setPertinencia(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("RAZON1");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setRazon1(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("RAZON2");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setRazon2(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("CONSIDERACION");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setConsideracion(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("ENCABEZADO");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setEncabezado(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("ANTECEDENTES");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setAntecedentes(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("PRUEBAS_RECAUDADAS");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setPruebasRecaudadas(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("ANALISIS_PROBATORIO");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setAnalisisProbatorio(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("INTERVENCION_INTERESADOS");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setIntervencionInteresados(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("RESUELVE");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setResuelve(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("ARGUMENTOS_RECURRENTE");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setArgumentosRecurrente(ls_texto);
		}

		{
			String ls_texto;

			ls_texto     = ars_rs.getString("CONSIDERACION_SAJR");

			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasApertura, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_comillasCierre, ls_comillasDobles);
			ls_texto     = ConversionTextos.convertirTextos(ls_texto, ls_parentesis, ls_parentesisCorregido);

			lot_oficiosTexto.setConsideracionSajr(ls_texto);
		}

		lot_oficiosTexto.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lot_oficiosTexto.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lot_oficiosTexto.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lot_oficiosTexto.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lot_oficiosTexto.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lot_oficiosTexto.setNumeroAnotaciones(getLong(ars_rs, "NUMERO_ANOTACIONES"));
		lot_oficiosTexto.setDiasSuspension(getLong(ars_rs, "DIAS_SUSPENSION"));
		lot_oficiosTexto.setPlantilla(ars_rs.getString("PLANTILLA"));
		lot_oficiosTexto.setFechaInicioTraslado(ars_rs.getTimestamp("FECHA_INICIO_TRASLADO"));
		lot_oficiosTexto.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lot_oficiosTexto.setIdPersonaNotificar(ars_rs.getString("ID_PERSONA_NOTIFICAR"));

		return lot_oficiosTexto;
	}
}
