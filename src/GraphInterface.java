package GraphPackage;
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
   isEdge(int source, int target);
   // Methods:  
   addEdge(int source, int target);
   getLabel(int vertex);
   neighbors(int vertex);
   removeEdge(int source, int target);
   setLabel(int vertex, E newLabel);
   size();
}