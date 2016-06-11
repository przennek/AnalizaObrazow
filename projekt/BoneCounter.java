package AnalizaObrazow.projekt;

import AnalizaObrazow.laboratories.plugins.BinPlugin;
import AnalizaObrazow.laboratories.plugins.Negate;
import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.component.ConnectedComponent;
import kimage.plugins.edge.Sobel;
import kimage.plugins.edge.Sobel4M;
import kimage.plugins.morphology.Dilation;
import kimage.plugins.morphology.Erosion;
import kimage.plugins.statistical.Median;
import kimage.utils.gui.ImageFrame;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by p on 23.05.16.
 */

public class BoneCounter {
    private static final String BONE_FILENAME = "./src/AnalizaObrazow/res/in.jpg";
    private static final Substract substract = new Substract();

    public static void main(String[] args) {
        boneLabeling();
    }

    private static Image getLowerBorders() {
        Image orginal = new Image(BONE_FILENAME);

        Image borders = getBorders(orginal, 30);
        new Erosion().process(borders);
        new Median().process(borders);
        new Blank(255).withEndH(borders.getHeight()/41 * 30).process(borders);

        return borders;
    }

    private static void boneLabeling() {
        Image im = new Image(BONE_FILENAME);
        Image im_p = new Image(BONE_FILENAME);
        Image out = new Image(BONE_FILENAME);
        Image orginal = new Image(BONE_FILENAME);

        Image borders = applyBorders(im, 60);

        // myk z saturacją
        extract(im);
        im = substract(im);

        substract.withStartH(im.getHeight()/35 * 18).process(im, im_p);

        // binaryzacja
        List<Plugin> pluginList = new LinkedList<>();
        pluginList.add(new BinPlugin().withThreshold(110));
        pluginList.add(new Erosion());
        pluginList.add(new Negate());
        pluginList.stream().forEach(plugin -> plugin.process(im_p));
        pluginList.clear();

        // odejmij krawędzie od zbinaryzowanego obrazka
        Negate negation = new Negate();
        negation.process(im_p);
        substract.withStartH(0).process(borders, im_p);
        negation.process(im_p);

        // i dolne krawedzie
        negation.process(im_p);
        Image lowerBorders = getLowerBorders();
        substract.withStartH(0).process(lowerBorders, im_p);
        negation.process(im_p);

        applyCCL(im_p);

        pluginList.clear();
        pluginList.add(new Negate());
        pluginList.stream().forEach(plugin -> plugin.process(im_p));

        new Cover().process(im_p, out);
//        new Median().process(out);

        // result show
        ImageFrame imageFrame = new ImageFrame(out);
        imageFrame.display();
    }

    private static Image applyBorders(Image im, Integer threshold) {
        Image orginal = new Image(BONE_FILENAME);

        Image processed = getBorders(orginal, threshold);
        substract.process(processed, im);
        return processed;

    }

    private static Image getBorders(Image processed, int threshold) {
        List<Plugin> pluginList = new LinkedList<>();

        Image sobel4m = processed.copy();

        new Sobel().process(processed);
        new Sobel4M().process(sobel4m);

        new Cover().process(sobel4m, processed);

        pluginList.add(new BinPlugin().withThreshold(threshold));
        pluginList.add(new Median());
        pluginList.add(new Negate());

        pluginList.stream().forEach(plugin -> plugin.process(processed));

        return processed;
    }

    private static void extract(Image im) {
        List<Plugin> pluginList = new LinkedList<>();
        pluginList.add(new BoneExtractor(.0034f));
        pluginList.stream().forEach(plugin -> plugin.process(im));
    }

    private static Image substract(Image im) {
        Image orginal = new Image(BONE_FILENAME);
        substract.process(im, orginal);
        return orginal.copy();
    }

    private static void applyCCL(Image im) {
        new ConnectedComponent().process(im);
    }
}

