package GraphPackage;
import org.junit.Test;
import static org.junit.Assert.*;

public class  JUnit
{
    @Test
    public void testGraphs() {
        System.out.println("\nstarted... \n");

        GraphInterface<String> graphV = new Graph<>(5);
        graphV.addEdge(3, 4);
        graphV.addEdge(1, 3);
        graphV.addEdge(3, 2);
        graphV.addEdge(2, 1);
        graphV.addEdge("A", "B");
        graphV.addEdge("A", "D");
        graphV.addEdge("A", "E");
        graphV.addEdge("B", "E");
        graphV.addEdge("D", "G");
        graphV.addEdge("E", "F");
        graphV.addEdge("E", "H");
        graphV.addEdge("G", "H");
        graphV.addEdge("H", "I");
        graphV.addEdge("I", "F");
        graphV.addEdge("F", "H");
        graphV.addEdge("F", "C");
        graphV.addEdge("C", "B");

        System.out.println("Breadth-first traversal of graph with A as starting vertex:");
        //System.out.println(graphV.getBreadthFirstTraversal("A"));

        //assertEquals("A", "A");
    }

    @Test
    public void testGetTopologicalOrder() {
        //STUB
        
        //assertEquals(expected, test);
    }

    @Test
    public void testGetShortestPath() {
        //STUB

        //assertEquals(expected, test);
    }

    @Test
    public void testGetCheapestPath() {
        //STUB

        //assertEquals(expected, test);
    }
}