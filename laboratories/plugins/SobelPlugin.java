package AnalizaObrazow.laboratories.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.util.Arrays;
import java.util.List;

import static AnalizaObrazow.reports.util.ImageProcUtil.getNeighbours;

/**
 * Created by p on 25.04.16.
 */
public class SobelPlugin extends Plugin {
    private List<Integer> h1;

    public SobelPlugin() {
        this.h1 = Arrays.asList(1, 2, 1, 0, 0, 0, -1, -2, -1);
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = 1; i < cpy.getWidth() - 1; i++) {
            for (int j = 1; j < cpy.getHeight() - 1; j++) {
                Integer val = putMask(cpy, this.h1, i, j);
                imgOut.setRGB(i, j, val, val, val);
            }
        }
    }

    private Integer putMask(Image im, List<Integer> mask, int x, int y) {
        List<Integer> neigh = getNeighbours(im, x, y, 1);
        Integer out = 0;
        Integer counter = 0;

        for (int i : neigh) {
            out += mask.get(counter++) * i;
        }
        System.out.println(out);
        return out;
    }
}
