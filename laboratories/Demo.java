package AnalizaObrazow.laboratories;

import AnalizaObrazow.laboratories.plugins.BinIterPlugin;
import AnalizaObrazow.laboratories.plugins.CCLPlugin;
import kimage.tools.executors.Executor;
import kimage.tools.executors.gui.StepHandlerExecutor;

public class Demo {

    public static void main(String[] args) {
        String filename = "./src/AnalizaObrazow/res/morp.jpg";
        Executor exec = new StepHandlerExecutor(filename);
        exec.add(new BinIterPlugin());
        exec.add(new CCLPlugin());
        exec.execute();
    }
}