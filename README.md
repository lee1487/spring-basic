#SPRING 기초 학습

#객체 지향 설계의 5가지 원칙
```
SOLID
  -SRP: 단일 책임 원칙(single responsibility principle)
  -OCP: 개방-폐쇄 원칙(Opne/closed principle)
  -LSP: 리스코프 치환 원칙(Liskov substitution principle)
  -ISP:인터페이스 분리 원칙(Interface segregation princple)
  -DIP:의존관계 역전 원칙(Dependency inversion principle)

SRP 단일 책임 원칙
  - 한 클래스는 하나의 책임만 가져야 한다
  - 하나의 책임이라는 것은 모호하다
    - 클 수 있고, 작을 수 있다.
    - 문맥과 상황에 따라 다르다.
  - 중요한 기준은 변경이다. 변경이 있을 때 파급 효과가 적으면 단일 책임 원칙을 잘 따른 것
    - ex) UI 변경, 객체의 생성과 사용을 분리

OCP 개방-폐쇄 원칙
  - 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
  - 이런 거짓말 같은 말이?
  - 다형성을 활용해보자
  - 인터페이스를 구현한 새로운 클래스를 만들어서 새로운 기능을 구현
  - 지금까지 배운 역할과 구현의 분리를 생각해보자
  
OCP 문제점
  - MemberService 클라이언트가 구현 클래스를 직접 선택
    - MemberRepository m = new MemoryMemberRepositosyt();
    - MemberRepository m = new  JdbcMemberRepository();
  - 구현 객체를 변경하려면 클라이언트 코드를 변경해야 한다
  - 분명 다형성을 사용했지만 OCP 원칙을 지킬 수 없다.
  - 이 문제를 어떻게 해결해야 하나?
  - 객채를 생성하고, 연관관계를 맺어주는 별도의 조립, 설정자가 필요하다.

LSP 리스코프 치환 원칙
  - 프로그램의 객체는 프로그램의 정확성을 깨뜨리지 않으면서 하위 타입의 인스턴스로 바꿀 수 있어야 한다.
  - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙, 인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.
  - 단순히 컴파일에 성공하는 것을 넘어서는 이야기
    -ex) 자동차 인터페이스의 엑셀은 앞으로 가라는 기능, 뒤로 가게 구현하면 LSP 위반, 느리더라도 앞으로 가야함
	
ISP 인터페이스 분리 원칙
  - 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
  - 자동차 인터페이스 -> 운전 인터페이스, 정비 인터페이스로 분리
  - 사용자 클라이언트 -> 운전자 클라이언트, 정비사 클라이언트로 분리
  - 분리하면 정비 인터페이스 자체가 변해도 운전자 클라이언트에 영향을 주지 않음
  - 인터페이스가 명확해지고, 대체 가능성이 높아진다.
 
DIP 의존관계 역전 원칙
  - 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다." 의존성 주입은 이 원칙을 따르는 방법 중 하나다.
  - 쉽게 이야기해서 구현 클래스에 의존하지 말고, 인터페이스에 의존하라는 뜻
  - 앞에서 이야기한 역할(ROLE)에 의존하게 해야 한다는 것과 같다. 객체 세상도 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다.
    구현체에 의존하게 되면 변경이 아주 어려워 진다.
  - 그런데 OCP에서 설명한 MemberService는 인터페이스에 의존하지만, 구현 클래스도 동시에 의존한다.
  - MemberService 클라이언트가 구현 클래스를 직접 선택
    - MemberRepository m = new MemoryMemberRepository();
  - DIP 위반

정리 
  - 객체 지향의 핵심은 다형성
  - 다형성 만으로는 쉽게 부품을 갈아 끼우듯이 개발할 수 없다.
  - 다형성 만으로는 구현 객체를 변경할 때 클라이언트 코드도 함께 변경된다.
  - 다형성 만으로는 OCP, DIP를 지킬 수 없다.
  - 뭔가 더 필요하다.
```

