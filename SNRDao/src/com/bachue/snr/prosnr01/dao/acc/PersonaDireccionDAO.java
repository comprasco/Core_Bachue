package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.DateUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.common.constants.EstadoCommon;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.PersonaDireccion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades PersonaDireccionDAO.
 *
 * @author  garias
 * Fecha de Creacion: 26/08/2020
 */
public class PersonaDireccionDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT APD.ID_PERSONA,APD.ID_DIRECCION,APD.TIPO_DIRECCION,APD.ID_PAIS,APD.ID_DEPARTAMENTO,APD.ID_MUNICIPIO,APD.ID_TIPO_PREDIO,APD.DIRECCION_COMPLETA,APD.ID_TIPO_EJE_PRINCIPAL,APD.DATO_EJE_PRINCIPAL,APD.ID_LETRA_EJE_PRINCIPAL,APD.ID_COMPLEMENTO_EJE_PRINCIPAL,APD.ID_COORDENADA_EJE_PRINCIPAL,APD.ID_TIPO_EJE_SECUNDARIO,APD.DATO_EJE_SECUNDARIO,APD.ID_LETRA1_EJE_SECUNDARIO,APD.ID_COMPLEMENTO1_EJE_SECUNDARIO,APD.ID_COORDENADA1_EJE_SECUNDARIO,APD.DATO2_EJE_SECUNDARIO,APD.ID_LETRA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO2_EJE_SECUNDARIO,APD.ID_COORDENADA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO_DIRECCION,APD.COMPLEMENTO_DIRECCION,APD.FECHA_DESDE,APD.FECHA_HASTA,APD.ACTIVO,APD.FECHA_CREACION,APD.ID_USUARIO_CREACION,APD.IP_CREACION,APD.ID_USUARIO_MODIFICACION,APD.FECHA_MODIFICACION,APD.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_PERSONA_DIRECCION APD LEFT JOIN SDB_PNG_TIPO_EJE PTE ON APD.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON APD.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON APD.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON APD.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON APD.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON APD.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON APD.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON APD.ID_COORDENADA1_EJE_SECUNDARIO = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON APD.ID_COORDENADA2_EJE_SECUNDARIO = PC.ID_COORDENADA WHERE APD.ID_PERSONA = ? AND APD.ID_DIRECCION = ?";

	/** Constante cs_FIND_BY_ID_PERSONA. */
	private static final String cs_FIND_BY_ID_PERSONA = "SELECT APD.ID_PERSONA,APD.ID_DIRECCION,APD.TIPO_DIRECCION,APD.ID_PAIS,APD.ID_DEPARTAMENTO,APD.ID_MUNICIPIO,APD.ID_TIPO_PREDIO,APD.DIRECCION_COMPLETA,APD.ID_TIPO_EJE_PRINCIPAL,APD.DATO_EJE_PRINCIPAL,APD.ID_LETRA_EJE_PRINCIPAL,APD.ID_COMPLEMENTO_EJE_PRINCIPAL,APD.ID_COORDENADA_EJE_PRINCIPAL,APD.ID_TIPO_EJE_SECUNDARIO,APD.DATO_EJE_SECUNDARIO,APD.ID_LETRA1_EJE_SECUNDARIO,APD.ID_COMPLEMENTO1_EJE_SECUNDARIO,APD.ID_COORDENADA1_EJE_SECUNDARIO,APD.DATO2_EJE_SECUNDARIO,APD.ID_LETRA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO2_EJE_SECUNDARIO,APD.ID_COORDENADA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO_DIRECCION,APD.COMPLEMENTO_DIRECCION,APD.FECHA_DESDE,APD.FECHA_HASTA,APD.ACTIVO,APD.FECHA_CREACION,APD.ID_USUARIO_CREACION,APD.IP_CREACION,APD.ID_USUARIO_MODIFICACION,APD.FECHA_MODIFICACION,APD.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_PERSONA_DIRECCION APD LEFT JOIN SDB_PNG_TIPO_EJE PTE ON APD.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON APD.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON APD.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON APD.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON APD.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON APD.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON APD.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON APD.ID_COORDENADA1_EJE_SECUNDARIO = PC1.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON APD.ID_COORDENADA2_EJE_SECUNDARIO = PC2.ID_COORDENADA WHERE APD.ID_PERSONA = ? AND APD.ACTIVO = 'S' ";

	/** Constante cs_FIND_BY_PERSONA_DOCUMENTO. */
	private static final String cs_FIND_BY_PERSONA_DOCUMENTO = "SELECT APD.ID_PERSONA,APD.ID_DIRECCION,APD.TIPO_DIRECCION,APD.ID_PAIS,APD.ID_DEPARTAMENTO,APD.ID_MUNICIPIO,APD.ID_TIPO_PREDIO,APD.DIRECCION_COMPLETA,APD.ID_TIPO_EJE_PRINCIPAL,APD.DATO_EJE_PRINCIPAL,APD.ID_LETRA_EJE_PRINCIPAL,APD.ID_COMPLEMENTO_EJE_PRINCIPAL,APD.ID_COORDENADA_EJE_PRINCIPAL,APD.ID_TIPO_EJE_SECUNDARIO,APD.DATO_EJE_SECUNDARIO,APD.ID_LETRA1_EJE_SECUNDARIO,APD.ID_COMPLEMENTO1_EJE_SECUNDARIO,APD.ID_COORDENADA1_EJE_SECUNDARIO,APD.DATO2_EJE_SECUNDARIO,APD.ID_LETRA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO2_EJE_SECUNDARIO,APD.ID_COORDENADA2_EJE_SECUNDARIO,APD.ID_COMPLEMENTO_DIRECCION,APD.COMPLEMENTO_DIRECCION,APD.FECHA_DESDE,APD.FECHA_HASTA,APD.ACTIVO,APD.FECHA_CREACION,APD.ID_USUARIO_CREACION,APD.IP_CREACION,APD.ID_USUARIO_MODIFICACION,APD.FECHA_MODIFICACION,APD.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_PERSONA_DIRECCION APD LEFT JOIN SDB_PNG_TIPO_EJE PTE ON APD.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON APD.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON APD.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON APD.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON APD.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON APD.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON APD.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON APD.ID_COORDENADA1_EJE_SECUNDARIO = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON APD.ID_COORDENADA2_EJE_SECUNDARIO = PC.ID_COORDENADA WHERE APD.ID_PERSONA = ? AND APD.TIPO_DIRECCION = ? ORDER BY APD.ID_DIRECCION DESC";

	/** Constante cs_FIND_MAX_ID_DIRECCION. */
	private static final String cs_FIND_MAX_ID_DIRECCION = "SELECT MAX(TO_NUMBER(ID_DIRECCION)) FROM SDB_ACC_PERSONA_DIRECCION WHERE ID_PERSONA = ?";

	/** Constante cs_UPDATE. */
	private static final String cs_UPDATE = "UPDATE SDB_ACC_PERSONA_DIRECCION SET TIPO_DIRECCION=?,ID_PAIS=?,ID_DEPARTAMENTO=?,ID_MUNICIPIO=?,ID_TIPO_PREDIO=?,DIRECCION_COMPLETA=?,ID_TIPO_EJE_PRINCIPAL=?,DATO_EJE_PRINCIPAL=?,ID_LETRA_EJE_PRINCIPAL=?,ID_COMPLEMENTO_EJE_PRINCIPAL=?,ID_COORDENADA_EJE_PRINCIPAL=?,ID_TIPO_EJE_SECUNDARIO=?,DATO_EJE_SECUNDARIO=?,ID_LETRA1_EJE_SECUNDARIO=?,ID_COMPLEMENTO1_EJE_SECUNDARIO=?,ID_COORDENADA1_EJE_SECUNDARIO=?,DATO2_EJE_SECUNDARIO=?,ID_LETRA2_EJE_SECUNDARIO=?,ID_COMPLEMENTO2_EJE_SECUNDARIO=?,ID_COORDENADA2_EJE_SECUNDARIO=?,ID_COMPLEMENTO_DIRECCION=?,COMPLEMENTO_DIRECCION=?,FECHA_DESDE=?,FECHA_HASTA=?,ACTIVO=?,ID_USUARIO_MODIFICACION=?,FECHA_MODIFICACION=SYSTIMESTAMP,IP_MODIFICACION=? WHERE ID_PERSONA=? AND ID_DIRECCION=?";

	/** Constante cs_INSERT. */
	private static final String cs_INSERT = "INSERT INTO SDB_ACC_PERSONA_DIRECCION (ID_PERSONA,ID_DIRECCION,TIPO_DIRECCION,ID_PAIS,ID_DEPARTAMENTO,ID_MUNICIPIO,ID_TIPO_PREDIO,DIRECCION_COMPLETA,ID_TIPO_EJE_PRINCIPAL,DATO_EJE_PRINCIPAL,ID_LETRA_EJE_PRINCIPAL,ID_COMPLEMENTO_EJE_PRINCIPAL,ID_COORDENADA_EJE_PRINCIPAL,ID_TIPO_EJE_SECUNDARIO,DATO_EJE_SECUNDARIO,ID_LETRA1_EJE_SECUNDARIO,ID_COMPLEMENTO1_EJE_SECUNDARIO,ID_COORDENADA1_EJE_SECUNDARIO,DATO2_EJE_SECUNDARIO,ID_LETRA2_EJE_SECUNDARIO,ID_COMPLEMENTO2_EJE_SECUNDARIO,ID_COORDENADA2_EJE_SECUNDARIO,ID_COMPLEMENTO_DIRECCION,COMPLEMENTO_DIRECCION,FECHA_DESDE,FECHA_HASTA,ACTIVO,FECHA_CREACION,ID_USUARIO_CREACION,IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?,?)";

	/** Constante cs_FIND_FIRST_BY_DATOS_DIRECCION_JSON. */
	private static final String cs_FIND_FIRST_BY_DATOS_DIRECCION_JSON = "SELECT * FROM SDB_ACC_PERSONA_DIRECCION WHERE ID_PERSONA = ? AND ID_PAIS = ? AND ID_DEPARTAMENTO = ? AND ID_MUNICIPIO = ? AND TIPO_DIRECCION = ? AND ID_TIPO_PREDIO = ? AND DATO_EJE_PRINCIPAL = ?";

	/**
	 * Busca un persona dirección asociado a un id específico.
	 *
	 * @param apd_parametros Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Persona dirección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaDireccion
	 */
	public PersonaDireccion findById(PersonaDireccion apd_parametros)
	    throws B2BException
	{
		return (apd_parametros != null) ? findById(apd_parametros.getIdPersona(), apd_parametros.getIdDireccion()) : null;
	}

	/**
	 * Busca un persona dirección asociado a un id específico.
	 *
	 * @param as_idPersona id de la persona asociado a la dirección
	 * @param as_idDireccion id de la dirección
	 * @return Persona dirección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaDireccion
	 */
	public PersonaDireccion findById(String as_idPersona, String as_idDireccion)
	    throws B2BException
	{
		PersonaDireccion lpce_correo;

		lpce_correo = null;

		if(StringUtils.isValidString(as_idPersona) && StringUtils.isValidString(as_idDireccion))
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

				lps_ps.setString(li_contador++, as_idPersona);
				lps_ps.setString(li_contador++, as_idDireccion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpce_correo = getPersonaDireccion(lrs_rs);
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
	 * Retorna el valor del objeto de Collection de PersonaDireccion.
	 *
	 * @param as_idPersona correspondiente al valor del tipo de objeto String
	 * @param ab_residencia correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de Collection de PersonaDireccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaDireccion
	 */
	public Collection<PersonaDireccion> findByIdPersona(String as_idPersona, boolean ab_residencia)
	    throws B2BException
	{
		Collection<PersonaDireccion> lpce_datos;
		PreparedStatement            lps_ps;
		ResultSet                    lrs_rs;

		lpce_datos     = new ArrayList<PersonaDireccion>();
		lps_ps         = null;
		lrs_rs         = null;

		if(StringUtils.isValidString(as_idPersona))
		{
			try
			{
				int           li_contador;
				StringBuilder lsb_consulta;
				String        ls_order;

				li_contador      = 1;
				lsb_consulta     = new StringBuilder();
				ls_order         = " ORDER BY DIRECCION_COMPLETA ";

				lsb_consulta.append(cs_FIND_BY_ID_PERSONA);

				if(ab_residencia)
				{
					String ls_residencia;
					ls_residencia = " AND TIPO_DIRECCION = 'R' ";

					lsb_consulta.append(ls_residencia);
				}
				else
				{
					String ls_correspondencia;
					ls_correspondencia = " AND TIPO_DIRECCION = 'C' ";

					lsb_consulta.append(ls_correspondencia);
				}

				lsb_consulta.append(ls_order);

				lps_ps = getConnection().prepareStatement(lsb_consulta.toString());

				lps_ps.setString(li_contador++, as_idPersona);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lpce_datos.add(getPersonaDireccion(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByIdPersona", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(lpce_datos.isEmpty())
			lpce_datos = null;

		return lpce_datos;
	}

	/**
	 * Retorna el valor del objeto de PersonaDireccion.
	 *
	 * @param apd_pd correspondiente al valor del tipo de objeto PersonaDireccion
	 * @param as_tipoDir correspondiente al valor del tipo de objeto String
	 * @return devuelve el valor de PersonaDireccion
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see PersonaDireccion
	 */
	public PersonaDireccion findDireccionByIdPersona(PersonaDireccion apd_pd, String as_tipoDir)
	    throws B2BException
	{
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;
		PersonaDireccion  lpd_datos;

		lps_ps        = null;
		lrs_rs        = null;
		lpd_datos     = null;

		if((apd_pd != null) && StringUtils.isValidString(as_tipoDir))
		{
			try
			{
				int li_contador;
				li_contador     = 1;

				lps_ps = getConnection().prepareStatement(cs_FIND_BY_PERSONA_DOCUMENTO);

				lps_ps.setString(li_contador++, apd_pd.getIdPersona());
				lps_ps.setString(li_contador++, as_tipoDir);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpd_datos = getPersonaDireccion(lrs_rs);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findIdCorreoElectronico", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpd_datos;
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @param apd_parametros correspondiente al valor del tipo de objeto PersonaDireccion
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findIdDireccion(PersonaDireccion apd_parametros)
	    throws B2BException
	{
		int               li_idCorreo;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_idCorreo     = 0;
		lps_ps          = null;
		lrs_rs          = null;

		if(apd_parametros != null)
		{
			try
			{
				lps_ps = getConnection().prepareStatement(cs_FIND_MAX_ID_DIRECCION);

				lps_ps.setString(1, apd_parametros.getIdPersona());

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_idCorreo = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findIdCorreoElectronico", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_idCorreo;
	}

	/**
	 * Find first by datos direccion JSON.
	 *
	 * @param apd_personaDireccion de apd persona direccion
	 * @return el valor de persona direccion
	 * @throws B2BException Objeto de tipo B2BException, se produce cuando se encuentra algun error controlado.
	 */
	public PersonaDireccion findFirstByDatosDireccionJSON(PersonaDireccion apd_personaDireccion)
	    throws B2BException
	{
		PersonaDireccion lpd_return;
		lpd_return = null;

		if(apd_personaDireccion != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_column;
				StringBuilder lsb_sb;

				li_column     = 1;
				lsb_sb        = new StringBuilder(cs_FIND_FIRST_BY_DATOS_DIRECCION_JSON);

				String ls_datoEjeSecundario;
				String ls_dato2EjeSecundario;
				String ls_idLetraEjePrincipal;
				String ls_idLetra1EjeSecundario;
				String ls_idLetra2EjeSecundario;
				String ls_idComplementoEjePrincipal;
				String ls_idComplemento1EjeSecundario;
				String ls_idComplemento2EjeSecundario;
				String ls_idCoordenadaEjePrincipal;
				String ls_idCoordenada1EjeSecundario;
				String ls_idCoordenada2EjeSecundario;
				String ls_idComplementoDireccion;
				String ls_complementoDireccion;

				ls_datoEjeSecundario = apd_personaDireccion.getDatoEjeSecundario();
				ls_dato2EjeSecundario = apd_personaDireccion.getDato2EjeSecundario();
				ls_idLetraEjePrincipal = apd_personaDireccion.getIdLetraEjePrincipal();
				ls_idLetra1EjeSecundario = apd_personaDireccion.getIdLetra1EjeSecundario();
				ls_idLetra2EjeSecundario = apd_personaDireccion.getIdLetra2EjeSecundario();
				ls_idComplementoEjePrincipal = apd_personaDireccion.getIdComplementoEjePrincipal();
				ls_idComplemento1EjeSecundario = apd_personaDireccion.getIdComplemento1EjeSecundario();
				ls_idComplemento2EjeSecundario = apd_personaDireccion.getIdComplemento2EjeSecundario();
				ls_idCoordenadaEjePrincipal = apd_personaDireccion.getIdCoordenadaEjePrincipal();
				ls_idCoordenada1EjeSecundario = apd_personaDireccion.getIdCoordenada1EjeSecundario();
				ls_idCoordenada2EjeSecundario = apd_personaDireccion.getIdCoordenada2EjeSecundario();
				ls_idComplementoDireccion = apd_personaDireccion.getIdComplementoDireccion();
				ls_complementoDireccion = apd_personaDireccion.getComplementoDireccion();

				if(StringUtils.isValidString(ls_datoEjeSecundario))
					lsb_sb.append(" AND DATO_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_dato2EjeSecundario))
					lsb_sb.append(" AND DATO2_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idLetraEjePrincipal))
					lsb_sb.append(" AND ID_LETRA_EJE_PRINCIPAL = ?");

				if(StringUtils.isValidString(ls_idLetra1EjeSecundario))
					lsb_sb.append(" AND ID_LETRA1_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idLetra2EjeSecundario))
					lsb_sb.append(" AND ID_LETRA2_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idComplementoEjePrincipal))
					lsb_sb.append(" AND ls_idComplemento1EjeSecundario = ?");

				if(StringUtils.isValidString(ls_idComplemento1EjeSecundario))
					lsb_sb.append(" AND ID_COMPLEMENTO1_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idComplemento2EjeSecundario))
					lsb_sb.append(" AND ID_COMPLEMENTO2_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idCoordenadaEjePrincipal))
					lsb_sb.append(" AND ID_COORDENADA_EJE_PRINCIPAL = ?");

				if(StringUtils.isValidString(ls_idCoordenada1EjeSecundario))
					lsb_sb.append(" AND ID_COORDENADA1_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idCoordenada2EjeSecundario))
					lsb_sb.append(" AND ID_COORDENADA2_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idCoordenada2EjeSecundario))
					lsb_sb.append(" AND ID_COORDENADA2_EJE_SECUNDARIO = ?");

				if(StringUtils.isValidString(ls_idComplementoDireccion))
					lsb_sb.append(" AND ID_COMPLEMENTO_DIRECCION = ?");

				if(StringUtils.isValidString(ls_complementoDireccion))
					lsb_sb.append(" AND COMPLEMENTO_DIRECCION = ?");

				lsb_sb.append(" AND ACTIVO = 'S'");

				lps_ps = getConnection().prepareStatement(lsb_sb.toString());

				lps_ps.setString(li_column++, apd_personaDireccion.getIdPersona());
				lps_ps.setString(li_column++, apd_personaDireccion.getIdPais());
				lps_ps.setString(li_column++, apd_personaDireccion.getIdDepartamento());
				lps_ps.setString(li_column++, apd_personaDireccion.getIdMunicipio());
				lps_ps.setString(li_column++, apd_personaDireccion.getTipoDireccion());
				lps_ps.setString(li_column++, apd_personaDireccion.getIdTipoPredio());
				lps_ps.setString(li_column++, apd_personaDireccion.getDatoEjePrincipal());

				if(StringUtils.isValidString(ls_datoEjeSecundario))
					lps_ps.setString(li_column++, ls_datoEjeSecundario);

				if(StringUtils.isValidString(ls_dato2EjeSecundario))
					lps_ps.setString(li_column++, ls_dato2EjeSecundario);

				if(StringUtils.isValidString(ls_idLetraEjePrincipal))
					lps_ps.setString(li_column++, ls_idLetraEjePrincipal);

				if(StringUtils.isValidString(ls_idLetra1EjeSecundario))
					lps_ps.setString(li_column++, ls_idLetra1EjeSecundario);

				if(StringUtils.isValidString(ls_idLetra2EjeSecundario))
					lps_ps.setString(li_column++, ls_idLetra2EjeSecundario);

				if(StringUtils.isValidString(ls_idComplementoEjePrincipal))
					lps_ps.setString(li_column++, ls_idComplementoEjePrincipal);

				if(StringUtils.isValidString(ls_idComplemento1EjeSecundario))
					lps_ps.setString(li_column++, ls_idComplemento1EjeSecundario);

				if(StringUtils.isValidString(ls_idComplemento2EjeSecundario))
					lps_ps.setString(li_column++, ls_idComplemento2EjeSecundario);

				if(StringUtils.isValidString(ls_idCoordenadaEjePrincipal))
					lps_ps.setString(li_column++, ls_idCoordenadaEjePrincipal);

				if(StringUtils.isValidString(ls_idCoordenada1EjeSecundario))
					lps_ps.setString(li_column++, ls_idCoordenada1EjeSecundario);

				if(StringUtils.isValidString(ls_idCoordenada2EjeSecundario))
					lps_ps.setString(li_column++, ls_idCoordenada2EjeSecundario);

				if(StringUtils.isValidString(ls_idComplementoDireccion))
					lps_ps.setString(li_column++, ls_idComplementoDireccion);

				if(StringUtils.isValidString(ls_complementoDireccion))
					lps_ps.setString(li_column++, ls_complementoDireccion);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					lpd_return = getPersonaDireccion(lrs_rs, false);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findFirstByDatosDireccionJSON", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return lpd_return;
	}

	/**
	 * Retorna el valor del objeto de long con el maximo id de la direccion.
	 *
	 * @param apd_parametros correspondiente al valor del tipo de objeto PersonaDireccion
	 * @param ab_query correspondiente al valor del tipo de objeto boolean
	 * @return devuelve el valor de long
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public long insertOrUpdate(PersonaDireccion apd_parametros, boolean ab_query)
	    throws B2BException
	{
		long li_max;
		li_max = 0;

		if(apd_parametros != null)
		{
			PreparedStatement lps_ps;
			PreparedStatement lps_max;
			ResultSet         lrs_rs;

			lps_ps      = null;
			lps_max     = null;
			lrs_rs      = null;

			try
			{
				int        li_column;
				Connection lc_connection;

				li_column         = 1;
				lc_connection     = getConnection();

				lps_ps      = lc_connection.prepareStatement(ab_query ? cs_INSERT : cs_UPDATE);
				lps_max     = lc_connection.prepareStatement(cs_FIND_MAX_ID_DIRECCION);

				if(ab_query)
				{
					String ls_idPersona;

					ls_idPersona = apd_parametros.getIdPersona();

					lps_ps.setString(li_column++, ls_idPersona);
					lps_max.setString(1, ls_idPersona);

					lrs_rs = lps_max.executeQuery();

					if(lrs_rs.next())
					{
						li_max     = lrs_rs.getLong(1);
						li_max     = li_max + 1;
						lps_ps.setString(li_column++, StringUtils.getString(li_max));
					}
				}

				lps_ps.setString(li_column++, apd_parametros.getTipoDireccion());
				lps_ps.setString(li_column++, apd_parametros.getIdPais());
				lps_ps.setString(li_column++, apd_parametros.getIdDepartamento());
				lps_ps.setString(li_column++, apd_parametros.getIdMunicipio());
				lps_ps.setString(li_column++, apd_parametros.getIdTipoPredio());
				lps_ps.setString(li_column++, apd_parametros.getDireccion());
				lps_ps.setString(li_column++, apd_parametros.getIdTipoEjePrincipal());
				lps_ps.setString(li_column++, apd_parametros.getDatoEjePrincipal());
				lps_ps.setString(li_column++, apd_parametros.getIdLetraEjePrincipal());
				lps_ps.setString(li_column++, apd_parametros.getIdComplementoEjePrincipal());
				lps_ps.setString(li_column++, apd_parametros.getIdCoordenadaEjePrincipal());
				lps_ps.setString(li_column++, apd_parametros.getIdTipoEjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getDatoEjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdLetra1EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdComplemento1EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdCoordenada1EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getDato2EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdLetra2EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdComplemento2EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdCoordenada2EjeSecundario());
				lps_ps.setString(li_column++, apd_parametros.getIdComplementoDireccion());
				lps_ps.setString(li_column++, apd_parametros.getComplementoDireccion());
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apd_parametros.getFechaDesde()));
				lps_ps.setTimestamp(li_column++, DateUtils.getTimestamp(apd_parametros.getFechaHasta()));

				if(ab_query)
				{
					lps_ps.setString(li_column++, EstadoCommon.S);
					lps_ps.setString(li_column++, apd_parametros.getIdUsuarioCreacion());
					lps_ps.setString(li_column++, apd_parametros.getIpCreacion());
				}
				else
				{
					lps_ps.setString(
					    li_column++,
					    ((!StringUtils.isValidString(apd_parametros.getActivo())) ? EstadoCommon.S
					                                                              : apd_parametros.getActivo())
					);
					lps_ps.setString(li_column++, apd_parametros.getIdUsuarioModificacion());
					lps_ps.setString(li_column++, apd_parametros.getIpModificacion());

					lps_ps.setString(li_column++, apd_parametros.getIdPersona());
					lps_ps.setString(li_column++, apd_parametros.getIdDireccion());
				}

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
				close(lps_max);
				close(lrs_rs);
			}
		}

		return li_max;
	}

	/**
	 * Retorna el valor de persona direccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona direccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaDireccion getPersonaDireccion(ResultSet ars_rs)
	    throws SQLException
	{
		return getPersonaDireccion(ars_rs, true);
	}

	/**
	 * Retorna el valor de persona direccion.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de persona direccion
	 * @throws SQLException Señala que se ha producido una excepción
	 */
	private PersonaDireccion getPersonaDireccion(ResultSet ars_rs, boolean ab_generarDireccionCompleta)
	    throws SQLException
	{
		PersonaDireccion lpd_return;

		lpd_return = new PersonaDireccion();

		lpd_return.setIdPersona(ars_rs.getString("ID_PERSONA"));
		lpd_return.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		lpd_return.setTipoDireccion(ars_rs.getString("TIPO_DIRECCION"));
		lpd_return.setIdPais(ars_rs.getString("ID_PAIS"));
		lpd_return.setIdDepartamento(ars_rs.getString("ID_DEPARTAMENTO"));
		lpd_return.setIdMunicipio(ars_rs.getString("ID_MUNICIPIO"));
		lpd_return.setIdTipoPredio(ars_rs.getString("ID_TIPO_PREDIO"));
		lpd_return.setIdTipoEjePrincipal(ars_rs.getString("ID_TIPO_EJE_PRINCIPAL"));
		lpd_return.setDatoEjePrincipal(ars_rs.getString("DATO_EJE_PRINCIPAL"));
		lpd_return.setIdLetraEjePrincipal(ars_rs.getString("ID_LETRA_EJE_PRINCIPAL"));
		lpd_return.setIdComplementoEjePrincipal(ars_rs.getString("ID_COMPLEMENTO_EJE_PRINCIPAL"));
		lpd_return.setIdCoordenadaEjePrincipal(ars_rs.getString("ID_COORDENADA_EJE_PRINCIPAL"));
		lpd_return.setIdTipoEjeSecundario(ars_rs.getString("ID_TIPO_EJE_SECUNDARIO"));
		lpd_return.setDatoEjeSecundario(ars_rs.getString("DATO_EJE_SECUNDARIO"));
		lpd_return.setIdLetra1EjeSecundario(ars_rs.getString("ID_LETRA1_EJE_SECUNDARIO"));
		lpd_return.setIdComplemento1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		lpd_return.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA1_EJE_SECUNDARIO"));
		lpd_return.setDato2EjeSecundario(ars_rs.getString("DATO2_EJE_SECUNDARIO"));
		lpd_return.setIdLetra2EjeSecundario(ars_rs.getString("ID_LETRA2_EJE_SECUNDARIO"));
		lpd_return.setIdComplemento2EjeSecundario(ars_rs.getString("ID_COMPLEMENTO2_EJE_SECUNDARIO"));
		lpd_return.setIdCoordenada2EjeSecundario(ars_rs.getString("ID_COORDENADA2_EJE_SECUNDARIO"));
		lpd_return.setIdComplementoDireccion(ars_rs.getString("ID_COMPLEMENTO_DIRECCION"));
		lpd_return.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));
		lpd_return.setFechaDesde(ars_rs.getTimestamp("FECHA_DESDE"));
		lpd_return.setFechaHasta(ars_rs.getTimestamp("FECHA_HASTA"));
		lpd_return.setActivo(ars_rs.getString("ACTIVO"));

		{
			String ls_direccionCompleta;

			ls_direccionCompleta = ars_rs.getString("DIRECCION_COMPLETA");

			if(!StringUtils.isValidString(ls_direccionCompleta) && ab_generarDireccionCompleta)
				ls_direccionCompleta = generarDireccionCompleta(ars_rs, lpd_return);

			lpd_return.setDireccion(ls_direccionCompleta);
		}

		fillAuditoria(ars_rs, lpd_return);

		return lpd_return;
	}
}
