public class Book {
    private int bookId;
    private String bookName;
    private String author;
    private String isbn;
    private Double price;
    private int numberOfPages;
    private boolean hasHardCover;

    public Book(int bookId, String bookName, String author, String isbn, Double price, int numberOfPages, boolean hasHardCover) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.author = author;
        this.isbn = isbn;
        this.price = price;
        this.numberOfPages = numberOfPages;
        this.hasHardCover = hasHardCover;
    }

    public Book() {
    }

    public String toString() {
        return "{" + this.getBookId() + ", " + this.getBookName() + ", " + this.getAuthor() + ", " + this.getPrice() + ", " + this.getNumberOfPages() + "}\n";
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public Double getPrice() {
        return price;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public boolean isHasHardCover() {
        return hasHardCover;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public void setHasHardCover(boolean hasHardCover) {
        this.hasHardCover = hasHardCover;
    }
}
