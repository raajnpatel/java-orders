package local.raajn.javaorder.models;

import javax.persistence.*;

@Entity
@Table(name = "theorders")
public class Orders
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long ordnum;

    private double ordaamount;
    private double advanceamount;

    @ManyToOne
    @JoinColumn(name = "custcode", nullable = false)
    private Customers thecustomer;

    private String orddescription;

    public Orders()
    {
    }

    public Orders(double ordaamount, double advanceamount, Customers thecustomer, String orddescription)
    {
        this.ordaamount = ordaamount;
        this.advanceamount = advanceamount;
        this.thecustomer = thecustomer;
        this.orddescription = orddescription;
    }

    public long getOrdnum()
    {
        return ordnum;
    }

    public void setOrdnum(long ordnum)
    {
        this.ordnum = ordnum;
    }

    public double getOrdaamount()
    {
        return ordaamount;
    }

    public void setOrdaamount(double ordaamount)
    {
        this.ordaamount = ordaamount;
    }

    public double getAdvanceamount()
    {
        return advanceamount;
    }

    public void setAdvanceamount(double advanceamount)
    {
        this.advanceamount = advanceamount;
    }

    public Customers getThecustomer()
    {
        return thecustomer;
    }

    public void setThecustomer(Customers thecustomer)
    {
        this.thecustomer = thecustomer;
    }

    public String getOrddescription()
    {
        return orddescription;
    }

    public void setOrddescription(String orddescription)
    {
        this.orddescription = orddescription;
    }
}
