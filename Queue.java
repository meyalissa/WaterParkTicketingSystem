/**
 * CSC248
 * GROUP PROJECT OCT 2023 - FEB 2024
 * WATERPARK TICKETING SYSTEM
 * 
 * Class : Queue
 */

public class Queue extends LinkedList 
{
    public Queue() {}
    
    public void enqueue(Object element) {
        addLast(element);
    }
    
    public Object dequeue() {
        return removeFirst();
    }
    
    public Object getFront() {
        return getFirst();
    }
}
