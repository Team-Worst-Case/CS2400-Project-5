import java.util.Arrays;
/**
    A class of queues whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class Queue<T> implements QueueInterface<T>
{
	private T[] queue;    // Array of queue entries
	private int frontIndex; // Index of front entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public Queue()
   {
      this(DEFAULT_CAPACITY);
   }
  
   public Queue(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      queue = tempStack;
		frontIndex = -1;
      integrityOK = true;
  }

   public void enqueue(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      queue[frontIndex + 1] = newEntry;
      frontIndex++;
   }

   private void ensureCapacity()
   {
      if (frontIndex >= queue.length - 1) // If array is full, double its size
      {
         int newLength = 2 * queue.length;
         checkCapacity(newLength);
         queue = Arrays.copyOf(queue, newLength);
      }
   }

   public T dequeue()
   {
      checkIntegrity();
      if (isEmpty())
         try {
            throw new IllegalStateException("Attempted to pop from empty queue.");
         } catch (IllegalStateException ex) {
            return null;
         }
      else
      {
         T front = queue[frontIndex];
         queue[frontIndex] = null;
         frontIndex--;
         return front;
      }
   }

   public T getFront()
   {
      checkIntegrity();
      if (isEmpty())
         try {
            throw new IllegalStateException("Attempted to peek inside empty queue.");
         } catch (IllegalStateException ex) {
            return null;
         }
      else
         return queue[frontIndex];
   }

   public void display()
   {
      for (int i = 0; i < queue.length; i++) {
         System.out.println(queue[i]);
      }
   }

   public boolean isEmpty()
   {
      return frontIndex < 0;
   }
   
   public void clear()
   {
      checkIntegrity();
      
      // Remove references to the objects in the queue,
      // but do not deallocate the array
      while (frontIndex > -1)
      {
         queue[frontIndex] = null;
         frontIndex--;
      }
      //Assertion: frontIndex is -1
   }

   private void checkCapacity(int newLength)
   {
      if (newLength > MAX_CAPACITY)
         throw new IllegalStateException("Attempted to create queue with capacity greater than " + MAX_CAPACITY);
   }

   // Throws an exception if this object is not initialized.
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   }
}