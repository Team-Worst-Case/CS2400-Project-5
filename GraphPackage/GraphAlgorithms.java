package GraphPackage;
import GraphPackage.VertexInterface;
import java.util.Iterator;
import ADTPackage.*;

public class GraphAlgorithms<T> implements GraphAlgorithmsInterface<T> {

    private Vertex<T> vertices;
    
    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();
        QueueInterface<T> traversalOrder = new Queue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new Queue<VertexInterface<T>>();

        VertexInterface<T> originVertex = vertices.getValue(origin);
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

        VertexInterface<T> originVertex = vertices.getValue(origin);
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
       resetVertices();
       StackInterface<T> vertexStack = new Stack<>();
       int numberOfVertices = getNumberOfVertices();

       for (int i = 1; i <= numberOfVertices; ++i);
       {
           VertexInterface<T> nextVertex = //?;
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
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while (!finished && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neghbors = frontVertex.getNeightborIterator();
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

        QueueInterface<EntryPQ> priorityQueue = new QueueInterface<>();
        VertexInterface<T> originVertex = vertices.getValue();
        VertexInterface<T> endVertex = vertices.getValue();

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!finished && !priorityQueue.isEmpty());
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if(!frontVertex.isVisited())
            {
                frontVertex.visit();
                frontVertex.setCost(frontEntry.getCost());
                frontVertex.setPredecessor(frontEntry.getPredecessor());
                
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
                            priorityQueue.add(new EntryPQ(nextNeighbor, nextCost, frontVertex));
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
        int len = vertices.size();
        for (int i = 0; i < len; i++)
        {
            Vertex<T> vertex = vertices.get(i);
            vertex.setPredecessor(null);
            vertex.unvisit();
            vertex.setCost(0);
        }
    }  
   
}