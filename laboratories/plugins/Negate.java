package AnalizaObrazow.laboratories.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 09.06.16.
 */
public class Negate extends Plugin {
    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < imgIn.getWidth(); i++) {
            for (int j = 0; j < imgIn.getHeight(); j++) {
                Integer red = 255 - imgIn.getRed(i, j);
                Integer green = 255 - imgIn.getGreen(i, j);
                Integer blue = 255 - imgIn.getBlue(i, j);
                imgOut.setRGB(i, j, red, green, blue);
            }
        }
    }
}
