### 1. **@Controller와 @RestController 차이**

   주요 차이점은 Http Response Body가 생성되는 방식.
   Controller는 주로 View를 반환하기 위해 사용하는데,Data를 반환해야 하는 경우 @ResposneBody 를 활용하여 Json 형태로 데이터를 반환할 수 있음.
   RestController는 @Controller에 @ResponseBody가 추가된 것으로 Json 형태로 객체 데이터를 반환하는 것이 주용도.


### 2. **@Autowired 동작원리**

   @Autowired는 Spring 프레임워크에서 사용되는 의존성 주입(Dependency Injection) 방법 중 하나로, Spring 컨테이너가 빈 객체를 생성하고 해당 빈 객체가 필요로 하는 다른 빈 객체를 자동으로 주입해주는 기능을 제공.
   이를 통해 객체 간의 의존 관계를 관리하고 객체 생성 및 관리를 단순화할 수 있음.

   **@Autowired의 동작 원리 및 특징**

   - Spring Context 또는 Bean Context에서 @Autowired 어노테이션을 사용하면 해당 필드나 메소드 파라미터의 타입에 맞는 빈을 찾아서 주입.
   - 인터페이스를 지정하면 해당 인터페이스를 구현한 구현체를 주입. 이는 다형성을 활용하여 런타임에 다양한 구현체를 주입할 수 있는 장점을 제공
   - 빈 인스턴스를 생성할 때, BeanPostProcessor를 사용하여 빈 초기화 시 클래스 정보를 읽어와서 자동으로 메타데이터를 얻어내고 주입 작업을 수행합니다. 이 과정에서 Java Reflection을 활용.
   
   **@Autowired를 통한 의존성 주입의 장점:**

  - IoC (Inversion of Control): 객체를 직접 생성하고 관리하는 것이 아닌 Spring 컨테이너가 객체의 라이프사이클을 제어하므로 제어의 역전 개념을 구현하며 애플리케이션의 느슨한 결합을 지원.
  - GC(Garbage Collection): Spring은 빈 객체의 생성 및 소멸을 관리하므로 메모리 관리를 자동으로 처리하며, 효율적으로 객체를 관리.
  - 빈 객체 등록과 원하는 시점에 소스 코드 상에서 주입을 받을 수 있어 유연한 구성이 가능.

**의존성 주입 관리 방법:**

   - 생성자 주입:

     가장 권장되는 방법으로, 생성자를 통해 의존성을 주입받고 이는 객체의 불변성과 의존성 해결에 도움. 
   - Setter 메서드 또는 getter 메서드를 통한 주입:

     생성자 주입이 어렵거나 복잡한 경우, setter 메서드 또는 getter 메서드를 통해 의존성을 주입받을 수 있음. 그러나 이 방법은 객체가 생성된 후에도 변경될 수 있다는 점을 고려해야 함.
   - 필드 주입:

     필드에 직접 주입하는 방법으로, 객체의 내부 상태를 외부에서 직접 변경할 수 있으므로 불변성과 테스트 가능성 측면에서 좋지 않을 수 있음.

   
### 3. **Date 클래스와 LocalDate 클래스**
   
   Java 8 이전 기존 Date 클래스의 문제로 local date 클래스 사용. ( 8이후에도 local date를 사용하는 이유는 좀 더 확인 필요 )
   - 부적절한 클래스와 메서드 이름을 가짐
     
      TimeStamp 방식으로 동작하고 시간을 내재하고 있으나, ClassName은 Date
   - Thread saftety 하지 않음.
     
      mutable 하기 때문에 다른 Thread에서 값을 참조하고 변경할 수 있어서 즉, Thread Safe X

### 4. **compile, api, implementation의 차이는 무엇일까요? only는 무슨뜻일까요?**

   implementaion, api 모두 라이브러리를 적용시키는 키워드인데, 빌드할때 라이브러리가 적용되는 범위가 다름.

   - api, compile은 같은 역할.  

     다만 api를 통해 라이브러리를 가져오는 경우 라이브러리 적용 범위가 달라 Gradle에서는 권장하지는 않음 ( ex. api를 통해 라이브러리를 가져오면 해당 모듈을 의존하는 모듈에도 가져와져서 중복으로 추가되는 문제 )

     gradle 버전이 변경되면서 compile -> api 로 변경되고 compile은 deprecated 됨.


   - Implementation은 의존하는 모듈에는 가져와지지 않아서 코드를 인터페이스로 제한해서 모듈간 의존성을 줄일수 있음.

     또한 모듈을 수정하면, 직접 의존하고 있는 라이브러리만 재빌드하기 때문에 빌드가 빠르다는 장점도 존재.

      참고 블로그 )

         https://bepoz-study-diary.tistory.com/372
         https://kotlinworld.com/317
   
### 5. **mavenCentral과 jcenter 차이점은 무엇인가요?**

Maven Central과 JCenter는 모두 Java 프로젝트의 종속성(라이브러리)을 호스팅하고 제공하는 중앙 저장소(Repository)
하지만 Jcenter는 현재 deprecated됨.
