# SPRING 기초 학습

## 객체 지향 설계와 스프링

### 객체 지향 설계의 5가지 원칙
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

### 객체 지향 설계와 스프링
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

## 스피링 핵심 원리 이해 1

### 비즈니스 요구사항과 설계
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

### 회원 도메인 실행과 테스트
```
회원 도메인 설계의 문제점
  - 이 코드의 설계상 문제점은 무엇일까요?
  - 다른 저장소로 변경할 때 OCP 원칙을 잘 준수할까요?
  - DIP를 잘 지키고 있을까요?
  - 의존관계가 인터페이스 뿐만 아니라 구현까지 모두 의존하는 문제점이 있음
    -> 주문까지 만들고나서 문제점과 해결 방안을 설명
```

## 스피링 핵심 원리 이해 2

### 객체 지향 원리 적용
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

### 관심사의 분리
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

### AppConfig 리펙토링
```
  - 현재 AppConfig를 보면 "중복"이 있고 "역할"에 따른 "구현"이 잘 안보인다.
  
  리펙토링 후
    - new MemoryMemberRepository() 이 부분이 중복 제거되었다. 
	  이제 MemoryMemberRepository를 다른 구현체로 변경할 때 한 부분만 변경하면 된다.
	- AppConfig를 보면 역할과 구현 클래스가 한눈에 들어온다. 
	  애플리케이션 전체 구성이 어떻게 되어있는지 빠르게 파악할 수 있다.
```

### 새로운 구조와 할인 정책 적용
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

### 전체 흐름 정리
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

### 좋은 객체 지향 설계의 5가지 원칙의 적용
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

### IoC, DI, 그리고 컨테이너
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
	  
	- 이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 
	  제어의 역전(IoC)이라 한다.

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

### 스프링으로 전환하기
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

## 스프링 컨테이너와 스프링 빈

### 스프링 컨테이너 생성
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
	       스프링 빈을 등록하면 생성자를 호출하면서 의존관계 주입도 한번에 처리된다. 
		   여기서는 이해를 돕기 위해 개념적으로 나누어 설명했다. 
		   자세한 내용은 의존관걔 자종 주입에서 다시 설명하겠다.
	
	정리
	  - 스프링 컨테이너를 생성하고, 설정(구성) 정보를 참고해서 스프링 빈도 등록하고, 의존관계도 설정했다.
	    이제 스프링 컨테이너에서 데이터를 조회해보자.
```
### 컨테이너에 등록된 모든 빈 조회 
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

### 스프링 빈 조회 - 기본
```
  스프링 컨테이너에서 스프링 빈을 찾는 가장 기본적인 조회 방법
    - ac.getBean(빈 이름, 타입)
	- ac.getBean(타입)
	- 조회 대상 스프링 빈이 없으면 예외 발생
	  - NoSuchBeanDefinitionException: No bean named 'xxxxxx' available
```

### 스프링 빈 조회 - 동일한 타입이 둘 이상
```
  - 타입으로 조회시 같은 타입의 스프링 빈이 둘 이상이면 오류가 발생한다. 이때는 빈 이름을 지정하자.
  - ac.getBeanOrTypes()을 사용하면 해당 타입의 모든 빈을 조회할 수 있다.
```

### 스프링 빈 조회 - 상속 관계
```
  - 부모 타입으로 조회하면, 자식 타입도 함께 조회한다.
  - 그래서 모든 자바 객체의 최고 부모인 Object 타입으로 조회하면, 모든 스프링 빈을 조회한다.
```

### BeanFactory와 ApplicationContext
```
  beanFactory와 ApplicationContext에 대해서 알아보자
  
  BeanFactory
    - 스프링 컨테이너의 최상위 인터페이스이다.
	- 스프링 빈을 관리하고 조회하는 역할을 담당한다.
	- getBean()을 제공한다.
	- 지금까지 우리가 사용했던 대부분의 기능은 BeanFactory가 제공하는 기능이다.
  
  ApplicationContext
    - BeanFactory 기능을 모두 상속받아서 제공한다.
	- 빈을 관리하고 검색하는 기능을 BeanFactory가 제공해주는데, 그러면 둘의 차이가 뭘까?
	- 애플리케이션을 개발할 때는 빈을 관리하고 조회하는 기능은 물론이고, 수 많은 부가기능이 필요하다.
	
  ApplicationContext가 제공하는 부가기능
    메세지소스를 활용한 국제화 기능
	  - 예를 들어서 한국에서 들어오면 한국어로, 영어권에서 들어오면 영어로 출력
	환경변수
	  - 로컬, 개발, 운영등을 구분해서 처리
	애플리케이션 이벤트
	  - 이벤트를 발행하고 구독하는 모델을 편리하게 지원
	편리한 리소스 조회
	  - 파일, 클래스패스, 외부 등에서 리소스를 편리하게 조회
	  
  정리
    - ApplicationContext는 BeanFactory의 기능을 상속받는다
	- ApplicationContext는 빈 관리기능 + 편리한 부가 기능을 제공한다.
	- BeanFactory를 직접 사용할 일은 거의 없다. 부가기능이 포함된 ApplicationContext를 사용한다.
	- BeanFactory나 ApplicationContext를 스프링 컨테이너라 한다.
