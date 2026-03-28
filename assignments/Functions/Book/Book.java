public class Book {

    private final String title;
    private final String author;
    private final PageNavigator pageNavigator;

    public Book(String title, String author, PageNavigator pageNavigator) {
        this.title = title;
        this.author = author;
        this.pageNavigator = pageNavigator;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public void turnPage() {
        pageNavigator.moveToNextPage();
    }

    public String getCurrentPage() {
        return pageNavigator.getCurrentPageContent();
    }
}
