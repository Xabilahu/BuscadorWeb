package packModelo;

public class Par implements Comparable<Par> {

    String web;
    double pageRank;

    public Par(String pWeb, double pPR) {
        this.web = pWeb;
        this.pageRank = pPR;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        else if (o == null || !(o instanceof Par)) return false;
        Par ob = (Par) o;
        return this.web.equals(ob.web) && Double.compare(this.pageRank, ob.pageRank) == 0;
    }

    @Override
    public int compareTo(Par o) {
        double diff = Double.compare(this.pageRank, o.pageRank);
        if (diff < 0) return 1;
        else if (diff > 0) return -1;
        return 0;
    }

    @Override
    public String toString() {
        return this.web + " -> " + this.pageRank;
    }

    public String getWeb() {
        return this.web;
    }
}