/**
 * Created by Artem on 20.06.16.
 */
public class AbstractFactoryApp {
    public static void main(String[] args) {
        DeviceFactory factory=getFactoryByCountryCode("UA");
        Mouse mouse=factory.getMouse();
        Keyboard keyboard=factory.getKeyboard();
        Touchpad touchpad=factory.getTouchpad();

        mouse.click();
        keyboard.print();
        keyboard.println();
        touchpad.track(10,35);
    }

    public static DeviceFactory getFactoryByCountryCode(String lang){
        if(lang.equals("EN")){
            return new EnDeviceFactory();
        }else if(lang.equals("UA")){
            return new UaDeviceFactory();
        }else{
            throw new RuntimeException("illegal input "+lang);
        }
    }
}

//abstract product
interface Mouse {
    void click();
    void dbClick();
    void scroll(int direction);
}

interface Keyboard {
    void print();

    void println();
}

interface Touchpad {
    void track(int deltaX, int deltaY);
}



//Abstract Factory
interface DeviceFactory {
    Mouse getMouse();
    Keyboard getKeyboard();
    Touchpad getTouchpad();
}



//concrete product
class UaMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("klik myshkou");
    }

    @Override
    public void dbClick() {
        System.out.println("dvoynoy click myshkoy");
    }

    @Override
    public void scroll(int direction) {
        if(direction>0){
            System.out.println("Prokrutka vverh");
        }else if(direction<0){
            System.out.println("Prokrutka vnyz");
        }else{
            System.out.println("Bez prokrutky");
        }
    }
}

class UaKeyboard implements Keyboard{

    @Override
    public void print() {
        System.out.println("Drukuemo stroku");
    }

    @Override
    public void println() {
        System.out.println("Drukuemo stroku z perehodom na novu");
    }
}

class UaTouchpad implements Touchpad{

    @Override
    public void track(int deltaX, int deltaY) {
        int s=(int)Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        System.out.println("Peredvynutsia na "+s+" pikseley");
    }
}


class EnMouse implements Mouse {

    @Override
    public void click() {
        System.out.println("mouse click");
    }

    @Override
    public void dbClick() {
        System.out.println("Double mouse click");
    }

    @Override
    public void scroll(int direction) {
        if(direction>0){
            System.out.println("Scroll up");
        }else if(direction<0){
            System.out.println("Scroll down");
        }else{
            System.out.println("Don't scroll");
        }
    }
}

class EnKeyboard implements Keyboard{

    @Override
    public void print() {
        System.out.println("Print line");
    }

    @Override
    public void println() {
        System.out.println("Print line with the transition on the next line");
    }
}

class EnTouchpad implements Touchpad{

    @Override
    public void track(int deltaX, int deltaY) {
        int s=(int)Math.sqrt(Math.pow(deltaX,2)+Math.pow(deltaY,2));
        System.out.println("Moved on "+s+" pixels");
    }
}



//Concrete Factory
class UaDeviceFactory implements DeviceFactory{

    @Override
    public Mouse getMouse() {
        return new UaMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new UaKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new UaTouchpad();
    }
}

class EnDeviceFactory implements DeviceFactory{

    @Override
    public Mouse getMouse() {
        return new EnMouse();
    }

    @Override
    public Keyboard getKeyboard() {
        return new EnKeyboard();
    }

    @Override
    public Touchpad getTouchpad() {
        return new EnTouchpad();
    }
}