```

### 다양한 설정 형식 지원 - 자바 코드, XML
```
  - 스프링 컨테이너는 다양한 형식의 설정 정보를 받아드릴 수 있게 유연하게 설계되어 있다.
    - 자바 코드, XML, Grooby 등등
	
  애노테이션 기반 자바 코드 설정 사용
    - 지금까지 했던 것이다.
	- new AnnotationConfigApplicationContext(AppConfig.class)
	- AnnotationConfigApplicationContext 클래스를 사용하면서 자바 코드로된 설정 정보를 넘기면 된다.
  
  XML 설정 사용
    - 최근에는 스프링 부트를 많이 사용하면서 XML 기반의 설정은 잘 사용하지 않는다. 
	  아직 많은 레거시 프로젝트 들이 XML로 되어있고, 
	  또 XML을 사용하면 컴파일 없이 빈 설정 정보를 변경할 수 있는 장점도 있으므로 
	  한번쯤 배워두는 것도 괜찮다.
	- GenericXmlApplicationContext를 사용하면서 xml 설정 파일을 넘기면 된다.
```

### 스프링 빈 설정 메타 정보 - BeanDefinition
```
  - 스프링은 어떻게 이런 다양한 설정 형식을 지원하는 것일까? 그 중심에는 BeanDefinition이라는 추상화가 있다.
  - 쉽게 이야기해서 역할과 구현을 개념적으로 나눈 것이다.
    - XML을 읽어서 BeanDefinition을 만들면 된다.
	- 자바 코드를 읽어서 BeanDefinition을 만들면 된다.
	- 스프링 컨테이너는 자바 코드인지, XML인지 몰라도 된다. 오직 BeanDefinition만 알면 된다.
  - BeanDefinition을 빈 설정 메타정보라 한다.
    - @Bean, <bean>당 각각 하나씩 메타 정보가 생성된다.
  - 스프링 컨테이너는 이 메타정보를 기반으로 스프링 빈을 생성한다.
  
  코드 레벨로 조금 더 깊이 있게 들어가 보자.
    - AnnotationConfigApplicationContext는 AnnotatedBeanDefinitionReader를 
	  사용해서 AppConfig.class를 읽고 BeanDefinition을 생성한다.
	- GenericXmlApplicationContext는 XmlBeanDefinitionReader를 
	  사용해서 appConfig.xml 설정 정보를 읽고 BeanDefinition을 생성한다.
	- 새로운 형식의 설정 정보가 추가되면, XxxBeanDefinitionReader를 
	  만들어서 BeanDefinition을 생성하면 된다.
	  
  BeanDefinition 살펴보기
    BeanDefinition 정보
	  - BeanClassName: 생성할 빈의 클래스 명(자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
	  - factoryBeanName: 팩토리 역할의 빈을 사용할 경우 이름, 예) appConfig
	  - factoryMethodName: 빈을 생성할 팩토리 메서드 지정, 예) memberService
	  - Scope: 싱글톤(기본값)
	  - lazyInit: 스프링 컨테이너를 생성할 때 빈을 생성하는 것이 아니라, 실제 빈을 사용할 때 까지
	    최대한 생성을 지연처리 하는지 여부
	  - InitMethodName: 빈을 생성하고, 의존관계를 적용한 뒤에 호출되는 초기화 메서드 명
	  - DestoryMethodName: 빈의 생명주기가 끝나서 제거하기 직전에 호출되는 메서드 명
	  - Constructor arguments, Properties: 의존관계 주입에서 사용한다.
	    (자바 설정 처럼 팩토리 역할의 빈을 사용하면 없음)
		
  정리
    - BeanDefinition을 직접 생성해서 스프링 컨테이너에 등록할 수 도 있다. 하지만 실무에서 
	  BeanDefinition을 직접 적의하거나 사용할 일은 거의 없다. -> 어려우면 그냥 넘어가면 된다^^
	- BeanDefinition에 대해서는 너무 깊이있게 이해하기 보다는, 스프링이 다양한 형태의 설정 정보를
	  BeanDefinition으로 추상화해서 사용하는 것 정도만 이해하면 된다.
	- 가끔 스프링 코드나 스프링 관련 오픈 소스의 코드를 볼 때, BeanDefinition 이라는 것이 보일 때가 있다.
	  이때 이러한 매커니즘을 떠올리면 된다.
```

## 싱글톤 컨테이너

### 웹 애플리케이션과 싱글톤
```
  웹 애플리케이션과 싱글톤
    - 스프링은 태생이 기업용 온라인 서비스 기술을 지원하기 위해 탄생했다.
	- 대부분의 스프링 애플리케이션은 웹 애플리케이션이다. 물론 웹이 아닌 애플리케이션 개발도 
	  얼마든지 개발할 수 있다.
	- 웹 애플리케이션은 보통 여러 고객이 동시에 요청을 한다.
    
	스프링 없는 순수한 DI 컨테이너 테스트
	  - 우리가 만들었던 스프링 없는 순수한 DI 컨테이너인 AppConfig는 
	    요청을 할 때 마다 객체를 새로 생성한다.
	  - 고객 트래픽이 초당 100이 나오면 초당 100개 객체가 생성되고 소멸된다! -> 메모리 낭비가 심하다.
	  - 해결방안은 해당 객체가 딱 1개만 생성되고, 공유하도록 설계하면 된다. -> 싱글톤 패턴
