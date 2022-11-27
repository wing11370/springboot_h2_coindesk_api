package com.example.coindesk_api.dto;

public class CurrencyDto {
    Integer id;
    String date;
    String title;
    String currency;
    Double rate;

    public CurrencyDto(Integer id, String date, String currency, Double rate) {
        this.id = id;
        this.date = date;
        this.currency = currency;
        this.rate = rate;
        switch (currency) {
            case "usd":
                this.title = "美金";
                break;
            case "gbp":
                this.title = "英鎊";
                break;
            case "eur":
                this.title = "歐元";
                break;
            default:
                break;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }
}
