package com.sonichollow.forum.util;

public class SearchForm {
    String searchKey;
    String order;


    public SearchForm(String searchKey, String order) {
        this.searchKey = searchKey;
        this.order = order;
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "SearchForm{" +
                "searchKey='" + searchKey + '\'' +
                ", order='" + order + '\'' +
                '}';
    }
}
