package org.example;

public class Configuration {
    private int TTickets ;
    private float TReleaseRate;
    private float customerRetrievalRate;
    private int maxTicket;

    public Configuration(int TTickets, float TReleaseRate, float customerRetrievalRate, int maxTicket) {
        this.TTickets = TTickets;
        this.TReleaseRate = TReleaseRate;
        this.customerRetrievalRate = customerRetrievalRate;
        this.maxTicket = maxTicket;
    }
    public Configuration(){}

    public int getTTickets() {
        return TTickets;
    }

    public float getTReleaseRate() {
        return TReleaseRate;
    }

    public float getCustomerRetrievalRate() {
        return customerRetrievalRate;
    }

    public int getMaxTicket() {
        return maxTicket;
    }

    public void setTTickets(int TTickets) {
        this.TTickets = TTickets;
    }

    public void setTReleaseRate(float TReleaseRate) {
        this.TReleaseRate = TReleaseRate;
    }

    public void setCustomerRetrievalRate(float customerRetrievalRate) {
        this.customerRetrievalRate = customerRetrievalRate;
    }

    public void setMaxTicket(int maxTicket) {
        this.maxTicket = maxTicket;
    }
}
