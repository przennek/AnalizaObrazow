package AnalizaObrazow.reports.report2.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 12.05.16.
 * TODO usunąć wklej i zwyciężaj pattern
 */
public class SubstractHitsPlugin extends Plugin {
    private Integer addingValue;
    private Integer background;

    public SubstractHitsPlugin(Integer addingValue, Integer background) {
        this.addingValue = addingValue;
        this.background = background;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image img2 = imgOut.copy();
        for (int i = 0; i < imgIn.getWidth(); i++) {
            for (int j = 0; j < imgIn.getHeight(); j++) {
                imgOut.setRGB(i, j, background, background, background);
                if ((imgIn.getBlue(i,j) != addingValue && img2.getBlue(i,j) == addingValue)
                        || (imgIn.getBlue(i,j) != addingValue && img2.getBlue(i,j) == addingValue)) {
                    imgOut.setRGB(i, j, addingValue, addingValue, addingValue);
                }

            }
        }
        for (int i = 0; i < imgIn.getWidth(); i++) {
            for (int j = 0; j < imgIn.getHeight(); j++) {
                Integer color = imgOut.getBlue(i, j) == 0 ? 255 : 0;
                imgOut.setRGB(i, j, color, color, color);
            }
        }
    }
}
