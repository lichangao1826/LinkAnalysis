package PageRank;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;

import java.lang.InterruptedException;

public class AlgorithmVisualizationHelper {

  private AlgorithmVisualizationHelper() {}

  public static final Color Red = new Color(0xF44336);
  public static final Color Pink = new Color(0xE91E63);
  public static final Color Purple = new Color(0x9C27B0);
  public static final Color DeepPurple = new Color(0x673AB7);
  public static final Color Indigo = new Color(0x3F51B5);
  public static final Color Blue = new Color(0x2196F3);
  public static final Color LightBlue = new Color(0x03A9F4);
  public static final Color Cyan = new Color(0x00BCD4);
  public static final Color Teal = new Color(0x009688);
  public static final Color Green = new Color(0x4CAF50);
  public static final Color LightGreen = new Color(0x8BC34A);
  public static final Color Lime = new Color(0xCDDC39);
  public static final Color Yellow = new Color(0xFFEB3B);
  public static final Color Amber = new Color(0xFFC107);
  public static final Color Orange = new Color(0xFF9800);
  public static final Color DeepOrange = new Color(0xFF5722);
  public static final Color Brown = new Color(0x795548);
  public static final Color Grey = new Color(0x9E9E9E);
  public static final Color BlueGrey = new Color(0x607D8B);
  public static final Color Black = new Color(0x000000);
  public static final Color White = new Color(0xFFFFFF);

  public static void strokeCircle(Graphics2D g, int x, int y, int r) {

    Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    g.draw(circle);
  }

  public static void fillCircle(Graphics2D g, int x, int y, int r) {

    Ellipse2D circle = new Ellipse2D.Double(x - r, y - r, 2 * r, 2 * r);
    g.fill(circle);
  }

  public static void strokeRectangle(Graphics2D g, int x, int y, int w, int h) {

    Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
    g.draw(rectangle);
  }

  public static void fillRectangle(Graphics2D g, int x, int y, int w, int h) {

    Rectangle2D rectangle = new Rectangle2D.Double(x, y, w, h);
    g.fill(rectangle);
  }

  public static void setColor(Graphics2D g, Color color) {
    g.setColor(color);
  }

  public static void setStrokeWidth(Graphics2D g, int strokeWidth) {
    g.setStroke(new BasicStroke(strokeWidth, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
  }

  public static void pause(int t) {
    try {
      Thread.sleep(t);
    } catch (InterruptedException e) {
      System.out.println("Error sleeping");
    }
  }

  public static void putImage(Graphics2D g, int x, int y, String imageURL) {

    ImageIcon icon = new ImageIcon(imageURL);
    Image image = icon.getImage();

    g.drawImage(image, x, y, null);
  }

  public static void drawText(Graphics2D g, String text, int x, int y) {

    if (text == null) throw new IllegalArgumentException("Text is null in drawText function!");

    FontMetrics metrics = g.getFontMetrics();
    int w = metrics.stringWidth(text);
    int h = metrics.getDescent();
    g.drawString(text, x - w / 2, y + h);
  }

  public static void drawLine(Graphics2D g, int fromX, int fromY, int toX, int toY) {
    g.drawLine(fromX, fromY, toX, toY);
  }

  public static void drawArrowLine(
      Graphics2D g, int fromX, int fromY, int toX, int toY, int fromR, int toR) {
    float[] position = convertXY(fromX, fromY, toX, toY, fromR, toR);
    fromX = (int) position[0];
    fromY = (int) position[1];
    toX = (int) position[2];
    toY = (int) position[3];
    g.drawLine(fromX, fromY, toX, toY);

    float H = 15; // 箭头高度
    float L = 5; // 底边的一半
    float angle = (float) Math.atan(L / H); // 箭头角度
    float arrowLen = (float) Math.sqrt(L * L + H * H); // 箭头的长度
    float[] arrXY_1 = rotateVec(toX - fromX, toY - fromY, angle, arrowLen);
    float[] arrXY_2 = rotateVec(toX - fromX, toY - fromY, -angle, arrowLen);
    float x1 = toX - arrXY_1[0];
    float y1 = toY - arrXY_1[1];
    float x2 = toX - arrXY_2[0];
    float y2 = toY - arrXY_2[1];
    GeneralPath triangle = new GeneralPath();
    triangle.moveTo(toX, toY);
    triangle.lineTo(x1, y1);
    triangle.moveTo(toX, toY);
    triangle.lineTo(x2, y2);
    triangle.closePath();
    g.draw(triangle);
  }

  private static float[] convertXY(int fromX, int fromY, int toX, int toY, int fromR, int toR) {
    float d = (float) Math.sqrt(Math.pow(fromX - toX, 2) + Math.pow(fromY - toY, 2));
    float[] result = new float[4];
    result[0] = fromX - fromR * (fromX - toX) / d;
    result[1] = fromY - fromR * (fromY - toY) / d;
    result[2] = toX - toR * (toX - fromX) / d;
    result[3] = toY - toR * (toY - fromY) / d;
    return result;
  }

  private static float[] rotateVec(int px, int py, float ang, float newLen) {
    float[] mathStr = new float[2];
    // 矢量旋转函数，参数含义分别是x分量、y分量、旋转角、是否改变长度、新长度
    double vx = px * Math.cos(ang) - py * Math.sin(ang);
    double vy = px * Math.sin(ang) + py * Math.cos(ang);
    double d = Math.sqrt(vx * vx + vy * vy);
    vx = vx / d * newLen;
    vy = vy / d * newLen;
    mathStr[0] = (float) vx;
    mathStr[1] = (float) vy;
    return mathStr;
  }
}
