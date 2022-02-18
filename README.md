# SPRING 기초 학습

## 객체 지향 설계의 5가지 원칙
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
  - 다형성에서 하위 클래스는 인터페이스 규약을 다 지켜야 한다는 것, 다형성을 지원하기 위한 원칙,
    인터페이스를 구현한 구현체는 믿고 사용하려면, 이 원칙이 필요하다.
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
  - 앞에서 이야기한 역할(ROLE)에 의존하게 해야 한다는 것과 같다. 
    객체 세상도 클라이언트가 인터페이스에 의존해야 유연하게 구현체를 변경할 수 있다.
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

## 객체 지향 설계와 스프링
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
	- 순수하게 자바로 OCP, DIP 원칙들을 지키면서 개발을 해보면, 
	  결국 스프링 프레임워크를 만들게 된다.(더 정확히는 DI컨테이너)
	- DI 개념은 말로 설명해도 이해가 잘 안된다. 코드로 짜봐야 필요성을 알게된다.
	- 그러면 이제 스프링이 왜: 만들어졌는지 코드로 이해해보자
	
정리 
  - 모든 설계에 역할과 구현을 분리하자
  - 자동차, 공연의 예를 떠올려보자.
  - 애플리케이션 설계또 공연을 설계 하듯이 배역만 만들어두고, 
    배우는 언제든지 유연하게 변경할 수 있도록 만드는 것이 좋은 객체 지향 설계다.
  - 이상적으로는 모든 설계에 인터페이스를 부여하자.

실무고민
  - 하지만 인터페이스를 도입하면 추상화라는 비용이 발생한다.
  - 기능을 확장할 가능성이 없다면, 구체 클래스를 직접 사용하고, 
    향후 꼭 필요할 때 리팩터링해서 인터페이스를 도입하는 것도 방법이다.
```

## 비즈니스 요구사항과 설계
```
  회원
    - 회원을 가입하고 조회할 수 있다.
	- 회원은 일반과 VIP 두 가지 등급이 있다.
	- 회원 데이터는 자체 DB를 구축 할 수 있고, 외부 시스템과 연동할 수 있다.(미확정)
  주문과 할인 정책
    - 회원은 상품을 주문할 수 있다.
	- 회원 등급에 따라 할인 정책을 적용할 수 있다.
	- 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라.(나중에 변경 될 수 있다.)
	- 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 
	  오픈 직전까지 고민을 미루고 싶다. 최악의 경우 할인을 적용하지 않을 수 있다.(미확정)
	
	요구사항을 보면 회원 데이터, 할인 정책 같은 부분은 지금 결정하기 어려운 부분이다. 
	그렇다고 이런 정책이 결정될 때 까지 개발을 무기한 기다릴 수도 없다.
	우리는 앞에서 배운 객체 지향 설계 방법이 있지 않은가!
	
	인터페이스를 만들고 구현체를 언제든지 갈아끼울 수 있도록 설계하면 된다. 그럼 시작해보자.
```

## 회원 도메인 실행과 테스트
```
회원 도메인 설계의 문제점
  - 이 코드의 설계상 문제점은 무엇일까요?
  - 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요?
  - DIP를 잘 지키고 있을까요?
  - 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
    -> 주문까지 만들고나서 문제점과 해결 방안을 설명
```

## 객체 지향 원리 적용
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
	
  어떻게 문제를 해결 할 수 있을까?
    - 클라이언트 코드인 OrderServiceImpl은 DiscountPolicy의 인터페이스 뿐만 아니라 구체 클래스도 함께 의존한다.
	- 그래서 구체 클래스를 변경할 때 클라이언트 코드도 함께 변경해야 한다.
	- DIP 위반 -> 추상에만 의존하도록 변경(인터페이스에만 의존)
	- DIP를 위반하지 않도록 인터페이스에만 의존하도록 의존관계를 변경하면 된다.
	
  인터페이스에만 의존하도록 설계를 변경하자!!
    - 인터페이스에만 의존하도록 설계와 코드를 변경했다.
	- 그런데 구현체가 없는데 어떻게 코드를 실행할 수 있을가?
	- 실제 실행을 해보면 NPE(Null Pointer Exception)가 발생한다.
	
  해결방안 
    - 이 문제를 해결하려면 누군가가 클라이언트인 OrderServiceImpl에
      DiscountPolicy의 구현 객체를 대신 생성하고 주입해주어야 한다.
```

