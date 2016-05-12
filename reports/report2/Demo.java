package AnalizaObrazow.reports.report2;

import AnalizaObrazow.reports.generators.SaltAndPepperGeneratorPlugin;
import AnalizaObrazow.reports.report2.plugins.ClearBordersPlugin;
import AnalizaObrazow.reports.report2.plugins.MergeHitsPlugin;
import AnalizaObrazow.reports.report2.plugins.MorphPlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.BWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.GrayStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.IOperationStrategy;
import AnalizaObrazow.reports.report2.plugins.SubstractHitsPlugin;
import kimage.image.Image;
import kimage.image.ImageForThreads;
import kimage.plugin.Plugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;
import kimage.utils.gui.ImageFrame;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static AnalizaObrazow.reports.report2.Util.MatrixProvider.*;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    private static final String LENNA = "./res/lena.png";
    private static final String POINTS_AND_LINES = "./src/AnalizaObrazow/res/art1.gif";
    private static final String HIT_OR_MISS_TEST = "./src/AnalizaObrazow/res/content_template1.png";
    private static final String THINNING = "./src/AnalizaObrazow/res/content_cross.png";
    private static final String LABIRYNTH = "./src/AnalizaObrazow/res/Thinning.png";


    private static final Integer MAX_VAL = 255;
    private static final Integer MIN_VAL = 0;

    private static final List<IOperationStrategy> strategyList = new ArrayList<>();
    private static final Integer BORDER_1 = 1;

    public static void main(String[] args) {
//        pointsRemoval();
//        linesRemoval();
//        saltAndPepperRemoval();
//        wayFind();
        thinningDemo();
    }

    private static void pointsRemoval() {
        strategyList.addAll(
                Arrays.asList(
                        new BWStrategy(MATRIX_POINTS, MAX_VAL),
                        new BWStrategy(MATRIX_3x255, MAX_VAL),
                        new BWStrategy(MATRIX_3x0, MIN_VAL),
                        new BWStrategy(MATRIX_CUSTOM_DYLATE, MIN_VAL)
                )
        );
        runIt(strategyList, MATRIX_POINTS.length, POINTS_AND_LINES);
        strategyList.clear();
    }

    private static void linesRemoval() {
        strategyList.addAll(
                Arrays.asList(
                // I give up
                )
        );
        runIt(strategyList, 0, POINTS_AND_LINES);
        strategyList.clear();
    }

    private static void saltAndPepperRemoval() {
        Executor saltPepper = new StepHandlerExecutor(LENNA);
        saltPepper.add(new SaltAndPepperGeneratorPlugin());
        Image saltAndPepperLenna = saltPepper.getResultImage();
        strategyList.addAll(
                Arrays.asList(
                        new GrayStrategy(saltAndPepperLenna, BORDER_1, MAX_VAL, (a, b) -> a <= b),
                        new GrayStrategy(saltAndPepperLenna, BORDER_1, MIN_VAL, (a, b) -> a >= b)
                )
        );
        runIt(strategyList, BORDER_1, saltPepper);
        strategyList.clear();
    }

    private static void wayFind() {
        strategyList.addAll(
                Arrays.asList(
                )
        );
//        mergeResults(strategyList, BORDER_1, HIT_OR_MISS_TEST);
        strategyList.clear();
    }

    private static void hitOrMissDemo() { // not exactly part of report but necessary
        strategyList.addAll(
                Arrays.asList(
                        new BWStrategy(MATRIX_R1, 0),
                        new BWStrategy(MATRIX_R2, 0),
                        new BWStrategy(MATRIX_R3, 0),
                        new BWStrategy(MATRIX_R4, 0)
                )
        );
//        mergeResults(strategyList, BORDER_1, HIT_OR_MISS_TEST);
        strategyList.clear();
    }

    private static void thinningDemo() { // not exactly part of report but necessary
        strategyList.addAll(
                Arrays.asList(
                        new BWStrategy(MATRIX_T1, 0),
                        new BWStrategy(MATRIX_T2, 0),
                        new BWStrategy(rotate90(MATRIX_T1), 0),
                        new BWStrategy(rotate90(MATRIX_T2), 0),
                        new BWStrategy(rotate90(rotate90(MATRIX_T1)), 0),
                        new BWStrategy(rotate90(rotate90(MATRIX_T2)), 0),
                        new BWStrategy(rotate90(rotate90(rotate90(MATRIX_T1))), 0),
                        new BWStrategy(rotate90(rotate90(rotate90(MATRIX_T2))), 0)
                )
        );

        Plugin substract = new SubstractHitsPlugin(0, 255);
        Image orginalIm = new Image(LABIRYNTH);
        Image prevIm;
        Image im = null;

        Integer counter = 0;
        do {
            if (counter++ == 0) {
                prevIm = orginalIm.copy();
                im = orginalIm.copy();
            } else {
                prevIm = im.copy();
            }

            im = mergeResults(strategyList, BORDER_1, im);
            substract.process(prevIm, im);
        } while (!equals(im, prevIm));

        new ImageFrame(prevIm).display();
    }

    private static void runIt(List<IOperationStrategy> strategyList, Integer borders, String filename, Executor executor) {
        Executor exec = executor == null ? new StepHandlerExecutor(filename) : executor;
        for (IOperationStrategy operationStrategy : strategyList) {
            exec.add(new MorphPlugin(operationStrategy));
        }
        exec.add(new ClearBordersPlugin(borders));
        exec.execute();
    }

    private static void runIt(List<IOperationStrategy> strategyList, Integer borders, String filename) {
        runIt(strategyList, borders, filename, null);
    }

    private static void runIt(List<IOperationStrategy> strategyList, Integer borders, Executor executor) {
        runIt(strategyList, borders, null, executor);
    }

    private static Image mergeResults(List<IOperationStrategy> strategyList, Integer borders, Image image) {
        Image cpy = image.copy();

        ArrayList<Image> imageList = new ArrayList<>();
        for (IOperationStrategy operationStrategy : strategyList) {
            Plugin currentPlugin = new MorphPlugin(operationStrategy);
            currentPlugin.process(cpy, image);
            imageList.add(image);
            image = cpy.copy();
        }

        Plugin merge = new MergeHitsPlugin(255, 0);
        image = new Image(image.getWidth(), image.getHeight());
        image.fillWithColor(new Color(0,0,0));

        for(Image i : imageList) {
            merge.process(i, image);
        }

        return image;
    }

    // TODO remove it, running out of time
    public static Boolean equals(Image im1, Image im2) {
        if(im1 == im2) return true;
        if(im2 == null) return false;

        for (int i = 0; i < im1.getWidth(); i++) {
            for (int j = 0; j < im1.getHeight(); j++) {
                if(im1.getBlue(i, j) != im2.getBlue(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
}

