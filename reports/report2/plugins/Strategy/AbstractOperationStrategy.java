package AnalizaObrazow.reports.report2.plugins.Strategy;

import kimage.image.Image;

/**
 * Created by p on 04.05.16.
 */
public abstract class AbstractOperationStrategy implements IOperationStrategy {
    private Integer[][] sE;
    protected Integer fillValue;
    protected Integer dDim;

    protected AbstractOperationStrategy() {
    }

    public AbstractOperationStrategy(Integer[][] sE, Integer fillVal) {
        this.sE = sE;
        this.fillValue = fillVal;
        this.dDim = sE.length / 2;
    }

    @Override
    public Integer getHitVal(Integer x, Integer y) {
        return sE[x][y];
    }

    @Override
    public Integer getFillVal(Integer x, Integer y) {
        return fillValue;
    }

    @Override
    public Integer getdDim() {
        return dDim;
    }

    @Override
    public void apply(final Image im, Image cpy, Integer x, Integer y, Integer n) {
        Boolean shouldFill = false;
        Integer newVal = null;
        for (int i = x - n, z = 0; i <= x + n; i++, z++) {
            for (int j = y - n, v = 0; j <= y + n; j++, v++) {
                if (getHitVal(z, v).equals(im.getBlue(i, j))) {
                    shouldFill = true;
                    newVal = getFillVal(x, y);
                }
            }
            if (shouldFill) {
                cpy.setRGB(x, y, newVal, newVal, newVal);
                break;
            }
        }
    }

    abstract protected boolean shouldBeFilled(Integer hit, Integer comp);
}
