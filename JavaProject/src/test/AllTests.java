package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ PlaneTest.class, Point3DTest.class, SphereTest.class, TriangleTest.class, VectorTest.class })
public class AllTests {


}
