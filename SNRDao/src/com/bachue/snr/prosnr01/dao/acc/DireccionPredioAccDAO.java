package com.bachue.snr.prosnr01.dao.acc;

import com.b2bsg.common.exception.B2BException;

import com.b2bsg.common.util.CollectionUtils;
import com.b2bsg.common.util.NumericUtils;
import com.b2bsg.common.util.StringUtils;

import com.bachue.snr.prosnr01.dao.auditoria.AuditoriaDao;

import com.bachue.snr.prosnr01.model.sdb.acc.DireccionPredioAcc;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Collection;


/**
 * Clase que contiene todos las propiedades de DireccionPredioAccDAO.
 *
 * @author mblanco
 */
public class DireccionPredioAccDAO extends AuditoriaDao
{
	/** Constante cs_FIND_BY_ID. */
	private static final String cs_FIND_BY_ID = "SELECT ADP.ID_DIRECCION_PREDIO,ADP.ID_TURNO_HISTORIA,ADP.ID_TURNO,ADP.ID_CIRCULO,ADP.ID_MATRICULA,ADP.ID_DIRECCION,ADP.DIRECCION_COMPLETA,ADP.ID_TIPO_EJE_PRINCIPAL,ADP.DATO_EJE_PRINCIPAL,ADP.ID_LETRA_EJE_PRINCIPAL,ADP.ID_COMPLEMENTO_EJE_PRINCIPAL,ADP.ID_COORDENADA_EJE_PRINCIPAL,ADP.ID_TIPO_EJE_SECUNDARIO,ADP.DATO_EJE_SECUNDARIO,ADP.ID_LETRA1_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO,ADP.ID_COORDENADA1_EJE_SECUNDARIO,ADP.DATO2_EJE_SECUNDARIO,ADP.ID_LETRA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO,ADP.ID_COORDENADA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO_DIRECCION,ADP.COMPLEMENTO_DIRECCION,ADP.ID_USUARIO_CREACION,ADP.FECHA_CREACION,ADP.IP_CREACION,ADP.ID_USUARIO_MODIFICACION,ADP.FECHA_MODIFICACION,ADP.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_DIRECCION_PREDIO ADP LEFT JOIN SDB_PNG_TIPO_EJE PTE ON ADP.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON ADP.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON ADP.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON ADP.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON ADP.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON ADP.ID_COORDENADA1_EJE_SECUNDARIO = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON ADP.ID_COORDENADA2_EJE_SECUNDARIO = PC.ID_COORDENADA WHERE ADP.ID_DIRECCION_PREDIO = ?";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA = "SELECT ADP.ID_DIRECCION_PREDIO,ADP.ID_TURNO_HISTORIA,ADP.ID_TURNO,ADP.ID_CIRCULO,ADP.ID_MATRICULA,ADP.ID_DIRECCION,ADP.DIRECCION_COMPLETA,ADP.ID_TIPO_EJE_PRINCIPAL,ADP.DATO_EJE_PRINCIPAL,ADP.ID_LETRA_EJE_PRINCIPAL,ADP.ID_COMPLEMENTO_EJE_PRINCIPAL,ADP.ID_COORDENADA_EJE_PRINCIPAL,ADP.ID_TIPO_EJE_SECUNDARIO,ADP.DATO_EJE_SECUNDARIO,ADP.ID_LETRA1_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO,ADP.ID_COORDENADA1_EJE_SECUNDARIO,ADP.DATO2_EJE_SECUNDARIO,ADP.ID_LETRA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO,ADP.ID_COORDENADA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO_DIRECCION,ADP.COMPLEMENTO_DIRECCION,ADP.ID_USUARIO_CREACION,ADP.FECHA_CREACION,ADP.IP_CREACION,ADP.ID_USUARIO_MODIFICACION,ADP.FECHA_MODIFICACION,ADP.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_DIRECCION_PREDIO ADP LEFT JOIN SDB_PNG_TIPO_EJE PTE ON ADP.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON ADP.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON ADP.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON ADP.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON ADP.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON ADP.ID_COORDENADA1_EJE_SECUNDARIO = PC1.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON ADP.ID_COORDENADA2_EJE_SECUNDARIO = PC2.ID_COORDENADA WHERE ADP.ID_CIRCULO = ? AND ADP.ID_MATRICULA = ?";

