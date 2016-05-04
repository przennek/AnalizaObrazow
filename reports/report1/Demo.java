package AnalizaObrazow.reports.report1;

import AnalizaObrazow.reports.report1.plugins.context.MedianFilterPlugin;
import AnalizaObrazow.reports.report1.plugins.context.binar.NiblackBinPlugin;
import AnalizaObrazow.reports.report1.plugins.context.binar.SauvolaBinPlugin;
import AnalizaObrazow.reports.generators.DummyNoiseGenerationPlugin;
import AnalizaObrazow.reports.generators.GaussNoiseGeneratorPlugin;
import AnalizaObrazow.reports.generators.SaltAndPepperGeneratorPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.QuantumPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.ReflectionPlugin;
import AnalizaObrazow.reports.report1.plugins.noncontext.ShuffleColorIntensityPlugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

/**
 * Created by p on 04.04.16.
 */
public class Demo {
    public static void main(String[] args) {
        String filename = "./res/lena.png";
        Executor nonContextExec = new StepHandlerExecutor(filename);
        nonContextExec.add(new ReflectionPlugin());
        nonContextExec.add(new ShuffleColorIntensityPlugin());
        nonContextExec.add(new QuantumPlugin(4));
        nonContextExec.execute();

        Executor niblackBinExec = new StepHandlerExecutor(filename);
        niblackBinExec.add(new NiblackBinPlugin(-0.1, 2));
        niblackBinExec.execute();

        Executor sauvolaBinExec = new StepHandlerExecutor(filename);
        sauvolaBinExec.add(new SauvolaBinPlugin(-0.01, 3));
        sauvolaBinExec.execute();

        Executor gaussExec = new StepHandlerExecutor(filename);
        gaussExec.add(new GaussNoiseGeneratorPlugin(32));
        gaussExec.add(new MedianFilterPlugin(1));
        gaussExec.execute();

        Executor saltAndPepperExec = new StepHandlerExecutor(filename);
        saltAndPepperExec.add(new SaltAndPepperGeneratorPlugin());
        saltAndPepperExec.add(new MedianFilterPlugin(1));
        saltAndPepperExec.execute();

        Executor dummyNoiseExec = new StepHandlerExecutor(filename);
        dummyNoiseExec.add(new DummyNoiseGenerationPlugin());
        dummyNoiseExec.add(new MedianFilterPlugin(1));
        dummyNoiseExec.execute();
    }
}
