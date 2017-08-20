package packagedemo.guiadapterdemo;

/**
 * Description:
 * Adapter
 *
 * @author apktool
 * @version 1.0
 * @since 2017/8/20
 */
public class GuiAdapterDemo {
    public static void main(String[] args){
        UserDao ud = new UserDaoImpl();
        ud.add();
    }
}
