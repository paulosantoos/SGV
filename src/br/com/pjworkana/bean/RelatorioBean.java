package br.com.pjworkana.bean;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;




import br.com.pjworkana.dao.ConectaBancoMVC;
import br.com.pjworkana.util.JSFUtil;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

@ManagedBean(name = "RelatorioMB")
@ViewScoped
public class RelatorioBean {
	
	ConectaBancoMVC c = new ConectaBancoMVC();

	
	public void gerarRelatorioProduto() {

		try {

			InputStream caminho = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/reports/RelProduto.jasper");

			// JasperReport report =
			// JasperCompileManager.compileReport(caminho);

			JasperPrint print = JasperFillManager.fillReport(caminho, null,
					c.obterConexao());

			byte[] bytes = JasperExportManager.exportReportToPdf(print);
			if (bytes != null) {
				HttpServletResponse response = JSFUtil.getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"attachment;filename=Relatorio de Produto.pdf");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.flushBuffer();
				JSFUtil.responseComplete();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public void gerarRelatorioVenda() {

		try {

			InputStream caminho = FacesContext.getCurrentInstance()
					.getExternalContext()
					.getResourceAsStream("/reports/RelVenda.jasper");

			// JasperReport report =
			// JasperCompileManager.compileReport(caminho);

			JasperPrint print = JasperFillManager.fillReport(caminho, null,
					c.obterConexao());

			byte[] bytes = JasperExportManager.exportReportToPdf(print);
			if (bytes != null) {
				HttpServletResponse response = JSFUtil.getResponse();
				response.setContentType("application/pdf");
				response.setHeader("Content-disposition",
						"attachment;filename=Relatorio de venda.pdf");
				response.setContentLength(bytes.length);
				response.getOutputStream().write(bytes, 0, bytes.length);
				response.getOutputStream().flush();
				response.flushBuffer();
				JSFUtil.responseComplete();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	
	
}
