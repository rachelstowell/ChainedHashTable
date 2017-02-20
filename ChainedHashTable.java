public class ChainedHashTable {

    private ChainedHashNode[] table;

    public ChainedHashTable(int tableSize) {
        if (tableSize <= 0)
            throw new IllegalArgumentException("Table size must be positive.");
        table = new ChainedHashNode[tableSize];
    }


    /**
     * Determines whether a specified key is in this ChainedTable.
     *
     * @param key the non-null key to look for
     *            <b>Precondition:</b>
     *            <CODE>key</CODE> cannot be null.
     * @return <CODE>true</CODE> (if this ChainedTable contains an object with the specified
     * key); <CODE>false</CODE> otherwise. Note that <CODE>key.equals( )</CODE>
     * is used to compare the <CODE>key</CODE> to the keys that are in the
     * ChainedTable.
     * @throws NullPointerException Indicates that <CODE>key</CODE> is null.
     **/
    public boolean containsKey(Object key) {
        if (key == null)
            throw new NullPointerException("The key is null.");
        else {
            int index = hash(key);
            ChainedHashNode thisNode = table[index];
            if (thisNode == null) {
                return false;
            }
            if (thisNode.key.equals(key)) {
                return true;
            } else {
                while (thisNode.link != null) {
                    if (thisNode.key.equals(key)) {
                        return true;
                    }
                    thisNode = thisNode.link;
                }
                if (thisNode.link == null && thisNode.key.equals(key)) {
                    return true;
                } else {
                    return false;
                }
            }
        }
    }


    /**
     * Retrieves an object for a specified key.
     *
     * @param key the non-null key to look for
     *            <b>Precondition:</b>
     *            <CODE>key</CODE> cannot be null.
     * @return a reference to the object with the specified <CODE>key</CODE> (if this
     * ChainedTable contains an such an object);  null otherwise. Note that
     * <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
     * to the keys that are in the ChainedTable.
     * @throws NullPointerException Indicates that <CODE>key</CODE> is null.
     **/
    public Object get(Object key) {
        if (key == null)
            throw new NullPointerException("The key is null.");

        else {
            if (containsKey(key)) {
                int index = hash(key);
                ChainedHashNode thisNode = table[index];
                if (thisNode.key.equals(key)) {
                    return thisNode.element;
                } else {
                    while (thisNode.link != null) {
                        if (thisNode.key.equals(key)) {
                            return thisNode;
                        }
                        thisNode = thisNode.link;
                    }
                    if (thisNode.link == null && thisNode.key.equals(key)) {
                        return thisNode;
                    } else {
                        return null;
                    }


                }
            } else {
                return null;
            }
        }

    }


    private int hash(Object key)
    // The return value is a valid index of the ChainedTable's table. The index is
    // calculated as the remainder when the absolute value of the key's
    // hash code is divided by the size of the ChainedTable's table.
    {
        return Math.abs(key.hashCode()) % table.length;
    }


    /**
     * Add a new element to this ChainedTable, using the specified key.
     *
     * @param key     the non-null key to use for the new element
     * @param element the new element that's being added to this ChainedTable
     *                <b>Precondition:</b>
     *                Neither <CODE>key</CODE> nor <CODE>element</CODE> is null.
     * @return If this ChainedTable already has an object with the specified <CODE>key</CODE>,
     * then that object is replaced by <CODE>element</CODE>, and the return
     * value is a reference to the replaced object. Otherwise, the new
     * <CODE>element</CODE> is added with the specified <CODE>key</CODE>
     * and the return value is null.
     * @throws NullPointerException Indicates that <CODE>key</CODE> or <CODE>element</CODE> is null.
     **/
    public Object put(Object key, Object element) {
        ChainedHashNode cursor = null;
        Object answer = null;


        if (cursor == null) {  // Add a new node at the front of the list of table[hash(key)].
            int i = hash(key);
            cursor = new ChainedHashNode();
            cursor.element = element;
            cursor.key = key;
            cursor.link = table[i];
            table[i] = cursor;
        } else {  // The new element replaces an old node.
            answer = cursor.element;
            cursor.element = element;
        }

        return answer;
    }


    /**
     * Removes an object for a specified key.
     *
     * @param key the non-null key to look for
     *            <b>Precondition:</b>
     *            <CODE>key</CODE> cannot be null.
     * @return If an object was found with the specified <CODE>key</CODE>, then that
     * object has been removed from this ChainedTable and a copy of the removed object
     * is returned; otherwise, this ChainedTable is unchanged and the null reference
     * is returned.  Note that
     * <CODE>key.equals( )</CODE> is used to compare the <CODE>key</CODE>
     * to the keys that are in the ChainedTable.
     * @throws NullPointerException Indicates that <CODE>key</CODE> is null.
     **/
    public Object remove(Object key) {
        if (key == null)
            throw new NullPointerException("The key is null.");
        int index = hash(key);
        if (table[index] != null) {
            ChainedHashNode prevEntry = null;
            ChainedHashNode entry = table[index];
            if (entry.key.equals(key)){
                if (prevEntry == null)
                    table[index] = entry.link;
                else
                    prevEntry.link = entry.link;
                return entry.element;
            }
            else {
                while (entry.link != null) {
                    prevEntry = entry;
                    entry = entry.link;
                    if (entry.key.equals(key)) {
                        if (prevEntry == null)
                            table[index] = entry.link;
                        else
                            prevEntry.link = entry.link;

                    }

                }
            }
        }
        return null;
    }



    public void show() {
        for (int i = 0; i < 10; i++) {
            ChainedHashNode n1 = table[i];
            System.out.println(i);
            while (n1 != null) {
                System.out.println("   " + n1.key + " " + n1.element);
                n1 = n1.link;
            }
        }
    }
}
    class ChainedHashNode
    {
        Object element;
        Object key;
        ChainedHashNode link;
    }



