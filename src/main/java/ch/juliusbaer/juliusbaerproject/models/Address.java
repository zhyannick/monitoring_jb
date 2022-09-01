package ch.juliusbaer.juliusbaerproject.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Address {

    @Id
    private Integer id;

    private String uId;

    private String city;

    private String street_name;

    private String street_address;

    private String secondary_address;

    private int building_number;

    private String mail_box;

    private String community;

    private int zip_code;

    private int zip;

    private int postcode;

    private String time_zone;

    private String street_suffix;

    private String city_suffix;

    private String city_prefix;

    private String state;

    private String state_abbr;

    private String country;

    private String country_code;

    private double latitude;

    private double longitude;

    private String full_address;

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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet_name() {
        return street_name;
    }

    public void setStreet_name(String street_name) {
        this.street_name = street_name;
    }

    public String getStreet_address() {
        return street_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public String getSecondary_address() {
        return secondary_address;
    }

    public void setSecondary_address(String secondary_address) {
        this.secondary_address = secondary_address;
    }

    public int getBuilding_number() {
        return building_number;
    }

    public void setBuilding_number(int building_number) {
        this.building_number = building_number;
    }

    public String getMail_box() {
        return mail_box;
    }

    public void setMail_box(String mail_box) {
        this.mail_box = mail_box;
    }

    public String getCommunity() {
        return community;
    }

    public void setCommunity(String community) {
        this.community = community;
    }

    public int getZip_code() {
        return zip_code;
    }

    public void setZip_code(int zip_code) {
        this.zip_code = zip_code;
    }

    public int getZip() {
        return zip;
    }

    public void setZip(int zip) {
        this.zip = zip;
    }

    public int getPostcode() {
        return postcode;
    }

    public void setPostcode(int postcode) {
        this.postcode = postcode;
    }

    public String getTime_zone() {
        return time_zone;
    }

    public void setTime_zone(String time_zone) {
        this.time_zone = time_zone;
    }

    public String getStreet_suffix() {
        return street_suffix;
    }

    public void setStreet_suffix(String street_suffix) {
        this.street_suffix = street_suffix;
    }

    public String getCity_suffix() {
        return city_suffix;
    }

    public void setCity_suffix(String city_suffix) {
        this.city_suffix = city_suffix;
    }

    public String getCity_prefix() {
        return city_prefix;
    }

    public void setCity_prefix(String city_prefix) {
        this.city_prefix = city_prefix;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState_abbr() {
        return state_abbr;
    }

    public void setState_abbr(String state_abbr) {
        this.state_abbr = state_abbr;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getFull_address() {
        return full_address;
    }

    public void setFull_address(String full_address) {
        this.full_address = full_address;
    }
}
