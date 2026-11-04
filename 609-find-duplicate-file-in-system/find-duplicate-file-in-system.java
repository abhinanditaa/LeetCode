
import java.util.*;

class Solution {
    public List<List<String>> findDuplicate(String[] paths) {

        Map<String, List<String>> map = new HashMap<>();

        for (String path : paths) {

            String[] parts = path.split(" ");
            String directory = parts[0];

            for (int i = 1; i < parts.length; i++) {

                String file = parts[i];

                int index = file.indexOf('(');

                String fileName = file.substring(0, index);
                String content = file.substring(index + 1, file.length() - 1);

                String fullPath = directory + "/" + fileName;

                map.computeIfAbsent(content, k -> new ArrayList<>())
                   .add(fullPath);
            }
        }

        List<List<String>> result = new ArrayList<>();

        for (List<String> files : map.values()) {
            if (files.size() > 1) {
                result.add(files);
            }
        }

        return result;
    }
}
