package br.com.pjworkana.converter;



import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.pjworkana.dao.FornecedorDAO;
import br.com.pjworkana.domain.Fornecedor;

@FacesConverter("fornecedorConverter")
//ESSA CLASSE SERVE PARA CONVERTER OS NUMEROS PASSADOS PELO PRIMEFACES, POIS AS CASAS DECIMAIS SÃO COM " . " E NÃO COM " ," E NÃO SÃO NUMERICOS, SÃO DO TIPO TEXTO
public class FornecedorConverter implements Converter {
	
	public Object getAsObject(FacesContext facesContext, UIComponent component,
			String valor) { //FACESCONTEXT É O CONTEXTO DO OBJETO E UICOMPONENT É DO COMPONENTE DO PRIMEFACES
		try {
			//PEGA O CÓDIGO E CONVERTE PARA INTEIRO
			Integer codigo = Integer.parseInt(valor);

			//CHAMA O DAO E MANDA BUSCAR PELO CÓDIGO
			FornecedorDAO fornecedorDAO = new FornecedorDAO();
			Fornecedor fornecedor = fornecedorDAO.buscarPorCodigo(codigo);

			return fornecedor;
		} catch (RuntimeException ex) {
			return null;
		}
	}

	
	public String getAsString(FacesContext facesContext, UIComponent component,
			Object objeto) {
		try {
			Fornecedor fornecedor = (Fornecedor) objeto;

			Integer codigo = fornecedor.getCodigo();

			return codigo.toString();
		} catch (RuntimeException ex) {
			return null;
		}
	}

	
}
