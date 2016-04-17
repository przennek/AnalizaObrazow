package AnalizaObrazow.reports.report1.plugins.noncontext;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 17.04.16.
 */
public class ReflectionPlugin extends Plugin {
    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        Integer width = imgIn.getWidth(), height = imgIn.getHeight();
        for (int i = 0, k = width - 1; i < width && k >= 0; i++, k--) {
            System.out.println("i: " + i + " k: " + k);
            for (int j = 0; j < height; j++) {
                imgOut.setRGB(i, j, cpy.getRed(k, j), cpy.getGreen(k, j), cpy.getBlue(k, j));
            }
        }
    }
}
