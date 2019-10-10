package local.raajn.javaorder.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "theagents")
public class Agents
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, nullable = false)
    private long agentcode;

    private String agentname;
    private String workingarea;
    private double commision;
    private String phone;
    private String country;

    @OneToMany(mappedBy = "agent",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Customers> thecustomers = new ArrayList<>();

    public Agents()
    {
    }

    public Agents(String agentname, String workingarea, double commision, String phone, String country)
    {
        this.agentname = agentname;
        this.workingarea = workingarea;
        this.commision = commision;
        this.phone = phone;
        this.country = country;
    }

    public long getAgentcode()
    {
        return agentcode;
    }

    public void setAgentcode(long agentcode)
    {
        this.agentcode = agentcode;
    }

    public String getAgentname()
    {
        return agentname;
    }

    public void setAgentname(String agentname)
    {
        this.agentname = agentname;
    }

    public String getWorkingarea()
    {
        return workingarea;
    }

    public void setWorkingarea(String workingarea)
    {
        this.workingarea = workingarea;
    }

    public double getCommision()
    {
        return commision;
    }

    public void setCommision(double commision)
    {
        this.commision = commision;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public List<Customers> getThecustomers()
    {
        return thecustomers;
    }

    public void setThecustomers(List<Customers> thecustomers)
    {
        this.thecustomers = thecustomers;
    }
}