```

### 싱글톤 패턴
```
  - 클래스의 인스턴스가 딱 1개만 생성되는 것을 보장하는 디자인 패턴이다.
  - 그래서 객체 인스턴스를 2개 이상 생성하지 못하도록 막아야 한다.
    - private 생성자를 사용해서 외부에서 임의로 new 키워드를 사용하지 못하도록 막아야 한다.
  
  - 싱글톤 패턴을 적용한 예제 코드를 보자 main이 아인 test 위치에 생성하자.
    1. static 영역에 객체 instance를 미리 하나 생성해서 올려둔다.
	2. 이 객체 인스턴스가 필요하면 오직 getInstance() 메서드를 통해서만 조회할 수 있다.
	   이 메서드를 호출하면 항상 같은 인스턴스를 반환한다.
	3. 딱 1개의 객체 인스턴스만 존재해야 하므로, 생성자를 private으로 막아서 혹시라도 외부에서
	   new 키워드로 객체 인스턴스가 생성되는 것을 막는다.
	   
	- 싱글톤 패턴을 사용하는 테스트 코드를 보자
	  - private으로 new 키워드를 막아두었다.
	  - 호출할 때 마다 같은 객체 인스턴스를 반환하는 것을 확인할 수 있다.
	참고 : 싱글톤 패턴을 구현하는 방법은 여러가지가 있다. 여기서는 객체를 미리 생성해 두는 가장 단순하고
	     안정적인 방법을 선택했다.
	
	- 싱글톤 패턴을 적용하면 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 
	  이미 만들어진 객체를 공유해서 효율적으로 사용할 수 있다. 
	  하지만 싱글톤 패턴은 다음과 같은 수 많은 문제점들을 가지고 있다.
	
  - 싱글톤 패턴의 문제점
    - 싱글톤 패턴을 구현하는 코드 자체가 많이 들어간다.
    - 의존관계상 클라이언트가 구체 클래스에 의존한다. -> DIP를 위반한다.
    - 클라이언트가 구체 클래스에 의존하세 OCP원칙을 위반할 가능성이 높다.
    - 테스트하기 어렵다.
    - 내부 속성을 변경하거나 초기화 하기 어렵다.
    - private 생성자로 자식 클래스를 만들기 어렵다.
    - 결론적으로 유연성이 떨어진다.
    - 안티패턴으로 불리기도 한다.	
```

### 싱글톤 컨테이너 
```
  - 스프링 컨테이너는 싱글톤 패턴의 문제점을 해결하면서, 객체 인스턴스를 싱글톤(1개만 생성)으로 관리한다.
    지금까지 우리가 학습한 스프링 빈이 바로 싱글톤으로 관리되는 빈이다.
	
  싱글톤 컨테이너
    - 스프링 컨테이너는 싱글턴 패턴을 적용하지 않아도, 객체 인스턴스를 싱글톤으로 관리한다.
	  - 이전에 설명한 컨테이너 생성 과정을 자세히 보자. 컨테이너는 객체를 하나만 생성해서 관리한다.
	- 스프링 컨테이너는 싱글톤 컨테이너 역할을 한다. 이렇게 싱글톤 객체를 생성하고 관리하는 기능을
	  싱글톤 레지스트리라 한다.
	- 스프링 컨테이너의 이런 기능 덕분에 싱글턴 패턴의 모든 단점을 해결하면서 
	  객체를 싱글톤으로 유지할 수 있다.
	  - 싱글톤 패턴을 위한 지저분한 코드가 들어가지 않아도 된다.
	  - DIP, OCP, 테스트, private 생성자로 부터 자유롭게 싱글톤을 사용할 수 있다.
	  
	싱글톤 컨테이너 적용 후
	  - 스프링 컨테이너 덕분에 고객의 요청이 올 때 마다 객체를 생성하는 것이 아니라, 
	    이미 만들어진 객체를 공유해서 효율적으로 재사용 할 수 있다.
    침고: 스프링의 기본 빈 등록 방식은 싱글톤이지만, 싱글톤 방식만 지원하는 것은 아니다. 
	    요청할 때 마다 새로운 객체를 생성해서 반환하는 기능도 제공한다. 
		자세한 내용은 뒤에 빈 스코프에서 설명하겠다.
