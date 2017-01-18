package com.parsec.tool;

import com.parsec.universal.utils.StringUtil;

import javax.imageio.ImageIO;
import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;

/**
 * @author ymr
 * @version 1.0.0
 */
public class CheckCoderTool {

    private static Map<String, String> map = new HashMap<>();

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
     * 十分钟后删除map对应的key值
     *
     * @param key map的键
     */
    private static void removeKey(String key) {
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                map.remove(key);
            }
        }, 1000 * 60 * 10);
    }

    /**
     * 生成验证码,将验证码写入map中
     *
     * @param randomStr 前端传入的随机数
     * @return 生成的验证码
     */
    private static String writeMap(String randomStr) {
        String codeNum = StringUtil.generateCode(4, 0);//得到四位验证码
        map.put(randomStr, codeNum);
        removeKey(randomStr);
        return codeNum;
    }

    public static void getCode(HttpServletResponse resp, String randomStr)
            throws IOException {

        String codeNum = writeMap(randomStr);
        String[] codeSplit = codeNum.split("");

        // 定义图像buffer
        BufferedImage buffImg = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        // 创建一个随机数生成器类
        Random random = new Random();
        // 将图像填充为白色
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
        StringBuilder randomCode = new StringBuilder();
        int red, green, blue;

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
            // 将产生的四个随机数组合在一起。
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
    public static boolean validateCode(String random, String validateCode) {
        Object mapCode = map.get(random);
        return mapCode != null && mapCode.toString().equalsIgnoreCase(validateCode);
    }
}