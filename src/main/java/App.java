import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.*;

/**
 *
 */
public class App {
    public static List<Book> books;

    public static void main(String[] args) {
        System.out.println("Learning Java 8 Stream API");
        addBooks();
        System.out.println("-=-=-=-=-getBooksByAuthor=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getBooksByAuthor();
        System.out.println("-=-=-=-=-getBooksGroupByAuthor=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getBooksGroupByAuthor();
        System.out.println("-=-=-=-=-getTotalPriceOfAllBooks=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getTotalPriceOfAllBooks();
        System.out.println("-=-=-=-=-getBookNamesInUpperCase=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getBookNamesInUpperCase();
        System.out.println("-=-=-=-=-getBookWithHighestPrice=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getBookWithHighestPrice();
        System.out.println("-=-=-=-=-getAuthorBooks=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getAuthorBooks();
        System.out.println("-=-=-=-=-getAllBooksOrderByAuthorNamePrice=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getAllBooksOrderByAuthorNamePrice();
        System.out.println("-=-=-=-=-getAllBooksOrderByAuthorNamePriceDesc=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getAllBooksOrderByAuthorNamePriceDesc();
        System.out.println("-=-=-=-=-getBooksGroupByAuthorSorted=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getBooksGroupByAuthorSorted();
        System.out.println("-=-=-=-=-getMapFromStream=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        getMapFromStream();
    }

    /**
     * Add books
     */
    public static void addBooks() {
        System.out.println("addBooks - Start");

        books = new ArrayList<>();
        books.add(new Book(1, "Murder on the Orient Express", "Agatha Christie",
                "9780396085751", 5.05, 2233, true));
        books.add(new Book(2, "The God of small things", "Arundhati Roy",
                "9780007617012", 65.35, 3211, false));
        books.add(new Book(3, "King of Scars", "Leigh Bardugo",
                "9781510104464", 14.53, 1142, false));
        books.add(new Book(11, "Professor Shoknu", "Satyajit Ray",
                "9781510104423", 25.49, 3000, false));
        books.add(new Book(4, "It Starts with Us", "Colleen Hoover",
                "9781398518179", 15.76, 6432, true));
        books.add(new Book(5, "The Secret Adversary", "Agatha Christie",
                "9781934451113", 21.11, 3453, true));
        books.add(new Book(6, "Unfinished Portrait", "Agatha Christie",
                "9780007357949", 4.72, 7533, false));
        books.add(new Book(7, "The Alchemist", "Paulo Coelho",
                "9780007739547", 12.49, 899, false));
        books.add(new Book(8, "Eleven Minutes", "Paulo Coelho",
                "9780007712984", 14.99, 783, false));
        books.add(new Book(9, "Harry Potter and the Sorcerer's Stone", "J.K. Rowling",
                "9780545790352", 20.55, 1264, true));
        books.add(new Book(10, "Da Vinci Code", "Dan Brown",
                "9780593052440", 62.29, 1599, true));
        books.add(new Book(12, "Sonar Kella", "Satyajit Ray",
                "9781510103092", 25.49, 2500, false));

        System.out.println("Output: " + books.toString());
        System.out.println("addBooks - End");
    }

    /**
     * Fetch Agatha Christie books which cost more than $100
     */
    public static void getBooksByAuthor() {
        List<Book> result = books.stream()
                .filter(b -> "Agatha Christie".equals(b.getAuthor()))
                .filter(c -> c.getPrice() > 20.00)
                .collect(toList());

        result.forEach(System.out::println);
    }

    /**
     * Group books by their authors
     */
    public static void getBooksGroupByAuthor() {
        Map<String, List<Book>> result = books.stream()
                .collect(groupingBy(Book::getAuthor));
        System.out.println(result);
    }

    /**
     * What is the total price of all books and Agatha Christie books
     */
    public static void getTotalPriceOfAllBooks() {
        double total = books.stream().mapToDouble(Book::getPrice).sum();
        System.out.println("Total Price of all books: " + total);

        double totalAgathaBooks = books.stream()
                .filter(b -> "Agatha Christie".equals(b.getAuthor()))
                .mapToDouble(Book::getPrice).sum();
        System.out.println("Total Price of Agatha Christie books: " + totalAgathaBooks);
    }

    /**
     * Get only book names in upper case
     */
    public static void getBookNamesInUpperCase() {
        books.stream()
                .map(b -> b.getBookName())
                .map(String::toUpperCase)
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * Return most costliest book in the list
     */
    public static void getBookWithHighestPrice() {
        Optional<Book> maxPrice = books.stream().reduce((p1, p2) -> p1.getPrice() > p2.getPrice() ? p1 : p2);
        System.out.println(maxPrice);
    }

    /**
     * Create a new list of authors and the books written by them
     */
    public static void getAuthorBooks() {
        List<BooksByAuthor> baList = new ArrayList<>();

        Map<String, List<Book>> groupedBook = books.stream()
                .collect(groupingBy(Book::getAuthor));

        for (Map.Entry<String, List<Book>> entry : groupedBook.entrySet()) {
            String k = entry.getKey();
            List<Book> v = entry.getValue();
            BooksByAuthor ba = new BooksByAuthor();

            ba.setAuthorName(k);
            List<String> bk = v.stream().map(Book::getBookName).collect(toList());
            ba.setBookName(bk);
            baList.add(ba);
        }
        System.out.println(baList);
    }

    /**
     * List all books order by author and price both ascending
     */
    public static void getAllBooksOrderByAuthorNamePrice() {
        books.stream().sorted(Comparator.comparing(Book::getAuthor)
                        .thenComparing(Book::getPrice))
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * List all books order by author ascending and price both descending
     */
    public static void getAllBooksOrderByAuthorNamePriceDesc() {
        books.stream().sorted(Comparator.comparing(Book::getAuthor)
                        .thenComparing(Comparator.comparing(Book::getPrice).reversed()))
                .collect(toList())
                .forEach(System.out::println);
    }

    /**
     * Group books by their authors sorted by price and then by book name
     */
    public static void getBooksGroupByAuthorSorted() {
        Map<String, List<Book>> bookMap = new HashMap<>();

        books.stream()
                .collect(groupingBy(Book::getAuthor))
                .forEach((k, v) -> {
                    bookMap.put(k, v.stream()
                            .sorted(Comparator.comparing(Book::getPrice)
                                    .thenComparing(Book::getBookName))
                            .collect(toList()));

                });
        Map<String, List<Book>> bookMapsorted = new TreeMap<>(bookMap);
        System.out.println(bookMapsorted);
    }

    /**
     * Stream to Map conversion example
     */
    public static void getMapFromStream() {
        System.out.println("Extract ISBN and Book name from stream into a map");
        books.stream().collect(Collectors.toMap(Book::getIsbn, Book::getBookName))
                .forEach((k, v) -> {
                            System.out.printf("ISBN = %s || Name = %s%n",k,v);

                        }
                );
        System.out.println("-:-----------------------------------------------------------------------------:-");
        System.out.println("Extract book id and the entire book object. ");
        books.stream().collect(Collectors.toMap(Book::getBookId, Function.identity()))
                .forEach((k, v) -> {
                            System.out.printf("Id = %s || Book = %s%n",k,v);

                        }
                );
        System.out.println("-:-----------------------------------------------------------------------------:-");
        System.out.println("Comparing by value.");
        Map<Integer, Book> mapBook = books.stream()
                .sorted(Comparator.comparing(Book::getBookName))
                .collect(Collectors.toMap(Book::getBookId, Function.identity()
                        , (o, n) -> o
                        , LinkedHashMap::new));
        System.out.println(mapBook);
    }


}
