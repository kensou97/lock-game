import java.util.concurrent.locks.ReentrantLock;

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
public class ReentrantLockSequence extends Sequence {

    private ReentrantLock reentrantLock = new ReentrantLock();

    public ReentrantLockSequence(int step) {
        super(step);
    }

    @Override
    public int getNext() {
        //TODO 仍然有问题...根源在于base和offset的修改不是原子性能的...
        int value = offset.getAndIncrement();
        if (value < step) {
            int r = base + value;
            return r;
        }

        try {
            reentrantLock.lock();
            value = offset.getAndIncrement();
            if (value < step) {
                int r = base + value;
                return r;
            }

            offset.set(1);
            base = getSequenceFromDB();
            return base;
        } finally {
            reentrantLock.unlock();
        }
    }
}
