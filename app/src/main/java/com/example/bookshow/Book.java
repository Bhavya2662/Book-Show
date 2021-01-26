package com.example.bookshow;

public class Book {
    private String title;
    private String authors;
    private String publishedDate;
    private String description;
    private String categories;
    private String thumbnail;
    private String retailPrice;
    private String buy;
    private String preview;
    private String price;
    private int pageCount;
    private String url;
    public Book(String title, String authors, String publishedDate, String description, String categories, String thumbnail,
                String buy, String preview, String price, int pageCount, String url, String retailPrice) {
        this.title = title;
        this.authors = authors;
        this.publishedDate = publishedDate;
        this.description = description;
        this.categories = categories;
        this.thumbnail = thumbnail;
        this.buy = buy;
        this.preview = preview;
        this.price = price;
        this.pageCount = pageCount;
        this.url = url;
        this.retailPrice=retailPrice;
    }


    public String getUrl() {
        return url;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthors() {
        return authors;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public String getDescription() {
        return description;
    }

    public String getCategories() {
        return categories;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String getRetailPrice() {
        return retailPrice;
    }

    public String getBuy() {
        return buy;
    }

    public String getPreview() {
        return preview;
    }

    public String getPrice() {
        return price;
    }





}