```

### 싱글톤 방식의 주의점
```
  - 싱글톤 패턴이든, 스프링 같은 싱글톤 컨테이너를 사용하든, 객체 인스턴스를 하나만 생성해서
    공유하는 싱글톤 방식은 여러 클라이언트가 하나의 같은 객체 인스턴스를 공유하기 때문에 싱글톤 객체는
	상태를 유지(stateful)하게 설계하면 안된다.
  - 무상태(stateless)로 설계해야 한다!
    - 특정 클라이언트에 의존적인 필드가 있으면 안된다.
	- 특정 클라이언트가 값을 변경할 수 있는 필드가 있으면 안된다.
	- 가급적 읽기만 가능해야 한다.
	- 필드 대신에 자바에서 공유되지 않는, 지역변수, 파라미터, ThreadLocal 등을 사용해야 한다.
  - 스프링 빈의 필드에 공유 값을 설정하면 정말 큰 장애가 발생할 수 있다!!! 
  
  - 상태를 유지할 경우 발생하는 문제점 예시
    - 최대한 단순히 설명하기 위해, 실제 쓰레드는 사용하지 않았다.
	- ThreadA가 사용자A 코드를 호출하고 ThreadB가 사용자B 코드를 호출한다 가정하자.
	- StatefulService의 price 필드는 공유되는 필드인데, 특정 클라이언트가 값을 변경한다.
	- 사용자A의 주문금액은 10000원이 되어야 하는데, 20000원이라는 결과가 나왔다.
	- 실무에서 이런 경우를 종종 보는데, 이로인해 정말 해결하기 어려운 큰 문제들이 터진다.
	  (몇년에 한번씩 꼭 만난다.)
	- 진짜 공유필드는 조심해야 한다! 스프링 빈은 항상 무상태(stateless)로 설계하자.
```

### @Configuration과 싱글톤
```
  그런데 이상한점이 있다. 다음 AppConfig 코드를 보자.
    - memberService 빈을 만드는 코드를 보면 memberRepository()를 호출한다.
	  - 이 메서드를 호출하면 new MemoryMemberRepository()를 호출한다.
	- orderService 빈을 만드는 코드도 동일하게 memberRepository()를 호출한다.
	  - 이 메서드를 호출하면 new MemoryMemberRepository()를 호출한다.
	
	- 결과적으로 각각 다른 2개의 MemoryMemberRepository가 생성되면서 
	  싱글톤이 깨지는 것 처럼 보인다. 스프링 컨테이너는 이 문제를 어떻게 해결할까?
	
	직접 테스트 해보자.
	  - 확인해보면 memberRepository 인스턴스는 모두 같은 인스턴스가 공유되어 사용된다.
	  - AppConfig의 자바 코드를 보면 분명히 각각 2번 new MemoryMemberRepository
		호출해서 다른 인스턴스가 생성되어야 하는데?
      - 어떻게 된 일일까? 혹시 두 번 호출이 안되는 것일까? 실험을 통해 알아보자. 
	
	AppConfig에 호출 로그 남김
```

### @Configuration과 바이트코드 조작의 마법
```
  - 스프링 컨테이너는 싱글톤 레지스트리다. 따라서 스프링 빈이 싱글톤이 되도록 보장해주어야 한다.
    그런데 스프링이 자바 코드까지 어떻게 하기는 어렵다. 저 자바 코드를 보면 분명 3번 호출
	되어야 하는 것이 맞다. 그래서 스프링은 클래스의 바이트 코드를 조작하는 
	라이브러리를 사용한다. 모든 비밀은 @Configuration을 적용한 AppConfig에 있다.
	
  다음 코드를 보자.
    - 사실 AnnotationConfigApplicationContext에 파라미터로 넘긴 값은
	  스프링 빈으로 등록된다. 그래서 AppConfig도 스프링 빈이 된다.
	- AppConfig 스프링 빈을 조회해서 클래스 정보를 출력해보자.
	- 순수한 크래스라면 다음과 같이 출력되어야 한다.
	  - class hello.core.AppConfig
	- 그런데 예상과는 다르게 클래스 명에 xxxCGUB가 붙으면서 상당히 복잡해진 것을
	  볼 수 있다. 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB라는 바이트 코드
	  조작 라이브러리를 사용해서 AppConfig 클래스를 상속받은 임의의 다른 클래스를
	  만들고, 그 다른 클래스를 스프링 빈으로 등록한 것이다!
	  
	- 그 임의의 클래스가 바로 싱글톤이 보장되도록 해준다. 아마도 다음과 같이
	  바이트 코드를 조작해서 작성되어 있을 것이다.
	  (실제로는 CGLIB의 내부 기술을 사용하는데 매우 복잡하다.)
	AppConfig@CGLIB 예상 코드
	
	@Bean
	public MemberRepository memberRepository() {
		if (memoryMemberRepository가 이미 스프링 컨테이너네 등록되어 있으면?) {
			return 스프링 컨테이너에서 찾아서 반환;
		} else {	//스프링 컨테이너에 없으면
			//기존 로직을 호출해서 MemoryMemberRepository를 
			//생성하고 스프링 컨테이너에 등록
			return 반환
		}
	}
	
	- @Bean이 붙은 메서드마다 이미 스프링 빈이 존재하면 존재하는 빈을 반환하고,
	  스프링 빈이 없으면 생성해서 스프링 빈으로 등록하고 반환하는 코드가 동적으로 만들어진다.
	- 덕분에 싱글톤이 보장되는 것이다.
	참고: AppConfig@CGLIB는 AppConfig의 자식 타입이므로, 
	    AppConfig 타입으로 조회 할 수 있다.

  @Configuration을 적용하지 않고 @Bean만 적용하면 어떻게 될까?
    - @Configuration을 붙이면 바이트코드를 조작하는 CGLIB 기술을 사용해서 싱글톤을
	  보장하지만, 만약 @Bean만 적용하면 어떻게 될까?
	  - AppConfig가 CGLIB 기술 없이 순수한 AppConfig로 스프링 빈에 
	    등록된 것을 확인할 수 있다.
	  - MemberRepository가 총 3번 호출된 것을 알 수 있다. 1번은 
	    @Bean에 의해 스프링 컨테이너에 등록하기 위해서이고, 2번은 각각
		memberRepository()를 호출하면서 발생한 코드이다.  
