package GraphPackage;
import ADTPackage.*;
/**
   An interface of methods that create, manipulate, and process a graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface GraphInterface<T>// extends GraphAlgorithmsInterface<T>//, BasicGraphInterface<T>
{
   //private boolean[] [] edges; // edges[i] [j] is true if there is a vertex from i to j
   //private E[] labels; // labels[i] contains the label for vertex i

   // Constructors
   // Boolean method
   /** 
      @return 
      @param source 
      @param target  */
   public boolean isEdge(int source, int target);

   // Methods:  
   /** 
      @param source 
      @param target  */
   public void addEdge(int source, int target);

   /** 
      @return 
      @param vertex  */
   public T getLabel(int vertex);

   /** 
      @return 
      @param vertex  */
   public int[] neighbors(int vertex);

   /** 
      @param source 
      @param target  */
   public void removeEdge(int source, int target);

   /** 
      @param vertex 
      @param newLabel  */
   public void setLabel(int vertex, T newLabel);

   /** 
      @return */
   public int size();


   /** Performs a breadth-first traversal of this graph.
       @param origin  An object that labels the origin vertex of the traversal.
       @return  A queue of labels of the vertices in the traversal, with
                the label of the origin vertex at the queue's front. */
                public QueueInterface<T> getBreadthFirstTraversal(T origin);

                /** Performs a depth-first traversal of this graph.
                    @param origin  An object that labels the origin vertex of the traversal.
                    @return  A queue of labels of the vertices in the traversal, with
                             the label of the origin vertex at the queue's front. */
                public QueueInterface<T> getDepthFirstTraversal(T origin);
             
                /** Performs a topological sort of the vertices in this graph without cycles.
                    @return  A stack of vertex labels in topological order, beginning
                             with the stack's top. */
                public StackInterface<T> getTopologicalOrder();
             
                /** Finds the shortest-length path between two given vertices in this graph.
                    @param begin  An object that labels the path's origin vertex.
                    @param end    An object that labels the path's destination vertex.
                    @param path   A stack of labels that is empty initially;
                                  at the completion of the method, this stack contains
                                  the labels of the vertices along the shortest path;
                                  the label of the origin vertex is at the top, and
                                  the label of the destination vertex is at the bottom       
                    @return  The length of the shortest path. */
                public int getShortestPath(T begin, T end, StackInterface<T> path);
             
                /** Finds the least-cost path between two given vertices in this graph.
                    @param begin  An object that labels the path's origin vertex.
                    @param end    An object that labels the path's destination vertex.
                    @param path   A stack of labels that is empty initially;
                                  at the completion of the method, this stack contains
                                  the labels of the vertices along the cheapest path;
                                  the label of the origin vertex is at the top, and
                                  the label of the destination vertex is at the bottom       
                    @return  The cost of the cheapest path. */
                public double getCheapestPath(T begin, T end, StackInterface<T> path);
}