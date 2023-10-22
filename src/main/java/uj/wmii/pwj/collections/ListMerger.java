package uj.wmii.pwj.collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListMerger {
    public static List<Object> mergeLists(List<?> l1, List<?> l2) {
        if (l1 == null && l2 == null) return Collections.unmodifiableList(new ArrayList<>()); // empty list
        else if (l1 == null) return List.copyOf(l2);
        else if (l2 == null) return List.copyOf(l1);

        ArrayList<Object> mergeResult = new ArrayList<>();

        int size1 = l1.size();
        int size2 = l2.size();
        int maxSize = Math.max(size1, size2);

        for (int i = 0; i < maxSize; i++) {
            if (i < size1)
                mergeResult.add(l1.get(i));
            if (i < size2)
                mergeResult.add(l2.get(i));
        }
        return Collections.unmodifiableList(mergeResult);
    }
}
