//package Time;
//
//public class TimeTaken implements Time {
//
//    float deltaTime;
//    long lastTime;
//    boolean resetDeltaTime = false;
//    long frameStart;
//    int frames;
//    int fps;
//
//    public TimeTaken(long lastTime) {
//        this.lastTime = lastTime;
//    }
//
//    public void resetDeltaTime () {
//        resetDeltaTime = true;
//    }
//
//    @Override
//    public float getDeltaTime () {
//        return deltaTime;
//    }
//
//    public void updateTime() {
//        long time;
//        if (resetDeltaTime) {
//            resetDeltaTime = false;
//            time = lastTime;
//        } else
//            time = System.nanoTime();
//        deltaTime = (time - lastTime) / 1000000000.0f;
//        lastTime = time;
//
//        if (time - frameStart >= 1000000000) {
//            fps = frames;
//            frames = 0;
//            frameStart = time;
//        }
//        frames++;
//    }
//}
