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
    private Integer se;

    public MorphPlugin(IOperationStrategy strategy, Integer se) {
        this.strategy = strategy;
        this.se = se;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        imgIn = imgIn.copy();
        new GrayscalePlugin().process(imgIn, imgIn);
        for (int i = se; i < imgIn.getWidth() - se; i++) {
            for (int j = se; j < imgIn.getHeight() - se; j++) {
                strategy.apply(imgIn, imgOut, i, j, se);
            }
        }
    }
}