#객체 지향 설계와 스프링
```
다시 스프링으로 
  - 스프링 이야기에 왜 객체 지향 이야기가 나오는가?
  - 스프링은 다음 기술로 다형성 + OCP,DIP를 가능하게 지원
    - DI(Dependency Injection):의존관계, 의존성 주입
	- DI 컨테이너 제공
  - 클라이언트 코드의 변경 없이 기능 확장
  - 쉽게 부품을 교체하듯이 개발
  
  스프링이 없던 시절로
    - 옛날 어떤 개발자가 좋은 객체 지향 개발을 하려고 OCP, DIP 원칙을 지키면서 개발을 해보니,
	  너무 할일이 많았다. 배보다 배꼽이 크다. 그래서 프레임워크로 만들어버림
	- 순수하게 자바로 OCP, DIP 원칙들을 지키면서 개발을 해보면, 결국 스프링 프레임워크를 만들게 된다.(더 정확히는 DI컨테이너)
	- DI 개념은 말로 설명해도 이해가 잘 안된다. 코드로 짜봐야 필요성을 알게된다.
	- 그러면 이제 스프링이 왜: 만들어졌는지 코드로 이해해보자
	
정리 
  - 모든 설계에 역할과 구현을 분리하자
  - 자동차, 공연의 예를 떠올려보자.
  - 애플리케이션 설계또 공연을 설계 하듯이 배역만 만들어두고, 배우는 언제든지 유연하게 변경할 수 있도록 만드는 것이 좋은 객체 지향 설계다.
  - 이상적으로는 모든 설계에 인터페이스를 부여하자.

실무고민
  - 하지만 인터페이스를 도입하면 추상화라는 비용이 발생한다.
  - 기능을 확장할 가능성이 없다면, 구체 클래스를 직접 사용하고, 향후 꼭 필요할 때 리팩터링해서 인터페이스를 도입하는 것도 방법이다.
```

#비즈니스 요구사항과 설계
```
  - 회원
    - 회원을 가입하고 조회할 수 있다.
	- 회원은 일반과 VIP 두 가지 등급이 있다.
	- 회원 데이터는 자체 DB를 구축 할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)
  - 주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
	- 회원 등급에 따라 할인 정책을 적용할 수 있다.
	- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라.(나중에 변경 될 수 있다.)
	- 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 있다.(미확정)
	
	요구사항을 보면 회원 데이터, 할인 정책 같은 부분은 지금 결정하기 어려운 부분이다. 그렇다고 이런 정책이 결정될 때 까지 개발을 무기한 기다릴 수도 없다.
	우리는 앞에서 배운 객체 지향 설계 방법이 있지 않은가!
	
	인터페이스를 만들고 구현체를 언제든지 갈아끼울 수 있도록 설계하면 된다. 그럼 시작해보자.
```

#회원 도메인 실행과 테스트
```
회원 도메인 설계의 문제점
  - 이 코드의 설계상 문제점은 무엇일까요?
  - 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요?
  - DIP를 잘 지키고 있을까요?
  - 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
    -> 주문까지 만들고나서 문제점과 해결 방안을 설명
```

