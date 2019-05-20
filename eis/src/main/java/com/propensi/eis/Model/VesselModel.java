package com.propensi.eis.Model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "vessel")
public class VesselModel implements Serializable {
    private static final long serialVersionUID = -1302423162348019512L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @NotNull
    @Size(max = 50)
    @Column(name = "name", nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "connecting_vessel")
    private String connectingVessel;

    /**
     * ======================================================== Foreign Key
     * ========================================================
     */

    // One-to-One
    @OneToOne(mappedBy = "vessel")
    @JsonIgnore
    private ShippingInstructionModel shippingInstruction;

    /**
     * ======================================================== Setter Getter
     * ========================================================
     */

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the connectingVessel
     */
    public String getConnectingVessel() {
        return connectingVessel;
    }

    /**
     * @param connectingVessel the connectingVessel to set
     */
    public void setConnectingVessel(String connectingVessel) {
        this.connectingVessel = connectingVessel;
    }

    /**
     * @return the shippingInstruction
     */
    public ShippingInstructionModel getShippingInstruction() {
        return shippingInstruction;
    }

    /**
     * @param shippingInstruction the shippingInstruction to set
     */
    public void setShippingInstruction(ShippingInstructionModel shippingInstruction) {
        this.shippingInstruction = shippingInstruction;
    }

}