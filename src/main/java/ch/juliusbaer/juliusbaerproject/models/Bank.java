package ch.juliusbaer.juliusbaerproject.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Bank {

    @Id
    private Integer id;

    private String uId;

    private int account_number;

    private String iban;

    private String bank_name;

    private int routing_number;

    private String swift_bic;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public int getAccount_number() {
        return account_number;
    }

    public void setAccount_number(int account_number) {
        this.account_number = account_number;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public int getRouting_number() {
        return routing_number;
    }

    public void setRouting_number(int routing_number) {
        this.routing_number = routing_number;
    }

    public String getSwift_bic() {
        return swift_bic;
    }

    public void setSwift_bic(String swift_bic) {
        this.swift_bic = swift_bic;
    }
}
