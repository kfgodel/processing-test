package app.ar.com.dgarcia.processing;

/**
 * This type measures the fps drawn on the screen
 * Created by ikari on 24/12/2014.
 */
public class FpsMeasurer {

    private double currentFps;
    private long frameCount;
    private long startMoment;

    public static FpsMeasurer create() {
        FpsMeasurer fpsMeasurer = new FpsMeasurer();
        fpsMeasurer.start();
        return fpsMeasurer;
    }

    private void start() {
        this.frameCount = 0;
        this.startMoment = getCurrentMillis();
    }

    public void startingFrame() {
        frameCount++;
    }

    public double getCurrentFps() {
        long elapsed = getCurrentMillis() - startMoment;
        if(elapsed > 1000){
            //Update fps every second so the value doesn't change between seconds
            currentFps = (frameCount * 1000d) / elapsed;
            start();
        }
        return currentFps;
    }

    public long getCurrentMillis() {
        return System.currentTimeMillis();
    }
}
