import java.util.*;

class Solution {

    public double[] calcEquation(List<List<String>> equations,
                                 double[] values,
                                 List<List<String>> queries) {

        Map<String, List<Edge>> graph = new HashMap<>();

        // Build weighted graph
        for (int i = 0; i < equations.size(); i++) {
            String a = equations.get(i).get(0);
            String b = equations.get(i).get(1);
            double value = values[i];

            graph.computeIfAbsent(a, k -> new ArrayList<>())
                 .add(new Edge(b, value));

            graph.computeIfAbsent(b, k -> new ArrayList<>())
                 .add(new Edge(a, 1.0 / value));
        }

        double[] answer = new double[queries.size()];

        for (int i = 0; i < queries.size(); i++) {
            String start = queries.get(i).get(0);
            String end = queries.get(i).get(1);

            if (!graph.containsKey(start) || !graph.containsKey(end)) {
                answer[i] = -1.0;
            } else if (start.equals(end)) {
                answer[i] = 1.0;
            } else {
                Set<String> visited = new HashSet<>();
                answer[i] = dfs(start, end, 1.0, graph, visited);
            }
        }

        return answer;
    }

    private double dfs(String current,
                       String target,
                       double product,
                       Map<String, List<Edge>> graph,
                       Set<String> visited) {

        if (current.equals(target)) {
            return product;
        }

        visited.add(current);

        for (Edge edge : graph.get(current)) {
            if (!visited.contains(edge.to)) {

                double result = dfs(
                    edge.to,
                    target,
                    product * edge.weight,
                    graph,
                    visited
                );

                if (result != -1.0) {
                    return result;
                }
            }
        }

        return -1.0;
    }

    static class Edge {
        String to;
        double weight;

        Edge(String to, double weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}