package test;

/**
 * Created by admin on 2017/8/10.
 */
public class CallBacks {
    interface Im {
        void im();
    }

    class ImImpl1 implements Im {
        private int i = 0;

        public void im() {
            i++;
            System.out.println("" + i);
        }
    }

    class Mim {
        public void im() {
            System.out.println("o    t    p");
        }

          void ff(Mim mim) {
            mim.im();
        }
    }

    class ImImpl2 extends Mim {
        private int i = 0;

        public void im() {
            super.im();
            i++;
            System.out.println("" + i);
        }

        private class Cc implements Im {
            public void im() {
                ImImpl2.this.im();
            }
        }

        Im getCallBack() {
            return new Cc();
        }
    }

    class Caller {
        private Im imcallback;

        Caller(Im im) {
            imcallback = im;
        }

        void go() {
            imcallback.im();
        }
    }

    public static void main(String[] strings) {


    }

}
