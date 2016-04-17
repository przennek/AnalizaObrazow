package AnalizaObrazow.reports.report1.plugins.context;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import static AnalizaObrazow.reports.report1.util.ImageProcUtil.getMedian;
import static AnalizaObrazow.reports.report1.util.ImageProcUtil.getNeighbours;

/**
 * Created by p on 17.04.16.
 */
public class MedianFilterPlugin extends Plugin {
    private Integer bounds;

    public MedianFilterPlugin(Integer bounds) {
        this.bounds = bounds;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = bounds; i < cpy.getWidth() - bounds; i++) {
            for (int j = bounds; j < cpy.getHeight() - bounds; j++) {
                Integer val = getMedian(getNeighbours(imgIn, i, j, bounds)).intValue();
                imgOut.setRGB(i, j, val, val, val);
            }
        }
    }
}
