import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MySet<E> implements Set<E>, Iterable<E> {
	// instance variables - replace the example below with your own
	private E[] backingArray;
	private int numElements;

	/**
	 * Constructor for objects of class MySet
	 */
	public MySet() {
		backingArray = (E[]) new Object[5];
		numElements = 0;
	}

	public boolean add(E e) {

		for (Object elem : backingArray) {
			if (elem == null ? e == null : elem.equals(e)) {
				return false;
			}
		}
		if (numElements == backingArray.length) {
			E[] newArray = Arrays.copyOf(backingArray, backingArray.length * 2);
			newArray[numElements] = e;
			numElements = numElements + 1;
			backingArray = newArray;
			return true;
		} else {
			backingArray[numElements] = e;
			numElements = numElements + 1;
			return true;
		}
	}

	public boolean addAll(Collection<? extends E> c) {
		for (E elem : c) {
			this.add(elem);
		}
		return true;
	}

	public void clear() {
		E[] newArray = (E[]) new Object[backingArray.length];
		numElements = 0;
		backingArray = newArray;
	}

	public boolean equals(Object o) {
		if (o instanceof Set && (((Set) o).size() == numElements)) {
			for (E elem : (Set<E>) o) {
				if (this.contains(o) == false) {
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public boolean contains(Object o) {
		for (E backingElem : backingArray) {
			if (o != null && o.equals(backingElem)) {
				return true;
			}
		}
		return false;
	}

	public boolean containsAll(Collection<?> c) {
		for (E elem : (Set<E>) c) {
			if (!(this.contains(elem))) {
				return false;
			}
		}
		return true;
	}

	public int hashCode() {
		int sum = 0;
		for (E elem : backingArray) {
			if (elem != null) {
				sum = sum + elem.hashCode();
			}
		}
		return sum;
	}

	public boolean isEmpty() {
		if (numElements == 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean remove(Object o) {
	    int i = 0;
	    for (Object elem : backingArray) {
	        if (o != null && o.equals(elem)) {
	            System.arraycopy(backingArray, i+1, backingArray, i, numElements-i-1);
	            backingArray[numElements-1] = null;
	            numElements = numElements - 1;
	            return true;
	        }
	        i = i + 1;
	    }
	    return false;
	}

	public boolean removeAll(Collection<?> c) {
		for (Object elem : c) {
			this.remove(elem);
		}
		return true;

	}

	public boolean retainAll(Collection<?> c) {
	    int index = 0;
	    boolean result = false;
	    if (this.containsAll(c)){
	        result = true;
	    }

	    while(index < numElements) {
	        E e = backingArray[index];
	        if (e != null && !(c.contains(e))) {
	            this.remove(e);
	        } else {
	            index++;
	        }
	    }
	    return result;
	}

	public int size() {
		return numElements;
	}

	public <T> T[] toArray(T[] a) throws ArrayStoreException, NullPointerException {
		for (int i = 0; i < numElements; i++) {
			a[i] = (T) backingArray[i];
		}
		for (int j = numElements; j < a.length; j++) {
			a[j] = null;
		}
		return a;
	}

	public Object[] toArray() {
		Object[] newArray = new Object[numElements];
		for (int i = 0; i < numElements; i++) {
			newArray[i] = backingArray[i];
		}
		return newArray;
	}

	public Iterator<E> iterator() {
		setIterator iterator = new setIterator();
		return iterator;
	}

	private class setIterator implements Iterator<E> {
		private int currIndex;
		private E lastElement;

		public setIterator() {
			currIndex = 0;
			lastElement = null;
		}

		public boolean hasNext() {
			while (currIndex <= numElements && backingArray[currIndex] == null) {
				currIndex = currIndex + 1;
			}
			if (currIndex <= numElements) {
				return true;
			}
			return false;
		}

		public E next() {
			E element = backingArray[currIndex];
			currIndex = currIndex + 1;
			lastElement = element;
			return element;
		}

		public void remove() throws UnsupportedOperationException, IllegalStateException {
			if (lastElement != null) {
				MySet.this.remove((Object) lastElement);
				numElements = numElements - 1;
			} else {
				throw new IllegalStateException();
			}
		}
	}
}
