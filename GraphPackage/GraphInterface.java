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
      @param source 
      @param target  */
   public void addEdge(String source, String target);

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
      //public QueueInterface<T> getBreadthFirstTraversal(T origin);

      public QueueInterface<T> getBreadthFirstTraversal(T origin);

      /** Performs a depth-first traversal of this graph.
          @param origin  An object that labels the origin vertex of the traversal.
         @return  A queue of labels of the vertices in the traversal, with
                  the label of the origin vertex at the queue's front. */
      public QueueInterface<T> getDepthFirstTraversal(T origin);
}