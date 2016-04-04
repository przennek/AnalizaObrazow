package AnalizaObrazow;

import kimage.image.Image;
import kimage.plugin.Plugin;

class GrayscalePlugin extends Plugin {

    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < imgIn.getWidth(); ++i) {
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                int buff = (imgIn.getRed(i, j) + imgIn.getGreen(i, j) + imgIn.getBlue(i, j)) / 3;
                imgIn.setRGB(i, j, buff, buff, buff);
            }
        }
    }
}