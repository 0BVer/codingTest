package dfs.q2606;

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int node = Integer.parseInt(br.readLine());
        int link = Integer.parseInt(br.readLine());

        Map<Integer, ArrayList<Integer>> graph = new HashMap<>(node);

        StringTokenizer st;
        for (int i = 0; i < link; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            if (graph.containsKey(n1)) graph.get(n1).add(n2);
            else {
                graph.put(n1, new ArrayList<>(node));
                graph.get(n1).add(n2);
            }
            if (graph.containsKey(n2)) graph.get(n2).add(n1);
            else {
                graph.put(n2, new ArrayList<>(node));
                graph.get(n2).add(n1);
            }
        }
        br.close();

        Set<Integer> visited = new HashSet<>(node);
        dfs(1, graph, visited);

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder result = new StringBuilder();
        result.append(visited.size() - 1);
        bw.write(result.toString());

        bw.flush();
        bw.close();
    }

    static void dfs(int node, Map<Integer, ArrayList<Integer>> graph, Set<Integer> visited) {
        if (graph.containsKey(node))
            graph.get(node)
                    .forEach(n -> {
                        if (!visited.contains(n)) {
                            visited.add(n);
                            dfs(n, graph, visited);
                        }
                    });
    }
}
