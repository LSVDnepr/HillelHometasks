package threadapp;

public class MainThread {
    private static String answer1 = null;
    private static String answer2 = null;
    private static String answer3 = null;

    public static void main(String[] args) {
        int answerCalc=0;

        new Thread(new Runnable() {
            public void run() {
                answer1 = Service1.getInstance().work();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                answer2 = Service2.getInstance().work();
            }
        }).start();

        new Thread(new Runnable() {
            public void run() {
                answer3 = Service3.getInstance().work();
            }
        }).start();


        while (answerCalc<3) {
            try {
                //    Thread.sleep(5100);
                if (answer1!=null){
                    System.out.println(answer1);
                    answer1=null;
                    answerCalc++;
                }
                if (answer2!=null){
                    System.out.println(answer2);
                    answer2=null;
                    answerCalc++;
                }
                if (answer3!=null){
                    System.out.println(answer3);
                    answer3=null;
                    answerCalc++;
                }
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

        System.out.println(answer1);
        System.out.println(answer2);
        System.out.println(answer3);
    }

}
