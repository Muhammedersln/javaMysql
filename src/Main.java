public class Main {
    public static void main(String[] args) {
        // Scrum takım üyelerinin oluşturulması
        ProductOwner productOwner = new ProductOwner("Product Owner", 1);
        ScrumMaster scrumMaster = new ScrumMaster("Scrum Master", 2);
        Developer developer1 = new Developer("Developer 1", 3);
        Developer developer2 = new Developer("Developer 2", 4);
        Developer developer3 = new Developer("Developer 3", 5);

        try {
            
            Thread poThread = new Thread(productOwner);
            poThread.start();
            poThread.join(); 

            
            Thread smThread = new Thread(scrumMaster);
            smThread.start();
            smThread.join(); 

            
            Thread dev1Thread = new Thread(developer1);
            dev1Thread.start();
            dev1Thread.join(); 

            Thread dev2Thread = new Thread(developer2);
            dev2Thread.start();
            dev2Thread.join(); 

            Thread dev3Thread = new Thread(developer3);
            dev3Thread.start();
            dev3Thread.join(); 

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
