package threadapp;

public class Service3 {
    private static Service3 ourInstance = new Service3();

    public static Service3 getInstance() {
        return ourInstance;
    }

    private Service3() {
    }

    public String work() {
        try {
            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Service 3 work done";
    }
}