	/** Constante cs_FIND_LAST_BY_CIRCULO_MATRICULA. */
	private static final String cs_FIND_LAST_BY_CIRCULO_MATRICULA = "SELECT ADP.ID_DIRECCION_PREDIO,ADP.ID_TURNO_HISTORIA,ADP.ID_TURNO,ADP.ID_CIRCULO,ADP.ID_MATRICULA,ADP.ID_DIRECCION,ADP.DIRECCION_COMPLETA,ADP.ID_TIPO_EJE_PRINCIPAL,ADP.DATO_EJE_PRINCIPAL,ADP.ID_LETRA_EJE_PRINCIPAL,ADP.ID_COMPLEMENTO_EJE_PRINCIPAL,ADP.ID_COORDENADA_EJE_PRINCIPAL,ADP.ID_TIPO_EJE_SECUNDARIO,ADP.DATO_EJE_SECUNDARIO,ADP.ID_LETRA1_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO,ADP.ID_COORDENADA1_EJE_SECUNDARIO,ADP.DATO2_EJE_SECUNDARIO,ADP.ID_LETRA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO,ADP.ID_COORDENADA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO_DIRECCION,ADP.COMPLEMENTO_DIRECCION,ADP.ID_USUARIO_CREACION,ADP.FECHA_CREACION,ADP.IP_CREACION,ADP.ID_USUARIO_MODIFICACION,ADP.FECHA_MODIFICACION,ADP.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_DIRECCION_PREDIO ADP LEFT JOIN SDB_PNG_TIPO_EJE PTE ON ADP.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON ADP.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON ADP.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON ADP.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON ADP.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON ADP.ID_COORDENADA1_EJE_SECUNDARIO = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON ADP.ID_COORDENADA2_EJE_SECUNDARIO = PC.ID_COORDENADA WHERE ADP.ID_CIRCULO = ? AND ADP.ID_MATRICULA = ? ORDER BY ADP.FECHA_CREACION DESC";

	/** Constante cs_FIND_BY_CIRCULO_MATRICULA_TURNO. */
	private static final String cs_FIND_BY_CIRCULO_MATRICULA_TURNO = "SELECT ADP.ID_DIRECCION_PREDIO,ADP.ID_TURNO_HISTORIA,ADP.ID_TURNO,ADP.ID_CIRCULO,ADP.ID_MATRICULA,ADP.ID_DIRECCION,ADP.DIRECCION_COMPLETA,ADP.ID_TIPO_EJE_PRINCIPAL,ADP.DATO_EJE_PRINCIPAL,ADP.ID_LETRA_EJE_PRINCIPAL,ADP.ID_COMPLEMENTO_EJE_PRINCIPAL,ADP.ID_COORDENADA_EJE_PRINCIPAL,ADP.ID_TIPO_EJE_SECUNDARIO,ADP.DATO_EJE_SECUNDARIO,ADP.ID_LETRA1_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO,ADP.ID_COORDENADA1_EJE_SECUNDARIO,ADP.DATO2_EJE_SECUNDARIO,ADP.ID_LETRA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO,ADP.ID_COORDENADA2_EJE_SECUNDARIO,ADP.ID_COMPLEMENTO_DIRECCION,ADP.COMPLEMENTO_DIRECCION,ADP.ID_USUARIO_CREACION,ADP.FECHA_CREACION,ADP.IP_CREACION,ADP.ID_USUARIO_MODIFICACION,ADP.FECHA_MODIFICACION,ADP.IP_MODIFICACION,PTE.NOMBRE EJE_PRINCIPAL,PTE1.NOMBRE EJE_SECUNDARIO,PTE2.NOMBRE COMPLEMENTO,PTE3.NOMBRE COMPLEMENTO1,PTE4.NOMBRE COMPLEMENTO2,PTE5.NOMBRE COMPLEMENTO_DIRECCION,PC.NOMBRE COORDENADA,PC1.NOMBRE COORDENADA1,PC2.NOMBRE COORDENADA2 FROM SDB_ACC_DIRECCION_PREDIO ADP LEFT JOIN SDB_PNG_TIPO_EJE PTE ON ADP.ID_TIPO_EJE_PRINCIPAL = PTE.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE1 ON ADP.ID_TIPO_EJE_SECUNDARIO = PTE1.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE2 ON ADP.ID_COMPLEMENTO_EJE_PRINCIPAL = PTE2.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE3 ON ADP.ID_COMPLEMENTO1_EJE_SECUNDARIO = PTE3.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE4 ON ADP.ID_COMPLEMENTO2_EJE_SECUNDARIO = PTE4.ID_TIPO_EJE LEFT JOIN SDB_PNG_TIPO_EJE PTE5 ON ADP.ID_COMPLEMENTO_DIRECCION = PTE5.ID_TIPO_EJE LEFT JOIN SDB_PNG_COORDENADA PC ON ADP.ID_COORDENADA_EJE_PRINCIPAL = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC1 ON ADP.ID_COORDENADA1_EJE_SECUNDARIO = PC.ID_COORDENADA LEFT JOIN SDB_PNG_COORDENADA PC2 ON ADP.ID_COORDENADA2_EJE_SECUNDARIO = PC.ID_COORDENADA WHERE ADP.ID_CIRCULO = ? AND ADP.ID_MATRICULA = ? AND ADP.ID_TURNO = ? ";

