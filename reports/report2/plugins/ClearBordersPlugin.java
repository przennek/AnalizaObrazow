package AnalizaObrazow.reports.report2.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 07.05.16.
 */
public class ClearBordersPlugin extends Plugin {
    private Integer bounds;

    public ClearBordersPlugin(Integer bounds) {
        this.bounds = bounds;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        // upper
        for(int i = imgIn.getWidth() - bounds; i < imgIn.getWidth(); i++) {
            for(int j = 0; j < imgIn.getHeight(); j++) {
                imgOut.setRGB(i, j, 0, 0, 0);
            }
        }
        // lower
        for(int i = 0; i < bounds; i++) {
            for(int j = 0; j < imgIn.getHeight(); j++) {
                imgOut.setRGB(i, j, 0, 0, 0);
            }
        }
        // left
        for(int i = 0; i < imgIn.getWidth(); i++) {
            for(int j = 0; j < bounds; j++) {
                imgOut.setRGB(i, j, 0, 0, 0);
            }
        }
        // right
        for(int i = 0; i < imgIn.getWidth(); i++) {
            for(int j = imgIn.getHeight() - bounds; j < imgIn.getHeight(); j++) {
                imgOut.setRGB(i, j, 0, 0, 0);
            }
        }
    }
}
