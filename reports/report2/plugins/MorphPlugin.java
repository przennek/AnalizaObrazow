package AnalizaObrazow.reports.report2.plugins;

import AnalizaObrazow.laboratories.plugins.GrayscalePlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.IOperationStrategy;
import kimage.image.Image;
import kimage.plugin.Plugin;

/**
 * Created by p on 04.05.16.
 */
public class MorphPlugin extends Plugin {
    private IOperationStrategy strategy;
    private Integer dDim;

    public MorphPlugin(IOperationStrategy strategy) {
        this.strategy = strategy;
        this.dDim = strategy.getdDim();
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        imgIn = imgIn.copy();
        new GrayscalePlugin().process(imgIn, imgIn);
        for (int i = dDim; i < imgIn.getWidth() - dDim; i++) {
            for (int j = dDim; j < imgIn.getHeight() - dDim; j++) {
                strategy.apply(imgIn, imgOut, i, j, dDim);
            }
        }
    }
}
