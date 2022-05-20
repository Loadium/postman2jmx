package com.loadium.postman2jmx.utils;

import com.loadium.postman2jmx.model.postman.PostmanCollection;
import com.loadium.postman2jmx.model.postman.PostmanItem;

import java.util.ArrayList;
import java.util.List;

public class PostmanCollectionUtils {

    private static void getItem(PostmanItem item, List<PostmanItem> itemList) {
        if (item.getItems().size() == 0 && item.getRequest() != null) {
            itemList.add(item);
        }

        for (PostmanItem i : item.getItems()) {
            getItem(i, itemList);
        }
    }

    public static List<PostmanItem> getItems(PostmanCollection postmanCollection) {
        List<PostmanItem> items = new ArrayList<>();

        for (PostmanItem item : postmanCollection.getItems()) {
            getItem(item, items);
        }
        return items;
    }

}
