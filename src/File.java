import java.sql.Timestamp;

public class File extends StorageItem {
    private String content;
    private final String ending;

    /** File object builder. */
    public File(String name, String ending){
        super(name, 0);
        this.content = "";
        this.ending = ending;
    }

    /** Returns this file's size. */
    @Override
    public int getSize(){ return this.content.length(); }

    /** Returns this file's name + suffix. */
    @Override
    public String getName(){ return (this.name + "." + this.ending); }

    /** Prints this file's tree (it's own name + suffix). */
    @Override
    public void printTree(SortingField field){
        System.out.println(this.getName());
    }

    /** Sets this file's content. */
    public void addContent(String content){ this.content = content; }

    /** Prints this file's properties + content. */
    public void printContent(){
        Timestamp temp = new Timestamp(this.getDate());
        String date = temp.toString();
        System.out.println(this.getName() + " Size: " + this.getSize()
                + "MB Created: " + date);
        System.out.println(this.content);
    }
}
