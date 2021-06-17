import java.sql.Timestamp;

public abstract class StorageItem {
    protected String name;
    final long date;
    private int size;

    /** StorageItem object builder. */
    public StorageItem(String name, int size){
        Timestamp start = Timestamp.valueOf("2017-01-01 00:00:00");
        Timestamp end = Timestamp.valueOf("2021-12-31 23:59:59");
        long tsStart = start.getTime();
        long tsEnd = end.getTime();
        long gap = tsEnd - tsStart;
        long tempLong = Main.rnd.nextLong();
        this.name = name;
        this.date = tsStart + (tempLong % gap);
        this.size = size;
    }

    public abstract void printTree(SortingField field);

    /** Returns this card's numeric value. */
    public String getName() {
        return this.name;
    }

    /** Returns this card's shape/symbol. */
    public long getDate() {
        return this.date;
    }

    /** Returns this card's shape/symbol. */
    public abstract int getSize();

    /** Returns this card's numeric value. */
    public void setName(String name) {
        this.name = name;
    }

    /** Returns this card's shape/symbol. */
    public void setSize() { this.size = size; }


}
