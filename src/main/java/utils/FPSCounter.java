package utils;

public class FPSCounter extends Thread{
    private long lastTime;
    private double fps;

    public void run(){
        while (true){
            lastTime = System.nanoTime();
            try{
                Thread.sleep(1000);
            }
            catch (InterruptedException e){
                System.out.println("fps Error");
            }
            fps = 1000000000.0 / (System.nanoTime() - lastTime);
            lastTime = System.nanoTime();
        }
    }
    public double fps(){
        return fps;
    }
}