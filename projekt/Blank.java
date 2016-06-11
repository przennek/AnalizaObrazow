package AnalizaObrazow.projekt;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 11.06.16.
 */
public class Blank extends Plugin {
    private Integer startW;
    private Integer startH;
    private Integer endW;
    private Integer endH;
    private int blankColor;

    public Blank(int blankColor) {
        this.startW = 0;
        this.startH = 0;
        this.endW = 0;
        this.endH = 0;
        this.blankColor = blankColor;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        int height = endH > imgIn.getHeight() ? imgIn.getHeight() : endH;
        int width = endW > imgIn.getWidth() ? imgIn.getWidth() : endW;

        height = height == 0 ? imgIn.getHeight() : height;
        width = width == 0 ? imgIn.getWidth() : width;

        for (int i = startW; i < width; ++i) {
            for (int j = startH; j < height; ++j) {
                imgOut.setRGB(i, j, blankColor, blankColor, blankColor);
            }
        }
    }

    public Blank withEndH(final Integer endH) {
        this.endH = endH;
        return this;
    }

    public Blank withEndW(final Integer endW) {
        this.endW = endW;
        return this;
    }

    public Blank withStartH(final Integer startH) {
        this.startH = startH;
        return this;
    }

    public Blank withStartW(final Integer startW) {
        this.startW = startW;
        return this;
    }

}
