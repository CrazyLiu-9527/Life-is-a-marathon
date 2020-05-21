package FlyWeight;

import java.util.UUID;

/**
 * 用乒乓球举例，一般来说训练的时候会有很多个乒乓球来循环使用，当这批乒乓球用完之后，再去买新的乒乓球
 * @author zhiyuanliu
 * @date 2020/5/21 15:03
 */
public class PingPang {
    /**
     * 给每个乒乓球一个编号和一个状态
     */
    private UUID id = UUID.randomUUID();
    private boolean using = false;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isUsing() {
        return using;
    }

    public void setUsing(boolean using) {
        this.using = using;
    }

    @Override
    public String toString() {
        return "PingPang{" +
                "id=" + id +
                ", using=" + using +
                '}';
    }
}
