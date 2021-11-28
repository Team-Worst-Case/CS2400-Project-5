import java.util.Arrays;
/**
    A class of stacks whose entries are stored in an array.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class ResizeableArrayStack<T> implements StackInterface<T>
{
	private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ResizeableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   }
  
   public ResizeableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  }

   public void push(T newEntry)
   {
      checkIntegrity();
      ensureCapacity();
      stack[topIndex + 1] = newEntry;
      topIndex++;
   }

   private void ensureCapacity()
   {
      if (topIndex >= stack.length - 1) // If array is full, double its size
      {
         int newLength = 2 * stack.length;
         checkCapacity(newLength);
         stack = Arrays.copyOf(stack, newLength);
      }
   }

   public T pop()
   {
      checkIntegrity();
      if (isEmpty())
         try {
            throw new IllegalStateException("Attempted to pop from empty stack.");
         } catch (IllegalStateException ex) {
            return null;
         }
      else
      {
         T top = stack[topIndex];
         stack[topIndex] = null;
         topIndex--;
         return top;
      }
   }

   public T peek()
   {
      checkIntegrity();
      if (isEmpty())
         try {
            throw new IllegalStateException("Attempted to peek inside empty stack.");
         } catch (IllegalStateException ex) {
            return null;
         }
      else
         return stack[topIndex];
   }

   public boolean isEmpty()
   {
      return topIndex < 0;
   }
   
   public void clear()
   {
      checkIntegrity();
      
      // Remove references to the objects in the stack,
      // but do not deallocate the array
      while (topIndex > -1)
      {
         stack[topIndex] = null;
         topIndex--;
      }
      //Assertion: topIndex is -1
   }

   private void checkCapacity(int newLength)
   {
      if (newLength > MAX_CAPACITY)
         throw new IllegalStateException("Attempted to create stack with capacity greater than " + MAX_CAPACITY);
   }

   // Throws an exception if this object is not initialized.
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   }
}