```

## 컴포넌트 스캔

### 컴포넌트 스캔과 의존관계 자동 주입 시작하기
```
  - 지금까지 스프링 빈을 등록할 때는 자바 코드의 @Bean이나 XML의 <bean> 등을 통해서 
    설정 정보에 직접 등록할 스프링 빈을 나열했다.
  - 예제에서는 몇개가 안되었지만, 어떻게 등록해야 할 스프링 빈이 수십, 수백개가 되면 일일이
    등록하기도 귀찮고, 설정 정보도 커지고, 누락하는 문제도 발생한다. 역시 개발자는 
	반복을 싫어한다.(무엇보다 귀찮다.)
  - 그래서 스프링은 설정 정보가 없어도 자동으로 스프링 빈을 등록하는 컴포넌트 스캔이라는
    기능을 제공한다.
  - 또 의존관계도 자동으로 주입하는 @Autowired라는 기능도 제공한다.
  
  코드로 컴포넌트 스캔과 의존관계 자동주입을 알아보자
  먼저 기존 AppConfig.java는 과거 코드와 테스트를 유지하기 위해 남겨두고, 
  새로운 AutoAppConfig.java를 만들자.
    - 컴포넌트 스캔을 사용하려면 먼저 @ComponentScan을 설정 정보에 붙여주면 된다.
	- 기존의 AppConfig와는 다르게 @Bean으로 등록한 클래스가 하나도 없다!
	
	참고: 컴포넌트 스캔을 사용하면 @Configuration이 붙은 설정 정보도 자동으로 
	    등록되기 때문에, AppConfig, TestConfig 등 앞서 만들어 두었던
		설정 정보도 함께 등록되고, 실행되어 버린다. 그래서 excludeFilters를 
		이용해서 설정정보는 컴포넌트 스캔 대상에서 제외했다. 보통 설정 정보를 
		스캔 대상에서 제외하지는 않지만, 기존 예제 코드를 최대한 남기고 
		유지하기 위해서 이 방법을 선택했다.
		
	- 컴포넌트 스캔은 이름 그대로 @Component 애노테이션이 붙은 클래스를 스캔해서
	  스프링 빈으로 등록한다. @Component를 붙여주자
	참고: @Configuration이 컴포넌트의 스캔의 대상이 된 이유도 @Configuration
	    소스코드를 열어보면 @Component 애노테이션이 붙어있기 때문이다.
	- 이제 각 클래스가 컴포넌트 스캔의 대상이 되도록 @Component 애노테이션을 붙여주자.
	
	- 이전에 AppConfig에서는 @Bean으로 직접 설정 정보를 작성했고, 의존관계도 직접
	  명시했다. 이제는 이런 설정 정보 자체가 없기 때문에 의존관계 주입도 이 클래스 안에서
	  해결해야 한다.
	- @Autowired는 의존관계를 자동으로 주입해준다. 자세한 룰은 조금 뒤에 설명하겠다. 
	- @Autowired를 사용하면 생성자에서 여러 의존관계도 한번에 주입받을 수 있다.
	
	- AnnotationConfigApplicationContext를 사용하는것은 기존과 동일하다.
	- 설정 정보로 AutoAppConfig 클래스를 넘겨준다.
	- 실행해보면 기존과 같이 잘 동작하는 것을 확인할 수 있다. 
	- 로그를 잘 보면 컴포넌트 스캔이 잘 동작하는 것을 확인할 수 있다.
	
	1.@ComponentScan
	  - @ComponentScan은 @Component가 붙은 모든 클래스를 스프링 빈으로 등록한다.
	  - 이때 스프링 빈의 기본 이름은 클래스명을 사용하되 맨 앞글자만 소문자를 사용한다.
	    - 빈 이름 기본 전략: MemberServiceImpl -> memberServiceImpl
		- 빈 이름 직접 지정: 만약 스프링 빈의 이름을 직접 지정하고 싶으면
		  @Component("memberService2") 이런식으로 이름을 부여하면 된다.
		  
	2. @Autowired 의존관계 자동 주입
	  - 생성자에 @Autowired를 지정하면, 스프링 컨테이너가 자동으로
	    해당 스프링 빈을 찾아서 주입한다.
	  - 이때 기본 조회 전략은 타입이 같은 빈을 찾아서 주입한다.
	    - getBean(MemberRepository.class)와 동일하다고 이해하면 된다.
		- 더 자세한 내용은 뒤에서 설명한다.
	  - 생성자에 파라미터가 많아도 다 찾아서 자동으로 주입한다.
