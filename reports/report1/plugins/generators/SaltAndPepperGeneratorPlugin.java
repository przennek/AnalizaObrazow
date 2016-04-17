package AnalizaObrazow.reports.report1.plugins.generators;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.util.Date;
import java.util.Random;

/**
 * Created by p on 17.04.16.
 */
public class SaltAndPepperGeneratorPlugin extends Plugin {
    private Random random;

    public SaltAndPepperGeneratorPlugin() {
        random = new Random();
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = 0; i < cpy.getWidth(); i++) {
            for (int j = 0; j < cpy.getHeight(); j++) {
                Double noise = random.nextDouble();
                if (noise > 0.95) {
                    imgOut.setRGB(i, j, 0, 0, 0);
                } else if (noise < 0.05) {
                    imgOut.setRGB(i, j, 255, 255, 255);
                } else {
                    imgOut.setRGB(i, j, cpy.getRGB(i, j));
                }
            }
        }
    }
}
