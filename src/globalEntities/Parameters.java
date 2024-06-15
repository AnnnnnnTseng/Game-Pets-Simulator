package globalEntities;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

public class Parameters {
	public static final int DELAY = 1000;
	public static final int DURATION_OF_GENERATION_MS = 3000; // 3 seconds
	public static final String[] WEATHER_CLASSIFICATIONS =
			{"Sunny", "Windy", "Rainy"};
	public static final int MS_PER_REPORT = 10;
    //dog class - run()
    public static final int DOG_TWELVE_HR_SLEEP = 4000;
    public static final int SLEEP_ENERGY_INCREASE = 1;
    public static Map<String, List<Integer>> getSmoothieMenu() {
        Map<String, List<Integer>> produce = new HashMap<>();
        produce.put("apple", List.of(2, 95, 19));       // price, calorie, sugar
        produce.put("banana", List.of(1, 105, 14));
        produce.put("pineapple", List.of(3, 50, 10));
        produce.put("orange", List.of(2, 62, 12));
        produce.put("carrot", List.of(1, 25, 5));
        produce.put("broccoli", List.of(2, 55, 2));
        produce.put("spinach", List.of(1, 23, 0));
        produce.put("strawberry", List.of(3, 33, 4));
        produce.put("grape", List.of(4, 62, 15));
        produce.put("watermelon", List.of(5, 86, 17));
        return produce;
    }
}
