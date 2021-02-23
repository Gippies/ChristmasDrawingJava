import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PairingRandomizer {
    private Map<String, List<String>> exclusionMap;
    private Map<String, String> resultMap;

    public PairingRandomizer() {
        exclusionMap = new HashMap<>();
        resultMap = new HashMap<>();
    }

    public void setExclusionMap(Map<String, List<String>> exclusionMap) {
        this.exclusionMap = exclusionMap;
    }

    public void randomize() {
        List<String> nameList = new ArrayList<>(exclusionMap.keySet());
        List<String> pairingList = new ArrayList<>(nameList);
        boolean paired = false;

        while (!paired) {
            Collections.shuffle(pairingList);
            paired = true;
            for (int i = 0; i < nameList.size(); i++) {
                if (exclusionMap.get(nameList.get(i)).contains(pairingList.get(i)) || nameList.get(i).equals(pairingList.get(i))) {
                    paired = false;
                    System.out.println("Exclusion pair detected! Re-shuffling...");
                    break;
                }
            }
        }


        System.out.println(nameList);
        System.out.println(pairingList);
    }
}
