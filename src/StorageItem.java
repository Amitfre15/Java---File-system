import java.sql.Timestamp;

public abstract class StorageItem {
    protected String name;
    final long date;
    private int size;

    /** StorageItem object builder. */
    public StorageItem(String name, int size){
        // randomly create a valid date and represent it in milliseconds
        Timestamp start = Timestamp.valueOf("2017-01-01 00:00:00");
        Timestamp end = Timestamp.valueOf("2021-12-31 23:59:59");
        long tsStart = start.getTime();
        long tsEnd = end.getTime();
        long gap = tsEnd - tsStart;
        long tempLong = Math.abs(Main.rnd.nextLong());

        this.name = name;
        this.date = tsStart + (tempLong % gap);
        this.size = size;
    }

    public abstract void printTree(SortingField field);

    /** Returns this item's name. */
    public String getName() {
        return this.name;
    }

    /** Returns this item's creation date. */
    public long getDate() { return this.date; }

    /** Returns this item's size. */
    public abstract int getSize();

    /** Set this item name. */
    public void setName(String name) { this.name = name; }

    /** Set this item size. */
    public void setSize(int size) { this.size = size; }
}
