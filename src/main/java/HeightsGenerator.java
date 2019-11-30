import java.util.HashMap;

public class HeightsGenerator {
    private HashMap<Integer, Double> heights;
    private int r;
    /**
     *
     * @param r
     * n = 2^k
     */
    public HeightsGenerator(int r) {
        heights = new HashMap<>();
        this.r = r;
    }

    public double getHeight(int v) {
        if (heights.containsKey(v)) {
            return heights.get(v);
        }
        else if (v == 0 || v == r) {
            heights.put(v, 0d);
        }
        else {
            int size = 1;
            while ((v & size) == 0) {
                size <<= 1;
            }
            double height = (getHeight(v - size) + getHeight(v + size)) / 2;
            double roughness = 0.01;
            height += (Math.random() - 0.5) * size * roughness;
            height = Math.max(0, Math.min(height, 1));
            heights.put(v, height);
        }
        return heights.get(v);
    }
}
