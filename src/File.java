import java.sql.Timestamp;

public class File extends StorageItem {
    private String content;
    private String ending;

    /** StorageItem object builder. */
    public File(String name, String ending){
        super(name, 0);
        this.content = "";
        this.ending = ending;
    }

    /** Returns this card's shape/symbol. */
    @Override
    public int getSize(){
        return this.content.length();
    }

    @Override
    public String getName(){
        return (this.name + "." + this.ending);
    }

    @Override
    public void printTree(SortingField field){
        System.out.println(this.getName());
    }

    public void addContent(String content){
        this.content = content;
    }

    public void printContent(){
        Timestamp temp = new Timestamp(this.getDate());
        String date = temp.toString();
        System.out.println(this.getName() + " Size: " + this.getSize()
                + "MB Created: " + date);
        System.out.println(this.content); //Verify if needed
    }
}
