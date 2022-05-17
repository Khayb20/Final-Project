/**
 * @author Abigail Animah Owusu
 * @author Solomon Kwabena Asante-Ansong
 * @author Oheneba Kwaku Addo Dade
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.io.IOException;

public class DijkstraAdjacencyMatrix {
    static final int NO_PARENT = -1;
    static HashMap<Integer,String> locations;
    static ArrayList<String> pathway = new ArrayList<>();

    public static class Graph{
        static int vertices;
        static int[][] matrix;

        public Graph(int vertex) {
            vertices = vertex;
            matrix = new int[vertex][vertex];
            if(vertices == 24){
                locations = new HashMap<>();
                locations.put(0, "School Gate");
                locations.put(1, "Fablab/Health centre");
                locations.put(2, "Ash Pitch/Gym");
                locations.put(3, "Inner Gate");
                locations.put(4, "Library");
                locations.put(5, "Dlab");
                locations.put(6, "Green Lounge");
                locations.put(7, "Norton Motulsky");
                locations.put(8, "Research Building");
                locations.put(9, "Nana Araba Hall");
                locations.put(10, "Akonor Cafeteria");
                locations.put(11, "The Hive");
                locations.put(12, "Big Ben Cafeteria");
                locations.put(13, "New Hostels");
                locations.put(14, "BasketBall Court 2");
                locations.put(15, "Car park 2");
                locations.put(16, "Lobby 2");
                locations.put(17, "BasketBall Court 1");
                locations.put(18, "Lobby 1");
                locations.put(19, "Car Park 1");
                locations.put(20, "Admin Block");
                locations.put(21, "Jackson Hall");
                locations.put(22, "Engineering Lab");
                locations.put(23, "Old hostels");
            }
        }

        public void addEdge(int source, int destination, int distance) {
            matrix[source][destination]=distance;
            matrix[destination][source] = distance;
        }

        //Obtain vertex with minimum distance which is not included in Shortest Path Tree
        static int getMinimumVertex(boolean[] mst, int[] key){
            int minimumKey = Integer.MAX_VALUE;
            int vertex = - 1;
            for (int i = 0; i <vertices ; i++) {
                if(!mst[i] && minimumKey>key[i]){
                    minimumKey = key[i];
                    vertex = i;
                }
            }
            return vertex;
        }

        public static void getShortestPath(int sourceVertex, int endVertex){
            boolean[] shortestPathTree = new boolean[vertices];
            int [] distances = new int[vertices];
            final int INFINITY = Integer.MAX_VALUE;

            for (int i = 0; i <vertices ; i++) {
                distances[i] = INFINITY;
            }

            distances[sourceVertex] = 0;
            int[] parents = new int[vertices];
            parents[sourceVertex] = NO_PARENT;

            for (int i = 0; i <vertices ; i++) {

                int vertexA = getMinimumVertex(shortestPathTree, distances);
                shortestPathTree[vertexA] = true;

                for (int vertexB = 0; vertexB <vertices ; vertexB++) {
                    if(matrix[vertexA][vertexB]>0){
                        if(!shortestPathTree[vertexB] && matrix[vertexA][vertexB]!=INFINITY){
                            int newKey = matrix[vertexA][vertexB] + distances[vertexA];
                            if(newKey<distances[vertexB])
                                distances[vertexB] = newKey;
                                parents[vertexB] = vertexA;
                        }
                    }
                }
            }
            displayStartEndDistance(sourceVertex, endVertex, distances, parents);
        }

        public static void displayStartEndDistance(int startVertex,
                                                   int endVertex,
                                                   int[] distances,
                                                   int[] parents)
        {
            int numVertices = distances.length;
            for (int vertexIndex = 0;
                 vertexIndex < numVertices;
                 vertexIndex++)
            {
                if (vertexIndex != startVertex && vertexIndex == endVertex)
                {
                    displayPath(vertexIndex, parents);
                }
            }
        }

        public static void displayPath(int currentVertex,
                                       int[] parents)
        {

            if (currentVertex == NO_PARENT)
                return;
            displayPath(parents[currentVertex], parents);
            pathway.add(locations.get(currentVertex));
        }
    }

    public static void main(String[] args) {
        int vertices = 24;
        Graph graph = new Graph(vertices);
        graph.addEdge(0, 19, 25);
        graph.addEdge(0, 1, 20);
        graph.addEdge(1, 2, 1);
        graph.addEdge(0, 2, 50);
        graph.addEdge(0, 3, 5);
        graph.addEdge(3, 4, 3);
        graph.addEdge(3, 5, 3);
        graph.addEdge(3, 12, 18);
        graph.addEdge(3, 9, 6);
        graph.addEdge(5, 22, 1);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 2);
        graph.addEdge(7, 8, 1);
        graph.addEdge(8, 10, 3);
        graph.addEdge(10, 11, 2);
        graph.addEdge(10, 12, 1);
        graph.addEdge(12, 13, 10);
        graph.addEdge(13, 14, 2);
        graph.addEdge(14, 15, 3);
        graph.addEdge(15, 0, 20);
        graph.addEdge(14, 16, 3);
        graph.addEdge(16, 17, 5);
        graph.addEdge(17, 23, 1);
        graph.addEdge(17, 18, 1);
        graph.addEdge(18, 19, 1);
        graph.addEdge(19, 20, 1);
        graph.addEdge(19, 21, 1);
        graph.addEdge(16, 12, 4);
        graph.addEdge(21, 9, 2);
        graph.addEdge(9, 4, 2);
        graph.addEdge(20, 4, 4);
        graph.addEdge(3, 1, 1);

        try {
            new GUI();
        } catch (IOException e) {
            System.out.println("Could not generate Graphical User Interface");
        }
    }
}