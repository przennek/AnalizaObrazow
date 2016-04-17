package AnalizaObrazow.reports.report1.plugins.context.binar;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.util.List;

import static AnalizaObrazow.reports.report1.util.ImageProcUtil.*;

/**
 * Created by p on 17.04.16.
 */
public class SauvolaBinPlugin extends Plugin {
    private static final Integer MAX_PIXEL_VAL = 255;
    private static final Integer MIN_PIXEL_VAL = 0;
    private static final Double R = 128.0;

    private Double kParam;
    private Integer bounds;

    public SauvolaBinPlugin(Double kParam, Integer bounds) {
        this.kParam = kParam;
        this.bounds = bounds;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = bounds; i < cpy.getWidth() - bounds; i++) {
            for (int j = bounds; j < cpy.getHeight() - bounds; j++) {
                List<Integer> neighbours = getNeighbours(cpy, i, j, bounds);
                Double avg = getAvg(neighbours);
                Double sdev = getStanDev(neighbours);
                Double treshold = avg * (1 + kParam * ((sdev / R) - 1));
                if(cpy.getBlue(i, j) > treshold) {
                    imgOut.setRGB(i, j, MAX_PIXEL_VAL, MAX_PIXEL_VAL, MAX_PIXEL_VAL);
                } else {
                    imgOut.setRGB(i, j, MIN_PIXEL_VAL, MIN_PIXEL_VAL, MIN_PIXEL_VAL);
                }
            }
        }
    }
}
