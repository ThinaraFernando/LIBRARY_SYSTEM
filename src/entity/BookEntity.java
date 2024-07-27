package entity;

public class BookEntity {

    private int BookID;
    private String Title;
    private String Author;
    private int CategoryID;
    private String Publisher;
    private int Year;

    
    public BookEntity() {

    }


    public BookEntity(int bookID, String title, String author, int categoryID, String publisher, int year) {
        BookID = bookID;
        Title = title;
        Author = author;
        CategoryID = categoryID;
        Publisher = publisher;
        Year = year;
    }


    public int getBookID() {
        return BookID;
    }


    public void setBookID(int bookID) {
        BookID = bookID;
    }


    public String getTitle() {
        return Title;
    }


    public void setTitle(String title) {
        Title = title;
    }


    public String getAuthor() {
        return Author;
    }


    public void setAuthor(String author) {
        Author = author;
    }


    public int getCategoryID() {
        return CategoryID;
    }


    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
    }


    

    public String getPublisher() {
        return Publisher;
    }


    public void setPublisher(String publisher) {
        Publisher = publisher;
    }


    public int getYear() {
        return Year;
    }


    public void setYear(int year) {
        Year = year;
    }


    @Override
    public String toString() {
        return "BookEntity [BookID=" + BookID + ", Title=" + Title + ", Author=" + Author + ", CategoryID=" + CategoryID    + ", Publisher=" + Publisher + ", Year=" + Year + "]";
    }


   

    

}
