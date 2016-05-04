package AnalizaObrazow.reports.report2;

import AnalizaObrazow.reports.report2.plugins.MorphPlugin;
import AnalizaObrazow.reports.report2.plugins.Strategy.DylateBWStrategy;
import AnalizaObrazow.reports.report2.plugins.Strategy.ErodeBWStrategy;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        String filename = "./src/AnalizaObrazow/res/art1.gif";
        Executor points = new StepHandlerExecutor(filename);
        points.add(new MorphPlugin(new DylateBWStrategy(), 1));
        points.add(new MorphPlugin(new DylateBWStrategy(), 1));
        points.add(new MorphPlugin(new DylateBWStrategy(), 1));
        points.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        points.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        points.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        points.execute();

        Executor lines = new StepHandlerExecutor(filename);
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new ErodeBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.add(new MorphPlugin(new DylateBWStrategy(), 1));
        lines.execute();
    }
}
