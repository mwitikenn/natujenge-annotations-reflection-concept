package com.meliora.natujenge.cron;

import com.meliora.natujenge.annotations.Autowired;
import com.meliora.natujenge.annotations.Helper;
import com.meliora.natujenge.models.CarModel;
import com.meliora.natujenge.services.CarService;

@Helper
public class Cronjob implements Runnable{

    Thread thread;

    @Autowired
    public CarService carService;

    public Cronjob() {
        System.out.println("Intializing cronjob");

        init();
    }

    public void init() {
        System.out.println("About to start thread...");

        thread = new Thread(this, "cron");
        thread.start();

        System.out.println("Started the thread...");
    }
    @Override
    public void run() {
        while (true) {
            try {
                System.out.println("Helper layer --- About to save car model");

                CarModel carModel = new CarModel();
                carModel.setModel("Toyota");
                carModel.setColor("grey");
                carModel.setYear(2000);
                carModel.setEngineSize(1500);
                carModel.setNumberPlate("KDE 245L");

                carService.Save(carModel);

                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
