# Task 1.1

## Description

Thread `A` starts thread `B` and `join`s. Uncaught exception happens in thread `B`.
- What happens in thread B?
- What happens in thread A?
- What if thread C joins thread B after exception happened?
- What if thread D joins thread A?

## Requirements

Provide 3 runnable Java samples for these cases, explain results in several sentences.


javac prevents me from writing a code without handling an exception from b, so I'll assume this was the example you've wanted to see
1) B throws an exception -> joins -> a finished and joined too
2) C does not handle exception from B either hence joins without errors too
3) A exits gracefuly, D does the same 
