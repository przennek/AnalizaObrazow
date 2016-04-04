package AnalizaObrazow;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.utils.histogram.Histogram;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p on 07.03.16.
 */
public class HistStrechPlugin extends Plugin {

    private static int xSize, ySize;

    public HistStrechPlugin(int x, int y) {
        xSize = x;
        ySize = y;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Histogram histogram = new Histogram(imgIn, true);
        int[][] h = histogram.getHistogram();
        int min = findExtrs(h).get(0);
        int max = findExtrs(h).get(1);

        int[] LUT = new int[256];
        for(int i = 0; i < 256; i++) {
            LUT[i] = (255/(max - min)) * (i - min);
        }

        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                int valr = LUT[imgOut.getRed(i, j)];
                imgOut.setRGB(i, j, valr, valr, valr);
            }
        }
    }

    public List<Integer> findExtrs(int[][] hist) {
        List<Integer> out = new ArrayList<>();
        for (int j = 0; j < 256; j++) {
            if(hist[0][j] != 0) {
                out.add(j);
                break;
            }
        }
        for (int j = 255; j > -1; j--) {
            if(hist[0][j] != 0) {
                out.add(j);
                break;
            }
        }
        return out;
    }
}
