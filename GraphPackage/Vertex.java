package GraphPackage;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.LinkedList;
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
   }//end constructor

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

    public Iterator <VertexInterface<T>> getNeighborIterator()
    {
       return new NeightborIterator();
    }

    private class NeighborIterator implements Iterator <VertexInterface<T>>
    {
       private Iterator <Edge> edges;

       private NeighborIterator() {

       edges = edgeList.getIterator();
      } // end default constructor
      
      public boolean hasNext()
      {
          return edges.hasNext();
      } // end hasNext
      
      public VertexInterface<T> next()
      {
          VertexInterface<T> nextNeighbor = null;
          
          if (edges.hasNext())
          {
              Edge edgeToNextNeighbor = edges.next();
              nextNeighbor = edgeToNextNeighbor.getEndVertex();
          }
          else
              throw new NoSuchElementException();
          
          return nextNeighbor;
      } // end next
      
      public void remove()
      {
          throw new UnsupportedOperationException();
      } // end remove
  } // end NeighborIterator

@Override
public Iterator<Double> getWeightIterator() {
    return null;
}

public boolean hasNeighbor()
{
return !edgeList.isEmpty();
} // end hasNeighbor

public VertexInterface<T> getUnvisitedNeighbor()
  {
   VertexInterface<T> result = null;
  
    Iterator<VertexInterface<T>> neighbors = getNeighborIterator();
    while ( neighbors.hasNext() && (result == null) )
      {
         VertexInterface<T> nextNeighbor = neighbors.next();
         if (!nextNeighbor.isVisited())
            result = nextNeighbor;
      } // end while
   return result;
} // end getUnvisitedNeighbor
  
  @Override
  public void setPredecessor(VertexInterface<T> predecessor) {
      previousVertex = predecessor;
  }

  @Override
  public VertexInterface<T> getPredecessor() {
      return previousVertex;
  }

  @Override
  public boolean hasPredecessor() {
      if (previousVertex != null)
          return true;
      return false;
  }

  public void setCost(double newCost) {
      cost = newCost;
  }

  public double getCost() {
      return cost;
  }

  
  
public boolean equals(Object other)
{
   boolean result;
   if ((other == null) || (getClass() != other.getClass()))
      result = false; 
   else
   { // The cast is safe within this else clause @SuppressWarnings("unchecked")
        @SuppressWarnings("unchecked")
       Vertex<T> otherVertex = (Vertex<T>)other; result = label.equals(otherVertex.label);
   } // end if
return result; 
    } // end equals
} // end Vertex

