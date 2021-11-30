package GraphPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import ADTPackage.*;

class Vertex<T> implements VertexInterface<T>
{
   private T label;
   private LinkedList<Edge> edgeList; // Edges to neighbors
   private boolean visited;                          // True if visited
   private VertexInterface<T> previousVertex;        // On path to this vertex
   private double cost;                              // Of path to this vertex
   
   public Vertex(T vertexLabel)
   {
      label = vertexLabel;
      edgeList = new LinkedList<>();
      visited = false;
      previousVertex = null;
      cost = 0;
//end constructor

protected class Edge
{
   private VertexInterface<T> vertex; // Vertex at end of edge
   private double weight;
   
   protected Edge(VertexInterface<T> endVertex, double edgeWeight)
   {
      vertex = endVertex;
      weight = edgeWeight;
   } // end constructor
   
   protected Edge(VertexInterface<T> endVertex)
   {
      vertex = endVertex;
      weight = 0;
   } // end constructor

   protected VertexInterface<T> getEndVertex()
   {
      return vertex;
   } // end getEndVertex
   
   protected double getWeight()
   {
      return weight; 
   } // end getWeight
} // end Edge

public T getLabel() {
   return label;

}

public void visit() {
   visited = true;
}

public void unvisit() {
   visited = false;
}

public boolean isVisited() {
   return visited;

}

public boolean connect(VertexInterface<T> endVertex, double edgeWeight)
    {
    boolean result = false;

    if (!this.equals(endVertex))
    {  
        Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
        boolean duplicateEdge = false;

        while (!duplicateEdge && neighbors.hasNext())
        {
            VertexInterface<T> nextNeighbor = neighbors.next();
            if (endVertex.equals(nextNeighbor))
                duplicateEdge = true;
        } 

        if (!duplicateEdge)
        {
            edgeList.add(new Edge(endVertex, edgeWeight));
            result = true;
        } 
    } 

    return result;
    } 

    public boolean connect(VertexInterface<T> endVertex)
    {
        return connect(endVertex, 0);
    } 


