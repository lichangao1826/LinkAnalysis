package PageRank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class AlgorithmVisualizer {

  private Graph graph; // 数据
  private AlgorithmFrame frame; // 视图

  public AlgorithmVisualizer(int sceneWidth, int sceneHeight) {

    // 初始化数据
    graph = new Graph();
    graph.initSimpleGraph();
//    graph.initTrapGraph();

    // 初始化视图
    EventQueue.invokeLater(
        () -> {
          frame = new AlgorithmFrame("PageRank", sceneWidth, sceneHeight);

          // TODO: 根据情况决定是否加入键盘鼠标事件监听器
          frame.addKeyListener(new AlgorithmKeyListener());
          frame.addMouseListener(new AlgorithmMouseListener());

          new Thread(
                  () -> {
                    run();
                  })
              .start();
        });
  }

  // 动画逻辑
  private void run() {
    while (true) {
      frame.render(graph);
      AlgorithmVisualizationHelper.pause(20);
    }
  }

  // TODO: 根据情况决定是否实现键盘鼠标等交互事件监听器类
  private class AlgorithmKeyListener extends KeyAdapter {

    @Override
    public void keyReleased(KeyEvent event) {
      if (event.getKeyChar() == ' ') {
        PageRankAlgorithm pageRank = new PageRankAlgorithm(graph);
        graph = pageRank.calcPageRank(1, 0.00001f);
      }
    }
  }

  private class AlgorithmMouseListener extends MouseAdapter {}

  public static void main(String[] args) {
    int sceneWidth = 800;
    int sceneHeight = 800;

    // TODO: 根据需要设置其他参数，初始化 visualizer
    AlgorithmVisualizer visualizer = new AlgorithmVisualizer(sceneWidth, sceneHeight);
  }
}
