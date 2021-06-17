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
//        Comparator<StorageItem> employeeNameComparator
//                = Comparator.comparing(StorageItem::getName);
//
//        Arrays.sort(employees, employeeNameComparator);
        System.out.println(this.name);
    }

    public void addContent(String content){
        this.content = content;
    }

    public void printContent(){
        System.out.println(this.name + " Size: " + this.getSize()
                + "Mb Created: " + this.date);
        System.out.println(this.content); //Verify if needed
    }
}
