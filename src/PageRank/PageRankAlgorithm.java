package PageRank;

public class PageRankAlgorithm {
  private float[][] TPMatrix;
  private Graph graph;
  private int N;

  public PageRankAlgorithm(Graph graph) {
    this.graph = graph;
    initPTMatrix(); // 初始化转移概率矩阵
  }

  /**
   * 计算 PageRank
   *
   * @param round 计算的轮次
   * @param alpha 迭代结束条件
   */
  public Graph calcPageRank(int round, float alpha) {
    if (round > 0) {
      float[] oldVertices = new float[N];
      float[] newVertices = new float[N];
      for (int i = 0; i < graph.getSize(); i ++) {
        oldVertices[i] = graph.getVertices()[i];
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (TPMatrix[j][i] != 0) {
            newVertices[i] += oldVertices[j] * TPMatrix[j][i];
          }
        }
        graph.saveVertex(i, newVertices[i]);
      }
      if (checkFinish(oldVertices, newVertices, alpha)) {
        graph.pageRankFinish = true;
        return graph;
      }
      return calcPageRank(round - 1, alpha);
    }
    return graph;
  }

  private boolean checkFinish(float[] oldVertices, float[] newVertices, float alpha) {
    for (int i = 0; i < oldVertices.length; i ++) {
      System.out.println(Math.abs(newVertices[i] - oldVertices[i]));
      if (Math.abs(newVertices[i] - oldVertices[i]) >= alpha) {
        return false;
      }
    }
    return true;
  }

  private void initPTMatrix() {
    N = graph.getSize();

    TPMatrix = new float[N][N];
    float[][] matrix = graph.getMatrix();

    for (int i = 0; i < N; i++) {
      float prop = (float) 1.0 / graph.getOutLinkCount(i);
      for (int j = 0; j < N; j++) {
        if (matrix[i][j] != Float.POSITIVE_INFINITY) {
          TPMatrix[i][j] = prop;
        }
      }
    }
  }
}
