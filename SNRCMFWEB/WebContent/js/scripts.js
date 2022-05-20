'use strict'
// window.onload = function(){
// if(window.addEventListener){
// window.addEventListener('keypress', forceKeyPressUppercase, false);
// }else if(document.body.attachEvent){// IE
// document.body.attachEvent('keypress', forceKeyPressUppercase);
// }else{
// document.onkeydown = forceKeyPressUppercase;
// };
// };
//
// function forceKeyPressUppercase(av_event)
// {
// var charInput = av_event.keyCode;
// if((charInput >= 97) && (charInput <= 122) || (charInput == 241 || charInput
// == 225 || charInput == 233 || charInput == 237 || charInput == 250 ||
// charInput == 252 || charInput == 243 || charInput == 231)) {
// if(!av_event.ctrlKey && !av_event.metaKey && !av_event.altKey) {
// var newChar = charInput - 32;
// var start = av_event.target.selectionStart;
// var end = av_event.target.selectionEnd;
// av_event.target.value = av_event.target.value.substring(0, start) +
// String.fromCharCode(newChar) + av_event.target.value.substring(end);
// av_event.target.setSelectionRange(start+1, start+1);
// av_event.preventDefault();
// }
// }
// }

function mostrarElemento(av_idElemento) {
    var lo_boton;
    lo_boton = document.getElementById(av_idElemento);

    if (lo_boton != null) {
        lo_boton.style.display = 'inline';
    }
}

function ocultarElemento(av_idElemento) {
    var lo_boton;
    lo_boton = document.getElementById(av_idElemento);

    if (lo_boton != null) {
        lo_boton.style.display = 'none';
    }
}
function accionBotonVisualizarDocumentoConstancia() {
    mostrarElemento('fRegistroCalif:idCBAprobadorMedidaCautelar');
    PrimeFaces.monitorDownload(start, stop);
}

function limpiarUpload(elemento) {
    if (elemento != null)
        elemento.clear();
}

function accionGuardarTipoDocumentalEntrega() {
    ocultarElemento('fDetalleEntrega:CBGuardarTipoDoc');
    PF('panelTiposDocumentales').collapse();
    mostrarElemento('fDetalleEntrega:idBotonDigitalizar');
}

function accionBotonGenerarDocumentoEntrega() {
    mostrarElemento('fDetalleEntrega:idVisualizarDocumento');
    ocultarElemento('fDetalleEntrega:idGuardarDocumento');
}

function accionBotonGenerarActa() {
	mostrarElemento('fDetalleEntrega:idGuardarActa');
	ocultarElemento('fDetalleEntrega:idGenerarActaNotificacion');
}

function ocultarElementos(...aav_elementosAOcultar) {
    if (isValidCollection(aav_elementosAOcultar)) {
        aav_elementosAOcultar.forEach((item) => {
            let ll_elemento;
            ll_elemento = document.getElementById(item);

            if (ll_elemento != null) {
                ll_elemento.style.display = 'none';
            }
        });
    }
}
function mostrarElementos(...aav_elementosAMostrar) {
    if (isValidCollection(aav_elementosAMostrar)) {
        aav_elementosAMostrar.forEach((item) => {
            let ll_elemento;
            ll_elemento = document.getElementById(item);

            if (ll_elemento != null) {
                ll_elemento.style.display = 'inline';
            }
        });
    }
}
function isValidCollection(av_coleccion) {
    return (av_coleccion != null) && (av_coleccion.length > 0);
}

function isValidString(av_string) {
    return (av_string != null) && (av_string.trim().length > 0);
}


function accionBotonDigitalizar() {
    ocultarElemento('fDetalleEntrega:idBotonDigitalizar');
    mostrarElemento('fDetalleEntrega:idBotonPadFirmas');
}

function accionBotonPadFirmas() {
    ocultarElemento('fDetalleEntrega:idBotonPadFirmas');
    mostrarElemento('fDetalleEntrega:idGenerarDocumentoEntrega');
}
function accionBotonPadFirmasNotificaciones() {
	ocultarElemento('fDetalleEntrega:idBotonPadFirmas');
	mostrarElemento('fDetalleEntrega:idGenerarActaNotificacion');
}

function accionBotonVisualizarDocumentoEntrega() {
    ocultarElemento('fDetalleEntrega:idVisualizarDocumento');
    mostrarElemento('fDetalleEntrega:idGuardarDocumento');
    PrimeFaces.monitorDownload(start, stop);
}

function accionGuardarActa() {
	mostrarElemento('fDetalleEntrega:idGenerarDocumentoEntrega');
	ocultarElemento('fDetalleEntrega:idGuardarActa');
}

function accionGuardarDocumentoEntrega() {
    ocultarElemento('fDetalleEntrega:idGenerarDocumentoEntrega');
    ocultarElemento('fDetalleEntrega:idGuardarDocumento');
}

function accionCambioTipoDocumentalEntrega() {
    mostrarElemento('fDetalleEntrega:CBGuardarTipoDoc');
    ocultarElemento('fDetalleEntrega:idBotonDigitalizar');
    ocultarElemento('fDetalleEntrega:idBotonPadFirmas');
    ocultarElemento('fDetalleEntrega:idVisualizarDocumento');
    ocultarElemento('fDetalleEntrega:idGenerarDocumentoEntrega');
    ocultarElemento('fDetalleEntrega:idGuardarDocumento');
}

