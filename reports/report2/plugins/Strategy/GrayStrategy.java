package AnalizaObrazow.reports.report2.plugins.Strategy;

import kimage.image.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static AnalizaObrazow.reports.util.ImageProcUtil.getNeighbours;

/**
 * Created by p on 07.05.16.
 */
public class GrayStrategy extends AbstractOperationStrategy {
    private Image im;
    private Integer comp;
    private Hitter comparator;

    public interface Hitter {
        Boolean shouldHit(int a, int b);
    }

    public GrayStrategy(Image grayscaleImage, Integer bounds, Integer comp, Hitter comparator) {
        this.im = grayscaleImage;
        this.dDim = bounds;
        this.comp = comp;
        this.comparator = comparator;
    }

    @Override
    public Integer getFillVal(Integer x, Integer y) {
        List <Integer> out = getNeighbours(im, x, y, dDim).stream()
                .filter(i -> shouldBeFilled(i, comp) && !i.equals(0))
                .collect(Collectors.toList());

        return out.isEmpty() ? comp : Collections.min(out);
    }

    @Override
    public Integer getHitVal(Integer x, Integer y) {
        return comp;
    }

    @Override
    protected boolean shouldBeFilled(Integer hit, Integer comp) {
        return comparator.shouldHit(hit, comp);
    }
}
