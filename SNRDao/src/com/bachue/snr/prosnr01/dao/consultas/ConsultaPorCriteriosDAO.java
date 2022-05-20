package com.bachue.snr.prosnr01.dao.consultas;

import com.b2bsg.common.exception.B2BException;

import com.bachue.snr.prosnr01.model.antiguoSistema.DataConsultaPorCriterio;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las consultas ára emcpmtrar dpcumentos por ID y datos de antiguo sistema por ID
 *
 * @author garias
 */
public class ConsultaPorCriteriosDAO extends com.b2bsg.common.dataAccess2.BaseDAO
{
	/** Constante CS_FIND_BY_ID_DOCUMENTO. */
	private static final String CS_FIND_BY_ID_DOCUMENTO = "SELECT AP.ID_CIRCULO, AP.ID_MATRICULA, AP.ID_ANOTACION, DP.DIRECCION_COMPLETA,DP.ID_TIPO_EJE_PRINCIPAL,DP.DATO_EJE_PRINCIPAL,DP.ID_LETRA_EJE_PRINCIPAL,"
		+ "DP.ID_COMPLEMENTO_EJE_PRINCIPAL,DP.ID_COORDENADA_EJE_PRINCIPAL,DP.ID_TIPO_EJE_SECUNDARIO,DP.DATO_EJE_SECUNDARIO,DP.ID_LETRA1_EJE_SECUNDARIO,DP.ID_COMPLEMENTO1_EJE_SECUNDARIO,DP.ID_COORDENADA1_EJE_SECUNDARIO,"
		+ "DP.DATO2_EJE_SECUNDARIO,DP.ID_LETRA2_EJE_SECUNDARIO,DP.ID_COMPLEMENTO2_EJE_SECUNDARIO,DP.ID_COORDENADA2_EJE_SECUNDARIO,DP.ID_COMPLEMENTO_DIRECCION,DP.COMPLEMENTO_DIRECCION, AP.FECHA_REGISTRO "
		+ "FROM SDB_BNG_ANOTACION_PREDIO AP INNER JOIN SDB_BNG_DIRECCION_PREDIO DP ON (DP.ID_MATRICULA = AP.ID_MATRICULA AND DP.ID_CIRCULO = AP.ID_CIRCULO AND DP.ID_DIRECCION = (SELECT MAX(ID_DIRECCION) "
		+ "FROM SDB_BNG_DIRECCION_PREDIO WHERE ID_CIRCULO = AP.ID_CIRCULO AND ID_MATRICULA = AP.ID_MATRICULA)) LEFT OUTER JOIN SDB_BNG_PREDIO_REGISTRO PR ON (PR.ID_DOCUMENTO = ? AND PR.PREDIO_DEFINITIVO = 'D' "
		+ "AND PR.ID_CIRCULO = AP.ID_CIRCULO AND PR.ID_MATRICULA = AP.ID_MATRICULA) WHERE AP.ID_CIRCULO = ? AND AP.ID_DOCUMENTO = ?";

	/** Constante CS_FIND_BY_ID_DATOS_ANT_SISTEMA. */
	private static final String CS_FIND_BY_ID_DATOS_ANT_SISTEMA = "SELECT AP.ID_CIRCULO, AP.ID_MATRICULA, AP.ID_ANOTACION, DP.DIRECCION_COMPLETA,DP.ID_TIPO_EJE_PRINCIPAL,DP.DATO_EJE_PRINCIPAL,DP.ID_LETRA_EJE_PRINCIPAL,"
		+ "DP.ID_COMPLEMENTO_EJE_PRINCIPAL,DP.ID_COORDENADA_EJE_PRINCIPAL,DP.ID_TIPO_EJE_SECUNDARIO,DP.DATO_EJE_SECUNDARIO,DP.ID_LETRA1_EJE_SECUNDARIO,DP.ID_COMPLEMENTO1_EJE_SECUNDARIO,DP.ID_COORDENADA1_EJE_SECUNDARIO,"
		+ "DP.DATO2_EJE_SECUNDARIO,DP.ID_LETRA2_EJE_SECUNDARIO,DP.ID_COMPLEMENTO2_EJE_SECUNDARIO,DP.ID_COORDENADA2_EJE_SECUNDARIO,DP.ID_COMPLEMENTO_DIRECCION,DP.COMPLEMENTO_DIRECCION, AP.FECHA_REGISTRO "
		+ "FROM SDB_BNG_ANOTACION_PREDIO AP INNER JOIN SDB_BNG_DIRECCION_PREDIO DP ON (DP.ID_MATRICULA = AP.ID_MATRICULA AND DP.ID_CIRCULO = AP.ID_CIRCULO AND DP.ID_DIRECCION = (SELECT MAX(ID_DIRECCION) "
		+ "FROM SDB_BNG_DIRECCION_PREDIO WHERE ID_CIRCULO = AP.ID_CIRCULO AND ID_MATRICULA = AP.ID_MATRICULA)) LEFT OUTER JOIN SDB_BNG_PREDIO_REGISTRO PR ON (PR.ID_DATOS_ANT_SISTEMA = ? AND PR.PREDIO_DEFINITIVO = 'D' "
		+ "AND PR.ID_CIRCULO = AP.ID_CIRCULO AND PR.ID_MATRICULA = AP.ID_MATRICULA) WHERE AP.ID_CIRCULO = ? AND AP.ID_DATOS_ANT_SISTEMA = ?";

