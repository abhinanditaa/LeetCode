import java.util.*;

class RandomizedCollection {

    private final List<Integer> list;
    private final Map<Integer, Set<Integer>> map;
    private final Random random;

    public RandomizedCollection() {
        list = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
    }

    public boolean insert(int val) {
        boolean isNew = !map.containsKey(val);

        map.computeIfAbsent(val, k -> new HashSet<>())
           .add(list.size());

        list.add(val);

        return isNew;
    }

    public boolean remove(int val) {
        Set<Integer> indices = map.get(val);

        if (indices == null || indices.isEmpty()) {
            return false;
        }

        // Get any occurrence of val
        int removeIndex = indices.iterator().next();
        indices.remove(removeIndex);

        int lastIndex = list.size() - 1;
        int lastValue = list.get(lastIndex);

        // If we're not already removing the last element
        if (removeIndex != lastIndex) {
            list.set(removeIndex, lastValue);

            Set<Integer> lastIndices = map.get(lastValue);
            lastIndices.remove(lastIndex);
            lastIndices.add(removeIndex);
        }

        list.remove(lastIndex);

        // Remove empty set from map
        if (indices.isEmpty()) {
            map.remove(val);
        }

        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }
}