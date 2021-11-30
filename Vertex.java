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

 