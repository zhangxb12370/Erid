package com.parsec.tool;

import com.parsec.tool.ReturnJson;
import com.parsec.universal.utils.StringUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

/**
 * 生成以及验证输入的验证码
 * Created by zxb on 2016/7/18.
 */
@RestController
public class CheckCodeUtil {

    private static final long serialVersionUID = 8207495979135996372L;
    // 验证码图片的宽度。
    private static int width = 80;
    // 验证码图片的高度。
    private static int height = 25;
    // 验证码字符个数
    private static int codeCount = 4;
    private static int x = width / (codeCount + 1);
    // 字体高度
    private static int fontHeight = height - 2;
    private static int codeY = height - 4;

    /**
     * 初始化验证图片属性
     */
//    private static void init() throws ServletException {
//        // 从web.xml中获取初始信息
//        // 宽度
//        String strWidth ="80";
//        // 高度
//        String strHeight ="25";
//        // 字符个数
//        String strCodeCount = "4";
//        // 将配置的信息转换成数值
//        try {
//            if (strWidth != null && strWidth.length() != 0) {
//                width = Integer.parseInt(strWidth);
//            }
//            if (strHeight != null && strHeight.length() != 0) {
//                height = Integer.parseInt(strHeight);
//            }
//            if (strCodeCount != null && strCodeCount.length() != 0) {
//                codeCount = Integer.parseInt(strCodeCount);
//            }
//        } catch (NumberFormatException e) {
//        }
//        x = width / (codeCount + 1);
//        fontHeight = height - 2;
//        codeY = height - 4;
//    }

    /**
     * 获取验证码
     * @param req
     * @param resp
     * @param userRandomCode 随机数
     * @throws ServletException
     * @throws IOException
     */
    public static void getCode(HttpServletRequest req, HttpServletResponse resp, String userRandomCode)
            throws IOException {
        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        // 创建字体，字体的大小应该根据图片的高度来定。
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        // 设置字体。
        g.setFont(font);
        // 画边框。
        g.setColor(Color.BLACK);
        g.drawRect(0, 0, width - 1, height - 1);
        // 随机产生160条干扰线，使图象中的认证码不易被其它程序探测到。
        g.setColor(Color.BLACK);
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
        // randomCode用于保存随机产生的验证码，以便用户登录后进行验证。
        StringBuffer randomCode = new StringBuffer();
        int red = 0, green = 0, blue = 0;

        String codeNum = StringUtil.generateCode(4, 0);//得到四位验证码

        String[] codeSplit = codeNum.split("");

        req.getServletContext().setAttribute(userRandomCode,codeNum);
        // 随机产生codeCount数字的验证码。
        for (int i = 0; i < codeCount; i++) {
            // 得到随机产生的验证码数字。
            String strRand = codeSplit[i];
            // 产生随机的颜色分量来构造颜色值，这样输出的每位数字的颜色值都将不同。
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            // 用随机产生的颜色将验证码绘制到图像中。
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            randomCode.append(strRand);
        }

        // 禁止图像缓存。
        resp.setHeader("Pragma", "no-cache");
        resp.setHeader("Cache-Control", "no-cache");
        resp.setDateHeader("Expires", 0);
        resp.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = resp.getOutputStream();
        ImageIO.write(buffImg, "jpeg", sos);
        sos.close();
    }

    /**
     * 验证验证码
     *
     * @param random       八位随机数
     * @param validateCode 验证码
     * @return 是否验证通过
     */
    public static boolean validateCode(HttpServletRequest request,String random, String validateCode) {
        return validateCode.equalsIgnoreCase(String.valueOf(request.getServletContext().getAttribute(random)))?true:false;
    }
}
