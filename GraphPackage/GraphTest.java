package GraphPackage;
//import GraphPackage.*;
/*import org.junit.Test;
import static org.junit.Assert.*;*/
public class GraphTest
{
	public static void main(String[] args)
	{
        GraphInterface<Integer> graphV = new Graph<>(14);
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
        graphV.getBreadthFirstTraversal(Integer.valueOf(Character.getNumericValue('A'))).display();
        System.out.println("Depth-first traversal of graph with A as starting vertex:");
        graphV.getDepthFirstTraversal(Integer.valueOf(Character.getNumericValue('A'))).display();
    }

    public void testGetTopologicalOrder() {
        //STUB
        
        //assertEquals(expected, test);
    }

    public void testGetShortestPath() {
        //STUB

        //assertEquals(expected, test);
    }

    public void testGetCheapestPath() {
        //STUB

        //assertEquals(expected, test);
    }
}