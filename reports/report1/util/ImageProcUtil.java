package AnalizaObrazow.reports.report1.util;

import kimage.image.Image;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p on 17.04.16.
 */
public class ImageProcUtil {
    public static List<Integer> get8Neighbours(Image im, int x, int y) {
        List<Integer> out = new ArrayList<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
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
}
