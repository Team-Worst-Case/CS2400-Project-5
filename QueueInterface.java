/**
   An interface for the ADT queue.
   @author Frank M. Carrano
   @author Timothy M. Henry
   @version 5.0
*/
public interface QueueInterface<T>
{
   /** Adds a new entry to the front of this queue.
       @param newEntry  An object to be added to the queue. */
   public void enqueue(T newEntry);

   /** Removes and returns this queue's front entry.
       @return  The object at the front of the queue. 
       @throws  EmptyQueueException if the queue is empty before the operation. */
   public T dequeue();
  
   /** Retrieves this queue's front entry.
       @return  The object at the front of the queue.
       @throws  EmptyQueueException if the queue is empty. */
   public T getFront();
   
   /** Displays the stack on the output stream
       @throws  EmptyQueueException if the queue is empty. */
   public void display();
  
   /** Detects whether this queue is empty.
       @return  True if the queue is empty. */
   public boolean isEmpty();
  
   /** Removes all entries from this queue. */
   public void clear();
}