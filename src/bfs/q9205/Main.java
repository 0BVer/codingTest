package bfs.q9205;


import java.io.*;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    static int c;
    static String res;
    static N[] ns;
    static Map<Integer, List<Integer>> graph;

    public static void main(String[] args) throws Exception {

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            StringBuilder result = new StringBuilder();

            c = Integer.parseInt(br.readLine());
            ns = new N[c + 2];
            graph = new HashMap<>(c + 2);

            setNode(0, 0);
            for (int j = 1; j < c + 1; j++) setNode(j, 1); //편의점
            setNode(c + 1, 2); //락페

            res = "sad";
            Set<Integer> visited = new HashSet<>(c + 2);
            Deque<Integer> deque = new ArrayDeque<>(c + 2);
            deque.add(0);

            while (!deque.isEmpty()) {
                int n = deque.poll();
                if (ns[n].type == 2) {
                    res = "happy";
                    break;
                }
                visited.add(n);
                if (graph.containsKey(n))
                    graph.get(n).forEach(node -> {
                        if (!visited.contains(node)) {
                            deque.add(node);
                        }
                    });
            }
            result.append(res);
            bw.write(result.toString());
            bw.flush();
        }
        br.close();
        bw.close();
    }

    private static void setNode(int index, int type) throws IOException {
        st = new StringTokenizer(br.readLine());
        int tempX = Integer.parseInt(st.nextToken());
        int tempY = Integer.parseInt(st.nextToken());
        ns[index] = new N(tempX, tempY, type);

        if (index == 0) return;
        for (int k = 0; k < index; k++) {
            N temp = ns[k];
            int m = Math.abs(temp.x - tempX) + Math.abs(temp.y - tempY);
            if (m <= 1000) {
                if (!graph.containsKey(k)) graph.put(k, new ArrayList<>(c + 1));
                graph.get(k).add(index);
                if (!graph.containsKey(index)) graph.put(index, new ArrayList<>(c + 1));
                graph.get(index).add(k);
            }
        }
    }

    static class N {
        final int x, y, type;

        N(int x, int y, int type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }
    }
}