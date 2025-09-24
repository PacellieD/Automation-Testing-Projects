public class ParallellTest {
    @org.junit.jupiter.api.Test
    public void premierTest() throws Exception{
        System.out.println("Test parallele : Premier test START => "            + Thread.currentThread().getName());
        System.out.println("Execution du premier test");
        Thread.sleep(300);
        System.out.println("Test parallele : Premier test STOP => "            + Thread.currentThread().getName());
    }
    @org.junit.jupiter.api.Test
    public void deuxiemeTest() throws Exception{
        System.out.println("Test parallele : Deuxieme test START => "            + Thread.currentThread().getName());
        System.out.println("Execution du deuxieme test");
        Thread.sleep(300);
        System.out.println("Test parallele : Deuxieme test STOP => "            + Thread.currentThread().getName());
    }
}
