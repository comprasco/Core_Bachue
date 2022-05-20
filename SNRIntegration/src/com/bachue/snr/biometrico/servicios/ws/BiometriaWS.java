/**
 * BiometriaWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.bachue.snr.biometrico.servicios.ws;

public interface BiometriaWS extends java.rmi.Remote {
    public com.bachue.snr.biometrico.servicios.ws.ConstantesSalidaDTO obtenerConstantes() throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO borrarHuellas(com.bachue.snr.biometrico.servicios.ws.BorrarHuellasDTO entradaUsuario) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO enrolarUsuario(com.bachue.snr.biometrico.servicios.ws.HuellaDTO[] entradaHuella) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO registrarEvento(com.bachue.snr.biometrico.servicios.ws.LogDTO entradaLog) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.SesionDTO consultarSesion(com.bachue.snr.biometrico.servicios.ws.SesionEntradaDTO entradaSesion) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerTipoSegundoFactor(com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO crearUsuario(com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaUsuario) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO actualizarClave(com.bachue.snr.biometrico.servicios.ws.UsuarioDTO entradaClave) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.StringSalidaDTO obtenerUsuario(com.bachue.snr.biometrico.servicios.ws.UsuarioEntradaDTO entradaUsuario) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarUsuario(com.bachue.snr.biometrico.servicios.ws.VerificacionDTO entradaVerificacion) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.EstadisticasSalidaDTO consultarEstadisticas(com.bachue.snr.biometrico.servicios.ws.EstadisticasEntradaDTO entradaEstadisticas) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.BooleanSalidaDTO verificarClave(com.bachue.snr.biometrico.servicios.ws.ClaveDTO entradaClave) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.AgregarFirmaSalidaDTO agregarFirma(com.bachue.snr.biometrico.servicios.ws.AgregarFirmaDTO entradaAgregarFirma) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaSalidaDTO obtenerFirma(com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaObtenerFirma) throws java.rmi.RemoteException;
    public com.bachue.snr.biometrico.servicios.ws.FirmaSalidaDTO eliminarFirma(com.bachue.snr.biometrico.servicios.ws.ObtenerFirmaDTO entradaEliminarFirma) throws java.rmi.RemoteException;
}
