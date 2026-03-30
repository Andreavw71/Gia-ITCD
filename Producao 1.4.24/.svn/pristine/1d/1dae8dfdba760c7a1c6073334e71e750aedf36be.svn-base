package br.gov.mt.sefaz.itc.util.seguranca;

import br.com.abaco.util.facade.FormAbaco;
import br.com.abaco.util.http.AbstractAbacoServlet;

import br.gov.mt.sefaz.acessoweb.util.captcha.background.BackgroundProducer;
import br.gov.mt.sefaz.acessoweb.util.captcha.background.BackgroundProducerFactory;
import br.gov.mt.sefaz.acessoweb.util.captcha.effect.EffectProducer;
import br.gov.mt.sefaz.acessoweb.util.captcha.effect.EffectProducerFactory;
import br.gov.mt.sefaz.acessoweb.util.captcha.text.TextProducer;
import br.gov.mt.sefaz.acessoweb.util.captcha.text.TextProducerFactory;
import br.gov.mt.sefaz.acessoweb.util.captcha.text.renderer.TextRenderer;
import br.gov.mt.sefaz.acessoweb.util.captcha.text.renderer.TextRendererFactory;

import java.awt.image.BufferedImage;

import java.io.OutputStream;

import javax.imageio.ImageIO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sefaz.mt.util.SefazValida;


/**
 * Servlet responsável por gerar a imagem com os caracteres.
 * @author Mônica Rodrigues da Silva
 * @author Fábio Vanzella
 * @version $Revision: 1.1 $
 */
public class ServletGeradorCaracteres extends AbstractAbacoServlet
{

	/**	
	 * Este método trabalha colaborativamente com o método redirecionar. 
	 * O método redirecionar determina a ação a ser tomada e processoRequest a executa.
	 * @param request
	 * @param response
	 * @implemented by Fábio Vanzella
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			int width = 0;
			int height = 0;
			int codeSize = 0;
			if (request.getParameter("width") != null && SefazValida.isSomenteDigito(request.getParameter("width")))
			{
				width = Integer.parseInt(request.getParameter("width"));
			}
			else
			{
				width = 200;
			}
			if (request.getParameter("height") != null && SefazValida.isSomenteDigito(request.getParameter("height")))
			{
				height = Integer.parseInt(request.getParameter("height"));
			}
			else
			{
				height = 65;
			}
			if (request.getParameter("codeSize") != null && SefazValida.isSomenteDigito(request.getParameter("codeSize")))
			{
				codeSize = Integer.parseInt(request.getParameter("codeSize"));
			}
			else
			{
				codeSize = 4;
			}
			TextProducerFactory textProducerFactory = new TextProducerFactory();
			TextProducer textProducer = textProducerFactory.getTextProducer();
			String code = textProducer.createText(codeSize);
			BufferedImage bufferedImage = createImage(width, height, code);
			getBuffer(request).setAttribute(FormAbaco.CODIGO_IMAGEM, code, request);
			response.setContentType("image/png");
			OutputStream outputStream = response.getOutputStream();
			ImageIO.write(bufferedImage, "png", outputStream);
			outputStream.close();
		}
		catch (Throwable exception)
		{
			exception.printStackTrace();
		}
	}

	/**
	 * Este método é responsável pela análise dos parâmetros e a
	 * tomada de decisão quanto ao controle do fluxo da aplicação.
	 * @param request
	 * @return int
	 * @implemented by Fábio Vanzella
	 */
	protected int redirecionar(HttpServletRequest request)
	{
		return REQUISICAO_VAZIA;
	}

	/**
	 * Método responsável por gerar a imagem com os caracteres.
	 * @param width
	 * @param height
	 * @param charCode
	 * @return BufferedImage
	 * @implemented by Fábio Vanzella
	 */
	private BufferedImage createImage(int width, int height, String charCode)
	{
		BufferedImage bufferedImage = new BufferedImage(width, height, 1);
		BackgroundProducerFactory backgroundProducerFactory = new BackgroundProducerFactory();
		EffectProducerFactory effectProducerFactory = new EffectProducerFactory();
		TextRendererFactory textRendererFactory = new TextRendererFactory();
		BackgroundProducer backgroundProducer = backgroundProducerFactory.getBackgroundProducer();
		backgroundProducer.addBackground(bufferedImage);
		TextRenderer textRenderer = textRendererFactory.getTextRenderer();
		textRenderer.addText(bufferedImage, charCode);
		EffectProducer effectProducer = effectProducerFactory.getEffectProducer();
		effectProducer.addEffect(bufferedImage);
		return bufferedImage;
	}
}
