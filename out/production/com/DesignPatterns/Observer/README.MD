# Observer (Behavioural)

-> when 1 object change the state all dependent object will be notified automatically
-> donot call me, i will call you

-> let us take a nice example
-> order service -> order placed
-> multiple things should react 
-> email, inventory, analytics service
-> order service should not know who all are listening

-> when to use observer pattern
-> multiple component depend on 1 object state
-> you want loose coupling (means in system design components depend on each other as little as possible)
-> means you care about what it does not how it does it
-> Loose coupling = minimal knowledge+ minimal dependency between components
-> you want to add/remove listeners dynamically
-> event driven system


-> structure of Observer pattern
1. Subject -> maintain observers, notify them
2. Observer -> gets notified
3. ConcreteSubject
4. ConcreteObserver