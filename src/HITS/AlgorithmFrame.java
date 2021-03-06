package HITS;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

public class AlgorithmFrame extends JFrame {

  private int canvasWidth;
  private int canvasHeight;

  public AlgorithmFrame(String title, int canvasWidth, int canvasHeight) {

    super(title);

    this.canvasWidth = canvasWidth;
    this.canvasHeight = canvasHeight;

    AlgorithmCanvas canvas = new AlgorithmCanvas();
    setContentPane(canvas);

    setResizable(false);
    pack();

    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setVisible(true);
  }

  public AlgorithmFrame(String title) {
    this(title, 1024, 768);
  }

  public int getCanvasWidth() {
    return canvasWidth;
  }

  public int getCanvasHeight() {
    return canvasHeight;
  }

  private Graph graph;

  public void render(Graph graph) {
    this.graph = graph;
    repaint();
  }

  private class AlgorithmCanvas extends JPanel {

    @Override
    public void paintComponent(Graphics g) {

      super.paintComponent(g);
      Graphics2D g2d = (Graphics2D) g;

      // 抗锯齿
      RenderingHints hints =
          new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
      g2d.addRenderingHints(hints);

      // 具体绘制
      AlgorithmVisualizationHelper.setStrokeWidth(g2d, 3);
      DecimalFormat decimalFormat = new DecimalFormat("0.00");

      int minR = 20;
      int baseR = 30;
      float[] vertices = graph.getAuthorityVertices();
      for (int i = 0; i < graph.getSize(); i++) {
        int[] position = graph.getVertexPosition(i);
        int r = (int) vertices[i] * baseR < minR ? minR : (int) vertices[i] * baseR;

        AlgorithmVisualizationHelper.strokeCircle(g2d, position[0], position[1], r);
        String score = decimalFormat.format(vertices[i]);
        AlgorithmVisualizationHelper.drawText(g2d, score, position[0], position[1]);
      }

      float[][] matrix = graph.getMatrix();
      for (int i = 0; i < graph.getSize(); i++) {
        for (int j = 0; j < graph.getSize(); j++) {
          if (matrix[i][j] != Float.POSITIVE_INFINITY) {
            int fromX = graph.getVertexPosition(i)[0];
            int fromY = graph.getVertexPosition(i)[1];
            int toX = graph.getVertexPosition(j)[0];
            int toY = graph.getVertexPosition(j)[1];
            int fromR = (int) vertices[i] * baseR < minR ? minR : (int) vertices[i] * baseR;
            int toR = (int) vertices[j] * baseR < minR ? minR : (int) vertices[j] * baseR;
            AlgorithmVisualizationHelper.drawArrowLine(g2d, fromX, fromY, toX, toY, fromR, toR);
          }
        }
      }

      if (graph.pageRankFinish) {
        AlgorithmVisualizationHelper.setColor(g2d, AlgorithmVisualizationHelper.Red);
        AlgorithmVisualizationHelper.drawText(g2d, "迭代结束！", 720, 720);
      }
    }

    @Override
    public Dimension getPreferredSize() {
      return new Dimension(canvasWidth, canvasHeight);
    }
  }
}
