package AnalizaObrazow.reports.report1.util;

import kimage.image.Image;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by p on 17.04.16.
 */
public class ImageProcUtil {
    public static List<Integer> getNeighbours(Image im, int x, int y, int bounds) {
        List<Integer> out = new ArrayList<>();
        for (int i = -bounds; i <= bounds; i++) {
            for (int j = -bounds; j <= bounds; j++) {
                out.add(im.getBlue(x + i, y + j));
            }
        }
        return out;
    }

    public static Double getAvg(List<Integer> sur) {
        Integer n = 0;
        Double sum = 0.0;
        for (Integer i : sur) {
            sum += i;
            n++;
        }
        return sum / n;
    }

    public static Double getStanDev(List<Integer> sur) {
        Integer n = 0, stanDev = 0;
        Double avg = getAvg(sur);
        Double buffer;
        for (Integer i : sur) {
            buffer = Math.pow((i - avg), 2);
            stanDev += buffer.intValue();
            n++;
        }
        buffer = Math.sqrt((double) (stanDev / n));
        return buffer;
    }

    public static Double getMedian(List<Integer> sur) {
        Integer size = sur.size();
        Collections.sort(sur);
        if (size % 2 == 0) {
            return (sur.get(size / 2) + sur.get(size / 2 + 1)) / 2.0;
        } else {
            return new Double(sur.get(size / 2));
        }
    }
}
