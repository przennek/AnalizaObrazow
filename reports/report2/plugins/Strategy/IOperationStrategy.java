package AnalizaObrazow.reports.report2.plugins.Strategy;

import kimage.image.Image;

/**
 * Created by p on 04.05.16.
 */
public interface IOperationStrategy {
    void apply(Image im, Image cpy, Integer x, Integer y, Integer n);
    Integer getHitVal(Integer x, Integer y);
    Integer getFillVal(Integer x, Integer y);
    Integer getdDim();
}