#객체 지향 원리 적용
```
새로운 할인 정책 적용과 문제점
  - 우리는 역할과 구현을 충실하게 분리했다. --> OK
  - 다형성도 활용하고, 인터페이스와 구현 객체를 분리했다. --> OK
  - OCP, DIP 같은 객체지향 설계 원칙을 충실히 준수했다.
    - 그렇게 보이지만 사실은 아니다.
  - DIP: 주문서비스 클라이언트(OrderServiceImpl)는 DiscountPolicy 인터페이스에 의존하면서 DIP를 지킨 것 같은데?
    - 클래스 의존관계를 분석해보자. 추상(인터페이스)뿐만 아니라 구체(구현) 클래스에도 의존하고 있다.
	  - 추상(인터페이스)의존: DiscountPolicy
	  - 구체(구현) zmffotm: FixDiscountPolicy, RateDiscountPolicy
  - OCP: 변경하지 않고 확장 할 수 있다고 했는데
    - 지금 코드는 기능을 확장해서 변경하면, 클라이언트 코드에 영향을 준다. 따라서 OCP를 위반한다.
	
  - 어떻게 문제를 해결 할 수 있을까?
    - 클라이언트 코드인 OrderServiceImpl은 DiscountPolicy의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존한다.
	- 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다.
	- DIP 위반 -> 추상에만 의존하도록 변경(인터페이스에만 의존)
	- DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다.
	
  - 인터페이스에만 의존하도록 설계를 변경하자!!
    - 인터페이스에만 의존하도록 설계와 코드를 변경했다.
	- 그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을가?
	- 실제 실행을 해보면 NPE(Null Pointer Exception)가 발생한다.
	
  - 해결방안 
    - 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl 에 DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
```

#관심사의 분리
```
  - 애플리케이션을 하나의 공연이라고 생각해보자. 각각의 인터페이스를 배역(배우 역할)이라 생각하자. 
    그런데! 실제 배역에 맞는 배우를 선택하는 것은 누가 하는가?
  - 로미오와 줄리엣 공연을 하면 로미오 역할을 누가 할지 줄리엣 역할을 누가 할지는 배우들이 정하는게 아니다. 
    이전 코드는 마치 로미오 역할(인터페이스)을 하는 레오나르도 디카프리오(구현체,배우)가 
	줄리엣 역할(인터페이스)을 하는 여자 주인공(구현체,배우)을 직접 초빙하는 것과 같다.
	디카프리오는 공연도 해야하고 동시에 여자 주인공도 공연에 직접 초빙해야 하는 "다양한 책임" 을 가지고 있다.
	
  - 관심사를 분리하자
    - 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 한다.
	- 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다.
	- 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 별도의 "공연 기획자"가 나올 시점이다.
	- 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자.
	
  - AppConfig 등장
    - 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, "구현 객체를 생성" 하고 "연결" 하는 책임을 가지는 별도의 설정 클래스를 만들자. 
  
  - AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
    - MemberServiceImpl
	- MemoryMemberRepository
	- OrderServiceImpl
	- FixDiscountPolicy
  - AppConfig는 생성한 객체 인스턴스의 차조(레퍼런스)fmf "생성자를 통해서 주입(연결)"해준다.
    - MemberServiceImpl -> MemoryMemberRepository
	- OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
  
  - MemberServiceImpl- 생성자 주입
    - 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다.
    - 단지 MemberRepository 인터페이스만 의존한다.
    - MemberServiceImpl입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    - MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
    - MemberServiceImpl은 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 "실행에만 집중"하면 된다.
    
    - 객체의 생성과 연결은 AppConfig가 담당한다.
    - DIP완성: MemberServiceImpl은 MemberRepository인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
    - "관심사의 분리": 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
    
    - appConfig 객체는 memoryMemberRepository 객체를 생성하고 그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
    - 클라이언트인 memberServiceImpl입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 DI(Dependency Injection)
    우리말로 의존관계 주입 또는 의존성 주입이라 한다.
	
  - OrderServiceImpl- 생성자 주입
    - 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다.
    - 단지 DiscountPolicy 인터페이스만 의존한다.
    - OrderServiceImpl입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    - OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
    - OrderServiceImpl은 이제부터 "실행에만 집중"하면 된다.
	
  - 테스트 코드에서 @BeforeEach는 각 테스트를 실행하기 전에 호출된다
  
  - 정리
    - AppConfig를 통해서 관심사를 확실하게 분리했다.
	- 배역, 배우를 생각해보자
	- AppConfig는 공연 기획자다.
	- AppConfig는 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택한다. 애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
	- 이제 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.
	- OrderServiceImpl은 기능을 실행하는 책임만 지면 된다.
```
