package packagedemo.genericdemo;

/**
 * Description:
 * Generic interface
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/14
 */

public class ObjectTool3<T> implements InterfaceTool<T>{
    @Override
    public void show(T t) {
        System.out.println(t);
    }
}
