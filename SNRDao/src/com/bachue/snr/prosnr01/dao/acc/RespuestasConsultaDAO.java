package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.registro.RespuestaConsulta;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_ACC_RESPUESTAS_CONSULTA.
 *
 * @author Julian Vaca
 */
public class RespuestasConsultaDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ID_SOLICITUD, CONSECUTIVO_CONSULTA, ID_PROCESO_CONSULTA, ID_TIPO_CRITERIO_BUSQUEDA, CONSECUTIVO_CONSULTA_DETALLE, CRITERIO_SELECCION, ID_CIRCULO, ID_MATRICULA, ID_DIRECCION, ID_OFICINA_ORIGEN, ID_TESTAMENTO, ID_PERSONA, ESTADO, RESPUESTA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_RESPUESTAS_CONSULTA WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ?";

	/** Constante cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO. */
	private static final String cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO = "SELECT ID_SOLICITUD, CONSECUTIVO_CONSULTA, ID_PROCESO_CONSULTA, ID_TIPO_CRITERIO_BUSQUEDA, CONSECUTIVO_CONSULTA_DETALLE, CRITERIO_SELECCION, ID_CIRCULO, ID_MATRICULA, ID_DIRECCION, ID_OFICINA_ORIGEN, ID_TESTAMENTO, ID_PERSONA, ESTADO, RESPUESTA, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION, ID_USUARIO_MODIFICACION, FECHA_MODIFICACION, IP_MODIFICACION FROM SDB_ACC_RESPUESTAS_CONSULTA WHERE ID_SOLICITUD = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ?";

	/** Constante cs_FIND_PANELS_BY_SOLICITUD. */
	private static final String cs_FIND_PANELS_BY_SOLICITUD = "SELECT DISTINCT TCB.DESCRIPCION, RC.ID_TIPO_CRITERIO_BUSQUEDA FROM SDB_ACC_RESPUESTAS_CONSULTA RC, SDB_PGN_TIPO_CRITERIO_BUSQUEDA TCB WHERE TCB.ID_TIPO_CRITERIO_BUSQUEDA = RC.ID_TIPO_CRITERIO_BUSQUEDA AND RC.ID_SOLICITUD = ? ORDER BY RC.ID_TIPO_CRITERIO_BUSQUEDA";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_RESPUESTAS_CONSULTA (ID_RESULTADO_CONSULTA,ID_SOLICITUD,CONSECUTIVO_CONSULTA,ID_PROCESO_CONSULTA,ID_TIPO_CRITERIO_BUSQUEDA,CONSECUTIVO_CONSULTA_DETALLE,CRITERIO_SELECCION,ID_CIRCULO,ID_MATRICULA,ID_DIRECCION,ID_OFICINA_ORIGEN,ID_TESTAMENTO,ID_PERSONA,ESTADO,RESPUESTA,ID_USUARIO_CREACION,FECHA_CREACION,IP_CREACION,ID_DEPARTAMENTO,ID_MUNICIPIO,DIRECCION,NUMERO_PREDIAL,NUPRE,NOMBRE_CIRCULO,NOMBRE_DEPARTAMENTO_CIRCULO,NOMBRE_MUNICIPIO_CIRCULO) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?,?,?,?,?,?,?,?)";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_RESPUESTAS_CONSULTA SET CRITERIO_SELECCION = ? ,ID_CIRCULO = ? ,ID_MATRICULA = ? ,ID_DIRECCION = ? ,ID_OFICINA_ORIGEN = ? ,ID_TESTAMENTO = ? ,ESTADO = ? ,RESPUESTA = ? ,ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP ,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ? AND CONSECUTIVO_CONSULTA = ? AND ID_PROCESO_CONSULTA = ? AND ID_TIPO_CRITERIO_BUSQUEDA = ? AND CONSECUTIVO_CONSULTA_DETALLE = ?";

	/** Constante cs_CONTEO_EXITOSO_BY_ID_SOLICITUD. */
	private static final String cs_CONTEO_EXITOSO_BY_ID_SOLICITUD = "SELECT SUM (CASE RESPUESTA WHEN 'EXITOSO' THEN 1 ELSE 0 END) AS CONTEO_EXISTE FROM SDB_ACC_RESPUESTAS_CONSULTA WHERE ID_SOLICITUD = ? ";

	/** Constante cs_UPDATE_CAMPO_ESTADO. */
	private static final String cs_UPDATE_CAMPO_ESTADO = "UPDATE SDB_ACC_RESPUESTAS_CONSULTA SET ESTADO = ? ,"
		+ " ID_USUARIO_MODIFICACION = ? ,FECHA_MODIFICACION = SYSTIMESTAMP ,IP_MODIFICACION = ? WHERE ID_SOLICITUD = ? "
		+ " AND RESPUESTA = ?";

	/** Constante cs_FIND_SECUENCIA. */
	private static final String cs_FIND_SECUENCIA = "SELECT SEC_ACC_RESPUESTAS_CONSULTA_ID_RESULTADO_CONSULTA.NEXTVAL FROM DUAL";

	/**
	 * Instancia un nuevo objeto respuestas consulta DAO.
	 */
	public RespuestasConsultaDAO()
	{
	}

	/**
	 * Metodo encargado de consultar el consecutivo consulta detalle máximo.
	 *
	 * @param as_idSolicitud de as id solicitud
	 * @return Variable de tipo long que contiene el consecutivo consulta detalle máximo.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long conteoExitosoById(String as_idSolicitud)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		long              ll_conteo;

		lps_ps        = null;
		lrs_rs        = null;
		ll_conteo     = 0L;

		if(StringUtils.isValidString(as_idSolicitud))
		{
			try
			{
				int li_column;

				li_column     = 1;

				lps_ps = getConnection().prepareStatement(cs_CONTEO_EXITOSO_BY_ID_SOLICITUD);

				lps_ps.setString(li_column++, as_idSolicitud);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					ll_conteo = lrs_rs.getLong("CONTEO_EXISTE");
			}
			catch(SQLException lse_e)
			{
				logError(this, "conteoExitosoById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return ll_conteo;
	}

	/**
	 * Método que consulta la tabla SDB_ACC_RESPUESTAS_CONSULTA por llave primaria ID_RESULTADO_CRITERIO_BUSQUEDA.
	 *
	 * @param arc_parametros Objeto de tipo RespuestaConsulta cargado con la información con la cúal se realizará la consulta a la BD
	 * @return Objeto de tipo RespuestaConsulta cargado con la información resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RespuestaConsulta
	 */
	public RespuestaConsulta findById(RespuestaConsulta arc_parametros)
	    throws B2BException
	{
		RespuestaConsulta lrc_data;
		lrc_data = null;

		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, arc_parametros.getIdSolicitud());
				setLong(lps_ps, arc_parametros.getConsecutivoConsulta(), li_contador++);
				lps_ps.setString(li_contador++, arc_parametros.getIdProcesoConsulta());
				setLong(lps_ps, arc_parametros.getConsecutivoConsultaDetalle(), li_contador++);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lrc_data = getRespuestaConsulta(lrs_rs);
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

		return lrc_data;
	}

	/**
	 * Método que consulta la tabla SDB_ACC_RESPUESTAS_CONSULTA por llave primaria ID_RESULTADO_CRITERIO_BUSQUEDA.
	 *
	 * @param arc_parametros Objeto de tipo RespuestaConsulta cargado con la información con la cúal se realizará la consulta a la BD
	 * @return Objeto de tipo RespuestaConsulta cargado con la información resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RespuestaConsulta
	 */
	public Collection<RespuestaConsulta> findByIdSolicitudTipoCriterio(RespuestaConsulta arc_parametros)
	    throws B2BException
	{
		Collection<RespuestaConsulta> lrc_data;
		lrc_data = null;

		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD_TIPO_CRITERIO);

				lps_ps.setString(li_contador++, arc_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, arc_parametros.getIdTipoCriterioBusqueda());

				lrs_rs     = lps_ps.executeQuery();

				lrc_data = new ArrayList<RespuestaConsulta>();

				while(lrs_rs.next())
					lrc_data.add(getRespuestaConsulta(lrs_rs));
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

		return lrc_data;
	}

	/**
	 * Método que consulta la tabla SDB_ACC_RESPUESTAS_CONSULTA por llave primaria ID_RESULTADO_CRITERIO_BUSQUEDA.
	 *
	 * @param as_solicitud correspondiente al valor del tipo de objeto String
	 * @return Objeto de tipo RespuestaConsulta cargado con la información resultado de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<String> findPanelsByIdSolicitud(String as_solicitud)
	    throws B2BException
	{
		Collection<String> lcs_data;
		lcs_data = null;

		if(StringUtils.isValidString(as_solicitud))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_FIND_PANELS_BY_SOLICITUD);

				lps_ps.setString(li_contador++, as_solicitud);

				lrs_rs     = lps_ps.executeQuery();

				lcs_data = new ArrayList<String>();

				while(lrs_rs.next())
					lcs_data.add(StringUtils.getString(lrs_rs.getString("DESCRIPCION")));
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

		return lcs_data;
	}

	/**
	 * Método encargado de insertar el registro en la tabla.
	 *
	 * @param arc_parametros de arc parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void insert(RespuestaConsulta arc_parametros)
	    throws B2BException
	{
		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lrs_rs            = null;

			try
			{
				Connection lc_connection;
				int        li_column;

				lc_connection     = getConnection();
				li_column         = 1;

				lps_ps = lc_connection.prepareStatement(cs_INSERT);

				{
					lps_secuencia     = lc_connection.prepareStatement(cs_FIND_SECUENCIA);

					lrs_rs = lps_secuencia.executeQuery();

					if(lrs_rs.next())
					{
						Object lo_o;

						lo_o = lrs_rs.getObject(1);

						if((lo_o != null) && lo_o instanceof BigDecimal)
							arc_parametros.setIdResultadoConsulta(((BigDecimal)lo_o).toString());

						lps_ps.setString(li_column++, arc_parametros.getIdResultadoConsulta());
					}
				}

				lps_ps.setString(li_column++, arc_parametros.getIdSolicitud());
				setLong(lps_ps, arc_parametros.getConsecutivoConsulta(), li_column++);
				lps_ps.setString(li_column++, arc_parametros.getIdProcesoConsulta());
				lps_ps.setString(li_column++, arc_parametros.getIdTipoCriterioBusqueda());
				setLong(lps_ps, arc_parametros.getConsecutivoConsultaDetalle(), li_column++);
				lps_ps.setString(li_column++, arc_parametros.getCriterioSeleccion());
				lps_ps.setString(li_column++, arc_parametros.getIdCirculo());
				setLong(lps_ps, arc_parametros.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, arc_parametros.getIdDireccion());
				lps_ps.setString(li_column++, arc_parametros.getIdOficinaOrigen());
				lps_ps.setString(li_column++, arc_parametros.getIdTestamento());
				lps_ps.setString(li_column++, arc_parametros.getIdPersona());
				lps_ps.setString(li_column++, arc_parametros.getEstado());
				lps_ps.setString(li_column++, arc_parametros.getRespuesta());
				lps_ps.setString(li_column++, arc_parametros.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, arc_parametros.getIpCreacion());
				lps_ps.setString(li_column++, arc_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, arc_parametros.getIdMunicipio());
				lps_ps.setString(li_column++, arc_parametros.getDireccion());
				lps_ps.setString(li_column++, arc_parametros.getNumeroPredial());
				lps_ps.setString(li_column++, arc_parametros.getNupre());
				lps_ps.setString(li_column++, arc_parametros.getNombreCirculo());
				lps_ps.setString(li_column++, arc_parametros.getNombreDepartamentoCirculo());
				lps_ps.setString(li_column++, arc_parametros.getNombreMunicipioCirculo());

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
	 * Método encargado de actualizar el registro en la tabla.
	 *
	 * @param arc_parametros de arc parametros
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public void update(RespuestaConsulta arc_parametros)
	    throws B2BException
	{
		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_UPDATE);

				lps_ps.setString(li_contador++, arc_parametros.getCriterioSeleccion());
				lps_ps.setString(li_contador++, arc_parametros.getIdCirculo());
				setLong(lps_ps, arc_parametros.getIdMatricula(), li_contador++);
				lps_ps.setString(li_contador++, arc_parametros.getIdDireccion());
				lps_ps.setString(li_contador++, arc_parametros.getIdOficinaOrigen());
				lps_ps.setString(li_contador++, arc_parametros.getIdTestamento());
				lps_ps.setString(li_contador++, arc_parametros.getEstado());
				lps_ps.setString(li_contador++, arc_parametros.getRespuesta());
				lps_ps.setString(li_contador++, arc_parametros.getIdSolicitud());
				setLong(lps_ps, arc_parametros.getConsecutivoConsulta(), li_contador++);
				lps_ps.setString(li_contador++, arc_parametros.getIdProcesoConsulta());
				lps_ps.setString(li_contador++, arc_parametros.getIdTipoCriterioBusqueda());
				setLong(lps_ps, arc_parametros.getConsecutivoConsultaDetalle(), li_contador++);
				lps_ps.setString(li_contador++, arc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_contador++, arc_parametros.getIpModificacion());
				lps_ps.setString(li_contador++, arc_parametros.getIdSolicitud());
				setLong(lps_ps, arc_parametros.getConsecutivoConsulta(), li_contador++);
				lps_ps.setString(li_contador++, arc_parametros.getIdProcesoConsulta());
				lps_ps.setString(li_contador++, arc_parametros.getIdTipoCriterioBusqueda());
				setLong(lps_ps, arc_parametros.getConsecutivoConsultaDetalle(), li_contador++);

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
	 * Metodo encargado de actualizar el campo ESTADO de la tabla SDB_ACC_RESPUESTAS_CONSULTA
	 * por ID_SOLICITUD y RESPUESTA anterior.
	 *
	 * @param arc_parametros Argumento de tipo RespuestaConsulta que contiene todos los criterios
	 * de actualización necesarios para realizar el update.
	 * @param as_respuestaAnterior Argumento de tipo String que contiene la respuesta anterior.
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateCampoEstado(RespuestaConsulta arc_parametros, String as_respuestaAnterior)
	    throws B2BException
	{
		if(arc_parametros != null)
		{
			PreparedStatement lps_ps;
			lps_ps = null;

			try
			{
				int li_contador;

				li_contador     = 1;
				lps_ps          = getConnection().prepareStatement(cs_UPDATE_CAMPO_ESTADO);

				lps_ps.setString(li_contador++, arc_parametros.getEstado());
				lps_ps.setString(li_contador++, arc_parametros.getIdUsuarioModificacion());
				lps_ps.setString(li_contador++, arc_parametros.getIpModificacion());
				lps_ps.setString(li_contador++, arc_parametros.getIdSolicitud());
				lps_ps.setString(li_contador++, as_respuestaAnterior);

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateCampoRespuesta", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Método que se encarga de llenar un objeto de tipo RespuestaConsulta con lo consultado y almacenado en un objeto ResultSet.
	 *
	 * @param ars_rs Argumento de tipo ResultSet que contiene los datos que serán almacenados en el objeto RespuestaConsulta
	 * @return Objeto de tipo RespuestaConsulta con lo consultado y almacenado en un objeto ResultSet.
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private RespuestaConsulta getRespuestaConsulta(ResultSet ars_rs)
	    throws SQLException
	{
		RespuestaConsulta lrc_datos;
		lrc_datos = null;

		if(ars_rs != null)
		{
			lrc_datos = new RespuestaConsulta();
			lrc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
			lrc_datos.setConsecutivoConsulta(getLong(ars_rs, "CONSECUTIVO_CONSULTA"));
			lrc_datos.setIdProcesoConsulta(ars_rs.getString("ID_PROCESO_CONSULTA"));
			lrc_datos.setIdTipoCriterioBusqueda(ars_rs.getString("ID_TIPO_CRITERIO_BUSQUEDA"));
			lrc_datos.setConsecutivoConsultaDetalle(getLong(ars_rs, "CONSECUTIVO_CONSULTA_DETALLE"));
			lrc_datos.setCriterioSeleccion(ars_rs.getString("CRITERIO_SELECCION"));
			lrc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
			lrc_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
			lrc_datos.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
			lrc_datos.setIdOficinaOrigen(ars_rs.getString("ID_OFICINA_ORIGEN"));
			lrc_datos.setIdTestamento(ars_rs.getString("ID_TESTAMENTO"));
			lrc_datos.setIdPersona(ars_rs.getString("ID_PERSONA"));
			lrc_datos.setEstado(ars_rs.getString("ESTADO"));
			lrc_datos.setRespuesta(ars_rs.getString("RESPUESTA"));
			lrc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
			lrc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
			lrc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
			lrc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
			lrc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
			lrc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));
		}

		return lrc_datos;
	}
}
