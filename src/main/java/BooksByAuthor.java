import java.util.List;

public class BooksByAuthor {
    private String authorName;
    private List<String> bookName;

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<String> getBookName() {
        return bookName;
    }

    public void setBookName(List<String> bookName) {
        this.bookName = bookName;
    }

    public BooksByAuthor(String authorName, List<String> bookName) {
        this.authorName = authorName;
        this.bookName = bookName;
    }

    public BooksByAuthor() {
    }

    public String toString() {
        return "{"
                + this.getAuthorName() + ", "
                + this.getBookName() +
                "}\n"
                ;
    }
}

