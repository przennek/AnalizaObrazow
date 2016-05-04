package AnalizaObrazow.reports.report2.plugins.Strategy;

import kimage.image.Image;

/**
 * Created by p on 04.05.16.
 */
abstract class AbstractOperationStrategy implements IOperationStrategy {
    @Override
    public void apply(final Image im, Image cpy, Integer x, Integer y, Integer n) {
        Boolean shouldFill = false;
        for (int i = x - n; i <= x + n; i++) {
            for (int j = y - n; j <= y + n; j++) {
                if (getHitVal(x, y).equals(im.getBlue(i, j))) {
                    shouldFill = true;
                }
            }
            if (shouldFill) {
                Integer newVal = getFillVal(x, y);
                cpy.setRGB(x, y, newVal, newVal, newVal);
                break;
            }
        }
    }
}