```

### 탐색 위치와 기본 스캔 대상
```
  탐색할 패키지의 시작 위치 지정
    - 모든 자바 클래스를 다 컴포넌트 스캔하면 시간이 오래 걸린다. 그래서 꼭 필요한 위치부터
	  탐색하도록 시작 위치를 지정할 수 있다.
	    - basePackages: 탐색할 패키지의 시작 위치를 지정한다. 이 패키지를 포함해서 
		                하위 패키지를 모두 탐색한다.
		  - basePackages = {"hello.core", "hello.service"}
		    이렇게 여러 시작 위치를 지정할 수도 있다.
		- basePackageClasses: 지정한 클래스의 패키지를 탐색 시작 위치로 지정한다.
		- 만약 지정하지 않으면 @ComponentScan이 붙은 설정 정보 클래스의 패키지가
		  시작 위치가 된다.
		
		권장하는 방법
		- 개인적으로 즐겨 사용하는 방법은 패키지 위치를 지정하지 않고, 설정 정보 클래스의
		  위치를 프로젝트 최상단에 두는 것이다. 최근 스프링 부트도 이 방법을 기본으로 
		  제공한다.
		- 예를 들어서 프로젝트가 다음과 같이 구조가 되어 있으면 
		  com.hello
		  com.hello.service
		  com.hello.repository
			
		  com.hello -> 프로젝트 시작 루트, 여기에 AppConfig같은 메인 설정 정보를
		  두고, @ComponentScan 애노테이션을 붙이고 basePackages 지정은 생략한다.
			
		- 이렇게 하면 com.hello를 포함한 하위는 모두 자동으로 컴포넌트 스캔의 대상이 된다.
		  그리고 프로젝트 메인 설정 정보는 프로젝트를 대표하는 정보이기 때문에 프로젝트 시작
		  루트 위치에 두는 것이 좋다 생각한다.
		  참고로 스프링 부트를 사용하면 스프링 부트의 대표 시작 정보인 
		  @SpringBootApplication를 이 프로젝트 시작 루트 위치에 두는 것이
		  관례이다(그리고 이 설정안에 바로 @ComponentScan이 들어있다.)
  
  컴포넌트 스캔 기본 대상 
    - 컴포넌트 스캔은 @Component 뿐만 아니라 다음과 같은 내용도 추가로 대상에 포함된다.
	  - @Component: 컴포넌트 스캔에서 사용
	  - @Controller: 스프링 MVC 컨트롤러에서 사용
	  - @Service: 스프링 비즈니스 로직에서 사용
	  - @Repository: 스프링 데이터 접근 계층에서 사용
	  - @Configuration: 스프링 설정 저보에서 사용
	- 해당 클래스의 소스 코드를 보면 @Component를 포함하고 있는 것을 알 수 있다.
	
	참고: 사실 애노테이션에는 상속관계라는 것이 없다. 그래서 이렇게 애노테이션이 특정 
	    애노테이션을 들고 있는 것을 인식할 수 있는 것은 자바 언어가 지원하는 기능은 
		아니고, 스프링이 지원하는 기능이다.
    
	- 컴포넌트 스캔의 용도 뿐만 아니라 다음 애노테이션이 있으면 
	  스프링은 부가 기능을 수행한다
	  - @Controller: 스프링 MVC 컨트롤러로 인식
	  - @Repository: 스프링 테이터 접근 계층으로 인식하고, 데이터 계층의 예외를
					 스프링 예외로 변환해 준다.
	  - @Configuration: 앞서 보았듯이 스프링 설정 정보로 인식하고,
						스프링 빈이 싱글톤을 유지하도록 추가 처리를 한다.
	  - @Service: 사실 @Service는 특별한 처리를 하지 않는다. 대신 개발자들이
				  핵심 비즈니스 로직이 여기에 있겠구나 라고 비즈니스 계층을
				  인식하는데 도움이 된다.
				  
	참고: useDefaultFilters 옵션은 기본으로 켜져있는데, 이 옵션을 끄면 기본
	    스캔 대상들이 제외된다. 그냥 이런 옵션이 있구나정도 알고 넘어가자.
```

### 필터 
```
  - includeFilters: 컴포넌트 스캔 대상을 추가로 지정한다.
  - excludeFilters: 컴포넌트 스캔에서 제외할 대상을 지정한다.
  
  - includeFilters에 MyIncludeComponent 애노테이션을 추가해서 
    BeanA가 스프링 빈에 등록된다.
  - excludeFilters에 MyExcludeComponent 애노테이션을 추가해서 
    BeanB는 스프링 빈에 등록되지 않는다.
  
  FilterType 옵션
    - FilterType은 5가지 옵션이 있다.
	  - ANNOTATION:기본값, 애노테이션을 인식해서 동작한다.
	    - ex) org.example.SomeAnnotation
	  - ASSIGNABLE_TYPE: 지정한 타입과 자식 타입을 인식해서 동작한다.
	    - ex) org.example.SomeClass
	  - ASPECTJ: AspectJ 패턴 사용
	    - ex) org.example..*Service+
	  - REGEX: 정규 표현식
	    - ex) org\.example\.Default.*
	  - CUSTOM: TypeFilter이라는 인터페이스를 구현해서 처리
	    - ex) org.example.MyTypeFilter
  참고: @Component면 충분하기 때문에 includeFilters를 사용할 일은 거의 없다.
      excludeFilters는 여러가지 이유로 간혹 사용할 때가 있지만 많지는 않다.
	  특히 최근 스프링 부트는 컴포넌트 스캔을 기본으로 제공하는데, 개인적으로는 
	  옵션을 변경하면서 사용하기 보다는 스프링의 기본 설정에 최대한 맞추어 사용
	  하는 것을 권장하고, 선호하는 편이다.
