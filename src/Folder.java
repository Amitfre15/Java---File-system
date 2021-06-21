import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


public class Folder extends StorageItem {
    private ArrayList<StorageItem> itemList;
    private static int depth = 0;

    /** Folder object builder. */
    public Folder(String name){
        super(name, 0);
        this.itemList = new ArrayList<>();
    }

    /** Returns this folder's size. */
    @Override
    public int getSize(){
        int sum = 0;
        for (StorageItem item: this.itemList){
            sum += item.getSize();
        }
        return sum;
    }

    /**
     * Prints this folder's tree (its own name + its items' trees).
     *
     * @param field The key to sort the items by
     */
    @Override
    public void printTree(SortingField field){
        // cast the items list to regular array in order to work with
        // comparator object
        StorageItem[] itemsArray = new StorageItem[this.itemList.size()];
        itemsArray = this.itemList.toArray(itemsArray);

        Comparator<StorageItem> nameComparator;
        // create the required comparator and assign it before usage
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

        // print this folder's tree, starting by its name
        System.out.println(this.getName());
        depth++; // indicates how many sub_folders we went into
        for (StorageItem item: itemsArray){
            for (int j = 0; j < depth; j++){
                System.out.print("|    ");
            }
            item.printTree(field);
        }
        depth--;
    }

    /**
     * Add an item to this folder (if there's no existing file in it with the
     * same name and suffix).
     *
     * @param item The item to add
     * @return The item was added - true, The item wasn't added (there's an
     * existing file with same name and suffix) - false
     */
    public boolean addItem(StorageItem item){
        for (StorageItem existingItem: this.itemList){
            if (existingItem.getName().equals(item.getName())){
                return false; //Exists
            }
        }
        this.itemList.add(item);
        return true; //Not exists
    }

    /**
     * Find a file in a path starting from this folder
     *
     * @param path The path to find the file in
     * @return The file, if not found - null
     */
    public File findFile(String path){
        // enables check of every part of the path separately
        String parts[] = path.split("/");

        int returnedIndex = containsPart(parts[0]);
        if(returnedIndex == -1) // file not found
            return null;

        if (parts.length == 1)
            // means we arrived to the file at the end of the path
            return ((File) this.itemList.get(returnedIndex));

        // we found a folder and not a file
        // reconstruct the path without the found folder
        StringBuilder nextPart = new StringBuilder();
        for (int i = 1; i < parts.length; i++){
            String tempPart = parts[i] + "/";
            nextPart.append(tempPart);
        }

        // search for the file in the found folder
        return ((Folder) this.itemList.get(returnedIndex)).
                findFile(nextPart.toString());
    }

    /**
     * Check if an item exists in this folder
     *
     * @param part The name of the item to find
     * @return item's index in this folder's items list, if not found - (-1)
     */
    public int containsPart(String part){
        for (int j = 0; j < this.itemList.size(); j++){
            if (part.equals(this.itemList.get(j).getName()))
                return j;
        }
        return -1;
    }
}
