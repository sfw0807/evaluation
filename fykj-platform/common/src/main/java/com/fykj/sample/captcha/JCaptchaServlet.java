/**
 * 
 */
package com.fykj.sample.captcha;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhengzw
 */
public class JCaptchaServlet extends HttpServlet {

	private static final long serialVersionUID = -8705767934794052840L;

	private static final int IMG_HEIGHT = 30;
	private static final int IMG_WIDTH = 146;

	private final Font font = new Font("宋体", Font.BOLD, 18);

	private static final int DISTURB_LINE_SIZE = 15;
	private Random random = new Random();

	private int xyresult;
	private String randomString;

	private static final String CVCNUMBERS = "\u96F6\u4E00\u4E8C\u4E09\u56DB\u4E94\u516D\u4E03\u516B\u4E5D\u5341\u4E58\u9664\u52A0\u51CF";
	// data operator
	private static final Map<String, Integer> OPMap = new HashMap<String, Integer>();

	static {
		OPMap.put("*", 11);
		OPMap.put("/", 12);
		OPMap.put("+", 13);
		OPMap.put("-", 14);
	}

	@Override
	public void init() throws ServletException {
		super.init();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			BufferedImage image = new BufferedImage(IMG_WIDTH, IMG_HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics g = image.getGraphics();
			g.setColor(Color.WHITE);

			g.fillRect(0, 0, IMG_WIDTH, IMG_HEIGHT);
			g.setColor(getRandomColor(200, 250));
			g.drawRect(0, 0, IMG_WIDTH - 2, IMG_HEIGHT - 2);

			g.setColor(getRandomColor(110, 133));

			for (int i = 0; i < DISTURB_LINE_SIZE; i++) {
				drawDisturbLine1(g);
				drawDisturbLine2(g);
			}

			getRandomMathString();

			request.getSession().setAttribute("validate_captcha_code", xyresult);

			StringBuffer logsu = new StringBuffer();
			for (int j = 0, k = randomString.length(); j < k; j++) {
				int chid = 0;
				if (j == 1) {
					chid = OPMap.get(String.valueOf(randomString.charAt(j)));
				} else {
					chid = Integer.parseInt(String.valueOf(randomString.charAt(j)));
				}
				String ch = String.valueOf(CVCNUMBERS.charAt(chid));
				logsu.append(ch);
				drawRandomString((Graphics2D) g, ch, j);
			}
			// = ?
			drawRandomString((Graphics2D) g, "\u7B49\u4E8E\uFF1F", 3);

			response.setHeader("Pragma", "no-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.setDateHeader("Expires", 0);

			response.setContentType("image/jpeg");

			ServletOutputStream sos = response.getOutputStream();

			ImageIO.write(image, "jpeg", sos);

			sos.flush();
			sos.close();
			sos = null;
			response.flushBuffer();
		} catch(Exception e) {
			throw new RuntimeException("验证码生成错误!");
		}
	}

	public Color getRandomColor(int fc, int bc) {
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}

		int r = fc + random.nextInt(bc - fc - 16);
		int g = fc + random.nextInt(bc - fc - 14);
		int b = fc + random.nextInt(bc - fc - 18);
		return new Color(r, g, b);
	}

	/**
	 * Draw line interference
	 * 
	 * @param g
	 *            Graphics
	 */
	public void drawDisturbLine1(Graphics g) {
		int x1 = random.nextInt(IMG_WIDTH);
		int y1 = random.nextInt(IMG_HEIGHT);
		int x2 = random.nextInt(13);
		int y2 = random.nextInt(15);

		g.drawLine(x1, y1, x1 + x2, y1 + y2);
	}

	/**
	 * Draw line interference
	 * 
	 * @param g
	 *            Graphics
	 */
	public void drawDisturbLine2(Graphics g) {
		int x1 = random.nextInt(IMG_WIDTH);
		int y1 = random.nextInt(IMG_HEIGHT);
		int x2 = random.nextInt(13);
		int y2 = random.nextInt(15);

		g.drawLine(x1, y1, x1 - x2, y1 - y2);
	}

	private void getRandomMathString() {
		int xx = random.nextInt(10);
		int yy = random.nextInt(10);

		StringBuilder suChinese = new StringBuilder();
		int Randomoperands = (int) Math.round(Math.random() * 2);
		if (Randomoperands == 0) {
			this.xyresult = yy * xx;
			suChinese.append(yy);
			suChinese.append("*");
			suChinese.append(xx);
		} else if (Randomoperands == 1) {
			if (!(xx == 0) && yy % xx == 0) {
				this.xyresult = yy / xx;
				suChinese.append(yy);
				suChinese.append("/");
				suChinese.append(xx);
			} else {
				this.xyresult = yy + xx;
				suChinese.append(yy);
				suChinese.append("+");
				suChinese.append(xx);
			}
			// subtraction
		} else if (Randomoperands == 2) {
			this.xyresult = yy - xx;
			suChinese.append(yy);
			suChinese.append("-");
			suChinese.append(xx);
			// add
		} else {
			this.xyresult = yy + xx;
			suChinese.append(yy);
			suChinese.append("+");
			suChinese.append(xx);
		}
		this.randomString = suChinese.toString();
	}

	public void drawRandomString(Graphics2D g, String randomvcch, int i) throws Exception {
		g.setFont(font);
		int rc = random.nextInt(255);
		int gc = random.nextInt(255);
		int bc = random.nextInt(255);
		g.setColor(new Color(rc, gc, bc));
		int x = random.nextInt(3);
		int y = random.nextInt(2);
		g.translate(x, y);
		int degree = new Random().nextInt() % 15;
		g.rotate(degree * Math.PI / 180, 5 + i * 25, 20);
		g.drawString(randomvcch, 5 + i * 25, 20);
		g.rotate(-degree * Math.PI / 180, 5 + i * 25, 20);
	}
}
