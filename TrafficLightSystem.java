import java.util.concurrent.Semaphore;

 

class TrafficLightSystem {

 

    private static

Semaphore semaphore = new Semaphore(1);

 

    public static void

controlTraffic(String direction) {

        try {

           semaphore.acquire();

 

            if

(direction.equals("NS")) {

               System.out.println("NS: GREEN | EW: RED");

               Thread.sleep(3000);

 

               System.out.println("NS: YELLOW");

               Thread.sleep(1000);

 

            } else {

               System.out.println("EW: GREEN | NS: RED");

               Thread.sleep(3000);

 

               System.out.println("EW: YELLOW");

               Thread.sleep(1000);

            }

 

        } catch

(InterruptedException e) {

           e.printStackTrace();

        } finally {

           semaphore.release();

        }

    }

}

 

class SensorThread extends Thread {

    private String

direction;

 

    public

SensorThread(String direction) {

        this.direction

= direction;

    }

 

    public void run()

{

       System.out.println(direction + " sensor detected vehicle!");

       TrafficLightSystem.controlTraffic(direction);

    }

}

 

public class Main {

    public static void

main(String[] args) {

        while (true) {

            Thread ns

= new SensorThread("NS");

            Thread ew

= new SensorThread("EW");

 

           ns.start();

           ew.start();

 

            try {

               ns.join();

               ew.join();

               Thread.sleep(2000);

            } catch

(InterruptedException e) {

               e.printStackTrace();

            }

        }

    }

}