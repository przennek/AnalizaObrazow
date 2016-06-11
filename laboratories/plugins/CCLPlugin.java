package AnalizaObrazow.laboratories.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

import java.util.*;

/**
 * Created by p on 09.05.16.
 */
public class CCLPlugin extends Plugin { // Connected Component labeling
    @Override
    public void process(Image imgIn, Image imgOut) {
        // background init
        Integer height = imgIn.getHeight(), width = imgIn.getWidth();
        Integer[][] labels = new Integer[height][width];
        Integer[][] img = new Integer[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                labels[i][j] = 0;
                img[i][j] = imgIn.getBlue(i, j);
            }
        }

        // parent child relation list
        Map<Integer, Set<Integer>> linked = new HashMap<>();

        // alg itself
        Integer currentLabel = 1;

        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {
                if (imgIn.getBlue(i, j) != 0) {
                    List<Integer> neighbours = getNeighbours(labels, i, j, 0);
                    if (neighbours.isEmpty()) {
                        Set<Integer> current = new HashSet<>();
                        current.add(currentLabel);
                        linked.put(currentLabel, current);
                        labels[i][j] = currentLabel++;
                    } else {
                        List<Integer> L = getNeighbours(labels, i, j, -1);
                        Integer minLab = Collections.min(L);
                        labels[i][j] = minLab;
                        for (Integer label : L) {
                            linked.get(label).addAll(L);
                        }
                    }
                }
            }
        }
        for (int i = 1; i < height - 1; i++) {
            for (int j = 1; j < width - 1; j++) {

            }
        }
    }

    public static List<Integer> getNeighbours(Integer[][] labels, int i, int j, int value) {
        List<Integer> neighbours = new ArrayList<>();
        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                Integer current = labels[x + i][y + j];
                if (current.equals(value)) {
                    neighbours.add(current);
                } else if (value == -1) {
                    neighbours.add(current);
                }
            }
        }
        return neighbours;
    }
}
