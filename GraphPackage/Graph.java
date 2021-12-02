package GraphPackage;
import java.util.Iterator;
import ADTPackage.*;

public final class Graph<T> implements GraphInterface<T> 
{
    private boolean[] [] edges; // edges[i] [j] is true if there is a vertex from i to j
    private T[] labels; // labels[i] contains the label for vertex i
    private VertexInterface<T>[] vertices = new VertexInterface[9999];

    public Graph(int n)
    {
        edges = new boolean[n] [n]; // All values initially false
        labels = (T[]) new Object[n]; // All values initially null
    }

    //Accessor method to get the label of a vertex of this Graph     
    public T getLabel(int vertex)
    {        
        return labels[vertex];    
    }   
    
    // Test whether an edge exists    
    public boolean isEdge(int source, int target) 
    {        
        return edges[source][target];    
    }    
    
    // Add an edge    
    public void addEdge(int source, int target) 
    {        
        edges[source][target] = true;    
        addVert(source);
        addVert(target);
    }
    
    // Add an edge    
    public void addEdge(String source, String target) 
    {        
        char charSource = source.charAt(0);
        char charTarget = target.charAt(0);
        int intSource = Character.getNumericValue(charSource);
        int intTarget = Character.getNumericValue(charTarget);
        edges[intSource][intTarget] = true;    
        addVert(intSource);
        addVert(intTarget);
    }
    
    public void addVert(int vert)
    {
        for(int i = 0; i < vertices.length; i++)
        {
            if(vertices[i].getLabel() == Integer.valueOf(vert)) {
                return;
            }
        }
        //VertexInterface<T> newVert = new VertexInterface<>();
        Vertex newVert = new Vertex(vert);
        vertices[vertices.length] = newVert;
    }

    // Obtain a list of neighbors of a specified vertex of this Graph    
    public int[] neighbors(int vertex) 
    {        
        int i;        
        int count = 0;        
        int[] answer;        
        
        for (i = 0; i < labels.length; i++) 
        {            
            if (edges[vertex][i])                
            count++;        
        }        
        answer = new int[count];        
        count = 0;        
        for (i = 0; i < labels.length; i++) 
        {            if (edges[vertex][i])                
            answer[count++] = i;        
        }        
        return answer;    
    }

    // Remove an edge    
    public void removeEdge(int source, int target) 
    {        
        edges[source][target] = false;    
    }    
    
    // Change the label of a vertex of this Graph    
    public void setLabel(int vertex, T newLabel) 
    {        
        labels[vertex] = newLabel;    
    }    
    
    // Accessor method to determine the number of vertices in this Graph    
    public int size() 
    {        
        return labels.length;    
    }

    
    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();
        QueueInterface<T> traversalOrder = new Queue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new Queue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices[0];
        originVertex.visit();
        traversalOrder.enqueue(origin);     // enqueue vertex label
        vertexQueue.enqueue(originVertex);  // enqueue vertex

        while (!vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();

            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();

            while (neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();
                if (!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    traversalOrder.enqueue(nextNeighbor.getLabel());
                    vertexQueue.enqueue(nextNeighbor);
                }
            }
        }
        return traversalOrder;
    }

   public QueueInterface<T> getDepthFirstTraversal(T origin)
   {
        //Assume graph is not empty
        resetVertices();
        QueueInterface<T> traversalOrder = new Queue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new Stack<> ();

        VertexInterface<T> originVertex = vertices[0];
        originVertex.visit();
        traversalOrder.enqueue(origin); //Enqueue vertex label
        vertexStack.push(originVertex); //Enqueue vertex

        while (!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertexStack.peek();
            VertexInterface<T> nextNeighbor = topVertex.getUnvisitedNeighbor();

            if (nextNeighbor != null)
            {
                nextNeighbor.visit();
                traversalOrder.enqueue(nextNeighbor.getLabel());
                vertexStack.push(nextNeighbor);
            } // All neighbors are visited
            else vertexStack.pop();
        }

        return traversalOrder;
   }

   public StackInterface<T> getTopologicalOrder()
   {
       //resetVertices();
       StackInterface<T> vertexStack = new Stack<>();
       int numberOfVertices = vertices.length;

       int i;
       for (i = 1; i <= numberOfVertices; ++i);
       {
           VertexInterface<T> nextVertex = vertices[i].getUnvisitedNeighbor();
           nextVertex.visit();
           vertexStack.push(nextVertex.getLabel());
       }

       return vertexStack;
   }

   public int getShortestPath(T begin, T end, StackInterface<T> path)
   {
        resetVertices();

        boolean finished = false;

        QueueInterface<VertexInterface<T>> vertexQueue = new Queue<>();
        VertexInterface<T> originVertex = vertices[0];
        VertexInterface<T> endVertex = vertices[vertices.length - 1];

        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while (!finished && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
            frontVertex.getNeighborIterator();

            while(!finished && neighbors.hasNext())
            {
                VertexInterface<T> nextNeighbor = neighbors.next();

                if(!nextNeighbor.isVisited())
                {
                    nextNeighbor.visit();
                    nextNeighbor.setCost(frontVertex.getCost());
                    nextNeighbor.setPredecessor(frontVertex);
                    vertexQueue.enqueue(nextNeighbor);
                }
                if (nextNeighbor.equals(endVertex))
                        finished = true;
            }
        }
        int pathLength = (int)endVertex.getCost();
        path.push(endVertex.getLabel());
        VertexInterface<T> vertex = endVertex;

        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }

        return pathLength;    
   }

   public double getCheapestPath(T begin, T end, StackInterface<T> path)
   {
        resetVertices();
        boolean finished = false;

        Queue<VertexInterface<T>> queueVertex = new Queue<>();
        VertexInterface<T> originVertex = vertices[0];
        VertexInterface<T> endVertex = vertices[vertices.length - 1];

        queueVertex.enqueue(originVertex);

        while (!finished && !queueVertex.isEmpty());
        {
            VertexInterface<T> frontVertex = queueVertex.getFront();
            queueVertex.dequeue();
            T frontEntry = frontVertex.getLabel();

            if(!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontVertex.getCost());
                frontVertex.setPredecessor(frontVertex.getPredecessor());
                
                if (frontVertex.equals(endVertex))
                {
                    finished = true;
                }
                else
                {
                    Iterator<VertexInterface<T>> neighbors = frontVertex.getNeighborIterator();
                    Iterator<Double> edgeWeights = frontVertex.getWeightIterator();

                    while (neighbors.hasNext())
                    {
                        VertexInterface<T> nextNeighbor = neighbors.next();
                        Double weightOfEdgeToNeighbor = edgeWeights.next();

                        if (!nextNeighbor.isVisited())
                        {
                            double nextCost = weightOfEdgeToNeighbor+frontVertex.getCost();
                            queueVertex.enqueue(nextNeighbor);
                        }
                    }
                }
            }
        }

        double pathCost = endVertex.getCost();
        path.push(endVertex.getLabel());

        VertexInterface<T> vertex = endVertex;

        while (vertex.hasPredecessor())
        {
            vertex = vertex.getPredecessor();
            path.push(vertex.getLabel());
        }

        return pathCost;
   }

    public void resetVertices()
    {  
        int len = vertices.length;
        for (int i = 0; i < len; i++)
        {
            VertexInterface<T> vertex = vertices[i];
            vertex.setPredecessor(null);
            vertex.unvisit();
            vertex.setCost(0);
        }
    }  
}