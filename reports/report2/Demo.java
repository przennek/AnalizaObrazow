package AnalizaObrazow.reports.report2;

import AnalizaObrazow.reports.generators.SaltAndPepperGeneratorPlugin;
import AnalizaObrazow.reports.report2.plugins.ClearBordersPlugin;
import AnalizaObrazow.reports.report2.plugins.MorphPlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.GrayStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.IOperationStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.BWStrategy;
import kimage.image.Image;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    private static final String lena = "./res/lena.png";
    private static final String filename = "./src/AnalizaObrazow/res/art1.gif";
    private static final Integer MAX_VAL = 255;
    private static final Integer MIN_VAL = 0;
    private static final Integer IGNORING_VAL = -1;

    private static Integer[][] dylateCustom = new Integer[][]{{IGNORING_VAL, MIN_VAL, IGNORING_VAL}, {MIN_VAL, IGNORING_VAL, MIN_VAL}, {IGNORING_VAL, MIN_VAL, IGNORING_VAL}};
    private static Integer[][] matrix3x255 = new Integer[][]{{MAX_VAL, MAX_VAL, MAX_VAL}, {MAX_VAL, MAX_VAL, MAX_VAL}, {MAX_VAL, MAX_VAL, MAX_VAL}};
    private static Integer[][] matrix3x0 = new Integer[][]{{MIN_VAL, MIN_VAL, MIN_VAL}, {MIN_VAL, MIN_VAL, MIN_VAL}, {MIN_VAL, MIN_VAL, MIN_VAL}};
    private static Integer[][] pointsMatrix = new Integer[][]{{IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, MAX_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {MAX_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, MAX_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}};

//    private static Integer[][] linesHorizontalMatrix = new Integer[][]{{MAX_VAL, MAX_VAL, MAX_VAL, MAX_VAL, MAX_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}};
//    private static Integer[][] linesVerticalMatrix = new Integer[][]{{IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, MAX_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}, {IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL, IGNORING_VAL}};

    public static void main(String[] args) {
        List<IOperationStrategy> strategyList = new ArrayList<>();
        final Integer border1 = 1;
        final Integer border4 = 4;

        // points removal
        strategyList.addAll(
                Arrays.asList(
                        new BWStrategy(pointsMatrix, MAX_VAL),
                        new BWStrategy(matrix3x255, MAX_VAL),
                        new BWStrategy(matrix3x0, MIN_VAL),
                        new BWStrategy(dylateCustom, MIN_VAL)
                )
        );
//        runIt(strategyList, border4);
        strategyList.clear();

        // lines removal
        strategyList.addAll(
                Arrays.asList(
//                        new BWStrategy(linesHorizontalMatrix, MAX_VAL),
//                        new BWStrategy(linesVerticalMatrix, MAX_VAL)
                )
        );
//        runIt(strategyList, linesMatrix.length, filename);
        strategyList.clear();

        // salt and pepper removal
        Executor saltPeper = new StepHandlerExecutor(lena);
        saltPeper.add(new SaltAndPepperGeneratorPlugin());
        Image saltAndPepperLenna = saltPeper.getResultImage();
        strategyList.addAll(
                Arrays.asList(
                        new GrayStrategy(saltAndPepperLenna, border1, MAX_VAL, (a, b) -> a <= b),
                        new GrayStrategy(saltAndPepperLenna, border1, MIN_VAL, (a, b) -> a >= b)
                )
        );
//        runIt(strategyList, border1, saltPeper);
        strategyList.clear();

        // way find
        strategyList.addAll(
                Arrays.asList(

                )
        );
    }

    public static void runIt(List<IOperationStrategy> strategyList, Integer borders, String filename, Executor executor) {
        Executor exec = executor == null ? new StepHandlerExecutor(filename) : executor;
        for (IOperationStrategy operationStrategy : strategyList) {
            exec.add(new MorphPlugin(operationStrategy));
        }
        exec.add(new ClearBordersPlugin(borders));
        exec.execute();
    }

    public static void runIt(List<IOperationStrategy> strategyList, Integer borders, String filename) {
        runIt(strategyList, borders, filename, null);
    }

    public static void runIt(List<IOperationStrategy> strategyList, Integer borders, Executor executor) {
        runIt(strategyList, borders, null, executor);
    }
}

