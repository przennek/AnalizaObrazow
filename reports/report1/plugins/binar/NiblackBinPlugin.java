package AnalizaObrazow.reports.report1.plugins.binar;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.util.List;

import static AnalizaObrazow.reports.report1.util.ImageProcUtil.*;

/**
 * Created by p on 17.04.16.
 */
public class NiblackBinPlugin extends Plugin {
    private static final Integer MAX_PIXEL_VAL = 255;
    private static final Integer MIN_PIXEL_VAL = 0;
    private Double kParam;

    public NiblackBinPlugin(Double kParam) {
        this.kParam = kParam;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = 1; i < cpy.getWidth() - 1; i++) {
            for (int j = 1; j < cpy.getHeight() - 1; j++) {
                List<Integer> neighbours = get8Neighbours(cpy, i, j);
                Integer val = new Double(getAvg(neighbours) + kParam * getStanDev(neighbours)).intValue();
                if(cpy.getBlue(i, j) > val) {
                    imgOut.setRGB(i, j, MAX_PIXEL_VAL, MAX_PIXEL_VAL, MAX_PIXEL_VAL);
                } else {
                    imgOut.setRGB(i, j, MIN_PIXEL_VAL, MIN_PIXEL_VAL, MIN_PIXEL_VAL);
                }
            }
        }
    }
}
