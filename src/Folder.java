import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;

public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemList;

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



    }

    public class CustomComparator implements Comparator<StorageItem> {
        @Override
        public int compare(StorageItem o1, StorageItem o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

    public boolean addItem(StorageItem item){
        for (int i = 0; i < this.itemList.size(); i++){
            if (this.itemList.get(i).getName() == item.getName()){
                return false; //Exists
            }
        }
        this.itemList.add(item);
        return true; //Not exists
    }

    public File findFile(String path){
        String parts[];
        boolean foundPart = false;
        File returnedFile;
        parts = path.split("/");
        for (int i = 0; i < parts.length; i++){
            for (int j = 0; j < this.itemList.size(); j++){
                if (parts[i] == this.itemList.get(j).getName()){
                    foundPart = true;
                    if (i == parts.length - 1){
                        if (this.itemList.get(j)) // to check if it's a file
                            returnedFile = this.itemList.get(j);
                    }
                    break;
                }
            }
            if (!foundPart)
                return null;
            foundPart = false;
        }
        return returnedFile;
    }
}
