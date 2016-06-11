package AnalizaObrazow.projekt;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 09.06.16.
 */
public class Substract extends Plugin {
    private Integer startW = 0;
    private Integer startH = 0;

    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = startW; i < imgIn.getWidth(); ++i) {
            for (int j = startH; j < imgIn.getHeight(); ++j) {
                Integer var = imgIn.getBlue(i, j);
                if(var == 0) {
                    imgOut.setRGB(i, j, 0, 0, 0);
                }
            }
        }
    }

    public Substract withStartW(final Integer startW) {
        this.startW = startW;
        return this;
    }

    public Substract withStartH(final Integer startH) {
        this.startH = startH;
        return this;
    }
}
