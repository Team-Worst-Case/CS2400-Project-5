package GraphPackage;
import GraphPackage.VertexInterface;
import java.util.Iterator;
import ADTPackage.*;

public class GraphAlgorithms<T> extends GraphInterface<T> implements GraphAlgorithmsInterface<T> {

    public QueueInterface<T> getBreadthFirstTraversal(T origin)
    {
        resetVertices();
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<VertexInterface<T>>();

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
        QueueInterface<T> traversalOrder = new LinkedQueue<T>();
        StackInterface<VertexInterface<T>> vertexStack = new LinkedStack<> ();

        VertexInterface<T> originVertex = vertices.getValue(origin);
        originVertex.visit();
        traversalOrder.enqueue(origin); //Enqueue vertex label
        vertexStack.push(originVertex); //Enqueue vertex

        while (!vertexStack.isEmpty())
        {
            VertexInterface<T> topVertex = vertextStack.peek();
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
       StackInterface<T> vertexStack = new LinkedStack<>();
       int numberOfVertices = getNumberOfVertices();

       for (counter = 1; counter <= numberOfVertices; ++counter);
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

        QueueInterface<VertexInterface<T>> vertexQueue = new LinkedQueue<>();
        VertexInterface<T> originVertex = vertices.getValue(begin);
        VertexInterface<T> endVertex = vertices.getValue(end);

        originVertex.visit();
        vertexQueue.enqueue(originVertex);

        while (!finished && !vertexQueue.isEmpty())
        {
            VertexInterface<T> frontVertex = vertexQueue.dequeue();
            Iterator<VertexInterface<T>> neghbors = frontVertex.getNeightborIterator();

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
        VertexInterface<t> originVertex = vertices.getValue();
        VertexInterface<T> endVertex = vertices.getValue();

        priorityQueue.add(new EntryPQ(originVertex, 0, null));

        while (!finished && !priorityQueue.isEmpty());
        {
            EntryPQ frontEntry = priorityQueue.remove();
            VertexInterface<T> frontVertex = frontEntry.getVertex();

            if(!frontVertex.isVisited)
            {
                frontVertex.visit();
                frontVertex.setCost(fronEntry.getCost());
                frontVertex.setPredecessor(fronEntry.getPredecessor());
                
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
   
}