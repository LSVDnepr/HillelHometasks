package threadapp;

public class Service2 {
    private static Service2 ourInstance = new Service2();

    public static Service2 getInstance() {
        return ourInstance;
    }

    private Service2() {
    }

    public String work() {
        try {
            Thread.sleep(5000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "Service 2 work done";
    }
}
