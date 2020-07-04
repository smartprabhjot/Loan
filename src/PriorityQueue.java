import java.util.NoSuchElementException;

/**
 * PriorityQueue class implemented via the binary heap.
 */
public class PriorityQueue<AnyType extends Comparable<? super AnyType>>
{

    private static int INITSIZE = 100;

    private int currentSize;   // Number of elements in heap
    private AnyType [ ] array; // The heap array
    
	
    /**
     * Construct an empty PriorityQueue.
     */
    public PriorityQueue( )
    {
        currentSize = 0;
        array = (AnyType[]) new Comparable[ INITSIZE + 1 ];
    }
    
    
    /**
     * Inserts an item to this PriorityQueue.
     * @param x any object.
     * @return true.
     */
    public boolean push( AnyType x )
    {
        if( currentSize + 1 == array.length )
            expandArray( );

            // Percolate up
        int hole = ++currentSize;
        array[ 0 ] = x;
        
        for( ; x.compareTo(array[ hole / 2 ] ) > 0; hole /= 2 )
            array[ hole ] = array[ hole / 2 ];
        array[ hole ] = x;
        
        return true;
    }
    
    /**
     *  isEmpty() indicates whether the heap is empty.
	 *  @return true if the list is empty, false otherwise.
	 **/

    public boolean isEmpty() {
    	return currentSize == 0;
    }
    
    /**
     * Returns the number of items in this PriorityQueue.
     * @return the number of items in this PriorityQueue.
     */
    public int size( )
    {
        return currentSize;
    }
    
    /**
     * Make this PriorityQueue empty.
     */
    public void clear( )
    {
        currentSize = 0;
    }
    
    /**
     * Returns the greatest item in the priority queue.
     * @return the greatest item.
     * @throws NoSuchElementException if empty.
     */
    public AnyType top( )
    {
        if( isEmpty( ) )
            throw new NoSuchElementException( );
        return array[ 1 ];
    }
    
    /**
     * Removes the smallest item in the priority queue.
     * @return the smallest item.
     * @throws NoSuchElementException if empty.
     */
    public AnyType pop( )
    {
        AnyType minItem = top( );
        array[ 1 ] = array[ currentSize-- ];
        percolateDown( 1 );

        return minItem;
    }


    /**
     * Establish heap order property from an arbitrary
     * arrangement of items. Runs in linear time.
     */
    public void buildHeap( )
    {
        for( int i = currentSize / 2; i > 0; i-- )
            percolateDown( i );
    }


    /**
     * Internal method to percolate down in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateDown( int hole )
    {
        int child;
        AnyType tmp = array[ hole ];

        for( ; hole * 2 <= currentSize; hole = child )
        {
            child = hole * 2;
            if( child != currentSize &&
                    array[ child + 1 ].compareTo(array[ child ] ) < 0 )
                child++;
            if( array[ child ].compareTo(tmp ) < 0 )
                array[ hole ] = array[ child ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
    /**
     * Internal method to percolate up in the heap.
     * @param hole the index at which the percolate begins.
     */
    private void percolateUp( int hole )
    {
        int parent;
        AnyType tmp = array[ hole ];

        for( ; hole / 2 >= 1; hole = parent )
        {
            parent = hole / 2;
            
            if( array[ parent ].compareTo(tmp ) > 0 )
                array[ hole ] = array[ parent ];
            else
                break;
        }
        array[ hole ] = tmp;
    }
    
    /**
     * Find an item in the priority queue and update the key
     * This is a **min** heap:
     *     if new key is greater, item should be percolated down
     *     if new key is smaller, item should be percolated up
     * If you need a max heap, reverse it
     */
    
    public void updateKey(AnyType updatedItem) {
        //it finds the item first
    	int index = 1;
    	for (; index <=size(); index++)
    		if (this.array[index].equals(updatedItem))
    			break;
    	
    	//if item does not exist in the queue, there is nothing to do
    	if (index > size())
    		return ;
    	
    	if (this.array[index].compareTo(updatedItem) > 0) {
    		this.array[index] = updatedItem;
    		percolateUp(index);
    	}
    	
    	else {
    		this.array[index] = updatedItem;
    		percolateDown(index);
    	}
    }
    /**
	 *  expandArray(): internal method to extend array.
	 *  creates a new array with larger size (twice)
	 */
	private void expandArray() {
        AnyType [ ] newArray;

        newArray = (AnyType []) new Comparable[ array.length * 2 ];
        for( int i = 0; i < array.length; i++ )
            newArray[ i ] = array[ i ];
        array = newArray;
    }
	
	public AnyType find(AnyType other) {
		for (int i = 1; i <= size(); i++)
			if (this.array[i].equals(other))
				return array[i];
		return null;
	}
    
 /*   public static void main( String [ ] args )
    {
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>( );
        final int NUMS = 4000;
        final int GAP  =   37;

        System.out.println( "Checking... (no more output means success)" );
        //This main method will test if your max heap works or not
        //currently it does not work because the priority queue we have is a min heap
        //just use this to test your max heap
        //when you are done, remove this main method and uncomment the main method in class test
        // (there should be only one main method in the whole project)
        int max = -1000000;
        for( int i = GAP; i != 0; i = ( i + GAP ) % NUMS ) {
        	if (max > i)
        		max = i;
        	queue.push(  i  );
            if( queue.top( ) != max )
                System.out.println( "Push error! "+i+"   "+queue.top( ));
        }

        for( int i = NUMS; i > 1; i-- )
             if( queue.pop( ) != i )
                 System.out.println( "Pop error!" );
    } */
}