function contarCaracteres(av_idTextArea, av_idCounter) {
    var lv_textoArea;
    var lv_numeroCaracteres;

    lv_textoArea = document.getElementById(av_idTextArea).value;
    lv_numeroCaracteres = lv_textoArea.length;

    document.getElementById(av_idCounter).innerHTML = lv_numeroCaracteres;
}

function stop() {
}

function contarLindero() {
    var textoArea = document
        .getElementById("idFormAntSistema:idProcesoAntiguoSistema:idITALinderos").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('idFormAntSistema:idProcesoAntiguoSistema:counterLinderosAntSis').innerHTML = numeroCaracteres;
}
function contarComplementacionND() {
    var textoArea = document.getElementById("fNofaDevolitiva:j_idt196").value;
    var numeroCaracteres = textoArea.length;
    document.getElementById('fNofaDevolitiva:idNumeroCaracteres').innerHTML = numeroCaracteres;
}
function contarLinderoDM() {
    var textoArea = document
        .getElementById("fdetalleDigitadorMasivo:idTVRegistroCalificacion:idLindero").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fdetalleDigitadorMasivo:idTVRegistroCalificacion:idNumeroCaracteres1').innerHTML = numeroCaracteres;
}
function contarObservacionesND() {
    var textoArea = document
        .getElementById("fNofaDevolitiva:idITAObservaciones").value;
    var numeroCaracteres = textoArea.length;
    document.getElementById('fNofaDevolitiva:idNumeroCaracteresObs').innerHTML = numeroCaracteres;
}

function contarLinderoRC2() {
    var textoArea = document
        .getElementById("fDetalleRegistroCalificacion:TvdetalleRegistroCalif:idLinderoDet").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fDetalleRegistroCalificacion:TvdetalleRegistroCalif:idNumeroCaracteres3').innerHTML = numeroCaracteres;
}
function contarLinderoRC3() {
    var textoArea = document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idlinder").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fRegistroCalif:idTVRegistroCalificacion:idNumeroCaracteres4').innerHTML = numeroCaracteres;
}
function contarLinderoRC4() {
    var textoArea = document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idlinder").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fRegistroCalif:idTVRegistroCalificacion:idNumeroCaracteres4').innerHTML = numeroCaracteres;
}
function contarLinderoRC() {
    var textoArea = document
        .getElementById("fDetalleRegistroCalificacion:idLindero").value;
    var numeroCaracteres = textoArea.length;
    document.getElementById('fDetalleRegistroCalificacion:idNumeroCaracteres1').innerHTML = numeroCaracteres;
}

function contarComplementacionRC() {
    var textoArea = document
        .getElementById("fDetalleRegistroCalificacion:idcomplementacionComplementacion").value;
    var numeroCaracteres = textoArea.length;
    document.getElementById('fDetalleRegistroCalificacion:idNumeroCaracteres2').innerHTML = numeroCaracteres;
}

function contarComplementacionDM() {
    var textoArea = document
        .getElementById("fdetalleDigitadorMasivo:idTVRegistroCalificacion:idcomplementacionComplementacion").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fdetalleDigitadorMasivo:idTVRegistroCalificacion:idNumeroCaracteres2').innerHTML = numeroCaracteres;
}

function contarComplementacionRC2() {
    var textoArea = document
        .getElementById("fDetalleRegistroCalificacion:TvdetalleRegistroCalif:idcomplementacionComplementacionView").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('fDetalleRegistroCalificacion:TvdetalleRegistroCalif:idNumeroCaracteres4').innerHTML = numeroCaracteres;
}
function contarComplementacionRC3() {
    var textoArea = document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idcomplement").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idNumeroCaracteres6").innerHTML = numeroCaracteres;
}
function contarComplementacionRC4() {
    var textoArea = document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idcomplementacionComplementacion").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById("fRegistroCalif:idTVRegistroCalificacion:idNumeroCaracteres7").innerHTML = numeroCaracteres;
}
function contarComplementacion() {
    var textoArea = document
        .getElementById("idFormAntSistema:idProcesoAntiguoSistema:idcomplementacionComplementacion").value;
    var numeroCaracteres = textoArea.length;
    document
        .getElementById('idFormAntSistema:idProcesoAntiguoSistema:counterComplementacionAntSis').innerHTML = numeroCaracteres;
}

function contarActualizarRC() {
    contarLinderoRC();
    contarComplementacionRC();
}

function contarActualizarRC2() {
    contarLinderoRC2();
    contarComplementacionRC2();
}

function contarActualizarRC3() {
    contarLinderoRC3();
    contarComplementacionRC3();
    contarComplementacionRC4();
}

function contarActualizarDM() {
    contarLinderoDM();
    contarComplementacionDM();
}
function contarActualizarND() {
    contarObservacionesND();
    contarComplementacionND();
}

function contarActualizar() {
    contarLindero();
    contarComplementacion();
}