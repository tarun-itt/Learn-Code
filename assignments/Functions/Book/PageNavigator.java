public class PageNavigator {

    private int currentPageIndex;
    private final String[] pages;

    public PageNavigator(String[] pages) {
        this.pages = pages;
        this.currentPageIndex = 0;
    }

    public void moveToNextPage() {
        if (currentPageIndex < pages.length - 1) {
            currentPageIndex++;
        }
    }

    public String getCurrentPageContent() {
        return pages[currentPageIndex];
    }
}
