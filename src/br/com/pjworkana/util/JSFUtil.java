package br.com.pjworkana.util;

import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;




import javax.servlet.http.HttpServletResponse;

import org.primefaces.context.RequestContext;






//A CLASSE JSFUTIL SERVE PARA CHAMAR AS MENSAGEM QUE SERÃO EXIBIDAS NO SISTEMA,LIMPAR OS CAMPOS DA TELA E PASSAR O PARAMETRO PARA AS OPERAÇÕES
public class JSFUtil {
	public static void adicionarMensagemSucesso(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,
				mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public static void adicionarMensagemErro(String mensagem) {
		FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR,
				mensagem, mensagem);
		FacesContext contexto = FacesContext.getCurrentInstance();
		contexto.addMessage(null, msg);
	}

	public static void limparCampos(String formPanel) {
		RequestContext.getCurrentInstance().reset(formPanel);
	}

	

	public static String getParam(String nome) {
		FacesContext facesContext = FacesContext.getCurrentInstance();

		ExternalContext externalContext = facesContext.getExternalContext();

		Map<String, String> parametros = externalContext
				.getRequestParameterMap();

		String valor = parametros.get(nome);
		return valor;
	}
	
	// Método requerido para geração de relatórios
		public static HttpServletResponse getResponse() {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			HttpServletResponse resp = (HttpServletResponse) externalContext
					.getResponse();
			return resp;
		}

		// Método requerido para geração de relatórios
		public static void responseComplete() {
			FacesContext.getCurrentInstance().renderResponse();
			FacesContext.getCurrentInstance().responseComplete();
		}

}
