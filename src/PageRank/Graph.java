package PageRank;

public class Graph {
  private float[][] matrix; // 邻接矩阵
  private float[] vertices; // 顶点权重
  private int[] outLinks; // 顶点出链数
  private int[][] position = {
    {400, 400}, {550, 100}, {700, 400}, {550, 700}, {250, 700}, {100, 400}, {250, 100}
  }; // 顶点位置信息
  private int N = 7; // 默认顶点数

  public boolean pageRankFinish = false;

  public Graph() {
    matrix = new float[N][N];
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        matrix[i][j] = Float.POSITIVE_INFINITY;
      }
    }
    vertices = new float[N];
    outLinks = new int[N];
  }

  public int getSize() {
    return N;
  }

  public float[][] getMatrix() {
    return matrix;
  }

  public float[] getVertices() {
    return vertices;
  }

  public int[] getVertexPosition(int v) {
    return position[v];
  }

  public int getOutLinkCount(int v) {
    return outLinks[v];
  }

  public void saveVertex(int v, float score) {
    vertices[v] = score;
  }

  public void saveEdge(int v1, int v2, float weight) {
    outLinks[v1] += 1;
    matrix[v1][v2] = weight;
  }

  public void initSimpleGraph() {
    for (int i = 0; i < N; i++) {
      saveVertex(i, 1);
    }
    saveEdge(0, 1, 0);
    saveEdge(0, 2, 0);
    saveEdge(0, 3, 0);
    saveEdge(0, 4, 0);
    saveEdge(0, 6, 0);
    saveEdge(1, 0, 0);
    saveEdge(2, 0, 0);
    saveEdge(2, 1, 0);
    saveEdge(3, 1, 0);
    saveEdge(3, 2, 0);
    saveEdge(3, 4, 0);
    saveEdge(4, 0, 0);
    saveEdge(4, 2, 0);
    saveEdge(4, 3, 0);
    saveEdge(4, 5, 0);
    saveEdge(5, 0, 0);
    saveEdge(5, 4, 0);
    saveEdge(6, 4, 0);
  }

  public void initTrapGraph() {
    for (int i = 0; i < N; i++) {
      saveVertex(i, 1);
    }
    saveEdge(0, 6, 0);
    saveEdge(1, 0, 0);
    saveEdge(2, 0, 0);
    saveEdge(2, 1, 0);
    saveEdge(3, 1, 0);
    saveEdge(3, 2, 0);
    saveEdge(3, 4, 0);
    saveEdge(4, 0, 0);
    saveEdge(4, 2, 0);
    saveEdge(4, 3, 0);
    saveEdge(4, 5, 0);
    saveEdge(5, 0, 0);
    saveEdge(6, 5, 0);
  }
}
