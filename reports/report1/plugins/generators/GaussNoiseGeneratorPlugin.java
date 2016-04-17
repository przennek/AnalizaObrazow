package AnalizaObrazow.reports.report1.plugins.generators;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.util.Date;
import java.util.Random;

/**
 * Created by p on 17.04.16.
 */
public class GaussNoiseGeneratorPlugin extends Plugin {
    private Random random = new Random();
    private Random random2 = new Random();

    private Integer strength;

    public GaussNoiseGeneratorPlugin(Integer noiseStrenght) {
        random.setSeed(new Date().getTime());
        random2.setSeed(new Date().getTime());
        this.strength = noiseStrenght;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        Image cpy = imgIn.copy();
        (new Grayscale()).process(cpy, cpy);
        for (int i = 0; i < cpy.getWidth(); i++) {
            for (int j = 0; j < cpy.getHeight(); j++) {
                Integer noise = new Double(nextNormalRandom() * strength).intValue();
                System.out.println(noise);
                Integer newVal = (imgIn.getBlue(i, j) + noise);
                if (newVal > 255) newVal = 255;
                if (newVal < 0) newVal = 0;
                imgOut.setRGB(i, j, newVal, newVal, newVal);
            }
        }
    }

    public Double nextNormalRandom() {
        return Math.sqrt(-2 * Math.log(random.nextDouble())) * Math.cos(2 * Math.PI * random2.nextDouble());
    }
}
