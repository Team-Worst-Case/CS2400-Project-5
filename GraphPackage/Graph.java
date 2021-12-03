package GraphPackage;
import java.util.Iterator;
import ADTPackage.*;
import java.util.ArrayList;

public final class Graph<T> implements GraphInterface<T> 
{
    private boolean[] [] edges; // edges[i] [j] is true if there is a vertex from i to j
    private T[] labels; // labels[i] contains the label for vertex i
    //ArrayList<VertexInterface<T>> vertices = new ArrayList<>();
    Queue<Vertex> vertices = new Queue<>();

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
        //edges[intSource][intTarget] = true;    
        addVert(intSource);
        addVert(intTarget);
    }
    
    public void addVert(int vert)
    {
    Queue<Vertex> temp = vertices;
        for(int i = 0; i < vertices.size(); i++)
        {
            if(temp.dequeue().getLabel() == Integer.valueOf(vert)) {
                return;
            }
        }
        
        String v = vert > 0 && vert < 27 ? String.valueOf((char)(vert + 55)) : null;
        Vertex newVert = new Vertex(v);
        vertices.enqueue(newVert);
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
        ArrayList<Vertex> traversalOrder = new ArrayList<>();
        for (int i = 0; i < vertices.size(); i++)
        {
            traversalOrder.add(vertices.dequeue());
        }
        Queue<T> traversalQueue = new Queue<>();
        for (int i = 0; i < traversalOrder.size(); i++)
        {
            Vertex frontVertex = traversalOrder.get(i);
            traversalQueue.enqueue(frontVertex.getLabel());

            Iterator<Vertex> neighborIterator = frontVertex.getNeighborIterator();

            while (neighborIterator.hasNext())
            {
                Vertex neighbor = neighborIterator.next();
                if (!neighbor.isVisited())
                {
                    traversalOrder.add(neighbor);
                    neighbor.visit();
                }
            }
        }
        for (int i = 0; i < traversalOrder.size(); i++)
        {
            vertices.enqueue(traversalOrder.get(i));
        }
        return traversalQueue;
    }

   public QueueInterface<T> getDepthFirstTraversal(T origin)
   {
        //Assume graph is not empty
        //resetVertices();
        /*QueueInterface<T> traversalOrder = new Queue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new Stack<> ();

        VertexInterface<T> originVertex = vertices.get(0);
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

<<<<<<< HEAD
        return traversalOrder;*/
=======
        return traversalOrder;
   }

   public StackInterface<T> getTopologicalOrder()
   {
       //resetVertices();
       StackInterface<T> vertexStack = new Stack<>();
       int numberOfVertices = vertices.size();

       int i;
       for (i = 1; i <= numberOfVertices; ++i);
       {
           VertexInterface<T> nextVertex = vertices.get(i).getUnvisitedNeighbor();
           nextVertex.visit();
           vertexStack.push(nextVertex.getLabel());
       }

       return vertexStack;
   }

   public int getShortestPath(T begin, T end, StackInterface<T> path)
   {
        //resetVertices();

        boolean finished = false;

        QueueInterface<VertexInterface<T>> vertexQueue = new Queue<>();
        VertexInterface<T> endVertex = vertices.get(0);
        VertexInterface<T> originVertex = vertices.get(vertices.size() - 1);

        endVertex.visit();
        vertexQueue.enqueue(endVertex);

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
        int pathLength = (int)originVertex.getCost();
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
        //resetVertices();
        boolean finished = false;

        Queue<VertexInterface<T>> queueVertex = new Queue<>();
        VertexInterface<T> originVertex = vertices.get(0);
        VertexInterface<T> endVertex = vertices.get(vertices.size() - 1);

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
>>>>>>> a03f580f394328c034fcea7624678e2c0f7b43d6
   }

    public void resetVertices()
    {  
        /*for (int i = 0; i < vertices.size(); i++)
        {
            VertexInterface<T> vertex = vertices.get(i);
            vertex.setPredecessor(null);
            vertex.unvisit();
            vertex.setCost(0);
        }*/
    }
}