## 관심사의 분리
```
  - 애플리케이션을 하나의 공연이라고 생각해보자. 각각의 인터페이스를 배역(배우 역할)이라 생각하자. 
    그런데! 실제 배역에 맞는 배우를 선택하는 것은 누가 하는가?
  - 로미오와 줄리엣 공연을 하면 로미오 역할을 누가 할지 줄리엣 역할을 누가 할지는 배우들이 정하는게 아니다. 
    이전 코드는 마치 로미오 역할(인터페이스)을 하는 레오나르도 디카프리오(구현체,배우)가 
	줄리엣 역할(인터페이스)을 하는 여자 주인공(구현체,배우)을 직접 초빙하는 것과 같다.
	디카프리오는 공연도 해야하고 동시에 여자 주인공도 공연에 직접 초빙해야 하는 "다양한 책임" 을 가지고 있다.
	
  관심사를 분리하자
    - 배우는 본인의 역할인 배역을 수행하는 것에만 집중해야 한다.
	- 디카프리오는 어떤 여자 주인공이 선택되더라도 똑같이 공연을 할 수 있어야 한다.
	- 공연을 구성하고, 담당 배우를 섭외하고, 역할에 맞는 배우를 지정하는 책임을 담당하는 
	  별도의 "공연 기획자"가 나올 시점이다.
	- 공연 기획자를 만들고, 배우와 공연 기획자의 책임을 확실히 분리하자.
	
  AppConfig 등장
    - 애플리케이션의 전체 동작 방식을 구성(config)하기 위해, 
	  "구현 객체를 생성" 하고 "연결" 하는 책임을 가지는 별도의 설정 클래스를 만들자. 
  
  AppConfig는 애플리케이션의 실제 동작에 필요한 "구현 객체를 생성"한다.
    - MemberServiceImpl
	- MemoryMemberRepository
	- OrderServiceImpl
	- FixDiscountPolicy
  AppConfig는 생성한 객체 인스턴스의 차조(레퍼런스)fmf "생성자를 통해서 주입(연결)"해준다.
    - MemberServiceImpl -> MemoryMemberRepository
	- OrderServiceImpl -> MemoryMemberRepository, FixDiscountPolicy
  
  MemberServiceImpl- 생성자 주입
    - 설계 변경으로 MemberServiceImpl은 MemoryMemberRepository를 의존하지 않는다.
    - 단지 MemberRepository 인터페이스만 의존한다.
    - MemberServiceImpl입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    - MemberServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
    - MemberServiceImpl은 이제부터 "의존관계에 대한 고민은 외부"에 맡기고 "실행에만 집중"하면 된다.
    
    - 객체의 생성과 연결은 AppConfig가 담당한다.
    - DIP완성: MemberServiceImpl은 MemberRepository인 추상에만 의존하면 된다. 이제 구체 클래스를 몰라도 된다.
    - "관심사의 분리": 객체를 생성하고 연결하는 역할과 실행하는 역할이 명확히 분리되었다.
    
    - appConfig 객체는 memoryMemberRepository 객체를 생성하고 
	  그 참조값을 memberServiceImpl을 생성하면서 생성자로 전달한다.
    - 클라이언트인 memberServiceImpl입장에서 보면 의존관계를 마치 외부에서 주입해주는 것 같다고 해서 
	  DI(Dependency Injection) 우리말로 의존관계 주입 또는 의존성 주입이라 한다.
	
  OrderServiceImpl- 생성자 주입
    - 설계 변경으로 OrderServiceImpl은 FixDiscountPolicy를 의존하지 않는다.
    - 단지 DiscountPolicy 인터페이스만 의존한다.
    - OrderServiceImpl입장에서 생성자를 통해 어떤 구현 객체가 들어올지(주입될지)는 알 수 없다.
    - OrderServiceImpl의 생성자를 통해서 어떤 구현 객체를 주입할지는 오직 외부(AppConfig)에서 결정된다.
    - OrderServiceImpl은 이제부터 "실행에만 집중"하면 된다.
	
  테스트 코드에서 @BeforeEach는 각 테스트를 실행하기 전에 호출된다
  
  정리
    - AppConfig를 통해서 관심사를 확실하게 분리했다.
	- 배역, 배우를 생각해보자
	- AppConfig는 공연 기획자다.
	- AppConfig는 구체 클래스를 선택한다. 배역에 맞는 담당 배우를 선택한다.
  	  애플리케이션이 어떻게 동작해야 할지 전체 구성을 책임진다.
	- 이제 각 배우들은 담당 기능을 실행하는 책임만 지면 된다.
	- OrderServiceImpl은 기능을 실행하는 책임만 지면 된다.
```

