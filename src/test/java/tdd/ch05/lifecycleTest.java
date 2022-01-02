package tdd.ch05;

import org.junit.jupiter.api.*;

/**
 * Junit lifecycle 테스트 클래스
 */
public class lifecycleTest {

  /**
   * 생성자 모든 @Test 메서드가 실행될때마다 실행
   */
  public lifecycleTest() {
    System.out.println("new Junit5LifecycleTest");
  }

  @BeforeAll
  static void beforeAll(){
    System.out.println("@BeforeAll");
  }

  @BeforeEach
  void setUp(){
    System.out.println("setUp - @BeforeEach");
  }

  @Test
  void a() {
    System.out.println("a - @Test");
  }

  @Test
  void b(){
    System.out.println("b - @Test");
  }

  @AfterEach
  void tearDown() {
    System.out.println("tearDown -  @AfterEach");
  }

  @AfterAll
  static void afterAll() {
    System.out.println("@AfterAll");
  }
}

