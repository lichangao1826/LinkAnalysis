package PageRank;

import java.util.Arrays;

public class Graph {
  private double[][] matrix; // 邻接矩阵
  private double[] vertices; // 顶点信息
  private int[][] position = {
    {400, 400}, {550, 100}, {700, 400}, {550, 700}, {250, 700}, {100, 400}, {250, 100}
  }; // 顶点位置信息
  private int N = 7; // 默认顶点数

  public Graph() {
    matrix = new double[N][N];
    for (int i = 0; i < N; i ++) {
      for (int j = 0; j < N; j ++) {
        matrix[i][j] = Double.POSITIVE_INFINITY;
      }
    }
    vertices = new double[N];
  }

  public int getSize() {
    return N;
  }

  public double[][] getMatrix() {
    return matrix;
  }

  public double[] getVertices() {
    return vertices;
  }

  public int[] getVertexPosition(int v) {
    return position[v];
  }

  private void saveVertex(int v, double score) {
    vertices[v] = score;
  }

  private void saveEdge(int v1, int v2, double weight) {
    matrix[v1][v2] = weight;
  }

  public void initGraph() {
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
}
