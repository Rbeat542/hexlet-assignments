package exercise;

class App {

    public static void main(String[] args) {
        // BEGIN
        SafetyList safetyList = new SafetyList();
        ListThread listThread1 = new ListThread(new SafetyList());
        ListThread listThread2 = new ListThread(new SafetyList());

        listThread1.start();
        listThread2.start();

        try {
            listThread1.join();
            listThread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("List size: " + safetyList.getSize());
        // END
    }
}

