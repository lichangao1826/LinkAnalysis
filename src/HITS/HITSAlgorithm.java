package HITS;

public class HITSAlgorithm {
  private Graph graph;
  private int N;

  public HITSAlgorithm(Graph graph) {
    this.graph = graph;
    this.N = graph.getSize();
  }

  /**
   * 计算 PageRank
   *
   * @param round 计算的轮次
   * @param alpha 迭代结束条件
   */
  public Graph calcHITS(int round, float alpha) {
    if (round > 0) {
      float[] oldAuthorityVertices = new float[N];
      float[] oldHubVertices = new float[N];
      float[] newAuthorityVertices = new float[N];
      float[] newHubVertices = new float[N];
      for (int i = 0; i < graph.getSize(); i++) {
        oldAuthorityVertices[i] = graph.getAuthorityVertices()[i];
        oldHubVertices[i] = graph.getHubVertices()[i];
      }

      float normAuthority = 0;
      float normHub = 0;
      float[][] matrix = graph.getMatrix();
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          if (matrix[i][j] != Float.POSITIVE_INFINITY) {
            newHubVertices[i] += oldAuthorityVertices[j];
            normHub += Math.pow(newHubVertices[i], 2);
          }
          if (matrix[j][i] != Float.POSITIVE_INFINITY) {
            newAuthorityVertices[i] += oldHubVertices[j];
            normAuthority += Math.pow(newAuthorityVertices[i], 2);
          }
        }
        normHub = (float) Math.sqrt(normHub);
        graph.saveVertexHub(i, newHubVertices[i] / normHub);

        normAuthority = (float) Math.sqrt(normAuthority);
        graph.saveVertexAuthority(i, newAuthorityVertices[i]);
      }

      if (checkFinish(oldAuthorityVertices, newAuthorityVertices, alpha)) {
        graph.pageRankFinish = true;
        return graph;
      }
      return calcHITS(round - 1, alpha);
    }
    return graph;
  }

  private boolean checkFinish(float[] oldVertices, float[] newVertices, float alpha) {
    for (int i = 0; i < oldVertices.length; i++) {
      System.out.println(Math.abs(newVertices[i] - oldVertices[i]));
      if (Math.abs(newVertices[i] - oldVertices[i]) >= alpha) {
        System.out.println();
        return false;
      }
    }
    System.out.println();
    return true;
  }
}
