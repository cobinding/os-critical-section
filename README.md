## os-critical-section
[OS] 임계구역 문제와 Semaphore를 Java로 구현하여 동작 확인

## Critical Section Problem
프로세스들이 임계 구역에서 경쟁 조건(race condition)이 발생하지 않도록 서로 협력하기 위해 사용할 수 있는 프로토콜을 설계해야 한다.
<img width="561" height="351" alt="image" src="https://github.com/user-attachments/assets/aea66621-20bc-4cb3-ab50-5178c4594e30" />

## race condition   
둘 이상의 프로세스/스레드가 공유된 자원에 접근하여 그 결과가 실행 순서에 따라 달라진다.

### race condition이 발생하면?
- 중복 처리: 동일한 주문/결제 등이 중복으로 청구
- 데드락
- 메모리 누수 / 리소스 고갈
- 보안 취약점
- 서비스 장애(무한루프, 커넥션풀 고갈 등)

### 언제 발생하는가
- a. 커널 작업 수행 중에 인터럽트 발생   
  <img width="503" height="232" alt="image" src="https://github.com/user-attachments/assets/34772be7-c779-422b-99c3-e4f17312d5a0" />

- b. 프로세스가 system call하여 커널모드 중에 context switching이 발생   
  <img width="491" height="309" alt="image" src="https://github.com/user-attachments/assets/0dfbe551-1912-4222-895c-d5b2f8959fef" />

- c. 멀티 프로세서에서 공유 메모리 내의 커널 데이터에 접근   
  <img width="485" height="128" alt="image" src="https://github.com/user-attachments/assets/015aa57f-968e-4ca0-917d-163253ee8fbc" />

### 방지할 수 있는 방법
1. Mutual Exception: 동시에 두개 이상의 프로세스는 임계구역에서 실행 불가능하다.
2. Progress: 자신의 임계구역으로 진입하려는 프로세스가 있다면 유한 시간 내에 누가 먼저 들어갈지 결정해주어야 한다.
3. Bounded Waiting (Starvation 방지): 어떤 프로세스도 임계 구역 진입을 영원히 기다리지 않아야 한다.

## Race condition 방지 방법들
> 해당 프로젝트에서는 아래 방식 중, 세마포어에 대해 다룬다.
  - **세마포어: 카운팅 기반 동기화**
  - 뮤텍스: 이진 세마포어, 상호 배제 전용
  - 모니터: 고수준 동기화 구조
  - 스핀락: 바쁜 대기 방식
  - 조건 변수: 특정 조건 대기
  - 원자적 연산: Compare-and-swap, Test-and-set

## OS에서의 동기화 VS Java에서의 동기화
###  목적
- OS: 여러 프로세스/스레드 간의 공유 자원에 대한 접근
- Java: 멀티 스레드 환경에서의 공유 자원에 대한 접근
### 구현 방식
- OS: 세마포어, 뮤텍스, 경쟁 조건 등의 동기화 기법 사용
- Java: synchronized, wait(), notify(), notifyAll() 메서드

## 결과 테스트
<img width="600" height="288" alt="image" src="https://github.com/user-attachments/assets/ddfdcacb-1445-436b-b4de-8e4b3435a8bc" />    
   
> Producer는 1부터 4까지의 값을 바구니에 반복적으로 넣고, Consumer는 바구니에서 값을 하나씩 꺼내 감소시킨다.
> 두 작업 사이에 P(acquire) 함수와 V(release) 함수를 구현하여 경합 상태를 방지하면, 작업 결과에 영향을 주지 않고 프로그램이 정상적으로 동작함을 확인할 수 있다.
> 
> 이제 Producer가 계속해서 값을 넣고 있는데도 Consumer에 Lock이 걸려 있지 않은 상황을 가정해 보자.
> 이 가정을 실험적으로 구현하기 위해, Que 클래스의 smpConsumer.release 메서드를 주석 처리한다.

- acquire(P): Java object 클래스의 `wait()` 활용 - 임계구역을 사용할 수 없을 때, 객체가 가진 고유락을 해제시키고 대기한다. 다른 스레드가 release()를 호출해서 임계 구역에 자원을 넣어줄 수 있도록 한다. 
- release: Java object 클래스의 `notify()` 활용 - 임계구역에 자원을 추가하고, 작업 뒤에 자고 있는 다음 스레드를 깨워서 자원을 소비할 수 잇도록 한다.

<img width="540" height="392" alt="image" src="https://github.com/user-attachments/assets/5735bd78-57d2-424f-93ca-4d08ef408b5e" />   
   
> 이렇게 실행하면 아래와 같이 Producer의 코드만 출력되고 terminal에 커서가 깜빡이면서 무한 loop 상태에 빠지게 된다.
> <img width="543" height="119" alt="image" src="https://github.com/user-attachments/assets/8f597982-d5fb-4da7-b9b0-3e45f436e24c" />

