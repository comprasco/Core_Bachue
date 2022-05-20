package com.bachue.snr.prosnr01.dao.bng;

import co.gov.supernotariado.www.schemas.bachue.cb.consultadatosregistrales.consultardatosregistrales.v1.Titular;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.predio.Predio;
import com.bachue.snr.prosnr01.model.sdb.bng.Propietario;

import com.bachue.snr.prosnr10.model.catastro.PropietarioCatastro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase DAO que extrae todos los atributos y propiedades usados en la tabla SDB_BNG_PROPIETARIOS.
 *
 * @author  Carlos Calderón
 * Fecha de Creacion: 18/03/2020
 */
public class PropietarioDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ACTIVO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ACTIVO = "SELECT * FROM SDB_BNG_PROPIETARIOS WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ESTADO = 'A'";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION = "SELECT * FROM SDB_BNG_PROPIETARIOS WHERE ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_ANOTACION = ?";

	/** Constante cs_FIND_BY_PERSONA_ACTIVO. */
	private static final String cs_FIND_BY_PERSONA_ACTIVO = "SELECT BP.ID_CIRCULO,BP.ID_MATRICULA,BP.ID_PERSONA "
		+ "FROM SDB_BNG_PROPIETARIOS BP "
		+ "INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON (PR.ID_MATRICULA = BP.ID_MATRICULA AND PR.ID_CIRCULO = BP.ID_CIRCULO) "
		+ "INNER JOIN SDB_PGN_ZONA_REGISTRAL ZR ON (ZR.ID_ZONA_REGISTRAL = PR.ID_ZONA_REGISTRAL) "
		+ "WHERE BP.ID_PERSONA = ? AND BP.ESTADO = 'A' ";

	/** Constante cs_CONSULTA_CIUDADANOS. */
	private static final String cs_CONSULTA_CIUDADANOS = "SELECT TP.DESCRIPCION TIPO_PERSONA, AP.PRIMER_NOMBRE,AP.SEGUNDO_NOMBRE,AP.PRIMER_APELLIDO,AP.SEGUNDO_APELLIDO,AP.RAZON_SOCIAL,"
		+ "AP.PRIMER_NOMBRE || ' ' || NVL(AP.SEGUNDO_NOMBRE,'')  || ' ' || AP.PRIMER_APELLIDO  || ' ' || NVL(AP.SEGUNDO_APELLIDO,'')  NOMBRE_PERSONA, NG.DESCRIPCION GENERO, "
		+ "ACP.ROL_PERSONA ,DT.ID_DOCUMENTO_TIPO, DT.DESCRIPCION TIPO_DOCUMENTO_PERSONA,AP.NUMERO_DOCUMENTO,NVL(ACP.PROPIETARIO,' ') PROPIETARIO,"
		+ "CASE NVL(ACP.PORCENTAJE_PARTICIPACION,'VACIO') WHEN 'VACIO' THEN NULL ELSE ACP.PORCENTAJE_PARTICIPACION || ' %' END PORCENTAJE_PARTICIPACION,"
		+ "NVL(PI.DESCRIPCION,' ') PARTE_INTERESADA FROM  SDB_BNG_PROPIETARIOS BP "
		+ "INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON (PR.ID_MATRICULA = BP.ID_MATRICULA AND PR.ID_CIRCULO = BP.ID_CIRCULO) "
		+ "INNER JOIN SDB_BNG_ANOTACION_PREDIO_CIUDADANO ACP ON (ACP.ID_MATRICULA = PR.ID_MATRICULA AND ACP.ID_CIRCULO = PR.ID_CIRCULO AND ACP.ID_ANOTACION = BP.ID_ANOTACION) "
		+ "INNER JOIN SDB_ACC_PERSONA AP ON (AP.ID_PERSONA = ACP.ID_PERSONA) "
		+ "INNER JOIN SDB_PGN_TIPO_PERSONA TP ON (TP.ID_TIPO_PERSONA = AP.ID_TIPO_PERSONA ) "
		+ "INNER JOIN SDB_COL_INTERESADO_DOCUMENTO_TIPO DT ON (DT.ID_DOCUMENTO_TIPO = AP.ID_DOCUMENTO_TIPO) "
		+ "INNER JOIN SDB_COL_INTERESADO_NATURAL_GENERO NG ON (AP.ID_NATURAL_GENERO = NG.ID_NATURAL_GENERO) "
		+ "LEFT JOIN SDB_COL_PARTE_INTERESADA PI ON (PI.ID_PARTE_INTERESADA = ACP.ID_PARTE_INTERESADA) "
		+ "WHERE BP.ID_CIRCULO = ? AND BP.ID_MATRICULA = ? ";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_ACTIVO. */
	private static final String cs_FIND_DATA_BY_CIRCULO_MATRICULA_ACTIVO = "SELECT CIDT.DESCRIPCION TIPO_IDENTIFICACION, SAP.NUMERO_DOCUMENTO IDENTIFICACION, SAP.PRIMER_NOMBRE, SAP.SEGUNDO_NOMBRE, SAP.PRIMER_APELLIDO, SAP.SEGUNDO_APELLIDO, SBP.PORCENTAJE_PARTICIPACION FROM SDB_BNG_PROPIETARIOS SBP INNER JOIN SDB_ACC_PERSONA SAP ON SAP.ID_PERSONA = SBP.ID_PERSONA INNER JOIN SDB_COL_INTERESADO_DOCUMENTO_TIPO CIDT ON CIDT.ID_DOCUMENTO_TIPO = SAP.ID_DOCUMENTO_TIPO WHERE SBP.ID_CIRCULO = ? AND SBP.ID_MATRICULA = ? AND SBP.ESTADO = 'A'";

	/** Constante cs_FIND_BY_PERSONA. */
	private static final String cs_FIND_BY_PERSONA = "SELECT PR.NUPRE, AP.ORDEN, PROP.* FROM SDB_BNG_PROPIETARIOS PROP INNER JOIN SDB_BNG_PREDIO_REGISTRO PR ON PROP.ID_CIRCULO = PR.ID_CIRCULO AND PROP.ID_MATRICULA = PR.ID_MATRICULA INNER JOIN SDB_BNG_ANOTACION_PREDIO AP ON PROP.ID_CIRCULO = AP.ID_CIRCULO AND PROP.ID_MATRICULA = AP.ID_MATRICULA AND PROP.ID_ANOTACION = AP.ID_ANOTACION WHERE PROP.ID_PERSONA = ?";

	/**
	 * Find by id circulo matricula activo.
	 *
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>long</code> que contiene id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByIdCirculoMatriculaActivo(String as_idCirculo, long al_idMatricula)
	    throws B2BException
	{
		Collection<Propietario> lcp_cllPropietario;

		lcp_cllPropietario = new ArrayList<Propietario>();

		if(StringUtils.isValidString(as_idCirculo) && (al_idMatricula > NumericUtils.DEFAULT_LONG_VALUE))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ACTIVO);

				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPropietario.add(getPropietario(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_cllPropietario.isEmpty())
			lcp_cllPropietario = null;

		return lcp_cllPropietario;
	}

	/**
	 * Find by id circulo matricula activo.
	 *
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>long</code> que contiene id matricula
	 * @param al_idAnotacion Argumento de tipo <code>long</code> que contiene id anotacion
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByIdCirculoMatriculaAnotacion(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion
	)
	    throws B2BException
	{
		Collection<Propietario> lcp_cllPropietario;

		lcp_cllPropietario = new ArrayList<Propietario>();

		if(
		    StringUtils.isValidString(as_idCirculo) && (al_idMatricula > NumericUtils.DEFAULT_LONG_VALUE)
			    && (al_idAnotacion > NumericUtils.DEFAULT_LONG_VALUE)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_ANOTACION);

				lps_ps.setString(li_count++, as_idCirculo);
				lps_ps.setLong(li_count++, al_idMatricula);
				lps_ps.setLong(li_count++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPropietario.add(getPropietario(lrs_rs, false));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdCirculoMatricula", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_cllPropietario.isEmpty())
			lcp_cllPropietario = null;

		return lcp_cllPropietario;
	}

	/**
	 * Buscar por id persona activo.
	 *
	 * @param as_idPersona Argumento de tipo <code>String</code> correspondiente al valor de id persona
	 * @param ap_predio Argumento de tipo <code>Predio</code> correspondiente al valor de predio
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByIdPersonaActivo(String as_idPersona, Predio ap_predio)
	    throws B2BException
	{
		return findByIdPersonaCirculoMatriculaActivo(as_idPersona, null, null, ap_predio);
	}

	/**
	 * Buscar por id persona circulo matricula activo.
	 *
	 * @param as_idPersona Argumento de tipo <code>String</code> correspondiente al valor de id persona
	 * @param as_idCirculo Argumento de tipo <code>String</code> correspondiente al valor de id circulo
	 * @param al_idMatricula Argumento de tipo <code>Long</code> correspondiente al valor de id matricula
	 * @param ap_predio Argumento de tipo <code>Predio</code> correspondiente al valor de predio
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByIdPersonaCirculoMatriculaActivo(
	    String as_idPersona, String as_idCirculo, Long al_idMatricula, Predio ap_predio
	)
	    throws B2BException
	{
		Collection<Propietario> lcp_cllPropietario;

		lcp_cllPropietario = new ArrayList<Propietario>();

		if(StringUtils.isValidString(as_idPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_count;
				String        ls_idDepartamento;
				String        ls_idMunicipio;
				StringBuilder lsb_sb;

				li_count              = 1;
				ls_idDepartamento     = ap_predio.getCodDepartamento();
				ls_idMunicipio        = ap_predio.getCodMunicipio();
				lsb_sb                = new StringBuilder(cs_FIND_BY_PERSONA_ACTIVO);

				if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula, 1L))
					lsb_sb.append(" AND BP.ID_CIRCULO = ? AND BP.ID_MATRICULA = ? ");

				if(StringUtils.isValidString(ls_idDepartamento))
				{
					lsb_sb.append(" AND ZR.ID_DEPARTAMENTO = ? ");

					if(StringUtils.isValidString(ls_idMunicipio))
						lsb_sb.append(" AND ZR.ID_MUNICIPIO = ? ");
				}

				lsb_sb.append(" GROUP BY BP.ID_CIRCULO,BP.ID_MATRICULA,BP.ID_PERSONA");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_idPersona);

				if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula, 1L))
				{
					lps_ps.setString(li_count++, as_idCirculo);
					setLong(lps_ps, al_idMatricula, li_count++);
				}

				if(StringUtils.isValidString(ls_idDepartamento))
				{
					lps_ps.setString(li_count++, ls_idDepartamento);

					if(StringUtils.isValidString(ls_idMunicipio))
						lps_ps.setString(li_count++, ls_idMunicipio);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPropietario.add(getPropietario(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersonaActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_cllPropietario.isEmpty())
			lcp_cllPropietario = null;

		return lcp_cllPropietario;
	}

	/**
	 * Buscar por id persona.
	 *
	 * @param as_idPersona Argumento de tipo <code>String</code> que contiene id persona
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByPersona(String as_idPersona)
	    throws B2BException
	{
		return findByPersona(as_idPersona, null, null);
	}

	/**
	 * Buscar por id persona, id circulo y id matrícula.
	 *
	 * @param as_idPersona Argumento de tipo <code>String</code> que contiene id persona
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>Long</code> que contiene id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Propietario> findByPersona(String as_idPersona, String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<Propietario> lcp_cllPropietario;

		lcp_cllPropietario = new ArrayList<Propietario>();

		if(StringUtils.isValidString(as_idPersona))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_sb;
				int           li_count;

				lsb_sb       = new StringBuilder(cs_FIND_BY_PERSONA);
				li_count     = 1;

				if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula, -1L))
					lsb_sb.append(" AND PROP.ID_CIRCULO = ? AND PROP.ID_MATRICULA = ? ");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_count++, as_idPersona);

				if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula, -1L))
				{
					lps_ps.setString(li_count++, as_idCirculo);
					setLong(lps_ps, al_idMatricula, li_count++);
				}

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPropietario.add(getPropietario(lrs_rs, true));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_cllPropietario.isEmpty())
			lcp_cllPropietario = null;

		return lcp_cllPropietario;
	}

	/**
	 * Find data by id circulo matricula activo.
	 *
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>Long</code> que contiene id matricula
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<Titular> findDataByIdCirculoMatriculaActivo(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		Collection<Titular> lcp_cllPropietario;

		lcp_cllPropietario = new ArrayList<Titular>();

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int li_count;

				li_count     = 1;
				lps_ps       = getConnection().prepareStatement(cs_FIND_DATA_BY_CIRCULO_MATRICULA_ACTIVO);

				lps_ps.setString(li_count++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_count++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcp_cllPropietario.add(getTitular(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findDataByIdCirculoMatriculaActivo", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lcp_cllPropietario.isEmpty())
			lcp_cllPropietario = null;

		return lcp_cllPropietario;
	}

	/**
	 * Find propietarios anotacion predio.
	 *
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>long</code> que contiene id matricula
	 * @param al_idAnotacion Argumento de tipo <code>long</code> que contiene id anotacion
	 * @param ab_rolDe Argumento de tipo <code>boolean</code> que contiene validacion del rol true o false
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PropietarioCatastro> findPropietariosAnotacionPredio(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion, boolean ab_rolDe
	)
	    throws B2BException
	{
		return findPropietariosAnotacionPredio(as_idCirculo, al_idMatricula, al_idAnotacion, ab_rolDe, false);
	}

	/**
	 * Find propietarios anotacion predio.
	 *
	 * @param as_idCirculo Argumento de tipo <code>String</code> que contiene id circulo
	 * @param al_idMatricula Argumento de tipo <code>long</code> que contiene id matricula
	 * @param al_idAnotacion Argumento de tipo <code>long</code> que contiene id anotacion
	 * @param ab_rolDe Argumento de tipo <code>boolean</code> que contiene validacion del rol true o false
	 * @param ab_sinAnotacion Argumento de tipo <code>boolean</code> que contiene validacion de consulta de anotaciones anotacion true o false
	 * @return el valor de collection
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public Collection<PropietarioCatastro> findPropietariosAnotacionPredio(
	    String as_idCirculo, long al_idMatricula, long al_idAnotacion, boolean ab_rolDe, boolean ab_sinAnotacion
	)
	    throws B2BException
	{
		Collection<PropietarioCatastro> lca_data;
		PreparedStatement               lps_ps;
		ResultSet                       lrs_rs;
		lca_data     = new ArrayList<PropietarioCatastro>();
		lps_ps       = null;
		lrs_rs       = null;

		if(
		    StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0L)
			    && ((al_idAnotacion > 0L) || ab_sinAnotacion)
		)
		{
			try
			{
				int           li_column;
				StringBuilder lbs_query;

				li_column     = 1;
				lbs_query     = new StringBuilder(cs_CONSULTA_CIUDADANOS);

				if(!ab_sinAnotacion)
				{
					lbs_query.append(" AND BP.ID_ANOTACION = ? ");
					lbs_query.append("AND ACP.ROL_PERSONA = " + (ab_rolDe ? "'De'" : "'A'"));
				}
				else
					lbs_query.append(" AND BP.ESTADO = 'A' ");

				lps_ps = getConnection().prepareStatement(lbs_query.toString());

				lps_ps.setString(li_column++, as_idCirculo);
				lps_ps.setLong(li_column++, al_idMatricula);

				if(!ab_sinAnotacion)
					lps_ps.setLong(li_column++, al_idAnotacion);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lca_data.add(getPropietarioCatastro(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findPropietariosAnotacionPredio", lse_e);

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

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene resultado de la consulta realizada
	 * @param ab_datosAdicionales Argumento de tipo <code>boolean</code> que contiene datos adicionales
	 * @return el valor de propietario
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Propietario getPropietario(ResultSet ars_rs, boolean ab_datosAdicionales)
	    throws SQLException
	{
		Propietario lp_propietario;

		lp_propietario = new Propietario();

		lp_propietario.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lp_propietario.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lp_propietario.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lp_propietario.setIdAnotacion(ars_rs.getLong("ID_ANOTACION"));
		lp_propietario.setPorcentajeParticipacion(ars_rs.getLong("PORCENTAJE_PARTICIPACION"));
		lp_propietario.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lp_propietario.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));

		if(ab_datosAdicionales)
		{
			lp_propietario.setOrden(ars_rs.getLong("ORDEN"));
			lp_propietario.setNupre(ars_rs.getString("NUPRE"));
		}

		fillAuditoria(ars_rs, lp_propietario);

		return lp_propietario;
	}

	/**
	 * Retorna Objeto o variable de valor propietario.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene resultado de la consulta realizada
	 * @return el valor de propietario
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Propietario getPropietario(ResultSet ars_rs)
	    throws SQLException
	{
		Propietario lp_propietario;

		lp_propietario = new Propietario();

		lp_propietario.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lp_propietario.setIdMatricula(ars_rs.getLong("ID_MATRICULA"));
		lp_propietario.setIdPersona(ars_rs.getString("ID_PERSONA"));

		return lp_propietario;
	}

	/**
	 * Retorna Objeto o variable de valor propietario catastro.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene resultado de la consulta realizada
	 * @return el valor de propietario catastro
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private PropietarioCatastro getPropietarioCatastro(ResultSet ars_rs)
	    throws SQLException
	{
		PropietarioCatastro lpc_pc;

		lpc_pc = new PropietarioCatastro();

		lpc_pc.setTipoPersona(ars_rs.getString("TIPO_PERSONA"));
		lpc_pc.setIdDocumentoTipo(ars_rs.getString("ID_DOCUMENTO_TIPO"));
		lpc_pc.setTipoDocumentoPersona(ars_rs.getString("TIPO_DOCUMENTO_PERSONA"));
		lpc_pc.setNumDocumentoPersona(ars_rs.getString("NUMERO_DOCUMENTO"));
		lpc_pc.setPrimerNombre(ars_rs.getString("PRIMER_NOMBRE"));
		lpc_pc.setSegundoNombre(ars_rs.getString("SEGUNDO_NOMBRE"));
		lpc_pc.setPrimerApellido(ars_rs.getString("PRIMER_APELLIDO"));
		lpc_pc.setSegundoApellido(ars_rs.getString("SEGUNDO_APELLIDO"));
		lpc_pc.setRazonSocial(ars_rs.getString("RAZON_SOCIAL"));
		lpc_pc.setRolInterviniente(ars_rs.getString("ROL_PERSONA"));
		lpc_pc.setTipoParteInteresada(ars_rs.getString("PARTE_INTERESADA"));
		lpc_pc.setPorcentajeParticipacion(ars_rs.getString("PORCENTAJE_PARTICIPACION"));
		lpc_pc.setNombrePersona(ars_rs.getString("NOMBRE_PERSONA"));
		lpc_pc.setDescripcionGenero(ars_rs.getString("GENERO"));

		return lpc_pc;
	}

	/**
	 * Retorna Objeto o variable de valor titular.
	 *
	 * @param ars_rs Argumento de tipo <code>ResultSet</code> que contiene resultado de la consulta realizada
	 * @return el valor de titular
	 * @throws SQLException cuando se produce algun error en el proceso
	 */
	private Titular getTitular(ResultSet ars_rs)
	    throws SQLException
	{
		Titular lt_return;

		lt_return = new Titular();

		lt_return.setTipoIdentificacionTitular(ars_rs.getString("TIPO_IDENTIFICACION"));
		lt_return.setIdentificacionTitular(ars_rs.getString("IDENTIFICACION"));
		lt_return.setPrimerNombreTitular(ars_rs.getString("PRIMER_NOMBRE"));
		lt_return.setSegundoNombreTitular(ars_rs.getString("SEGUNDO_NOMBRE"));
		lt_return.setPrimerApellidoTitular(ars_rs.getString("PRIMER_APELLIDO"));
		lt_return.setSegundoApellidoTitular(ars_rs.getString("SEGUNDO_APELLIDO"));
		lt_return.setPorcentajeParticipacion(ars_rs.getString("PORCENTAJE_PARTICIPACION"));

		return lt_return;
	}
}