	/** Constante cs_FIND_SECUENCE. */
	private static final String cs_FIND_SECUENCE = "SELECT SEC_ACC_DIRECCION_PREDIO_ID_DIRECCION_PREDIO.NEXTVAL FROM DUAL";

	/** Constante cs_FIND_MAX_ID_DIRECCION. */
	private static final String cs_FIND_MAX_ID_DIRECCION = "SELECT MAX(TO_NUMBER(ID_DIRECCION)) FROM SDB_ACC_DIRECCION_PREDIO WHERE ID_CIRCULO = ? AND ID_MATRICULA = ?";

	/** Constante cs_INSERT_DIRECCION. */
	private static final String cs_INSERT_DIRECCION = "INSERT INTO SDB_ACC_DIRECCION_PREDIO (ID_DIRECCION_PREDIO, ID_TURNO_HISTORIA, ID_TURNO, ID_CIRCULO, ID_MATRICULA, ID_DIRECCION, DIRECCION_COMPLETA, ID_TIPO_EJE_PRINCIPAL, DATO_EJE_PRINCIPAL, ID_LETRA_EJE_PRINCIPAL, ID_COMPLEMENTO_EJE_PRINCIPAL, ID_COORDENADA_EJE_PRINCIPAL, ID_TIPO_EJE_SECUNDARIO, DATO_EJE_SECUNDARIO, ID_LETRA1_EJE_SECUNDARIO, ID_COMPLEMENTO1_EJE_SECUNDARIO, ID_COORDENADA1_EJE_SECUNDARIO, DATO2_EJE_SECUNDARIO, ID_LETRA2_EJE_SECUNDARIO, ID_COMPLEMENTO2_EJE_SECUNDARIO, ID_COORDENADA2_EJE_SECUNDARIO, ID_COMPLEMENTO_DIRECCION, COMPLEMENTO_DIRECCION, ID_USUARIO_CREACION, FECHA_CREACION, IP_CREACION) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,SYSTIMESTAMP,?)";

	/** Constante cs_DELETE_BY_ID. */
	private static final String cs_DELETE_BY_ID = "DELETE FROM SDB_ACC_DIRECCION_PREDIO WHERE ID_DIRECCION_PREDIO = ?";

	/** Constante cs_UPDATE_DIRECCION. */
	private static final String cs_UPDATE_DIRECCION = "UPDATE SDB_ACC_DIRECCION_PREDIO SET ID_DIRECCION_PREDIO = ?, ID_DIRECCION = ?, DIRECCION_COMPLETA = ?, ID_TIPO_EJE_PRINCIPAL = ?, DATO_EJE_PRINCIPAL = ?, ID_LETRA_EJE_PRINCIPAL = ?, ID_COMPLEMENTO_EJE_PRINCIPAL = ?, ID_COORDENADA_EJE_PRINCIPAL = ?, ID_TIPO_EJE_SECUNDARIO = ?, DATO_EJE_SECUNDARIO = ?, ID_LETRA1_EJE_SECUNDARIO = ?, ID_COMPLEMENTO1_EJE_SECUNDARIO = ?, ID_COORDENADA1_EJE_SECUNDARIO = ?, DATO2_EJE_SECUNDARIO = ?, ID_LETRA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO2_EJE_SECUNDARIO = ?, ID_COORDENADA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO_DIRECCION = ?, COMPLEMENTO_DIRECCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO_HISTORIA = ? AND ID_CIRCULO = ? AND ID_MATRICULA = ? AND ID_TURNO = ? AND ID_DIRECCION_PREDIO = ?";