## AppConfig 리펙토링
```
  - 현재 AppConfig를 보면 "중복"이 있고 "역할"에 따른 "구현"이 잘 안보인다.
  
  리펙토링 후
    - new MemoryMemberRepository() 이 부분이 중복 제거되었다. 
	  이제 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
	- AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다. 
	  애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
```

## 새로운 구조와 할인 정책 적용
```
  - 처음으로 돌아가서 정액 할인 정책을 정률% 할인 정책으로 변경해보자.
  - FixDiscountPolicy -> RateDiscountPolicy
  - 어떤 부분만 변경하면 되겠는가?
  
  AppConfig의 등장으로 애플리케이션이 크게 사용 영역과, 
  객체를 생성하고 구성(Configuration)하는 영역으로 분리되었다.
    - FixDiscountPolicy -> RateDiscountPolicy로 변경해도 구성 영역만 영향을 받고, 
	  사용 영역은 전혀 영향을 받지 않는다.
  
  코드 변경 후 
    - AppConfig에서 할인 정책 역할을 담당하는 구현을 FixDiscountPolicy -> RateDiscountPolicy객체로 변경 했다.
	- 이제 할인 정책을 변경해도, 애플리케이션의 구성 역할을 담당하는 AppConfig만 변경하면 된다. 
	  클라이언트 코드인 OrderServiceImpl를 포함해서 사용영역의 어떤 코드도 변경할 필요가 없다.
	- 구성 영역은 당연히 변경된다. 
	  구성 역할을 담당하는 AppConfig를 애플리케이션이라는 공연의 기획자로 생각하자. 
	  공연 기획자는 공연 참여지인 구현 객체들을 모두 알아야 한다.
```

## 전체 흐름 정리
```
  - 새로운 할인 정책 개발
  - 새로운 할인 정책 적용과 문제점
  - 관심사의 분리
  - AppConfig 리펙토링
  - 새로운 구조와 할인 정책 적용
  
  새로운 할인 정책 개발
    - 다형성 덕분에 새로운 정률 할인 정책 코드를 추가로 개발하는 것 자체는 아무 문제가 없음
  
  새로운 할인 정책 적용과 문제점
    - 새로 개발한 정률 할인 정책을 적용하려고 하니 "클라이언트 코드"인 주문 서비스 구현체도 함께 변경해야함
	- 주문 서비스 클라이언트가 인터페이스인 "DiscountPolicy" 뿐만 아니라, 
	  구체 클래스인 "FixDiscountPolicy"도 함께 의존 -> DIP 위반
	
  관심사의 분리
    - 애플리케이션을 하나의 공연으로 생각
	- 기존에는 클라이언트가 의존하는 서버 구현 객체를 직접 생성하고, 실행함
	- 비유를 하면 기존에는 남자 주인공 배우가 공연도 하고, 
	  동시에 여자 주인공도 직접 초빙하는 다양한 책임을 가지고 있음
	- 공연을 구성하고, 담당 배우를 섭외하고, 지정하는 책임을 담당하는 별도의 "공연 기획자"가 나올 시점
	- 공연 기획자인 AppConfig가 등장
	- AppConfig는 애플리케이션의 전체 동작 방식을 구성(config)하기 위해,
  	  "구현 객체를 생성"하고, "연결"하는 책임
	- 이제부터 클라이언트 객체는 자신의 역할을 실행하는 것만 집중, 권한이 줄어듬(책임이 명확해짐)
	
  AppConfig 리펙토링
    - 구성 정보에서 역할과 구현을 명확하게 분리
	- 역할이 잘 들어남
	- 중복 제거
	
  새로운 구조와 할인 정책 적용
    - 정액 할인 정책 -> 정률% 할인 정책으로 변경
	- AppConfig의 등장으로 애플리케이션이 크게 "사용 영역"과 객체를 생성하고,
	  "구성(Configuration)하는 영역"으로 분리
	- 할인 정책을 변경해도 AppConfig가 있는 구성 영역만 변경하면 됨, 사용 영역은 변경할 필요가 없음. 
	  물론 클라이언트 코드인 주문 서비스 코드도 변경하지 않음
```

