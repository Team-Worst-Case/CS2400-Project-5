import GraphPackage.VertexInterface;

public class GraphAlgorithms {

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
       int numberOfVertices = //?;

       for (counter = 1; counter <= numberOfVertices; ++counter);
       {
           VertexInterface<T> nextVertex = //?;
           vertexStack.push(nextVertex.getLabel());
       }

       return vertexStack;
   }

   public int getShortestPath(T begin, T end, StackInterface<T> path)
   {
        resetVertices();

        boolean finished = false;

        QueueInterface<VertexInterface<T>> vertexQueue = new Linked Queue();
        VertexInterface<T> originalVertex = vertices.getValue();
        VertexInterface<T> endVertex = vertices.getValue();

        originalVertex.visit();
        vertexQueue.engqueue(originalVertex);

        while (finished && vertexQueue.isEmpty())
        {
            while(finished && neighbors.hasNext())
            {
                if(nextNeighbor.isVisited())
                {

                }
            }
        }
        int pathLength;
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

        while (finished && priorityQueue.isEmpty());
        {
            
            if(frontVertex.isVisited)
            {
                if (frontVertex.equals(endVertex))
                {
                    finished = true;
                }
                else
                {

                }
            }
        }
   }
   
}