
package ua.com.alevel;

import ua.com.alevel.test.Test;
import ua.com.alevel.number.Number;
public class App {
    public static void main( String[] args ) {
        Test test = new Test();
        test.run();
        new Number().numbers();
    }
}