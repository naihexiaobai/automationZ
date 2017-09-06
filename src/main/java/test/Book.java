package test;

/**
 * Created by admin on 2017/7/31.
 */
public class Book {
    boolean checkOut = false;

    protected void checkIn() {
        checkOut = false;
        System.out.println("book package ~ ");
    }

    protected void qqq() {
        if (checkOut) {
            System.out.println("error :checkOut ");
        }
    }

    public static void main(String[] qq) {
        Book book = new Book();
        book.checkIn();
        new Book();
        System.gc();
    }
}
