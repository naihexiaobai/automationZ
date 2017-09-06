package test;

import javax.swing.text.html.HTMLDocument;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by admin on 2017/8/9.
 */
public class Test9 {
    interface A {
        void f();
    }

    interface B {
        int f(int i);
    }

    interface D{
        int f();
    }

    class C {
        int f() {
            return 0;
        }
    }

    class F extends C implements B{
        public int f(int b) {
        return 0;
        }

        public int f( ) {
            return 0;
        }
    }

    public static void main(String[] args){
        ArrayList arrayList=new ArrayList();

    }

    public void iterators( Iterator iterator){
        while (iterator.hasNext()){
             iterator.next().toString();
        }
    }
}
