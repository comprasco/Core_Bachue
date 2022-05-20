package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.dataAccess2.BaseDAO;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.model.sdb.acc.SolicitudDireccionCertificado;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * Clase dedicada al manejo de conexion y transacciones en la base de datos que afecten
 * directamente a la tabla SDB_ACC_SOLICITUD_DIRECCION_CERTIFICADO.
 *
 * @author Manuel Blanco
 */
public class SolicitudDireccionCertificadoDAO extends BaseDAO
{
	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_SOLICITUD_DIRECCION_CERTIFICADO (ID_DIRECCION_PREDIO,ID_TURNO_HISTORIA,ID_CIRCULO,ID_MATRICULA,ID_TURNO,ID_TIPO_PREDIO,NOMBRE_PREDIO,ID_SOLICITUD,DIRECCION_COMPLETA,ID_TIPO_EJE_PRINCIPAL,DATO_EJE_PRINCIPAL,ID_LETRA_EJE_PRINCIPAL,ID_COMPLEMENTO_EJE_PRINCIPAL,ID_COORDENADA_EJE_PRINCIPAL,ID_TIPO_EJE_SECUNDARIO,DATO_EJE_SECUNDARIO,ID_LETRA1_EJE_SECUNDARIO,ID_COMPLEMENTO1_EJE_SECUNDARIO,ID_COORDENADA1_EJE_SECUNDARIO,DATO2_EJE_SECUNDARIO,ID_LETRA2_EJE_SECUNDARIO,ID_COMPLEMENTO2_EJE_SECUNDARIO,ID_COORDENADA2_EJE_SECUNDARIO,ID_COMPLEMENTO_DIRECCION,COMPLEMENTO_DIRECCION,ID_PAIS,ID_DEPARTAMENTO,ID_MUNICIPIO,ID_VEREDA,ACTIVO,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_SECUENCIA. */
	private static final String cs_SECUENCIA = "SELECT SEC_ACC_SOLICITUD_DIRECCION_CERTIFICADO_ID_DIRECCION_PREDIO.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_BY_ID_SOLICITUD. */
	private static final String cs_FIND_BY_ID_SOLICITUD = "SELECT * FROM SDB_ACC_SOLICITUD_DIRECCION_CERTIFICADO WHERE ID_SOLICITUD =?";

	/**
	 * Trae los datos de la tabla SBD_ACC_SOLICITUD_DIRECCION_CERTIFICADO.
	 *
	 * @param asdc_solicitudDireccion correspondiente al valor del tipo de objeto SolicitudDireccionCertificado
	 * @return Objeto SolicitudDireccionCertificado con la información de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see SolicitudDireccionCertificado
	 */
	public SolicitudDireccionCertificado findByIdSolicitud(SolicitudDireccionCertificado asdc_solicitudDireccion)
	    throws B2BException
	{
		SolicitudDireccionCertificado lca_data;
		PreparedStatement             lps_ps;
		ResultSet                     lrs_rs;

		lca_data     = new SolicitudDireccionCertificado();
		lps_ps       = null;
		lrs_rs       = null;

		try
		{
			int li_contador;

			li_contador     = 1;
			lps_ps          = getConnection().prepareStatement(cs_FIND_BY_ID_SOLICITUD);

			lps_ps.setString(li_contador++, asdc_solicitudDireccion.getIdSolicitud());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				lca_data = getObjectFromResultSet(lrs_rs);
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

		return lca_data;
	}

	/**
	 * Inserta un registro en la tabla.
	 *
	 * @param asdc_param Objeto contenedor de la información a almacenar en la tabla
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insert(SolicitudDireccionCertificado asdc_param)
	    throws B2BException
	{
		long ll_secuencia;

		ll_secuencia = 0;

		if(asdc_param != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_secuencia;
			Connection        lc_connection;
			ResultSet         lrs_rs;

			lps_ps            = null;
			lps_secuencia     = null;
			lc_connection     = getConnection();
			lrs_rs            = null;

			try
			{
				int li_column;

				li_column         = 1;
				lps_ps            = lc_connection.prepareStatement(cs_INSERT);
				lps_secuencia     = lc_connection.prepareStatement(cs_SECUENCIA);
				lrs_rs            = lps_secuencia.executeQuery();

				if(lrs_rs.next())
				{
					ll_secuencia = lrs_rs.getLong(1);

					lps_ps.setLong(li_column++, ll_secuencia);
				}

				setLong(lps_ps, asdc_param.getIdTurnoHistoria(), li_column++);
				lps_ps.setString(li_column++, asdc_param.getIdCirculo());
				setLong(lps_ps, asdc_param.getIdMatricula(), li_column++);
				lps_ps.setString(li_column++, asdc_param.getIdTurno());
				lps_ps.setString(li_column++, asdc_param.getIdTipoPredio());
				lps_ps.setString(li_column++, asdc_param.getNombrePredio());
				lps_ps.setString(li_column++, asdc_param.getIdSolicitud());
				lps_ps.setString(li_column++, asdc_param.getDireccion());
				lps_ps.setString(li_column++, asdc_param.getIdTipoEjePrincipal());
				lps_ps.setString(li_column++, asdc_param.getDatoEjePrincipal());
				lps_ps.setString(li_column++, asdc_param.getIdLetraEjePrincipal());
				lps_ps.setString(li_column++, asdc_param.getIdComplementoEjePrincipal());
				lps_ps.setString(li_column++, asdc_param.getIdCoordenadaEjePrincipal());
				lps_ps.setString(li_column++, asdc_param.getIdTipoEjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getDatoEjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdLetra1EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdComplemento1EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdCoordenada1EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getDato2EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdLetra2EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdComplemento2EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdCoordenada2EjeSecundario());
				lps_ps.setString(li_column++, asdc_param.getIdComplementoDireccion());
				lps_ps.setString(li_column++, asdc_param.getComplementoDireccion());
				lps_ps.setString(li_column++, asdc_param.getIdPais());
				lps_ps.setString(li_column++, asdc_param.getIdDepartamento());
				lps_ps.setString(li_column++, asdc_param.getIdMunicipio());
				lps_ps.setString(li_column++, asdc_param.getIdVereda());
				lps_ps.setString(li_column++, asdc_param.isActivo() ? EstadoCommon.S : EstadoCommon.N);
				lps_ps.setString(li_column++, asdc_param.getIdUsuarioCreacion());
				lps_ps.setString(li_column++, asdc_param.getIpCreacion());

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

		return ll_secuencia;
	}

	/**
	 * Retorna el valor de SolicitudDireccionCertificado
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de SolicitudDireccionCertificado
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private SolicitudDireccionCertificado getObjectFromResultSet(ResultSet ars_rs)
	    throws SQLException
	{
		SolicitudDireccionCertificado lsdc_datos;

		lsdc_datos = new SolicitudDireccionCertificado();

		lsdc_datos.setIdDireccionPredio(ars_rs.getString("ID_DIRECCION_PREDIO"));
		lsdc_datos.setIdSolicitud(ars_rs.getString("ID_SOLICITUD"));
		lsdc_datos.setIdTurnoHistoria(getLong(ars_rs, "ID_TURNO_HISTORIA"));
		lsdc_datos.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		lsdc_datos.setIdMatricula(getLong(ars_rs, "ID_MATRICULA"));
		lsdc_datos.setIdTurno(ars_rs.getString("ID_TURNO"));
		lsdc_datos.setTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		lsdc_datos.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		lsdc_datos.setNombrePredio(ars_rs.getString("NOMBRE_PREDIO"));
		lsdc_datos.setDireccion(ars_rs.getString("DIRECCION_COMPLETA"));
		lsdc_datos.setIdTipoEjePrincipal(ars_rs.getString("ID_TIPO_EJE_PRINCIPAL"));
		lsdc_datos.setDatoEjePrincipal(ars_rs.getString("DATO_EJE_PRINCIPAL"));
		lsdc_datos.setIdLetraEjePrincipal(ars_rs.getString("ID_LETRA_EJE_PRINCIPAL"));
		lsdc_datos.setIdComplementoEjePrincipal(ars_rs.getString("ID_COMPLEMENTO_EJE_PRINCIPAL"));
		lsdc_datos.setIdCoordenadaEjePrincipal(ars_rs.getString("ID_COORDENADA_EJE_PRINCIPAL"));
		lsdc_datos.setIdTipoEjeSecundario(ars_rs.getString("ID_TIPO_EJE_SECUNDARIO"));
		lsdc_datos.setDatoEjeSecundario(ars_rs.getString("DATO_EJE_SECUNDARIO"));
		lsdc_datos.setIdLetra1EjeSecundario(ars_rs.getString("ID_LETRA1_EJE_SECUNDARIO"));
		lsdc_datos.setIdComplemento1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		lsdc_datos.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA1_EJE_SECUNDARIO"));
		lsdc_datos.setDato2EjeSecundario(ars_rs.getString("DATO2_EJE_SECUNDARIO"));
		lsdc_datos.setIdLetra2EjeSecundario(ars_rs.getString("ID_LETRA2_EJE_SECUNDARIO"));
		lsdc_datos.setIdComplemento2EjeSecundario(ars_rs.getString("ID_COMPLEMENTO2_EJE_SECUNDARIO"));
		lsdc_datos.setIdCoordenada2EjeSecundario(ars_rs.getString("ID_COORDENADA2_EJE_SECUNDARIO"));
		lsdc_datos.setIdComplementoDireccion(ars_rs.getString("ID_COMPLEMENTO_DIRECCION"));
		lsdc_datos.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));
		lsdc_datos.setIdPais(ars_rs.getString("ID_PAIS"));
		lsdc_datos.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lsdc_datos.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lsdc_datos.setIdVereda(ars_rs.getString("ID_VEREDA"));

		{
			String ls_activo;

			ls_activo = ars_rs.getString("ACTIVO");

			if(StringUtils.isValidString(ls_activo))
				lsdc_datos.setActivo(ls_activo.equalsIgnoreCase(EstadoCommon.S));
		}

		lsdc_datos.setIdUsuarioCreacion(ars_rs.getString("ID_USUARIO_CREACION"));
		lsdc_datos.setFechaCreacion(ars_rs.getTimestamp("FECHA_CREACION"));
		lsdc_datos.setIpCreacion(ars_rs.getString("IP_CREACION"));
		lsdc_datos.setIdUsuarioModificacion(ars_rs.getString("ID_USUARIO_MODIFICACION"));
		lsdc_datos.setFechaModificacion(ars_rs.getTimestamp("FECHA_MODIFICACION"));
		lsdc_datos.setIpModificacion(ars_rs.getString("IP_MODIFICACION"));

		return lsdc_datos;
	}
}
