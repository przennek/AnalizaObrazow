package AnalizaObrazow.laboratories;

import AnalizaObrazow.laboratories.plugins.GrayscalePlugin;
import kimage.plugins.edge.Sobel;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

public class Demo {

    public static void main(String[] args) {
        String filename = "./res/lena.png";
        Executor exec = new StepHandlerExecutor(filename);
        exec.add(new GrayscalePlugin());
        exec.add(new Sobel());
        exec.execute();
    }
}