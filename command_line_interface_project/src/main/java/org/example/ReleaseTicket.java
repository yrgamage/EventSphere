package  org.example;

public class ReleaseTicket implements Runnable{

    private  final int vendorsCount;
    private final Configuration obj;
    private  final int ticketCount;
    Object key = new Object(); //Object to lock the critical section
    private static int increment=1;
    protected   static int ticketID =1;

    public ReleaseTicket(int vendorCount, Configuration obj,int ticketCount) {
        this.vendorsCount = vendorCount;
        this.obj = obj;
        this.ticketCount = ticketCount;
    }

    @Override
    public void run() {
        {
            int ticketsAddedByVendor = 1; // Tracks tickets added by this vendor

            while (true) {
                synchronized (key) {
                    String vendorId = Thread.currentThread().getName();

                    try {
                        // Wait if the pool is full or the vendor has reached its limit
                        while (TicketPool.ticketPool.size() == obj.getMaxTicket() ) {
                            System.out.println("Vendor "+vendorsCount+" "+vendorId+" is waiting for space to add tickets");
                            key.wait(); // Wait until space is available in the pool
                        }

                        for (int i = 1; i <=ticketCount ; i++) {
                            if (TicketPool.ticketPool.size() <obj.getMaxTicket() && increment<=obj.getTReleaseRate()) {
                                TicketPool.ticketPool.add(ticketID++);
                                System.out.println("Vendor "+vendorsCount+" "+vendorId+" added ticket: NO " + ticketID+" pool size "+TicketPool.ticketPool.size());
                                increment++;
                                key.notifyAll();
                                Thread.sleep(1000);
                            }
                            else if(increment>=obj.getTReleaseRate()){
                                System.out.println("Vendor "+vendorsCount+" "+vendorId+" has reached the ticket limit for this 15 seconds.waiting to release ...");
                                Thread.sleep(15000-(increment*1000));// Delay to achieve  tickets per second, Assumed that one thread will run for one second
                                increment=1;                            //Resetting the rate
                            }
                        }
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt(); // Restore interrupt status
                        break; // Exit the loop on interruption
                    }
                }

            }
        }

    }
}

