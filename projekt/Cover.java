package AnalizaObrazow.projekt;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 10.06.16.
 */
public class Cover extends Plugin {
    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < imgIn.getWidth(); ++i) {
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                if(!(imgIn.getBlue(i, j) == 0 && imgIn.getRed(i, j) == 0 && imgIn.getGreen(i, j) == 0)) {
                    imgOut.setRGB(i, j, imgIn.getRed(i, j), imgIn.getGreen(i, j), imgIn.getBlue(i, j));
                }
            }
        }
    }
}
