/**
 * title: <br/>
 * description: 描述<br/>
 * Copyright: Copyright (c)2014<br/>
 * Company: 易宝支付(YeePay)<br/>
 *
 * @author wenkang.zhang
 * @version 1.0.0
 * @since 16-1-21 下午5:08
 */
public class SynchronizedSequence extends Sequence {

    public SynchronizedSequence(int step) {
        super(step);
    }

    @Override
    public synchronized int getNext() {
        if (offset.intValue() < step) {
            return base + offset.getAndIncrement();
        }

        base = getSequenceFromDB();
        offset.set(0);
        return base + offset.getAndIncrement();
    }
}
