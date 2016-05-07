package AnalizaObrazow.reports.report2.plugins.Strategy;

import kimage.image.Image;

/**
 * Created by p on 04.05.16.
 */
abstract class AbstractOperationStrategy implements IOperationStrategy {
    protected Integer[][] sE;
    protected Integer fillVal;
    protected Integer dDim;


    public AbstractOperationStrategy(Integer seVal, Integer n) {
        sE = new Integer[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                sE[i][j] = seVal;
            }
        }
    }

    protected AbstractOperationStrategy() {
    }

    @Override
    public void apply(final Image im, Image cpy, Integer x, Integer y, Integer n) {
        Boolean shouldFill = false;
        for (int i = x - n, z = 0; i <= x + n; i++, z++) {
            for (int j = y - n, v = 0; j <= y + n; j++, v++) {
                if (getHitVal(z, v).equals(im.getBlue(i, j))) {
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

    @Override
    public Integer getHitVal(Integer x, Integer y) {
        return sE[x][y];
    }

    @Override
    public Integer getFillVal(Integer x, Integer y) {
        return fillVal;
    }

    @Override
    public Integer getdDim() {
        return dDim;
    }
}
