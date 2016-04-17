package AnalizaObrazow.reports.report1.plugins.noncontext;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 17.04.16.
 */
public class ShuffleColorIntensityPlugin extends Plugin {
    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < imgOut.getHeight(); i++) {
            for (int j = 0; j < imgOut.getWidth(); j++) {
                imgOut.setRGB(i, j, imgIn.getGreen(i, j), imgIn.getBlue(i, j), imgIn.getRed(i, j));
            }
        }
    }
}