## 좋은 객체 지향 설계의 5가지 원칙의 적용
```
여기서 3가지 SRP,DIP,OCP 적용
 
  SRP 단일 책임 원칙
    - 한 클래스는 하나의 책임만 가져야 한다
      - 클라이언트 객체는 직접 구현 객체를 생성하고, 연결하고, 실행하는 다양한 책임을 가지고 있음
  	  - SRP 단일 책임 원칙을 따르면서 관심사를 분리함
  	  - 구현 객체를 생성하고 연결하는 책임은 AppConfig가 담당
  	  - 클라이언트 객체는 실행하는 책임만 담당
	  
  DIP 의존관계 역전 원칙
    - 프로그래머는 "추상화에 의존해야지, 구체화에 의존하면 안된다" 
	  의존성 주입은 이 원칙을 따르는 방법 중 하나다.
	  - 새로운 할인 정책을 개발하고, 적용하려고 하니 클라이언트 코드도 함께 변경해야 했다. 
	    왜냐하면 기존 클라이언트 코드(OrderServiceImpl)는 DIP를 지키며 DiscountPolicy 
		추상 인터페이스에 의존하는 것 같았지만, FixDiscountPolicy 구체화 구현 클래스에도 함께 의존했다.
	  - 클라이언트 코드가 DiscountPolicy 추상화 인터페이스에만 의존하도록 코드를 변경했다.
	  - 하지만 클라이언트 코드는 인터페이스만으로는 아무것도 실행할 수 없다.
	  - AppConfig가 FixDiscountPolicy 객체 인스턴스를 클라이언트 코드 대신 생성해서 
	    클라이언트 코드에 의존관계를 주입했다. 이렇게 해서 DIP 원칙을 따르면서 문제도 해결했다.
		
  OCP 개방-폐쇄 원칙
    - 소프트웨어 요소는 확장에는 열려 있으나 변경에는 닫혀 있어야 한다.
	  - 다형성 사용하고 클라이언트가 DIP를 지킴
	  - 애플리케이션을 사용 영역과 구성 영역으로 나눔
	  - AppConfig가 의존 관계를 FixDiscountPolicy -> RateDiscountPolicy로 
	    변경해서 클라이언트 코드에 주입하므로 클라이언트 코드는 변경하지 않아도 됨
	  - 소프트웨어 요소를 새롭게 확장해도 사용 영역의 변경은 닫혀있다!
```

