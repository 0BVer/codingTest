package dfs.q1012;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int test = Integer.parseInt(br.readLine());
        for (int t = 0; t < test; t++) {

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int node = Integer.parseInt(st.nextToken());

            // 그리드에 노드 표시
            boolean[][] grid = new boolean[x][y];
            for (int i = 0; i < node; i++) {
                st = new StringTokenizer(br.readLine());
                int tempX = Integer.parseInt(st.nextToken());
                int tempY = Integer.parseInt(st.nextToken());
                grid[tempX][tempY] = true;
            }

            int count = 0;
            for (int i = 0; i < x; i++) {
                for (int j = 0; j < y; j++) {
                    if (grid[i][j]) {
                        count++;
                        dfs(i, j, grid, x, y);
                    }
                }
            }
            result.append(count);
            result.append("\n");
        }
        bw.write(result.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int x, int y, boolean[][] grid, int xSize, int ySize) {
        if (grid[x][y]) {
            grid[x][y] = false;
            if (x - 1 > -1) {
                dfs(x - 1, y, grid, xSize, ySize);
            }
            if (x + 1 < xSize) {
                dfs(x + 1, y, grid, xSize, ySize);
            }
            if (y - 1 > -1) {
                dfs(x, y - 1, grid, xSize, ySize);
            }
            if (y + 1 < ySize) {
                dfs(x, y + 1, grid, xSize, ySize);
            }
        }
    }
}
