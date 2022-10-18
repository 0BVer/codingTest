package bfs.q1260.Main;

import java.util.*;

class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int size = scanner.nextInt();
        int line = scanner.nextInt();
        int start = scanner.nextInt();

        HashMap<Integer, ArrayList<Integer>> graph = new HashMap<>(size);

        for (int i = 0; i < line; i++) {
            int n1 = scanner.nextInt();
            int n2 = scanner.nextInt();

            setNode(graph, n1, n2);
            setNode(graph, n2, n1);
        }

        // DFS
        HashSet<Integer> visited = new HashSet<>(List.of(start));
        System.out.print(start + " ");
        dfs(start, graph, visited);
        System.out.println();

        // BFS
        bfs(start, graph);
    }

    private static void setNode(HashMap<Integer, ArrayList<Integer>> graph, int node, int link) {
        if (graph.containsKey(node))
            graph.get(node).add(link);
        else
            graph.put(node, new ArrayList<>(List.of(link)));
    }

    private static void dfs(
            int now,
            Map<Integer, ArrayList<Integer>> graph,
            Set<Integer> visited
    ) {
        if (!graph.containsKey(now)) return;
        graph.get(now)
                .stream()
                .sorted()
                .forEach(node -> {
                    if (!visited.contains(node)) {
                        visited.add(node);
                        System.out.print(node + " ");
                        dfs(node, graph, visited);
                    }
                });
    }

    private static void bfs(
            int start,
            HashMap<Integer, ArrayList<Integer>> graph
    ) {
        Queue<Integer> queue = new LinkedList<>(List.of(start));
        HashSet<Integer> visited = new HashSet<>(List.of(start));

        while (!queue.isEmpty()) {
            int now = queue.poll();
            System.out.print(now + " ");
            if (!graph.containsKey(now)) return;

            graph.get(now)
                    .stream()
                    .sorted()
                    .forEach(node -> {
                        if (!visited.contains(node)) {
                            visited.add(node);
                            queue.add(node);
                        }
                    });
        }
    }
}