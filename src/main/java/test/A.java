package test;

/**
 * Created by admin on 2017/8/3.
 */
class A {
    private int oi = 1;
    private void hi() { System.out.println("Outer hi"); }
    class Inner {
        private int  qq;
        void modifyOuter() {
            oi *= 2;
            hi();
        }
    }
    public void showOi() { System.out.println(oi); }
    void testInner() {
        Inner in = new Inner();
        in.modifyOuter();
    }
    public static void main(String[] args) {
        A out = new A();
//        out.
        out.showOi();
        out.testInner();
        out.showOi();
    }
}