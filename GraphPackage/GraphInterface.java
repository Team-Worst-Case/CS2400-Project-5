
/**
   An interface of methods that create, manipulate, and process a graph.
   
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface GraphInterface<T> extends BasicGraphInterface<T>, 
                                           GraphAlgorithmsInterface<T>
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
}