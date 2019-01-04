package in.hiaccounts.model.retailpos;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Admin on 2/28/2017.
 */

public class ItemsDataSource
    {
        //Singleton pattern
        private static ItemsDataSource datasource = null;

        private List<Object> data = null;

//    private static final int SIZE = 74;

    public static ItemsDataSource getInstance()
    {
        if (datasource == null)
        {
            datasource = new ItemsDataSource();
        }
        return datasource;
    }

    private ItemsDataSource()
    {/*
        data = new ArrayList<String>(SIZE);
        for (int i =1 ; i <= SIZE; i++)
        {
            data.add("row " + i);
        }
    */}

    public int getSize()
    {
        return data.size();
    }

    /**
     * Returns the elements in a <b>NEW</b> list.
     */
    public List<Object> getData(int offset, int limit)
    {
        List<Object> newList = new ArrayList<>(limit);
        int end = offset + limit;
        if (end > data.size())
        {
            end = data.size();
        }
        newList.addAll(data.subList(offset, end));
        return newList;
    }

        /**
         * Returns the elements in a <b>NEW</b> list.
         */
        public List<Object> getData() {
            if (data!=null){
                return data;
            }
            return null;
        }


        public void putItemList(List<Object> itemList) {

        data=new ArrayList<>();
        if (data!=null){
            data.clear();
        }
        data=itemList;

    }


}
