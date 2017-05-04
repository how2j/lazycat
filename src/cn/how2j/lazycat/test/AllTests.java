package cn.how2j.lazycat.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TestTomcat.class, TestWebApp.class })
public class AllTests {

}
