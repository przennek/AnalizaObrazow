package AnalizaObrazow.projekt;

import kimage.image.Image;
import kimage.plugin.Plugin;

import java.awt.*;

/**
 * Created by p on 27.05.16.
 */
public class BoneExtractor extends Plugin {
    private float threshold;
    private int performanceCounter = 0;

    public BoneExtractor(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        for (int i = 0; i < imgIn.getWidth(); ++i) {
            for (int j = 0; j < imgIn.getHeight(); ++j) {
                Integer[] rgb = hsbManipulation(new Integer[]{
                                imgIn.getRed(i, j),
                                imgIn.getGreen(i, j),
                                imgIn.getBlue(i, j)
                        }, threshold);

                // 'BW'
                Integer val = rgb[0];

                imgOut.setRGB(i, j, val, val, val);
            }
        }
    }

    private Integer[] hsbManipulation(Integer[] rgb, float kSaturation) {
        float[] hsv = new float[3];
        Color.RGBtoHSB(rgb[0], rgb[1], rgb[2], hsv);

        // manipulation
        float hue = hsv[0] * 0.0f;
        float saturation = hsv[1] * 0.0f;
        float brightness = hsv[2] * kSaturation;
        //

        brightness = brightness > 1 ? 1 : brightness;

        Color newValueRGB = new Color(Color.HSBtoRGB(hue, saturation, brightness));

        rgb[0] = newValueRGB.getRed();
        rgb[1] = newValueRGB.getGreen();
        rgb[2] = newValueRGB.getBlue();

        return rgb;
    }
}
