import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemList;
    private static int depth = 0;

    public Folder(String name){
        super(name, 0);
        this.itemList = new ArrayList<>();
    }

    /** Returns this card's shape/symbol. */
    @Override
    public int getSize(){
        int sum = 0;
        for (int i = 0; i < this.itemList.size(); i++){
            sum += this.itemList.get(i).getSize();
        }
        return sum;
    }

    @Override
    public void printTree(SortingField field){
        StorageItem[] itemsArray = new StorageItem[this.itemList.size()];
        itemsArray = this.itemList.toArray(itemsArray);
        Comparator<StorageItem> nameComparator;
        if (field == SortingField.DATE){
            nameComparator = Comparator.comparing
                    (StorageItem::getDate).thenComparing(StorageItem::getName);
        } else {
            if (field == SortingField.NAME){
                nameComparator = Comparator.comparing(StorageItem::getName)
                        .thenComparing(StorageItem::getName);
            }
            else {
                nameComparator = Comparator.comparing(StorageItem::getSize)
                        .thenComparing(StorageItem::getName);
            }
        }
        Arrays.sort(itemsArray, nameComparator);
        System.out.println(this.getName());
        depth++;
        for (int i = 0; i < itemsArray.length; i++){
            for (int j = 0; j < depth; j++){
                System.out.print("|    ");
            }
            itemsArray[i].printTree(field);
        }
        depth--;
    }

    public boolean addItem(StorageItem item){
        for (int i = 0; i < this.itemList.size(); i++){
            if (this.itemList.get(i).getName().equals(item.getName())){
                return false; //Exists
            }
        }
        this.itemList.add(item);
        return true; //Not exists
    }

    public File findFile(String path){
        String parts[];
        parts = path.split("/");

        int returnedIndex = containsPart(parts[0]);
        if(returnedIndex == -1)
            return null;

        if (parts.length == 1){
            for (StorageItem item: this.itemList){
                if (item.getName().equals(parts[0]))
                    return (File) item;
            }
        }

        StringBuilder nextPart = new StringBuilder();
        for (int i = 1; i < parts.length; i++){
            String tempPart = parts[i] + "/";
            nextPart.append(tempPart);
        }

        return ((Folder) this.itemList.get(returnedIndex)).
                findFile(nextPart.toString());
    }

    public int containsPart(String part){
        for (int j = 0; j < this.itemList.size(); j++){
            if (part.equals(this.itemList.get(j).getName()))
                return j;
        }
        return -1;
    }
}
