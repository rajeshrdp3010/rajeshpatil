import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class CountItemsList<E> extends ArrayList<E> { 

    // This is private. It is not visible from outside.
    private Map<E,AtomicInteger> count = new HashMap<E,AtomicInteger>();
    

    // There are several entry points to this class
    // this is just to show one of them.
    public boolean add( E element  ) { 
        AtomicInteger value = count.get(element);
        if (value == null) {
           count.put(element, new AtomicInteger(1));
        } else {
           value.incrementAndGet();
           count.put( element, value);
        }
        return super.add( element );
    }

    // This method belongs to CountItemList interface ( or class ) 
    // to used you have to cast.
    public int getCount( E element ) { 
    	AtomicInteger value = count.get(element);
        if( ! count.containsKey( element ) ) {
            return 0;
        }
        return value.get();
    }

    public static void main( String [] args ) { 
        List<String> animals = new CountItemsList<String>();
        animals.add("bat");
        animals.add("owl");
        animals.add("bat");
        animals.add("bat");

        System.out.println( (( CountItemsList<String> )animals).getCount( "bat" ));
    }
}