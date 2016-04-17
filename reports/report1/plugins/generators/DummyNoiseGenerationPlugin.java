package AnalizaObrazow.reports.report1.plugins.generators;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

/**
 * Created by p on 17.04.16.
 */
public class DummyNoiseGenerationPlugin extends Plugin {
    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = 0; i < cpy.getWidth(); i++) {
            for (int j = 0; j < cpy.getHeight(); j++) {
                if (i % 2 == 0) {
                    imgOut.setRGB(i, j, 0, 0, 0);
                }
            }
        }
    }
}
