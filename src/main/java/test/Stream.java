package test;

/**
 * Created by admin on 2017/8/3.
 */
public class Stream extends Root {

    public Stream() {
        System.out.println("这是Stream 。");
    }

    @Override
    public void gogogo(Root root) {
        System.out.println("Stream=-------gogogogo");
    }

    public static void main(String[] arg) {
        Stream stream = new Stream();
        stream.gogogo(stream);
    }
}
