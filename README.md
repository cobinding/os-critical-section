## os-critical-section
[OS] 임계구역 문제와 Semaphore를 Java로 구현하여 동작 확인

## Critical Section Problem
프로세스들이 임계 구역에서 경쟁 조건(race condition)이 발생하지 않도록 서로 협력하기 위해 사용할 수 있는 프로토콜을 설계해야 한다.
<img width="561" height="351" alt="image" src="https://github.com/user-attachments/assets/aea66621-20bc-4cb3-ab50-5178c4594e30" />

## race condition   
둘 이상의 프로세스/스레드가 공유된 자원에 접근하여 그 결과가 실행 순서에 따라 달라진다.
### **언제 발생하는가**
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
