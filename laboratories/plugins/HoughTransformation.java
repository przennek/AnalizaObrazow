package AnalizaObrazow.laboratories.plugins;

import kimage.image.Image;
import kimage.plugin.Plugin;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p on 16.05.16.
 */
public class HoughTransformation extends Plugin {
    private Integer borderValue;

    public HoughTransformation(Integer borderValue) {
        this.borderValue = borderValue;
    }

    public HoughTransformation() {
        this.borderValue = 0;
    }

    public HoughTransformation withBorderValue(Integer borderValue) {
        this.borderValue = borderValue;
        return this;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Integer width = imgIn.getWidth();
        Integer height = imgIn.getHeight();
        List<Integer[]> borders = new ArrayList<>();

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (imgIn.getBlue(i, j) == borderValue) {
                    borders.add(new Integer[] {i, j});
                }
            }
        }
    }
}
