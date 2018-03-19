package threadapp;

public class Service1 {
    private static Service1 ourInstance = new Service1();

    public static Service1 getInstance() {
        return ourInstance;
    }

    private Service1() {
    }

    public String work() {
        try {
            Thread.sleep(2000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Service 1 work done";
    }
}