	/** Constante cs_UPDATE_BY_ID_TURNO. */
	private static final String cs_UPDATE_BY_ID_TURNO = "UPDATE SDB_ACC_DIRECCION_PREDIO SET ID_DIRECCION_PREDIO = ?, ID_TURNO_HISTORIA = ?, ID_CIRCULO = ?, ID_MATRICULA = ?, ID_DIRECCION = ?, DIRECCION_COMPLETA = ?, ID_TIPO_EJE_PRINCIPAL = ?, DATO_EJE_PRINCIPAL = ?, ID_LETRA_EJE_PRINCIPAL = ?, ID_COMPLEMENTO_EJE_PRINCIPAL = ?, ID_COORDENADA_EJE_PRINCIPAL = ?, ID_TIPO_EJE_SECUNDARIO = ?, DATO_EJE_SECUNDARIO = ?, ID_LETRA1_EJE_SECUNDARIO = ?, ID_COMPLEMENTO1_EJE_SECUNDARIO = ?, ID_COORDENADA1_EJE_SECUNDARIO = ?, DATO2_EJE_SECUNDARIO = ?, ID_LETRA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO2_EJE_SECUNDARIO = ?, ID_COORDENADA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO_DIRECCION = ?, COMPLEMENTO_DIRECCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_TURNO = ?";

	/** Constante cs_UPDATE_BY_ID_DIRECCION. */
	private static final String cs_UPDATE_BY_ID_DIRECCION = "UPDATE SDB_ACC_DIRECCION_PREDIO SET ID_TURNO_HISTORIA = ?, ID_TURNO = ?, ID_CIRCULO = ?, ID_MATRICULA = ?, ID_DIRECCION = ?, DIRECCION_COMPLETA = ?, ID_TIPO_EJE_PRINCIPAL = ?, DATO_EJE_PRINCIPAL = ?, ID_LETRA_EJE_PRINCIPAL = ?, ID_COMPLEMENTO_EJE_PRINCIPAL = ?, ID_COORDENADA_EJE_PRINCIPAL = ?, ID_TIPO_EJE_SECUNDARIO = ?, DATO_EJE_SECUNDARIO = ?, ID_LETRA1_EJE_SECUNDARIO = ?, ID_COMPLEMENTO1_EJE_SECUNDARIO = ?, ID_COORDENADA1_EJE_SECUNDARIO = ?, DATO2_EJE_SECUNDARIO = ?, ID_LETRA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO2_EJE_SECUNDARIO = ?, ID_COORDENADA2_EJE_SECUNDARIO = ?, ID_COMPLEMENTO_DIRECCION = ?, COMPLEMENTO_DIRECCION = ?, ID_USUARIO_MODIFICACION = ?, FECHA_MODIFICACION = SYSTIMESTAMP, IP_MODIFICACION = ? WHERE ID_DIRECCION_PREDIO = ?";

