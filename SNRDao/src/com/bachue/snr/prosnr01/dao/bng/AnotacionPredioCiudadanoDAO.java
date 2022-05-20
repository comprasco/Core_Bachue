package com.bachue.snr.prosnr01.dao.bng;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Interviniente;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.model.calificacion.RegistroCalificacion;
import com.bachue.snr.prosnr01.model.sdb.bng.AnotacionPredioCiudadano;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas de la tabla SDB_BNG_ANOTACION_PREDIO_CIUDADANO
 *
 * @author mblanco
 */
public class AnotacionPredioCiudadanoDAO extends BaseDAO
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? ORDER BY ID_ANOTACION ASC, ROL_PERSONA DESC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT * FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO "
		+ "WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ? ORDER BY ID_ANOTACION ASC, ROL_PERSONA DESC";

	/** Constante cs_CONSULTA_DETALLE_CIUDADANOS. */
	private static final String cs_CONSULTA_DETALLE_CIUDADANOS = " SELECT ACP.ID_PERSONA,AP.PRIMER_NOMBRE || ' ' || NVL(AP.SEGUNDO_NOMBRE,'')  || ' ' || AP.PRIMER_APELLIDO  || ' ' || NVL(AP.SEGUNDO_APELLIDO,'')  NOMBRE_PERSONA,NVL(AP.RAZON_SOCIAL,' ')RAZON_SOCIAL,"
		+ "  ACP.ROL_PERSONA ,AP.ID_DOCUMENTO_TIPO,AP.NUMERO_DOCUMENTO,NVL(ACP.PROPIETARIO,' ')PROPIETARIO,NVL(ACP.PORCENTAJE_PARTICIPACION,' ')PORCENTAJE_PARTICIPACION,NVL(PI.DESCRIPCION,' ')"
		+ "  ID_PARTE_INTERESADA  FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO ACP  INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = ACP.ID_PERSONA   LEFT JOIN SDB_COL_PARTE_INTERESADA PI ON PI.ID_PARTE_INTERESADA = ACP.ID_PARTE_INTERESADA"
		+ "  WHERE ACP.ID_CIRCULO = ?  AND ACP.ID_MATRICULA =  ? AND ACP.ID_ANOTACION = ?     ORDER BY ACP.ROL_PERSONA DESC";

	/** Constante cs_CONSULTA_DETALLE_CIUDADANOS_ENTREGA. */
	private static final String cs_CONSULTA_DETALLE_CIUDADANOS_ENTREGA = " SELECT DISTINCT ACP.ID_PERSONA , AP.PRIMER_NOMBRE || ' ' || NVL(AP.SEGUNDO_NOMBRE,'')  || ' ' || AP.PRIMER_APELLIDO"
		+ " || ' ' || NVL(AP.SEGUNDO_APELLIDO,'')  NOMBRE_PERSONA,NVL(AP.RAZON_SOCIAL,' ')RAZON_SOCIAL,AP.ID_DOCUMENTO_TIPO,AP.NUMERO_DOCUMENTO FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO ACP  INNER JOIN SDB_ACC_PERSONA AP ON AP.ID_PERSONA = ACP.ID_PERSONA"
		+ " LEFT JOIN SDB_COL_PARTE_INTERESADA PI ON PI.ID_PARTE_INTERESADA = ACP.ID_PARTE_INTERESADA WHERE ACP.ID_CIRCULO = ?  AND ACP.ID_MATRICULA =  ?  AND ACP.ID_ANOTACION = ?   ";

	/** Constante CS_CONSULTA_DATOS_REGISTRALES. */
	private static final String CS_CONSULTA_DATOS_REGISTRALES = "SELECT SCIDT.DESCRIPCION TIPO_IDENTIFICACION, SAP.NUMERO_DOCUMENTO IDENTIFICACION, NVL(SAP.PRIMER_NOMBRE,' ') PRIMER_NOMBRE, NVL(SAP.SEGUNDO_NOMBRE,' ') SEGUNDO_NOMBRE, NVL(SAP.PRIMER_APELLIDO,'') PRIMER_APELLIDO, NVL(SAP.SEGUNDO_APELLIDO,' ') SEGUNDO_APELLIDO, SBAPC.ROL_PERSONA ROL FROM SDB_BNG_ANOTACION_PREDIO_CIUDADANO SBAPC INNER JOIN SDB_ACC_PERSONA SAP ON SAP.ID_PERSONA = SBAPC.ID_PERSONA INNER JOIN SDB_COL_INTERESADO_DOCUMENTO_TIPO SCIDT ON SCIDT.ID_DOCUMENTO_TIPO = SAP.ID_DOCUMENTO_TIPO WHERE SBAPC.ID_CIRCULO = ? AND SBAPC.ID_MATRICULA = ? AND SBAPC.ID_ANOTACION = ?";

	/**
	 * Método encargado de consultar los intervinientes de anotación para el servicio CDR
	 *
	 * @param as_idCirculo variable que contiene el id del circulo
	 * @param al_idMatricula Variable que contiene el id de la matricula
	 * @param al_idAnotacion Variable que contiene el id de la anotacion
	 * @return Colección que contiene los datos consultados
	 * @throws B2BException
	 */
	public Collection<Interviniente> consultaDatosRegistrales(
	    String as_idCirculo, Long al_idMatricula, Long al_idAnotacion
	)
	    throws B2BException
	{
		Collection<Interviniente> lci_return;
		PreparedStatement         lps_ps;
		ResultSet                 lrs_rs;

		lci_return     = new ArrayList<Interviniente>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			if(
			    StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula)
				    && NumericUtils.isValidLong(al_idAnotacion)
			)
			{
				int li_column;

				li_column     = 1;
				lps_ps        = getConnection().prepareStatement(CS_CONSULTA_DATOS_REGISTRALES);

				lps_ps.setString(li_column++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_column++);
				setLong(lps_ps, al_idAnotacion, li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lci_return.add(getAnotacionDatosRegistrales(lrs_rs));
			}
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaDatosRegistrales", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		if(!CollectionUtils.isValidCollection(lci_return))
			lci_return = null;

		return lci_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Consulta predio ciudadanos.
	 *
	 * @param aap_param correspondiente al valor del tipo de objeto AnotacionPredioCiudadano
	 * @return devuelve el valor del objeto collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public Collection<AnotacionPredioCiudadano> consultaPredioCiudadanos(AnotacionPredioCiudadano aap_param)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lpr_Predio;
		PreparedStatement                    lps_ps;
		ResultSet                            lrs_rs;

		lpr_Predio     = new ArrayList<AnotacionPredioCiudadano>();
		lps_ps         = null;
		lrs_rs         = null;

		try
		{
			int li_contador;
			li_contador     = 1;

			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(li_contador++, aap_param.getIdCirculo());
			lps_ps.setLong(li_contador++, aap_param.getIdMatricula());
			lps_ps.setLong(li_contador++, aap_param.getIdAnotacion());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lpr_Predio.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "consultaPredioCiudadanos", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lpr_Predio;
	}

	/**
	 * Consulta todos lo registros asociados a una matrícula e id anotación.
	 *
	 * @param aapc_param Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionPredioCiudadano> findByCirculoMatricula(AnotacionPredioCiudadano aapc_param)
	    throws B2BException
	{
		return (aapc_param != null)
		? findByCirculoMatricula(aapc_param.getIdCirculo(), aapc_param.getIdMatricula(), aapc_param.getIdAnotacion())
		: null;
	}

	/**
	 * Consulta todos lo registros asociados a una matrícula e id anotación.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la busqueda
	 * @param al_idAnotacion id de la anotación asociada a la matrícula
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public Collection<AnotacionPredioCiudadano> findByCirculoMatricula(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion
	)
	    throws B2BException
	{
		Collection<AnotacionPredioCiudadano> lcapc_datos;

		lcapc_datos = new ArrayList<AnotacionPredioCiudadano>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L) && (al_idAnotacion > 0L))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA);

				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);
				lps_ps.setLong(li_contador++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcapc_datos.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcapc_datos))
			lcapc_datos = null;

		return lcapc_datos;
	}

	/**
	 * Find by id.
	 *
	 * @param aap_param de aap param
	 * @return el valor de anotacion predio ciudadano
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public AnotacionPredioCiudadano findById(AnotacionPredioCiudadano aap_param)
	    throws B2BException
	{
		if(aap_param != null)
			return findById(aap_param.getIdCirculo(), aap_param.getIdMatricula(), aap_param.getIdAnotacion());
		else

			return null;
	}

	/**
	 * Retorna el valor del objeto de tipo AnotacionPredioCiudadano filtrado por ID.
	 *
	 * @param as_idCirculo correspondiente al valor de id circulo
	 * @param al_idMatricula correspondiente al valor de id matricula
	 * @param al_idAnotacion correspondiente al valor de id anotacion
	 * @return devuelve el valor del objeto anotacion predio ciudadano
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	public AnotacionPredioCiudadano findById(String as_idCirculo, long al_idMatricula, long al_idAnotacion)
	    throws B2BException
	{
		AnotacionPredioCiudadano lpr_Predio;

		lpr_Predio = null;

		if(StringUtils.isValidString(as_idCirculo))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lpr_Predio     = null;
			lps_ps         = null;
			lrs_rs         = null;

			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

				lps_ps.setString(li_contador++, as_idCirculo);
				lps_ps.setLong(li_contador++, al_idMatricula);
				lps_ps.setLong(li_contador++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpr_Predio = getObjetFromResultSet(lrs_rs);
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

		return lpr_Predio;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de RegistroCalificacion filtrado por el detalle ciudadano
	 * por id circulo, id matricula, id anotacion
	 * @param aorc_data correspondiente al valor del tipo de objeto RegistroCalificacion
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor del objeto collection de RegistroCalificacion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	public Collection<RegistroCalificacion> findDataPredioAnotacion(RegistroCalificacion aorc_data, boolean ab_b)
	    throws B2BException
	{
		Collection<RegistroCalificacion> lca_data;
		PreparedStatement                lps_ps;
		ResultSet                        lrs_rs;
		int                              li_column;
		li_column     = 1;
		lca_data      = new ArrayList<RegistroCalificacion>();
		lps_ps        = null;
		lrs_rs        = null;

		if(aorc_data != null)
		{
			try
			{
				String ls_query;
				ls_query = cs_CONSULTA_DETALLE_CIUDADANOS;

				if(ab_b)
					ls_query = cs_CONSULTA_DETALLE_CIUDADANOS_ENTREGA;

				lps_ps = getConnection().prepareStatement(ls_query);

				lps_ps.setString(li_column++, aorc_data.getIdCirculo());
				setLong(lps_ps, aorc_data.getIdMatricula(), li_column++);
				setLong(lps_ps, aorc_data.getIdAnotacion(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getDataInfo(lrs_rs, ab_b));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataPredioAnotacion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lca_data.isEmpty())
			lca_data = null;

		return lca_data;
	}

	private Interviniente getAnotacionDatosRegistrales(ResultSet ars_rs)
	    throws SQLException
	{
		Interviniente li_interviniente;

		li_interviniente = new Interviniente();

		li_interviniente.setTipoIdentificacionInterviniente(ars_rs.getString("TIPO_IDENTIFICACION"));
		li_interviniente.setIdentificacionInterviniente(ars_rs.getString("IDENTIFICACION"));
		li_interviniente.setPrimerNombreInterviniente(ars_rs.getString("PRIMER_NOMBRE"));
		li_interviniente.setSegundoNombreInterviniente(ars_rs.getString("SEGUNDO_NOMBRE"));
		li_interviniente.setPrimerApellidoInterviniente(ars_rs.getString("PRIMER_APELLIDO"));
		li_interviniente.setSegundoApellidoInterviniente(ars_rs.getString("SEGUNDO_APELLIDO"));
		li_interviniente.setRol(ars_rs.getString("ROL"));

		return li_interviniente;
	}

	/**
	 * Retorna el valor de RegistroCalificacion
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @return el valor de RegistroCalificacion
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see RegistroCalificacion
	 */
	private RegistroCalificacion getDataInfo(ResultSet ars_rs, boolean ab_b)
	    throws SQLException
	{
		RegistroCalificacion lorc_datos;

		lorc_datos = new RegistroCalificacion();

		lorc_datos.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lorc_datos.setNombrePersona(ars_rs.getString("NOMBRE_PERSONA"));
		lorc_datos.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lorc_datos.setTipoDoc(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lorc_datos.setDocumento(ars_rs.getString("NUMERO_DOCUMENTO"));

		if(!ab_b)
		{
			lorc_datos.setValuePropietario(ars_rs.getString("PROPIETARIO"));
			lorc_datos.setRolPersona(ars_rs.getString("ROL_PERSONA"));
			lorc_datos.setPorcentajeStr(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
			lorc_datos.setInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
		}

		return lorc_datos;
	}

	/**
	 * Retorna el valor de AnotacionPredioCiudadano
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de AnotacionPredioCiudadano
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see AnotacionPredioCiudadano
	 */
	private AnotacionPredioCiudadano getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		AnotacionPredioCiudadano lapc_apc;

		lapc_apc = new AnotacionPredioCiudadano();

		lapc_apc.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lapc_apc.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lapc_apc.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lapc_apc.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lapc_apc.setRolPersona(ars_rs.getString("ROL_PERSONA"));
		lapc_apc.setPropietario(ars_rs.getString("PROPIETARIO"));
		lapc_apc.setPorcentajeParticipacion(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
		lapc_apc.setIdInteresadaRrr(ars_rs.getString("ID_PARTE_INTERESADA"));
		lapc_apc.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lapc_apc.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));

		return lapc_apc;
	}
}
