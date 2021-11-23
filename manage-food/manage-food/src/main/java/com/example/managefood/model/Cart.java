package com.example.managefood.model;

import javax.persistence.*;
import java.util.List;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private long quantity;

    private long total;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    private List<DetailsCart> detailsCarts;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    public Cart() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<DetailsCart> getDetailsCarts() {
        return detailsCarts;
    }

    public void setDetailsCarts(List<DetailsCart> detailsCarts) {
        this.detailsCarts = detailsCarts;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
