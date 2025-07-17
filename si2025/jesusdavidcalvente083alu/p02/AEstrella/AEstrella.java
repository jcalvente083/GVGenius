package si2025.jesusdavidcalvente083alu.p02.AEstrella;

import java.util.*;

public class AEstrella {

    public enum ACCIONES {
        UP, DOWN, LEFT, RIGHT, NIL
    }

    static class Node implements Comparable<Node> {
        int x, y;
        int g;
        int f;
        Node parent;

        public Node(int x, int y, int g, int f, Node parent) {
            this.x = x;
            this.y = y;
            this.g = g;
            this.f = f;
            this.parent = parent;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(this.f, other.f);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Node)) return false;
            Node node = (Node) o;
            return x == node.x && y == node.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public static List<ACCIONES> aStarSearch(int startX, int startY, int goalX, int goalY, int[][] map) {
        int width = map.length;
        int height = map[0].length;

        if (!isInBounds(startX, startY, width, height) || !isInBounds(goalX, goalY, width, height)) return new ArrayList<>();
        if (map[startX][startY] == 1 || map[goalX][goalY] == 1) return new ArrayList<>();

        PriorityQueue<Node> openSet = new PriorityQueue<>();
        Map<String, Integer> gScores = new HashMap<>();
        Set<String> closedSet = new HashSet<>();

        Node startNode = new Node(startX, startY, 0, heuristic(startX, startY, goalX, goalY), null);
        openSet.add(startNode);
        gScores.put(key(startX, startY), 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.x == goalX && current.y == goalY) {
                return reconstructActions(current);
            }

            closedSet.add(key(current.x, current.y));

            for (int[] dir : directions) {
                int nx = current.x + dir[0];
                int ny = current.y + dir[1];

                if (!isInBounds(nx, ny, width, height)) continue;
                if (map[nx][ny] == 1) continue;

                String neighborKey = key(nx, ny);
                if (closedSet.contains(neighborKey)) continue;

                int tentativeG = current.g + 1;
                int currentG = gScores.getOrDefault(neighborKey, Integer.MAX_VALUE);

                if (tentativeG < currentG) {
                    gScores.put(neighborKey, tentativeG);
                    int f = tentativeG + heuristic(nx, ny, goalX, goalY);
                    Node neighbor = new Node(nx, ny, tentativeG, f, current);
                    openSet.add(neighbor);
                }
            }
        }

        return new ArrayList<>();
    }

    private static List<ACCIONES> reconstructActions(Node node) {
        LinkedList<ACCIONES> actions = new LinkedList<>();
        while (node.parent != null) {
            int dx = node.x - node.parent.x;
            int dy = node.y - node.parent.y;

            if (dx == 1 && dy == 0) actions.addFirst(ACCIONES.RIGHT);
            else if (dx == -1 && dy == 0) actions.addFirst(ACCIONES.LEFT);
            else if (dx == 0 && dy == 1) actions.addFirst(ACCIONES.DOWN);
            else if (dx == 0 && dy == -1) actions.addFirst(ACCIONES.UP);

            node = node.parent;
        }
        return actions;
    }

    private static int heuristic(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2); // Manhattan
    }

    private static boolean isInBounds(int x, int y, int w, int h) {
        return x >= 0 && y >= 0 && x < w && y < h;
    }

    private static String key(int x, int y) {
        return x + "," + y;
    }

    private static final int[][] directions = {
        {0, -1}, // UP
        {0, 1},  // DOWN
        {-1, 0}, // LEFT
        {1, 0}   // RIGHT
    };
}
