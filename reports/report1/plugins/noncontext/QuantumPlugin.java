package AnalizaObrazow.reports.report1.plugins.noncontext;

import kimage.image.Image;
import kimage.plugin.Plugin;
import kimage.plugins.color.Grayscale;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by p on 17.04.16.
 */
public class QuantumPlugin extends Plugin {
    private static final Integer MAX_PIXEL_VAL = 255;
    private static final Integer MIN_PIXEL_VAL = 0;
    private Integer intervals;

    public QuantumPlugin(Integer intervals) {
        if (intervals < 2) {
            throw new InvalidParameterException("Interval number not supported, minimal value is 2.");
        }
        this.intervals = intervals;
    }

    @Override
    public void process(Image imgIn, Image imgOut) {
        (new Grayscale()).process(imgIn, imgOut);
        List<Integer> intrlv = createIntevals();
        for (int i = 0; i < imgOut.getHeight(); i++) {
            for (int j = 0; j < imgOut.getWidth(); j++) {
                Double index = (Math.ceil(((double) imgOut.getBlue(i, j) / (double) MAX_PIXEL_VAL) * (double) intervals));
                Integer val = intrlv.get(index.intValue());
                imgOut.setRGB(i, j, val, val, val);
            }
        }
    }

    private List<Integer> createIntevals() {
        List<Integer> out = new ArrayList<>();
        for (int i = MIN_PIXEL_VAL; i < MAX_PIXEL_VAL; i += (MAX_PIXEL_VAL / intervals)) {
            out.add(i);
        }
        return out;
    }

}
