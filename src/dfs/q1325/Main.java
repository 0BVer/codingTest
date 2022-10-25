package dfs.q1325;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int size = Integer.parseInt(st.nextToken());
        int link = Integer.parseInt(st.nextToken());

        // 그래프 생성, 단방향 그래프
        Map<Integer, List<Integer>> graph = new HashMap<>(size);
        for (int i = 0; i < link; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (!graph.containsKey(b)) {
                graph.put(b, new LinkedList<>());
            }
            graph.get(b).add(a);
        }
        br.close();

        // dfs
        int max = -1;
        Set<Integer> visited = new HashSet<>(size);
        StringBuilder result = new StringBuilder();
        for (var i : graph.keySet()) {
            int count = 1;
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            visited.add(i);
            while (!queue.isEmpty()) {
                int now = queue.poll();
                count++;
                if (graph.containsKey(now)) {
                    graph.get(now).forEach(n -> {
                        if (!visited.contains(n)) {
                            visited.add(n);
                            queue.add(n);
                        }
                    });
                }
            }
//            dfs(i, graph, visited);
//            int count = visited.size();
            if (count > max) {
                max = count;
                result = new StringBuilder();
                result.append(i).append(" ");
            } else if (count == max) result.append(i).append(" ");
            visited.clear();
        }
        bw.write(result.toString());
        bw.flush();
        bw.close();
    }

    static void dfs(int node, Map<Integer, List<Integer>> graph, Set<Integer> visited) {
        if (graph.containsKey(node))
            graph.get(node).forEach(n -> {
                if (!visited.contains(n)) {
                    visited.add(n);
                    dfs(n, graph, visited);
                }
            });
    }
}