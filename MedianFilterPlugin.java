package AnalizaObrazow;

import kimage.image.Image;
import kimage.plugin.Plugin;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by p on 21.03.16.
 */
public class MedianFilterPlugin extends Plugin {

    private int width, height;

    public MedianFilterPlugin(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {

            }
        }
    }

    Integer median(Image img, int x, int y, int mlen) {
        List<Integer> out = new ArrayList<>();
        for (int i = x; i < x + mlen; i++) {
            for (int j = y; j < y + mlen; j++) {
                out.add(img.getBlue(i, j));
            }
        }
        Integer[] out2 = (Integer[]) out.toArray();
        Arrays.sort(out2);
        return out2[(mlen/2)+1];
    }
}
