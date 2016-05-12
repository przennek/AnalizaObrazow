package AnalizaObrazow.reports.report2.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 12.05.16.
 */
public class MergeHitsPlugin extends Plugin {
    private Integer addingValue;
    private Integer background;

    public MergeHitsPlugin(Integer addingValue, Integer background) {
        this.addingValue = addingValue;
        this.background = background;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image img2 = imgOut.copy();
        for (int i = 0; i < imgIn.getWidth(); i++) {
            for (int j = 0; j < imgIn.getHeight(); j++) {
                imgOut.setRGB(i, j, background, background, background);
                if (imgIn.getBlue(i,j) == addingValue || img2.getBlue(i,j) == addingValue) {
                    imgOut.setRGB(i, j, addingValue, addingValue, addingValue);
                }
            }
        }
    }
}