## IoC, DI, 그리고 컨테이너
```
  제어의 역전 IoC(Inversion of Controll)
    - 기존 프로그램은 클라이언트 구현 객체가 스스로 필요한 서버 구현 객체를 생성하고, 연결하고, 실행했다.
	  한마디로 구현 객체가 프로그램의 제어 흐름을 스스로 조종했다. 개발자 입장에서는 자연스러운 흐름이다.
	- 반면에 AppConfig가 등장한 이후에는 구현 객체는 자신의 로직을 실행하는 역할만 담당한다.
	  프로그램의 제어 흐름은 이제 AppConfig가 가져간다. 예를 들어서, OrderServiceImpl은
	  필요한 인터페이스들을 호출하지만 어떤 구현 객체들이 실행될지 모른다.
	- 프로그램에 대한 제어 흐름에 대한 권한은 모두 AppConfig가 가지고 있다. 심지어 OrderServiceImpl도
	  AppConfig가 생성한다. 그리고 AppConfig는 OrderServiceImpl이 아닌 OrderService 인터페이스의
	  다른 구현 객체를 생성하고 실행할 수 도 있다. 그런 사실도 모른체 OrderServiceImpl은 
	  묵묵히 자신의 로직을 실행할 뿐이다. 
	  
	- 이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.

  프레임워크 vs 라이브러리
    - 프레임워크가 내가 작성한 코드를 제어하고, 대신 실행하면 그것은 프레임워크가 맞다.(Junit)
	- 반면에 내가 작성한 코드가 직접 제어의 흐름을 담당한다면 그것은 프레임워크가 아니라 라이브러리이다.

  의존관계 주입 DI(Dependency Injection)
    - OrderServiceImpl은 DiscountPolicy 인터페이스에 의존한다. 실제 어떤 구현 객체가 사용될지는 모른다.
	- 의존관계는 "정적인 클래스 의존 관계와, 실행 시점에 결정되는 동적인 객체(인스턴스)의존관계" 
	  둘을 분리해서 생각해야 한다.
	  
    정적인 클래스 의존관계
	  - 클래스가 사용하는 import 코드만 보고 의존관계를 쉽게 판단 할 수 있다. 정적인 의존관계는
	    애플리케이션을 실행하지 않아도 분석할 수 있다. 클라스 다이어그램을 보자
		OrderServiceImpl은 MemberRepository, DiscountPolicy에 
		의존한다는 것을 알 수 있다. 그런데 이러한 클래스 의존관계 만으로는 실제 어떤 객체가
		OrderServiceImpl에 주입 될지 알 수 없다.
	클래스 다이어그램
	
	동적인 객체 인스턴스 의존 관계
	  - 애플리케이션 실행 시점에 실제 생성된 객체 인스턴스의 참조가 연결된 의존 관계다.
	객체 다이어그램
	  - 애플리케이션 "실행 시점(런타임)"에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서
	    클라이언트와 서버의 실제 의존관계가 연결 되는 것을 "의존관계 주입"이라 한다.
	  - 객체 인스턴스를 생성하고, 그 참조값을 전달해서 연결된다.
	  - 의존관계 주입을 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의
	    타입 인스턴스를 변경할 수 있다.
	  - 의존관계 주입을 사용하면 정적인 클래스 의존관계를 변경하지 않고, 
	    동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.
		
	IoC 컨테이너, DI 컨테이너
	  - AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해 주는 것을 
	    IoC 컨테이너 또는 "DI 컨테이너"라 한다.
	  - 의존관계 주입에 초점을 맞추어 최근에는 주로 DI 컨테이너라 한다.
	  - 또는 어셈블러, 오브젝트 팩토리 등으로 불리기도 한다.
```