	/**
	 * Retorna el valor del objeto de tipo Collection con DataConsultaPorCriterio consultado por ID de antiguo sistema
	 *
	 * @param idCirculo correspondiente al valor del tipo de objeto String
	 * @param ls_idDatosAntSistema correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de DataConsultaPorCriterio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DataConsultaPorCriterio
	 */
	public Collection<DataConsultaPorCriterio> findByDatosAntSistema(String idCirculo, String ls_idDatosAntSistema)
	    throws B2BException
	{
		Collection<DataConsultaPorCriterio> lc_return;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;
		int                                 count;

		lc_return     = new ArrayList<DataConsultaPorCriterio>();
		lps_ps        = null;
		lrs_rs        = null;
		count         = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(CS_FIND_BY_ID_DATOS_ANT_SISTEMA);

			lps_ps.setString(count++, ls_idDatosAntSistema);
			lps_ps.setString(count++, idCirculo);
			lps_ps.setString(count++, ls_idDatosAntSistema);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_return.add(getDataConsultaDatosDocumento(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lc_return;
	}

	/**
	 * Retorna el valor del objeto de tipo Collection de DataConsultaPorCriterio consultado por el ID del documento
	 *
	 * @param as_idCirculo correspondiente al valor del tipo de objeto String
	 * @param as_idDocumento correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor del objeto collection de DataConsultaPorCriterio
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DataConsultaPorCriterio
	 */
	public Collection<DataConsultaPorCriterio> findByDatosDocumento(String as_idCirculo, String as_idDocumento)
	    throws B2BException
	{
		Collection<DataConsultaPorCriterio> lc_return;
		PreparedStatement                   lps_ps;
		ResultSet                           lrs_rs;
		int                                 count;

		lc_return     = new ArrayList<DataConsultaPorCriterio>();
		lps_ps        = null;
		lrs_rs        = null;
		count         = 1;

		try
		{
			lps_ps = getConnection().prepareStatement(CS_FIND_BY_ID_DOCUMENTO);

			lps_ps.setString(count++, as_idDocumento);
			lps_ps.setString(count++, as_idCirculo);
			lps_ps.setString(count++, as_idDocumento);

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				lc_return.add(getDataConsultaDatosDocumento(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "getCustonQuery", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return lc_return;
	}

	/**
	 * Retorna el valor de una nueva instancia de DataConsultaPorCriterio
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de data consulta datos documento
	 * @throws SQLException Señala que se ha producido una excepción
	 * @see DataConsultaPorCriterio
	 */
	private DataConsultaPorCriterio getDataConsultaDatosDocumento(ResultSet ars_rs)
	    throws SQLException
	{
		DataConsultaPorCriterio ldcdc_consultaDatosDocumento;

		ldcdc_consultaDatosDocumento = new DataConsultaPorCriterio();

		ldcdc_consultaDatosDocumento.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldcdc_consultaDatosDocumento.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		ldcdc_consultaDatosDocumento.setIdAnotacion(ars_rs.getString("ID_ANOTACION"));
		ldcdc_consultaDatosDocumento.setIdTipoEjePrincipal(ars_rs.getString("ID_TIPO_EJE_PRINCIPAL"));
		ldcdc_consultaDatosDocumento.setDatoEjePrincipal(ars_rs.getString("DATO_EJE_PRINCIPAL"));
		ldcdc_consultaDatosDocumento.setIdLetraEjePrincipal(ars_rs.getString("ID_LETRA_EJE_PRINCIPAL"));
		ldcdc_consultaDatosDocumento.setIdComplementoEjePrincipal(ars_rs.getString("ID_COMPLEMENTO_EJE_PRINCIPAL"));
		ldcdc_consultaDatosDocumento.setIdCoordenadaEjePrincipal(ars_rs.getString("ID_COORDENADA_EJE_PRINCIPAL"));
		ldcdc_consultaDatosDocumento.setIdTipoEjeSecundario(ars_rs.getString("ID_TIPO_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setDatoEjeSecundario(ars_rs.getString("DATO_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdLetra1EjeSecundario(ars_rs.getString("ID_LETRA1_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdComplemento1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA1_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setDato2EjeSecundario(ars_rs.getString("DATO2_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdLetra2EjeSecundario(ars_rs.getString("ID_LETRA2_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdComplemento2EjeSecundario(ars_rs.getString("ID_COMPLEMENTO2_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdCoordenada2EjeSecundario(ars_rs.getString("ID_COORDENADA2_EJE_SECUNDARIO"));
		ldcdc_consultaDatosDocumento.setIdComplementoDireccion(ars_rs.getString("ID_COMPLEMENTO_DIRECCION"));
		ldcdc_consultaDatosDocumento.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));
		ldcdc_consultaDatosDocumento.setFechaRegistro(ars_rs.getDate("FECHA_REGISTRO"));

		return ldcdc_consultaDatosDocumento;
	}
}
