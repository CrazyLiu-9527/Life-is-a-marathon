package FlyWeight;

import java.util.ArrayList;
import java.util.List;

/**
 * 乒乓球的池子
 *
 * @author zhiyuanliu
 * @date 2020/5/21 15:04
 */
public class PingPangPool {
    private List<PingPang> pingPangList = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            pingPangList.add(new PingPang());
        }
    }

    public PingPang getPingPang() {
        for (PingPang pingPang : pingPangList) {
            if(!pingPang.isUsing()) {
                return pingPang;
            }
        }

        return new PingPang();
    }
}