	/**
	 * Delete by id.
	 *
	 * @param adpa_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(DireccionPredioAcc adpa_param)
	    throws B2BException
	{
		if(adpa_param != null)
			deleteById(adpa_param.getIdDireccionPredio());
	}

	/**
	 * Delete by id.
	 *
	 * @param as_param correspondiente al valor del tipo de objeto String
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void deleteById(String as_param)
	    throws B2BException
	{
		if(StringUtils.isValidString(as_param))
		{
			PreparedStatement lps_ps;
			int               li_column;

			li_column     = 1;
			lps_ps        = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_DELETE_BY_ID);

				lps_ps.setString(li_column++, as_param);

				lps_ps.executeQuery();
			}
			catch(SQLException lse_e)
			{
				logError(this, "deleteById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor del objeto de Collection.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @return devuelve el valor de Collection
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public Collection<DireccionPredioAcc> findAllByIdCirculoMatriculaTurno(DireccionPredioAcc at_param)
	    throws B2BException
	{
		int                            li_cont;
		Collection<DireccionPredioAcc> ls_object;
		PreparedStatement              lps_ps;
		ResultSet                      lrs_rs;

		li_cont       = 1;
		ls_object     = new ArrayList<DireccionPredioAcc>();
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

			lps_ps.setString(li_cont++, at_param.getIdCirculo());
			setLong(lps_ps, at_param.getIdMatricula(), li_cont++);
			lps_ps.setString(li_cont++, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			while(lrs_rs.next())
				ls_object.add(getObjetFromResultSet(lrs_rs));
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoMatriculaTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Obtiene todas las direcciones relacionadas a un círculo y matrícula.
	 *
	 * @param as_idCirculo Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param al_idMatricula Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_idDireccion Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param as_idTurno Objeto contenedor de los parametros a utilizar como filtro en la consulta
	 * @param ab_maxIdOnly true para utilizar el id dirección en el filtro de la consulta, false para buscar todos los registros
	 * diferentes a este id
	 * @return Colección resultante de la consulta
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public Collection<DireccionPredioAcc> findByCirculoMatriculaIdDireccion(
	    String as_idCirculo, long al_idMatricula, String as_idDireccion, String as_idTurno, boolean ab_maxIdOnly
	)
	    throws B2BException
	{
		Collection<DireccionPredioAcc> lcdp_direccionPredio;

		lcdp_direccionPredio = new ArrayList<DireccionPredioAcc>();

		if(
		    StringUtils.isValidString(as_idCirculo) && (al_idMatricula > 0)
			    && StringUtils.isValidString(as_idDireccion) && StringUtils.isValidString(as_idTurno)
		)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				StringBuilder lsb_query;
				int           li_cont;

				lsb_query     = new StringBuilder();
				li_cont       = 1;

				lsb_query.append(cs_FIND_BY_CIRCULO_MATRICULA);

				if(ab_maxIdOnly)
					lsb_query.append(" AND ID_DIRECCION = ? ");
				else
					lsb_query.append(" AND ID_DIRECCION <> ? ");

				lsb_query.append(" AND ID_TURNO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_idCirculo);
				lps_ps.setLong(li_cont++, al_idMatricula);
				lps_ps.setString(li_cont++, as_idDireccion);
				lps_ps.setString(li_cont++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdp_direccionPredio.add(getObjetFromResultSet(lrs_rs));
			}
			catch(SQLException lse_e)
			{
				logError(this, "findByCirculoMatriculaIdDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		if(!CollectionUtils.isValidCollection(lcdp_direccionPredio))
			lcdp_direccionPredio = null;

		return lcdp_direccionPredio;
	}

	/**
	 * Retorna el valor del objeto de DireccionPredioAcc.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @return devuelve el valor de DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public DireccionPredioAcc findById(DireccionPredioAcc at_param)
	    throws B2BException
	{
		DireccionPredioAcc ls_object;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_ID);

			lps_ps.setString(1, at_param.getIdDireccionPredio());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
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

		return ls_object;
	}

	/**
	 * Busca todas las direcciones relacionadas a un círculo y matrícula específicos.
	 *
	 * @param adpa_param Objeto contenedor de los parametros a utilizar como filtro en la busqueda
	 * @return Colección resultante de la busqueda
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public Collection<DireccionPredioAcc> findByIdCirculoMatricula(DireccionPredioAcc adpa_param)
	    throws B2BException
	{
		Collection<DireccionPredioAcc> lcdp_direccionPredio;

		lcdp_direccionPredio = new ArrayList<DireccionPredioAcc>();

		if(adpa_param != null)
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				int           li_column;
				StringBuilder lsb_query;

				li_column     = 1;
				lsb_query     = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA);

				lsb_query.append(" ORDER BY ID_DIRECCION ASC");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_column++, adpa_param.getIdCirculo());
				setLong(lps_ps, adpa_param.getIdMatricula(), li_column++);

				lrs_rs = lps_ps.executeQuery();

				while(lrs_rs.next())
					lcdp_direccionPredio.add(getObjetFromResultSet(lrs_rs));
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

		if(!CollectionUtils.isValidCollection(lcdp_direccionPredio))
			lcdp_direccionPredio = null;

		return lcdp_direccionPredio;
	}

	/**
	 * Retorna el valor del objeto de DireccionPredioAcc.
	 *
	 * @param at_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @return devuelve el valor de DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public DireccionPredioAcc findByIdCirculoMatriculaTurno(DireccionPredioAcc at_param)
	    throws B2BException
	{
		int                li_cont;
		DireccionPredioAcc ldpa_object;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		li_cont         = 1;
		ldpa_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

			lps_ps.setString(li_cont++, at_param.getIdCirculo());
			setLong(lps_ps, at_param.getIdMatricula(), li_cont++);
			lps_ps.setString(li_cont++, at_param.getIdTurno());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldpa_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoMatriculaTurno", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldpa_object;
	}

	/**
	 * Método encargado de consultar las direcciones con base a un circulo, matrícula, turno y id dirección.
	 *
	 * @param at_param Objeto que contiene la información para realizar la consulta.
	 * @return Objeto que contiene la información de al dirección consultada
	 * @throws B2BException
	 */
	public DireccionPredioAcc findByIdCirculoMatriculaTurnoDireccion(DireccionPredioAcc at_param)
	    throws B2BException
	{
		DireccionPredioAcc ldpa_object;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ldpa_object     = null;
		lps_ps          = null;
		lrs_rs          = null;

		try
		{
			int           li_cont;
			StringBuilder lsb_sb;

			li_cont     = 1;
			lsb_sb      = new StringBuilder(cs_FIND_BY_CIRCULO_MATRICULA_TURNO);

			lsb_sb.append(" AND ADP.ID_DIRECCION = ? ");

			lps_ps = getConnection().prepareStatement(lsb_sb.toString());

			lps_ps.setString(li_cont++, at_param.getIdCirculo());
			setLong(lps_ps, at_param.getIdMatricula(), li_cont++);
			lps_ps.setString(li_cont++, at_param.getIdTurno());
			lps_ps.setString(li_cont++, at_param.getIdDireccion());

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ldpa_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findByIdCirculoMatriculaTurnoDireccion", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ldpa_object;
	}

	/**
	 * Retorna el valor del objeto de DireccionPredioAcc.
	 *
	 * @param adpa_param correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @return devuelve el valor de DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public DireccionPredioAcc findLastByIdCirculoMatricula(DireccionPredioAcc adpa_param)
	    throws B2BException
	{
		return (adpa_param != null)
		? findLastByIdCirculoMatricula(adpa_param.getIdCirculo(), adpa_param.getIdMatricula()) : null;
	}

	/**
	 * Retorna el valor del objeto de DireccionPredioAcc.
	 *
	 * @param as_idCirculo correspondiente al valor de id circulo
	 * @param al_idMatricula correspondiente al valor de id matricula
	 * @return devuelve el valor de DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see DireccionPredioAcc
	 */
	public DireccionPredioAcc findLastByIdCirculoMatricula(String as_idCirculo, Long al_idMatricula)
	    throws B2BException
	{
		DireccionPredioAcc ls_object;
		PreparedStatement  lps_ps;
		ResultSet          lrs_rs;

		ls_object     = null;
		lps_ps        = null;
		lrs_rs        = null;

		try
		{
			lps_ps = getConnection().prepareStatement(cs_FIND_LAST_BY_CIRCULO_MATRICULA);

			lps_ps.setString(1, as_idCirculo);
			setLong(lps_ps, al_idMatricula, 2);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				ls_object = getObjetFromResultSet(lrs_rs);
		}
		catch(SQLException lse_e)
		{
			logError(this, "findLastByIdCirculoMatricula", lse_e);

			throw new B2BException(SQL_ERROR, lse_e);
		}
		finally
		{
			close(lrs_rs);
			close(lps_ps);
		}

		return ls_object;
	}

	/**
	 * Busca el maximo id dirección para una matrícula específica.
	 *
	 * @param adpa_param Objeto contenedor del id de la matrícula a utilizar como filtro en la busqueda
	 * @return número correspondiente al maximo id dirección
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findMaxIdDireccion(DireccionPredioAcc adpa_param)
	    throws B2BException
	{
		return (adpa_param != null) ? findMaxIdDireccion(adpa_param.getIdCirculo(), adpa_param.getIdMatricula(), null) : 0;
	}

	/**
	 * Busca el maximo id dirección para una matrícula específica.
	 *
	 * @param as_idCirculo id del círculo al cual pertenece la matrícula
	 * @param al_idMatricula id de la matrícula a utilizar como filtro en la búsqueda
	 * @param as_idTurno id del turno al cual se encuentra asociada la matrícula
	 * @return número correspondiente al maximo id dirección
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findMaxIdDireccion(String as_idCirculo, Long al_idMatricula, String as_idTurno)
	    throws B2BException
	{
		int li_idDireccion;

		li_idDireccion = 0;

		if(StringUtils.isValidString(as_idCirculo) && NumericUtils.isValidLong(al_idMatricula))
		{
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			lps_ps     = null;
			lrs_rs     = null;

			try
			{
				boolean       lb_conTurno;
				StringBuilder lsb_query;
				int           li_cont;

				lb_conTurno     = StringUtils.isValidString(as_idTurno);
				lsb_query       = new StringBuilder(cs_FIND_MAX_ID_DIRECCION);
				li_cont         = 1;

				if(lb_conTurno)
					lsb_query.append(" AND ID_TURNO = ? ");

				lps_ps = getConnection().prepareStatement(lsb_query.toString());

				lps_ps.setString(li_cont++, as_idCirculo);
				setLong(lps_ps, al_idMatricula, li_cont++);

				if(lb_conTurno)
					lps_ps.setString(li_cont++, as_idTurno);

				lrs_rs = lps_ps.executeQuery();

				if(lrs_rs.next())
					li_idDireccion = lrs_rs.getInt(1);
			}
			catch(SQLException lse_e)
			{
				logError(this, "findMaxIdDireccion", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}

		return li_idDireccion;
	}

	/**
	 * Retorna el valor del objeto de int.
	 *
	 * @return devuelve el valor de int
	 * @throws B2BException Señala que se ha producido una excepción
	 * @see int
	 */
	public int findSecuence()
	    throws B2BException
	{
		int               li_secuencia;
		PreparedStatement lps_ps;
		ResultSet         lrs_rs;

		li_secuencia     = 0;
		lps_ps           = null;
		lrs_rs           = null;

		try
		{
			lps_ps     = getConnection().prepareStatement(cs_FIND_SECUENCE);

			lrs_rs = lps_ps.executeQuery();

			if(lrs_rs.next())
				li_secuencia = lrs_rs.getInt(1);
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

		return li_secuencia;
	}

	/**
	 * Insert.
	 *
	 * @param adp_dp correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void insert(DireccionPredioAcc adp_dp)
	    throws B2BException
	{
		if(adp_dp != null)
		{
			DireccionPredioAcc ldp_direccion;
			ldp_direccion = adp_dp;

			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_INSERT_DIRECCION);

				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccionPredio());
				setLong(lps_ps, ldp_direccion.getIdTurnoHistoria(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdTurno());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCirculo());
				setLong(lps_ps, ldp_direccion.getIdMatricula(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetraEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenadaEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDato2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdUsuarioCreacion());
				lps_ps.setString(li_cont++, ldp_direccion.getIpCreacion());

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
			}
		}
	}

	/**
	 * Update.
	 *
	 * @param adp_dp correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void update(DireccionPredioAcc adp_dp)
	    throws B2BException
	{
		if(adp_dp != null)
		{
			DireccionPredioAcc ldp_direccion;
			ldp_direccion = adp_dp;

			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				lps_ps = getConnection().prepareStatement(cs_UPDATE_DIRECCION);

				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccionPredio());
				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetraEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenadaEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDato2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, ldp_direccion.getIpModificacion());
				setLong(lps_ps, ldp_direccion.getIdTurnoHistoria(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdCirculo());
				setLong(lps_ps, ldp_direccion.getIdMatricula(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdTurno());
				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccionPredio());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "update", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Update by id.
	 *
	 * @param adp_dp correspondiente al valor del tipo de objeto DireccionPredioAcc
	 * @param ab_b correspondiente al valor del tipo de objeto boolean
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	public void updateById(DireccionPredioAcc adp_dp, boolean ab_b)
	    throws B2BException
	{
		if(adp_dp != null)
		{
			DireccionPredioAcc ldp_direccion;
			ldp_direccion = adp_dp;

			int               li_cont;
			PreparedStatement lps_ps;
			ResultSet         lrs_rs;
			String            ls_query;

			li_cont     = 1;
			lps_ps      = null;
			lrs_rs      = null;

			try
			{
				if(ab_b)
					ls_query = cs_UPDATE_BY_ID_DIRECCION;
				else
					ls_query = cs_UPDATE_BY_ID_TURNO;

				lps_ps = getConnection().prepareStatement(ls_query);

				setLong(lps_ps, ldp_direccion.getIdTurnoHistoria(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdTurno());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCirculo());
				setLong(lps_ps, ldp_direccion.getIdMatricula(), li_cont++);
				lps_ps.setString(li_cont++, ldp_direccion.getIdDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetraEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenadaEjePrincipal());
				lps_ps.setString(li_cont++, ldp_direccion.getIdTipoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDatoEjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada1EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getDato2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdLetra2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplemento2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdCoordenada2EjeSecundario());
				lps_ps.setString(li_cont++, ldp_direccion.getIdComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getComplementoDireccion());
				lps_ps.setString(li_cont++, ldp_direccion.getIdUsuarioModificacion());
				lps_ps.setString(li_cont++, ldp_direccion.getIpModificacion());

				if(ab_b)
					lps_ps.setString(li_cont++, ldp_direccion.getIdDireccionPredio());
				else
					lps_ps.setString(li_cont++, ldp_direccion.getIdTurno());

				lps_ps.executeUpdate();
			}
			catch(SQLException lse_e)
			{
				logError(this, "updateById", lse_e);

				throw new B2BException(SQL_ERROR, lse_e);
			}
			finally
			{
				close(lrs_rs);
				close(lps_ps);
			}
		}
	}

	/**
	 * Retorna el valor de objet from result set.
	 *
	 * @param ars_rs correspondiente al valor del tipo de objeto ResultSet
	 * @return el valor de objet from result set
	 * @throws SQLException Señala que se ha producido una excepción
	 * @throws B2BException Señala que se ha producido una excepción
	 */
	private DireccionPredioAcc getObjetFromResultSet(ResultSet ars_rs)
	    throws SQLException, B2BException
	{
		DireccionPredioAcc ldp_direccion;

		ldp_direccion = new DireccionPredioAcc();

		ldp_direccion.setIdDireccionPredio(ars_rs.getString("ID_DIRECCION_PREDIO"));
		ldp_direccion.setIdTurnoHistoria(NumericUtils.getLongWrapper(ars_rs.getLong("ID_TURNO_HISTORIA")));
		ldp_direccion.setIdTurno(ars_rs.getString("ID_TURNO"));
		ldp_direccion.setIdCirculo(ars_rs.getString("ID_CIRCULO"));
		ldp_direccion.setIdMatricula(NumericUtils.getLongWrapper(ars_rs.getLong("ID_MATRICULA")));
		ldp_direccion.setIdDireccion(ars_rs.getString("ID_DIRECCION"));
		ldp_direccion.setIdTipoEjePrincipal(ars_rs.getString("ID_TIPO_EJE_PRINCIPAL"));
		ldp_direccion.setDatoEjePrincipal(ars_rs.getString("DATO_EJE_PRINCIPAL"));
		ldp_direccion.setIdLetraEjePrincipal(ars_rs.getString("ID_LETRA_EJE_PRINCIPAL"));
		ldp_direccion.setIdComplementoEjePrincipal(ars_rs.getString("ID_COMPLEMENTO_EJE_PRINCIPAL"));
		ldp_direccion.setIdCoordenadaEjePrincipal(ars_rs.getString("ID_COORDENADA_EJE_PRINCIPAL"));
		ldp_direccion.setIdTipoEjeSecundario(ars_rs.getString("ID_TIPO_EJE_SECUNDARIO"));
		ldp_direccion.setDatoEjeSecundario(ars_rs.getString("DATO_EJE_SECUNDARIO"));
		ldp_direccion.setIdLetra1EjeSecundario(ars_rs.getString("ID_LETRA1_EJE_SECUNDARIO"));
		ldp_direccion.setIdComplemento1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		ldp_direccion.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COMPLEMENTO1_EJE_SECUNDARIO"));
		ldp_direccion.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA1_EJE_SECUNDARIO"));
		ldp_direccion.setDato2EjeSecundario(ars_rs.getString("DATO2_EJE_SECUNDARIO"));
		ldp_direccion.setIdLetra2EjeSecundario(ars_rs.getString("ID_LETRA2_EJE_SECUNDARIO"));
		ldp_direccion.setIdComplemento2EjeSecundario(ars_rs.getString("ID_COMPLEMENTO2_EJE_SECUNDARIO"));
		ldp_direccion.setIdCoordenada1EjeSecundario(ars_rs.getString("ID_COORDENADA2_EJE_SECUNDARIO"));
		ldp_direccion.setIdComplementoDireccion(ars_rs.getString("ID_COMPLEMENTO_DIRECCION"));
		ldp_direccion.setComplementoDireccion(ars_rs.getString("COMPLEMENTO_DIRECCION"));

		{
			String ls_direccionCompleta;

			ls_direccionCompleta = ars_rs.getString("DIRECCION_COMPLETA");

			if(!StringUtils.isValidString(ls_direccionCompleta))
				ls_direccionCompleta = generarDireccionCompleta(ars_rs, ldp_direccion);

			ldp_direccion.setDireccion(ls_direccionCompleta);
		}

		fillAuditoria(ars_rs, ldp_direccion);

		return ldp_direccion;
	}
}
