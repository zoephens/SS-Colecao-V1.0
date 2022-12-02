//Order Queue
//Implement ADT's 
//FIFO - Queue first in first out
public interface OrderQueue{
    // Adds one element to the rear of the queue
    public void enqueue(Order o);

    // Removes and returns the element at the front of the queue
    public Order dequeue( );

    // Returns without removing the element at the front of the queue
    public Order first( );

    // Returns true if the queue contains no elements
    public boolean isEmpty( );

    // Returns the number of elements in the queue
    public int queue_size( );

    // Returns a string representation of the queue
    public String toString( );

    //Returns without removing the last element of the queue
    public Order last();
}