```

### 중복 등록과 충돌
```
  - 컴포넌트 스캔에서 같은 빈 이름을 등록하면 어떻게 될까?
    다음 두 가지 상황이 있다.
	
	1. 자동 빈 등록 vs 자동 빈 등록
	2. 수동 빈 등록 vs 자동 빈 등록

  자동 빈 등록 vs 자동 빈 등록 
    - 컴포넌트 스캔에 의해 자동으로 스프링 빈이 등록되는데, 그 이름이 같은 경우 
	  스프링은 오류를 발생시킨다.
	    - ConflictingBeanDefinitionException 예외 발생
  
  수동 빈 등록 vs 자동 빈 등록 
    - 만약 수동 빈 등록과 자동 빈 등록에서 빈 이름이 충돌되면 어떻게 될까?
	  - 이 경우 수동 빈 등록이 우선권을 가진다.
	    (수동 빈이 자동 빈을 오버라이딩 해버린다.)
	- 수동 빈 등록시 남는 로그 
	  - Overriding bean definition for bean 
	    'memoryMemberRepository' with a different 
		definition: replacing 
      - 물론 개발자가 의도적으로 이런 결과를 기대했다면, 자동 보다는 
	    수동이 우선권을 가지는 것이 좋다. 하지만 현실은 개발자가 의도적으로
		설정해서 이런 결과가 만들어지기 보다는 여러 설정들이 꼬여서 
		이런 결과가 만들어지는 경우가 대부분이다! 그러면 정말 잡기 어려운
		버그가 만들어진다. 항상 잡기 어려운 버그는 애매한 버그다.
		그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 
		충돌이 나면 오류가 발생하도록 기본 값을 바꾸었다.
	- 수동 빈 등록, 자동 빈 등록 오류시 스프링 부트 에러 
	  - Consider renaming one of the beans of enabling
	    overriding by setting spring.main.allow-bean-
		definition-overriding=true
	  - 스프링 부트인 CoreApplication을 실행해보면 오류를 볼 수 있다.
```

## 의존관계 자동 주입

### 다양한 의존관계 주입 방법 
```
  - 의존관계 주입은 크게 4가지 방법이 있다.
	- 생성자 주입
	- 수정자 주입(setter 주입)
	- 필드 주입
	- 일반 메서드 주입 
	
  생성자 주입 
    - 이름 그대로 생성자를 통해서 의존 관계를 주입 받는 방법이다.
	- 지금까지 우리가 진행했던 방법이 바로 생성자 주입이다.
	- 특징 
	  - 생성자 호출 시점에 딱 1번만 호출되는 것이 보장된다.
	  - 불변, 필수 의존관계에 사용
	- 중요! 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다.
	  물론 스프링 빈에만 해당한다.

  수정자 주입
	- setter라 불리는 필드의 값을 변경하는 수정자 메서드를 통해서 의존관계를 
	  주입하는 방법이다.
	- 특징 
	  - 선택, 변경 가능성이 있는 의존관계에 사용
	  - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용하는 방법이다.
	
	참고: @Autowired의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 
	    주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false)
		로 지정하면 된다.
	참고: 자바빈 프로퍼티, 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, setXxx,
		getXxx 라는 메서드를 통해서 값을 읽거나 수정하는 규칙을 만들었는데, 
		그것이 자바빈 프로퍼티 규약이다. 더 자세한 내용이 궁금하면 자바빈 프로퍼티로 
		검색해보자.
		
	필드 주입
	  - 이름 그대로 필드에 바로 주입하는 방법이다.
	  - 특징 
	    - 코드가 간결해서 많은 개발자들을 유혹하지만 외부에서 변경이 불가능해서
		  테스트 하기 힘들다는 치명적인 단점이 있다.
		- DI 프레임워크가 없으면 아무것도 할 수 없다.
		- 사용하지 말자!
		  - 애플리케이션의 실제 코드와 관계 없는 테스트 코드
		  - 스프링 설정을 목적으로 하는 @Configuration 같은 곳에서만
		    특별한 용도로 사용
	
	일반 메서드 주입
	  - 일반 메서드를 통해서 주입 받을 수 있다.
	  - 특징 
	    - 한번에 여러 필드를 주입 받을 수 있다.
		- 일반적으로 잘 사용하지 않는다. 
	  참고: 어쩌면 당연한 이야기이지만 의존관계 자동 주입은 스프링 컨테이너가 
	      관리하는 스프링 빈이어야 동작한다. 스프링 빈이 아닌 Member 같은
		  클래스에서 @Autowired 코드를 적용해도 아무 기능도 동작하지 않는다.
