package AbstractFactory;

/**
 * @author liuzy
 * @date 2020/5/19 00:05
 */
public interface Factory {
    Phone create();
    Charger produce();
    HeadSet make();
}
