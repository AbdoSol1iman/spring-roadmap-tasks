public class book {
    private String title;
    private String author;
    private String isbn;
    private boolean isAvailable=true;

    book(String title, String author, String isbn) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
    }
//    book(String title, String author, String isbn, boolean isAvailable) {
//        this.title = title;
//        this.author = author;
//        this.isbn = isbn;
//        this.isAvailable = isAvailable;
//    }


    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    boolean isAvailable() {
        return isAvailable;
    }
    void setAvailable(boolean available) {
        isAvailable = available;
    }

    String GetInfo(){
        return this.title + " By " + this.author + " (" + this.isbn + ").";
    }
    void borrow(){
        this.isAvailable=false;
    }
    void returnBook(){
        this.isAvailable=true;
    }

}
