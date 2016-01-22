/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16-1-22 下午1:20
 */
public class ThreadLocalSequence extends Sequence {

    private ThreadLocal<Integer> base = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return 0;
        }
    };
    private ThreadLocal<Integer> offset = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return Integer.MAX_VALUE;
        }
    };

    public ThreadLocalSequence(int step) {
        super(step);
    }

    @Override
    public int getNext() {
        int off = offset.get();
        if (off < step) {
            offset.set(off + 1);
            return base.get() + off;
        }

        base.set(getSequenceFromDB());
        offset.set(1);
        return base.get();
    }
}
