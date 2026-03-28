public class HtmlPrinter implements Printer {

    @Override
    public void printPage(String pageContent) {
        System.out.println(
                "<div style=\"single-page\">" +
                pageContent +
                "</div>"
        );
    }
}