```

### 옵션처리
```
  - 주입할 스프링 빈이 없어도 동작해야 할 때가 있다.
    그런데 @Autowired만 사용하면 required 옵션의 기본값이 true로 되어 있어서
	자동 주입 대상이 없으면 오류가 발생한다.
	
	자동 주입 대상을 옵션으로 처리하는 방법은 다음과 같다.
	  - @Autowired(required=false): 자동 주입할 대상이 없으면 수정자 메서드 자체가 호출 안됨
	  - org.springframework.lang.@Nullable: 자동 주입할 대상이 없으면 null이 입력된다.
	  - Optional<>: 자동 주입할 대상이 없으면 Optinal.empty가 입력된다.
	  
	예제로 확인해 보자
	  - Member는 스프링 빈이 아니다.
	    - setNoBean1()은 @Autowired(required=false)이므로 호출 자체가 안된다.
	  - 출력 결과 
	    noBean3 = Optional.empty
		noBean2 = null
	  참고: @Nullable, Optional은 스프링 전반에 걸쳐서 지원된다. 예를 들어서 생성자 자동 주입에서 
	       특정 필드에만 사용해도 된다.
```

### 생성자 주입을 선택해라!
```
  - 과거에는 수정자 주입과 필드 주입을 많이 사용했지만, 최근에는 스프링을 포함한 DI 프레임워크 대부분이 
    생성자 주입을 권장한다. 그 이유는 다음과 같다.
    
	- 불변 
	  - 대부분의 의존관계 주입은 한번 일어나면 애플리케이션 종료시점까지 의존관계를 변경할 일이 없다.
	    오히려 대부분의 의존관계는 애플리케이션 종료 전까지 변하면 안된다.(불변해야 한다.)
	  - 수정자 주입을 사용하면, setXxx 메서드를 public으로 열어두어야 한다.
	  - 누군가 실수로 변경할 수 도 있고, 변경하면 안되는 메서드를 열어두는 것은 
	    좋은 설계 방법이 아니다.
	  - 생성자 주입은 객체를 생성할 때 딱 1번만 호출되므로 이후에 호출되는 일이 없다. 
	    따라서 불변하게 설계할 수 있다.
	
	- 누락
	  - 프레임워크 없이 순수한 자바 코드를 단위 테스트 하는 경우에
	    수정자 의존관계인 경우 
	    - @Autowired가 프레임워크 안에서 동작할 때는 의존관계가 없으면 오류가 발생하지만, 
		  지금은 프레임워크 없이 순수한 자바 코드로만 단위 테스트를 수행하고 있다.
		- 생성자 주입을 사용하면 다음처럼 주입 데이터를 누락 했을 때 컴파일 오류가 발생한다.
		  그리고 IDE에서 바로 어떤 값을 필수로 주입해야 하는지 알 수 있다.
		  
	
	final 키워드
	  - 생성자 주입을 사용하면 필드에 final 키워드를 사용할 수 있다. 그래서 생성자에서 
	    혹시라도 값이 설정되지 않는 오류를 컴파일 시점에서 막아준다.
	  - 기억하자! 컴파일 오류는 세상에서 가장 빠르고, 좋은 오류다!
	  
	  참고: 수정자 주입을 포함한 나머지 주입 방식은 모두 생성자 이후에 호출되므로, 필드에 
	      final 키워드를 사용할 수 없다. 오직 생성자 주입 방식만 final 키워드를 
		  사용할 수 있다.
	
	정리 
	  - 생성자 주입 방식을 선택하는 이유는 여러가지가 있지만, 프레임워크에 의존하지 않고,
	    순수한 자바 언어의 특징을 잘 살리는 방법이기도 하다.
	  - 기본으로 생성자 주입을 사용하고, 필수 값이 아닌 경우에는 수정자 주입 방식을 옵션으로
	    부여하면 된다. 생성자 주입과 수정자 주입을 동시에 사용할 수 있다.
	  - 항상 생성자 주입을 선택해라! 그리고 가끔 옵션이 필요하면 수정자 주입을 선택해라.
	    필드 주입은 사용하지 않는게 좋다.
```

### 롬복과 최신 트랜드
```
  - 막상 개발을 해보면, 대부분이 다 불변이고, 그래서 다음과 같이 생성자에 final 키워드를 사용하게 된다.
    그런데 생성자도 만들어야 하고, 주입 받은 값을 대입하는 코드도 만들어야 하고...
	필드 주입처럼 좀 편리하게 사용하는 방법은 없을까?
	역시 개발자는 귀찮은 것은 못 참는다!

    - 롬복을 적용해보자.
	- 롬복 라이브러리가 제고하는 @RequiredArgsConstructor 기능을 사용하면 
	  final이 붙은 필드를 모아서 생성자를 자동으로 만들어준다.
	  
	정리 
	  - 최근에는 생성자를 딱 1개 두고, @Autowired를 생략하는 방법을 주로 사용한다.
	    여기에 Lombok 라이브러리의 @RequiredArgsConstructor를 함께 사용하면 
	    기능은 다 제공하면서, 코드는 깔끔하게 사용할 수 있다.	
```