## 스프링으로 전환하기
```
  스프링 컨테이너 
    - ApplicationContext를 스프링 컨테이너라 한다.
	- 기존에는 개발자가 AppConfig를 사용해서 직접 객체를 생성하고, DI를 했지만, 
	  이제부터는 스프링 컨테이너를 통해서 사용한다.
	- 스프링 컨테이너는 @Configuration이 붙은 AppConfig를 설정(구성)정보로 사용한다.
	  여기서 @Bean 이라 적힌 메서드를 모두 호출해서 반환된 객체를 스프링 컨테이너에 등록한다.
	  이렇게 스프링 컨테이너에 등록된 객체를 스프링 빈이라 한다.
	- 스프링 빈은 @Bean이 붙은 메서드의 명을 스프링 빈의 이름으로 사용한다.(memberService, orderService)
	- 이전에는 개발자가 필요한 객체를 AppConfig를 사용해서 직접 조회했지만, 
	  이제부터는 스프링 컨테이너를 통해서 
	  필요한 스프링 빈(객체)를 찾아야 한다. 스프링 빈은 applicationContext.getBean() 메서드를 
	  사용해서 찾을 수 있다.
	- 기존에는 개발자가 직접 자바코드로 모든 것을 했다면 이제부터는 스프링 컨테이너에 객체를 
	  스프링 빈으로 등록하고, 스프링 컨테이너에서 스프링 빈을 찾아서 사용하도록 변경되었다.
	- 코드가 약간 더 복잡해진 것 같은데, 스프링 컨테이너를 사용하면 어떤 장점이 있을까?
```
## 스프링 컨테이너 생성
```
  스프링 컨테이너가 생성되는 과정을 알아보자.
    - ApplicationContext를 스프링 컨테이너라 한다.
	- ApplicationContext는 인터페이스이다.
	- 스프링 컨테이너는 XML을 기반으로 만들 수 있고, 애노테이션 기반의 자바 설정 클래스로 만들 수 있다.
	- 직전에 AppConfig를 사용했던 방식이 애노테이션 기반의 자바 설정 클래스로 스프링 컨테이너를 만든 것이다.
	- 자바 설정 클래스를 기반으로 스프링 컨테이너(ApplicationContext는)를 만들어 보자
	  - new AnnotationConfigApplicationContext(AppConfig.class);
	  - 이 클래스는 ApplicationContext는 인터페이스의 구현체이다.
	  참고 : 더 정확히는 스프링 컨테이너를 부를 때 BeanFactory, ApplicationContext로 구분해서
		   이야기한다. 이부분은 뒤에서 설명하겠다. BeanFactory를 직접 사용하는 경우는 거의 없으므로
		   일반적으로 ApplicationContext를 스프링 컨테이너라 한다.
		   
  스프링 컨테이너의 생성 과정
    1. 스프링 컨테이너 생성
      - new AnnotationConfigApplicationContext(AppConfig.class)
	  - 스프링 컨테이너를 생성할 때는 구성 정보를 지정해주어야 한다.
	  - 여기서는 AppConfig.class를 구성 정보르 지정했다. 
	2. 스프링 빈 등록
	  - 스프링 컨테이너는 파라미터로 넘어온 설정 클래스 정보를 사용해서 스프링 빈을 등록한다.
	  빈 이름
	    - 빈 이름은 메서드 이름을 사용한다.
	    - 빈 이름을 직접 부여할 수 도 있다.
	      - @Bean(name="memberService2")
		  주의 : 빈 이름은 항상 다른 이름을 부여 해야 한다. 같은 이름을 부여하면,
		       다른 빈이 무시되거나, 기존 빈을 덮어버리거나 설정에 따라 오류가 발생한다.
    3. 스프링 빈 의존관계 설정 - 준비
	4. 스프링 빈 의존관계 설정 - 완료
	  - 스프링 컨테이너는 설정 정보를 참고해서 의존관계를 주입(DI)한다.
	  - 단순히 자바 코드를 호출하는 것 같지만, 차이가 있다. 이 차이는 뒤에 싱글톤 컨테이너에서 설명한다 
	  참고 : 스프링은 빈을 생성하고, 의존관계를 주입하는 단계가 나누어져 있다. 그런데 이렇게 자바 코드로 
	       스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다. 여기서는 이해를 돕기 위해
		   개념적으로 나누어 설명했다. 자세한 내용은 의존관걔 자종 주입에서 다시 설명하겠다.
	
	정리
	  - 스프링 컨테이너를 생성하고, 설정(구성) 정보를 참고해서 스프링 빈도 등록하고, 의존관계도 설정했다.
	    이제 스프링 컨테이너에서 데이터를 조회해보자.
```
## 컨테이너에 등록된 모든 빈 조회 
```
  스프링 컨테이너에 실제 스프링 빈들이 잘 등록 되었는지 확인해보자.
    - 모든 빈 출력하기
	  - 실행하면 스프링에 등록된 모든 빈 정보를 출력할 수 있다.
	  - ac.getBeanDefinitionNames() 스프링에 등록된 모든 빈 이름을 조회한다.
	  - ac.getBean() 빈 이름으로 빈 객체(인스턴스)를 조회한다.
	  
	- 애플리케이션 빈 출력하기 
	  - 스프링 내부에서 사용하는 빈은 제외하고, 내가 등록한 빈만 출력해보자
	  - 스프링 내부에서 사용하는 빈은 getRole()로 구분할 수 있다.
	    - ROLE_APPLICATION 일반적으로 사용자가 정의한 빈
		- ROLE_INFRASTRUCTURE 스프링이 내부에서 사용하는 빈
```
