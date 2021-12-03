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
        String v = vert > 0 && vert < 27 ? String.valueOf((char)(vert + 55)) : null;
        Vertex newVert = new Vertex(v);

        while(temp.size() > 0)
        {
            Queue<Vertex> temp2 = vertices;
            Vertex tempVert = temp.dequeue();
            while (temp2.size() > 0)
            {
                if(tempVert.getLabel() == temp2.dequeue()) {
                    System.out.println("exit " + newVert.getLabel() + " ");
                }
            }
        }
        
        System.out.println(newVert.getLabel() + " ");
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
        Queue<Object> traversalQueue = new Queue<>();
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
        //return traversalQueue;
        return new Queue<>();
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

        return traversalOrder;*/
        return new Queue<T>();
   }
}