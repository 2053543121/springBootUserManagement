package com.znsd.springboot.entities;

public class Page {
    private Integer page;
    private Integer limit;
    private String search;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    @Override
    public String toString() {
        return "Page{" +
                "page=" + page +
                ", limit=" + limit +
                ", search='" + search + '\'' +
                '}';
    }
}
